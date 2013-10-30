package fr.familiar.gui.synthesis;

import java.util.Comparator;

import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;

public class ParentComparator implements Comparator<String>{
	
	private String feature;
	private WeightedImplicationGraph<String> big;

	public ParentComparator(String feature, WeightedImplicationGraph<String> big) {
		this.big = big;
		this.feature = feature;
	}
	
	@Override
	public int compare(String o1, String o2) {
		double sim1 = big.getEdgeWeight(big.findEdge(feature, o1));
		double sim2 = big.getEdgeWeight(big.findEdge(feature, o2));
		if (sim1 > sim2) {
			return -1;
		} else if (sim1 < sim2) {
			return 1;
		} else {
			return 0;
		}
	}

}
