package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.fm.converter.tvl.Couple;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * Compute edit distance with Zhang's algorithm on rooted ordered trees
 * 
 * @author gbecan
 *
 */
public class ZhangEditDistance implements FMEditDistanceMetric {

	@Override
	public int editDistance(FeatureModelVariable fm, FeatureModelVariable reference) {

		// Define an order for the trees
		List<String> order1 = computeFeatureOrder(fm);
		List<String> order2 = computeFeatureOrder(reference);
		
		// Compute the leftmost leaf descendant for each node
		List<Integer> l1 = computeLeftMostLeaves(fm, order1);
		List<Integer> l2 = computeLeftMostLeaves(reference, order2);
		
		// Compute the key roots
		List<Integer> keyRoots1 = computeKeyRoots(fm, order1, l1);
		List<Integer> keyRoots2 = computeKeyRoots(reference, order2, l2);
		
		
		// Compute the edit distance
		int nbFeatures = order1.size();
		int[][] treeDist = new int[nbFeatures][nbFeatures];
		for (int keyRoot1 : keyRoots1) {
			for (int keyRoot2 : keyRoots2) {
				treeDist(order1, order2, treeDist, keyRoot1, keyRoot2, l1, l2, fm, reference); 
			}
		}

		return treeDist[nbFeatures-1][nbFeatures-1];
	}
	
	private List<String> computeFeatureOrder(FeatureModelVariable fm) {
		FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
		ArrayList<String> order = new ArrayList<String>();
		
		LinkedList<FeatureNode<String>> nodesToVisit = new  LinkedList<FeatureNode<String>>();
		nodesToVisit.add(hierarchy.getTopVertex());
		while (!nodesToVisit.isEmpty()) {
			FeatureNode<String> feature = nodesToVisit.pop();
			if (!feature.isTop()) {
				order.add(feature.getFeature());
			}
			
			List<FeatureNode<String>> children = new ArrayList<FeatureNode<String>>(hierarchy.children(feature));
			Collections.sort(children, new AlphabeticalFeatureNodeComparator());
			for (FeatureNode<String> child : children) {
				nodesToVisit.push(child);
			}
		}
		
		Collections.reverse(order);
		
		return order;
	}
	
	private class AlphabeticalFeatureNodeComparator implements Comparator<FeatureNode<String>> {
		public int compare(gsd.synthesis.FeatureNode<String> o1, gsd.synthesis.FeatureNode<String> o2) { 
			return o1.getFeature().compareTo(o2.getFeature());
		}
	}
	
	private List<Integer> computeLeftMostLeaves(FeatureModelVariable fm, List<String> order) {
		ArrayList<Integer> leftMostLeaves = new ArrayList<Integer>();
		for (String feature : order) {
			leftMostLeaves.add(computeLeftMostLeaf(fm, order, feature));
		}
		return leftMostLeaves;
	}
	
	private int computeLeftMostLeaf(FeatureModelVariable fm, List<String> order, String feature) {
		FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
		FeatureNode<String> leftMostLeafNode = hierarchy.findVertex(feature);
		int leftMostLeafIndex = order.indexOf(feature);
		
		Set<FeatureNode<String>> children = hierarchy.children(leftMostLeafNode);
		while (!children.isEmpty()) {
			for (FeatureNode<String> child : children) {
				int childIndex = order.indexOf(child.getFeature());
				if (childIndex < leftMostLeafIndex) {
					leftMostLeafIndex = childIndex;
					children = hierarchy.children(child);
				}
			}
		}
		
		return leftMostLeafIndex;
	}

	private List<Integer> computeKeyRoots(FeatureModelVariable fm, List<String> order, List<Integer> l) {
		List<Integer> keyRoots = new ArrayList<Integer>();
		
		FeatureGraph<String> hierarchy = fm.getFm().getDiagram();
		int featureIndex = 0;
		for (String feature : order) {
			FeatureNode<String> featureNode = hierarchy.findVertex(feature);
			FeatureNode<String> parent = hierarchy.parents(featureNode).iterator().next();
			int parentIndex = order.indexOf(parent.getFeature());
			
			if (parent.isTop() || l.get(featureIndex) != l.get(parentIndex)) {
				keyRoots.add(featureIndex);
			}
			
			featureIndex++;
		}
		
		return keyRoots;
	}
	
