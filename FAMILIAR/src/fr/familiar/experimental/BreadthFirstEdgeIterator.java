package fr.familiar.experimental;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author mathieuacher Like depth first edge iterator (queue rather than
 *         stack).
 * @param <T>
 */
public class BreadthFirstEdgeIterator<T extends Comparable<T>> implements
		Iterator<FeatureEdge> {

	private final FeatureGraph<T> _g;
	private final Queue<FeatureEdge> _next;

	public BreadthFirstEdgeIterator(FeatureGraph<T> g) {
		_g = g;
		_next = new LinkedList<FeatureEdge>();
		if (g.vertices().size() > 0)
			for (FeatureEdge e : g.incomingEdges(g.getTopVertex()))
				_next.add(e);
	}

	@Override
	public boolean hasNext() {
		return (!_next.isEmpty());
	}

	@Override
	public FeatureEdge next() {
		FeatureEdge polled = _next.poll();
		for (FeatureNode<T> source : _g.getSources(polled)) {
			_next.addAll(_g.incomingEdges(source));
		}
		return polled;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
