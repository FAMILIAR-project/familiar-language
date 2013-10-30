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
package fr.familiar.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.ConfigurationVariable;

@Ignore
@RunWith(value = Parameterized.class)
public class FMLConfigurationTest extends FMLTest {

	protected String _fm;

	public FMLConfigurationTest(String fm) {
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
		// { FMLTest.FM_LAPTOP }, // takes too much time!
		};
		return Arrays.asList(data);
	}

	@Test
	public void testAutoSelect1() throws Exception {

		_shell.parse("fm1 =" + _fm + "\n" + "fm2 =" + _fm + "\n"
				+ "c1 = configuration fm1" + "\n" + "c2 = configuration fm2 \n"
				+ "autoSelect c1 MAX\n" + "autoSelect c2\n" // randomly
		);

		ConfigurationVariable c1 = getConfigurationVariable("c1");
		ConfigurationVariable c2 = getConfigurationVariable("c2");

		assertTrue(c1.isComplete());
		assertTrue(c2.isComplete());

		int n1 = c1.getSelected().size();
		int n2 = c2.getSelected().size();
		assertTrue("n1=" + n1 + " should be superior or equals to c2=" + n2,
				n1 >= n2);

	}
	
}
