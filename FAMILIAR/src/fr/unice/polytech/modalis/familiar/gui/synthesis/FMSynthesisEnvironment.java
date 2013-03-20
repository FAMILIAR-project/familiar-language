package fr.unice.polytech.modalis.familiar.gui.synthesis;

import gsd.graph.ImplicationGraph;

import java.awt.GridLayout;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		fmViewer = new FMPanel();
		parentSelector = new ParentSelector(this);

		// Set layout
		this.setLayout(new GridLayout(1, 1));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parentSelector, fmViewer);
		this.add(splitPane);
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		parentSelector.updateParents(convertBIGToParentMap(synthesizer.getImplicationGraph()));
		
	}

	@Override
	public void update(Observable o, Object arg) {
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		parentSelector.updateParents(convertBIGToParentMap(synthesizer.getImplicationGraph()));
	}

	private List<Entry<String, List<String>>> convertBIGToParentMap(ImplicationGraph<String> big) {
		List<Entry<String, List<String>>> parents = new ArrayList<Map.Entry<String,List<String>>>();
		for (String feature : big.vertices()) {
			List<String> parentList = new ArrayList<String>(big.parents(feature));
			Entry<String, List<String>> parentEntry = new AbstractMap.SimpleEntry<String, List<String>>(feature, parentList);
			parents.add(parentEntry);
		}
		return parents;
	}

	public void selectParent(String child, String parent) {
		synthesizer.selectParent(child, parent);
	}

	public void ignoreParent(String child, String parent) {
		synthesizer.ignoreParent(child, parent);
	}

}
