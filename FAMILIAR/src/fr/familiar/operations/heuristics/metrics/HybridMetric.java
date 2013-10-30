package fr.familiar.operations.heuristics.metrics;

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

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
