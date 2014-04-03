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

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.HashSet;
import java.util.Set;

public class Swap implements Comparable<Swap> {
	private FeatureGraph<String> hierarchy;
	private FeatureNode<String> feature1, feature2;
	private int distance;


	public Swap(FeatureGraph<String> hierarchy, FeatureNode<String> feature1,
			FeatureNode<String> feature2, int distance) {
		this.hierarchy = hierarchy;
		this.feature1 = feature1;
		this.feature2 = feature2;
		this.distance = distance;
	}

	@Override
	public int compareTo(Swap o) {
		return o.distance - distance;
	}

	public void perform() {
		// Save original parents and children of both features
		Set<FeatureNode<String>> parents1 = hierarchy.parents(feature1);
		Set<FeatureNode<String>> children1 = hierarchy.children(feature1);
		Set<FeatureNode<String>> parents2 = hierarchy.parents(feature2);
		Set<FeatureNode<String>> children2 = hierarchy.children(feature2);
		
		// Delete incident edges of feature1 and feature2
		hierarchy.removeAllEdges(new HashSet<FeatureEdge>(
				hierarchy.incidentEdges(feature1)));
		hierarchy.removeAllEdges(new HashSet<FeatureEdge>(
				hierarchy.incidentEdges(feature2)));

		// Create new edges from original edges
		createEdges(feature1, parents2, children2, feature2);
		createEdges(feature2, parents1, children1, feature1);
	}

	private void createEdges(FeatureNode<String> feature,
			Set<FeatureNode<String>> parents,
			Set<FeatureNode<String>> children, FeatureNode<String> otherFeature) {

		for (FeatureNode<String> parent : parents) {
			if (!feature.equals(parent)) { 
				hierarchy.addEdge(feature, parent, FeatureEdge.HIERARCHY);	
			} else {
				hierarchy.addEdge(feature, otherFeature, FeatureEdge.HIERARCHY);
			}
		}

		for (FeatureNode<String> child : children) {
			if (!feature.equals(child)) { 
				hierarchy.addEdge(child, feature, FeatureEdge.HIERARCHY);	
			} else {
				hierarchy.addEdge(otherFeature, feature, FeatureEdge.HIERARCHY);
			}
		}
	}

	public int getCost() {
		return distance;
	}
}
