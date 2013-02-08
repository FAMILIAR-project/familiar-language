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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;

import fr.unice.polytech.modalis.familiar.interpreter.VariableNotExistingException;
import fr.unice.polytech.modalis.familiar.parser.MyExpressionParser;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.VariabilityOperatorVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.Excludes;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher
 * 
 */
public class FMLFMBuilderTest extends FMLTest {

	@Test
	public void testORGroup1() throws Exception {

		// _shell.setVerbose(true);
		_shell.parse("fm1 = FM (G : (H|I|J)+ ; )" + "op = operator fm1.H");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue("v2=" + v2, v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.OR);

	}

	@Test
	public void testORGroup2() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J)+ ; )"
				+ "op = operator fm1.H");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue(v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.OR);

	}

	@Test
	public void testORGroup3() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; C : (X|Y|Z); Y : (H|I|J)+ ; )"
				+ "op = operator fm1.J");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue(v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.OR);

	}

	@Test
	public void testXORGroup1() throws Exception {

		_shell.parse("fm1 = FM (G : (H|I|J) ; )" + "op = operator fm1.I");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue(v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.ALTERNATIVE);

	}

	@Test
	public void testXORGroup2() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "op = operator fm1.H");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue(v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.ALTERNATIVE);

	}

	@Test
	public void testXORGroup3() throws Exception {

		_shell.parse("fm1 = FM (A: B C [D]; C : (X|Y|Z); Y : (H|I|J) ; )"
				+ "op = operator fm1.J");
		Variable v = _environment.getVariable("fm1");
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		Variable v2 = _environment.getVariable("op");
		assertNotNull(v2);
		assertTrue(v2 instanceof VariabilityOperatorVariable);
		VariabilityOperatorVariable op = (VariabilityOperatorVariable) v2;
		assertEquals(op.getFek(), FeatureEdgeKind.ALTERNATIVE);

	}

	@Test
	public void testConstraints1() throws Exception {

		String cst = "(B -> !E) ; (C | E); ((B & C) -> D);";
		_shell.parse("fm1 = FM (A: B C [D] [E]; " + cst + " )\n"
				+ "cst1 = constraints fm1");

		FeatureModelVariable fv = getFMVariable("fm1");
		// Variable v2 = _environment.getVariable("cst1");
		// assertNotNull(v2);
		// assertTrue (v2 instanceof ConstraintsVariable);
		// ConstraintsVariable cst1 = (ConstraintsVariable) v2 ;
		Set<Expression<String>> expected = parseExpression(cst);
		assertMySetInclusion(expected, fv.getFm().getConstraints());
		assertMySetInclusion(fv.getFm().getConstraints(), expected);
		int i = 0;
		for (Expression<String> e : expected) {
			System.out.println("e" + i++ + "=" + e.toString());
		}

	}

	@Test
	public void testConstraints2() throws Exception {

		Expression<String> cst1 = MyExpressionParser.parseString("F -> !E");
		Expression<String> cst2 = MyExpressionParser.parseString("F -> !E");
		assertEquals(cst1, cst2);
		
		Excludes<String> cst11 = new Excludes<String>("F", "E") ;
		Excludes<String> cst21 = new Excludes<String>("F", "E") ;
		assertEquals(cst11, cst21);
		
		String cst = "(F -> !E); (A -> F);"; // issue with (F | !A)
		_shell.parse("fm1 = FM (A: B C [E] [F] ; " + cst + " )\n"
				+ "cst1 = constraints fm1");

		FeatureModelVariable fv = getFMVariable("fm1");
		// Variable v2 = _environment.getVariable("cst1");
		// assertNotNull(v2);
		// assertTrue (v2 instanceof ConstraintsVariable);
		// ConstraintsVariable cst1 = (ConstraintsVariable) v2 ;
		Set<Expression<String>> expected = parseExpression(cst);
		assertMySetInclusion(expected, fv.getFm().getConstraints());
		assertMySetInclusion(fv.getFm().getConstraints(), expected);
		
			
		
		int i = 0;
		for (Expression<String> e : expected) {
			System.out.println("e" + i++ + "=" + e.toString());
		}

	}

	

	@Test
	public void testConstraints3() throws Exception {

		String cst = "(B -> (C|D)) & (!(C&D)) ;";
		_shell.parse("fm1 = " + "FM (A:[B] [C] [D]; " + cst + " )\n");

		FeatureModelVariable fv = getFMVariable("fm1");

		Set<Expression<String>> expected = parseExpression(cst);
		assertEquals(expected, fv.getFm().getConstraints());
		int i = 0;
		for (Expression<String> e : expected) {
			System.out.println("e" + i++ + "=" + e.toString());
		}

	}

	@Test
	public void testConstraints4() throws Exception {

		String cst = "B -> (C|D) & !(C&D) ;";
		_shell.parse("fm1 = " + "FM (A:[B] [C] [D]; " + cst + " )\n");

		FeatureModelVariable fv = getFMVariable("fm1");

		Set<Expression<String>> expected = parseExpression(cst);
		assertEquals(expected, fv.getFm().getConstraints());
		int i = 0;
		for (Expression<String> e : expected) {
			System.out.println("e" + i++ + "=" + e.toString());
		}

	}

	@Test
	public void testConstraints5() throws Exception {

		String cst = "B -> (C|D) & !(C&D) ;";
		String cst2 = "B implies (C or D) and !(C&D) ;";
		_shell.parse("fm1 = " + "FM (A: [B] [C] [D]; " + cst2 + " )\n");

		FeatureModelVariable fv = getFMVariable("fm1");

		Set<Expression<String>> expected = parseExpression(cst);
		assertEquals(expected, fv.getFm().getConstraints());
		int i = 0;
		for (Expression<String> e : expected) {
			System.out.println("e" + i++ + "=" + e.toString());
		}

	}

	@Test
	public void testConstraints6() throws Exception {

		String cst = "B implies !C ;";
		_shell.parse("fm1 = " + "FM (A: B C [D]; " + cst + " )\n");
		FeatureModelVariable fv = getFMVariable("fm1");
		assertFalse("feature model is not valid", fv.isValid());

	}

	@Test
	public void testNotWellFormed1() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : B A ;)"; // features with same name
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed2() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : A ; )"; // A feature appears twice
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed3() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : A ; Z : X Y ; )"; // Z has no parent!
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed4() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : D [E] F ; F : (B|H|I) ; )"; // B appears
																	// twice
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed5() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : D [E] F ; F : (C|H|I)+ ; )"; // C appears
																	// twice
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed6() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : D [E] [F] B ; )"; // B appears twice
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	@Test
	public void testNotWellFormed7() throws Exception {

		_shell.setVerbose(true);
		String fm = "FM (A : B C ; C : D [E] [F] ; F : (G|H|I); I : Z B ; )"; // B
																				// appears
																				// twice
		_shell.parse("fm1 = " + fm + "\n");

		assertTrue(_shell.hasErrors());
		// should compile with errors
		Variable fm1 = null;
		try {
			fm1 = _environment.getVariable("fm1");
		} catch (Exception e) {
			assertNull(fm1);
			assertNotNull(e);
			assertTrue(e instanceof VariableNotExistingException);
		}

	}

	private static Set<Expression<String>> parseExpression(String expr) {
		Set<Expression<String>> r = new HashSet<Expression<String>>() ;
		String[] exprs = expr.split(";");
		for (String e : exprs) {
			r.add(MyExpressionParser.parseString(e));
		}
		
		return r;
	}

}
