/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fml.FeatureEdgeKind;

import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.VariabilityOperatorVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.Requires;

/**
 * @author mathieuacher
 * 
 */
public class FMLRemoveFeatureTest extends FMLTest {

	@Test
	public void testRemoveTrue1() throws Exception {

		final String original = "C";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = removeFeature fm1." + original); // explicit

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
	}

	@Test
	public void testRemoveTrue2() throws Exception {

		final String original = "D";
		String child1 = "H";
		String child2 = "I";
		String child3 = "J";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = removeFeature fm1." + original); // explicit and
														// recursively

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertFalse(FMLTest.isContained(child1, fts));
		assertFalse(FMLTest.isContained(child2, fts));
		assertFalse(FMLTest.isContained(child3, fts));
	}

	@Test
	public void testRemoveTrue3() throws Exception {

		final String original = "D";
		String child1 = "H";
		String child2 = "I";
		String child3 = "J";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J)+ ; )"
				+ "b = removeFeature fm1." + original); // explicit and
														// recursively
														// (Or-group)

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertFalse(FMLTest.isContained(child1, fts));
		assertFalse(FMLTest.isContained(child2, fts));
		assertFalse(FMLTest.isContained(child3, fts));
	}

	@Test
	public void testRemoveTrue4() throws Exception {

		final String original = "J";
		final String child1 = "H";
		final String child2 = "I";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )\n"
				+ "b = removeFeature fm1." + original + "\n"
				+ "op1 = operator fm1." + child1 + "\n" + "op2 = operator fm1."
				+ child2 + "\n"); // explicit and recursively (Or-group)

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		System.out.println("fm1=" + fmv.getFm());
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect

		// still here
		assertTrue(FMLTest.isContained(child1, fts));
		assertTrue(FMLTest.isContained(child2, fts));

		// check the variability operators
		Variable v3 = _environment.getVariable("op1");
		assertNotNull(v3);
		assertTrue(v3 instanceof VariabilityOperatorVariable);

		VariabilityOperatorVariable vop1 = (VariabilityOperatorVariable) v3;
		assertEquals(FeatureEdgeKind.ALTERNATIVE, vop1.getFek());

		Variable v4 = _environment.getVariable("op2");
		assertNotNull(v4);
		assertTrue(v4 instanceof VariabilityOperatorVariable);

		VariabilityOperatorVariable vop2 = (VariabilityOperatorVariable) v4;
		assertEquals(FeatureEdgeKind.ALTERNATIVE, vop2.getFek());

	}

	@Test
	public void testRemoveTrue5() throws Exception {

		final String original = "J";
		final String child1 = "H";
		final String child2 = "I";

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J)+ ; )\n"
				+ "b = removeFeature fm1." + original + "\n"
				+ "op1 = operator fm1." + child1 + "\n" + "op2 = operator fm1."
				+ child2 + "\n"); // explicit and recursively (Or-group)

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertTrue(bv.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		System.out.println("fm1=" + fmv.getFm());
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect

		// still here
		assertTrue(FMLTest.isContained(child1, fts));
		assertTrue(FMLTest.isContained(child2, fts));

		// check the variability operators
		Variable v3 = _environment.getVariable("op1");
		assertNotNull(v3);
		assertTrue(v3 instanceof VariabilityOperatorVariable);

		VariabilityOperatorVariable vop1 = (VariabilityOperatorVariable) v3;
		assertEquals(FeatureEdgeKind.OR, vop1.getFek());

		Variable v4 = _environment.getVariable("op2");
		assertNotNull(v4);
		assertTrue(v4 instanceof VariabilityOperatorVariable);

		VariabilityOperatorVariable vop2 = (VariabilityOperatorVariable) v4;
		assertEquals(FeatureEdgeKind.OR, vop2.getFek());

	}

	@Test
	public void testRemoveTrue6() throws Exception {

		final String original = "J";
		final String original2 = "H";
		final String child1 = "I"; // keep only I in the alternative group

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )\n"
				+ "b = removeFeature fm1." + original + "\n"
				+ "b2 = removeFeature fm1." + original2 + "\n"
				+ "op1 = operator fm1." + child1 + "\n"

		); // explicit and recursively (Or-group)

		Variable v0 = _environment.getVariable("b");
		assertNotNull(v0);
		assertTrue(v0 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v0;
		assertTrue(bv.isTrue());

		Variable v1 = _environment.getVariable("b2");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv2 = (BooleanVariable) v1;
		assertTrue(bv2.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		System.out.println("fm1=" + fmv.getFm());
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertFalse(FMLTest.isContained(original2, fts)); // side effect

		// still here
		assertTrue(FMLTest.isContained(child1, fts));

		// check the variability operators
		Variable v3 = _environment.getVariable("op1");
		assertNotNull(v3);
		assertTrue(v3 instanceof VariabilityOperatorVariable);

		// when only one feature is present in an alternative group, it should
		// be mandatory
		VariabilityOperatorVariable vop1 = (VariabilityOperatorVariable) v3;
		assertEquals(FeatureEdgeKind.MANDATORY, vop1.getFek());
	}

	@Test
	public void testRemoveTrue7() throws Exception {

		final String original = "J";
		final String original2 = "H";
		final String child1 = "I"; // keep only I in the alternative group

		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J)+ ; )\n"
				+ "b = removeFeature fm1." + original + "\n"
				+ "b2 = removeFeature fm1." + original2 + "\n"
				+ "op1 = operator fm1." + child1 + "\n"

		); // explicit and recursively (Or-group)

		Variable v0 = _environment.getVariable("b");
		assertNotNull(v0);
		assertTrue(v0 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v0;
		assertTrue(bv.isTrue());

		Variable v1 = _environment.getVariable("b2");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv2 = (BooleanVariable) v1;
		assertTrue(bv2.isTrue());

		FeatureModelVariable fmv = getFMVariable("fm1");
		System.out.println("fm1=" + fmv.getFm());
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect
		assertFalse(FMLTest.isContained(original2, fts)); // side effect

		// still here
		assertTrue(FMLTest.isContained(child1, fts));

		// check the variability operators
		Variable v3 = _environment.getVariable("op1");
		assertNotNull(v3);
		assertTrue(v3 instanceof VariabilityOperatorVariable);

		// when only one feature is present in an Or-group, it should be
		// mandatory
		VariabilityOperatorVariable vop1 = (VariabilityOperatorVariable) v3;
		assertEquals(FeatureEdgeKind.MANDATORY, vop1.getFek());
	}

	@Test
	public void testRemoveFalse1() throws Exception {

		final String attempt = "Dbis";
		final String original = "D";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = removeFeature fm1." + attempt); // does not exist

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		Variable v2 = _environment.getVariable("fm1");
		assertNotNull(v2);
		assertTrue(v2 instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v2;
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(original, fts)); // no effect (still
														// here)
		assertFalse(FMLTest.isContained(attempt, fts)); // no effect

	}

	@Test
	public void testRemoveRoot1() throws Exception {

		final String attempt = "A";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = removeFeature fm1." + attempt); // does not exist

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(attempt, fts)); // no effect

		// should return false (it has no sense to remove the root from a
		// feature model)
		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

	}

	@Test
	public void testRemoveRoot2() throws Exception {

		final String attempt = "A";
		_shell.parse("fm1 = FM (A: B C [D]; D : (H|I|J) ; )"
				+ "b = removeFeature fm1." + attempt); // does not exist

		Variable v1 = _environment.getVariable("b");
		assertNotNull(v1);
		assertTrue(v1 instanceof BooleanVariable);

		FeatureModelVariable fmv = getFMVariable("fm1");
		SetVariable fts = fmv.features();
		assertNotNull(fts);
		assertTrue(FMLTest.isContained(attempt, fts)); // no effect

		// should return false (it has no sense to remove the root from a
		// feature model)
		BooleanVariable bv = (BooleanVariable) v1;
		assertFalse(bv.isTrue());

		assertEquals(fts.getVars().size(), 7);

	}

	@Test
	public void testRemoveWithConstraints1() throws Exception {

		final String original = "D";

		_shell.parse("fm1 = FM (A: B C [D]; (D -> C) ; )\n"
				+ "b = removeFeature fm1." + original + "\n"); // explicit and
																// should
																// transform D
																// -> C as false
																// -> C

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);

		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		System.out.println("fm1=" + fm1.getFm());
		SetVariable fts = fm1.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect

		Variable v2 = _environment.getVariable("b");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);

		BooleanVariable b = (BooleanVariable) v2;
		assertTrue(b.isTrue());

		// when only one feature is present in an Or-group, it should be
		// mandatory
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertEquals(1, constraints.size());

		Expression<String> constraint = constraints.iterator().next(); // first
																		// one
		assertEquals(new Requires<String>(
				FeatureGraphFactory.DEFAULT_BOTTOM_STRING, "C").toString(),
				constraint.toString());

	}

	@Test
	public void testRemoveWithConstraints2() throws Exception {

		final String original = "D";

		_shell.parse("fm1 = FM (A: B C [D]; (D | C) ; (A -> !D) ; )\n"
				+ "b = removeFeature fm1." + original + "\n"); // explicit and
																// should
																// transform D
																// -> C as false
																// -> C

		FeatureModelVariable fm1 = getFMVariable("fm1");
		System.out.println("fm1=" + fm1.getFm());
		SetVariable fts = fm1.features();
		assertNotNull(fts);
		assertFalse(FMLTest.isContained(original, fts)); // side effect

		Variable v2 = _environment.getVariable("b");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);

		BooleanVariable b = (BooleanVariable) v2;
		assertTrue(b.isTrue());

		// when only one feature is present in an Or-group, it should be
		// mandatory
		Set<Expression<String>> constraints = fm1.getFm().getConstraints();
		assertEquals(2, constraints.size());
		
		Set<Expression<String>> expectedConstraints = new HashSet<Expression<String>>() ;
		Expression<String> expected1 = new Expression<String>(ExpressionType.IMPLIES,
				new Expression<String>("A"), new Expression<String>(
						ExpressionType.NOT, new Expression<String>(
								ExpressionType.FALSE), null)) ; 


			
		Expression<String> expected2 = new Expression<String>(ExpressionType.OR,
				new Expression<String>(ExpressionType.FALSE),
				new Expression<String>("C")) ;

		expectedConstraints.add(expected1);
		expectedConstraints.add(expected2);
		
		assertMySetInclusion(expectedConstraints, constraints);
		//assertMySetInclusion(constraints, expectedConstraints);
	}

	@Test
	public void testFMLRemove1() throws Exception {

		_shell.parse("// testing 'removeFeature' operation\n"
				+ "\n"
				+ "// syntacs: b = removeFeature ft \n"
				+ " \n"
				+ "// ft is a feature\n"
				+ " \n"
				+ "// b is a boolean value:\n"
				+ " \n"
				+ "// true if the removal can be performed (ft does exist)\n"
				+ "\n"
				+ "// false otherwise (and the feature model is not modified)\n"
				+ "\n"
				+ "\n"
				+ "fm1 = FM (A: B [C] D; D : (E|F); F : (I|J|K); E : [Z]; )\n"
				+ "fm1bis = copy fm1 // save the original version\n"
				+ "\n"
				+ "\n"
				+ "b0 = removeFeature fm1.B \n"
				+ "TRUE = true\n"
				+ "assert (b0 eq TRUE)\n"
				+ "bExist = isExisting fm1.B // B does not exist anymore\n"
				+ "\n"
				+ "FALSE = false\n"
				+ "assert (bExist eq FALSE)\n"
				+ "\n"
				+ "b1 = removeFeature B // fm1bis.B (non ambigous)\n"
				+ "\n"
				+ "assert (b1 eq TRUE)\n"
				+ "assert (fm1 eq fm1bis)\n"
				+ "\n"
				+ "b2 = removeFeature fm1.B // does not exist\n"
				+ "\n"
				+ "assert (b2 eq FALSE)\n"
				+ "\n"
				+ "b3 = removeFeature fm1bis.B // does not exist\n"
				+ "\n"
				+ "assert (b3 eq FALSE)\n"
				+ "\n"
				+ "b4 = removeFeature fm1.D // D, E, F, I, J, K, Z are removed\n"
				+ "\n" + "assert (b4 eq TRUE) \n"
				+ "fm1Expected = FM (A: [C];)\n"
				+ "assert (fm1 eq fm1Expected)");

	}

	/**
	 * @param lrequires
	 *            a require expression
	 * @param rexpression
	 *            an expression Determines if two require-like expressions are
	 *            equivalent TODO: difficult to compare with false/true
	 */
	private void assertRequiresEquals(Requires<String> lrequires,
			Expression<String> rexpression) {
		boolean b1 = (rexpression instanceof Requires);
		assertEquals(true, b1);

		Requires<String> rrequires = (Requires<String>) rexpression;

		assertEquals(lrequires.getAntecedent(), rrequires.getAntecedent());
		assertEquals(lrequires.getConsequent(), rrequires.getConsequent());

	}

}
