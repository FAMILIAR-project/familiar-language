/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.fm.converter.FeatureModelToExpression;
import fr.unice.polytech.modalis.familiar.operations.ExpressionUtility;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

/**
 * @author macher1
 *
 */
public class FMLFM2ConstraintTest extends FMLTest {

	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1 = FM ("fm1", "A : B C [D] ; D : E [F] ; B : [I] J K ; K : L M [N] ;") ;
		Collection<Expression<String>> exprs1 = new FeatureModelToExpression(fm1).convert() ;
		System.err.println("exprs1=" + exprs1);
		
		SATFMLFormula satCst = new SATFMLFormula(ExpressionUtility.mkConjunction(exprs1)) ; 
		System.err.println("cores=" + satCst.cores(fm1.features().names()));
		System.err.println("counting=" + satCst.counting());
		System.err.println("fm1=" + fm1.counting());
		
		/*assertEquals(Comparison.REFACTORING, 
		satCst.compare(new SATFMLFormula(fm1)));
		
		assertEquals(Comparison.REFACTORING, 
		fm1.compare(new FeatureModelVariableBDDFormula("", 
				new Formula<String>(_builder.mkExpression(ExpressionUtility.mkConjunction(exprs1)),
						fm1.features().names()
						, _builder), _builder))
						
				);*/
		
		
		FeatureModelVariable fm2 = FM ("fm2", "A : B [C] [D] ; D : E [F] ; B : [I] J K ; K : L M [N] ; I -> N ; C -> !D ; ") ;
		Collection<Expression<String>> exprs2 = new FeatureModelToExpression(fm2).convert() ;
		SATFMLFormula satCst2 = new SATFMLFormula(ExpressionUtility.mkConjunction(exprs2)) ; 
		assertEquals (fm2.counting(), satCst2.counting(), 0);
		
		FeatureModelVariable fm3 = FM ("fm3", "A : B [C] [D] ; D : E [F] ; B : (I|J|K)+ ; K : (L|N|M) ; I -> N ; C -> !D ; ") ;
		Collection<Expression<String>> exprs3 = new FeatureModelToExpression(fm3).convert() ;
		SATFMLFormula satCst3 = new SATFMLFormula(ExpressionUtility.mkConjunction(exprs3)) ; 
		assertEquals (fm3.counting(), satCst3.counting(), 0);
		
		
		System.err.println("fm3=" + fm3.counting());
		
		
	}

}
