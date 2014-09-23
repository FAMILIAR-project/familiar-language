/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.graph;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.Iterator;
import java.util.Stack;

public class DepthFirstEdgeIterator<T extends Comparable<T>> implements Iterator<FeatureEdge>{

	private final FeatureGraph<T> _g;
	private final Stack<FeatureEdge> _next;
	private final Stack<FeatureNode<T>> _depth;

	public DepthFirstEdgeIterator(FeatureGraph<T> g) {
		_g = g;
		_next = new Stack<FeatureEdge>();
		_depth = new Stack<FeatureNode<T>>();

		if (g.vertices().size() > 0) {
			for (FeatureEdge e : g.incomingEdges(g.getTopVertex()))
				_next.add(e);
		}
	}

	@Override
	public boolean hasNext() {
		return !_next.isEmpty();
	}

	@Override
	public FeatureEdge next() {
		FeatureEdge polled = _next.pop();

		while (_depth.contains(_g.getTarget(polled)))
			_depth.pop();

		for (FeatureNode<T> source : _g.getSources(polled)) {
			_next.addAll(_g.incomingEdges(source));
		}

		if (!_depth.contains(_g.getTarget(polled)))
			_depth.push(_g.getTarget(polled));

		return polled;
	}

	public int depth() {
		return _depth.size();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}