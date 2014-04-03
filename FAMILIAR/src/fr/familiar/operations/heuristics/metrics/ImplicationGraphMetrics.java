/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations.heuristics.metrics;

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
	
	
	public <V> double meanDegree(ImplicationGraph<V> big) {
		double sumDegree = 0;
		for (V vertice : big.vertices()) {
			sumDegree += big.edgesOf(vertice).size();
		}
		return sumDegree / big.vertices().size();
	}
	
	public <V> double meanIndegree(ImplicationGraph<V> big) {
		double sumIndegree = 0;
		for (V vertice : big.vertices()) {
			sumIndegree += big.inDegreeOf(vertice);
		}
		return sumIndegree / big.vertices().size();
	}
	
	public <V> double meanOutdegree(ImplicationGraph<V> big) {
		double sumOutdegree = 0;
		for (V vertice : big.vertices()) {
			sumOutdegree += big.outDegreeOf(vertice);
		}
		return sumOutdegree / big.vertices().size();
	}
	
	
}

