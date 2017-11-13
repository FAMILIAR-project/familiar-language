/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

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
