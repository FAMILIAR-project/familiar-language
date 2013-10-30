package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import fr.familiar.fm.converter.FeatureModelToExpression;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

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
