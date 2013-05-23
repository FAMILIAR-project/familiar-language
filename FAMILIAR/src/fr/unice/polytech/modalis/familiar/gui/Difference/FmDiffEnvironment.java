package fr.unice.polytech.modalis.familiar.gui.Difference;

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
