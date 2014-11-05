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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.ComparisonOperator;

import fr.familiar.parser.VariableComparator;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher features() operation that returns the set of features of
 *         an fm e.g., fm1 = FM (....) then fm1.*
 */
public class FMLFeaturesTest extends FMLTest {

	@Test
	public void testFeatures1() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )" + "fts = fm1.*"); // non
																				// explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 7);
	}

	@Test
	public void testFeatures2() throws Exception {

		_shell.parse("fm1 = FM (A: [B] [C] [D]; D : (H|I|J)+ ; C : (Z|X|Y|U) ; )"
				+ "fts = fm1.*"); // non explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 11);
	}

	@Test
	public void testFeatures3() throws Exception {

		_shell.parse("fm1 = FM (A: [B] ; )" + "fts = fm1.*"); // non explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 2);
	}

	@Test
	public void testFeatures4() throws Exception {

		_shell.parse("fm1 = FM (A: [B] C D ; D : (E|F|G); )\n"
				+ "fts = fm1.*\n" + "fts2 = features fm1\n" + "n1 = size fts\n"
				+ "n2 = size fts2\n" + "n3 = nbFeatures fm1");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 7);

		Variable v3 = _environment.getVariable("fts2");
		assertNotNull(v3);
		assertTrue(v3 instanceof SetVariable);

		SetVariable fts2 = (SetVariable) v3;
		assertTrue(new VariableComparator(fts, fts2, ComparisonOperator.EQUAL)
				.eval());
		assertTrue(new VariableComparator(fts2, fts, ComparisonOperator.EQUAL)
				.eval());
		assertTrue(fts.size() == fts2.size());

		IntegerVariable n1 = getIntegerVariable("n1");
		IntegerVariable n2 = getIntegerVariable("n2");
		IntegerVariable n3 = getIntegerVariable("n3");

		assertTrue(new VariableComparator(n1, n2, ComparisonOperator.EQUAL)
				.eval());
		assertTrue(new VariableComparator(n3, n2, ComparisonOperator.EQUAL)
				.eval());

		assertTrue(n1.getV() == n2.getV());
		assertTrue(n1.getV() == n3.getV());
		assertTrue(n2.getV() == n3.getV());
	}

	@Test
	public void testFeaturesWithRename1() throws Exception {

		String original1 = "D";
		String original2 = "C";

		String newName1 = "Dbis";
		String newName2 = "Cbis";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "renameFeature fm1." + original1 + " as \"" + newName1 + "\""
				+ "renameFeature fm1." + original2 + " as \"" + newName2 + "\""
				+ "fts = fm1.*"); // non explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 7); // no side effect on the
														// number of features

		assertTrue(FMLTest.isContained(newName1, internalFts));
		assertTrue(FMLTest.isContained(newName1, fts));
		assertTrue(FMLTest.isContained(newName2, internalFts));
		assertTrue(FMLTest.isContained(newName2, fts));

		assertFalse(FMLTest.isContained(original1, internalFts));
		assertFalse(FMLTest.isContained(original1, fts));
		assertFalse(FMLTest.isContained(original2, internalFts));
		assertFalse(FMLTest.isContained(original2, fts));

	}

	@Test
	public void testFeaturesWithRename2() throws Exception {

		String attempt1 = "DDDD"; // does not exist
		String attempt2 = "CCCC"; // does not exist

		String original1 = "D"; // does not exist
		String original2 = "C"; // does not exist

		String newName1 = "Dbis";
		String newName2 = "Cbis";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "renameFeature fm1." + attempt1 + " as \"" + newName1 + "\""
				+ "renameFeature fm1." + attempt2 + " as \"" + newName2 + "\""
				+ "fts = fm1.*"); // non explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		SetVariable fts = getSetVariable("fts");
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());

		assertFalse(FMLTest.isContained(newName1, internalFts));
		assertFalse(FMLTest.isContained(newName1, fts));
		assertFalse(FMLTest.isContained(newName2, internalFts));
		assertFalse(FMLTest.isContained(newName2, fts));

		assertTrue(FMLTest.isContained(original1, internalFts));
		assertTrue(FMLTest.isContained(original1, fts));
		assertTrue(FMLTest.isContained(original2, internalFts));
		assertTrue(FMLTest.isContained(original2, fts));

		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 7); // no side effect on the
														// number of features

	}

	@Test
	public void testFeaturesWithRemove1() throws Exception {

		String original1 = "B";
		String original2 = "C";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "removeFeature fm1." + original1 + "\n"
				+ "removeFeature fm1." + original2 + "\n" + "fts = fm1.*"); // non
																			// explicit

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 5); // side effect on the
														// number of features

		assertFalse(FMLTest.isContained(original1, internalFts));
		assertFalse(FMLTest.isContained(original1, fts));
		assertFalse(FMLTest.isContained(original2, internalFts));
		assertFalse(FMLTest.isContained(original2, fts));

	}

	@Test
	public void testFeaturesWithRemove2() throws Exception {

		String attempt1 = "Bbis";
		String attempt2 = "Cbis";

		String original1 = "B";
		String original2 = "C";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "removeFeature fm1." + attempt1 + "\n" + "removeFeature fm1."
				+ attempt2 + "\n" + "fts = fm1.*");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 7); // no side effect on the
														// number of features

		assertFalse(FMLTest.isContained(attempt1, internalFts));
		assertFalse(FMLTest.isContained(attempt1, fts));
		assertFalse(FMLTest.isContained(attempt2, internalFts));
		assertFalse(FMLTest.isContained(attempt2, fts));

		// still there
		assertTrue(FMLTest.isContained(original1, internalFts));
		assertTrue(FMLTest.isContained(original1, fts));
		assertTrue(FMLTest.isContained(original2, internalFts));
		assertTrue(FMLTest.isContained(original2, fts));

	}

	@Test
	public void testFeaturesWithRemove3() throws Exception {

		String original1 = "D";
		String original2 = "C";

		String child1 = "H";
		String child2 = "I";
		String child3 = "J";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "removeFeature fm1." + original1 + "\n"
				+ "removeFeature fm1." + original2 + "\n" + "fts = fm1.*"); // H,
																			// I
																			// and
																			// J
																			// are
																			// also
																			// removed

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("fts");
		assertNotNull(v2);
		assertTrue(v2 instanceof SetVariable);

		SetVariable fts = (SetVariable) v2;
		SetVariable internalFts = fm1.features();
		assertTrue(fts.getVars().size() == internalFts.getVars().size());
		assertTrue(new VariableComparator(fts, internalFts,
				ComparisonOperator.EQUAL).eval());
		assertTrue(internalFts.getVars().size() == 2); // effect on the
														// number of features

		// H, I and J are also removed
		assertFalse(FMLTest.isContained(child1, internalFts));
		assertFalse(FMLTest.isContained(child2, internalFts));
		assertFalse(FMLTest.isContained(child3, internalFts));
		assertFalse(FMLTest.isContained(child1, fts));
		assertFalse(FMLTest.isContained(child2, fts));
		assertFalse(FMLTest.isContained(child3, fts));

		// still there
		assertFalse(FMLTest.isContained(original1, internalFts));
		assertFalse(FMLTest.isContained(original1, fts));
		assertFalse(FMLTest.isContained(original2, internalFts));
		assertFalse(FMLTest.isContained(original2, fts));

	}

	@Test
	public void testFMLFeatures1() throws Exception {

		_shell.parse("fm1 = FM (A: (B|C)+ ; )\n"
				+ "nfm1 = nbFeatures fm1\n"
				+ "nfm1Expected = 3\n"
				+ "nfm1bis = size fm1.*\n"
				+ "assert (nfm1 eq nfm1Expected)\n"
				+ "assert (nfm1 eq nfm1bis)\n"
				+ "\n"
				+ "fm2 = FM (A: B [C]; B : [D]; )\n"
				+ "nfm2 = nbFeatures fm2\n"
				+ "nfm2Expected = 4\n"
				+ "nfm2bis = size fm2.*\n"
				+ "assert (nfm2 eq nfm2Expected)\n"
				+ "assert (nfm2 eq nfm2bis)\n"
				+ "\n"
				+ "fm3 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; )\n"
				+ "nfm3 = nbFeatures fm3\n"
				+ "nfm3Expected = 12\n"
				+ "nfm3bis = size fm3.*\n"
				+ "assert (nfm3 eq nfm3Expected)\n"
				+ "assert (nfm3 eq nfm3bis)\n"
				+ "\n"
				+ "fm4 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; W : [T] [A] B; )\n"
				+ "nfm4 = nbFeatures fm4\n" + "nfm4Expected = 15\n"
				+ "nfm4bis = size fm4.*\n" + "assert (nfm4 eq nfm4Expected)\n"
				+ "assert (nfm4 eq nfm4bis)");

	}

}
