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
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher Counting operation, i.e., number of
 *         configurations/products of a feature model/family
 *         
 * TODO: "worst" bug of FAMILIAR related to counting issues (BDD)
 */


@Ignore
@RunWith(value = Parameterized.class)
public class FMLCountingTest extends FMLTest {

	protected String _fm;

	public FMLCountingTest(String fm) {
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

		// FIXME two calls to "countingBDD" completely fail :(

		_shell.parse("fm1 =" + _fm + "\n" + "n = counting fm1");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		// double withBDD = FMLTest.countingBDD(fm1, _builder) ;
		double withSAT = fm1.counting (CountingStrategy.SAT_FEATUREIDE);
		// assertEquals (withBDD, withSAT, 0);
		
		double withBDDSPLOT = fm1.counting() ; //fm1.countingBDDSPLOT();
		assertEquals(withSAT, withBDDSPLOT, 0);

		Variable v2 = _environment.getVariable("n");
		assertNotNull(v2);
		assertTrue(v2 instanceof IntegerVariable);
		IntegerVariable iv = (IntegerVariable) v2;
		int n = iv.getV();

		assertEquals(withBDDSPLOT, n, 0);

	}

	@Test
	public void testCounting2() throws Exception {
		_shell.parse("fm1 =" + _fm);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		// double withBDD = FMLTest.countingBDD(fm1, _builder) ;
		double withSAT = fm1.counting (CountingStrategy.SAT_FEATUREIDE);
		// assertEquals (withBDD, withSAT, 0);

		Set<Variable> configsBDD = fm1.configsBDD();
		assertEquals(withSAT, configsBDD.size(), 0);
		double withBDDSPLOT = fm1.counting (CountingStrategy.BDD_SPLOT);
		assertEquals(withSAT, withBDDSPLOT, 0);
	}

	// @Test
	// public void testCountingFMLaptop() throws Exception {
	// // FMLTest.FM_LAPTOP // takes a lot of time with SAT solvers
	// _shell.parse("fm1 =" + FMLTest.FM_LAPTOP);
	// Variable v1 = _environment.getVariable("fm1");
	// assertNotNull(v1);
	// assertTrue(v1 instanceof FeatureModelVariable);
	// FeatureModelVariable fm1 = (FeatureModelVariable) v1 ;
	// double withBDD = FMLTest.countingBDD(fm1.getFm(), _builder) ;
	// Set<Set<String>> configsBDD = fm1.configsBDD() ;
	// assertEquals (withBDD, configsBDD.size() );
	// /*System.err.println("\n\n\rconfigsBDD=" + configsBDD);
	// int i = 1 ;
	// for (Set<String> config : configsBDD) {
	// System.err.println("config" + i++ + "= " + config);
	// }*/
	//
	// // double withSAT = fm1.countingSAT() ;
	// // assertEquals (withBDD, withSAT, 0);
	// }
	//

}
