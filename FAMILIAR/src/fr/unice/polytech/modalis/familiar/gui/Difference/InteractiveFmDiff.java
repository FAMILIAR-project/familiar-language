package fr.unice.polytech.modalis.familiar.gui.Difference;

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
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureFrequencyMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FrequencyMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.OptimumBranchingFinder;
import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.unice.polytech.modalis.familiar.operations.measures.cliques.BIGCliques_Threshold;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class InteractiveFmDiff {
	
	private FeatureModelVariable fmv1;
	private FeatureModelVariable fmv2;
	private List<FeatureEdge> fm1_diffEdges  = new ArrayList<FeatureEdge>();
	private List<FeatureEdge> fm2_diffEdges  = new ArrayList<FeatureEdge>();
	
	public FeatureModelVariable getFeatureModelVariable1() {
		return fmv1;
	}
	public FeatureModelVariable getFeatureModelVariable2() {
		return fmv2;
	}
	public List<FeatureEdge> getFm1_diffEdges() {
		return fm1_diffEdges;
	}
	public List<FeatureEdge> getFm2_diffEdges() {
		return fm2_diffEdges;
	}
	
	
}
