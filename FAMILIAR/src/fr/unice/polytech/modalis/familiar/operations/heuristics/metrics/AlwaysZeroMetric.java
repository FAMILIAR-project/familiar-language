package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

public class AlwaysZeroMetric implements FeatureSimilarityMetric {

	@Override
	public double similarity(String featureName1, String featureName2) {
		return 0;
	}

}
