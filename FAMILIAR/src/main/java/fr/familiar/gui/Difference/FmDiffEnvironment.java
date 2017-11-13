/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.gui.Difference;

import gsd.synthesis.FeatureEdge;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class FmDiffEnvironment extends JPanel {

	private InteractiveFmDiff diff;
	private FmDiffViewer fmViewer1;
	private FmDiffViewer fmViewer2;
	
	
	public FmDiffEnvironment() {
		this.diff = diff;
	
		// Create views
		fmViewer1 = new JGraphXFMViewer();
		fmViewer2 = new JGraphXFMViewer();
		
		// Set layout
		this.setLayout(new GridLayout(1, 1));
		
		JSplitPane fmDiffSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fmViewer1, fmViewer2); 
		
		this.add(fmDiffSplitPane);

		
	}
	public void commentFM() {
		List<FeatureEdge> fm1_diffEdges = diff.getFm1_diffEdges();
		List<FeatureEdge> fm2_diffEdges = diff.getFm2_diffEdges();
		fmViewer1.commentFM(fm1_diffEdges);
		fmViewer2.commentFM(fm2_diffEdges);
		
	}
	
}
