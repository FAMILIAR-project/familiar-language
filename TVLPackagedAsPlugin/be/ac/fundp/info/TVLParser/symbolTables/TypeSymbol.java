package be.ac.fundp.info.TVLParser.symbolTables;

public interface TypeSymbol {

	public void print();
	
	public boolean isARecordSymbol();
	
	public int getType();
	
	public String getID();
	
	public boolean hasASetExpressionSymbol();
	
	public SetExpressionSymbol getSetExpressionSymbol();
	
}
