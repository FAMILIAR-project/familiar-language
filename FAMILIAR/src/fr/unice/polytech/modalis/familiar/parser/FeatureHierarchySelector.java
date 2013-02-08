package fr.unice.polytech.modalis.familiar.parser;

import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.graph.TransitiveReduction;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.EdgeContainer;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

import org.apache.commons.collections15.MultiMap;
import org.apache.commons.collections15.multimap.MultiHashMap;

public class FeatureHierarchySelector<T extends Comparable<T>> {

	private BDD _formula;
	
	private Set<T> _support;
	private BDDFactory _factory;
	
	private final BDDBuilder<T> _builder;
	private final FeatureGraphFactory<T> _fgf;
	
	public FeatureHierarchySelector(BDDBuilder<T> builder) {
		_builder = builder;
		_fgf = builder.getFeatureGraphFactory();
	}

	public FeatureModel<T> build(Formula<T> f) {
		_formula = f.getBDD();
		_support = f.getDomain();
		_factory = _formula.getFactory();

		FeatureGraph<T> g = _fgf.mkTop();
		FeatureModel<T> fm = new FeatureModel<T>(g);

		
		ImplicationGraph<T> impl = IGBuilder.build(f, _builder);
		mkHierarchyAndGroups(g, impl);
		TransitiveReduction.INSTANCE.reduce(g);
		mkSyntheticRoot(g);

		return fm;
	}
	
	public void mkSyntheticRoot(FeatureGraph<T> g) {

		Set<FeatureNode<T>> roots = FeatureHierarchySelectorMST.findRoots(g);
		FeatureNode<T> synth = g.getTopVertex();

		for (FeatureNode<T> v : roots) {
			g.addEdge(v, synth, FeatureEdge.HIERARCHY);
			g.addEdge(v, synth, FeatureEdge.MANDATORY);
		}
	}
	
	

	public void mkHierarchyAndGroups(FeatureGraph<T> g, ImplicationGraph<T> impl) {

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
	
	public static <T extends Comparable<T>> MultiMap<FeatureNode<T>, FeatureEdge> findCliques(
			FeatureGraph<T> g) {
		MultiMap<FeatureNode<T>, FeatureEdge> result = new MultiHashMap<FeatureNode<T>, FeatureEdge>();

		for (FeatureEdge e : g.selectEdges(FeatureEdge.HIERARCHY)) {
			FeatureNode<T> source = g.getSource(e);
			if (source.features().size() > 1)
				result.put(source, e);
		}

		return result;
	}

	public void contract(FeatureModel<String> fmImplHierarchy) {
		
		
		FeatureGraph<String> fg = fmImplHierarchy.getDiagram() ; 
		
		MultiMap<FeatureNode<String>, FeatureEdge> ambigs = FeatureHierarchySelector.findCliques(fmImplHierarchy.getDiagram());
		Set<FeatureNode<String>> ftNodesAmbig = ambigs.keySet();
	    
		for (FeatureNode<String> featureNode : ftNodesAmbig) {
			
			Set<String> fts = featureNode.features() ;
						
			
			String ftParent = selectParent(fts);
			FeatureNode<String> fnParent = new FeatureNode<String>(ftParent);
						
			fg.replaceVertex(featureNode, fnParent);
			
			
			Set<String> children = new HashSet<String>(fts); 
			children.remove(ftParent);
			
			for (String ch : children) {
				
				FeatureNode<String> fnChild = new FeatureNode<String>(ch);				

				fg.addVertex(fnChild);
			    fg.addEdge(fnChild, fnParent, FeatureEdge.HIERARCHY);
				fg.addEdge(fnChild, fnParent, FeatureEdge.MANDATORY);								
				
			}		
			
		}
		
	}
	
	/**
	 * We can implement some heuristics here!
	 * @param fts
	 * @return
	 */
	private static String selectParent(Set<String> fts) {
		assert (fts.size() > 1);
		return fts.iterator().next() ; 
	}

	public void contractSameLevel(FeatureModel<String> fmImplHierarchy) {
		
		FeatureGraph<String> fg = fmImplHierarchy.getDiagram() ; 
		
		MultiMap<FeatureNode<String>, FeatureEdge> ambigs = FeatureHierarchySelector.findCliques(fmImplHierarchy.getDiagram());
		Set<FeatureNode<String>> ftNodesAmbig = ambigs.keySet();
		for (FeatureNode<String> featureNode : ftNodesAmbig) {
			contractVertex(fg, featureNode);
		}
		

		
	}

	/**
	 * contract a compound vertex (A&B&C) => A, B, C (fts)
	 * @param fg
	 * @param v 
	 * 
	 */
	private void contractVertex(FeatureGraph<String> fg, FeatureNode<String> v) {
		Set<String> fts = v.features() ;
		assert(fts.size() > 1);
		
		Set<FeatureNode<String>> newVertices = new HashSet<FeatureNode<String>>();
		for (String ft : fts) {
			FeatureNode<String> newVertice = new FeatureNode<String>(ft);
			newVertices.add(newVertice);		
		}
		
		
		Collection<EdgeContainer<String>> edges = new HashSet<EdgeContainer<String>> ();
		
		for (FeatureNode<String> w : newVertices) {
				
			for (FeatureEdge e : fg.incomingEdges (v)) {
				edges.add (new EdgeContainer<String> (fg.getSources (e), w,	e.getType ()));
			}

			for (FeatureEdge e : fg.outgoingEdges (v)) {
				Set<FeatureNode<String>> sources = fg.getSources (e);
				sources.remove (v);
				sources.add (w);
				edges.add (new EdgeContainer<String> (sources, fg.getTarget (e),
					e.getType ()));
			}
		}
		Iterator<FeatureNode<String>> itNewVertices = newVertices.iterator() ; 
		fg.replaceVertex(v, itNewVertices.next());
		while (itNewVertices.hasNext()) {
			fg.addVertex(itNewVertices.next());
		}
		for (EdgeContainer<String> e : edges) {
			fg.addEdge (e.getSources (), e.getTarget (), e.getType ());
		}
		
		
		 	
		
		
	}

	
	
	

}