	private void treeDist(List<String> order1, List<String> order2, int[][] treeDist, int i, int j, List<Integer> l1, List<Integer> l2, FeatureModelVariable fm, FeatureModelVariable reference) {
		HashMap<ForestCouple, Integer> forestDist = new HashMap<ForestCouple, Integer>();
		
		forestDist.put(getForestCouple(-1, -1, -1, -1), 0);
		
		for (int u = l1.get(i); u<=i; u++) {
			forestDist.put(getForestCouple(l1.get(i), u, -1, -1), 
					forestDist.get(getForestCouple(l1.get(i), u-1, -1, -1)) + getCost(order1.get(u), null));
			
		}
		
		for (int v = l2.get(j); v<=j; v++) {
			forestDist.put(getForestCouple(-1, -1, l2.get(j), v), 
					forestDist.get(getForestCouple(-1, -1, l2.get(j), v-1)) + getCost(null, order2.get(v)));
		}
		
		for (int u = l1.get(i); u<=i; u++) {
			for (int v = l2.get(j); v<=j; v++) {
				if (l1.get(u) == l1.get(i) && l2.get(v) == l2.get(j)) {
					int delete = forestDist.get(getForestCouple(l1.get(i), u-1, l2.get(j), v)) + getCost(order1.get(u), null);
					int add = forestDist.get(getForestCouple(l1.get(i), u, l2.get(j), v-1)) + getCost(null, order2.get(v));
					int rename = forestDist.get(getForestCouple(l1.get(i), u-1, l2.get(j), v-1)) + getCost(order1.get(u), order2.get(v));
					int min = Math.min(delete, add);
					min = Math.min(min, rename);
					forestDist.put(getForestCouple(l1.get(i), u, l2.get(j), v), min);
					treeDist[u][v] = min;
				} else {
					int delete = forestDist.get(getForestCouple(l1.get(i), u-1, l2.get(j), v)) + getCost(order1.get(u), null);
					int add = forestDist.get(getForestCouple(l1.get(i), u, l2.get(j), v-1)) + getCost(null, order2.get(v));
					int rename = forestDist.get(getForestCouple(l1.get(i), l1.get(u)-1, l2.get(j), l2.get(v)-1)) + treeDist[u][v];
					int min = Math.min(delete, add);
					min = Math.min(min, rename);
					forestDist.put(getForestCouple(l1.get(i), u, l2.get(j), v), min);
				}
			}
		}
		
	}
	
	private ForestCouple getForestCouple(int inf1, int sup1, int inf2, int sup2) {
		Forest first = inf1 <= sup1 ? new Forest(inf1, sup1) : new Forest(-1, -1);
		Forest second = inf2 <= sup2 ? new Forest(inf2, sup2) : new Forest(-1, -1);

		return new ForestCouple(first, second);
	}
	
	private int getCost(String f1, String f2) {
		if (f1 == null) { // Add node
			return 0; 
		} else if (f2 == null){ // Remove node
			return 1;
		} else if (!f1.equals(f2)) { // Rename node
			return 1;
		} else { // Do nothing
			return 0;
		}
	}
	
	private class Forest {
		private Integer inf, sup;

		public Forest(Integer inf, Integer sup) {
			this.inf = inf;
			this.sup = sup;
		}

		

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((inf == null) ? 0 : inf.hashCode());
			result = prime * result + ((sup == null) ? 0 : sup.hashCode());
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Forest) {
				Forest that = (Forest) obj;
				return this.inf == that.inf && this.sup == that.sup;
			} else {
				return false;
			}
		}


		private ZhangEditDistance getOuterType() {
			return ZhangEditDistance.this;
		}
	}

	private class ForestCouple extends Couple<Forest, Forest>{

		public ForestCouple(Forest first, Forest second) {
			super(first, second);
		}
		
	}
}
