/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test.featureide;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.prop4j.Node;

import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATBuilder;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */
@RunWith(value = Parameterized.class)
public class SATBuilderTest extends FMLTest {

	protected String _fm;

	public SATBuilderTest(String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B [C] [E] ; E : (F|G); )" },
				{ "FM (A: B [C] ; )" },
				{ "FM (A: B [C] [E] ;)" },
				{ "FM (A: B C ; )" },
				{ "FM (A: B [C] D ; C : E F G ; G : (H|I) ; (D -> !C); )" },
				{ "FM (A: (D|E|F) ; )" },
				{ "FM (A: B C ; C : E [F]; F : [G]; G : H I [J]; )" },
				{ "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [D] ; (D <-> C); )" },
				{ "FM (A: B C D ; (D -> !C); )" }, // non valid should be zero
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ;)\n" },
				{ "FM (A: (D|E|F)+ ; )" },
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D;  )" },
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D; !D ; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; J : [J1] J2 [J3] [J4] J5; K : [K1] K2 [K3] [K4] K5;  )" },
				{ "FM (A : B C [D] ; (!B | !C) ;)\n" }, // non valid should be
														// zero

		};
		return Arrays.asList(data);
	}

	@Test
	public void test1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		double c0 = fm1.counting (CountingStrategy.BDD_FML);
		double c1 = new SATFeatureIDEFormula(fm1).counting();

		// assertEquals(c0, c1, 0);

		Node n1 = new SATBuilder().mkNode(fm1);
		double c2 = new SATFMLFormula(n1).counting();

		// assertEquals(c0, c2, 0);

	}
	
	
}
