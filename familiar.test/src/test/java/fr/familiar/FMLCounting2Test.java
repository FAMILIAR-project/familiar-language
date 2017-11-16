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

import org.junit.Ignore;
import org.junit.Test;

/**
 * @author mathieuacher Basically FML scripts calling 'counting' operation
 */
public class FMLCounting2Test extends FMLTest {

	/**
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testFMLCounting1() throws Exception {

		_shell.parse("fm0 = FM (A : B C D; )\n"
				+ "nfm0 = counting fm0 \n"
				+ "nfm0Expected = 1\n"
				+ "assert (nfm0 eq nfm0Expected) \n"
				+ "\n"
				+ "fm1 = FM (A: (B|C|D|E) ; )\n"
				+ "nfm1 = counting fm1\n"
				+ "chA = children fm1.A \n"
				+ "nfm1Expected = size chA\n"
				+ "assert (nfm1 eq nfm1Expected) \n"
				+ "\n"
				+ "fm2 = FM (A : G [B] [C] [D]; ) \n"
				+ "nfm2 = counting fm2 \n"
				+ "nfm2Expected = 8 // 2 ^ 3\n"
				+ "\n"
				+ "assert (nfm2 eq nfm2Expected) \n"
				+ "\n"
				+ "fm3 = FM (A : [B] [C] [D] I; I : (J|K|L); )\n"
				+ "nfm3 = counting fm3\n"
				+ "nfm3Expected = 24 // (2 ^ 3) * 3\n"
				+ "\n"
				+ "assert (nfm3 eq nfm3Expected)\n"
				+ "\n"
				+ "fm4 = FM (A: B C ; B : (D|E); !C | !D; !C | !E; ) // actually valid\n"
				+ "\n" + "validfm4 = isValid fm4\n" + "nfm4 = counting fm4\n"
				+ "FALSE = false\n" + "ZERO = 0\n"
				+ "assert (validfm4 eq FALSE)\n" + "assert (nfm4 eq ZERO) ");

	}

}
