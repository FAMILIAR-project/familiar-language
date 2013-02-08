package fr.unice.polytech.modalis.familiar.parser;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class HierarchyMergerWithMST extends HierarchyMerger {

	/**
	 * @return a feature model in which the hierarchy of features is optimized
	 *         in the sense that the number of parent-child relationship in each
	 *         feature model is maximal (minimum (actually, maximum) spanning
	 *         tree algorithm (MST) is used) FIXME why Mode m is not used?
	 */
	@Override
	public FeatureModel<String> build(Collection<FeatureModelVariable> lfmvs) {
	
		assert (lfmvs != null);
		assert (lfmvs.size() > 1);

		SimpleDirectedWeightedGraph<FeatureNode<String>, DefaultWeightedEdge> wGraph = new SimpleDirectedWeightedGraph<FeatureNode<String>, DefaultWeightedEdge>(
				DefaultWeightedEdge.class);
		
		
		List<FeatureModel<String>> lfms = new ArrayList<FeatureModel<String>>() ;
		for (FeatureModelVariable fmv : lfmvs) {
			lfms.add(fmv.getHierarchy()) ;
		}

		for (FeatureModel<String> fm : lfms) {
			FeatureGraph<String> fg = fm.getDiagram().clone();
			for (FeatureNode<String> fn : fg.vertices())
				wGraph.addVertex(fn);
		}

		for (FeatureModel<String> fm : lfms) {
			FeatureGraph<String> fg = fm.getDiagram().clone();
			Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
			for (FeatureEdge fe : edges) {
				FeatureNode<String> source = fg.getSource(fe);
				FeatureNode<String> target = fg.getTarget(fe);
				if (!wGraph.containsEdge(source, target))
					wGraph.addEdge(source, target); // we add it
				else { // already exists

					DefaultWeightedEdge edgeToAdd = wGraph.getEdge(source,
							target);
					double weight = wGraph.getEdgeWeight(edgeToAdd);
					wGraph.setEdgeWeight(edgeToAdd, weight + 1);
					// System.err.println("already exists... s=" + source +
					// "\t t=" + target + "\t w=" +
					// wGraph.getEdgeWeight(edgeToAdd));
				}

			}

		}

		// maximum spanning tree
		// minus all weight
		Set<DefaultWeightedEdge> wedges = wGraph.edgeSet();
		for (DefaultWeightedEdge dwe : wedges) {
			double w = wGraph.getEdgeWeight(dwe);
			wGraph.setEdgeWeight(dwe, w * -1);
		}

		// System.err.println("wGraph=" + wedgesToString(wGraph.edgeSet(),
		// wGraph));

		KruskalMinimumSpanningTree<FeatureNode<String>, DefaultWeightedEdge> mst = new org.jgrapht.alg.KruskalMinimumSpanningTree<FeatureNode<String>, DefaultWeightedEdge>(
				wGraph); // Release 0.8.2

		Set<DefaultWeightedEdge> spanningEdges = mst.getEdgeSet();

		// first we had vertices...
		FeatureModel<String> basis = FeatureGraphFactory.mkStringFactory().mkTopModel() ; 
		FeatureGraph<String> diagBasis = basis.getDiagram();

		for (FeatureModel<String> fm : lfms) {
			FeatureGraph<String> fg = fm.getDiagram().clone();
			for (FeatureNode<String> fn : fg.vertices())
				diagBasis.addVertex(fn);
		}

		// ...and then the edges of the MST
		for (DefaultWeightedEdge dwe : spanningEdges) {
			FeatureNode<String> s = wGraph.getEdgeSource(dwe);
			FeatureNode<String> t = wGraph.getEdgeTarget(dwe);
			diagBasis.addEdge(s, t, FeatureEdge.HIERARCHY);
		}

		return basis;

	}
	
	

	

}
