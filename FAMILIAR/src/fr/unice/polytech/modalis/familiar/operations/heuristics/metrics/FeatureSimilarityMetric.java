package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

public interface FeatureSimilarityMetric {

	/**
	 * Compute the similarity between the two features
	 * @param feature
	 * @param parent
	 * @return the similarity expressed by a double between 0 and 1
	 */
	double similarity(String feature, String parent);
}
