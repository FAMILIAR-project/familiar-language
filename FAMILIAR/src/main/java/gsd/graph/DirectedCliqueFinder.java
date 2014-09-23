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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DirectedCliqueFinder {

	private DirectedCliqueFinder() {}
	
	public static DirectedCliqueFinder INSTANCE = new DirectedCliqueFinder();
	
	
	/**
	 * Finds a clique: a simple algorithm that only works in a directed graph with
	 * transitive adjacency relation. Returns null if no clique is found.
	 * 
	 * Only nontrivial (non singleton) cliques are returned.
	 */
	public <V,E> List<Set<V>> findAll(BasicGraph<V,E> g) {

		List<Set<V>> result = new LinkedList<Set<V>> ();
		Set<V> seeds = new HashSet<V> ();
		seeds.addAll (g.vertices());

		while (!seeds.isEmpty ()) {

			Set<V> clique = growClique (seeds.iterator ().next (), g);

			if (clique.size () > 1)
				result.add (clique);
			seeds.removeAll (clique);
		}

		return result;

	}



	/**
	 * An algorithm for finding cliques in TRANSITIVE DAGS by running BFS on
	 * bidirectional edges.
	 * 
	 * @param seed
	 * @return
	 */
	private <V,E> Set<V> growClique(V seed, BasicGraph<V,E> g) {

		Set<V> clique = new HashSet<V> ();
		LinkedList<V> fifo = new LinkedList<V> ();
		fifo.add (seed);

		// inefficient. Could be improved.
		while (!fifo.isEmpty ()) {

			V v = fifo.removeFirst ();
			clique.add (v);
			Set<V> vs = biNeighbours (v, g);
			vs.removeAll (clique);
			vs.removeAll (fifo);
			fifo.addAll (vs);
		}

		return clique;
	}
	
	/**
	 * Returns a set of neighbours of v that are connected with v in both
	 * directions.
	 * 
	 * @param v
	 * @return
	 */
	private <V,E> Set<V> biNeighbours(V v, BasicGraph<V,E> g) {
		Set<V> result = new HashSet<V>();
		
		for (E e : g.outgoingEdges(v)) {
			V target = g.getTarget(e);
			if (g.findEdge(target, v) != null) {
				result.add(target);
			}
		}
		
		return result;
	}
	
}
