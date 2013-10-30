package fr.familiar.gui.synthesis;

import gsd.graph.ImplicationGraph;

import java.util.List;

public class OutDegreeComparator implements FeatureComparator {

	private ImplicationGraph<String> big;
	
	
	public OutDegreeComparator(ImplicationGraph<String> big) {
		this.big = big;
	}

	@Override
	public int compare(KeyValue<String, List<String>> o1, KeyValue<String, List<String>> o2) {
		return big.outDegreeOf(o2.getKey()) - big.outDegreeOf(o1.getKey());
	}

	
}
