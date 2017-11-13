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
