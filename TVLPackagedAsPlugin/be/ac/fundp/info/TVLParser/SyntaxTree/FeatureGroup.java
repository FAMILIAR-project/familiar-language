package be.ac.fundp.info.TVLParser.SyntaxTree;

import java.util.Vector;

public class FeatureGroup implements FeatureBodyItem {
	
	Cardinality cardinality;
	HierarchicalFeatures hierarchicalFeatures;
	
	public FeatureGroup(Cardinality cardinality, HierarchicalFeatures hierarchicalFeatures) {
		this.cardinality = cardinality;
		this.hierarchicalFeatures = hierarchicalFeatures;
	}

	/**
	 * @return the cardinality
	 */
	public Cardinality getCardinality() {
		return cardinality;
	}

	/**
	 * @return the hierarchicalFeatures
	 */
	public HierarchicalFeatures getHierarchicalFeatures() {
		return hierarchicalFeatures;
	}

	@Override
	public boolean isAConstraint() {
		return false;
	}

	@Override
	public boolean isAData() {
		return false;
	}

	@Override
	public boolean isAFeatureGroup() {
		return true;
	}

	@Override
	public boolean isAnAttribute() {
		return false;
	}

	@Override
	public void accept(Visitor v) {
		v.visit(this);
	}
	
	public String toString() {
		String result = " ";
		
		String minGroupCardinality = cardinality.getMin();
		String maxGroupCardinality = cardinality.getMax();
		Vector<Feature> childrenFeatures = hierarchicalFeatures.getFeatures();
		
		int i = 0;
		result = result.concat(" group [ "+minGroupCardinality+".."+maxGroupCardinality+" ] {\n");
		int childrenSize = childrenFeatures.size(); 
		while (i <= childrenSize-2) {
			result = result.concat(childrenFeatures.get(i).toString("  ")+",\n");
			i++;
		}
		result = result.concat(childrenFeatures.get(childrenSize-1).toString("  ")+"\n");
		result = result.concat("}\n");
		
		return result;
	}
}
