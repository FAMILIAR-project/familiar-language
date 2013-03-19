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
import gsd.synthesis.FeatureNode;

public class InteractiveFMSynthesizer extends Observable{
	
	private FeatureModelVariable fmv;
	private ImplicationGraph<String> big;
	
	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this.fmv = fmv;
		big = fmv.computeImplicationGraph();
//		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));
	}
	
	public FeatureModelVariable getFeatureModelVariable() {
		return fmv;
	}

	public ImplicationGraph<String> getImplicationGraph() {
		return big;
	}
	
	public void selectParent(String child, String parent) {
		// Add the edge to the feature graph
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		FeatureNode<String> childNode;
		try {
			childNode = graph.findVertex(child);	
		} catch (IllegalArgumentException e) {
			childNode = new FeatureNode<String>(child);
			graph.addVertex(childNode);
		}
		
		FeatureNode<String> parentNode;
		try {
			parentNode = graph.findVertex(parent);
		} catch (IllegalArgumentException e) {
			parentNode = new FeatureNode<String>(parent);
			graph.addVertex(parentNode);
		}
		
		graph.addEdge(childNode, parentNode, FeatureEdge.HIERARCHY);
		
		// Modify the implication graph to represent this new relation
		Set<SimpleEdge> removedEdges = new HashSet<SimpleEdge>(big.outgoingEdges(child));
		removedEdges.remove(big.findEdge(child, parent));
		big.removeAllEdges(removedEdges);
		
		setChanged();
		notifyObservers();
	}

	public void ignoreParent(String child, String parent) {
		// Suppress the corresponding edge from the implication graph
		SimpleEdge edge = big.findEdge(child, parent);
		if (edge != null) {
			big.removeEdge(edge);
		}
		setChanged();
		notifyObservers();
	}
	

}
