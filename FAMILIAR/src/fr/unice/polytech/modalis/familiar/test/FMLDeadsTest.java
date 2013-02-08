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
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLDeadsTest extends FMLTest {

	@Test
	public void testDead1() throws Exception {

		String strfm1 = "FM (A : B [C] ; C : (D|E|F)+ ; B : (I|J)+ ; (!B | !C); )"; // C
																					// is
																					// dead,
																					// so
																					// D,
																					// E
																					// and
																					// F
		// compare two FMs
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> deads = fm1.deads();

		Set<String> expected = new HashSet<String>();
		expected.add("C");
		expected.add("D");
		expected.add("E");
		expected.add("F");

		assertEquals(expected.size(), deads.size());

		assertTrue(expected.containsAll(deads));

	}

	@Test
	public void testDead2() throws Exception {

		String strfm1 = "FM (A : B C ; (!B | !C); )"; // non valid feature model
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(false, fm1.isValid());

		Set<String> deads = fm1.deads(); // all features should be dead
		SetVariable expected = fm1.features();
		assertEquals(expected.size(), deads.size());
		assertTrue(FMLTest.setVariabletoString(expected).containsAll(deads));

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 6 about dead features
	 */
	@Test
	public void testBenavides1() throws Exception {

		String strfm1 = "FM ( A: B C ; C: (D|E) ; B -> !D ; )"; // B excludes D:
																// D is a dead
																// feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size()); // dead feature is D in this example

		assertEquals("D", deads.iterator().next());

		Set<String> fulls = fm1.fullMandatoryFeatures();

		assertEquals(1, fulls.size());
		assertEquals(fulls.iterator().next(), "E");

		Set<String> cores = fm1.cores(); // A, B, C, D
		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("E");
		assertEquals(expected.size(), cores.size());

		assertTrue(expected.containsAll(cores));

		fm1.cleanup(); // clean up should restitute the following fm: (E is
						// mandatory)
		assertEquals(FMBuilder.getInternalFM("A: B C; C : E ; ")
				.toString(), fm1.getFm().toString());

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 6 about dead features
	 */
	@Test
	public void testBenavides2() throws Exception {

		String strfm1 = "FM ( A: B C ; C: (D|E) ; B -> D ; )"; // B implies D: E
																// is a dead
																// feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size()); // dead feature is D in this example

		assertEquals("E", deads.iterator().next());

		Set<String> fulls = fm1.fullMandatoryFeatures();

		assertEquals(1, fulls.size());
		assertEquals(fulls.iterator().next(), "D");

		Set<String> cores = fm1.cores(); // A, B, C, D
		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("D");
		assertEquals(expected.size(), cores.size());

		assertTrue(expected.containsAll(cores));

		fm1.cleanup(); // clean up should restitute the following fm: (E is
						// mandatory)
		assertEquals(FMBuilder.getInternalFM("A: B C; C : D ; ")
				.toString(), fm1.getFm().toString());

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 6 about dead features
	 */
	@Test
	public void testBenavides3() throws Exception {

		String strfm1 = "FM ( A: B [C] ; B -> !C ; )"; // B excludes C: C is a
														// dead feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size()); // dead feature is D in this example

		assertEquals("C", deads.iterator().next());

		Set<String> fulls = fm1.fullMandatoryFeatures();
		assertEquals(0, fulls.size());

		Set<String> cores = fm1.cores(); // A, B
		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		assertEquals(expected.size(), cores.size());

		assertTrue(expected.containsAll(cores));

		fm1.cleanup(); // clean up should restitute the following fm: (E is
						// mandatory)
		assertEquals(FMBuilder.getInternalFM("A: B ; ").toString(), fm1
				.getFm().toString());

	}

	@Test
	public void testDeadScript1() throws Exception {

		String strfm1 = "FM ( A: B [C] ; B -> !C ; )"; // C is a full mandatory
														// feature
		_shell.parse("fm1 =" + strfm1 + "\n" + "dfm1 = deads fm1" + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		SetVariable dfm1 = getSetVariable("dfm1");
		assertEquals(1, dfm1.size());

		// TODO

	}

}
