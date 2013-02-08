package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class HierarchicalFeatures {
	
	Vector<Feature> features;
	
	public HierarchicalFeatures(Feature feature) {
		this.features = new Vector<Feature>();
		this.features.add(feature);
	}
	
	public HierarchicalFeatures(Feature feature, HierarchicalFeatures hierarchicalFeatures) {
		this.features = hierarchicalFeatures.getFeatures();
		this.features.add(feature);
	}
	
	public Vector<Feature> getFeatures() {
		return this.features;
	}
}
