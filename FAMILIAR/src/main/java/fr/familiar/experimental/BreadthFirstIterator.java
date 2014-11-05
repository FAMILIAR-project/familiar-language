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

import gsd.graph.BasicGraph;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;

public class BreadthFirstIterator<V, E> implements Iterator<V> {
	private final BasicGraph<V, E> _g;
	private final Queue<V> _next;
	private final HashSet<V> _visited;
	private final HashSet<V> _siblings;
	private int depth = 0;

	public BreadthFirstIterator(BasicGraph<V, E> g) {
		this._g = g;
		this._visited = new HashSet<V>();
		this._next = new LinkedList<V>();
		this._siblings = new HashSet<V>();
		this._next.addAll(findAllRoots(_g));
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
		return (!(this._next.isEmpty()));
	}

	public V next() {
		V next = this._next.poll();

		Set<V> parents = this._g.parents(next);
		if (CollectionUtils.containsAny(this._siblings, parents)) {
			this._siblings.clear();
			this.depth += 1;
		}
		this._siblings.add(next);
		this._visited.add(next);

		this._next.addAll(this._g.children(next));
		while ((!(this._next.isEmpty()))
				&& (this._visited.contains(this._next.peek()))) {
			this._next.poll();
		}
		return next;
	}

	public int getDepth() {
		return this.depth;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}
