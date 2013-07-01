package fr.unice.polytech.modalis.familiar.gui.Difference;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;

public class InteractiveFmDiff {
	
	private FeatureModelVariable fmv1;
	private FeatureModelVariable fmv2;
	private List<FeatureEdge> fm1_diffEdges  = new ArrayList<FeatureEdge>();
	private List<FeatureEdge> fm2_diffEdges  = new ArrayList<FeatureEdge>();
	
	public FeatureModelVariable getFeatureModelVariable1() {
		return fmv1;
	}
	public FeatureModelVariable getFeatureModelVariable2() {
		return fmv2;
	}
	public List<FeatureEdge> getFm1_diffEdges() {
		return fm1_diffEdges;
	}
	public List<FeatureEdge> getFm2_diffEdges() {
		return fm2_diffEdges;
	}
	
	
}
