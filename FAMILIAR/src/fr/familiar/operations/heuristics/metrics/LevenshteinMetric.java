package fr.familiar.operations.heuristics.metrics;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.operations.heuristics.SimpleHeuristic;

public class LevenshteinMetric extends SimpleHeuristic implements KSynthesisPlugin {

	private Levenshtein metric = new Levenshtein();
	
	@Override
	public String getName() {
		return "Levenshtein (Simmetrics)";
	}

	@Override
	public double similarity(String child, String parent) {
		return metric.getSimilarity(child, parent);
	}

}
