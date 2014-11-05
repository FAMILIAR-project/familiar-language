/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.fm.featureide;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.xtext.example.mydsl.fML.AutoConfMode;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.SelectableFeature;
import de.ovgu.featureide.fm.core.configuration.Selection;
import fr.familiar.interpreter.FMLShell;

public class MinMaxConfiguration extends Configuration {

	private AutoConfMode mode;

	protected static final int TIMEOUT_MINMAX = 300000;

	public MinMaxConfiguration(
			de.ovgu.featureide.fm.core.configuration.Configuration configuration,
			AutoConfMode mode) {
		super(configuration.getFeatureModel());

		FMLShell.getInstance().printDebugMessage(
				"### (init) selected:" + configuration.getSelectedFeatures());
		for (Feature ft : configuration.getSelectedFeatures()) {
			setManual(ft.getName(), Selection.SELECTED);
		}
		FMLShell.getInstance().printDebugMessage(
				"### (init) deselected:"
						+ configuration.getUnSelectedFeatures());
		for (Feature ft : configuration.getUnSelectedFeatures()) {
			setManual(ft.getName(), Selection.UNSELECTED);
		}

		this.mode = mode;
	}

	/*
	 * Mathieu: I have put the exception handling directly into the method
	 */
	public void updateManualUndefinedValues() {
		List<Node> children = new LinkedList<Node>();
		try {
			for (SelectableFeature feature : getFeaturesList())
				if (feature.getManual() != Selection.UNDEFINED) {
					Literal literal = new Literal(feature.getName());
					literal.positive = feature.getManual() == Selection.SELECTED;
					children.add(literal);
				}
			Node node = new And(getNodeRootField().clone(), new And(children));
			if (mode != null) {
				SatSolver solver = new MinMaxSatSolver(node, TIMEOUT_MINMAX,
						mode);
				for (Literal literal : solver.knownValues()) {
					// FMShell.getInstance().printDebugMessage("l : " +
					// literal);
					SelectableFeature feature = getTableField()
							.get(literal.var);
					// if (feature.getManual() == Selection.UNDEFINED)
					Class sfc = feature.getClass(); // TODO check
					Method setAutomaticMethod = sfc
							.getDeclaredMethod("SelectableFeature");
					setAutomaticMethod.invoke(feature,
							literal.positive ? Selection.SELECTED
									: Selection.UNSELECTED);
					// feature.setAutomatic(literal.positive ?
					// Selection.SELECTED: Selection.UNSELECTED);
				}
			}
		} catch (Exception e) {

		}
	}

	public ArrayList<SelectableFeature> getFeaturesList()
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class c = this.getClass();
		Field featuresField = c.getDeclaredField("features");
		featuresField.setAccessible(true);
		return (ArrayList<SelectableFeature>) featuresField.get(this);
	}

	public Node getNodeRootField() throws SecurityException,
			NoSuchFieldException, IllegalArgumentException,
			IllegalAccessException {
		Class c = this.getClass();
		Field nodeField = c.getDeclaredField("rootNode");
		nodeField.setAccessible(true);
		return (Node) nodeField.get(this);
	}

	public Hashtable<String, SelectableFeature> getTableField()
			throws SecurityException, NoSuchFieldException,
			IllegalArgumentException, IllegalAccessException {
		Class c = this.getClass();
		Field tableField = c.getDeclaredField("table");
		tableField.setAccessible(true);
		return (Hashtable<String, SelectableFeature>) tableField.get(this);
	}
}
