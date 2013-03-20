package be.ac.fundp.info.TVLParser.symbolTables;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;

public class ConstantSymbol {
	
	String id, value;
	int type;
	
	public ConstantSymbol(int type, String id, String value) {
		this.type = type;
		this.id = id;
		this.value = value;
	}

	public int getType() {
		return type;
	}

	public String getID() {
		return id;
	}

	public String getValue() {
		return value;
	}

	public void printConstant() {
		String typeName = "";
		switch (this.type) {
		case Expression.INT :typeName = "int"; break;
		case Expression.REAL : typeName = "real"; break;
		case Expression.BOOL : typeName = "bool"; break;
		}
		System.out.println("  "+id+"  "+typeName+"  "+value);
	}
	
}
