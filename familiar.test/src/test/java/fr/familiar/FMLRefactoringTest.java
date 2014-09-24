/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
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

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */

@RunWith(value = Parameterized.class)
public class FMLRefactoringTest extends FMLTest {

	protected String _fm;

	public FMLRefactoringTest(String fm) {
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
	public void testEquality() throws Exception {

		// compare two FMs
		_shell.parse("fm1 =" + _fm + "\n" + "fm2 =" + _fm + "\n"
				+ "cmp = compare fm1 fm2" + "\n" + "b = fm1 eq fm2 \n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");

		Variable v3 = _environment.getVariable("cmp");
		assertNotNull(v3);
		assertTrue(v3 instanceof StringVariable);
		StringVariable cmp = (StringVariable) v3;

		Variable v4 = _environment.getVariable("b");
		assertNotNull(v4);
		assertTrue(v4 instanceof BooleanVariable);
		BooleanVariable b = (BooleanVariable) v4;

		assertEquals(Comparison.REFACTORING.toString(), cmp.getVal());
		assertEquals(true, b.isTrue());

		assertFMsEqual(fm1.getFm(), fm2.getFm());
		assertEquals(fm1.counting(), fm2.counting(), 0);
		assertEquals(fm1.counting (CountingStrategy.BDD_FML), fm2.counting (CountingStrategy.BDD_FML), 0);
		assertFormulaEquals(fm1.getFormula(), fm2.getFormula());
		// assertEquals(fm1.countingSAT(), fm2.countingSAT(), 0); // takes a lot
		// of time with FM_LAPTOP

	}

}
