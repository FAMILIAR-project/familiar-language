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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureModel;

/**
 * @author mathieuacher
 * 
 */
public class FMLCleanupTest extends FMLTest {

	@Test
	public void testCleanup1() throws Exception {

		String strfm1 = "FM (A : B [C] ; (B & !C); )"; // C is dead
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		fm1.cleanup();

		FeatureModel<String> fm1Bis = FMBuilder.getInternalFM("A : B; ") ; 
		
		assertFMsEqual(fm1Bis, fm1.getFm());

	}

	@Test
	public void testCleanup2() throws Exception {

		String strfm1 = "FM (A : B [C] ; C : (D|E|F)+ ; B : (I|J)+ ; (!B | !C); )"; // C
																					// is
																					// dead,
																					// so
																					// D,
																					// E
																					// and
																					// F
		// compare two FMs
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		fm1.cleanup();
		assertFMsEqual(FMBuilder.getInternalFM("A : B; B : (I|J)+ ; "),
				fm1.getFm());

	}

	@Test
	public void testCleanup3() throws Exception {

		String strfm1 = "FM (A : B [C] ; (!B | !C); )"; // C is dead
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		fm1.cleanup();
		assertFMsEqual(FMBuilder.getInternalFM("A : B; "), fm1.getFm());

	}

