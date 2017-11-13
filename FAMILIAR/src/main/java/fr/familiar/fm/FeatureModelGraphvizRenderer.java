/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.fm;

import gsd.graph.GraphvizGraph.ArrowType;
import gsd.graph.GraphvizGraph.RankDir;
import gsd.graph.GraphvizGraph.Shape;
import gsd.graph.GraphvizProperties;
import gsd.graph.IGraphvizLabelProvider;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author mathieuacher
 * 
 */
public class FeatureModelGraphvizRenderer<T> {

	private static int _idIncrementor = 0;

	protected IGraphvizLabelProvider<FeatureEdge> mEdgeLbl = new IGraphvizLabelProvider<FeatureEdge>() {
		public String getLabel(FeatureEdge edge) {
			switch (edge.getType()) {
			case FeatureEdge.MANDATORY:
				return "mandatory";
			case FeatureEdge.XOR:
				return "XOR";
			case FeatureEdge.MUTEX:
				return "MUTEX";
			case FeatureEdge.OR:
				return "OR";
			default:
				break;
			}
			return "";
		}
	};

	protected IGraphvizLabelProvider<FeatureNode<T>> mVertexLbl = new IGraphvizLabelProvider<FeatureNode<T>>() {
		public String getLabel(FeatureNode<T> vertex) {
			return vertex.toString().replace("\"", "\\\"");
		}
	};

	protected GraphvizProperties<FeatureNode<T>, FeatureEdge> mProps = new GraphvizProperties<FeatureNode<T>, FeatureEdge>() {

		@Override
		public String getEdgePropertiesString(FeatureEdge edge) {
			String width = "0.3";
			switch (edge.getType()) {
			case FeatureEdge.XOR:
			case FeatureEdge.MUTEX:
				return "shape=" + "\"" + "triangle" + "\"" + "," + "width="
						+ width;

			case FeatureEdge.OR:
				return "shape=" + "\"" + "triangle" + "\"" + "," + "style="
						+ "\"" + "filled" + "\"" + "," + "color=" + "\""
						+ "black" + "\"" + "," + "fontcolor=" + "\"" + "white"
						+ "\"";
			default:
				break;
			}
			return "";
		}

	};

	private FeatureGraph<T> _fg;

	private String _fmID;

	public FeatureModelGraphvizRenderer(FeatureGraph<T> fg) {
		this(fg, "fm");
	}

	public FeatureModelGraphvizRenderer(FeatureGraph<T> fg, String fmID) {
		_fg = fg;
		_fmID = fmID;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("digraph " + _fmID + " {\n");
		b.append("graph [");
		// b.append("nodesep=0.3\n");
		b.append("rankdir=" + RankDir.TOP_BOTTOM);
		b.append("];\n");
		b.append("node [shape=" + Shape.BOX + "];\n");
		b.append("edge [arrowhead=" + ArrowType.NORMAL + "];\n");

		Map<FeatureNode<T>, Integer> vertexIdMap = new HashMap<FeatureNode<T>, Integer>();
		Iterator<? extends FeatureNode<T>> iter = _fg.vertices().iterator();
		while (iter.hasNext()) {
			_idIncrementor++;
			FeatureNode<T> fnvertex = iter.next();

			if (!fnvertex.isBottom() && !fnvertex.isTop()) {

				vertexIdMap.put(fnvertex, _idIncrementor);
				b.append(_idIncrementor);
				b.append("[label=\"");
				b.append(mVertexLbl.getLabel(fnvertex));
				b.append("\",");
				b.append(mProps.getVertexPropertiesString(fnvertex));
				b.append("]\n");
			}
		}

		Map<FeatureEdge, Integer> edgeIdMap = new HashMap<FeatureEdge, Integer>();
		int j = _idIncrementor + 1;
		Iterator<? extends FeatureEdge> edgeIter = _fg.edges().iterator();
		while (edgeIter.hasNext()) {
			FeatureEdge fe = edgeIter.next();

			FeatureNode<T> fntarget = _fg.getTarget(fe);
			if (fntarget.isBottom() || fntarget.isTop())
				continue;

			if (fe.isBinary()) {
				FeatureNode<T> fnsource = _fg.getSource(fe);

				boolean isMandatory = FeatureNodeUtils.isMandatory(
						(FeatureGraph<String>) _fg,
						(FeatureNode<String>) fnsource);
				boolean isOptional = FeatureNodeUtils.isOptional(
						(FeatureGraph<String>) _fg,
						(FeatureNode<String>) fnsource);
				if ((isMandatory && fe.getType() != FeatureEdge.HIERARCHY)
						|| isOptional) {

					// inverse
					b.append(vertexIdMap.get(fntarget));
					b.append("->");
					String shape = isOptional ? "odot" : "dot";
					b.append(vertexIdMap.get(fnsource));
					b.append("[arrowhead=");
					b.append(shape); // mEdgeLbl.getLabel(fe)
					b.append("];\n");

				}

			}
			// Xor/Or-groups
			else {

				// register Xor/Or edges
				edgeIdMap.put(fe, ++j);
				b.append(j);
				b.append("[label=\"");
				b.append(mEdgeLbl.getLabel(fe));
				b.append("\",");
				b.append(mProps.getEdgePropertiesString(fe));
				b.append("]\n");

				b.append(vertexIdMap.get(fntarget));
				b.append("->");
				b.append(edgeIdMap.get(fe));
				b.append("[arrowhead=none];\n"); // , headport=n, tailport=s

				Set<FeatureNode<T>> fnsources = _fg.getSources(fe);
				for (FeatureNode<T> fn : fnsources) {
					b.append(edgeIdMap.get(fe));
					b.append("->");
					b.append(vertexIdMap.get(fn));
					b.append("[arrowhead=none];\n");
				}

			}

		}

		b.append("}");

		_idIncrementor = j++;

		return b.toString();
	}

}
