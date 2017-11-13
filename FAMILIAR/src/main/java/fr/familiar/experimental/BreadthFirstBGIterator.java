/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.experimental;

import gsd.graph.BasicGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;

public class BreadthFirstBGIterator<V, E> implements Iterator<V> {
	private final BasicGraph<V, E> _g;
	private final Queue<V> _next;
	private final Set<V> _visited;
	private final Set<V> _siblings;
	private int depth = 0;

	public BreadthFirstBGIterator(BasicGraph<V, E> g) {
		_g = g;
		_visited = new HashSet<V>();
		_next = new LinkedList<V>();
		_siblings = new HashSet<V>();
		_next.addAll(findAllRoots(_g));
	}

	public Set<V> findAllRoots(BasicGraph<V, E> g) {
		Set<V> result = new HashSet<V>();
		for (V v : g.vertices()) {
			if (g.parents(v).size() == 0)
				result.add(v);
		}
		return result;
	}

	public boolean hasNext() {
		return (!(_next.isEmpty()));
	}

	public V next() {
		V next = _next.poll();

		Set<V> parents = _g.parents(next);
		if (CollectionUtils.containsAny(_siblings, parents)) {
			_siblings.clear();
			depth += 1;
		}
		_visited.add(next);
		_siblings.add(next);		

		_next.addAll(_g.children(next));
		while ((!(_next.isEmpty()))
				&& (_visited.contains(_next.peek()))) {
			_next.poll();
		}
		return next;
	}

	public int getDepth() {
		return depth;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
