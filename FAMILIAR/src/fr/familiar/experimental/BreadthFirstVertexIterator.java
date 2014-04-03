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

import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.CollectionUtils;

/**
 * @author mathieuacher
 * 
 */
public class BreadthFirstVertexIterator<T extends Comparable<T>> implements
		Iterator<FeatureNode<T>> {
	private final FeatureGraph<T> _g;
	private final Queue<FeatureNode<T>> _next;
	private final HashSet<FeatureNode<T>> _visited;
	private final HashSet<FeatureNode<T>> _siblings;
	private int depth = 0;

	public BreadthFirstVertexIterator(FeatureGraph<T> g) {
		this._g = g;
		this._visited = new HashSet<FeatureNode<T>>();
		this._next = new LinkedList<FeatureNode<T>>();
		this._siblings = new HashSet<FeatureNode<T>>();
		this._next.add(this._g.getTopVertex());
	}

	public boolean hasNext() {
		return (!(this._next.isEmpty()));
	}

	public FeatureNode<T> next() {
		FeatureNode<T> next = this._next.poll();

		Set<FeatureNode<T>> parents = this._g.parents(next);
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
