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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.sat4j.specs.TimeoutException;

import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.SelectableFeature;
import de.ovgu.featureide.fm.core.configuration.Selection;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

public class AllConfigsSAT extends Configuration {

	protected static final int TIMEOUT_MINMAX = 300000;
	private FeatureModelVariable fmw;

	private AllConfigsSAT(Configuration c, FeatureModelVariable fmw) {
		super(c.getFeatureModel());
		this.fmw = fmw;

	}

	public static AllConfigsSAT make(FeatureModelVariable fmw) {
		de.ovgu.featureide.fm.core.FeatureModel fm = new FMLtoFeatureIDE(fmw).convert();

		FMLShell.getInstance().printDebugMessage(
				new GuidslWriter(fm).writeToString());

		de.ovgu.featureide.fm.core.configuration.Configuration cf = new de.ovgu.featureide.fm.core.configuration.Configuration(
				fm, true);
		return new AllConfigsSAT(cf, fmw);
	}

	public Set<Variable> process() throws SecurityException,
			IllegalArgumentException, NoSuchFieldException,
			IllegalAccessException {

		List<Node> children = new LinkedList<Node>();
		for (SelectableFeature feature : getFeaturesList())
			if (feature.getManual() != Selection.UNDEFINED) {
				Literal literal = new Literal(feature.getName());
				literal.positive = feature.getManual() == Selection.SELECTED;
				children.add(literal);
			}
		Node node = new And(getNodeRootField().clone(), new And(children));
		AllConfigsSolver solver = new AllConfigsSolver(node, TIMEOUT_MINMAX);

		Set<Variable> vars = new HashSet<Variable>();
		try {
			List<List<String>> sols = solver.getSolutions();

			for (List<String> sol : sols) {

				Set<Variable> fts = new HashSet<Variable>();

				for (String ftName : sol) {
					FeatureVariable ft = new FeatureVariable(null, ftName,
							this.fmw); // no name
					fts.add(ft);
				}

				SetVariable sv = new SetVariable(fts, null); // no name
				vars.add(sv);

			}

		} catch (TimeoutException e) {
			e.printStackTrace();
		}

		return vars;
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
}
