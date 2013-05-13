package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import java.util.Map;

import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.unice.polytech.modalis.familiar.operations.measures.cliques.ComputeFeatureFrequency;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;

public class LatentSemanticMetric implements FeatureSimilarityMetric{
	 private ImplicationGraph<String> big;
	 private Map<SimpleEdge, Double> cosinusSimilarityMap;

	
	public LatentSemanticMetric(WeightedImplicationGraph<String> originalBig) throws Exception {
		ComputeLSA compute = new ComputeLSA(originalBig);
		big = originalBig.getImplicationGraph();
		compute.computeCosinusSimilarity(big);
		cosinusSimilarityMap = compute.getCosinusSimilarityMap();
	}
	
	@Override
	public double similarity(String featureName1, String featureName2) {
		
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		
		if (!cosinusSimilarityMap.containsKey(cle)){
			cle = big.getEdge(featureName2, featureName1);
		}
		return cosinusSimilarityMap.get(cle);
	}
}
