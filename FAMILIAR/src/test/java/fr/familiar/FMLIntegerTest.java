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

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.variable.IntegerVariable;

/**
 * @author mathieuacher TODO: grammar issues with * (STAR and MULT) Currently,
 *         you have to write 2 mult 4 or 2mult4 but not 2 * 4 or 2*4 due to the
 *         * used in fm1.* or ns*
 */

public class FMLIntegerTest extends FMLTest {

	@Test
	public void testInteger1() throws Exception {

		_shell.parse("a = 2 + 4\n" + "b = 2 - 4\n" + "c = a + b\n"
				+ "d = 4 - 2\n" + "e = d + b\n" + "f = 78 mult d\n"
				+ "g = f / 78\n" + "h = 2 ^ 4\n");

		IntegerVariable a = getIntegerVariable("a");
		assertEquals(6, a.getV());

		IntegerVariable b = getIntegerVariable("b");
		assertEquals(-2, b.getV());

		IntegerVariable c = getIntegerVariable("c");
		assertEquals(a.getV() + b.getV(), c.getV());
		assertEquals(4, c.getV());

		IntegerVariable d = getIntegerVariable("d");
		assertEquals(2, d.getV());

		IntegerVariable e = getIntegerVariable("e");
		assertEquals(d.getV() + b.getV(), e.getV());
		assertEquals(0, e.getV());

		IntegerVariable f = getIntegerVariable("f");
		assertEquals(d.getV() * 78, f.getV());

		IntegerVariable g = getIntegerVariable("g");
		assertEquals(f.getV() / 78, g.getV());

		IntegerVariable h = getIntegerVariable("h");
		assertEquals(16, h.getV());

	}

	@Test
	public void testInteger2() throws Exception {

		_shell.parse("a = (4 + 4) + 4\n" + "a2 = (4 - 4) + 4\n"
				+ "b = (4 - 4) - 4\n" + "b2 = (4 + 4) - 4\n"
				+ "c = (4 mult 3) mult b2\n");

		IntegerVariable a = getIntegerVariable("a");
		assertEquals(12, a.getV());

		IntegerVariable a2 = getIntegerVariable("a2");
		assertEquals(4, a2.getV());

		IntegerVariable b = getIntegerVariable("b");
		assertEquals(-4, b.getV());

		IntegerVariable b2 = getIntegerVariable("b2");
		assertEquals(4, b2.getV());

		IntegerVariable c = getIntegerVariable("c");
		assertEquals(4 * 3 * b2.getV(), c.getV());

	}
	
	/**
	 * the test does not pass if executed with others (but works individually / for all tests of the class)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testInteger3() throws Exception {

		_shell.parse("a = counting FM (A : [B] [C] D ; ) - 4\n"
				+ "b = size { \"a\" \"b\" \"c\" \"d\" } / 4\n"
				+ "c = counting FM (A : [B] D ; ) mult 4\n");

		IntegerVariable a = getIntegerVariable("a");
		assertEquals(4 - 4, a.getV());

		IntegerVariable b = getIntegerVariable("b");
		assertEquals(4 / 4, b.getV());

		IntegerVariable c = getIntegerVariable("c");
		assertEquals(2 * 4, c.getV());

	}

}
