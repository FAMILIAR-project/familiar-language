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
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.OptimumBranchingFinder;
import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.unice.polytech.modalis.familiar.operations.measures.cliques.BIGCliques_Threshold1;
import fr.unice.polytech.modalis.familiar.operations.measures.cliques.BIGCliques_Threshold2;
import fr.unice.polytech.modalis.familiar.operations.measures.cliques.BIGCliques_Threshold3;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class InteractiveFMSynthesizer extends Observable{
	
	public static final MetricName defaultParentSimilarityMetric = MetricName.ALWAYS_ZERO;
	public static final MetricName defaultClusteringSimilarityMetric = MetricName.SIMMETRICS_SMITHWATERMAN;
	
	private FeatureModelVariable fmv;
	private WeightedImplicationGraph<String> big;

	private FeatureSimilarityMetric parentSimilarityMetric;
	private FeatureComparator featureComparator;

	private FeatureSimilarityMetric clusteringSimilarityMetric;
	private Set<Set<String>> similarityClusters;
	private List<Set<Set<String>>> supportClusters;
	private double clusteringThreshold;

	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this.fmv = fmv;
		big = new WeightedImplicationGraph<String>(fmv.computeImplicationGraph());
		//		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));

		setParentSimilarityMetric(new AlwaysZeroMetric());
		setClusteringParameters(new SimmetricsMetric(MetricName.SIMMETRICS_SMITHWATERMAN), 0.4);
		featureComparator = new OutDegreeComparator(big.getImplicationGraph());
	}

	public FeatureModelVariable getFeatureModelVariable() {
		return fmv;
	}

	public ImplicationGraph<String> getImplicationGraph() {
		return big.getImplicationGraph();
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

	/**
	 * Set the similarity metric used in choosing the parent of each feature
	 * It also computes the associated weighted implication graph
	 * @param parentSimilarityMetric
	 */
	public void setParentSimilarityMetric(
			FeatureSimilarityMetric parentSimilarityMetric) {
		this.parentSimilarityMetric = parentSimilarityMetric;
		for (SimpleEdge edge : big.edges()) {
			String source = big.getSource(edge);
			String target = big.getTarget(edge);
			double weight = parentSimilarityMetric.similarity(source, target);
			big.setEdgeWeight(edge, weight);
		}
		setChanged();
		notifyObservers();
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
	
	public void setSupportClusteringParameters(double threshold) {
		this.clusteringThreshold = threshold;
		computeSupportClusters();
	}
	/**
	 * Compute clusters according to the previously specified similarity metric and threshold
	 */
	private void computeClusters() {
		HierarchicalFeatureClusterer hierarchicalClustering = new HierarchicalFeatureClusterer();
		FMExperiment experiment = new FMExperiment(big.getImplicationGraph());
		Dendrogram dendrogram = hierarchicalClustering.computeDendrogram(experiment, clusteringSimilarityMetric);
		similarityClusters = hierarchicalClustering.extractClusters(experiment,dendrogram, clusteringThreshold);
		setChanged();
		notifyObservers();
	}
	private void computeSupportClusters() {
		supportClusters = new ArrayList<Set<Set<String>>>();
		
		BIGCliques_Threshold1 cliques1 = new BIGCliques_Threshold1();
		cliques1.updateBIG(big);
		supportClusters.add(cliques1.getCliques());
		
		BIGCliques_Threshold2 cliques2 = new BIGCliques_Threshold2();
		cliques2.updateBIG(big);
		supportClusters.add(cliques2.getCliques());
		
		BIGCliques_Threshold3 cliques3 = new BIGCliques_Threshold3();
		cliques3.updateBIG(big);
		supportClusters.add(cliques3.getCliques());
		
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
	
	public List<Set<Set<String>>> getSupportClusters() {
		return supportClusters;
	}
	public List<Set<String>> getCliques() {
		return DirectedCliqueFinder.INSTANCE.findAll(big.getImplicationGraph());
	}

	/**
	 * Set this feature as the root of the feature model
	 * @param root
	 */
	public void setRoot(String root) {
		// Add an edge to the feature graph to define the node as the root
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		FeatureNode<String> rootNode;
		try {
			rootNode = graph.findVertex(root);	
		} catch (IllegalArgumentException e) {
			rootNode = new FeatureNode<String>(root);
			graph.addVertex(rootNode);
		}

		graph.addEdge(rootNode, graph.getTopVertex(), FeatureEdge.HIERARCHY);

		// Delete every outgoing edges of the root node in the implication graph
		Set<SimpleEdge> removedEdges = new HashSet<SimpleEdge>(big.outgoingEdges(root));
		big.removeAllEdges(removedEdges);

		setChanged();
		notifyObservers();
	}
	
	/**
	 * Compute a feature model with a complete feature diagram
	 * according to the parent similarity metric
	 * @return a complete feature model
	 */
	public FeatureModelVariable computeCompleteFeatureModel() {
		
		// Compute optimum branching for the implication graph
		OptimumBranchingFinder<String> branchingFinder = new OptimumBranchingFinder<String>();
		ImplicationGraph<String> hierarchy = branchingFinder.findOptimumBranching(big);
		
		// Create the corresponding feature model
		FeatureGraph<String> fg = FeatureGraphFactory.mkStringFactory().mkTop();
		for (String feature : hierarchy.vertices()) {
			fg.addVertex(new FeatureNode<String>(feature));
		}
		for (SimpleEdge edge : hierarchy.edges()) {
			String source = hierarchy.getSource(edge);
			String target = hierarchy.getTarget(edge);
			FeatureNode<String> sourceNode = fg.findVertex(source);
			FeatureNode<String> targetNode = fg.findVertex(target);
			fg.addEdge(sourceNode, targetNode, FeatureEdge.HIERARCHY);
		}
		
		FeatureModel<String> fm = new FeatureModel<String>(fg);
		FeatureModelVariable completeFM = new FeatureModelVariable(fmv.getIdentifier() + "_completed", fm);
		return completeFM;
	}

	public double getClusteringThreshold() {
		return clusteringThreshold;
	}

	public FeatureSimilarityMetric getClusteringSimilarityMetric() {
		return clusteringSimilarityMetric;
	}

	public WeightedImplicationGraph<String> getWeightedImplicationGraph() {
		return big;
	}
}
