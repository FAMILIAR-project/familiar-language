package fr.unice.polytech.modalis.familiar.operations.heuristics.clustering;

import java.util.HashSet;
import java.util.Set;

import ch.usi.inf.sape.hac.HierarchicalAgglomerativeClusterer;
import ch.usi.inf.sape.hac.agglomeration.AgglomerationMethod;
import ch.usi.inf.sape.hac.agglomeration.AverageLinkage;
import ch.usi.inf.sape.hac.dendrogram.Dendrogram;
import ch.usi.inf.sape.hac.dendrogram.DendrogramBuilder;
import ch.usi.inf.sape.hac.dendrogram.DendrogramNode;
import ch.usi.inf.sape.hac.dendrogram.MergeNode;
import ch.usi.inf.sape.hac.dendrogram.ObservationNode;
import ch.usi.inf.sape.hac.experiment.DissimilarityMeasure;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import gsd.graph.ImplicationGraph;

public class HierarchicalFeatureClusterer {

	/**
	 * Compute dendrogram
	 * @param experiment
	 * @param heuristic
	 * @return
	 */
	public Dendrogram computeDendrogram(FMExperiment experiment, FeatureSimilarityMetric metric) {
		DissimilarityMeasure dissimilarityMeasure = new HeuristicDissimilarityMeasure(metric);
		AgglomerationMethod agglomerationMethod = new AverageLinkage();
		DendrogramBuilder dendrogramBuilder = new DendrogramBuilder(experiment.getNumberOfObservations());
		HierarchicalAgglomerativeClusterer clusterer = new HierarchicalAgglomerativeClusterer(experiment, dissimilarityMeasure, agglomerationMethod);
		clusterer.cluster(dendrogramBuilder);
		return dendrogramBuilder.getDendrogram();
	}

	/**
	 * Print dendrogram
	 * @param experiment
	 * @param node
	 * @param depth
	 */
	public void printDendrogram(FMExperiment experiment, DendrogramNode node, int depth) {
		if (node != null) {
			StringBuilder builder = new StringBuilder();
			for (int i=0; i<depth; i++) {
				builder.append(" ");
			}

			if (node instanceof ObservationNode) {
				ObservationNode obsNode = (ObservationNode) node;
				builder.append(experiment.getFeature(obsNode.getObservation()));
			} else if (node instanceof MergeNode) {
				MergeNode mergeNode = (MergeNode) node;
				builder.append(mergeNode.getDissimilarity());
			}
			System.out.println(builder.toString());
			printDendrogram(experiment, node.getLeft(), depth+1);
			printDendrogram(experiment, node.getRight(), depth+1);	
		}
	}

	/**
	 * Extract clusters from a dendrogram and its corresponding experiment.
	 * The threshold is the maximum dissimilarity to form a cluster.
	 * @param experiment
	 * @param dendrogram
	 * @param threshold
	 * @return
	 */
	public Set<Set<String>> extractClusters(FMExperiment experiment, Dendrogram dendrogram, double threshold) {
		return extractClusters(dendrogram.getRoot(), experiment, threshold);
	}

	private Set<Set<String>> extractClusters(DendrogramNode node, FMExperiment experiment, double threshold) {
		Set<Set<String>> clusters = new HashSet<Set<String>>();
		if (node != null) {
			Set<Set<String>> leftClusters = extractClusters(node.getLeft(), experiment, threshold);
			Set<Set<String>> rightClusters = extractClusters(node.getRight(), experiment, threshold);

			if (node instanceof ObservationNode) {
				ObservationNode obsNode = (ObservationNode) node;
				Set<String> cluster = new HashSet<String>();
				cluster.add(experiment.getFeature(obsNode.getObservation()));
				clusters.add(cluster);
			} else if (node instanceof MergeNode) {
				MergeNode mergeNode = (MergeNode) node;

				if (mergeNode.getDissimilarity() > threshold) {
					clusters.addAll(leftClusters);
					clusters.addAll(rightClusters);
				} else {
					leftClusters.iterator().next().addAll(rightClusters.iterator().next());
					clusters.addAll(leftClusters);
				}
			}
		}
		return clusters;
	}

	/**
	 * Merge features in a implication graph
	 * Only the common edges are retained
	 * @param big : implication graph
	 * @param cluster : features to merge
	 */
	public void mergeCluster(ImplicationGraph<String> big, Set<String> cluster) {
		if (cluster.size() > 1) {
			Set<String> commonParents = null;
			Set<String> commonChildren = null;

			// Remove edges including the cluster's features and compute common parents and children
			StringBuilder builder = new StringBuilder();
			for (String feature : cluster) {
				builder.append(feature);

				Set<String> parents = big.parents(feature);
				Set<String> children = big.children(feature);

				if (commonParents == null && commonChildren == null) {
					commonParents = new HashSet<String>();
					commonParents.addAll(parents);
					commonChildren = new HashSet<String>();
					commonChildren.addAll(children);
				} else {
					commonParents.retainAll(parents);
					commonChildren.retainAll(children);
				}

				big.removeVertex(feature);
			}

			// Add new merged feature
			String clusterName = builder.toString();
			big.addVertex(clusterName);

			// Add common edges
			for (String parent : commonParents) {
				big.addEdge(clusterName, parent);
			}

			for (String child : commonChildren) {
				big.addEdge(child, clusterName);
			}

		}
	}

	/**
	 * Separate a previously merged cluster.
	 * Only common edges are restored.
	 * @param big
	 * @param cluster
	 */
	public void separateCluster(ImplicationGraph<String> big, Set<String> cluster) {
		if (cluster.size() > 1) {
			StringBuilder builder = new StringBuilder();
			for (String feature : cluster) {
				builder.append(feature);
				big.addVertex(feature);
			}
			String clusterName = builder.toString();
			for (String parent : big.parents(clusterName)) {
				for (String feature : cluster) {
					big.addEdge(feature, parent);
				}
			}

			for (String child : big.children(clusterName)) {
				for (String feature : cluster) {
					big.addEdge(child, feature);
				}
			}

			big.removeVertex(clusterName);
		}
	}
}
