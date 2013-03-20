package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class InteractiveFMSynthesizer extends Observable{
	
	private FeatureModelVariable fmv;
	private ImplicationGraph<String> big;
	private FeatureSimilarityMetric similarityMetric;
	private FeatureComparator featureComparator;
	
	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this.fmv = fmv;
		big = fmv.computeImplicationGraph();
//		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));
		featureComparator = new OutDegreeComparator(big);
	}
	
	public FeatureModelVariable getFeatureModelVariable() {
		return fmv;
	}

	public ImplicationGraph<String> getImplicationGraph() {
		return big;
	}
	
	/**
	 * Create the child/parent relation in the feature diagram
	 * @param child
	 * @param parent
	 */
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

	/**
	 * Suppress the possible child/parent relation in the feature diagram
	 * @param child
	 * @param parent
	 */
	public void ignoreParent(String child, String parent) {
		// Suppress the corresponding edge from the implication graph
		SimpleEdge edge = big.findEdge(child, parent);
		if (edge != null) {
			big.removeEdge(edge);
		}
		setChanged();
		notifyObservers();
	}
	
	/**
	 * List the parent candidates of each feature of the feature model
	 * The list is sorted according to the current metrics
	 * @return
	 */
	public List<KeyValue<String, List<String>>> getParentCandidates() {
		List<KeyValue<String, List<String>>> parents = new ArrayList<KeyValue<String,List<String>>>();
		for (String feature : big.vertices()) {
			List<String> parentList = new ArrayList<String>(big.parents(feature));
			
			if (similarityMetric != null) {
				Collections.sort(parentList, new ParentComparator(feature, similarityMetric));	
			}
			
			KeyValue<String, List<String>> parentEntry = new KeyValue<String, List<String>>(feature, parentList);
			parents.add(parentEntry);
		}
		Collections.sort(parents, featureComparator);
		return parents;
	}
	

	public void setSimilarityMetric(FeatureSimilarityMetric similarityMetric) {
		this.similarityMetric = similarityMetric;
	}
}
