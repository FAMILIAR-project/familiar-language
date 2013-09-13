package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Random;
import java.util.Set;

public class RandomMetric implements FeatureSimilarityMetric {
	
	private Random rand;

	public RandomMetric() {
		rand = new Random();
	}
	
	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
		return rand.nextDouble();
	}

	@Override
	public String toString() {
		return "Random metric";
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
