package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Random;

public class RandomMetric implements FeatureSimilarityMetric {
	
	private Random rand;

	public RandomMetric() {
		rand = new Random();
	}
	
	@Override
	public double similarity(String featureName1, String featureName2) {
		return rand.nextDouble();
	}

}
