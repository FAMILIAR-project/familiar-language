package fr.unice.polytech.modalis.familiar.gui.synthesis;


import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.SimpleEdge;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

public class FMPanel extends FMViewer{
	
	 private ListenableUndirectedGraph<FeatureNode<String>, DefaultEdge>  fm_AnalysisGraph; 
	 private JGraphModelAdapter fm_jgAdapter;
	 protected JGraph fm_Graph;
	 private  static  List<FeatureNode> existingVertexs = new ArrayList<FeatureNode>();
	 private static final Dimension DEFAULT_SIZE = new Dimension( 320, 320 );
	 private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
	 private FeatureModelVariable lastFM;
	 
	public FMPanel() {
		// create a JGraphT FM
		   this.fm_AnalysisGraph = new ListenableUndirectedGraph( DefaultEdge.class );
		// create a visualization using JGraph, via an adapter
	       this.fm_jgAdapter = new JGraphModelAdapter( this.fm_AnalysisGraph );
	       this.fm_Graph = new JGraph( fm_jgAdapter );
	   	// Set layout
	       
	        this.setLayout(new GridLayout(1, 1));
			this.add(new JScrollPane(this.fm_Graph));
	}
	@Override
	public void updateFM(FeatureModelVariable fmv) {
	    boolean existe = false;
	    FeatureGraph<String> graph = fmv.getFm().getDiagram();
	   
	    
	    if(lastFM == null){
	    	
	    	for (FeatureEdge edge : graph.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY) {	
			String debut = graph.getSource(edge).toString();
			String fin = graph.getTarget(edge).toString();
			FeatureNode source = seekVertex(debut, graph, this.fm_AnalysisGraph);
		    FeatureNode destination = seekVertex(fin, graph,this.fm_AnalysisGraph);
		    this.fm_AnalysisGraph.addEdge(source, destination);
			}
	    	}
	    }	
			
	    else
	    {
	    	FeatureGraph<String> last_graph = lastFM.getFm().getDiagram();
	    	for (FeatureEdge e : graph.edges()) {
	    		if (! last_graph.edges().contains(e)){
	    			String debut = graph.getSource(e).toString();
	    			String fin = graph.getTarget(e).toString();
	    			FeatureNode source = seekVertex(debut, graph, this.fm_AnalysisGraph);
	    			FeatureNode destination = seekVertex(fin, graph,this.fm_AnalysisGraph);
	    			this.fm_AnalysisGraph.addEdge(source, destination);
	    		}
		}	
	    	
	    	for (DefaultEdge edge : fm_AnalysisGraph.edgeSet()) {
	    		FeatureNode<String> lastSource = fm_AnalysisGraph.getEdgeSource(edge);
	    		FeatureNode<String> lastTarget = fm_AnalysisGraph.getEdgeTarget(edge);
	    		FeatureNode<String> sourceNode = graph.findVertex(lastSource.getFeature());
	    		FeatureNode<String> targetNode = graph.findVertex(lastTarget.getFeature());
	    		if (!graph.containsEdge(sourceNode, targetNode, FeatureEdge.HIERARCHY)) {
	    			fm_AnalysisGraph.removeEdge(lastSource, lastTarget);
	    		}
	    	}
//	    	for (FeatureEdge e : last_graph.edges()) {
//	    		if (! graph.edges().contains(e)){
//	    			String debut = last_graph.getSource(e).toString();
//	    			String fin = last_graph.getTarget(e).toString();
//	    			System.out.println(debut + " " + fin);
//	    			FeatureNode source = seekVertex(debut, last_graph, this.fm_AnalysisGraph);
//	    			FeatureNode destination = seekVertex(fin, last_graph,this.fm_AnalysisGraph);
//	    			this.fm_AnalysisGraph.removeEdge(source, destination);
//	    		}
//	    	}
	    }	
		
	    	lastFM = fmv;	
	}
		 static  FeatureNode seekVertex(String v_name, FeatureGraph graph, ListenableUndirectedGraph fm_AnalysisGraph) {
				
			 boolean existe = false;
			 FeatureNode newNode = null;
			 
			 for (FeatureNode n : existingVertexs){
					if (n.toString().equals(v_name) == true){
						existe= true;
						 newNode = n;
						break;
						}
				}
					if(existe == false)
					{
					FeatureNode v = new FeatureNode (v_name);
					fm_AnalysisGraph.addVertex(v);
					existingVertexs.add(v);
					 newNode = v;
					}
					return newNode;
					
				}
		 private void adjustDisplaySettings( JGraph jg ) {
		       jg.setPreferredSize( DEFAULT_SIZE );
		       Color  c        = DEFAULT_BG_COLOR;
		       String colorStr = null;
		       if( colorStr != null ) {
		           c = Color.decode( colorStr );
		       }
		       jg.setBackground( c );
		   }

		   private void positionVertexAt( Object vertex, int x, int y ) {
		       DefaultGraphCell cell = fm_jgAdapter.getVertexCell( vertex );
		       Map              attr = cell.getAttributes(  );
		       Rectangle        b    = (Rectangle) GraphConstants.getBounds( attr );

		       GraphConstants.setBounds( attr, new Rectangle( x, y, b.width, b.height ) );
		       Map cellAttr = new HashMap(  );
		       cellAttr.put( cell, attr );
		       fm_jgAdapter.edit(cellAttr, null, null, null);
		   }
		 
	
	
}
