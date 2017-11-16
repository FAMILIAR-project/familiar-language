/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import fr.familiar.fm.converter.FeatureModelToExpression;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
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
		
		
		//assertEquals (fm3.counting(), satCst3.counting(), 0);
		
		
		System.err.println("fm3=" + fm3.counting());
		
		
	}

}
