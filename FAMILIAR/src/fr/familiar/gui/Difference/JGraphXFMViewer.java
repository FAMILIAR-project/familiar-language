package fr.familiar.gui.Difference;
import gsd.synthesis.FeatureEdge;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JScrollPane;

import com.mxgraph.layout.mxCompactTreeLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
public class JGraphXFMViewer extends FmDiffViewer{

	private final static String VERTEX_DEFAULT_COLOR = "lightblue";
	private mxGraph graph;
	private mxIGraphLayout layout;
	private HashMap<String, mxCell> vertices;
	private mxGraphComponent graphComponent;
	
	public JGraphXFMViewer() {
		// Create graph
		graph = new mxGraph();
		//	layout = new mxHierarchicalLayout(graph, SwingConstants.NORTH);
		layout = new mxCompactTreeLayout(graph, false);
		graphComponent = new mxGraphComponent(graph);
		graphComponent.setEnabled(false);
		// Set layout
		this.setPreferredSize(new Dimension(100, 100));
		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(graphComponent));
	}

	@Override
	public void commentFM(List<FeatureEdge> fm_diffEdges) {
		// if different edge is selected print popup
		for(FeatureEdge edge : fm_diffEdges){
			 
			graphComponent.getGraphControl().addMouseListener( new MouseAdapter() {
		          public void mouseReleased(MouseEvent e) {
		            if( e.isPopupTrigger()) {

		            

		           

		            }
		          }

		        });
		      }
		
	}
	
}

