package fr.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.List;

import fr.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.familiar.variable.FeatureModelVariable;

public class OptimumBranchingResult implements Comparable<OptimumBranchingResult> {

	private FeatureModelVariable referenceFM;
	private FeatureModelVariable synthesizedFM;
	private List<String> commonEdges;
	private FeatureSimilarityMetric metric;
	private double ontologicalScore;
	
	public OptimumBranchingResult() {
		referenceFM = null;
		synthesizedFM = null;
		commonEdges = new ArrayList<String>();
	}
	
	public FeatureModelVariable getReferenceFM() {
		return referenceFM;
	}
	public void setReferenceFM(FeatureModelVariable referenceFM) {
		this.referenceFM = referenceFM;
	}
	public FeatureModelVariable getSynthesizedFM() {
		return synthesizedFM;
	}
	public void setSynthesizedFM(FeatureModelVariable synthesizedFM) {
		this.synthesizedFM = synthesizedFM;
	}
	public List<String> getCommonEdges() {
		return commonEdges;
	}
	public void setCommonEdges(List<String> commonEdges) {
		this.commonEdges = commonEdges;
	}
	public FeatureSimilarityMetric getMetric() {
		return metric;
	}
	public void setMetric(FeatureSimilarityMetric metric) {
		this.metric = metric;
	}
	public double getOntologicalScore() {
		return ontologicalScore;
	}
	public void setOntologicalScore(double ontologicalScore) {
		this.ontologicalScore = ontologicalScore;
	}

	public double getCommonEdgesPercentage() {
		if (referenceFM != null) {
			return commonEdges.size() / ((double) referenceFM.features().size());	
		} else {
			return 0;
		}
		
	}
	
	@Override
	public int compareTo(OptimumBranchingResult o) {
//		return Double.compare(getOntologicalScore(), o.getOntologicalScore());
		return Double.compare(getCommonEdgesPercentage(), o.getCommonEdgesPercentage());
	}
	
	
	
	@Override
	public String toString() {
		return synthesizedFM.getIdentifier() + "\n"
				+ "\t Metric : " + metric + "\n"
				+ "\t CommonEdges : " + getCommonEdgesPercentage() + "\n"
				+ "\t Onto score : " + ontologicalScore;
	}


}
