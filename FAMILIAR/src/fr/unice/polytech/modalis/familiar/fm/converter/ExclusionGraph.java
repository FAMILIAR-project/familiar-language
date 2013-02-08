package fr.unice.polytech.modalis.familiar.fm.converter;

import gsd.synthesis.Excludes;

import java.util.Set;

import org.jgrapht.EdgeFactory;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.Subgraph;

public class ExclusionGraph<T> extends SimpleGraph<T, Excludes<T>> { // ImplicationGraph<T>
																		// {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExclusionGraph(EdgeFactory<T, Excludes<T>> edgeFactory) {
		super(edgeFactory);
	}

	public ExclusionGraph() {
		this(new EdgeFactory<T, Excludes<T>>() {
			public Excludes<T> createEdge(T source, T target) {
				return new Excludes<T>(source, target);
			}
		});
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		Set<Excludes<T>> edges = edgeSet();
		for (Excludes<T> excludes : edges) {
			sb.append(excludes.toString() + "\n");
		}
		return sb.toString();
	}

	/**
	 * remove all edges that involve "p" and another potential child-feature 
	 * @param p
	 * @param impl
	 */
	public ExclusionGraph<T> subgraph(String p, Set<T> children) {
		Subgraph<T, Excludes<T>, ExclusionGraph<T>> subE = 
        		new Subgraph<T, Excludes<T>, ExclusionGraph<T>>(this, children);
        Set<T> vertices = subE.vertexSet() ; 
       
       
        ExclusionGraph<T> resExg = new ExclusionGraph<T>();
        for (T v : vertices) {
        	 resExg.addVertex(v);
		}
        
        Set<Excludes<T>> eds = subE.edgeSet() ;
        for (Excludes<T> ed : eds) {
			T aT = ed.getAntecedent() ; 
			T cT = ed.getConsequent() ; 
			resExg.addEdge(aT, cT);
		}
        
       
        
		return resExg ; 
		
	}

}
