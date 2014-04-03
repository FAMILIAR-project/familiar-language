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

package fr.familiar.operations.heuristics.mst;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class DisjointSetCollection {
	
	private HashMap<Integer, Set<Integer>> disjointSets;
	
	/**
	 * Initialize the collection with one set by element containing this element
	 * @param elements
	 */
	public DisjointSetCollection() {
		disjointSets = new HashMap<Integer, Set<Integer>>();
	}
	
	/**
	 * Create a set with a single representative element
	 * @param element
	 */
	public void create(Integer element) {
		HashSet<Integer> singleton = new HashSet<Integer>();
		singleton.add(element);
		disjointSets.put(element, singleton);
	}

	/**
	 * Find the name of the set containing the element
	 * @param element
	 * @return
	 */
	public Integer find(Integer element) {
		for (Integer name : disjointSets.keySet()) {
			if (disjointSets.get(name).contains(element)) { 
				return name;
			}
		}
		return null;
	}
	
	/**
	 * Include the elements of set B in set A, removing set B
	 * @param setA
	 * @param setB
	 */
	public void union(Integer setA, Integer setB) {
		disjointSets.get(setA).addAll(disjointSets.get(setB));
		disjointSets.remove(setB);
	}
	
	/**
	 * Returns the corresponding set
	 * @param name
	 * @return
	 */
	public Set<Integer> get(Integer name) {
		return disjointSets.get(name);
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Integer key : disjointSets.keySet()) {
			result += "" + key + disjointSets.get(key) + "\n";
		}
		return result;
	}
	
}
