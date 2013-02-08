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

import org.junit.Test;

/**
 * @author mathieuacher
 * 
 */
public class FMLStringTest extends FMLTest {

	@Test
	public void testString1() throws Exception {

		_shell.parse("foo = \"FOO\"\n"
				+ "foo2 = \"FOO2\"\n"
				+ "foo3 = strConcat foo foo2\n"
				+ "assert ( foo3 eq \"FOOFOO2\" )\n"
				+ "\n"
				+ "foo4 = strConcat foo2 foo\n"
				+ "assert ( foo4 eq \"FOO2FOO\" )\n"
				+ "\n"
				+ "assert (foo4 neq foo3)\n"
				+ "\n"
				+ "i = strIndexOf foo3 \"2\"\n"
				+ "i2 = strIndexOf foo4 \"2\"\n"
				+ "\n"
				+ "assert (i neq i2)\n"
				+ "assert (i > i2)\n"
				+ "\n"
				+ "assert ( (strLength foo3) eq (strLength foo + strLength foo2) )\n"
				+ "assert ( (strLength foo4) eq (strLength foo + strLength foo2) )\n"
				+ "assert ( (strLength foo3) eq (strLength foo4) )\n" + "\n"
				+ "println ((strLength foo4))\n" + "\n"
				+ "foo5 = strSubstring foo4 3 7\n" + "println foo5\n" + "\n"
				+ "i=i+1\n" + "foo6 = strSubstring foo4 i2 i\n"
				+ "assert (foo6 eq foo5)\n" + "\n"
				+ "assert ( (strIndexOf foo \"X\") < 0 )\n"
				+ "assert ( (strIndexOf foo2 \"Y\") < 0 )\n"
				+ "assert ( (strIndexOf foo3 \"ZZ\") < 0 )\n" + "\n"
				+ "assert ( (strIndexOf foo3 foo) eq 0 )\n" + "\n"
				+ "assert ( (strIndexOf foo3 foo2) eq (strLength foo) )");
	}
}
