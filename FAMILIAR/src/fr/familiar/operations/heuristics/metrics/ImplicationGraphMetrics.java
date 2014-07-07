package fr.familiar.operations.heuristics.metrics;

import gsd.graph.ImplicationGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
	
	
	public <V> double averageDegree(ImplicationGraph<V> big) {
		double sumDegree = 0;
		for (V vertice : big.vertices()) {
			sumDegree += big.edgesOf(vertice).size();
		}
		return sumDegree / big.vertices().size();
	}
	
	public <V> double averageIndegree(ImplicationGraph<V> big) {
		double sumIndegree = 0;
		for (V vertice : big.vertices()) {
			sumIndegree += big.inDegreeOf(vertice);
		}
		return sumIndegree / big.vertices().size();
	}
	
	public <V> double averageOutdegree(ImplicationGraph<V> big) {
		double sumOutdegree = 0;
		for (V vertice : big.vertices()) {
			sumOutdegree += big.outDegreeOf(vertice);
		}
		return sumOutdegree / big.vertices().size();
	}
	
	
	public <V> int medianDegree(ImplicationGraph<V> big) {
		List<Integer> degrees = new ArrayList<Integer>();
		for (V vertice : big.vertices()) {
			degrees.add(big.edgesOf(vertice).size());
		}
		Collections.sort(degrees);
		return degrees.get(degrees.size()/2);
	}
	
	
}

