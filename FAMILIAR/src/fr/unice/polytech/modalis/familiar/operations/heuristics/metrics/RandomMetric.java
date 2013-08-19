package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import gsd.graph.ImplicationGraph;

import java.util.Random;

public class RandomMetric implements FeatureSimilarityMetric {
	
	private Random rand;

	public RandomMetric() {
		rand = new Random();
	}
	
	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, String featureName1, String featureName2) {
		return rand.nextDouble();
	}

	@Override
	public String toString() {
		return "Random metric";
	}
}
