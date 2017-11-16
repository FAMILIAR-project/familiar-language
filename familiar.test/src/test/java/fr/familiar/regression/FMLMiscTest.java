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
package fr.familiar.regression;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.FMLTest;
import fr.familiar.variable.IntegerVariable;

/**
 * @author mathieuacher
 * 
 */
@Ignore
public class FMLMiscTest extends FMLTest {

	@Test
	public void testMisc1() throws Exception {

		File file = new File("examples/testing/script/repository0.fml");
		String script = "cmp = 7\n";

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(new StringReader(script));
			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str + "\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {

		}

		_shell.parse("run \"repository0.fml\" into repo // repository.fml contains cmp\n"
				+ "	println repo.cmp // no problem\n"
				+ "	cmp = 8\n"
				+ "	println cmp // no problem\n"
				+ "	println repo.cmp // problem");

		// we have to clarify this point:
		// is it allowed that you can modify a variable in a namespace from the
		// outside

		IntegerVariable cmp = getIntegerVariable("cmp"); // should be 8

		assertEquals(8, cmp.getV());

		IntegerVariable repoCmp = getIntegerVariable("repo.cmp"); // should be 7

		assertEquals(7, repoCmp.getV());

	}

	@Test
	public void testMisc2() throws Exception {

		File file = new File("examples/testing/script/repository0.fml");
		String script = "cmp = 7\n";

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			BufferedReader br = new BufferedReader(new StringReader(script));
			String str;
			while ((str = br.readLine()) != null) {
				bw.write(str + "\n");
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {

		}

		_shell.parse("cmp = 8\n"
				+ "   run \"repository0.fml\" into repo // repository.fml contains cmp\n"
				+ "	println repo.cmp // 7\n" + "	println cmp // 8\n"
				+ "	println repo.cmp // 7");

		// we have to clarify this point:
		// is it allowed that you can modify a variable in a namespace from the
		// outside

		IntegerVariable cmp = getIntegerVariable("cmp"); // should be 8

		assertEquals(8, cmp.getV());

		IntegerVariable repoCmp = getIntegerVariable("repo.cmp"); // should be 7

		assertEquals(7, repoCmp.getV());

	}

}
