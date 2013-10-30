package fr.familiar.gui.synthesis;

import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import fr.familiar.variable.FeatureModelVariable;

public abstract class FMViewer extends JPanel {
	
	
	public abstract void updateFM(FeatureModelVariable fmv);

	public abstract void updateSelectedClusters(List<Set<String>> selectedClusters, List<Set<String>> unselectedClusters);
}
