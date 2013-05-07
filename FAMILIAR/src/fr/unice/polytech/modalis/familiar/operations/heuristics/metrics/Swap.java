package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

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
