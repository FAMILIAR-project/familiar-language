package fr.familiar.operations;

import fr.familiar.fm.converter.ExclusionGraph;
import gsd.synthesis.Excludes;
import gsd.synthesis.FeatureNode;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class ExclusionGraphUtil<T> {
	
	private Map<T, FeatureNode<T>> _maps ;  
	
	public ExclusionGraphUtil () {
		_maps = new HashMap<T, FeatureNode<T>>() ; 
	}

	public Collection<Set<T>> cliques(ExclusionGraph<T> excl) {
		Collection<Set<FeatureNode<T>>> fnCliques = findMutexCliques(toUndirectedGraph(excl));
		Collection<Set<T>> cliques = new HashSet<Set<T>>();
		for (Set<FeatureNode<T>> fnClique : fnCliques) {
			Set<T> clique = new HashSet<T>();
			for (FeatureNode<T> featureNode : fnClique) {
				if (!featureNode.isBottom() && !featureNode.isTop())
					clique.add(featureNode.getFeature()); 
			}
			cliques.add(clique); 
		}
		
		return cliques ; 
	}
	
	private UndirectedGraph<FeatureNode<T>, DefaultEdge> toUndirectedGraph(ExclusionGraph<T> excl) {

		UndirectedGraph<FeatureNode<T>, DefaultEdge> rUndirected = 
			 new SimpleGraph<FeatureNode<T>, DefaultEdge>(DefaultEdge.class);
		
		Set<T> vertices = excl.vertexSet() ;
		for (T vertex : vertices) {
			rUndirected.addVertex(mkFeatureNode(vertex));
		}
		
		Set<Excludes<T>> excludes = excl.edgeSet() ;
		for (Excludes<T> exclude : excludes) {
			T ant = exclude.getAntecedent() ; 
			T cons = exclude.getConsequent() ;
			rUndirected.addEdge(mkFeatureNode(ant), mkFeatureNode(cons)) ;
		}
		
		return rUndirected ; 
	}
	
	private FeatureNode<T> mkFeatureNode (T name) {
		if (_maps.containsKey(name))
			return _maps.get(name);
		FeatureNode<T> fn = new FeatureNode<T> (name) ; 
		_maps.put(name, fn) ; 
		return fn ; 
	}
	
	private static <T> Collection<Set<FeatureNode<T>>> findMutexCliques(
			UndirectedGraph<FeatureNode<T>, DefaultEdge> g) {

		BronKerboschCliqueFinder<FeatureNode<T>, DefaultEdge> finder = new BronKerboschCliqueFinder<FeatureNode<T>, DefaultEdge>(
				g);

		Collection<Set<FeatureNode<T>>> cliques = finder.getAllMaximalCliques();
		Iterator<Set<FeatureNode<T>>> iter = cliques.iterator();
		while (iter.hasNext()) {
			if (iter.next().size() < 2)
				iter.remove();
		}
		return cliques;
	}

}
