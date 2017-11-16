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

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class FMLCoresTest extends FMLTest {

	@Test
	public void testCore1() throws Exception {

		String strfm1 = "FM (A : B C D E F; E : G I ; )"; // valid feature model
															// with only
															// mandatory
															// features
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(true, fm1.isValid());
		assertEquals(1, fm1.counting(), 0);

		Set<String> cores = fm1.cores(); // all features are cores
		SetVariable fts = fm1.features();
		assertEquals(fts.size(), cores.size());

		assertEquals(FMLTest.setVariabletoString(fts), cores);

	}

	/**
	 * the test does not pass if executed with others (but works individually / for all tests of the class)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testCore2() throws Exception {

		String strfm1 = "FM (A : B C D E F; E : (G|I) ; )"; // valid feature
															// model
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(true, fm1.isValid());
		_shell.setVerbose(true);
		assertEquals(2, fm1.counting(), 0);
		_shell.setVerbose(false);
		Set<String> cores = fm1.cores(); // all features are cores

		Set<String> expected = new HashSet<String>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("D");
		expected.add("E");
		expected.add("F");

		assertEquals(expected.size(), cores.size());

		assertEquals(expected, cores);

	}

	@Test
	public void testCore3() throws Exception {

		String strfm1 = "FM (A : B C ; (!B | !C); )"; // non valid feature model
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(false, fm1.isValid());

		Set<String> cores = fm1.cores(); // all features should be dead
		assertEquals(0, cores.size());

	}

	@Test
	public void testScriptCore2() throws Exception {

		String strfm1 = "FM (A : B C ; )"; // valid feature model with three
											// cores
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm1 + "\n"
				+ "cfm1 = cores fm1" + "\n" + "cfm2 = cores fm2" + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(true, fm1.isValid());

		Set<String> cores = fm1.cores(); // all features should be dead
		assertEquals(3, cores.size());

		SetVariable cfm1 = getSetVariable("cfm1");
		SetVariable cfm2 = getSetVariable("cfm2");

		assertEquals(3, cfm1.size());
		assertEquals(3, cfm2.size());

	}

	@Test
	public void testScriptCore3() throws Exception {

		String strfm1 = "FM (A : B C ; (!B | !C); )"; // non valid feature model
		_shell.parse("fm1 =" + strfm1 + "\n" + "fm2 =" + strfm1 + "\n"
				+ "cfm1 = cores fm1" + "\n" + "cfm2 = cores fm2" + "\n");

		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;

		assertEquals(false, fm1.isValid());

		Set<String> cores = fm1.cores(); // all features should be dead
		assertEquals(0, cores.size());

		SetVariable cfm1 = getSetVariable("cfm1");
		SetVariable cfm2 = getSetVariable("cfm2");

		assertTrue(cfm1.isEmpty());
		assertTrue(cfm2.isEmpty());

	}

}
