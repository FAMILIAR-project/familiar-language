package fr.familiar.gui.synthesis;

import javax.swing.JPanel;

import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;

public abstract class BIGViewer extends JPanel{

	public abstract void updateBIG(WeightedImplicationGraph<String> big);
	
}
