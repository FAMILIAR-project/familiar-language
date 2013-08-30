package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

public class AlwaysZeroMetric implements FeatureSimilarityMetric {

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
		return 0;
	}

}
