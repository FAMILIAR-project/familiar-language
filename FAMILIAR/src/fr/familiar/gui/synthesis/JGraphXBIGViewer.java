package fr.familiar.gui.synthesis;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JScrollPane;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;

import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import gsd.graph.SimpleEdge;

public class JGraphXBIGViewer extends BIGViewer {

	private final static String VERTEX_DEFAULT_COLOR = "lightblue";

	private mxGraph graph;
	private HashMap<String, mxCell> vertices;
	private mxIGraphLayout layout;

	public JGraphXBIGViewer() {
		// Create graph
		graph = new mxGraph();
		layout = new mxCircleLayout(graph);
		
		mxGraphComponent graphComponent = new mxGraphComponent(graph);
		graphComponent.setEnabled(false);
		// Set layout
		this.setPreferredSize(new Dimension(100, 100));
		this.setLayout(new GridLayout(1, 1));
		this.add(new JScrollPane(graphComponent));
	}

	@Override
	public void updateBIG(WeightedImplicationGraph<String> big) {
		vertices = new HashMap<String, mxCell>();
		graph.getModel().beginUpdate();
		graph.selectAll();
		graph.removeCells();

		for (SimpleEdge edge : big.edges()) {
			String source = big.getSource(edge);
			String target = big.getTarget(edge);
			mxCell sourceCell = seekVertex(source);
			mxCell targetCell = seekVertex(target);
			String style = "startArrow=" + mxConstants.NONE +  ";endArrow=" + mxConstants.ARROW_CLASSIC;
			graph.insertEdge(graph.getDefaultParent(), null, null, sourceCell, targetCell, style);	
		}

		graph.getModel().endUpdate();
		layout.execute(graph.getDefaultParent());
		this.revalidate();
	}

	private mxCell seekVertex(String vertex) {
		mxCell cell = vertices.get(vertex);
		if (cell == null) {
			String style = mxConstants.STYLE_FILLCOLOR + "=" + VERTEX_DEFAULT_COLOR;
			cell = (mxCell) graph.insertVertex(graph.getDefaultParent(), null, vertex, 0, 0, vertex.length() * 10, 20, style);
			vertices.put(vertex, cell);
		}
		return cell;
	}


}
