package fr.unice.polytech.modalis.familiar.parser;


import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.operations.KSynthesis;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class FeatureHierarchySelectorMST {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureHierarchySelector.class);


	private final BDDBuilder<String> _builder;
	private final FeatureGraphFactory<String> _fgf;

	private Collection<FeatureModelVariable> _lfms;
	
	public FeatureHierarchySelectorMST(Collection<FeatureModelVariable> lfms, BDDBuilder<String> builder) {
		_lfms = lfms ;
		_builder = builder;
		_fgf = builder.getFeatureGraphFactory();
	}

	public FeatureModel<String> build(Formula<String> f) {
		/*
		FeatureGraph<String> g = _fgf.mkTop();
		FeatureModel<String> fm = new FeatureModel<String>(g);
		*/
		
		ImplicationGraph<String> impl = IGBuilder.build(f, _builder);
		return new FeatureModel<String>(ImplicationGraphUtil.toFeatureGraph(impl));
		//mkHierarchyAndGroups(g, impl);
		//TransitiveReduction.INSTANCE.reduce(g);
		//mkSyntheticRoot(g);

		//return fm;
	}
	
	public void mkSyntheticRoot(FeatureGraph<String> g) {

		Set<FeatureNode<String>> roots = findRoots(g);
		FeatureNode<String> synth = g.getTopVertex();

		for (FeatureNode<String> v : roots) {
			g.addEdge(v, synth, FeatureEdge.HIERARCHY);
			g.addEdge(v, synth, FeatureEdge.MANDATORY);
		}
	}
	
	public static <String> Set<FeatureNode<String>> findRoots(FeatureGraph<String> g) {
		Set<FeatureNode<String>> cand = new HashSet<FeatureNode<String>>(g.vertices());
		for (FeatureEdge e : g.edges()) {
			cand.removeAll(g.getSources(e));
		}
		cand.remove(g.getTopVertex());
		cand.remove(g.getBottomVertex());
		return cand;
	}

	public void mkHierarchyAndGroups(FeatureGraph<String> g, ImplicationGraph<String> impl) {

		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);

		//Make the hierarchy
		for (Set<String> clique : cliques)
			for (String t : clique) {
				g.addVertex(new FeatureNode<String>(t));
			}
	
		
		
		for (String v : impl.vertices())
			if (!g.features().contains(v))
				g.addVertex(new FeatureNode<String>(v));
		
		for (Set<String> clique : cliques) {

			FeatureNode<String> group = g.findVertex(clique.iterator().next());

			for (String u : clique) {
				for (SimpleEdge e : impl.incomingEdges(u).toArray(new SimpleEdge[0])) {
					String source = impl.getSource(e);
					if (!clique.contains(source))
						g.addEdge(g.findVertex(source), group, FeatureEdge.HIERARCHY);
					impl.removeEdge(e);
				}

				for (SimpleEdge e : impl.outgoingEdges(u).toArray(new SimpleEdge[0])) {
					String target = impl.getTarget(e);
					if (!clique.contains(target))
						g.addEdge(group, g.findVertex(target), FeatureEdge.HIERARCHY);
					impl.removeEdge(e);
				}
			}
		}

		//Add the remaining hierarchy edges
		for (SimpleEdge e : impl.edges())
			g.addEdge(g.findVertex(impl.getSource(e)),
					g.findVertex(impl.getTarget(e)),
					FeatureEdge.HIERARCHY);

	}
	
	

	public FeatureModel<String> mkMST(FeatureModel<String> fmImplHierarchy) {
						
		FeatureGraph<String> fgI = fmImplHierarchy.getDiagram() ; 
		Set<FeatureNode<String>> vertices = fgI.vertices() ;
		
		// DEBUG
		for (FeatureNode<String> vertex : vertices) {
			Collection<FeatureEdge> iEdges = fgI.outgoingEdges(vertex, FeatureEdge.HIERARCHY);
			if (iEdges.size() > 1) {
				_LOGGER.debug("ambigous edges for " + vertex);
			}
		}
		
		SimpleDirectedWeightedGraph<FeatureNode<String>, DefaultWeightedEdge> wGraph = 
			new SimpleDirectedWeightedGraph<FeatureNode<String>, DefaultWeightedEdge>(DefaultWeightedEdge.class);

		for (FeatureNode<String> v : vertices) {
			wGraph.addVertex(v);
		}
		
		Set<FeatureEdge> edgesI = fgI.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge fe : edgesI) {
		
			FeatureNode<String> source = fgI.getSource(fe);
			FeatureNode<String> target = fgI.getTarget(fe);
			wGraph.addEdge(source, target); 
		
		}
		

		for (FeatureModelVariable fm : _lfms) {
			FeatureGraph<String> fg = (FeatureGraph<String>) fm.getHierarchy().getDiagram().clone();
			Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
			for (FeatureEdge fe : edges) {
				FeatureNode<String> source = fg.getSource(fe);
				FeatureNode<String> target = fg.getTarget(fe);
				if (wGraph.containsVertex(source) && wGraph.containsVertex(target)) {
					if (!wGraph.containsEdge(source, target))
						wGraph.addEdge(source, target); // we add it
					else { // already exists
						DefaultWeightedEdge edgeToAdd = wGraph.getEdge(source,
								target);
						double weight = wGraph.getEdgeWeight(edgeToAdd);
						wGraph.setEdgeWeight(edgeToAdd, weight + 1);
					}
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


		KruskalMinimumSpanningTree<FeatureNode<String>, DefaultWeightedEdge> mst = 
			new org.jgrapht.alg.KruskalMinimumSpanningTree<FeatureNode<String>, DefaultWeightedEdge>(
				wGraph); // Release 0.8.2
		
		
		
		
		Set<DefaultWeightedEdge> spanningEdges = mst.getEdgeSet();

		// first we had vertices...
		FeatureModel<String> basis = _fgf.mkTopModel();
		FeatureGraph<String> diagBasis = basis.getDiagram();

		for (FeatureModelVariable fm : _lfms) {
			FeatureGraph<String> fg = (FeatureGraph<String>) fm.getHierarchy().getDiagram().clone();
			for (FeatureNode<String> fn : fg.vertices())
				diagBasis.addVertex(fn);
		}

		// ...and then the edges of the MST
		for (DefaultWeightedEdge dwe : spanningEdges) {
			FeatureNode<String> s = wGraph.getEdgeSource(dwe);
			FeatureNode<String> t = wGraph.getEdgeTarget(dwe);
			diagBasis.addEdge(s, t, FeatureEdge.HIERARCHY);
		}


		_LOGGER.debug("diagBasis=" + diagBasis);
		Set<FeatureNode<String>> rTs = KSynthesis.findMyRoots(diagBasis);
		_LOGGER.debug("roots=" + rTs);
		if (rTs.size() > 1) {
			_LOGGER.debug("Multiple roots! " + rTs);
			/*
			 * check that there is at least the common root of input feature models
			 * 
			 */
			FeatureNode<String> rootNode = determineCommontRootFeatures(_lfms);
			rTs.remove(rootNode);
			for (FeatureNode<String> ch : rTs) {
				diagBasis.removeEdge(diagBasis.findEdge(ch, diagBasis.getTopVertex(), FeatureEdge.HIERARCHY));
				diagBasis.addEdge(ch, rootNode, FeatureEdge.HIERARCHY) ;
			}
			diagBasis.addEdge(rootNode, diagBasis.getTopVertex(), FeatureEdge.MANDATORY) ;
			
		}
		FeatureModel<String> fmMST = new FeatureModel<String>(diagBasis) ; 
		_LOGGER.debug("fmMST=" + fmMST);
		
	
		
		
		return fmMST ; 
	}

	private FeatureNode<String> determineCommontRootFeatures(Collection<FeatureModelVariable> lfms) {
		
		// TODO some checkings
		for (FeatureModelVariable featureModel : lfms) {
			Set<FeatureNode<String>> roots = KSynthesis.findMyRoots(featureModel.getHierarchy().getDiagram());
			return (FeatureNode<String>) roots.iterator().next();
		}
		return null ; 
	}
	
	
	
	

}

