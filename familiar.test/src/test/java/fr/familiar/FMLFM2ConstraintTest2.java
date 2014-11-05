/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.fm.converter.FeatureModelToExpression;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

@RunWith(value = Parameterized.class)
public class FMLFM2ConstraintTest2 extends FMLTest {

	protected String _fm;

	public FMLFM2ConstraintTest2 (String fm) {
		_fm = fm;
	}
	
	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ; )" },
				
				{ "FM (A : B C [D] ; D : (E|F)? G [H] ; C : (I|J|K|L)+ (M|N|O) (P|Q)? ; J : (X|Y|Z) [XXX] ; XXX <-> F ; )" },
		// { FMLTest.FM_LAPTOP }, // takes too much time!
		};
		return Arrays.asList(data);
	}

	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1 = FM ("fm1", _fm);
		
		Collection<Expression<String>> exprs1 = new FeatureModelToExpression(fm1).convert() ;
		System.err.println("exprs1=" + exprs1);
		
		SATFMLFormula satCst = new SATFMLFormula(ExpressionUtility.mkConjunction(exprs1)) ;
		Set<String> coresCst = satCst.cores(fm1.features().names()) ; 
		coresCst.remove(FeatureModelToExpression._TOP_VARIABLE_NAME);
		assertEquals(fm1.cores(), coresCst);
		assertEquals(fm1.counting(), satCst.counting(), 0);
		
	}
	
}
