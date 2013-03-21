package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import ch.usi.inf.sape.hac.dendrogram.Dendrogram;
import fr.unice.polytech.modalis.familiar.operations.heuristics.clustering.FMExperiment;
import fr.unice.polytech.modalis.familiar.operations.heuristics.clustering.HierarchicalFeatureClusterer;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class InteractiveFMSynthesizer extends Observable{
	
	private FeatureModelVariable fmv;
	private ImplicationGraph<String> big;
	
	private FeatureSimilarityMetric parentSimilarityMetric;
	private FeatureComparator featureComparator;
	
	private FeatureSimilarityMetric clusteringSimilarityMetric;
	private Set<Set<String>> similarityClusters;
	private double clusteringThreshold;
	
	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this.fmv = fmv;
		big = fmv.computeImplicationGraph();
//		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));
		
		// TODO : replace with final default parameters
		setClusteringParameters(new SimmetricsMetric(SimmetricsMetric.MetricName.SMITHWATERMAN), 0.4);
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
			
			if (parentSimilarityMetric != null) {
				Collections.sort(parentList, new ParentComparator(feature, parentSimilarityMetric));	
			}
			
			KeyValue<String, List<String>> parentEntry = new KeyValue<String, List<String>>(feature, parentList);
			parents.add(parentEntry);
		}
		Collections.sort(parents, featureComparator);
		return parents;
	}

	public void setParentSimilarityMetric(
			FeatureSimilarityMetric parentSimilarityMetric) {
		this.parentSimilarityMetric = parentSimilarityMetric;
	}

	/**
	 * Set clustering parameters and compute clusters
	 * @param clusteringSimilarityMetric
	 * @param threshold
	 */
	public void setClusteringParameters(FeatureSimilarityMetric clusteringSimilarityMetric, double threshold) {
		this.clusteringSimilarityMetric = clusteringSimilarityMetric;
		this.clusteringThreshold = threshold;
		computeClusters();
	}

	/**
	 * Compute clusters according to the previously specified similarity metric and threshold
	 */
	private void computeClusters() {
		HierarchicalFeatureClusterer hierarchicalClustering = new HierarchicalFeatureClusterer();
		FMExperiment experiment = new FMExperiment(big);
		Dendrogram dendrogram = hierarchicalClustering.computeDendrogram(experiment, clusteringSimilarityMetric);
		similarityClusters = hierarchicalClustering.extractClusters(experiment,dendrogram, clusteringThreshold);
		setChanged();
		notifyObservers();
	}

	/**
	 * Return similarity clusters or null if they have not been computed yet
	 * @return
	 */
	public Set<Set<String>> getSimilarityClusters() {
		return similarityClusters;
	}
	
	public List<Set<String>> getCliques() {
		return DirectedCliqueFinder.INSTANCE.findAll(big);
	}

}
