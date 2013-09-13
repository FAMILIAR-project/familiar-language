package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.Map;
import java.util.Set;

public class LatentSemanticMetric implements FeatureSimilarityMetric{
	 private ImplicationGraph<String> big;
	 private Map<SimpleEdge, Double> similarityMap;
	private WikipediaMinerDB wikipediaDB;

	 public LatentSemanticMetric(WikipediaMinerDB wikipediaDB) {
		 this.wikipediaDB = wikipediaDB;
	}
	 
	 
	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
		
		if (big == null || !big.equals(implicationGraph)) {
			this.big = implicationGraph;
			try {
				ComputeLSA compute = new ComputeLSA(big, wikipediaDB);
				
//				compute.cosinusSimilarity(big);
//				compute.correlationSimilarity(big);
				compute.spearmanRankCorrelation(big);
//				compute.euclideanDistance(big);
//				compute.goodmanKruskalGamma(big);
//				compute.jaccardIndex(big);
//				compute.kendallsTau(big);
//				compute.klDivergence(big);
//				compute.linSimilarity(big);
//				compute.tanimotoSimilarity(big);

				similarityMap = compute.getSimilarityMap();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
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
	
	@Override
	public String toString() {
		return "Latent Semantic Analysis";
	}


	@Override
	public boolean isXorGroupRequired() {
		return false;
	}


	@Override
	public boolean isOrGroupRequired() {
		return false;
	}

}
