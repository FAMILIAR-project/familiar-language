package fr.familiar.experimental;

import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.graph.TransitiveReduction;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.FeatureType;

import java.util.List;
import java.util.Set;

public class FGBuilderUtils {
	
	public static FGBuilderUtils _INSTANCE = new FGBuilderUtils() ; 
	
	public <T> void mkHierarchyAndGroups(FeatureGraph<T> g, ImplicationGraph<T> impl) {

		List<Set<T>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);

		//Make the hierarchy
		for (Set<T> clique : cliques)
			g.addVertex(new FeatureNode<T>(clique));

		for (T v : impl.vertices())
			if (!g.features().contains(v))
				g.addVertex(new FeatureNode<T>(v));

		
		for (Set<T> clique : cliques) {

			FeatureNode<T> group = g.findVertex(clique.iterator().next());

			for (T u : clique) {
				for (SimpleEdge e : impl.incomingEdges(u).toArray(new SimpleEdge[0])) {
					T source = impl.getSource(e);
					if (!clique.contains(source))
						g.addEdge(g.findVertex(source), group, FeatureEdge.HIERARCHY);
					impl.removeEdge(e);
				}

				for (SimpleEdge e : impl.outgoingEdges(u).toArray(new SimpleEdge[0])) {
					T target = impl.getTarget(e);
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
	
	/**
	 * TODO: remove solitary features
	 * @param impl
	 * @return
	 */
	public <T> ImplicationGraph<T> transitiveReductions(ImplicationGraph<T> impl) {

		FeatureGraph<T> g = (FeatureGraph<T>) FeatureGraphFactory.mkStringFactory().mkTop() ; 
		FGBuilderUtils._INSTANCE.mkHierarchyAndGroups(g, impl);		
		
		TransitiveReduction.INSTANCE.reduce(g);
			
		ImplicationGraph<T> rImpl = new ImplicationGraph<T>();
		Set<FeatureNode<T>> vertices = g.vertices() ;
		for (FeatureNode<T> v : vertices) {
			if (v.getType() != FeatureType.SOLITARY) {				
				Set<T> fts = v.features() ;
				for (T ft : fts) {
					rImpl.addVertex((T) ft);
				}
				_addEdgesSolitary(rImpl, fts);
			}
			else
				rImpl.addVertex(v.getFeature());
		}
		
		Set<FeatureEdge> edges = g.selectEdges(FeatureEdge.HIERARCHY); // g.edges() ; 
		for (FeatureEdge fe : edges) {
			FeatureNode<T> target = g.getTarget(fe) ;
			Set<FeatureNode<T>> sources = g.getSources(fe) ;
			for (FeatureNode<T> source : sources) {
				if (source.getType() == FeatureType.SOLITARY && target.getType() == FeatureType.SOLITARY)
					rImpl.addEdge(source.getFeature(), target.getFeature());
				// else: AND-group, handled previously (see above)
			}			
		}		
		
		return rImpl ; 
		
	}
	
	private <T> void _addEdgesSolitary(ImplicationGraph<T> rImpl, Set<T> fts) {
		for (T ft : fts) {
			for (T ft2 : fts) {
				if (ft != ft2) {
					rImpl.addEdge(ft, ft2);
				}
			}
		}
		
	}

}
