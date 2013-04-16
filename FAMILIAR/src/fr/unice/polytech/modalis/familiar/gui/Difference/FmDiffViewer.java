package fr.unice.polytech.modalis.familiar.gui.Difference;

import gsd.synthesis.FeatureEdge;

import java.util.List;

import javax.swing.JPanel;

public abstract class FmDiffViewer extends JPanel {

	public abstract void commentFM(List<FeatureEdge> fm_diffEdges);

}
