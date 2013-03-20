package fr.unice.polytech.modalis.familiar.operations.heuristics.clustering;

import gsd.graph.ImplicationGraph;
import ch.usi.inf.sape.hac.experiment.Experiment;

public class FMExperiment implements Experiment {

	private String[] features;
	
	
	public FMExperiment(ImplicationGraph<String> big) {
		features = new String[big.vertices().size()];
		int i=0;
		for (String feature : big.vertices()) {
			features[i] = feature;
			i++;
		}
	}


	@Override
	public int getNumberOfObservations() {
		return features.length;
	}


	public String getFeature(int observation) {
		return features[observation];
	}

}
