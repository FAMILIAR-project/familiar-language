package fr.unice.polytech.modalis.familiar.operations.heuristics.clustering;

import gsd.graph.ImplicationGraph;
import ch.usi.inf.sape.hac.experiment.Experiment;

public class FMExperiment implements Experiment {

	private String[] features;
	private ImplicationGraph<String> implicationGraph;
	
	
	public FMExperiment(ImplicationGraph<String> implicationGraph) {
		this.implicationGraph = implicationGraph;
		features = new String[implicationGraph.vertices().size()];
		int i=0;
		for (String feature : implicationGraph.vertices()) {
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
	
	public ImplicationGraph<String> getImplicationGraph() {
		return implicationGraph;
	}

}
