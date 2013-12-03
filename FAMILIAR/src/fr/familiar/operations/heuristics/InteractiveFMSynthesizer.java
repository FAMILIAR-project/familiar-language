package fr.familiar.operations.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Set;

import ch.usi.inf.sape.hac.dendrogram.Dendrogram;
import fr.familiar.experimental.FGroup;
import fr.familiar.gui.synthesis.FeatureComparator;
import fr.familiar.gui.synthesis.KeyValue;
import fr.familiar.gui.synthesis.OutDegreeComparator;
import fr.familiar.gui.synthesis.ParentComparator;
import fr.familiar.operations.heuristics.actions.IgnoreParentAction;
import fr.familiar.operations.heuristics.actions.KSynthesisAction;
import fr.familiar.operations.heuristics.actions.SelectParentAction;
import fr.familiar.operations.heuristics.clustering.FMExperiment;
import fr.familiar.operations.heuristics.clustering.HierarchicalFeatureClusterer;
import fr.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.familiar.operations.heuristics.metrics.FeatureFrequencyMetric;
import fr.familiar.operations.heuristics.metrics.FrequencyMetric;
import fr.familiar.operations.heuristics.metrics.MetricName;
import fr.familiar.operations.heuristics.mst.OptimumBranchingFinder;
import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.familiar.operations.measures.cliques.BIGCliques_Threshold;
import fr.familiar.variable.FeatureModelVariable;
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
	private WeightedImplicationGraph<String> originalBig;
	private Set<FGroup> mutexGroups;
	private Set<FGroup> xorGroups;
	private Set<FGroup> orGroups;

	private Heuristic parentSimilarityMetric;
	private FeatureFrequencyMetric featureFrequencyMetric;
	private FeatureComparator featureComparator;

	private Heuristic clusteringSimilarityMetric;
	private Set<Set<String>> similarityClusters;
	private List<Set<Set<String>>> supportClusters;
	private double clusteringThreshold;
	private List<Heuristic> complementaryParentSimilarityMetrics;
	
	private FeatureModelVariable originalFmv;

	private LinkedList<KSynthesisAction> actionUndoHistory;
	private LinkedList<KSynthesisAction> actionRedoHistory;

	public InteractiveFMSynthesizer(FeatureModelVariable fmv,
			Heuristic parentSimilarityMetric,
			List<Heuristic> complementaryParentSimilarityMetrics,
			Heuristic clusteringSimilarityMetric,
			double clusteringThreshold) {

		originalFmv = fmv;
		// Initialize data
		big = new WeightedImplicationGraph<String>(fmv.computeImplicationGraph());
		originalBig = big.clone();
		
		actionUndoHistory = new LinkedList<KSynthesisAction>();
		actionRedoHistory = new LinkedList<KSynthesisAction>();
		
		this.fmv = new FeatureModelVariable(fmv.getIdentifier() + "_synthesis",
				new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()),
				fmv.getFormula().clone());
		
		
		featureComparator = new OutDegreeComparator(big.getImplicationGraph());

		// Check algorithm's parameters
		if (parentSimilarityMetric != null) {
			this.parentSimilarityMetric = parentSimilarityMetric;
		} else {
			this.parentSimilarityMetric = new AlwaysZeroMetric();
		}

		if (clusteringSimilarityMetric != null) {
			this.clusteringSimilarityMetric = clusteringSimilarityMetric;
		} else {
			this.clusteringSimilarityMetric = new AlwaysZeroMetric();
		}
		
		if (clusteringThreshold >= 0 && clusteringThreshold <=1) {
			this.clusteringThreshold = clusteringThreshold;		
		} else {
			this.clusteringThreshold = -1;
		}

		this.complementaryParentSimilarityMetrics = complementaryParentSimilarityMetrics;
		if (complementaryParentSimilarityMetrics  == null ) {
			this.complementaryParentSimilarityMetrics = new ArrayList<Heuristic>();
		}
			

		// Compute groups if necessary
		if (this.parentSimilarityMetric.isMutexGroupsRequired()) {
			mutexGroups = originalFmv.computeMutexGroups();
		} else {
			mutexGroups = new HashSet<FGroup>();
		}
		
		if (this.parentSimilarityMetric.isXorGroupsRequired()) {
			xorGroups = originalFmv.computeXorGroups();	
		} else {
			xorGroups = new HashSet<FGroup>();
		}
		
		if (this.parentSimilarityMetric.isOrGroupsRequired()) {
			orGroups = originalFmv.computeOrGroups();	
		} else {
			orGroups = new HashSet<FGroup>();
		}
		
		// Compute clusters and weights
		computeClusters();
		computeBIGWeights(big);
		
		

	}

	public InteractiveFMSynthesizer(FeatureModelVariable fmv) {
		this(fmv, null, null, null, 0.5);
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
		SelectParentAction action = new SelectParentAction(this, child, parent);
		action.execute();
		actionUndoHistory.push(action);
		
		// A new action prevent from redoing actions
		actionRedoHistory = new LinkedList<KSynthesisAction>();
		
		// Update weights in case this new information modifies our understanding of the clusters
		computeBIGWeights(big);
		
		setChanged();
		notifyObservers();
	}
	
	public void selectParentUnrecorded(String child, String parent) {
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


		// Remove the old child/parent relation if it exists
		String selectedParent = getParentOf(child);
		if (selectedParent != null) {
			FeatureNode<String> selectedParentNode = graph.findVertex(selectedParent);
			graph.removeEdge(graph.findEdge(childNode, selectedParentNode, FeatureEdge.HIERARCHY));
		}

		// Add the edge to the feature graph
		graph.addEdge(childNode, parentNode, FeatureEdge.HIERARCHY);

		// Modify the implication graph to represent this new relation
		Set<SimpleEdge> removedEdges = new HashSet<SimpleEdge>(big.outgoingEdges(child));
		removedEdges.remove(big.findEdge(child, parent));
		big.removeAllEdges(removedEdges);
		
		// Remove the inverse relation in the implication graph, if it exists, to avoid invalid choices
		big.removeEdge(parent, child);
	}

	/**
	 * Suppress the possible child/parent relation in the feature diagram
	 * @param child
	 * @param parent
	 */
	public void ignoreParent(String child, String parent) {
		IgnoreParentAction action = new IgnoreParentAction(this, child, parent);
		action.execute();
		actionUndoHistory.push(action);
		
		// A new action prevent from redoing actions
		actionRedoHistory = new LinkedList<KSynthesisAction>();
		
		// Update weights in case this new information modifies our understanding of the clusters
		computeBIGWeights(big);

		setChanged();
		notifyObservers();
	}
	
	public void ignoreParentUnrecorded(String child, String parent) {
		if (big.parents(child).size() > 1) {
			// Suppress the corresponding edge from the implication graph
			SimpleEdge edge = big.findEdge(child, parent);
			if (edge != null) {
				big.removeEdge(edge);
			}

			// if there is only one parent remaining, we select it
			Set<String> parents = big.parents(child);
			if (parents.size() == 1) {
				selectParent(child, parents.iterator().next());
			}
		}
	}
	
	/**
	 * Reset the parent candidates of a feature
	 * @param child
	 * @param parent
	 */
	public void resetParentCandidates(String feature) {
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
		FeatureNode<String> featureNode;
		try {
			featureNode = graph.findVertex(feature);	
		} catch (IllegalArgumentException e) {
			// Should not happen
			featureNode = new FeatureNode<String>(feature);
			graph.addVertex(featureNode);
		}
		
		// Remove a child/parent relation in the feature diagram if it exists
		String parent = getParentOf(feature);
		if (parent != null) {
			FeatureNode<String> parentNode;
			parentNode = graph.findVertex(parent);
			graph.removeEdge(graph.findEdge(featureNode, parentNode, FeatureEdge.HIERARCHY));

		}
		
		// Reset the parent candidates in the implication graph
		for (String originalParent : originalBig.parents(feature)) {
			big.addEdge(feature, originalParent);
		}
		
		// Update weights in case this new information modifies our understanding of the clusters
		computeBIGWeights(big);

		setChanged();
		notifyObservers();
	}

	/**
	 * List the parent candidates of each feature of the feature model
	 * The list is sorted according to the current metrics
	 * @return
	 */
	public List<KeyValue<String, List<String>>> getParentCandidates() {
		return getParentCandidates(big);
	}
	
	public List<KeyValue<String, List<String>>> getOriginalParentCandidates() {
		return getParentCandidates(originalBig);
	}
	
	public List<KeyValue<String, List<String>>> getParentCandidates(WeightedImplicationGraph<String> big) {
		List<KeyValue<String, List<String>>> parents = new ArrayList<KeyValue<String,List<String>>>();
		for (String feature : big.vertices()) {
			
			List<String> parentList = new ArrayList<String>(big.parents(feature));

			if (parentList.size() > 0) {
	 			// Sort parent candidates according to big weights
				if (parentSimilarityMetric != null) {
					Collections.sort(parentList, new ParentComparator(feature, big));	
				}

				KeyValue<String, List<String>> parentEntry = new KeyValue<String, List<String>>(feature, parentList);
				parents.add(parentEntry);				
			}

		}
		
		// Sort ranking lists according to number of parent candidates
		Collections.sort(parents, featureComparator); 
		return parents;
	}

	/**
	 * Set the similarity metric used in choosing the parent of each feature
	 * It also computes the associated weighted implication graph
	 * @param parentSimilarityMetric
	 */
	public void setParentSimilarityMetric(Heuristic parentSimilarityMetric) {
		this.parentSimilarityMetric = parentSimilarityMetric;
		
		if (parentSimilarityMetric.isMutexGroupsRequired() && mutexGroups == null) {
			mutexGroups = originalFmv.computeMutexGroups();
		} 
		
		if (parentSimilarityMetric.isXorGroupsRequired() && xorGroups == null) {
			xorGroups = originalFmv.computeXorGroups();
			
		}
		if (parentSimilarityMetric.isOrGroupsRequired() && orGroups == null) {
			orGroups = originalFmv.computeOrGroups();	
		}
		computeBIGWeights(big);
		computeBIGWeights(originalBig);
	}

	public void selectFeatureFrequencyMetric() {
		featureFrequencyMetric = new FrequencyMetric (fmv);

		for (SimpleEdge edge : big.edges()) {
			String source = big.getSource(edge);
			String target = big.getTarget(edge);
			double weight = featureFrequencyMetric.support(source, target);
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
	public void setClusteringParameters(Heuristic clusteringSimilarityMetric, double threshold) {
		this.clusteringSimilarityMetric = clusteringSimilarityMetric;
		this.clusteringThreshold = threshold;
		computeClusters();
		computeBIGWeights(big);
		computeBIGWeights(originalBig);
	}

	public void setSupportClusteringParameters(double threshold) {
		this.clusteringThreshold = threshold;
		computeSupportClusters();
	}

	/**
	 * Compute clusters according to the previously specified similarity metric and threshold
	 */
	private void computeClusters() {
		similarityClusters = new HashSet<Set<String>>();
		
		HierarchicalFeatureClusterer hierarchicalClustering = new HierarchicalFeatureClusterer();
		FMExperiment experiment = new FMExperiment(big.getImplicationGraph(), xorGroups, orGroups);
		Dendrogram dendrogram = hierarchicalClustering.computeDendrogram(experiment, clusteringSimilarityMetric);
		Set<Set<String>> clusters = hierarchicalClustering.extractClusters(experiment, dendrogram, clusteringThreshold);

		for (Set<String> cluster : clusters) {
			if (cluster.size() > 1 && checkCluster(cluster)) {
				similarityClusters.add(cluster);
			}
		}
		 
		
		setChanged();
		notifyObservers();
	}

	/**
	 * Check if the cluster is correct w.r.t the BIG
	 * @param cluster
	 * @return
	 */
	private boolean checkCluster(Set<String> cluster) {
		return checkClusterForSiblings(cluster) || checkClusterForParentAndChildren(cluster);
	}
	
	/**
	 * Check if the cluster is formed of siblings w.r.t the BIG
	 * @param cluster
	 * @return
	 */
	private boolean checkClusterForSiblings(Set<String> cluster) {
		for (String parent : big.vertices()) {
			
			if (!cluster.contains(parent)) {
				boolean ok = true;
				
				for (String child : cluster) {
					if (!big.containsEdge(child, parent)) {
						ok = false;
						break;
					}
				}
				
				if (ok) {
					return true;
				}	
			}
		}
		
		return false;
	}

	/**
	 * Check if the cluster is formed of a parent and its children w.r.t the BIG
	 * @param cluster
	 * @return
	 */
	private boolean checkClusterForParentAndChildren(Set<String> cluster) {
		for (String parent : cluster) {
			boolean ok = true;
			
			for (String child : cluster) {
				if (!parent.equals(child) && !big.containsEdge(child, parent)) {
					ok = false;
					break;
				}
			}
			
			if (ok) {
				return true;
			}
		}
		
		return false;
	}
	
	private void computeSupportClusters() {
		supportClusters = new ArrayList<Set<Set<String>>>();

		BIGCliques_Threshold cliques1 = new BIGCliques_Threshold(0.2);
		cliques1.updateBIG(big);
		supportClusters.add(new HashSet<Set<String>>(cliques1.getCliques()));

		BIGCliques_Threshold cliques2 = new BIGCliques_Threshold(0.6);
		cliques2.updateBIG(big);
		supportClusters.add(new HashSet<Set<String>>(cliques2.getCliques()));

		BIGCliques_Threshold cliques3 = new BIGCliques_Threshold(0.8);
		cliques3.updateBIG(big);
		supportClusters.add(new HashSet<Set<String>>(cliques3.getCliques()));

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
		return DirectedCliqueFinder.INSTANCE.findAll(originalBig.getImplicationGraph());
	}

	/**
	 * Set this feature as the root of the feature model
	 * @param root
	 */
	public void setRoot(String root) {
		FeatureGraph<String> graph = fmv.getFm().getDiagram();

		// Restore the state of the old root if it exist
		String selectedRoot = getRoot();
		if (selectedRoot != null) {
			for (String possibleParent : originalBig.parents(selectedRoot)) {
				big.addEdge(selectedRoot, possibleParent);
			}
			FeatureNode<String> selectedRootNode = graph.findVertex(selectedRoot);
			graph.removeEdge(graph.findEdge(selectedRootNode, graph.getTopVertex(), FeatureEdge.HIERARCHY));
		}

		FeatureNode<String> rootNode;
		try {
			rootNode = graph.findVertex(root);	
		} catch (IllegalArgumentException e) {
			rootNode = new FeatureNode<String>(root);
			graph.addVertex(rootNode);
		}

		// Remove the current parent of the new root		
		for (FeatureNode<String> parent : graph.parents(rootNode)) {
			graph.removeEdge(graph.findEdge(rootNode, parent, FeatureEdge.HIERARCHY));
		}

		// Add an edge to the feature graph to define the node as the root
		graph.addEdge(rootNode, graph.getTopVertex(), FeatureEdge.HIERARCHY);

		// Delete every outgoing edges of the root node in the implication graph
		Set<SimpleEdge> removedEdges = new HashSet<SimpleEdge>(big.outgoingEdges(root));
		big.removeAllEdges(removedEdges);

		// Update weights in case this new information modifies our understanding of the clusters
		computeBIGWeights(big);

		setChanged();
		notifyObservers();
	}

	/**
	 * Compute a feature model with a complete feature diagram
	 * according to the parent similarity metric
	 * @return a complete feature model
	 */
	public FeatureModelVariable computeCompleteFeatureModel() {
		
		
		// FIXME : experimental : choose a root and put the other root candidates as its children 
//		WeightedImplicationGraph<String> tempBig = big.clone();
//		Set<String> possibleRoots = tempBig.reduceCliques().roots().iterator().next();
//		String selectedRoot = possibleRoots.iterator().next();
//		tempBig.removeAllEdges(new HashSet<SimpleEdge>(tempBig.outgoingEdges(selectedRoot)));
//		possibleRoots.remove(selectedRoot);
//		for (String featureLevel1 : possibleRoots) {
//			tempBig.removeAllEdges(new HashSet<SimpleEdge>(tempBig.outgoingEdges(featureLevel1)));
//			tempBig.addEdge(featureLevel1, selectedRoot);
//		}
		// END experimental

		
		// FIXME : experimental : reduce clique and apply transitive reduction
//		ImplicationGraph<Set<String>> reducedBIG = big.reduceCliques();
//		TransitiveReduction.INSTANCE.reduce(reducedBIG);
//		
//		for (Set<String> clique : reducedBIG.vertices()) {
//			Iterator<String> it = clique.iterator();
//			String representativeFeature = it.next();
//
//			HashSet<String> nonRepresentativeFeatures = new HashSet<String>();
//			while (it.hasNext()) {
//				String feature = it.next();
//				selectParent(feature, representativeFeature);
//				nonRepresentativeFeatures.add(feature);
//			}
//			
//			clique.removeAll(nonRepresentativeFeatures);
//		}
//
//		for (SimpleEdge edge : reducedBIG.edges()) {
//			String child = reducedBIG.getEdgeSource(edge).iterator().next();
//			String parent = reducedBIG.getEdgeTarget(edge).iterator().next();
//			if (big.outgoingEdges(child).size() > 1) {
//				selectParent(child, parent);	
//			}
//		}
		
		// END experimental

		
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

		// Add edge root -> top
		String root = hierarchy.roots().iterator().next();
		fg.addEdge(fg.findVertex(root), fg.getTopVertex(), FeatureEdge.HIERARCHY);


		FeatureModel<String> fm = new FeatureModel<String>(fg);
		FeatureModelVariable completeFM = new FeatureModelVariable(fmv.getIdentifier() + "_completed", fm);
		return completeFM;
	}

	public double getClusteringThreshold() {
		return clusteringThreshold;
	}

	public Heuristic getClusteringSimilarityMetric() {
		return clusteringSimilarityMetric;
	}

	public WeightedImplicationGraph<String> getWeightedImplicationGraph() {
		return big;
	}

	/**
	 * Returns the parent of the feature if defined, otherwise null
	 * @param feature
	 * @return
	 */
	public String getParentOf(String feature) {
		String parent = null;
		FeatureGraph<String> hierarchy = fmv.getFm().getDiagram();
		FeatureNode<String> featureNode = null;
		try {
			featureNode = hierarchy.findVertex(feature);	
		} catch (IllegalArgumentException e) {

		}
		if (featureNode != null) {
			Set<FeatureNode<String>> parents = hierarchy.parents(featureNode);
			if (!parents.isEmpty()) {
				parent = parents.iterator().next().getFeature();
			}	
		}
		return parent;
	}

	/**
	 * Returns the root of the feature model if defined, otherwise null
	 * @return
	 */
	public String getRoot() {
		FeatureGraph<String> hierarchy = fmv.getFm().getDiagram();
		Set<FeatureNode<String>> roots = hierarchy.children(hierarchy.getTopVertex());
		if (roots.isEmpty()) {
			return null;
		} else {
			return roots.iterator().next().getFeature();
		}
	}

	/**
	 * Returns the possible children among the cluster's features
	 * @param feature
	 * @param cluster
	 */
	public Set<String> getPossibleChildren(String feature, Set<String> cluster) {
		Set<String> possibleChildren = new HashSet<String>();
		for (String possibleChild : cluster) {
			if (big.containsEdge(possibleChild, feature)) {
				possibleChildren.add(possibleChild);
			}
		}
		return possibleChildren;
	}

	/**
	 * Returns the possible parents of these features 
	 * @param features
	 * @return 
	 */
	public Set<String> getPossibleParents(Set<String> features) {
		Set<String> possibleParents = new HashSet<String>();
		boolean first = true;
		for (String feature : features) {
			if (first) {
				first = false;
				possibleParents.addAll(big.parents(feature));
			} else {
				possibleParents.retainAll(big.parents(feature));	
			}
		}
		return possibleParents;
	}

	public void setComplementaryParentSimilarityMetrics(List<Heuristic> complementaryMetrics) {
		this.complementaryParentSimilarityMetrics = complementaryMetrics;
		computeBIGWeights(big);
		computeBIGWeights(originalBig);
	}

	/**
	 * Compute the weights of the Binary Implication Graph according to 
	 * the main parent similarity heuristics,
	 * the complementary heuristics
	 * and the clusters
	 */
	private void computeBIGWeights(WeightedImplicationGraph<String> big) {
		// TODO : avoid computing the same thing twice

		// Compute weights with the main heuristic
		parentSimilarityMetric.setImplicationGraph(big.getImplicationGraph());
		Set<FGroup> groups = new HashSet<FGroup>();
		groups.addAll(mutexGroups);
		groups.addAll(xorGroups);
		groups.addAll(orGroups);
		parentSimilarityMetric.setGroups(groups);
		
		for (SimpleEdge edge : big.edges()) {
			String source = big.getSource(edge);
			String target = big.getTarget(edge);
			double weight = parentSimilarityMetric.similarity(source, target);
			big.setEdgeWeight(edge, weight);
		}

		// Tune weights with complementary heuristics
		tuneWeightsWithComplementaryHeuristics(big);

		// Tune weights with clusters
		tuneWeightsWithComplementaryClusters(big, similarityClusters);

		setChanged();
		notifyObservers();
	}


	/**
	 * Tune the weights of the BIG according to the complementary heuristics
	 */
	private void tuneWeightsWithComplementaryHeuristics(WeightedImplicationGraph<String> big) {

		// TODO : put these thresholds in a better place
		final double VERY_HIGH_THRESHOLD = 0.6; // the feature should be selected
		final double HIGH_THRESHOLD = 0.6; // the feature might be selected
		final double HIGH_VALUE = 1; // assigned value if very high probability

		final double VERY_LOW_THRESHOLD = 0.1; // the feature might be rejected
		final double LOW_THRESHOLD = 0.3; // the feature should be rejected
		final double LOW_VALUE = 0; // assigned value if very low probability

		for (SimpleEdge edge : big.edges()) {
			String source = big.getSource(edge);
			String target = big.getTarget(edge);

			// Compute the complementary heuristic choices
			boolean veryHigh = false, high = false, veryLow = false, low = false; 
			for (Heuristic metric : complementaryParentSimilarityMetrics) {
				
				metric.setImplicationGraph(big.getImplicationGraph());
				Set<FGroup> groups = new HashSet<FGroup>();
				groups.addAll(mutexGroups);
				groups.addAll(xorGroups);
				groups.addAll(orGroups);
				metric.setGroups(groups);
				
				double weight = metric.similarity(source, target);
				if (weight != 0) { // Avoid non representative value
					if (weight > VERY_HIGH_THRESHOLD)
						veryHigh = true;
					if (weight > HIGH_THRESHOLD)
						high = true;
					if (weight < VERY_LOW_THRESHOLD)
						veryLow = true;
					if (weight < LOW_THRESHOLD)
						low = true;					
				}
			}	

			// Check if there is no conflicts between the heuristics (including the main heuristic)
			if (veryHigh && !low && (big.getEdgeWeight(edge) > LOW_THRESHOLD || big.getEdgeWeight(edge) == 0)) {
				big.setEdgeWeight(edge, HIGH_VALUE);
			} else if (veryLow && !high && big.getEdgeWeight(edge) < HIGH_THRESHOLD) {
				big.setEdgeWeight(edge, LOW_VALUE);
			}
		}
	}

	/**
	 * Tune the weights of the BIG according to the clusters
	 */
	private void tuneWeightsWithComplementaryClusters(WeightedImplicationGraph<String> big, Set<Set<String>> clusters) {
		for (Set<String> cluster : clusters) {
			if (cluster.size() > 1) {
				// Look for a parent within the cluster
				String bestParent = findBestParentWithinCluster(cluster);

				// Look for a common parent
				if (bestParent == null) {
					bestParent = findBestCommonParent(cluster);
				}
				
				if (bestParent != null) {
					promoteParentOfCluster(big, cluster, bestParent);
				} else {
					tuneWeightsWithComplementaryClusters(big, separateInSubclusters(clusters));
				}
				
			}
		}
	}


	/**
	 * Find the best parent within the cluster
	 * @param cluster
	 * @return the best parent or null if it does not exist
	 */
	public String findBestParentWithinCluster(Set<String> cluster) {
		String bestParent = null;
		double bestParentScore = 0;
		
		for (String feature : cluster) {
			Set<String> otherFeatures = new HashSet<String>(cluster);
			otherFeatures.remove(feature);
			if (getPossibleParents(otherFeatures).contains(feature)) {
				double parentScore = getParentScoreForCluster(otherFeatures, feature);
				if (bestParent == null || bestParentScore < parentScore) {
					bestParent = feature;
					bestParentScore = parentScore;
				}
			}
		}
		
		return bestParent;
	}
	
	/**
	 * Find the best common parent of the cluster
	 * @param cluster
	 * @return the best parent or null if it does not exist
	 */
	public String findBestCommonParent(Set<String> cluster) {
		String bestParent = null;
		double bestParentScore = 0;
		
		Set<String> possibleParents = this.getPossibleParents(cluster);
		if (!possibleParents.isEmpty()) {
			for (String possibleParent : possibleParents) {
				double parentScore = getParentScoreForCluster(cluster, possibleParent);
				if (bestParent == null || bestParentScore < parentScore) {
					bestParent = possibleParent;
					bestParentScore = parentScore;
				}
			}
		}
		
		return bestParent;
	}
	
	private double getParentScoreForCluster(Set<String> cluster, String parent) {
		double score = 0;
		for (String feature : cluster) {
			score += big.getEdgeWeight(big.findEdge(feature, parent));
		}
		return score;
	}

	private Set<Set<String>> separateInSubclusters(Set<Set<String>> clusters) {
		// TODO : separate in subclusters
		return new HashSet<Set<String>>();
	}

	private void promoteParentOfCluster(WeightedImplicationGraph<String> big, Set<String> cluster, String parent) {
		final double MAX = 1;
		if (parent != null) {
			for (String feature : cluster) {
				if (!feature.equals(parent)) {
					big.setEdgeWeight(big.findEdge(feature, parent), MAX);
				}
			}
		}
		
	}

	public WeightedImplicationGraph<String> getOriginalBig() {
		return originalBig;
	}
	
	/**
	 * Reset the FM to its initial state (no hierarchy)
	 */
	private void reset() {
		fmv.setFm(new FeatureModel<String>(FeatureGraphFactory.mkStringFactory().mkTop()));
		big = originalBig.clone();
	}
	
	/**
	 * Undo the previous action
	 */
	public void undo() {
		if (!actionUndoHistory.isEmpty()) {
			reset();
			actionRedoHistory.push(actionUndoHistory.peek());
			actionUndoHistory.pop();
			for (KSynthesisAction action : actionUndoHistory) {
				action.execute();
			}	
		}
	}
	
	/**
	 * Redo the previously undone action
	 */
	public void redo() {
		if (!actionRedoHistory.isEmpty()) {
			KSynthesisAction action = actionRedoHistory.pop();
			action.execute();
			actionUndoHistory.push(action);	
		}
	}
	
}
