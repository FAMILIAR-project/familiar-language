package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import gsd.graph.ImplicationGraph;

public class ImplicationGraphMetrics {

	
	public <V> int maxDegree(ImplicationGraph<V> big) {
		int maxDegree = 0;
		for (V vertice : big.vertices()) {
			int degree = big.edgesOf(vertice).size();
			if (degree > maxDegree) {
				maxDegree = degree;
			}
		}
		return maxDegree;
	}
	
	public <V> int maxIndegree(ImplicationGraph<V> big) {
		int maxIndegree = 0;
		for (V vertice : big.vertices()) {
			int indegree = big.inDegreeOf(vertice);
			if (indegree > maxIndegree) {
				maxIndegree = indegree;
			}
		}
		return maxIndegree;
	}
	
	public <V> int maxOutdegree(ImplicationGraph<V> big) {
		int maxOutdegree = 0;
		for (V vertice : big.vertices()) {
			int outdegree = big.outDegreeOf(vertice);
			if (outdegree > maxOutdegree) {
				maxOutdegree = outdegree;
			}
		}
		return maxOutdegree;
	}
	
	
	public <V> int minDegree(ImplicationGraph<V> big) {
		int minDegree = Integer.MAX_VALUE;
		for (V vertice : big.vertices()) {
			int degree = big.edgesOf(vertice).size();
			if (degree < minDegree) {
				minDegree = degree;
			}
		}
		return minDegree;
	}
	
	public <V> int minIndegree(ImplicationGraph<V> big) {
		int minIndegree = Integer.MAX_VALUE;
		for (V vertice : big.vertices()) {
			int indegree = big.inDegreeOf(vertice);
			if (indegree < minIndegree) {
				minIndegree = indegree;
			}
		}
		return minIndegree;
	}
	
	public <V> int minOutdegree(ImplicationGraph<V> big) {
		int minOutdegree = Integer.MAX_VALUE;
		for (V vertice : big.vertices()) {
			int outdegree = big.outDegreeOf(vertice);
			if (outdegree < minOutdegree) {
				minOutdegree = outdegree;
			}
		}
		return minOutdegree;
	}
}

