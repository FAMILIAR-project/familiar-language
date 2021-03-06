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

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author mathieuacher
 * 
 */
@Ignore
public class BDDOperationsTest extends FMLTest {

	@Test
	public void testDead1() throws Exception {
		assertFMsEqual("a: [b] c; c->!b ;", "a : c; !b; ");
	}

	@Test
	public void testCounting1() throws Exception {
		double c = FMLTest.countingBDD("a : b [c] [d] ; ", _builder);
		assertEquals(Double.valueOf(4), Double.valueOf(c), 0);
	}

	@Test
	public void testCounting2() throws Exception {
		double c = FMLTest.countingBDD("a : b [c] [d] ; d : (e|f|g|h); ",
				_builder);
		assertEquals(Double.valueOf(2 * 5), Double.valueOf(c), 0);
	}

	@Test
	public void testCounting3() throws Exception {
		double c = FMLTest.countingBDD("a : b [c] d ; d : (e|f|g|h)+; ",
				_builder);
		assertEquals(Double.valueOf(2 * 15), Double.valueOf(c), 0);
	}

	@Test
	public void testCounting4() throws Exception {
		double c = FMLTest.countingBDD("a: [b] c; c->!b ;", _builder);
		assertEquals(Double.valueOf(1), Double.valueOf(c), 0);
	}

	@Test
	public void testCounting5() throws Exception {
		double c = FMLTest.countingBDD("a: b c; c->!b ;", _builder);
		assertEquals(0, Double.valueOf(c), 0);
	}

}
