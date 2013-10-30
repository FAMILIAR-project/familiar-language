package fr.familiar.operations.heuristics.metrics;

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

public class AlwaysZeroMetric implements FeatureSimilarityMetric {

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
		return 0;
	}

	@Override
	public boolean isXorGroupRequired() {
		return false;
	}

	@Override
	public boolean isOrGroupRequired() {
		return false;
	}

}
