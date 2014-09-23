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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleDirectedGraph;

public class BasicDirectedGraph<V, E> extends SimpleDirectedGraph<V, E>
		implements BasicGraph<V, E> {

	public BasicDirectedGraph(Class<E> clazz) {
		super(clazz);
	}

	public BasicDirectedGraph(EdgeFactory<V, E> fact) {
		super(fact);
	}

	public Collection<E> edges() {
		return edgeSet();
	}

	public Collection<V> vertices() {
		return vertexSet();
	}

	public Set<V> children(V v) {
		Set<V> result = new HashSet<V>();
		for (E e : super.incomingEdgesOf(v)) {
			result.add(getEdgeSource(e));
		}
		return result;
	}

	public E findEdge(V v1, V v2) {
		return getEdge(v1, v2);
	}

	public V getSource(E e) {
		return getEdgeSource(e);
	}

	public V getTarget(E e) {
		return getEdgeTarget(e);
	}

	public Collection<E> incomingEdges(V v) {
		return incomingEdgesOf(v);
	}

	public Collection<E> outgoingEdges(V v) {
		return outgoingEdgesOf(v);
	}

	public Set<V> parents(V v) {
		Set<V> result = new HashSet<V>();
		for (E e : super.outgoingEdgesOf(v)) {
			result.add(getEdgeTarget(e));
		}
		return result;
	}
}
