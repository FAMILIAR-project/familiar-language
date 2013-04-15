package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public interface FMEditDistanceMetric {

	public int editDistance(FeatureModelVariable fm, FeatureModelVariable reference);
	
}
