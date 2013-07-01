package be.ac.fundp.info.TVLParser.SyntaxTree;

/**
 * This interface is implemented by all the expressions which are compatible 
 * with the boolean form of the feature models. The transformation of an expression
 * in its boolean form is divided in four phases :
 * 	1) The expression is simplified.
 * 	2) All the implications are simplified and removed.
 * 	3) All the negations are distributed.               
 * 	4) All the disjunctions are distributed.
 * 
 * The steps 2, 3 and 4 transform the expression into CNF.
 *
 */
public interface BooleanExpression extends Expression{
	
	/**
	 * If the booleanExpression is "complex", this method transform it into a simplest booleanExpression.
	 * This is the list of the "complex" booleanExpression (for boolean attributes ):
	 * 		1) expression1 == expression2 
	 * 					becomes 
	 * 		   expression1 <-> expression2
	 * 
	 * 		2) expression1 != expression2
	 * 					becomes
	 * 		   !( expression1 <-> expression2 )
	 * 
	 * 		3) and( expression1, expression2, expression3 )
	 * 					becomes
	 * 		   expression1 && expression2 && expression3
	 * 
	 *  	4) or( expression1, expression2, expression3 )
	 *  				becomes
	 *  	   expression1 || expression2 || expression3
	 *  
	 *  	5) xor( expression1, expression2, expression3 )
	 * 					becomes
	 * 		      ( expression1 && !expression2 && !expression3 )
	 * 		   || ( expression2 && !expression1 && !expression3 )
	 * 		   || ( expression3 && !expression1 && !expression2 )
	 * 
	 * 		6) longIDExpression1 excludes longIDExpression2
	 * 					becomes
	 * 		   !longIDExpression1 || !longIDExpression2
	 * 
	 *  	7) longIDExpression1 requires longIDExpression2
	 *  				becomes
	 *  	   !longIDExpression1 || longIDExpression2
	 *  
	 *  	8) expression1 ? expression2 : expression3
	 *  				becomes
	 *  	   ( expression1 -> expression2 ) && ( !expression1 -> expression3)
	 *  
	 *  For the enum attributes, the simplification of the expression is different.
	 *  Imagine that :
	 *  				enum carColor in { Red, Blue, White };
	 *  				enum truckColor in { Red, Blue, White };
	 *  
	 *	has been converted in :
	 *					bool carColor_Red;
	 *					bool carColor_Blue;
	 *					bool carColor_White;
	 *					bool truckColor_Red;
	 *					bool truckColor_Blue;
	 *					bool truckColor_White;
	 *					( carColor_Red && !carColor_Blue && !carColor_White ) || ( carColor_Blue && !carColor_Red && !carColor_White )  || ( carColor_White && !carColor_Red && !carColor_Blue );
	 *					( truckColor_Red && !truckColor_Blue && !truckColor_White ) || ( truckColor_Blue && !truckColor_Red && !truckColor_White )  || ( truckColor_White && !truckColor_Red && !truckColor_Blue );
	 *
	 *  For example, the booleanExpressions :
	 *  
	 *  	1) carColor == Red;
	 *  				becomes
	 *  	   carColor_Red;
	 *  
	 *  	2) carColor == truckColor;
	 *  				becomes
	 *  	   ( carColor_Red && truckColor_Red ) || ( carColor_Blue && truckColor_Blue ) || ( carColor_White && truckColor_White );
	 *  
	 *  	3) carColor != Red;
	 *  				becomes
	 *  	   !carColor_Red;
	 *  
	 *  	4) carColor != truckColor;
	 *  				becomes
	 *  	   !(( carColor_Red && truckColor_Red ) || ( carColor_Blue && truckColor_Blue ) || ( carColor_White && truckColor_White ));
	 *  
	 *  	5) carColor in { Red, White }
	 *  				becomes
	 *  	   !carColor_Blue
	 *  	
	 *  	6) carColor <= Blue
	 *  				becomes
	 *  	   !carColor_White
	 *  
	 *    	7) carColor <= truckColor
	 *  				becomes
	 *  	      ( truckColor_Blue -> carColor_Red || carColor_Blue )
	 *  	   && ( truckColor_Red -> carColor_Red )
	 *  
	 *  	8) carColor < Blue
	 *  				becomes
	 *  	   !carColor_Blue && !carColor_White
	 *  
	 *    	9) carColor < truckColor
	 *  				becomes
	 *  	      ( truckColor_Red -> false )
	 *  	   && ( truckColor_Blue -> carColor_Red )
	 *  	   && ( truckColor_White -> carColor_Red || carColor_Blue )
	 *  
	 *  	10) carColor >= Blue
	 *  				becomes
	 *  	   ! carColor_Red
	 *  
	 *    	11) carColor >= truckColor
	 *  				becomes
	 *  	       ( truckColor_Blue -> carColor_Blue || carColor_White )
	 *  	    && ( truckColor_White -> carColor_White )
	 *  
	 *  	12) carColor > Blue
	 *  				becomes
	 *  	   !carColor_Red && !carColor_Blue
	 *  
	 *    	13) carColor > truckColor
	 *  				becomes
	 *  		   ( truckColor_Red -> !carColor_Red )
	 *  	    && ( truckColor_Blue -> !carColor_Red && !carColor_Blue )
	 *  	    && ( truckColor_White -> false )
	 * 
	 * @return
	 * 		Return a simplest booleanExpression of this booleanExpression.
	 */
	public BooleanExpression toSimplifiedForm();
	
	/**
	 * Remove all the "arrows" that the booleanExpression may contain :
	 *  - a -> b becomes !a || b
	 *  - b <- a becomes !a || b
	 *  - a <-> b becomes ( !a || b ) && ( !b || a )
	 * @return
	 * 		The booleanExpression without arrows.
	 */
	public BooleanExpression removeArrows();
	
	/**
	 * Distribute all the negations that the booleanExpression may contain ( Morgan's law ):
	 * - !( a || b ) becomes !( a ) && !( b )
	 * - !( a && b ) becomes !( a ) || !( b )
	 * 
	 * @return
	 * 		The booleanExpression with all the negations distributed.
	 */
	public BooleanExpression distributeNegations();
	
	/**
	 * Distribute all the disjunctions that the booleanExpression may contain :
	 * - ( a || ( b && c ) ) becomes ( a || b ) && ( a || c )
	 * - ( ( b && c ) || a ) becomes ( a || b ) && ( a || c )
	 * 
	 * @return
	 * 		The booleanExpression with all the disjunctions distributed.
	 */
	public BooleanExpression distributeDisjunctions();

}