	@Test
	public void testCleanup4() throws Exception {

		String strfm1 = "FM ( W: [X] Y Z ; (X <-> Y); )"; // X is a full
															// mandatory feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		_shell.setVerbose(true);
		fm1.cleanup();
		assertEquals(FMBuilder.getInternalFM("W : X Y Z; ").toString(),
				fm1.getFm().toString());

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 8 about false optional features
	 */
	@Test
	public void testBenavides1() throws Exception {

		String strfm1 = "FM ( A: B C ; C: (D|E)+ ; B -> D ; )"; // D is a full
																// mandatory
																// feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

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

		assertEquals(0, fm1.deads().size()); // no dead feature in this example

		fm1.cleanup(); // clean up should restitute the following fm: (E is
						// optional)
		assertEquals(FMBuilder.getInternalFM("A: B C; C : D [E]; ")
				.toString(), fm1.getFm().toString());

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 8 about false optional features
	 */
	@Test
	public void testBenavides2() throws Exception {

		String strfm1 = "FM ( A: B C ; C: (D|E) ; B -> D ; )"; // D is a full
																// mandatory
																// feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

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

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size()); // dead feature is E in this example

		assertEquals("E", deads.iterator().next());

		fm1.cleanup(); // clean up should restitute the following fm: (E is
						// optional)
		assertEquals(FMBuilder.getInternalFM("A: B C; C : D ; ")
				.toString(), fm1.getFm().toString());

	}

	/**
	 * @throws Exception
	 *             benavides2010 Figure 8 about false optional features
	 */
	@Test
	public void testBenavides3() throws Exception {

		String strfm1 = "FM ( A: B [C] ; B -> C ; )"; // C is a full mandatory
														// feature
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> fulls = fm1.fullMandatoryFeatures();

		assertEquals(1, fulls.size());
		assertEquals(fulls.iterator().next(), "C");

		Set<String> cores = fm1.cores(); // A, B, C, D
		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		assertEquals(expected.size(), cores.size());

		assertTrue(expected.containsAll(cores));

		Set<String> deads = fm1.deads();
		assertEquals(0, deads.size()); // no dead feature

		fm1.cleanup(); // clean up should restitute the following fm:
		assertEquals(FMBuilder.getInternalFM("A: B C; ").toString(), fm1
				.getFm().toString());

	}

	@Test
	public void testCleanUpScript1() throws Exception {

		String strfm1 = "FM ( A: B [C] ; B -> C ; )"; // C is a full mandatory
														// feature
		_shell.parse("fm1 =" + strfm1 + "\n" + "cleanup fm1");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Set<String> fulls = fm1.fullMandatoryFeatures();
		assertEquals(0, fulls.size());

		Set<String> cores = fm1.cores(); // A, B, C, D
		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		assertEquals(expected.size(), cores.size());

		assertTrue(expected.containsAll(cores));

		Set<String> deads = fm1.deads();
		assertEquals(0, deads.size());

		assertEquals(FMBuilder.getInternalFM("A: B C; ").toString(), fm1
				.getFm().toString());

	}

	@Test
	public void testCleanUpScript2() throws Exception {

		String strfm1 = "FM ( A: B [C] D ; B -> !C ; )"; // C is a dead feature
		_shell.parse("fm1 =" + strfm1 + "\n" + "cleanup fm1" + "\n"
				+ "cfm1 = cores fm1" + "\n" + "dfm1 = deads fm1" + "\n"
		// + "ffm1 = falseOptionals fm1\n"
		);

		FeatureModelVariable fm1 = getFMVariable("fm1");

		SetVariable dfm1 = getSetVariable("dfm1");
		assertEquals(0, dfm1.size());

		SetVariable cfm1 = getSetVariable("cfm1");
		assertEquals(3, cfm1.size()); // A, B, D

	}

	@Test
	public void testFullMandatory1() throws Exception {

		String strfm1 = "FM ( A: B [C] ; B -> C ; )"; // C is a full mandatory
														// feature
		_shell.parse("fm1 =" + strfm1 + "\n" + "ffm1 = fullMandatorys fm1"
				+ "\n" + "ffm1bis = falseOptionals fm1" + "\n");

		SetVariable ffm1 = getSetVariable("ffm1");
		assertEquals(1, ffm1.size());
		Variable v = ffm1.getVars().iterator().next();
		assertTrue(v instanceof FeatureVariable);
		FeatureVariable ftv = (FeatureVariable) v;

		assertEquals("C", ftv.getFtName());

		SetVariable ffm1bis = getSetVariable("ffm1bis");
		assertEquals(1, ffm1bis.size());
		Variable vbis = ffm1bis.getVars().iterator().next();
		assertTrue(vbis instanceof FeatureVariable);
		FeatureVariable ftvbis = (FeatureVariable) v;

		assertEquals("C", ftvbis.getFtName());

	}

	@Test
	public void testFullMandatory2() throws Exception {

		String strfm1 = "FM ( A: B [C] ; true -> C ; )"; // C is a full
															// mandatory feature

		_shell.parse("fm1 =" + strfm1 + "\n" + "ffm1 = fullMandatorys fm1"
				+ "\n" + "ffm1bis = falseOptionals fm1" + "\n");

		SetVariable ffm1 = getSetVariable("ffm1");
		assertEquals(1, ffm1.size());
		Variable v = ffm1.getVars().iterator().next();
		assertTrue(v instanceof FeatureVariable);
		FeatureVariable ftv = (FeatureVariable) v;

		assertEquals("C", ftv.getFtName());

		SetVariable ffm1bis = getSetVariable("ffm1bis");
		assertEquals(1, ffm1bis.size());
		Variable vbis = ffm1bis.getVars().iterator().next();
		assertTrue(vbis instanceof FeatureVariable);
		FeatureVariable ftvbis = (FeatureVariable) v;

		assertEquals("C", ftvbis.getFtName());

	}

	@Test
	public void testDead1() throws Exception {

		String strfm1 = "FM ( A: B [C] ; true -> !C ; )"; // C is a dead feature

		_shell.parse("fm1 =" + strfm1 + "\n" + "ffm1 = deads fm1" + "\n"
				+ "cleanup fm1" + "\n");

		SetVariable ffm1 = getSetVariable("ffm1");
		assertEquals(1, ffm1.size());
		Variable v = ffm1.getVars().iterator().next();
		assertTrue(v instanceof FeatureVariable);
		FeatureVariable ftv = (FeatureVariable) v;

		assertEquals("C", ftv.getFtName());

		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertFMsEqual(FMBuilder.getInternalFM("A: B ;"), fm1.getFm());

	}

}
