package fr.unice.polytech.modalis.familiar.operations.heuristics.mst;

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
