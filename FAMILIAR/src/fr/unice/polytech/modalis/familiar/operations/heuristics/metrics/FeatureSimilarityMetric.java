package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

public interface FeatureSimilarityMetric {

	/**
	 * Compute the similarity between the two features
	 * @param feature
	 * @param parent
	 * @return the similarity expressed by a double between 0 and 1
	 */
	double similarity(ImplicationGraph<String> implicationGraph, 
			Set<FGroup> xorGroups, Set<FGroup> orGroups, 
			String source, String target);
}
