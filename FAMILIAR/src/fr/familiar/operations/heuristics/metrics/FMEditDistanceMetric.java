package fr.familiar.operations.heuristics.metrics;

import fr.familiar.variable.FeatureModelVariable;

public interface FMEditDistanceMetric {

	public int editDistance(FeatureModelVariable fm, FeatureModelVariable reference);
	
}
