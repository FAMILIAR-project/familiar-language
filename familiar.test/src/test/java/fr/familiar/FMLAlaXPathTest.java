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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLAlaXPathTest extends FMLTest {

	@Test
	public void testQualifiedFeature1() throws Exception {

		String fmSpecification = "FM (A : B C D; D : E F ; E : G H I ; I : J ; B : Z Y ; Y : O [P] ;)";
		String fmID = "fm1";
		_shell.parse(fmID + " = " + fmSpecification);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		FeatureVariable ftE = getFeatureVariable("fm1.E");
		assertEquals("E", ftE.getFtName());

		_shell.setVerbose(true);
		FeatureVariable ftQualifiedE = getFeatureVariable("fm1.D.E");
		assertEquals("E", ftQualifiedE.getFtName());

		FeatureVariable ftQualifiedP = getFeatureVariable("fm1.B.Y.P");
		assertEquals("P", ftQualifiedP.getFtName());

		FeatureVariable ftQualifiedP2 = getFeatureVariable("fm1.A.B.Y.P");
		assertEquals("P", ftQualifiedP2.getFtName());

		try {
			getFeatureVariable("fm1.A.B.Z.P"); // Z is not an ancestor of P!
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true); // expected
		}

		FeatureVariable ftQualifiedP3 = getFeatureVariable("fm1.A.B.P"); // Y
		assertEquals("P", ftQualifiedP3.getFtName());

		try {
			getFeatureVariable("fm1.A.D.P"); // D is not an ancestor of P!
			assertFalse(true);
		} catch (Exception e) {
			assertTrue(true); // expected
		}

		FeatureVariable ftQualifiedP4 = getFeatureVariable("fm1.A.P");
		assertEquals("P", ftQualifiedP4.getFtName());

	}

	@Test
	public void testQualifiedFeatureStar1() throws Exception {

		String fmSpecification = "FM (A : B C D; D : E F ; E : G H I ; I : J ; B : Z Y ; Y : O [P] ;)";
		String fmID = "fm1";
		_shell.parse(fmID + " = " + fmSpecification);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);
		SetVariable bFTs = getSetVariable("fm1.B.*");
		assertEquals(4, bFTs.size());
		Set<String> bFTsExpected = new HashSet<String>();
		bFTsExpected.add("Z");
		bFTsExpected.add("Y");
		bFTsExpected.add("O");
		bFTsExpected.add("P");
		assertEquals(bFTsExpected, setVariabletoString(bFTs));

		SetVariable bFTs2 = getSetVariable("fm1.A.B.*");
		assertEquals(4, bFTs2.size());
		assertEquals(bFTsExpected, setVariabletoString(bFTs2));

		SetVariable aFTs = getSetVariable("fm1.A.*");
		SetVariable fts = getSetVariable("fm1.*");
		assertEquals(fts.size(), aFTs.size() + 1);

		SetVariable iFTs = getSetVariable("fm1.I.*");
		assertEquals(1, iFTs.size());
		String jFT = setVariabletoString(iFTs).iterator().next();
		assertEquals("J", jFT);

		SetVariable iFTs2 = getSetVariable("fm1.E.I.*");
		assertEquals(1, iFTs2.size());
		String jFT2 = setVariabletoString(iFTs2).iterator().next();
		assertEquals("J", jFT2);

		// leaf
		SetVariable pFTs = getSetVariable("fm1.P.*");
		assertEquals(0, pFTs.size());

	}

	@Test
	public void testQualifiedFeatureStarUnion() throws Exception {

		String fmSpecification = "FM (A : B C D; D : E F ; E : G H I ; I : J ; B : Z Y ; Y : O [P] ;)";
		String fmID = "fm1";
		_shell.parse(fmID + " = " + fmSpecification);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);
		SetVariable bFTs = getSetVariable("fm1.B.*");
		assertEquals(4, bFTs.size());
		Set<String> bFTsExpected = new HashSet<String>();
		bFTsExpected.add("Z");
		bFTsExpected.add("Y");
		bFTsExpected.add("O");
		bFTsExpected.add("P");
		assertEquals(bFTsExpected, setVariabletoString(bFTs));

		String sID = "s1";
		_shell.parse(sID + " = " + "fm1.B.*" + " ++ " + "fm1.I.*");
		SetVariable s1 = getSetVariable("s1");

		System.err.println("s1=" + s1.getSpecificValue());

		assertEquals(5, s1.size());
		Set<String> s1Expected = new HashSet<String>(bFTsExpected);
		s1Expected.add("J");

		assertEquals(s1Expected, setVariabletoString(s1));
	}

	@Test
	public void testQualifiedFeatureStarUnion2() throws Exception {

		String fmSpecification = "FM (A : B C D; D : E F ; E : G H I ; I : J ; B : Z Y ; Y : O [P] ;)";
		String fmID = "fm1";
		_shell.parse(fmID + " = " + fmSpecification);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);
		SetVariable bFTs = getSetVariable("fm1.B.*");
		String sID = "s1";
		_shell.parse(sID + " = " + "fm1.B.*" + " ++ " + "fm1.B.*");
		SetVariable s1 = getSetVariable("s1");
		assertEquals(bFTs.size(), s1.size());

		SetVariable eFTs = getSetVariable("fm1.E.*");
		SetVariable fFTs = getSetVariable("fm1.F.*");
		String s2ID = "s2";
		_shell.parse(s2ID + " = " + "fm1.E.*" + " ++ " + "fm1.F.*");
		SetVariable s2FTs = getSetVariable(s2ID);

		_shell.parse("n2 = size " + s2ID + "\n");
		IntegerVariable n2 = getIntegerVariable("n2");
		assertEquals(s2FTs.size(), n2.getV());
		assertEquals(s2FTs.size(), eFTs.size() + fFTs.size());

		SetVariable dFTs = getSetVariable("fm1.D.*");

		String s3ID = "s3";
		_shell.parse(s3ID + " = " + s2ID + " ++ " + "fm1.D.*");
		SetVariable s3FTs = getSetVariable(s3ID);
		assertEquals(dFTs.size(), s3FTs.size());
	}

	@Test
	public void testQualifiedFeatureStarDiff1() throws Exception {

		String fmSpecification = "FM (A : B C D; D : E F ; E : G H I ; I : J ; B : Z Y ; Y : O [P] ;)";
		String fmID = "fm1";
		_shell.parse(fmID + " = " + fmSpecification);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);

		String sID = "s1";
		_shell.parse(sID + " = " + "fm1.B.*" + " -- " + "fm1.B.*");
		SetVariable s1 = getSetVariable("s1");
		assertEquals(0, s1.size());

		String s2ID = "s2";
		_shell.parse(s2ID + " = " + "fm1.B.*" + " -- " + "fm1.Y.*");
		SetVariable s2 = getSetVariable(s2ID);
		assertEquals(2, s2.size());

		Set<String> s2Expected = new HashSet<String>();
		s2Expected.add("Z");
		s2Expected.add("Y");
		assertEquals(s2Expected, setVariabletoString(s2));

	}

}
