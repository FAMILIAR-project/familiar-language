package be.ac.fundp.info.TVLParser.SyntaxTree;

public interface ModelItem {
	
	public boolean isAFeature();
	
	public boolean isAconstant();
	
	public boolean isAType();
	
	public void accept(Visitor v);
	
}
