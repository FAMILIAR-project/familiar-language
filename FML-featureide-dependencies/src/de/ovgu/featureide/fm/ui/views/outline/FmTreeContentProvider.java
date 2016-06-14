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
package de.ovgu.featureide.fm.ui.views.outline;

import static de.ovgu.featureide.fm.core.localization.StringTable.CONSTRAINTS;
import static de.ovgu.featureide.fm.core.localization.StringTable.NO_DATA_TO_DISPLAY_AVAILABLE_;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.ovgu.featureide.fm.core.Constraint;
import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureModel;

/**
 * This class is part of the outline. It provides the content that should be
 * displayed. Therefore it maps the information provided by the fmProject to the
 * methods of the ITreeContentProvider interface.
 * 
 * @author Jan Wedding
 * @author Melanie Pflaume
 */
public class FmTreeContentProvider implements ITreeContentProvider {

	private FeatureModel fModel;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		if (newInput != null && newInput instanceof FeatureModel)
			fModel = ((FeatureModel) newInput);

	}

	@Override
	public Object[] getElements(Object inputElement) {
		Object[] elements;
		if (fModel != null && fModel.getRoot() != null) {
			elements = new Object[2];
			elements[0] = fModel.getRoot();
			elements[1] = CONSTRAINTS;
			return elements;
		}

		return new String[] { NO_DATA_TO_DISPLAY_AVAILABLE_ };
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement == null)
			return null;

		// we have a String as parent of constraints
		if (parentElement instanceof String && CONSTRAINTS.equals(parentElement)) {
			Object[] elements = new Object[fModel.getConstraintCount()];
			List<Constraint> cList = fModel.getConstraints();
			for (int i = 0; i < fModel.getConstraintCount(); i++) {
				elements[i] = cList.get(i);
			}
			return elements;
		}

		// we store the group stage into an extra object in order to be able to
		// show an own element for GroupStages
		if (parentElement instanceof FmOutlineGroupStateStorage) {
			return featureListToArray(((FmOutlineGroupStateStorage) parentElement).getFeature().getChildren());
		}

		if (!(parentElement instanceof Feature))
			return null;
		if (!((Feature) parentElement).hasChildren())
			return null;

		return featureListToArray(((Feature) parentElement).getChildren());
	}

	/**
	 * converts a list of Features into an Array
	 * 
	 * @param f
	 *            FeatureList
	 * @return Array of Features
	 */
	private Feature[] featureListToArray(LinkedList<Feature> f) {
		Feature[] fArray = new Feature[f.size()];
		for (int i = 0; i < f.size(); i++) {
			fArray[i] = f.get(i);
		}
		return fArray;
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof Feature)
			return ((Feature) element).getParent();
		else if (element instanceof Constraint)
			return CONSTRAINTS;
		else if (element instanceof FmOutlineGroupStateStorage)
			return ((FmOutlineGroupStateStorage) element).getFeature();

		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof Feature)
			return ((Feature) element).hasChildren();
		else if (element instanceof FmOutlineGroupStateStorage)
			return true;
		else if (element instanceof String)
			if (CONSTRAINTS.equals(element))
				return fModel.getConstraintCount() > 0;

		return false;
	}

}
