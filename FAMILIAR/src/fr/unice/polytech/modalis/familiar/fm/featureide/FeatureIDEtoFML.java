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
package fr.unice.polytech.modalis.familiar.fm.featureide;

import java.util.LinkedList;

import org.prop4j.Node;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.AbstractFeatureModelWriter;
import fr.unice.polytech.modalis.familiar.operations.FeatureIDEUtils;

/**
 * @author mathieuacher TODO: highly buggy!
 */

public class FeatureIDEtoFML extends AbstractFeatureModelWriter {

	
	/**
	 * 
	 */
	public FeatureIDEtoFML(FeatureModel fm) {
		featureModel = fm;
	}

	@Override
	public String writeToString() {
		StringBuilder sb = new StringBuilder();

		sb.append("FM ( ");

		writeGrammarDefinition(sb);
		sb.append("\n");
		writePropositionalConstraints(sb);

		sb.append(" )");
		return sb.toString();
	}

	private void writeGrammarDefinition(StringBuilder out) {
		Feature root = featureModel.getRoot();
		if (root != null) {
			/*
			 * if (root.isOr()) { out.append(root.getName());
			 * out.append("_ : "); out.append(root.getName());
			 * out.append("+ :: _"); out.append(root.getName());
			 * out.append(" ;\r\n\r\n"); }
			 */
			writeRule(featureModel.getRoot(), out);
		} else
			out.append("\r\n");
	}

	private void writePropositionalConstraints(StringBuilder out) {
		if (featureModel.getPropositionalNodes().isEmpty())
			return;
		for (Node node : featureModel.getPropositionalNodes())
			out.append(_normalize(node.toString(FeatureIDEUtils._textualSymbols)) + " ; ");
	}

	private void writeRule(Feature mainFeature, StringBuilder out) {
		// check if there is a rule to write
		if (!mainFeature.hasChildren())
			return;
		LinkedList<Feature> mainChildren = mainFeature.getChildren();

		// left part of the rule
		out.append(_normalize(mainFeature.getName()));
		out.append(" : ");

		// check if a output over more than one line is possible
		boolean moreThanOneLine = isMoreThanOneLinePossible(mainFeature,
				mainChildren);
		// moreThanOneLine = !mainFeature.isAND();

		// write out the line(s)

		if (!mainFeature.isAnd())
			out.append("(");

		for (int i = 0; i < mainChildren.size(); i++) {
			Feature feature = mainChildren.get(i);
			// if (moreThanOneLine && i > 0) {
			// out.append("\r\n\t|");
			// }
			// else
			if (!mainFeature.isAnd() && i > 0) {
				out.append(" | ");
			}
			if (!mainFeature.isAnd() && feature.hasInlineRule()) {
				LinkedList<Feature> children = feature.getChildren();
				for (int j = 0; j < children.size(); j++) {
					out.append(" ");
					out.append(getRightGrammarToken(children.get(j)));
				}
				// out.append(" :: ");
				// out.append(feature.getName());
			} else {
				out.append(" ");
				out.append(getRightGrammarToken(feature));
				if (!mainFeature.isAnd()
						&& (!feature.isMandatory() || feature.isMultiple())) {
					// out.append(" :: ");
					// out.append(feature.getName() + "_");
				}
			}
		}
		if (mainFeature.isAnd()) {// && mainChildren.size() > 1) {
			// out.append(" :: _");
			// out.append(mainFeature.getName());
		}

		if (!mainFeature.isAnd())
			out.append(")");
		if (mainFeature.isMultiple()) {
			out.append(mainFeature.isMandatory() ? "+" : "");
		}

		out.append(" ; ");
		// out.append(" ;\r\n\r\n");

		// write all left rules
		writeChildRules(mainFeature, mainChildren, out);
	}

	public static String getRightGrammarToken(Feature feature) {
		return feature.isMandatory() ? _normalize(feature.getName()) : "["
				+ _normalize(feature.getName()) + "]";
	}

	private static String _normalize(String name) {
		return name.replace("+", "PLUS").replace("$", "DOLLAR");
	}

	private boolean isMoreThanOneLinePossible(Feature feature,
			LinkedList<Feature> children) {
		if (!feature.isAnd()) {
			for (int i = 0; i < children.size(); i++) {
				Feature child = children.get(i);
				if (child.hasInlineRule()) {
					return true;
				}
			}
		}
		return false;
	}

	private void writeChildRules(Feature mainFeature,
			LinkedList<Feature> mainChildren, StringBuilder out) {
		for (int i = 0; i < mainChildren.size(); i++) {
			Feature feature = mainChildren.get(i);
			if (!mainFeature.isAnd() && feature.hasInlineRule()) {
				LinkedList<Feature> children = feature.getChildren();
				for (int j = 0; j < children.size(); j++) {
					writeRule(children.get(j), out);
				}
			} else {
				writeRule(feature, out);
			}
		}
	}

}
