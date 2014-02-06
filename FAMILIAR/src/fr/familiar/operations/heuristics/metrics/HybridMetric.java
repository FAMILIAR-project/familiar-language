package fr.familiar.operations.heuristics.metrics;

import fr.familiar.experimental.FGroup;
import fr.familiar.operations.heuristics.Heuristic;
import gsd.graph.ImplicationGraph;

import java.util.Set;

public class HybridMetric implements Heuristic {
	
	private Heuristic semanticalMetric;
	private TransitiveReductionMetric transitiveReductionMetric;

	public HybridMetric(Heuristic semanticalMetric) {
		this.semanticalMetric = semanticalMetric;
		transitiveReductionMetric = new TransitiveReductionMetric();
	}

	@Override
	public double similarity(String source, String target) {
		
		double logicalSimilarity = transitiveReductionMetric.similarity(source, target);
		double semanticalSimilarity = semanticalMetric.similarity(source, target);
		
		return logicalSimilarity > 0 ? semanticalSimilarity : semanticalSimilarity * 0.8;
	}

	@Override
	public boolean isMutexGroupsRequired() {
		return true;
	}

	@Override
	public boolean isXorGroupsRequired() {
		return true;
	}

	@Override
	public boolean isOrGroupsRequired() {
		return true;
	}

	@Override
	public void setImplicationGraph(ImplicationGraph<String> implicationGraph) {
		transitiveReductionMetric.setImplicationGraph(implicationGraph);
		semanticalMetric.setImplicationGraph(implicationGraph);
	}

	@Override
	public void setGroups(Set<FGroup> groups) {
		transitiveReductionMetric.setGroups(groups);
		semanticalMetric.setGroups(groups);
	}

}
