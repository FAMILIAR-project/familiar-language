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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class FMLRenameFeatureTest extends FMLTest {

	@Test
	public void testRenameRootTrue1() throws Exception {

		final String newName = "Abis";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature fm1.A as \"" + newName + "\"");

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		FeatureVariable fv = fmv.root();

		assertNotNull(fv);
		assertTrue(fv instanceof FeatureVariable);

		assertEquals("Abis", fv.getFtName());
	}

	@Test
	public void testRenameRootTrue2() throws Exception {

		final String newName = "Abis";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature A as \"" + newName + "\""); // non explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		FeatureVariable fv = fmv.root();

		assertNotNull(fv);
		assertTrue(fv instanceof FeatureVariable);

		assertEquals("Abis", fv.getFtName());
	}

	@Test
	public void testRenameRootFalse1() throws Exception {

		final String newName = "Abis";
		final String original = "A";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature fm1.Abis as \"" + newName + "\"");

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		FeatureVariable fv = fmv.root();

		assertNotNull(fv);
		assertTrue(fv instanceof FeatureVariable);

		assertEquals(original, fv.getFtName()); // no effect
	}

	@Test
	public void testRenameRootFalse2() throws Exception {

		final String newName = "Abis";
		final String original = "A";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature Abis as \"" + newName + "\""); // non
																	// explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		FeatureVariable fv = fmv.root();

		assertNotNull(fv);
		assertTrue(fv instanceof FeatureVariable);

		assertEquals(original, fv.getFtName()); // no effect
	}

	@Test
	public void testRenameTrue1() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature fm1.D as \"" + newName + "\""); // explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		Variable v2 = _environment.getVariable("fm1");
		assertNotNull(v2);
		assertTrue(v2 instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v2;
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(newName, fts)); // side effect
		assertFalse(FMLTest.isContained(original, fts)); // side effect
	}

	@Test
	public void testRenameTrue2() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature D as \"" + newName + "\""); // non explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(newName, fts)); // side effect
		assertFalse(FMLTest.isContained(original, fts)); // side effect
	}

	@Test
	public void testRenameFalse1() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature fm1.DDDD as \"" + newName + "\""); // explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(original, fts)); // no effect
		assertFalse(FMLTest.isContained(newName, fts)); // no effect

	}

	@Test
	public void testRenameFalse2() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = renameFeature DDDD as \"" + newName + "\""); // non
																	// explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(original, fts)); // no effect
		assertFalse(FMLTest.isContained(newName, fts)); // no effect
	}

	@Test
	public void testRenameConstraints1() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D -> C ; )"
				+ "b = renameFeature fm1.D as \"" + newName + "\""); // explicit
																		// and
																		// does
																		// exist

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertTrue(FMLTest.isContained(newName, fts)); // side effect

		Set<Expression<String>> constraints = fmv.getFm().getConstraints();

		assertTrue(FMLTest.inExpressions(newName, constraints)); // side effect
		assertFalse(FMLTest.inExpressions(original, constraints)); // side
																	// effect
	}

	@Test
	public void testRenameConstraints2() throws Exception {

		final String newName = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D -> C ; )"
				+ "b = renameFeature fm1." + newName + " as \"" + newName
				+ "\""); // explicit but does not exist!

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(original, fts)); // no side effect
		assertFalse(FMLTest.isContained(newName, fts)); // no side effect

		Set<Expression<String>> constraints = fmv.getFm().getConstraints();

		assertFalse(FMLTest.inExpressions(newName, constraints)); // no side
																	// effect
		assertTrue(FMLTest.inExpressions(original, constraints)); // no side
																	// effect
	}

	@Test
	public void testRenameConstraints3() throws Exception {

		final String newName = "Gprime";
		final String original = "G";
		_shell.parse("fm1 = FM (A: B C [D]; D : (E|F|G) ; G -> !C ; (!G | F) -> B; )"
				+ "b = renameFeature fm1."
				+ original
				+ " as \""
				+ newName
				+ "\""); // explicit but does not exist!

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertTrue(FMLTest.isContained(newName, fts)); // side effect

		Set<Expression<String>> constraints = fmv.getFm().getConstraints();
		assertEquals(2, constraints.size());
		assertTrue(FMLTest.inExpressions(newName, constraints)); // side effect
		assertFalse(FMLTest.inExpressions(original, constraints)); // side
																	// effect

		String expected = "A: B C [D]; D : (E|F|G) ; G -> !C ; (!G | F) -> B;"
				.replace(original, newName);

		assertEquals(FMBuilder.getInternalFM(expected), fmv.getFm());

	}

	@Test
	public void testFMLRename1() throws Exception {

		_shell.parse("// testing 'renameFeature' operation\n"
				+ "\n"
				+ "// syntacs: b = renameFeature ft as ftnew\n"
				+ " \n"
				+ "// ft is a feature\n"
				+ " \n"
				+ "// ftnew is a string\n"
				+ "\n"
				+ "// b is a boolean value:\n"
				+ " \n"
				+ "// true if the renaming can be performed (ft does exist, ftnew does not exist)\n"
				+ "\n"
				+ "// false otherwise (and the feature model is not modified)\n"
				+ "\n"
				+ "\n"
				+ "fm1 = FM (A: B [C] D; D : (E|F)+; F : (I|J|K); E : [Z]; )\n"
				+ "fm1bis = copy fm1 // save the original version\n"
				+ "\n"
				+ "\n"
				+ "renameFeature fm1.B as \"Bbis\"\n"
				+ "bExist = isExisting fm1.B // B does not exist anymore\n"
				+ "\n"
				+ "FALSE = false\n"
				+ "assert (bExist eq FALSE)\n"
				+ "bBisExist = isExisting fm1.Bbis // Bbis does exist\n"
				+ "\n"
				+ "TRUE = true \n"
				+ "assert (bBisExist eq TRUE)\n"
				+ "\n"
				+ "renameFeature Bbis as \"B\" // come back to the original version\n"
				+ "\n"
				+ "assert (fm1 eq fm1bis)\n"
				+ "\n"
				+ "ibis = \"Ibis\"\n"
				+ "jbis = \"Jbis\"\n"
				+ "kbis = \"Kbis\"\n"
				+ "renameFeature fm1bis.I as ibis\n"
				+ "renameFeature fm1bis.J as jbis\n"
				+ "renameFeature fm1bis.K as kbis\n"
				+ "\n"
				+ "// checking that fm1bis is actually modified\n"
				+ "\n"
				+ "chF = children fm1bis.F\n"
				+ "chStrF = setEmpty\n"
				+ "foreach (ch in chF) do \n"
				+ "	chStr = name ch \n"
				+ "	setAdd chStrF chStr \n"
				+ "end\n"
				+ "chStrFExpected = setEmpty\n"
				+ "setAdd chStrFExpected ibis\n"
				+ "setAdd chStrFExpected jbis\n"
				+ "setAdd chStrFExpected kbis\n"
				+ "assert (chStrFExpected eq chStrF)\n"
				+ "\n"
				+ "fm1bisbis = copy fm1\n"
				+ "b = renameFeature fm1.D as \"C\" // C already exists!\n"
				+ "\n"
				+ "assert (b eq FALSE)\n"
				+ "assert (fm1bisbis eq fm1) // fm1 is not modified\n"
				+ "\n"
				+ "\n"
				+ "b2 = renameFeature fm1.U as \"Y\" // U does not exist!\n"
				+ "\n"
				+ "assert (b2 eq FALSE) \n"
				+ "assert (fm1bisbis eq fm1) // fm1 is not modified\n"
				+ "\n"
				+ "\n"
				+ "b3 = renameFeature B as \"Y\" // B is ambigous (fm1, fm1bis, fm1bisbis?)\n"
				+ "\n" + "assert (b3 eq FALSE)\n" + " \n" + "");

	}

}
