package be.ac.fundp.info.TVLParser.SyntaxTree;

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

/**
 * This interface is implemented by each class which could be an expression. See
 * the
 * grammar on the web site and the .cup for more details.
 */
public interface Expression {

	public static final int UNKNOWN = 0;
	public static final int INT = 1;
	public static final int REAL = 2;
	public static final int BOOL = 3;
	public static final int ENUM = 4;
	public static final int STRUCT = 5;
	public static final int STRUCT_FIELD = 6;
	public static final int USER_CREATED = 7;

	/**
	 * Return the type of the expression.
	 * 
	 * @return
	 *         An int corresponding to the type of the expression. See the
	 *         constants
	 *         above for more details.
	 * 
	 * @throws IllegalExpressionException
	 * @throws SymbolNotFoundException
	 * @throws AmbiguousReferenceException
	 * @throws SharedFeatureUsingParentConstructorException
	 * @throws BadTypeUseException
	 * @throws SharedFeatureUsingParentSelectorException
	 * @throws ChildrenFeatureNotFoundException
	 * @throws IDEnumValuesConflictException
	 * @throws NumberFormatException
	 * @throws SetExpressionMemberOutOfBoundException
	 * @throws SetExpressionMemberViolatingAttributeTypeException
	 * @throws ExpressionTypeViolatingAttributeTypeException
	 * @throws ExpressionOutOfBoundException
	 */
	public int getType() throws IllegalExpressionException, SymbolNotFoundException, AmbiguousReferenceException,
			SharedFeatureUsingParentConstructorException, BadTypeUseException, SharedFeatureUsingParentSelectorException,
			ChildrenFeatureNotFoundException, IDEnumValuesConflictException, NumberFormatException, SetExpressionMemberOutOfBoundException,
			SetExpressionMemberViolatingAttributeTypeException, ExpressionTypeViolatingAttributeTypeException,
			ExpressionOutOfBoundException;

	/**
	 * Return a string which is the textual representation of the expression.
	 * 
	 * @return
	 *         A string which is the textual representation of the expression.
	 */
	public String toString();

	/**
	 * Transform the expression into its normal form. For more information
	 * about the normalization of an expression, see : "Introducing TVL,
	 * a comprehensive Text-based Feature Modeling Language and its Semantics".
	 * 
	 * @return
	 *         A new expression which is the normal form of this expression.
	 */
	public Expression toNormalForm();
	
	public void accept(Visitor v) throws Exception;

}
