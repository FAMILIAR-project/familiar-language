package be.ac.fundp.info.TVLParser.SyntaxTree;

public interface FeatureBodyItem {
	
	public boolean isAData();
	
	public boolean isAnAttribute();
	
	public boolean isAConstraint();
	
	public boolean isAFeatureGroup();
	
	public void accept(Visitor v);
	
}
