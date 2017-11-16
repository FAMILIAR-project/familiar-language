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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
@Ignore
public class FMLIsValid2Test extends FMLTest {

	@Test
	public void testIsValid1() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints (!B | !C ;)\n"
				+ "bf1 = isValid fm1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(false, bv.isTrue());
		assertEquals(fm1.isValid(), bv.isTrue());

		assertEquals(fm1.counting(), 0, 0); // non valid

	}

	@Test
	public void testIsValid2() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D] ; (!B | !C) ;)\n"
				+ "bf1 = isValid fm1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(false, bv.isTrue());
		assertEquals(fm1.isValid(), bv.isTrue());

		assertEquals(fm1.counting(), 0, 0); // non valid

	}

	@Test
	public void testIsValid3() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((!B -> !C) ;)\n"
				+ "bf1 = isValid fm1\n");

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(true, bv.isTrue());
		assertEquals(fm1.isValid(), bv.isTrue());

		assertTrue(fm1.counting() > 0); // valid

	}

	@Test
	public void testIsValid4() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D] ; (!B -> !C) ;)\n"
				+ "bf1 = isValid fm1\n"); // internal constraints rather than
											// mapping

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(fm1.isValid(), bv.isTrue());
		assertEquals(2, fm1.counting(), 0); // valid
		assertEquals(true, bv.isTrue());

	}

	@Test
	public void testIsValid5() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D] ; (B | !C) ;)\n"
				+ "bf1 = isValid fm1\n");

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(fm1.isValid(), bv.isTrue());
		assertEquals(2, fm1.counting(), 0); // valid
		assertEquals(true, bv.isTrue());

	}

	/**
	 * A misc test (involving FML operations like isValid, counting, configs,
	 * etc.)
	 * 
	 * @throws Exception
	 */
	@Test
	public void testIsValid6() throws Exception {
		_shell.parse("fm1 = FM (A : (B|C); B : D; C -> D ;)\n"
				+ "bf1 = isValid fm1\n" + "c = configuration fm1\n"
				+ "nexpected = counting fm1\n" + "s = configs fm1\n");

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bv = (BooleanVariable) v2;

		assertEquals(fm1.isValid(), bv.isTrue());
		assertEquals(true, bv.isTrue());

		Variable v3 = _environment.getVariable("s");
		assertNotNull(v3);
		assertTrue(v3 instanceof SetVariable);
		SetVariable s = (SetVariable) v3;

		int n = s.size();

		Variable v4 = _environment.getVariable("nexpected");
		assertNotNull(v4);
		assertTrue(v4 instanceof IntegerVariable);
		IntegerVariable nexpected = (IntegerVariable) v4;

		assertEquals(nexpected.getV(), n);
		assertEquals(fm1.counting(), n, 0);
		assertEquals(fm1.counting(), nexpected.getV(), 0);

	}

	@Test
	public void testIsValid7() throws Exception {
		// two FMs with same name should not disturb BDD operations like
		// counting or isValid
		_shell.parse("fm1 = FM (A : B C [D] ; (!B -> !C) ;)\n"
				+ "fm2 = FM (A : B C [D] ; (!B | !C) ;)\n"
				+ "bf1 = isValid fm1\n" + "bf2 = isValid fm2\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		Variable v2 = _environment.getVariable("bf1");
		assertNotNull(v2);
		assertTrue(v2 instanceof BooleanVariable);
		BooleanVariable bf1 = (BooleanVariable) v2;

		assertEquals(fm1.isValid(), bf1.isTrue());
		assertEquals(2, fm1.counting(), 0); // valid
		assertEquals(true, bf1.isTrue());

		Variable v3 = _environment.getVariable("fm2");
		assertNotNull(v3);
		assertTrue(v3 instanceof FeatureModelVariable);
		FeatureModelVariable fm2 = (FeatureModelVariable) v3;

		Variable v4 = _environment.getVariable("bf2");
		assertNotNull(v4);
		assertTrue(v4 instanceof BooleanVariable);
		BooleanVariable bf2 = (BooleanVariable) v4;

		assertEquals(fm2.isValid(), bf2.isTrue());
		assertEquals(0, fm2.counting(), 0); // non valid
		assertEquals(false, bf2.isTrue());

	}

	@Test
	public void testFMLisValid1() throws Exception {

		_shell.parse("fm1 = FM (A: B C ; B : (D|E); C -> D; ) // valid\n"
				+ "\n"
				+ "validfm1 = isValid fm1\n"
				+ "assert (validfm1)\n"
				+ "c1 = configuration fm1\n"
				+ "bc1 = isValid c1\n"
				+ "assert (bc1)\n"
				+ "\n"
				+ "fm2 = FM (A: B C ; B : (D|E); !C | !D; !C | !E; ) // not valid\n"
				+ "\n" + "validfm2 = isValid fm2\n" + "FALSE = false\n"
				+ "assert (validfm2 eq FALSE)");

	}

	public void testFMLisValid1bis() throws Exception {

		_shell.parse("fm1 = FM (A: B C ; B : (D|E); !C -> !D; !C -> !E; ) // valid actually\n"
				+ "\n" + "validfm1 = isValid fm1\n" + "assert (validfm1)");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertTrue(fm1.configs().size() > 0);

	}

	@Test
	public void testFMLisValid2() throws Exception {

		_shell.parse("fm1 = FM (A: B C ; B : (D|E); C -> D; ) // valid\n"
				+ "assert (isValid fm1)\n"
				+ "c1 = configuration fm1\n"
				+ "assert (isValid c1)\n"
				+ "\n"
				+ "fm2 = FM (A: B C ; B : (D|E); !C | !D; !C | !E; ) // not valid\n"
				+ "assert (not (isValid fm2))");

	}

}
