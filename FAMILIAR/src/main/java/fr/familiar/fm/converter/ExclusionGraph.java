/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.fm.converter;

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
