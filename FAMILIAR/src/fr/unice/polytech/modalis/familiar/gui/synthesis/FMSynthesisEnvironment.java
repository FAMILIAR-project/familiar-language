package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class FMSynthesisEnvironment extends JPanel implements Observer{

	private InteractiveFMSynthesizer synthesizer;
	private FMViewer fmViewer;
	private BIGViewer bigViewer;
	private ParentSelector parentSelector;
	private ClusterViewer clusterViewer;

	public FMSynthesisEnvironment(InteractiveFMSynthesizer synthesizer) {
		this.synthesizer = synthesizer;
		synthesizer.addObserver(this);

		// Create views
		fmViewer = new FMPanel();
		bigViewer = new BIGPanel();
		parentSelector = new ParentSelector(this);
		clusterViewer = new ClusterViewer();

		// Set layout
		this.setLayout(new GridLayout(1, 1));
		JSplitPane parentClusterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, parentSelector, clusterViewer);
		JSplitPane globalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parentClusterSplitPane, fmViewer);
		this.add(globalSplitPane);
		
		update(synthesizer, null); // Initialize display
	}

	@Override
	public void update(Observable o, Object arg) {
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		parentSelector.updateParents(synthesizer.getParentCandidates());
		Set<Set<String>> similarityClusters = synthesizer.getSimilarityClusters();
		if (similarityClusters != null) {
			clusterViewer.updateClusters(similarityClusters);	
		}
	}

	public void selectParent(String child, String parent) {
		synthesizer.selectParent(child, parent);
	}

	public void ignoreParent(String child, String parent) {
		synthesizer.ignoreParent(child, parent);
	}

}
