package fr.unice.polytech.modalis.familiar.experimental;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BreadthFirstEdgeSetIterator<T extends Comparable<T>> extends
		BreadthFirstEdgeIterator<T> {
	private final Set<FeatureEdge> _edgeSet;
	private final Collection<FeatureEdge> _edges;
	private FeatureEdge _curr;

	public BreadthFirstEdgeSetIterator(FeatureGraph<T> g,
			Collection<FeatureEdge> edges) {
		super(g);
		_edges = edges;
		_edgeSet = new HashSet<FeatureEdge>(edges);
	}

	public boolean hasNext() {
		if (_edgeSet.size() == 0)
			return false;
		return super.hasNext();
	}

	public FeatureEdge next() {
		FeatureEdge next = super.next();
		while (!(_edgeSet.contains(next))) {
			next = super.next();
		}
		_edgeSet.remove(next);
		_curr = next;
		return next;
	}

	public void remove() {
		_edges.remove(_curr);
	}
}
