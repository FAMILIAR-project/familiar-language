package fr.familiar.operations.heuristics.metrics;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.Set;

public class Move {

	private FeatureGraph<String> fmHierarchy;
	private Set<FeatureNode<String>> children;
	private FeatureNode<String> oldParent;
	private FeatureNode<String> newParent;

	public Move(FeatureGraph<String> fmHierarchy, Set<FeatureNode<String>> children, FeatureNode<String> oldParent, FeatureNode<String> newParent) {
		this.fmHierarchy = fmHierarchy;
		this.children = children;
		this.oldParent = oldParent;
		this.newParent = newParent;
	}

	public void perform() {
		for (FeatureNode<String> child : children) {
			// Remove old parent
			fmHierarchy.removeEdge(fmHierarchy.findEdge(child, oldParent, FeatureEdge.HIERARCHY));
			
			// Add the new parent
			fmHierarchy.addEdge(child, newParent, FeatureEdge.HIERARCHY);
		}
	}

	public int getCost() {
		return 1;
	}

	@Override
	public String toString() {
		return children + " : " + oldParent + " -> " + newParent;
	}
}
