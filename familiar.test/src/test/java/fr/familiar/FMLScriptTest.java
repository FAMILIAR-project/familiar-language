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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fml.ComparisonOperator;

import fr.familiar.parser.VariableComparator;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;

public class FMLScriptTest extends FMLTest {

	@Test
	public void testExternalScript1() throws Exception {

		// FML script file
		File file = new File("examples/testing/script/script0.fml");
		String script = "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

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

		// running the FML script file
		String scriptCalling = "run \"script0.fml\"";
		_shell.parse(scriptCalling);

		getFMVariable("fm1");
		getFMVariable("fm2");
		getFMVariable("fm3");
		getIntegerVariable("a");
		getIntegerVariable("b");
		getStringVariable("c");

	}

	@Test
	public void testExternalScript2() throws Exception {

		// FML script file
		File file = new File("examples/testing/script/script0.fml");
		String script = "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

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

		// running the FML script file with a namespace
		String scriptCalling = "run \"script0.fml\" into fooNS";
		_shell.parse(scriptCalling);

		// no ambiguity
		getFMVariable("fm1");
		getFMVariable("fm2");
		getFMVariable("fm3");
		getIntegerVariable("a");
		getIntegerVariable("b");
		getStringVariable("c");

	}

	@Test
	public void testExternalScript3() throws Exception {

		// FML script file
		File file = new File("examples/testing/script/script0.fml");
		String script = "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

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

		// two running of the FML script file
		String scriptCalling = "run \"script0.fml\" into fooNS\n"
				+ "run \"script0.fml\" into fooNS2\n";
		_shell.parse(scriptCalling);

		// ambiguity
		try {
			getFMVariable("fm1");
			getFMVariable("fm2");
			getFMVariable("fm3");
			getIntegerVariable("a");
			getIntegerVariable("b");
			getStringVariable("c");
		} catch (Exception e) {
			assertNotNull(e);
		}

	}

