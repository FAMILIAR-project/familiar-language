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
public class BreadthFirstFGIterator<T extends Comparable<T>> implements
		Iterator<FeatureNode<T>> {
	private final FeatureGraph<T> _g;
	private final Queue<FeatureNode<T>> _next;
	private final HashSet<FeatureNode<T>> _visited;
	private final HashSet<FeatureNode<T>> _siblings;
	private int depth = 0;

	public BreadthFirstFGIterator(FeatureGraph<T> g) {
		_g = g;
		_visited = new HashSet<FeatureNode<T>>();
		_next = new LinkedList<FeatureNode<T>>();
		_siblings = new HashSet<FeatureNode<T>>();
		_next.add(_g.getTopVertex());
	}

	public boolean hasNext() {
		return (!(_next.isEmpty()));
	}

	public FeatureNode<T> next() {
		FeatureNode<T> next = _next.poll();

		Set<FeatureNode<T>> parents = _g.parents(next);
		if (CollectionUtils.containsAny(_siblings, parents)) {
			_siblings.clear();
			depth += 1;
		}
		_siblings.add(next);

		_visited.add(next);

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
