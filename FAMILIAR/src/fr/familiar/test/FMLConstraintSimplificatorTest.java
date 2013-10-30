package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;


@RunWith(value = Parameterized.class)
public class FMLConstraintSimplificatorTest extends FMLTest {
	
	protected String _fm1Specification;
	
	private static int _iDTest = 0;

	public FMLConstraintSimplificatorTest(String fm) {
		_fm1Specification = fm;
		
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "A : B [C] [D] ; D : (E|F) ; F -> !E ;" },
				{ "A : B [C] [D] ; D : (E|F) ; E -> !F ; C -> A ; " },
				{ "A : B [C] [D] ; D : (E|F) ; E -> !F ; A <-> C ; F -> !E ;" },
				{ "A : B [C] [D] ; D : (E|F) ; B -> C ; C -> D ; F -> !E ;" },
				{ "A : B [C] [D] ; D : (E|F|H) ; B -> !F ; " },
				{ "A : B [C] [D] ; D : (E|F|H) ; B -> !D; B -> !F ; " },
				{ "A : B [C] [D] ; D : (E|F|H) ; B -> D; B -> F ; " },
				{ "A : B C [D] ; (!B | !C) ;\n" }, // non valid
		};
		return Arrays.asList(data);
	}
	
	
	@Test
	public void testDetection() throws Exception {
		
		_iDTest ++ ; 
		
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1Specification);
		
		double n1 = fmv1.counting() ; 
		FeatureModelVariable fmv1Copy = (FeatureModelVariable) fmv1.copy() ; 
				
		Set<Expression<String>> csts = fmv1.computeRendundantConstraints();
		if (fmv1.hasRedundantConstraints()) {
			System.err.println("(" + _iDTest + ") redundant constraints: " + csts);
			assertTrue(csts.size() >= 1);
		}
		else
			assertTrue(csts.size() == 0);
					
		_shell.setVerbose(true);
		fmv1.eliminateRedundantConstraints();
		
		if (fmv1.isValid())
			assertFalse(fmv1.hasRedundantConstraints());
		double n2 = fmv1.counting() ; 
		
		assertEquals(n1, n2, 0);
		assertEquals(fmv1Copy.compareBDD(fmv1, _builder), Comparison.REFACTORING);
		
	}
	
	

}
