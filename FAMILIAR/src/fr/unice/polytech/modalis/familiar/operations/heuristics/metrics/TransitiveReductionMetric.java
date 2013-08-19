package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Set;

import gsd.graph.ImplicationGraph;
import gsd.graph.TransitiveReduction;

public class TransitiveReductionMetric implements FeatureSimilarityMetric {

	private ImplicationGraph<Set<String>> reducedGraph;

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, String feature, String parent) {
		 // TODO : check performance of equals compared to the reduction
		if (reducedGraph == null || !reducedGraph.equals(implicationGraph)) {
			reducedGraph = implicationGraph.reduceCliques();
			TransitiveReduction.INSTANCE.reduce(reducedGraph);	
		}
		
		Set<String> featureVertex = findVertex(feature);
		Set<String> parentVertex = findVertex(parent);
		return reducedGraph.containsEdge(featureVertex, parentVertex) ? 1 : 0;
	
	}
	
	/**
	 * Find the vertex of reducedGraph containing the feature
	 * @param feature
	 * @return vertex of reducedGraph containing feature
	 */
	private Set<String> findVertex(String feature) {
		for (Set<String> vertex : reducedGraph.vertices()) {
			if (vertex.contains(feature)) {
				return vertex;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return "Transitive reduction metric";
	}
 

}
