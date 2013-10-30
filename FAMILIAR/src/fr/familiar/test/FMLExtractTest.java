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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLExtractTest extends FMLTest {

	@Test
	public void testExtract1() throws Exception {

		_shell.parse("fmTest = FM (A: B C [D]; D: (E|F)+; F: J [K];)\n"
				+ "fmD = extract fmTest.D \n" + "fmC = extract fmTest.C\n"
				+ "nC = nbFeatures fmC\n" + "nD = nbFeatures fmD\n"
				+ "NprodD = counting fmD\n"
				+ "collectionOfFMs = { fmD fmC fmTest }");

		IntegerVariable nC = getIntegerVariable("nC");
		assertEquals(1, nC.getV());
		IntegerVariable nD = getIntegerVariable("nD");
		assertEquals(5, nD.getV());

		IntegerVariable NprodD = getIntegerVariable("NprodD");
		FeatureModelVariable fmD = getFMVariable("fmD");
		assertEquals(fmD.counting(), NprodD.getV(), 0);
		assertEquals(5, NprodD.getV());

	}

	@Test
	public void testExtract2() throws Exception {

		_shell.parse("fm1 = FM (A : B [C] [D]; C : (E|F|G) ; F : (I|J)+; I : K L [M];)\n"
				+ "fm1bis = copy fm1\n"
				+ "fm1C = extract fm1.C\n"
				+ "\n"
				+ "// checkings\n"
				+ " \n"
				+ "ftC = root fm1C\n"
				+ "nameC = name ftC\n"
				+ "nameCexpected = \"C\" \n"
				+ "assert (nameC eq nameCexpected) \n"
				+ "\n"
				+ "fm1Cexpected = FM (C : (E|F|G) ; F : (I|J)+; I : K L [M];)\n"
				+ "assert (fm1C eq fm1Cexpected)\n"
				+ "\n"
				+ "// extract does not alter feature models\n"
				+ "\n"
				+ "assert (fm1 eq fm1bis)");

	}

	@Test
	public void testExtract3() throws Exception {

		String scriptExtract3 = "fm1 = FM (A : B [C] [D]; C : (E|F|G) ; F : (I|J)+; I : K L [M];)\n"
			+ "fm1C = extract fm1.C\n"
			+ "assert ((size children fm1C.C) eq 3)\n"
			+ "assert ((size fm1C.*) eq ((size fm1.*) - 3) ) // there is no A, B and D\n"
			+ "assert (counting fm1C < counting fm1)\n"
			+ "println 'fm1 has ', (counting fm1), \" valid solutions whereas fm1C has \", (counting fm1C), \" solutions\"\n"
			+ "\n"
			+ "fm1R = copy fm1\n"
			+ "assert (removeFeature fm1R.D)\n"
			+ "println 'fm1C has ', (counting fm1C), \" valid solutions whereas fm1R has \", (counting fm1R), \" solutions\"\n"
			+ "assert (counting fm1C eq ((counting fm1R) - 1))" ; 
		System.err.println(scriptExtract3);
		_shell.parse(scriptExtract3);

	}

}
