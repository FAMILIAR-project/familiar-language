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
package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.xtext.example.mydsl.fml.FeatureModel;

import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.familiar.fm.basic.FMLFeatureModelReader;
import fr.familiar.fm.basic.FMLFeatureModelWriter;
import fr.familiar.interpreter.FMLAssertionError;
import fr.familiar.interpreter.FMLBasicInterpreter;
import fr.familiar.interpreter.FMLFatalError;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.parser.VariableAmbigousConflictException;

/**
 * @author mathieuacher
 * 
 */
public class FMLBasicInterpreterTest {

	private FMLBasicInterpreter _interpreter;

	@Before
	public void setUp() {
		_interpreter = new FMLBasicInterpreter();
	}

	@Test
	public void testFMConverter1() throws Exception {

		String fmInput = "FM (A : B [C]; )";
		_interpreter.eval("fm1 = " + fmInput);
		String strfm1 = _interpreter.exprToString("fm1");
		assertEquals("A: B [C] ;", strfm1);

		// there ... and back again
		FMLFeatureModelReader reader = new FMLFeatureModelReader();
		FeatureModel fm1 = reader.parseString(strfm1);
		FMLFeatureModelWriter writer = new FMLFeatureModelWriter(fm1);
		String strfm1bis = writer.toString();

		assertEquals("FM (A: B [C] ; )", strfm1bis);

		// back again in the shell ;-)
		_interpreter.eval("fm1bis = " + strfm1bis + "\n"
				+ "cmp11 = compare fm1bis fm1" + "\n");

		String cmp11 = _interpreter.exprToString("cmp11");
		assertEquals("REFACTORING", cmp11);

	}

	@Test
	public void testFMConverter2() {

		String fmInput = "FM (A : B [C]; "; // forget the ')'
		try {
			_interpreter.eval("fm1 = " + fmInput);
		} catch (FMLFatalError e) {
			assertNotNull(e);
			assertNotNull(e.getMessage());
		} catch (FMLAssertionError e) {
			Assert.fail("No assertion Error");
		}
	}

	@Test
	public void testFMConverter3() throws Exception {

		String fmInput = "FM (A : B [C]; )";
		try {
			_interpreter.eval("fm1 = " + fmInput + "\n"
					+ "strfm1 = convert fm1 into featureide" + "\n");
		} catch (FMLFatalError e) {
			Assert.fail("No fatal Error " + e.getMessage());
		} catch (FMLAssertionError e) {
			Assert.fail("No assertion Error");
		}

		String strfm1 = _interpreter.exprToString("strfm1");

		// there and back again... using FeatureIDE facilities
		de.ovgu.featureide.fm.core.FeatureModel fm = new de.ovgu.featureide.fm.core.FeatureModel();
		GuidslReader readerFeatureIDE = new GuidslReader(fm);
		try {
			readerFeatureIDE.readFromString(strfm1);
		} catch (UnsupportedModelException e) {
			Assert.fail("No convertion Error");
		}

		assertNotNull(fm);
		assertNotNull(fm.getRoot());

		assertEquals("A", fm.getRoot().getName());

	}

	@Test
	public void testFMReset1() {

		String fmInput = "FM (A : B [C]; )";
		try {
			_interpreter.eval("fm1 = " + fmInput + "\n"
					+ "strfm1 = convert fm1 into featureide" + "\n");
		} catch (FMLFatalError e) {
			Assert.fail("No fatal Error " + e.getMessage());
		} catch (FMLAssertionError e) {
			Assert.fail("No assertion Error");
		}

		_interpreter.reset();
		assertTrue("no longer variables after reset", _interpreter
				.getAllIdentifiers().size() == 0);

	}

	@Test
	public void testScript1() {

		String strfm1 = "FM (A : B [C]; )";
		String nsAfm1 = "FM (A : B C; )";
		String nsAfm2 = "FM (A : B [C]; )";
		try {
			_interpreter.eval("fm1 = " + strfm1 + "\n" + "ascript = [ "
					+ "fm1 = " + nsAfm1 + "\n" + "fm2 = " + nsAfm2 + "\n"
					+ "] \n" + "run ascript into nsA \n");
		} catch (FMLFatalError e) {
			Assert.fail("No fatal Error " + e.getMessage());
		} catch (FMLAssertionError e) {
			Assert.fail("No assertion Error");
		}

		String fm1 = AssertGetVariable("fm1");
		String fm1bis = AssertGetVariable("nsA.fm1");
		String fm2 = AssertGetVariable("nsA.fm2");

		assertFalse("fm1 is different from fm1 in nsA", fm1.equals(fm1bis));
		assertFalse("fm1 in nsA is different from fm2 in nsA",
				fm2.equals(fm1bis));
		assertTrue("fm1 is equal to fm2 in nsA", fm1.equals(fm2));

		AssertGetVariable("ascript");

		// note that nsA is not considered as a variable -- this is a namespace!
		assertEquals(4, _interpreter.getAllIdentifiers().size());

		_interpreter.reset();
		assertTrue("no longer variables after reset", _interpreter
				.getAllIdentifiers().size() == 0);

	}

	/**
	 * Some assertions about the existence of the variable a.k.a. a reliable way
	 * to get the string representation value of a variable
	 * 
	 * @param identifier
	 * @return the string representation value of the variable whose identifier
	 *         is 'identifier'
	 */
	protected String AssertGetVariable(String identifier) {

		try {
			return _interpreter.exprToString(identifier);
		} catch (VariableNotExistingException e) {
			Assert.fail("Variable does not exist " + e.toString());
		} catch (VariableAmbigousConflictException e) {
			Assert.fail("(ambigous) Variable does not exist " + e.toString());
		}
		// unreachable in JUnit (either the tests fails, or return earlier)
		return "";

	}

	@After
	public void tearDown() {
		if (!_interpreter.hasFatalErrors()) {
			List<String> vars = _interpreter.getAllIdentifiers();
			for (String varID : vars) {
				try {
					assertNotNull(_interpreter.exprToString(varID));
				} catch (VariableNotExistingException e) {
					Assert.fail("Variable does not exist! " + e.toString());
				} catch (VariableAmbigousConflictException e) {
					// not normal since we use fully qualified identifiers
					Assert.fail("(ambigous) Variable does not exist! "
							+ e.toString());
				}

			}
		}

	}

}
