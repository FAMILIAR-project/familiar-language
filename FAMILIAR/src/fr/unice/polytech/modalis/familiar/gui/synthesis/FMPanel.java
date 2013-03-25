package fr.unice.polytech.modalis.familiar.gui.synthesis;


import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.ListenableUndirectedGraph;

public class FMPanel extends FMViewer{

	private ListenableUndirectedGraph<FeatureNode<String>, DefaultEdge>  fm_AnalysisGraph; 
	private JGraphModelAdapter<FeatureNode<String>, DefaultEdge> fm_jgAdapter;
	protected JGraph fm_Graph;
	private List<FeatureNode<String>> existingVertexs = new ArrayList<FeatureNode<String>>();
	private static final Dimension DEFAULT_SIZE = new Dimension( 320, 320 );
	private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
	private FeatureModelVariable lastFM;

	public FMPanel() {
		// create a JGraphT FM
		this.fm_AnalysisGraph = new ListenableUndirectedGraph<FeatureNode<String>, DefaultEdge>( DefaultEdge.class );
		
		// create a visualization using JGraph, via an adapter
		this.fm_jgAdapter = new JGraphModelAdapter<FeatureNode<String>, DefaultEdge>( this.fm_AnalysisGraph );
		this.fm_Graph = new JGraph( fm_jgAdapter );
		
		// Set layout
		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(this.fm_Graph));
	}
	
	@Override
	public void updateFM(FeatureModelVariable fmv) {
		FeatureGraph<String> graph = fmv.getFm().getDiagram();

		FeatureGraph<String> last_graph = null;
		if(lastFM != null){
			last_graph = lastFM.getFm().getDiagram();
		}

		// Add new edges
		for (FeatureEdge edge : graph.edges()) {
			if (edge.getType() == FeatureEdge.HIERARCHY && (last_graph == null || ! last_graph.edges().contains(edge))) {	
				FeatureNode<String> source = graph.getSource(edge);
				FeatureNode<String> target = graph.getTarget(edge);
				if (!target.isTop()) {
					FeatureNode<String> sourceVertex = seekVertex(source.getFeature(), graph);
					FeatureNode<String> targetVertex = seekVertex(target.getFeature(), graph);
					this.fm_AnalysisGraph.addEdge(sourceVertex, targetVertex);	
				}
				
			}
		}

		// Remove removed edges
		if (lastFM != null) {
			for (DefaultEdge edge : fm_AnalysisGraph.edgeSet()) {
				FeatureNode<String> lastSource = fm_AnalysisGraph.getEdgeSource(edge);
				FeatureNode<String> lastTarget = fm_AnalysisGraph.getEdgeTarget(edge);
				FeatureNode<String> source = graph.findVertex(lastSource.getFeature());
				FeatureNode<String> target = graph.findVertex(lastTarget.getFeature());
				if (!graph.containsEdge(source, target, FeatureEdge.HIERARCHY)) {
					fm_AnalysisGraph.removeEdge(lastSource, lastTarget);
				}
			}	
		}
		
		lastFM = fmv;	
	}
	private FeatureNode<String> seekVertex(String v_name, FeatureGraph<String> graph) {

		boolean existe = false;
		FeatureNode<String> newNode = null;

		for (FeatureNode<String> n : existingVertexs){
			if (n.getFeature().equals(v_name)){
				existe= true;
				newNode = n;
				break;
			}
		}
		if(existe == false)
		{
			FeatureNode<String> v = new FeatureNode<String> (v_name);
			fm_AnalysisGraph.addVertex(v);
			existingVertexs.add(v);
			newNode = v;
		}
		return newNode;

	}
	
//	private void adjustDisplaySettings( JGraph jg ) {
//		jg.setPreferredSize( DEFAULT_SIZE );
//		Color  c        = DEFAULT_BG_COLOR;
//		String colorStr = null;
//		if( colorStr != null ) {
//			c = Color.decode( colorStr );
//		}
//		jg.setBackground( c );
//	}
//
//	private void positionVertexAt( Object vertex, int x, int y ) {
//		DefaultGraphCell cell = fm_jgAdapter.getVertexCell( vertex );
//		Map              attr = cell.getAttributes(  );
//		Rectangle        b    = (Rectangle) GraphConstants.getBounds( attr );
//
//		GraphConstants.setBounds( attr, new Rectangle( x, y, b.width, b.height ) );
//		Map cellAttr = new HashMap(  );
//		cellAttr.put( cell, attr );
//		fm_jgAdapter.edit(cellAttr, null, null, null);
//	}



}
