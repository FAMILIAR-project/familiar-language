package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import gsd.graph.ImplicationGraph;

public class AlwaysZeroMetric implements FeatureSimilarityMetric {

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, String featureName1, String featureName2) {
		return 0;
	}

}
