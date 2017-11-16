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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher Determine whether a feature model is valid or not
 */

@RunWith(value = Parameterized.class)
public class FMLIsValidTest extends FMLTest {

	protected String _fm;

	public FMLIsValidTest(String fm) {
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
				{ "FM (A : B C [D] ; (!B -> !C) ; )" }, { FMLTest.FM_LAPTOP }, };
		return Arrays.asList(data);
	}

	@Test
	public void testIsValid1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n" + "b = isValid fm1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("b");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;
		boolean b = bv.isTrue();

		assertEquals(b, fm1.counting() > 0);
		assertEquals(b, fm1.isValid());
	}

}
