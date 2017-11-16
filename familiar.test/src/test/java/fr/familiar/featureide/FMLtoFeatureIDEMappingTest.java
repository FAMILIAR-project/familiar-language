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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.prop4j.Node;
import org.prop4j.SatSolver;

import fr.familiar.FMLTest;
import fr.familiar.experimental.featureide.FMLtoSATFeatureIDE;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;

@Ignore
@RunWith(value = Parameterized.class)
public class FMLtoFeatureIDEMappingTest extends FMLTest {

	protected String _fm;

	public FMLtoFeatureIDEMappingTest(String fm) {
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
				{ "FM (A : B C [D] ; (!B -> !C) ;)\n" },
				{ "FM (A : B C [D] ; (!B | !C) ;)\n" }, // non valid should be
														// zero

		};
		return Arrays.asList(data);
	}

	@Test
	public void testCounting1() throws Exception {

		_shell.parse("fm1 =" + _fm + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		FMLtoSATFeatureIDE fIDEReasoner = new FMLtoSATFeatureIDE(fm1);
		long cfIDE = fIDEReasoner.counting();

		double cExpected = fm1.counting (CountingStrategy.BDD_FML);

		assertEquals(cExpected, cfIDE, 0);
		System.err.println("solution=" + fIDEReasoner.solution());

		HashMap<Object, Node> fts = new HashMap<Object, Node>();
		SatSolver satSimplified = fIDEReasoner.simplify(fts);

		assertEquals(cExpected, satSimplified.countSolutions(), 0);
		System.err.println("solution (bis)=" + satSimplified.getSolution());
	}
}
