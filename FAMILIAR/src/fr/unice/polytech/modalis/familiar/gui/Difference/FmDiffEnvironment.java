package fr.unice.polytech.modalis.familiar.gui.Difference;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;



import fr.unice.polytech.modalis.familiar.gui.FamiliarConsole;
import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.gui.synthesis.BIGViewer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.CliqueViewer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.ClusterViewer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.FMViewer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.InteractiveFMSynthesizer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.JGraphXBIGViewer;
import fr.unice.polytech.modalis.familiar.gui.Difference.JGraphXFMViewer;
import fr.unice.polytech.modalis.familiar.gui.synthesis.ParentSelector;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureNode;

public class FmDiffEnvironment extends JPanel {

	private InteractiveFmDiff diff;
	private FmDiffViewer fmViewer1;
	private FmDiffViewer fmViewer2;
	
	
	public FmDiffEnvironment(InteractiveFMSynthesizer synthesizer) {
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
