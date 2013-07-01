package be.ac.fundp.info.TVLParser.SyntaxTree;

public interface Type extends ModelItem {

	public int getType();
	
	public String getID();
	
	public boolean isARecord();
	
	public boolean isAFeature();
	
	public boolean isAconstant();
	
	public boolean isAType();
}
