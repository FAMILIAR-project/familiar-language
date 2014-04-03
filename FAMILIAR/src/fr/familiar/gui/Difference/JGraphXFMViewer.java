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

