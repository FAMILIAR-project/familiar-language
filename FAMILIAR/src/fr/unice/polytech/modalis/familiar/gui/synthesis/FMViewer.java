package fr.unice.polytech.modalis.familiar.gui.synthesis;

import javax.swing.JPanel;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public abstract class FMViewer extends JPanel {
	
	
	public abstract void updateFM(FeatureModelVariable fmv);
}
