package fr.unice.polytech.modalis.familiar.gui.synthesis;

import javax.swing.JPanel;

import gsd.graph.ImplicationGraph;

public abstract class BIGViewer extends JPanel{

	public abstract void updateBIG(ImplicationGraph<String> big);
	
}
