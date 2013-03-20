package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

import org.jgraph.JGraph;
import org.jgrapht.ext.JGraphModelAdapter;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.ListenableDirectedWeightedGraph;


import gsd.graph.ImplicationGraph;
import gsd.synthesis.FeatureNode;

public class BIGPanel extends BIGViewer {
	
	 private ListenableDirectedWeightedGraph  big_AnalysisGraph; 
	 private JGraphModelAdapter big;
	 protected JGraph big_Graph;
	 private  static  List<FeatureNode> existingVertexs = new ArrayList<FeatureNode>();
	 private static final Dimension DEFAULT_SIZE = new Dimension( 320, 320 );
	 private static final Color     DEFAULT_BG_COLOR = Color.decode( "#FAFBFF" );
	 
	 public BIGPanel() {
			
		}
	@Override
	public void updateBIG(ImplicationGraph<String> big) {

	}

}
