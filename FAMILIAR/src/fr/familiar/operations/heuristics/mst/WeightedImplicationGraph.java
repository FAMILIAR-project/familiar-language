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

package fr.familiar.operations.heuristics.mst;

import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.EdgeSetFactory;

/**
 * Wrapper for adding weights to an implication graph
 * @author gbecan
 *
 * @param <V> : type of the vertices in the implication graph
 */
public class WeightedImplicationGraph<V> {

	private ImplicationGraph<V> ig;
	private Map<SimpleEdge, Double> weights;
	
	public WeightedImplicationGraph() {
		ig = new ImplicationGraph<V>();
		weights = new HashMap<SimpleEdge, Double>();
	}
	
	public WeightedImplicationGraph(ImplicationGraph<V> ig) {
		this.ig = ig;
		weights = new HashMap<SimpleEdge, Double>(); 
	}
	
	public void setEdgeWeight(SimpleEdge edge, double weight) {
		weights.put(edge, weight);
	}
	
	public double getEdgeWeight(SimpleEdge edge) {
		Double weight = weights.get(edge);
		if (weight == null) {
			return 0;
		} else {
			return weight;
		}
	}

	public Map<SimpleEdge, Double> getWeights() {
		return weights;
	}

	public void setWeights(Map<SimpleEdge, Double> weights) {
		this.weights = weights;
	}

	public boolean addEdge(V arg0, V arg1, SimpleEdge arg2) {
		return ig.addEdge(arg0, arg1, arg2);
	}

	public SimpleEdge addEdge(V arg0, V arg1) {
		return ig.addEdge(arg0, arg1);
	}

	public boolean addVertex(V arg0) {
		return ig.addVertex(arg0);
	}

	public Collection<SimpleEdge> edges() {
		return ig.edges();
	}

	public Set<V> children(V v) {
		return ig.children(v);
	}

	public V getTarget(SimpleEdge e) {
		return ig.getTarget(e);
	}

	public Set<V> ancestors(V v) {
		return ig.ancestors(v);
	}

	public Set<V> descendants(V v) {
		return ig.descendants(v);
	}

	public WeightedImplicationGraph<V> clone() {
		WeightedImplicationGraph<V> clone = new WeightedImplicationGraph<V>(ig.clone());
		// Copy weights
		for (SimpleEdge edge : clone.edges()) {
			SimpleEdge originalEdge = this.findEdge(clone.getSource(edge), clone.getTarget(edge));
			clone.setEdgeWeight(edge, this.getEdgeWeight(originalEdge));
		}
		return clone;
	}

	public boolean containsEdge(SimpleEdge arg0) {
		return ig.containsEdge(arg0);
	}

	public boolean containsEdge(V arg0, V arg1) {
		return ig.containsEdge(arg0, arg1);
	}

	public boolean containsVertex(V arg0) {
		return ig.containsVertex(arg0);
	}

	public int degreeOf(V arg0) {
		return ig.degreeOf(arg0);
	}

	public Set<SimpleEdge> edgeSet() {
		return ig.edgeSet();
	}

	public SimpleEdge findEdge(V v1, V v2) {
		return ig.findEdge(v1, v2);
	}

	public String edgeString(SimpleEdge e) {
		return ig.edgeString(e);
	}

	public Set<SimpleEdge> edgesOf(V arg0) {
		return ig.edgesOf(arg0);
	}

	public Set<SimpleEdge> getAllEdges(V arg0, V arg1) {
		return ig.getAllEdges(arg0, arg1);
	}

	public SimpleEdge getEdge(V arg0, V arg1) {
		return ig.getEdge(arg0, arg1);
	}

	public EdgeFactory<V, SimpleEdge> getEdgeFactory() {
		return ig.getEdgeFactory();
	}

	public V getEdgeSource(SimpleEdge arg0) {
		return ig.getEdgeSource(arg0);
	}

	public V getEdgeTarget(SimpleEdge arg0) {
		return ig.getEdgeTarget(arg0);
	}

