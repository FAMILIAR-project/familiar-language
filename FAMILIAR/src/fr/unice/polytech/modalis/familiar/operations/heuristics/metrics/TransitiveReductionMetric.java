package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.graph.TransitiveReduction;
import gsd.synthesis.FeatureNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class TransitiveReductionMetric implements FeatureSimilarityMetric {

	private ImplicationGraph<String> implicationGraph;
	private ImplicationGraph<String> hierarchy;
	private boolean orRequired;
	
	public TransitiveReductionMetric() {
		orRequired = true;
	}

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String feature, String parent) {
		if (this.implicationGraph == null || !this.implicationGraph.equals(implicationGraph)) {
			this.implicationGraph = implicationGraph.clone();
			
			ImplicationGraph<Set<String>> reducedGraph = implicationGraph.reduceCliques();
			TransitiveReduction.INSTANCE.reduce(reducedGraph);
			hierarchy = buildHierarchy(reducedGraph, xorGroups, orGroups);
		}

		
		if (hierarchy.containsEdge(feature, parent)) {
//			System.out.println(feature + " : " + parent);
//			return new RandomMetric().similarity(null, null, null, null, null);
			return 1;
		} else {
			return 0;
		}
	}

	/**
	 * Build the hierarchy of the FM
	 * @param reducedGraph 
	 * @param xorGroups 
	 * @return
	 */
	private ImplicationGraph<String> buildHierarchy(ImplicationGraph<Set<String>> reducedGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups) {
		hierarchy = new ImplicationGraph<String>();
		
		HashMap<Set<String>, String> representativeFeatures = 
				createVertices(reducedGraph);
		
		expandCliques(reducedGraph, representativeFeatures);
		
		createEdges(reducedGraph, representativeFeatures);
		
		selectParents(xorGroups, orGroups);
		
		return hierarchy;
	}



	/**
	 * Create vertices and choose representative features
	 * @param reducedGraph
	 * @param hierarchy
	 * @return 
	 * @return representative features
	 */
	private HashMap<Set<String>, String> createVertices(ImplicationGraph<Set<String>> reducedGraph) {
		
		HashMap<Set<String>, String> representativeFeatures = new HashMap<Set<String>, String>();
		
		for (Set<String> clique : reducedGraph.vertices()) {
			String representativeFeature = null;
			for (String f : clique) {
				hierarchy.addVertex(f);
				
				if (representativeFeature == null){ // || f.compareTo(representativeFeature) < 0) {
					representativeFeature = f;
				} 
			}
			representativeFeatures.put(clique, representativeFeature);
//			System.out.println(representativeFeature + " : " + clique);
		}
		
		return representativeFeatures;
	}
	
	/**
	 * Expand cliques
	 * @param reducedGraph
	 * @param hierarchy
	 * @param representativeFeatures
	 */
	private void expandCliques(ImplicationGraph<Set<String>> reducedGraph,
			HashMap<Set<String>, String> representativeFeatures) {
		// Expand cliques
		for (Set<String> clique : reducedGraph.vertices()) {
			String representativeFeature = representativeFeatures.get(clique);
			for (String f : clique) {
				if (!f.equals(representativeFeature)) {
					hierarchy.addEdge(f, representativeFeature);	
				}
			}
		}
	}
	
	/**
	 * Create edges from the implication graph between representative features 
	 * @param reducedGraph
	 * @param hierarchy
	 * @param representativeFeatures
	 */
	private void createEdges(ImplicationGraph<Set<String>> reducedGraph, HashMap<Set<String>, String> representativeFeatures) {
		for (SimpleEdge edge : reducedGraph.edges()) {
			Set<String> source = reducedGraph.getEdgeSource(edge);
			String sourceFeature = representativeFeatures.get(source);
			
			Set<String> target = reducedGraph.getEdgeTarget(edge);
			String targetFeature = representativeFeatures.get(target);
			
			hierarchy.addEdge(sourceFeature, targetFeature);
		}
	}
	
	private void selectParents(Set<FGroup> xorGroups, Set<FGroup> orGroups) {
		
		Set<FGroup> groups = new HashSet<FGroup>();
		if (xorGroups != null) {
			groups.addAll(xorGroups);	
		}
		if (orGroups != null) {
			groups.addAll(orGroups);	
		}
		
		for (String feature : hierarchy.vertices()) {
			Set<String> possibleParents = hierarchy.parents(feature);
			String parent = null;
			
			// If multiple parents, find one according to groups
			if (possibleParents.size() > 1) {
				for (String possibleParent : possibleParents) {
					if (sameGroup(feature, possibleParent, groups)) {
						parent = possibleParent;
					}
				}
			}
			
			// Remove edges of the wrong parents
			if (parent != null) {
				for (String possibleParent : possibleParents) {
					if (!possibleParent.equals(parent)) {
						hierarchy.removeEdge(feature, possibleParent);
					}
				}
			}
		} 
		
	}
	
	/**
	 * Check that feature is a child of possibleParent in a group
	 * @param feature
	 * @param possibleParent
	 * @param groups
	 * @return
	 */
	private boolean sameGroup(String feature, String possibleParent, Set<FGroup> groups) {
		for (FGroup group : groups) {
			Set<String> parents = group.getTarget().features();
			
			Set<String> children = new HashSet<String>();
			for (FeatureNode<String> node : group.getSources()) {
				children.addAll(node.features());
			}

			if (parents.contains(possibleParent) && children.contains(feature)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Transitive reduction metric";
	}

	@Override
	public boolean isXorGroupRequired() {
		return true;
	}

	@Override
	public boolean isOrGroupRequired() {
		return this.orRequired;
	}

	public void setOrRequired(boolean orRequired) {
		this.orRequired = orRequired;
	}
 

}
