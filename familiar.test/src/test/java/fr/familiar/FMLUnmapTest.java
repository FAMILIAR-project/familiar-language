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
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.familiar.parser.MyExpressionParser;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLUnmapTest extends FMLTest {

	@Test
	public void testUnMap1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((!B | !C) ;)\n" // non valid
				+ "cfm1 = constraints fm1\n" + "unmap fm1" // fm1 becomes valid
		);

		SetVariable cfm1 = getSetVariable("cfm1");

		assertEquals(1, cfm1.size());
		ConstraintVariable cst1 = (ConstraintVariable) cfm1.getVars().iterator().next() ; 
		assertEquals(MyExpressionParser.parseString("B -> !C"), cst1.getConstraint());

		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertTrue("No more constraints",
				fm1.getFm().getConstraints().size() == 0);
		assertTrue("fm1 is actually valid!", fm1.isValid());

	}

	@Test
	public void testUnMap2() throws Exception {

		_shell.parse("fm1 = FM (A : B C [D] ; )\n"
				+ "map fm1 with constraints ((!B | !C) ;)\n" // non valid
		);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertTrue("Some constraints", fm1.getFm().getConstraints().size() == 1);
		assertFalse("fm1 is actually non valid!", fm1.isValid());
		assertTrue("fm1 is actually non valid!", fm1.counting() == 0);

		_shell.parse("cfm1 = constraints fm1\n" + "unmap fm1" // fm1 becomes
																// valid
		);

		SetVariable cfm1 = getSetVariable("cfm1");

		assertEquals(1, cfm1.size());
		ConstraintVariable cst1 = (ConstraintVariable) cfm1.getVars().iterator().next() ; 
		assertEquals(MyExpressionParser.parseString("(B -> !C)"), cst1.getConstraint());


		fm1 = getFMVariable("fm1");
		assertTrue("No more constraints",
				fm1.getFm().getConstraints().size() == 0);
		assertTrue("fm1 is actually valid!", fm1.isValid());
		assertTrue("fm1 is actually valid!", fm1.counting() == 2);

	}

	@Test
	public void testUnMap3() throws Exception {

		_shell.parse("fm1 = FM (A : [B] [C] [D] ; )\n" + "fm2 = copy fm1\n"
				+ "map fm1 with constraints ((!B | !C) ;)\n" // valid
				+ "c1 = counting fm1\n" + "fm3 = copy fm1\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertTrue("Some constraints", fm1.getFm().getConstraints().size() == 1);
		assertTrue("fm1 is actually non valid!", fm1.isValid());
		double beforeFM1 = fm1.counting();
		assertTrue("fm1 is actually non valid!", fm1.counting() > 0);
		assertEquals(getIntegerVariable("c1").getV(), beforeFM1, 0);

		System.err.println("beforeFM1=" + beforeFM1);

		_shell.parse("cfm1 = constraints fm1\n" + "unmap fm1\n" // fm1 has less
																// configurations
																// -- equals to
																// its origin,
																// fm2
				+ "cmp12 = compare fm1 fm2\n" + "cmp13 = compare fm1 fm3");

		FeatureModelVariable fm2 = getFMVariable("fm2");
		fm1 = getFMVariable("fm1");
		double afterFM1 = fm1.counting();
		assertTrue("No more constraints",
				fm1.getFm().getConstraints().size() == 0);
		assertTrue("fm1 has less configurations", afterFM1 >= beforeFM1);
		assertFMsEqual(fm2.getFm(), fm1.getFm());

		// assertArbitrary("fm1", "fm2");

		/*
		 * System.err.println("#fm1=" + getFMVariable("fm1").counting());
		 * System.err.println("#fm2=" + getFMVariable("fm2").counting());
		 * System.err.println("#fm3=" + getFMVariable("fm3").counting());
		 */

		StringVariable cmp12 = getStringVariable("cmp12");
		assertEquals("REFACTORING", cmp12.getVal());

		StringVariable cmp13 = getStringVariable("cmp13");
		assertEquals("GENERALIZATION", cmp13.getVal());

	}

}
