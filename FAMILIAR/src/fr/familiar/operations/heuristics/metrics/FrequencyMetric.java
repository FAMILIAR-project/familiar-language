package fr.familiar.operations.heuristics.metrics;

import fr.familiar.operations.measures.cliques.ComputeFeatureFrequency;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.Map;

public class FrequencyMetric implements FeatureFrequencyMetric{
	private Map<SimpleEdge, Double> suppMap;
	 private Map<SimpleEdge, Double> confMap;
	 private ImplicationGraph<String> big;
	 
	    public FrequencyMetric(FeatureModelVariable phiR) {
	    	ComputeFeatureFrequency compute = new ComputeFeatureFrequency(phiR);
			compute.computeSupportConfidenceMeasures(phiR);
			this.big = compute.getBig();
			suppMap= compute.getSuppMap();
			confMap= compute.getConfMap();
		}
		@Override
		public double support(String featureName1, String featureName2){
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		return suppMap.get(cle);    
		}
	
		public double confidence(String featureName1, String featureName2){
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		return confMap.get(cle);   
		}
}
