package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.Map;

public class LatentSemanticMetric implements FeatureSimilarityMetric{
	 private ImplicationGraph<String> big;
	 private Map<SimpleEdge, Double> similarityMap;
	 

	
	
	@Override
	public double similarity(String featureName1, String featureName2) {
		
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		
		if (!similarityMap.containsKey(cle)){
			cle = big.getEdge(featureName2, featureName1);
		}
		
		if (similarityMap.containsKey(cle)) {
			return similarityMap.get(cle);	
		} else {
			return 0;
		}
		
	}
	
	public void setBig(ImplicationGraph<String> big) {
		this.big = big;
		try {
			ComputeLSA compute = new ComputeLSA(big);
			
			//compute.cosinusSimilarity(big);
			compute.correlationSimilarity(big);
//			compute.spearmanRankCorrelation(big);
//			compute.euclideanDistance(big);
//			compute.goodmanKruskalGamma(big);
//			compute.jaccardIndex(big);
//			compute.kendallsTau(big);
//			compute.klDivergence(big);
//			compute.linSimilarity(big);
//			compute.tanimotoSimilarity(big);
//		
			
			similarityMap = compute.getSimilarityMap();
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
