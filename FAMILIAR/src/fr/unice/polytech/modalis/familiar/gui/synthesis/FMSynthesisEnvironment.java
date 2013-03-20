package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class FMSynthesisEnvironment extends JPanel implements Observer{

	private InteractiveFMSynthesizer synthesizer;
	private FMViewer fmViewer;
	private ParentSelector parentSelector;

	public FMSynthesisEnvironment(InteractiveFMSynthesizer synthesizer) {
		this.synthesizer = synthesizer;
		synthesizer.addObserver(this);

		// Create views
		fmViewer = new FMExplorer();
		parentSelector = new ParentSelector(this);

		// Set layout
		this.setLayout(new GridLayout(1, 1));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parentSelector, fmViewer);
		this.add(splitPane);
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		parentSelector.updateParents(synthesizer.getParentCandidates());
		
	}

	@Override
	public void update(Observable o, Object arg) {
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		parentSelector.updateParents(synthesizer.getParentCandidates());
	}

	public void selectParent(String child, String parent) {
		synthesizer.selectParent(child, parent);
	}

	public void ignoreParent(String child, String parent) {
		synthesizer.ignoreParent(child, parent);
	}

}
