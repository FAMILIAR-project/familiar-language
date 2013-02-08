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

import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.hasOrGroup;
import static fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils.hasXorGroup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;
import org.apache.log4j.Logger;
import org.prop4j.Node;
import org.prop4j.NodeWriter;

import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelCloner;
import fr.unice.polytech.modalis.familiar.fm.converter.FeatureModelUtil;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.featureide.NodeUtility;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;

/**
 * @author mathieuacher Converts an FML representation in FeatureIDE textual
 *         specification
 */

public class FMLtoFeatureIDE extends FeatureIDEConverterUtils {
	
	
	private static Logger _LOGGER = Logger.getLogger(FMLtoFeatureIDE.class);

	/**
	 * the feature model variable to process
	 */
	protected FeatureModelVariable _fmv;

	/**
	 * the feature model to process
	 */
	protected FeatureModel<String> _fm;

	private final FeatureGraphFactory<String> _fgf = FeatureGraphFactory
			.mkStringFactory();

	protected FeatureGraph<String> _g;

	public FMLtoFeatureIDE(FeatureModelVariable fmv) {
		_fmv = fmv;
		assert (fmv.getFm() != null);
		_fm = FeatureModelCloner.clone(fmv.getFm()); 
		_g = _fm.getDiagram();
	}

	Map<FeatureNode<String>, String> groupToId = LazyMap.decorate(
			new HashMap<FeatureNode<String>, String>(), new Factory<String>() {

				private int groupId = 1;

				public String create() {
					return "G" + groupId++;
				}

			});

	/**
	 * Inspired from FeatureModelSerializer
	 * 
	 * @return a FML, string-based representation of a feature model
	 */
	public String transform() {

		// transform multi-groups in the feature model (Thuem refactorings)
		FeatureModelUtil.normalizeMultiGroups(_fm);

		final StringBuilder sb = new StringBuilder();

		if (_g.isTop())
			return FeatureGraphFactory.DEFAULT_TOP_STRING;
		else if (_g.isBottom())
			return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

		final Queue<FeatureNode<String>> rest = new LinkedList<FeatureNode<String>>();
		final StringBuilder rootName = new StringBuilder();

		Closure<FeatureNode<String>> processNode = new Closure<FeatureNode<String>>() {

			public void execute(FeatureNode<String> v) {
				Collection<FeatureNode<String>> children = _g.children(v);
				if (children.size() == 0)
					return;

				rest.addAll(children);

				if (v.equals(_g.getTopVertex())) {
					return;
					// sb.append(_fgf.getTopFeature() + ": ");
				}

				else {

					if (FeatureVariable.isRoot(v, _g) && hasOrGroup(_g, v)) {
						sb.append(v.getFeature());
						sb.append("_ : ");
						sb.append(v.getFeature());
						sb.append("+ :: _");
						sb.append(v.getFeature());
						sb.append(" ;\n");
					}

					sb.append((v.getType() == FeatureType.AND_GROUP ? groupToId
							.get(v) : v.getFeature()) + " : ");

				}

				if (FeatureVariable.isRoot(v, _fmv))
					rootName.append(v.getFeature());

				// First, process AND-Groups
				Iterator<FeatureNode<String>> iter = children.iterator();
				while (iter.hasNext()) {
					FeatureNode<String> child = iter.next();
					if (child.getType() == FeatureType.AND_GROUP) {
						// throw new
						// UnsupportedOperationException("Does not support AND-grouping of nodes -- DAGify first!");
						String groupId = groupToId.get(child);
						groupToId.put(child, groupId);

						sb.append(groupId).append("=(");
						for (String f : child.features()) {
							sb.append(f.toString()).append("&");
						}
						sb.deleteCharAt(sb.length() - 1);
						sb.append(")");

						iter.remove();

					}
				}

				// Process Groups
				for (FeatureEdge e : _g.incomingEdges(v)) {
					Set<FeatureNode<String>> sources = _g.getSources(e);

					if (sources.size() == 1)
						continue;

					// sb.append("(");
					for (FeatureNode<String> m : _g.getSources(e)) {
						sb.append(
								m.getType() == FeatureType.AND_GROUP ? groupToId
										.get(m) // throw new
												// UnsupportedOperationException("Does not support AND-grouping of nodes -- DAGify first!");
										: m.getFeature()).append(" | ");
					}
					sb.deleteCharAt(sb.length() - 1); // delete the last " "
					sb.deleteCharAt(sb.length() - 1); // delete the last "|"
					sb.deleteCharAt(sb.length() - 1); // delete the last " "
					// sb.append(")");
					switch (e.getType()) {
					case FeatureEdge.MUTEX:
						throw new UnsupportedOperationException(
								"Does not support MUTEX group -- refactoring needed !");
						// sb.append("?");
						// break;
					case FeatureEdge.OR:
						// sb.append("+");
						break;
					}
					sb.append(" ");
					children.removeAll(sources);
				}

				// Process remaining children
				for (FeatureNode<String> child : children) {
					String strChild = child.getFeature();
					if (_g.findEdge(child, v, FeatureEdge.MANDATORY) == null) {
						if (hasOrGroup(_g, child))
							strChild = strChild + "*"; // OPT + Or-group
						else
							strChild = "[" + strChild + "]"; // OPT but no
																// Or-group
					} else if (hasOrGroup(_g, child)) { // MAND + Or-group
						strChild = strChild + "+";
					}
					sb.append(strChild);
					sb.append(" ");
				}

				if (!(v.equals(_g.getTopVertex()))) {
					String ftName = (v.getType() == FeatureType.AND_GROUP ? groupToId
							.get(v) : v.getFeature());
					if (!hasOrGroup(_g, v) && !hasXorGroup(_g, v)) {
						sb.append(":: _");
						// TODO! sb.append(":: " + ftName + "_"); //(multiple:
						// or/alternative)
						sb.append(ftName);
					}
				}

				sb.append(";");

				sb.append("\n");
			}

			/*
			 * private String groupInformation(FeatureNode<String> v) { if
			 * (isOR(v)) return "+" ; return "" ; }
			 */
		};

		rest.add(_g.getTopVertex());
		while (!rest.isEmpty())
			processNode.execute(rest.poll());

		// process Constraints

		if (!(_fm.getConstraints().isEmpty())) {
			sb.append("%%\n\n");

			for (Expression<String> e : _fm.getConstraints()) {
				Node node = NodeUtility.toNode(e, rootName.toString()) ; 
				if (node != null)
					sb.append(node.toString(NodeWriter.textualSymbols) + " ;\n");
				
			}

		}

		return sb.toString();
	}

	

	public de.ovgu.featureide.fm.core.FeatureModel convert() {
		String strFeatureIDE = transform(); // string-based
														// representation
		de.ovgu.featureide.fm.core.FeatureModel fmide = new de.ovgu.featureide.fm.core.FeatureModel();
		GuidslReader reader = new GuidslReader(fmide);
		try {
			reader.readFromString(strFeatureIDE);
		} catch (UnsupportedModelException e) {
			FMLShell.getInstance().setError("For variable " + _fmv.getIdentifier()
							+ " convertion from FAMILIAR to FeatureIDE failed "
							+ e.getMessage() + " strFeatureIDE="
							+ strFeatureIDE);
			return null;
		}
		return fmide;
	}

	public String convertToString() {
		de.ovgu.featureide.fm.core.FeatureModel fm = convert() ;
		fm.addComment("Generated by FAMILIAR");
		de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter writerFmIDE = new de.ovgu.featureide.fm.core.io.guidsl.GuidslWriter(
				fm);
		return writerFmIDE.writeToString();
	}

}
