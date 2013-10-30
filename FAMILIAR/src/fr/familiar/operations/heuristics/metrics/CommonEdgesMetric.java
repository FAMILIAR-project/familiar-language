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
