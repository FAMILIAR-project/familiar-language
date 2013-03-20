package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.util.Comparator;

import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;

public class ParentComparator implements Comparator<String>{
	
	private String feature;
	private FeatureSimilarityMetric metric;

	public ParentComparator(String feature, FeatureSimilarityMetric metric) {
		this.metric = metric;
		this.feature = feature;
	}
	
	@Override
	public int compare(String o1, String o2) {
		double sim1 = metric.similarity(feature, o1);
		double sim2 = metric.similarity(feature, o2);
		if (sim1 > sim2) {
			return -1;
		} else if (sim1 < sim2) {
			return 1;
		} else {
			return 0;
		}
	}

}
