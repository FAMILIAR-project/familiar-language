package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

public enum MetricName {
	ALWAYS_ZERO("Always zero"),
	RANDOM("Random"),
	SIMMETRICS_SMITHWATERMAN("Simmetrics (Smith Waterman)"),
	WORDNET_WUP("Wordnet (Wu & Palmer)");
	
	private final String name;
	
	private MetricName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
