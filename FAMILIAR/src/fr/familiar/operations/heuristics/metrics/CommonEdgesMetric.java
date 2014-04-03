/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations.heuristics.metrics;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class CommonEdgesMetric {


	/**
	 * Number of common edges of the hierarchies of the two feature models 
	 * @param fm1
	 * @param fm2
	 * @return
	 */
	public int commonEdges(FeatureModelVariable fm1, FeatureModelVariable fm2) {
		int nbOfCommonEdges = 0;

		FeatureGraph<String> hierarchy1 = fm1.getFm().getDiagram();
		FeatureGraph<String> hierarchy2 = fm2.getFm().getDiagram();

		for (FeatureEdge edge : hierarchy1.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY) {
				String source1 = hierarchy1.getSource(edge).getFeature();
				String target1 = hierarchy1.getTarget(edge).getFeature();
				FeatureNode<String> source2 = hierarchy2.findVertex(source1);
				FeatureNode<String> target2 = hierarchy2.findVertex(target1);
				if (hierarchy2.containsEdge(source2, target2, FeatureEdge.HIERARCHY)) {
					nbOfCommonEdges++;
				}
			}
		}

		return nbOfCommonEdges;
	}

}
