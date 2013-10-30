package fr.familiar.fm;

import gsd.graph.SimpleEdge;

import org.jgrapht.graph.AbstractGraph;

public class SimpleExtendedEdge<T> extends SimpleEdge {

	private T _target;
	private T _source;
	private AbstractGraph<T, SimpleExtendedEdge<T>> _graph;

	public SimpleExtendedEdge(T source, T target,
			AbstractGraph<T, SimpleExtendedEdge<T>> graph) {
		_source = source;
		_target = target;
		_graph = graph;
	}

	public AbstractGraph<T, SimpleExtendedEdge<T>> getGraph() {
		return _graph;
	}

	public T getTarget() {
		return _target;
	}

	public T getSource() {
		return _source;
	}

	@Override
	public String toString() {

		T s1 = getSource();
		assert (s1 != null);
		T t1 = getTarget();
		assert (t1 != null);
		return ("" + s1 + " -> " + "" + t1);

	}
}
