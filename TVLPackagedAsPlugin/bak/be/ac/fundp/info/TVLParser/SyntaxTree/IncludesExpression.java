package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;

public class IncludesExpression implements BooleanExpression {
	
String longID1, longID2;
String normalFormLongID1;
String normalFormLongID2;
FeaturesSymbolTable featuresSymbolTable;
	
	public IncludesExpression(String longID1, String longID2, FeaturesSymbolTable featuresSymbolTable) {
		this.longID1 = longID1;
		this.longID2 = longID2;
		this.featuresSymbolTable = featuresSymbolTable;
	}

	/**
	 * @return the featureID1
	 */
	public String getLongID1() {
		return this.longID1;
	}

	/**
	 * @return the featureID2
	 */
	public String getLongID2() {
		return this.longID2;
	}

	/**
	 * @return the featureIDPath1
	 */
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.longID1);
		sb.append(" requires ");
		sb.append(this.longID2);
		return sb.toString();
	}

	public int getType() throws AmbiguousReferenceException, IllegalExpressionException, SymbolNotFoundException, ChildrenFeatureNotFoundException, SharedFeatureUsingParentSelectorException {
		if (this.featuresSymbolTable.containsSymbol(longID1)) {
			this.normalFormLongID1 = (String) this.featuresSymbolTable.getSymbol(longID1).get(1);
			if (this.featuresSymbolTable.containsSymbol(longID2)) {
				this.normalFormLongID2 = (String) this.featuresSymbolTable.getSymbol(longID2).get(1);
				return Expression.BOOL;
			}
			else {
				throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The right paramater ( "+longID2+" ) is not a valid feature ID. In an includes expression, each parameter must be a valid feature ID.");
			}
		}
		else {
			throw new IllegalExpressionException("Type error : the expression "+this.toString()+" is invalid. The let paramater ( "+longID1+" ) is not a valid feature ID. In an includes expression, each parameter must be a valid feature ID.");
		}
	}
	
	public Expression toNormalForm() {
		return new IncludesExpression(this.normalFormLongID1, this.normalFormLongID2, null);
	}
	
	public BooleanExpression toSimplifiedForm() {
		return new OrExpression(new NotExpression(new LongIDExpression(this.longID1, this.featuresSymbolTable)), new LongIDExpression(this.longID2, this.featuresSymbolTable));
	}
	
	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression removeArrows() {
		return this;
	}
	
	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression distributeDisjunctions() {
		return this;
	}

	/** This method is normally never used because the toSimplifiedForm() method removes each XorAggExpression from the diagram.
	 * 
	 * @return
	 */
	public BooleanExpression distributeNegations() {
		return this;
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}
}
