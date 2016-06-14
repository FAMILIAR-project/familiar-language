/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2015  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 * 
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.ui.editors;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;

import de.ovgu.featureide.fm.core.Constraint;
import de.ovgu.featureide.fm.core.FMPoint;
import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureConnection;
import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.PropertyConstants;
import de.ovgu.featureide.fm.ui.editors.featuremodel.editparts.ConnectionEditPart;
import de.ovgu.featureide.fm.ui.editors.featuremodel.figures.LegendFigure;

/**
 * this is a hack to quickly associate features with dimension and size (which
 * is not available in the model). luckily these informations do not need to be
 * stored persistently.
 * 
 * @author Christian Kaestner
 */
public class FeatureUIHelper {

	private static final WeakHashMap<Feature, Point> featureLocation = new WeakHashMap<>();
	private static final WeakHashMap<Feature, Dimension> featureSize = new WeakHashMap<>();
	private static final WeakHashMap<Constraint, Point> constraintLocation = new WeakHashMap<>();
	private static final WeakHashMap<Constraint, Dimension> constraintSize = new WeakHashMap<>();
	private static final WeakHashMap<FeatureModel, Dimension> legendSize = new WeakHashMap<>();
	private static final WeakHashMap<FeatureModel, LegendFigure> legendFigure = new WeakHashMap<>();

	/**
	 * Necessary for correct manual drag-and-drop movement while zoomed.
	 */
	private static double zoomFactor = 1.0;
	private static ZoomManager zoomManager = null;

	public static Dimension getLegendSize(FeatureModel featureModel) {
		return legendSize.get(featureModel);
	}

	public static boolean showHiddenFeatures(FeatureModel featureModel) {
		return featureModel.getLayout().showHiddenFeatures();
	}

	public static void showHiddenFeatures(boolean show, FeatureModel featureModel) {
		featureModel.getLayout().showHiddenFeatures(show);
	}

	public static void setLegendSize(FeatureModel featureModel, Dimension dim) {
		legendSize.put(featureModel, dim);
	}

	public static Point getLocation(Feature feature) {
		return featureLocation.get(feature);
	}

	public static void setLocation(Feature feature, FMPoint newLocation) {
		setLocation(feature, toPoint(newLocation));
	}

	public static void setLocation(Feature feature, Point newLocation) {
		Point oldLocation = getLocation(feature);
		feature.setNewLocation(toFMPoint(newLocation));
		if (newLocation == null) {
			return;
		}
		featureLocation.put(feature, newLocation);
		fireLocationChanged(feature, oldLocation, newLocation);
	}

	public static void setTemporaryLocation(Feature feature, Point newLocation) {
		Point oldLocation = getLocation(feature);
		if (newLocation == null || newLocation.equals(oldLocation)) {
			return;
		}
		featureLocation.put(feature, newLocation);
		fireLocationChanged(feature, oldLocation, newLocation);
	}

	public static Dimension getSize(Feature feature) {
		return featureSize.get(feature);
	}

	public static void setSize(Feature feature, Dimension size) {
		featureSize.put(feature, size);
	}

	public static Rectangle getBounds(Feature feature) {
		if (getLocation(feature) == null || getSize(feature) == null) {
			// UIHelper not set up correctly, refresh the feature model
			feature.getFeatureModel().handleModelDataChanged();
		}
		return new Rectangle(getLocation(feature), getSize(feature));
	}

	public static Rectangle getBounds(Constraint constraint) {
		if (getLocation(constraint) == null || getSize(constraint) == null) {
			// UIHelper not set up correctly, refresh the feature model
			constraint.getFeatureModel().handleModelDataChanged();
		}
		return new Rectangle(getLocation(constraint), getSize(constraint));
	}

	public static List<ConnectionEditPart> getConnections(Feature feature, EditPartViewer viewer) {
		final List<ConnectionEditPart> editPartList = new LinkedList<ConnectionEditPart>();
		final Map<?, ?> registry = viewer.getEditPartRegistry();
		for (FeatureConnection connection : feature.getTargetConnections()) {
			final Object connectionEditPart = registry.get(connection);
			if (connectionEditPart instanceof ConnectionEditPart) {
				editPartList.add((ConnectionEditPart) connectionEditPart);
			}
		}
		return editPartList;
	}

	private static void fireLocationChanged(Feature feature, Point oldLocation, Point newLocation) {
		PropertyChangeEvent event = new PropertyChangeEvent(feature, PropertyConstants.LOCATION_CHANGED, oldLocation, newLocation);
		feature.fire(event);
	}

