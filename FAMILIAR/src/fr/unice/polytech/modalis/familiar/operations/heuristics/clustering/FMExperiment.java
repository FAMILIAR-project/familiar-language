package fr.unice.polytech.modalis.familiar.operations.heuristics.clustering;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;
import ch.usi.inf.sape.hac.experiment.Experiment;

public class FMExperiment implements Experiment {

	private String[] features;
	private ImplicationGraph<String> implicationGraph;
	private Set<FGroup> xorGroups;
	private Set<FGroup> orGroups;
	
	
	public FMExperiment(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups) {
		this.implicationGraph = implicationGraph;
		this.xorGroups = xorGroups;
		this.orGroups = orGroups;
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
	
	public Set<FGroup> getXorGroups() {
		return xorGroups;
	}


	public Set<FGroup> getOrGroups() {
		return orGroups;
	}

}
