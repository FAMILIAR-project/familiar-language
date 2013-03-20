package fr.unice.polytech.modalis.familiar.gui.synthesis;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgraph.graph.DefaultGraphCell;
import org.jgraph.graph.GraphConstants;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

public class FMPanel extends FMViewer{
	
	 private ListenableUndirectedGraph  fm_AnalysisGraph; 
	 private JGraphModelAdapter fm_jgAdapter;
	 protected JGraph fm_Graph;
	 private  static  List<FeatureNode> existingVertexs = new ArrayList<FeatureNode>();
	 private static final Dimension DEFAULT_SIZE = new Dimension( 320, 320 );
	 private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
	
	 
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
		
		FeatureGraph<String> graph = fmv.getFm().getDiagram();
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
