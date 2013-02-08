package fr.unice.polytech.modalis.familiar.operations;

import gsd.graph.BasicGraph;
import gsd.graph.GraphvizGraph;

public class MyGraphvizGraph<V, E> extends
		GraphvizGraph<V, E> {

	public MyGraphvizGraph(BasicGraph<V, E> graph) {
		// TODO some checking (getSource)
		super(graph);
		
	}
	
	

}
