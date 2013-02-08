package be.ac.fundp.info.TVLParser.SyntaxTree;

import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;

public class CountAggExpression implements Expression {

	int childrenAttributeID;
	FeatureSymbol currentFeatureSymbol;

	FeaturesSymbolTable featuresSymbolTable;

	public CountAggExpression(int childrenAttributeID, FeaturesSymbolTable featuresSymbolTable) {
		this.childrenAttributeID = childrenAttributeID;
		this.featuresSymbolTable = featuresSymbolTable;
	}

	/**
	 * @return the expressionList
	 */

	/**
	 * @return the childrenAttributeID
	 */
	public int getChildrenAttributeID() {
		return childrenAttributeID;
	}

	public String toString() {
		if (this.childrenAttributeID == ChildrenAttributeID.SELECTED_CHILDREN) {
			return "count( selectedchildren )";
		} else
			return "count( children )";
	}

	@Override
	public int getType() throws AmbiguousReferenceException, SymbolNotFoundException, ChildrenFeatureNotFoundException,
			IllegalExpressionException {
		currentFeatureSymbol = featuresSymbolTable.getFeatureSymbol(featuresSymbolTable.getStack().toPath());
		if (currentFeatureSymbol.hasChildrenFeatures())
			return Expression.INT;
		else
			throw new IllegalExpressionException("Type error : the expression " + this.toString() + " is not valid. The feature "
					+ currentFeatureSymbol.getID() + " has no children features.");
	}

	public Expression toNormalForm() {
		if (this.childrenAttributeID == ChildrenAttributeID.SELECTED_CHILDREN) {
			Object[] currentFeatureChildrenArray = this.currentFeatureSymbol.getChildrenFeatures().keySet().toArray();
			FeatureSymbol childFeature = this.currentFeatureSymbol.getChildrenFeature(currentFeatureChildrenArray[0].toString());
			String childFeatureNonAmbiguousPath = featuresSymbolTable.getNonAmbiguousPath(childFeature);
			ExpressionList expressionList = new ExpressionList(new QuestExpression(
					new LongIDExpression(childFeatureNonAmbiguousPath, null), new IntExpression("1"), new IntExpression("0")));
			int i = 1;
			while (i <= currentFeatureChildrenArray.length - 1) {
				childFeature = this.currentFeatureSymbol.getChildrenFeature(currentFeatureChildrenArray[0].toString());
				childFeatureNonAmbiguousPath = featuresSymbolTable.getNonAmbiguousPath(childFeature);
				expressionList = new ExpressionList(new QuestExpression(new LongIDExpression(childFeatureNonAmbiguousPath, null),
						new IntExpression("1"), new IntExpression("0")), expressionList);
				i++;
			}
			return new SumAggExpression(expressionList);
		} else {
			return new IntExpression(String.valueOf(this.currentFeatureSymbol.getChildrenFeatures().size()));
		}
	}

	@Override
	public void accept(Visitor v) throws Exception {
		v.visit(this);
	}

}
