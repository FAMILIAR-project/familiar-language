package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

public enum MetricName {
	ALWAYS_ZERO("Always zero"),
	RANDOM("Random"),
	SIMMETRICS_SMITHWATERMAN("Simmetrics (Smith Waterman)"),
	SIMMETRICS_LEVENSHTEIN("Simmetrics (Levenshtein)"),
	WORDNET_WUP("Wordnet (Wu & Palmer)"),
	WORDNET_PATHLENGTH("Wordnet (Path length)");
	
	private final String name;
	
	private MetricName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
