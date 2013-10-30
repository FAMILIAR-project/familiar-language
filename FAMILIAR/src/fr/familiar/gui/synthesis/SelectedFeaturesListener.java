package fr.familiar.gui.synthesis;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class SelectedFeaturesListener implements TreeSelectionListener {


	private FMSynthesisEnvironment environment;
	
	
	
	public SelectedFeaturesListener(FMSynthesisEnvironment environment) {
		this.environment = environment;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		List<String> selectedFeatures = new ArrayList<String>();
		List<String> unselectedFeatures = new ArrayList<String>();
		for (TreePath path : e.getPaths()) {
			if (e.isAddedPath(path)) {
				selectedFeatures.add(path.getLastPathComponent().toString());	
			} else {
				unselectedFeatures.add(path.getLastPathComponent().toString());
			}
		}
		environment.updateSelectedFeatures(selectedFeatures, unselectedFeatures);
	}

}
