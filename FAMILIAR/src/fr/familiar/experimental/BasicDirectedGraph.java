package fr.familiar.experimental;

import gsd.graph.BasicGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleDirectedGraph;

public class BasicDirectedGraph<V, E> extends SimpleDirectedGraph<V, E>
		implements BasicGraph<V, E> {

	public BasicDirectedGraph(Class<E> clazz) {
		super(clazz);
	}

	public BasicDirectedGraph(EdgeFactory<V, E> fact) {
		super(fact);
	}

	public Collection<E> edges() {
		return edgeSet();
	}

	public Collection<V> vertices() {
		return vertexSet();
	}

	public Set<V> children(V v) {
		Set<V> result = new HashSet<V>();
		for (E e : super.incomingEdgesOf(v)) {
			result.add(getEdgeSource(e));
		}
		return result;
	}

	public E findEdge(V v1, V v2) {
		return getEdge(v1, v2);
	}

	public V getSource(E e) {
		return getEdgeSource(e);
	}

	public V getTarget(E e) {
		return getEdgeTarget(e);
	}

	public Collection<E> incomingEdges(V v) {
		return incomingEdgesOf(v);
	}

	public Collection<E> outgoingEdges(V v) {
		return outgoingEdgesOf(v);
	}

	public Set<V> parents(V v) {
		Set<V> result = new HashSet<V>();
		for (E e : super.outgoingEdgesOf(v)) {
			result.add(getEdgeTarget(e));
		}
		return result;
	}
}
