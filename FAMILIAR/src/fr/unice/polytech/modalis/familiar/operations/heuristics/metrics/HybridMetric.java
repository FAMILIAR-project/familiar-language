package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

public class HybridMetric implements FeatureSimilarityMetric {
	
	private FeatureSimilarityMetric semanticalMetric;
	private TransitiveReductionMetric transitiveReductionMetric;

	public HybridMetric(FeatureSimilarityMetric semanticalMetric) {
		this.semanticalMetric = semanticalMetric;
		transitiveReductionMetric = new TransitiveReductionMetric();
	}

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph,
			Set<FGroup> xorGroups, Set<FGroup> orGroups, String source,
			String target) {
		
		double logicalSimilarity = transitiveReductionMetric.similarity(implicationGraph, xorGroups, orGroups, source, target);
		double semanticalSimilarity = semanticalMetric.similarity(implicationGraph, xorGroups, orGroups, source, target);
		
		return logicalSimilarity > 0 ? semanticalSimilarity : semanticalSimilarity * 0.8;
	}

	@Override
	public boolean isXorGroupRequired() {
		return true;
	}

	@Override
	public boolean isOrGroupRequired() {
		return true;
	}

}
