/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FMLBooleanTest extends FMLTest {

	@Test
	public void testBool1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("a = true && false\n" + "b = true || false\n"
				+ "c = true && true\n" + "d = false && false\n"
				+ "e = false && false\n"
				+ "f = isValid FM ( A : B C ; ) && false\n"
				+ "g = isValid FM ( A : B C ; ) && true\n"
				+ "h = isValid FM ( A : B C ; !B | !C; ) && true\n"
				+ "i = isValid FM ( A : B C ; !B | !C; ) || true\n"
				+ "j = isValid FM ( A : B C ; !B | !C; ) || false");

		assertFalse(getBooleanVariable("a").isTrue());
		assertTrue(getBooleanVariable("b").isTrue());
		assertTrue(getBooleanVariable("c").isTrue());
		assertFalse(getBooleanVariable("d").isTrue());
		assertFalse(getBooleanVariable("e").isTrue());
		assertFalse(getBooleanVariable("f").isTrue());
		assertTrue(getBooleanVariable("g").isTrue());
		assertFalse(getBooleanVariable("h").isTrue());
		assertTrue(getBooleanVariable("i").isTrue());
		assertFalse(getBooleanVariable("j").isTrue());

	}

	// TODO: other scripts

}
