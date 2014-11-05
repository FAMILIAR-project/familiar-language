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

package fr.familiar.experimental;

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
