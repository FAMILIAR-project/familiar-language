package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

public interface FeatureFrequencyMetric {

	double support(String featureName1, String featureName2);
	double confidence(String featureName1, String featureName2);
	
	}
