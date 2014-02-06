package fr.familiar.operations.heuristics.metrics;

import java.util.Random;

import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.operations.heuristics.SimpleHeuristic;

public class RandomMetric extends SimpleHeuristic implements KSynthesisPlugin {
	
	private Random rand;

	public RandomMetric() {
		rand = new Random();
	}

	@Override
	public String getName() {
		return "Random";
	}

	@Override
	public double similarity(String child, String parent) {
		return rand.nextDouble();
	}

}
