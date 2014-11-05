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

package fr.familiar.featureide;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.prop4j.Node;

import fr.familiar.FMLTest;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.fm.featureide.SATFeatureIDEFormula;

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