	public static Point getReferencePoint(Feature feature) {
		return getBounds(feature).getCenter();
	}

	public static Point calculateReferencePoint(Feature feature, Point newLocation) {
		return new Rectangle(newLocation, getSize(feature)).getCenter();
	}

	public static Point getSourceLocation(Feature feature) {
		Feature parentFeature = feature;
		boolean parentFeatureHidden = false;
		while (!parentFeature.isRoot()) {
			parentFeature = parentFeature.getParent();
			if (parentFeature.isHidden()) {
				parentFeatureHidden = true;
			}
		}
		if ((feature.isHidden() || parentFeatureHidden) && !feature.getFeatureModel().getLayout().showHiddenFeatures()) {
			return getTargetLocation(feature.getParent());
		}

		return getSourceLocation(getBounds(feature), feature.getFeatureModel());
	}

	public static Point getSourceLocation(Feature feature, Point newLocation) {
		return getSourceLocation(new Rectangle(newLocation, getSize(feature)), feature.getFeatureModel());
	}

	private static Point getSourceLocation(Rectangle bounds, FeatureModel featureModel) {
		if (featureModel.getLayout().verticalLayout()) {
			return new Point(bounds.getLeft().x, (bounds.bottom() + bounds.getTop().y) / 2);
		} else {
			return new Point(bounds.getCenter().x, bounds.y);
		}
	}

	public static Point getTargetLocation(Feature feature) {
		Rectangle bounds = getBounds(feature);
		if (feature.getFeatureModel().getLayout().verticalLayout()) {
			return new Point(bounds.getRight().x, (bounds.bottom() + bounds.getTop().y) / 2);
		}

		return new Point(bounds.getCenter().x, bounds.bottom() - 1);

	}

	public static void setVerticalLayoutBounds(boolean isVerticalLayout, FeatureModel featureModel) {
		featureModel.getLayout().verticalLayout(isVerticalLayout);
	}

	public static boolean hasVerticalLayout(FeatureModel featureModel) {
		return featureModel.getLayout().verticalLayout();
	}

	public static Dimension getSize(Constraint constraint) {
		return constraintSize.get(constraint);
	}

	public static void setSize(Constraint constraint, Dimension size) {
		constraintSize.put(constraint, size);
	}

	public static Point getLocation(Constraint constraint) {
		return constraintLocation.get(constraint);
	}

	public static void setLocation(Constraint constraint, FMPoint newLocation) {
		setLocation(constraint, toPoint(newLocation));
	}

	public static void setLocation(Constraint constraint, Point newLocation) {
		Point oldLocation = getLocation(constraint);
		if (newLocation == null || newLocation.equals(oldLocation)) {
			return;
		}
		constraintLocation.put(constraint, newLocation);
		fireLocationChanged(constraint, oldLocation, newLocation);
		constraint.setLocation(toFMPoint(newLocation));
	}

	private static void fireLocationChanged(Constraint constraint, Point oldLocation, Point newLocation) {
		PropertyChangeEvent event = new PropertyChangeEvent(constraint, PropertyConstants.LOCATION_CHANGED, oldLocation, newLocation);
		constraint.fire(event);
	}

	public static void setLegendFigure(FeatureModel featureModel, LegendFigure figure) {
		legendFigure.put(featureModel, figure);
	}

	public static LegendFigure getLegendFigure(FeatureModel featureModel) {
		return legendFigure.get(featureModel);
	}

	public static Point toPoint(FMPoint point) {
		return new Point(point.getX(), point.getY());
	}

	public static FMPoint toFMPoint(Point point) {
		return new FMPoint(point.x, point.y);
	}

	/**
	 * @return the zoomFactor
	 */
	public static double getZoomFactor() {
		return zoomFactor;
	}

	/**
	 * @param zoomFactor
	 *            the zoomFactor to set
	 */
	public static void setZoomFactor(double zoomFactor) {
		FeatureUIHelper.zoomFactor = zoomFactor;
	}

	/**
	 * @param zoomManager
	 */
	public static void setZoomManager(ZoomManager zoomManager) {
		FeatureUIHelper.zoomManager = zoomManager;
		if (zoomManager == null) {
			return;
		}
		zoomManager.addZoomListener(new ZoomListener() {
			@Override
			public void zoomChanged(double newZoomFactor) {
				FeatureUIHelper.zoomFactor = newZoomFactor;
			}
		});
	}

	/**
	 * @return the zoomManager
	 */
	public static ZoomManager getZoomManager() {
		return zoomManager;
	}
}