	@Test
	public void testExternalScript4() throws Exception {

		// FML script file
		File file = new File("examples/testing/script/script0.fml");
		String script = "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

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

		// two running of the FML script file
		String ns1 = "fooNS";
		String ns2 = "fooNS2";
		String scriptCalling = "run \"script0.fml\" into " + ns1 + "\n"
				+ "run \"script0.fml\" into " + ns2 + "\n";
		_shell.parse(scriptCalling);

		// ambiguity
		try {
			getFMVariable(ns1 + "." + "fm1");
			getFMVariable(ns1 + "." + "fm2");
			getFMVariable(ns1 + "." + "fm3");
			getIntegerVariable(ns1 + "." + "a");
			getIntegerVariable(ns1 + "." + "b");
			getStringVariable(ns1 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		// ambiguity
		try {
			getFMVariable(ns2 + "." + "fm1");
			getFMVariable(ns2 + "." + "fm2");
			getFMVariable(ns2 + "." + "fm3");
			getIntegerVariable(ns2 + "." + "a");
			getIntegerVariable(ns2 + "." + "b");
			getStringVariable(ns2 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		FeatureModelVariable ns1fm1 = getFMVariable(ns1 + "." + "fm1");
		FeatureModelVariable ns2fm1 = getFMVariable(ns2 + "." + "fm1");

		VariableComparator vc = new VariableComparator(ns1fm1, ns2fm1,
				ComparisonOperator.EQUAL);
		assertTrue(vc.eval());

		VariableComparator vcRef = new VariableComparator(ns1fm1, ns2fm1,
				ComparisonOperator.REF_EQUAL);
		assertFalse(vcRef.eval());

		FeatureModelVariable ns1fm1bis = getFMVariable(ns1 + "." + "fm1");
		VariableComparator vcRefBis = new VariableComparator(ns1fm1, ns1fm1bis,
				ComparisonOperator.EQUAL);
		assertTrue(vcRefBis.eval());

		assertTrue(true);

	}

	@Ignore
	@Test
	public void testExternalScriptWithParameters1() throws Exception {

		// FML script file
		File file = new File("examples/testing/script/scriptParameter0.fml");
		String script = "parameter fmFoo : FeatureModel \n"
				+ "fm1 = FM (A : B C D; ) \n" + "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "n = counting fmFoo\n"
				+ "a = 7\n" + "b = 8\n" + "c = \"foo\" \n";

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

		// two running of the FML script file
		String ns1 = "fooNS";
		String ns2 = "fooNS2";
		String scriptCalling = "fmns1 = FM (A : [B] [C] [D] ; )\n"
				+ "fmns2 = FM (A : [B] [C] D ; )\n"
				+ "run \"scriptParameter0.fml\" { fmns1 } into " + ns1 + "\n"
				+ "run \"scriptParameter0.fml\" { fmns2 } into " + ns2 + "\n"
				+ "ns1s2 = " + ns1 + "." + "n" + " + " + ns2 + "." + "n" + "\n";
		_shell.parse(scriptCalling);

		// ambiguity
		try {
			getFMVariable(ns1 + "." + "fm1");
			getFMVariable(ns1 + "." + "fm2");
			getFMVariable(ns1 + "." + "fm3");
			getIntegerVariable(ns1 + "." + "n");
			getIntegerVariable(ns1 + "." + "a");
			getIntegerVariable(ns1 + "." + "b");
			getStringVariable(ns1 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		// ambiguity
		try {
			getFMVariable(ns2 + "." + "fm1");
			getFMVariable(ns2 + "." + "fm2");
			getFMVariable(ns2 + "." + "fm3");
			getIntegerVariable(ns2 + "." + "n");
			getIntegerVariable(ns2 + "." + "a");
			getIntegerVariable(ns2 + "." + "b");
			getStringVariable(ns2 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		IntegerVariable ns1n = getIntegerVariable(ns1 + "." + "n");
		IntegerVariable ns2n = getIntegerVariable(ns2 + "." + "n");

		assertEquals(8, ns1n.getV());
		assertEquals(4, ns2n.getV());

		IntegerVariable ns1s2 = getIntegerVariable("ns1s2");

		assertEquals(ns1n.getV() + ns2n.getV(), ns1s2.getV());

		assertTrue(true);

	}

	@Test
	public void testInternalScript1() throws Exception {

		String scriptName = "scriptFoo";
		String scriptContent = "" + "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

		// two running of the FML script file
		String ns1 = "fooNS";
		String ns2 = "fooNS2";
		String scriptCalling = "" + "run " + scriptName + " into " + ns1 + "\n"
				+ "run " + scriptName + " into " + ns2 + "\n";

		String toParse = scriptName + " = " + "[ " + scriptContent + " ]\n"
				+ scriptCalling;
		_shell.parse(toParse);

		// ambiguity
		try {
			getFMVariable(ns1 + "." + "fm1");
			getFMVariable(ns1 + "." + "fm2");
			getFMVariable(ns1 + "." + "fm3");
			getIntegerVariable(ns1 + "." + "a");
			getIntegerVariable(ns1 + "." + "b");
			getStringVariable(ns1 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		// ambiguity
		try {
			getFMVariable(ns2 + "." + "fm1");
			getFMVariable(ns2 + "." + "fm2");
			getFMVariable(ns2 + "." + "fm3");
			getIntegerVariable(ns2 + "." + "a");
			getIntegerVariable(ns2 + "." + "b");
			getStringVariable(ns2 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		IntegerVariable ns1a = getIntegerVariable(ns1 + "." + "a");
		IntegerVariable ns2a = getIntegerVariable(ns2 + "." + "a");

		assertEquals(ns2a.getV(), ns1a.getV());

		FeatureModelVariable ns1fm1 = getFMVariable(ns1 + "." + "fm1");
		FeatureModelVariable ns2fm1 = getFMVariable(ns2 + "." + "fm1");

		VariableComparator vc = new VariableComparator(ns1fm1, ns2fm1,
				ComparisonOperator.EQUAL);
		assertTrue(vc.eval());

		VariableComparator vcRef = new VariableComparator(ns1fm1, ns2fm1,
				ComparisonOperator.REF_EQUAL);
		assertFalse(vcRef.eval());

		FeatureModelVariable ns1fm1bis = getFMVariable(ns1 + "." + "fm1");
		VariableComparator vcRefBis = new VariableComparator(ns1fm1, ns1fm1bis,
				ComparisonOperator.EQUAL);
		assertTrue(vcRefBis.eval());

		assertTrue(true);

	}

	@Test
	public void testInternalScript2() throws Exception {

		String scriptName = "scriptFoo";
		String scriptContent = "" + "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

		// two running of the FML script file
		String ns1 = "fooNS";
		String ns2 = "fooNS2";
		String scriptCalling = "" + "run " + scriptName + " into " + ns1 + "\n"
				+ "run " + scriptName + " into " + ns2 + "\n" + "as1s2 = "
				+ ns1 + "." + "a" + " + " + ns2 + "." + "a" + "\n";

		String toParse = scriptName + " = " + "[ " + scriptContent + " ]\n"
				+ scriptCalling;

		_shell.parse(toParse);

		// ambiguity
		try {
			getFMVariable(ns1 + "." + "fm1");
			getFMVariable(ns1 + "." + "fm2");
			getFMVariable(ns1 + "." + "fm3");
			getIntegerVariable(ns1 + "." + "a");
			getIntegerVariable(ns1 + "." + "b");
			getStringVariable(ns1 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		// ambiguity
		try {
			getFMVariable(ns2 + "." + "fm1");
			getFMVariable(ns2 + "." + "fm2");
			getFMVariable(ns2 + "." + "fm3");
			getIntegerVariable(ns2 + "." + "a");
			getIntegerVariable(ns2 + "." + "b");
			getStringVariable(ns2 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		IntegerVariable ns1a = getIntegerVariable(ns1 + "." + "a");
		IntegerVariable ns2a = getIntegerVariable(ns2 + "." + "a");

		IntegerVariable as1s2 = getIntegerVariable("as1s2");

		assertEquals(ns1a.getV() + ns2a.getV(), as1s2.getV());

		assertTrue(true);

	}

	@Test
	public void testInternalScript3() throws Exception {

		String scriptName = "scriptFoo";
		String scriptContent = "" + "fm1 = FM (A : B C D; ) \n"
				+ "fm2 = FM (H : (I|J|K) ; )\n"
				+ "fm3 = FM (M : (N|O|P)+ ; )\n" + "a = 7\n" + "b = 8\n"
				+ "c = \"foo\" \n";

		String ns2 = "fooNS2";
		String scriptCalling = "" + "run " + scriptName + "\n"
				+ // calling with no namespace
				"run " + scriptName + " into " + ns2 + "\n" + "as1s2 = " + "a"
				+ " + " + ns2 + "." + "a" + "\n";

		String toParse = scriptName + " = " + "[ " + scriptContent + " ]\n"
				+ scriptCalling;

		_shell.parse(toParse);

		// ambiguity
		try {
			getFMVariable("fm1");
			getFMVariable("fm2");
			getFMVariable("fm3");
			getIntegerVariable("a");
			getIntegerVariable("b");
			getStringVariable("c");

		} catch (Exception e) {
			// no way!

		}

		// ambiguity
		try {
			getFMVariable(ns2 + "." + "fm1");
			getFMVariable(ns2 + "." + "fm2");
			getFMVariable(ns2 + "." + "fm3");
			getIntegerVariable(ns2 + "." + "a");
			getIntegerVariable(ns2 + "." + "b");
			getStringVariable(ns2 + "." + "c");

		} catch (Exception e) {
			// no way!

		}

		IntegerVariable noNSa = getIntegerVariable("a");
		IntegerVariable ns2a = getIntegerVariable(ns2 + "." + "a");

		IntegerVariable as1s2 = getIntegerVariable("as1s2");

		assertEquals(noNSa.getV() + ns2a.getV(), as1s2.getV());

		assertTrue(true);

	}

	// TODO: with parameters!

}
