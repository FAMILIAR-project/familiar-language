package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;

public class InteractiveFMSynthesizer extends Observable{
	
	private FeatureModelVariable fmv;
	private ImplicationGraph<String> big;
	
	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this.fmv = fmv;
		big = fmv.computeImplicationGraph();
//		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));
	}
	
	public void selectParent(String child, String parent) {
		// Add the edge to the feature graph
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		graph.addEdge(graph.findVertex(child), graph.findVertex(parent), FeatureEdge.HIERARCHY);
		
		// Modify the implication graph to represent this new relation
		Set<SimpleEdge> removedEdges = new HashSet<SimpleEdge>(big.outgoingEdges(child));
		removedEdges.remove(big.findEdge(child, parent));
		big.removeAllEdges(removedEdges);
		
		setChanged();
		notifyObservers(fmv);
		setChanged();
		notifyObservers(big);
	}

	public FeatureModelVariable getFeatureModelVariable() {
		return fmv;
	}

	public ImplicationGraph<String> getImplicationGraph() {
		return big;
	}
	

}
