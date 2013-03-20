package be.ac.fundp.info.TVLParser.symbolTables;

import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;


public interface SetExpressionSymbol {

	public void print();
	
	public boolean containsExpression(Expression expression) throws SetExpressionMemberOutOfBoundException, SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, NumberFormatException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, IDEnumValuesConflictException;
	
	public boolean containsSetExpression(SetExpression setExpression) throws SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SetExpressionMemberOutOfBoundException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, NumberFormatException, IDEnumValuesConflictException;
	
	public boolean isAnEnumSetExpressionSymbol();
	
	public void setAttributeID(String attributeID);
	
	public SetExpressionSymbol copy();
	
	public boolean containsSetExpressionSymbol(SetExpressionSymbol setExpressionSymbol) throws SetExpressionMemberViolatingAttributeTypeException, IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException, SharedFeatureUsingParentConstructorException, BadTypeUseException, SetExpressionMemberOutOfBoundException, ExpressionTypeViolatingAttributeTypeException, ExpressionOutOfBoundException, SharedFeatureUsingParentSelectorException, ChildrenFeatureNotFoundException, NumberFormatException, IDEnumValuesConflictException;

	public int getType();
}