	public V getSource(SimpleEdge e) {
		return ig.getSource(e);
	}

	public int inDegreeOf(V arg0) {
		return ig.inDegreeOf(arg0);
	}

	public Collection<SimpleEdge> incomingEdges(V v) {
		return ig.incomingEdges(v);
	}

	public Set<V> getSuccessors(V v) {
		return ig.getSuccessors(v);
	}

	public Set<V> getPredecessors(V v) {
		return ig.getPredecessors(v);
	}

	public Set<SimpleEdge> incomingEdgesOf(V arg0) {
		return ig.incomingEdgesOf(arg0);
	}

	public boolean isAllowingLoops() {
		return ig.isAllowingLoops();
	}

	public boolean isAllowingMultipleEdges() {
		return ig.isAllowingMultipleEdges();
	}

	public int outDegreeOf(V arg0) {
		return ig.outDegreeOf(arg0);
	}

	public Collection<SimpleEdge> outgoingEdges(V v) {
		return ig.outgoingEdges(v);
	}

	public Set<SimpleEdge> outgoingEdgesOf(V arg0) {
		return ig.outgoingEdgesOf(arg0);
	}

	public Collection<V> vertices() {
		return ig.vertices();
	}

	public Set<V> parents(V v) {
		return ig.parents(v);
	}

	public String toString() {
		return ig.toString();
	}

	public List<SimpleEdge> topologicalEdges() {
		return ig.topologicalEdges();
	}

	public ImplicationGraph<Set<V>> reduceCliques() {
		return ig.reduceCliques();
	}

	public void printEdges() {
		ig.printEdges();
	}

	public boolean removeAllEdges(Collection<? extends SimpleEdge> arg0) {
		// Remove useless weights
		for (SimpleEdge edge : arg0) {
			weights.remove(edge);
		}
		return ig.removeAllEdges(arg0);
	}

	public Set<SimpleEdge> removeAllEdges(V arg0, V arg1) {
		Set<SimpleEdge> removedEdges = ig.removeAllEdges(arg0, arg1);
		// Remove useless weights
		for (SimpleEdge edge : removedEdges) {
			weights.remove(edge);
		}
		return removedEdges;
	}

	public boolean removeAllVertices(Collection<? extends V> arg0) {
		// Remove useless weights
		for (V vertex : arg0) {
			if (ig.containsVertex(vertex)) {
				for (SimpleEdge edge : ig.edgesOf(vertex)) {
					weights.remove(edge);
				}
			}
		}
		return ig.removeAllVertices(arg0); 
	} 

	public boolean removeEdge(SimpleEdge arg0) {
		weights.remove(arg0); // Remove useless weight
		return ig.removeEdge(arg0);
	}

	public SimpleEdge removeEdge(V arg0, V arg1) {
		SimpleEdge edge = ig.removeEdge(arg0, arg1); 
		weights.remove(edge); // Remove useless weight
		return edge;
	}

	public boolean removeVertex(V vertex) {
		// Remove useless weights
		if (ig.containsVertex(vertex)) {
			for (SimpleEdge edge : ig.edgesOf(vertex)) {
				weights.remove(edge);
			}
		}
		return ig.removeVertex(vertex); 
	}

	public Set<V> roots() {
		return ig.roots();
	}

	public void setEdgeSetFactory(EdgeSetFactory<V, SimpleEdge> arg0) {
		ig.setEdgeSetFactory(arg0);
	}

	public Set<V> vertexSet() {
		return ig.vertexSet();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ig == null) ? 0 : ig.hashCode());
		result = prime * result + ((weights == null) ? 0 : weights.hashCode());
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeightedImplicationGraph other = (WeightedImplicationGraph) obj;
		if (ig == null) {
			if (other.ig != null)
				return false;
		} else if (!ig.equals(other.ig))
			return false;
		if (weights == null) {
			if (other.weights != null)
				return false;
		} else if (!weights.equals(other.weights))
			return false;
		return true;
	}

	public ImplicationGraph<V> getImplicationGraph() {
		return ig;
	}
	
	

	
}
