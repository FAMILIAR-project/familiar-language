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

import java.util.Set;

import org.junit.Test;

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
public class FMLRefactoring2Test extends FMLTest {

	@Test
	public void testNonValid1() throws Exception {

		String strfm1 = "FM (A : B [C] D E; E : (F|G); (!B | !F) ; (!B | !G); ) ";
		String strfm2 = "FM (A : B [C] D E; E : (F|G); (!B | !G) ; (!B | !F); )";
		// compare two FMs
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm2 + "\n"
				+ "cmp = compare fm1 fm2" + "\n" + "b = fm1 eq fm2 \n");

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("fm2");
		assertNotNull(v2);
		assertTrue(v2 instanceof FeatureModelVariable);
		FeatureModelVariable fm2 = (FeatureModelVariable) v2;

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
		assertEquals(fm1.counting (CountingStrategy.SAT_FEATUREIDE), fm2.counting (CountingStrategy.SAT_FEATUREIDE), 0);

		/* non valid */
		assertEquals(false, fm1.isValid());
		assertEquals(false, fm1.isValid());

		assertEquals(0, fm1.counting(), 0);
		assertEquals(0, fm2.counting(), 0);

	}

	@Test
	public void testRefactoringDead1() throws Exception {

		String strfm1 = "FM (A : B [C] ; (!C & B); )";
		String strfm2 = "FM (A : B ; )";
		// compare two FMs
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm2 + "\n"
				+ "cmp = compare fm1 fm2" + "\n" + "b = fm1 eq fm2 \n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");

		Variable v3 = _environment.getVariable("cmp");
		assertNotNull(v3);
		assertTrue(v3 instanceof StringVariable);
		StringVariable cmp = (StringVariable) v3;

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size());

		assertEquals("C", deads.iterator().next());

		assertEquals(Comparison.REFACTORING.toString(), cmp.getVal());

		assertEquals(fm1.counting(), fm2.counting(), 0);
		assertEquals(fm1.counting (CountingStrategy.BDD_FML), fm2.counting (CountingStrategy.BDD_FML), 0);
		assertEquals(fm1.counting (CountingStrategy.SAT_FEATUREIDE), fm2.counting (CountingStrategy.SAT_FEATUREIDE), 0);

		assertEquals(1, fm1.counting (CountingStrategy.BDD_FML), 0);
		assertEquals(1, fm2.counting (CountingStrategy.BDD_FML), 0);

		assertEquals(1, fm1.counting (CountingStrategy.SAT_FEATUREIDE), 0);
		assertEquals(1, fm2.counting (CountingStrategy.SAT_FEATUREIDE), 0);

		/* valid */
		assertEquals(true, fm1.isValid());
		assertEquals(true, fm2.isValid());

		assertTrue(fm1.counting() > 0);
		assertTrue(fm2.counting() > 0);

	}

	@Test
	public void testRefactoringDead2() throws Exception {

		String strfm1 = "FM (A : B [C] ; (!B | !C); )"; // equivalent to strfm1
														// above
		String strfm2 = "FM (A : B [C] ; (!C & B); )";

		// compare two FMs
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm2 + "\n"
				+ "cmp = compare fm1 fm2" + "\n" + "b = fm1 eq fm2 \n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");

		Variable v3 = _environment.getVariable("cmp");
		assertNotNull(v3);
		assertTrue(v3 instanceof StringVariable);
		StringVariable cmp = (StringVariable) v3;

		Set<String> deads = fm1.deads();
		assertEquals(1, deads.size());

		assertEquals("C", deads.iterator().next());

		assertEquals(Comparison.REFACTORING.toString(), cmp.getVal());

		assertEquals(fm1.counting(), fm2.counting(), 0);
		assertEquals(fm1.counting (CountingStrategy.BDD_FML), fm2.counting (CountingStrategy.BDD_FML), 0);
		assertEquals(fm1.counting (CountingStrategy.SAT_FEATUREIDE), fm2.counting (CountingStrategy.SAT_FEATUREIDE), 0);

		assertEquals(1, fm1.counting (CountingStrategy.BDD_FML), 0);
		assertEquals(1, fm2.counting (CountingStrategy.BDD_FML), 0);

		assertEquals(1, fm1.counting (CountingStrategy.SAT_FEATUREIDE), 0);
		assertEquals(1, fm2.counting (CountingStrategy.SAT_FEATUREIDE), 0);

		/* valid */
		assertEquals(true, fm1.isValid());
		assertEquals(true, fm2.isValid());

		assertTrue(fm1.counting() > 0);
		assertTrue(fm2.counting() > 0);

		// TODO:
		/*
		 * Variable v4 = _environment.getVariable("b"); assertNotNull(v4);
		 * assertTrue(v4 instanceof BooleanVariable); BooleanVariable b =
		 * (BooleanVariable) v4;
		 */
		// VariableComparator comparator = new VariableComparator(fm1, fm2,
		// ComparisonOperator.EQUAL);
		// assertEquals(true, b.isV());
	}

	@Test
	public void testRefactoring1() throws Exception {

		_shell.parse("	fm2 = FM (A: B [C] [D]; )\n"
				+ "	fm3 = FM (A: B [C]; B : [D]; )\n"
				+ "	cmp23 = compare fm2 fm3\n"
				+ "	assert (cmp23 eq REFACTORING)");

		StringVariable cmp = getStringVariable("cmp23");
		assertEquals("REFACTORING", cmp.getVal());

		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");

		assertEquals(fm2.counting(), fm3.counting(), 0);

		assertFMsEqual(fm2.getFm(), fm3.getFm());

	}

	@Test
	public void testRefactoring2() throws Exception {

		_shell.parse("	fm2 = FM (A: B [C] D; )\n"
				+ "	fm3 = FM (A: B [C]; B : D; )\n"
				+ "	cmp23 = compare fm2 fm3\n"
				+ "	assert (cmp23 eq REFACTORING)");

		StringVariable cmp = getStringVariable("cmp23");
		assertEquals("REFACTORING", cmp.getVal());

		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");

		assertEquals(fm2.counting(), fm3.counting(), 0);

		assertFMsEqual(fm2.getFm(), fm3.getFm());

	}

	/**
	 * believe me or not but the test fails if a new, fresh version of SAT4J if
	 * used (same compatibility issues with min/max SAT)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCompare1() throws Exception {

		_shell.parse("fm0 = FM (A: (B|C) ; )\n"
				+ "fm1 = FM (A: (B|C)+ ; )\n"
				+ "\n"
				+ "cmp01 = compare fm0 fm1 // fm0 is a specialization of fm1\n"
				+ "\n"
				+ "assert (cmp01 eq SPECIALIZATION)\n"
				+ "cmp10 = compare fm1 fm0 // fm1 is a generalization of fm0\n"
				+ "\n"
				+ "assert (cmp10 eq GENERALIZATION)\n"
				+ "\n"
				+ "\n"
				+ "// example taken from Automated Analysis of Feature Models 20 Years Later: A Literature Review (Benavides et al.)\n"
				+ "\n"
				+ "fm2 = FM (A: B [C] [D]; )\n"
				+ "fm3 = FM (A: B [C]; B : [D]; )\n"
				+ "cmp23 = compare fm2 fm3\n"
				+ "assert (cmp23 eq REFACTORING) \n"
				+ "\n"
				+ "// example taken from Reasoning about Edits to Feature Models (Thum et al.)\n"
				+ "\n"
				+ "batoryICSEf = FM (S: (T|D)+; T : [A] B; )\n"
				+ "batoryICSEg = FM (S: (T|D)+; T : [A] B [C]; )\n"
				+ "cmpfg = compare batoryICSEf batoryICSEg // f is a specialization of g\n"
				+ "\n"
				+ "assert (cmpfg eq SPECIALIZATION)\n"
				+ "cmpgf = compare batoryICSEg batoryICSEf // g is a generalization of f\n"
				+ "\n"
				+ "assert (cmpgf eq GENERALIZATION)\n"
				+ "\n"
				+ "\n"
				+ "fm4 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; )\n"
				+ "fm5 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; W : [T] [A] B; )\n"
				+ "cmp45 = compare fm4 fm5 // fm4 is an arbitrary edit of fm5\n"
				+ "\n"
				+ "assert (cmp45 eq ARBITRARY)\n"
				+ "cmp54 = compare fm5 fm4 // fm5 is an arbitrary edit of fm4\n"
				+ "\n"
				+ "assert (cmp54 eq ARBITRARY)\n"
				+ "\n"
				+ "\n"
				+ "gc0 = FM ( GraphicCard: DirectX Speed Bus [Multi] ; DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; Bus: n128; )\n"
				+ "gc1 = FM ( GraphicCard: DirectX Speed Bus [Vertex] ; DirectX: (v10dot1 | v10 | v9) ; Speed: n800 ; Bus: (n64 | n128); )\n"
				+ "cmpgc01 = compare gc0 gc1 // gc0 is an arbitrary edit of gc1\n"
				+ "\n"
				+ "assert (cmpgc01 eq ARBITRARY)\n"
				+ "cmpgc10 = compare gc1 gc0 // gc1 is an arbitrary edit of gc0\n"
				+ "\n" + "assert (cmpgc10 eq ARBITRARY)\n" + "\n" + "\n" + "\n"
				+ "");

		assertSpecialization("fm0", "fm1");
		assertGeneralization("fm1", "fm0");
		assertSpecialization("batoryICSEf", "batoryICSEg");
		assertGeneralization("batoryICSEg", "batoryICSEf");

	}

	@Test
	public void testCompare2() throws Exception {

		_shell.parse("fm0 = FM (A: (B|C) ; )\n"
				+ "fm1 = FM (A: (B|C)+ ; )\n"
				+ "assert ((compare fm0 fm1) eq SPECIALIZATION) // fm0 is a specialization of fm1\n"
				+ "assert ((compare fm1 fm0) eq GENERALIZATION) // fm1 is a generalization of fm0\n"
				+ "\n"
				+ "\n"
				+ "// example taken from Automated Analysis of Feature Models 20 Years Later: A Literature Review (Benavides et al.)\n"
				+ "\n"
				+ "fm2 = FM (A: B [C] [D]; )\n"
				+ "fm3 = FM (A: B [C]; B : [D]; )\n"
				+ "cmp23 = compare fm2 fm3\n"
				+ "assert (cmp23 eq REFACTORING) // TODO: correct the bug\n"
				+ "\n"
				+ "// example taken from Reasoning about Edits to Feature Models (Thum et al.)\n"
				+ "\n"
				+ "batoryICSEf = FM (S: (T|D)+; T : [A] B; )\n"
				+ "batoryICSEg = FM (S: (T|D)+; T : [A] B [C]; )\n"
				+ "cmpfg = compare batoryICSEf batoryICSEg // f is a specialization of g\n"
				+ "assert (cmpfg eq SPECIALIZATION)\n"
				+ "assert ((compare batoryICSEg batoryICSEf) eq GENERALIZATION) // g is a generalization of f\n"
				+ "\n"
				+ "\n"
				+ "fm4 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; )\n"
				+ "fm5 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; W : [T] [A] B; )\n"
				+ "cmp45 = compare fm4 fm5 // fm4 is an arbitrary edit of fm5\n"
				+ "assert (cmp45 eq ARBITRARY)\n"
				+ "assert ((compare fm5 fm4) eq ARBITRARY) // fm5 is an arbitrary edit of fm4\n"
				+ "\n"
				+ "\n"
				+ "gc0 = FM ( GraphicCard: DirectX Speed Bus [Multi] ; DirectX: (v10dot1 | v10) ; Speed: (n800 | n1000) ; Bus: n128; )\n"
				+ "gc1 = FM ( GraphicCard: DirectX Speed Bus [Vertex] ; DirectX: (v10dot1 | v10 | v9) ; Speed: n800 ; Bus: (n64 | n128); )\n"
				+ "assert ((compare gc0 gc1) eq ARBITRARY) // gc0 is an arbitrary edit of gc1\n"
				+ "assert ((compare gc1 gc0) eq ARBITRARY) // gc1 is an arbitrary edit of gc0");

		assertRefactoring("fm2", "fm3");
		assertRefactoring("fm3", "fm2");
		assertSpecialization("fm0", "fm1");
		assertGeneralization("fm1", "fm0");
		assertSpecialization("batoryICSEf", "batoryICSEg");
		assertGeneralization("batoryICSEg", "batoryICSEf");
		assertArbitrary("fm4", "fm5");
		assertArbitrary("fm5", "fm4");
		assertArbitrary("gc0", "gc1");
		assertArbitrary("gc1", "gc0");
	}

	@Test
	public void testEdit1() throws Exception {

		_shell.parse("fm1 = FM (A: B [C] [D]; )\n"
				+ "fm2 = FM (A: B [C] [D]; )\n" + "cmp12 = compare fm1 fm2\n"
				+ "assert (cmp12 eq REFACTORING)\n" + "setOptional fm1.B\n"
				+ // unleashed constraint
				"cmp12bis = compare fm1 fm2\n"
				+ "assert (cmp12bis eq GENERALIZATION)\n");

		assertGeneralization("fm1", "fm2");
		assertSpecialization("fm2", "fm1");

	}

	@Test
	public void testEdit2() throws Exception {

		_shell.parse("fm1 = FM (A: B [C] [D]; )\n"
				+ "fm2 = FM (A: B [C] [D]; )\n" + "cmp12 = compare fm1 fm2\n"
				+ "assert (cmp12 eq REFACTORING)\n" + "setMandatory fm1.C\n"
				+ // reinforce constraint
				"cmp12bis = compare fm1 fm2\n"
				+ "assert (cmp12bis eq SPECIALIZATION)\n");

		assertSpecialization("fm1", "fm2");
		assertGeneralization("fm2", "fm1");

	}

}
