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
package fr.familiar.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.fm.FeatureNodeUtils;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class FeatureNodeUtilsTest extends FMLTest {

	@Test
	public void testXOR1() throws Exception {

		String strfm1 = "FM ( W: (X|Y|Z) ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		Set<String> features = new HashSet<String>();
		features.add("X");
		features.add("Y");
		features.add("Z");

		for (String ft : features) {
			assertTrue(FeatureNodeUtils.isInXOR(fg1, fg1.findVertex(ft)));

		}

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isOptional(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInOR(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isMandatory(fg1, fg1.findVertex(ft)));

	}

	@Test
	public void testXOR2() throws Exception {

		String strfm1 = "FM ( W: (X|Y|Z) ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		assertTrue(FeatureNodeUtils.hasXorGroup(fg1, fg1.findVertex("W")));

	}

	@Test
	public void testOR1() throws Exception {

		String strfm1 = "FM ( W: (X|Y|Z)+ ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		Set<String> features = new HashSet<String>();
		features.add("X");
		features.add("Y");
		features.add("Z");

		for (String ft : features)
			assertTrue(FeatureNodeUtils.isInOR(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isOptional(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInXOR(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isMandatory(fg1, fg1.findVertex(ft)));

	}

	@Test
	public void testOR2() throws Exception {

		String strfm1 = "FM ( W: (X|Y|Z)+ ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		assertTrue(FeatureNodeUtils.hasOrGroup(fg1, fg1.findVertex("W")));

	}

	@Test
	public void testMAND1() throws Exception {

		String strfm1 = "FM ( W: X Y Z ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		Set<String> features = new HashSet<String>();
		features.add("X");
		features.add("Y");
		features.add("Z");

		for (String ft : features)
			assertTrue(FeatureNodeUtils.isMandatory(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isOptional(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInOR(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInXOR(fg1, fg1.findVertex(ft)));

	}

	@Test
	public void testMAND2() throws Exception {

		String strfm1 = "FM ( W: X Y Z ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		assertTrue(FeatureNodeUtils.hasMandatoryFeatures(fg1,
				fg1.findVertex("W")));
		assertFalse(FeatureNodeUtils.hasOptionalFeatures(fg1,
				fg1.findVertex("W")));

	}

	@Test
	public void testOPT1() throws Exception {

		String strfm1 = "FM ( W: [X] [Y] [Z] ; X : (I|J); Y : [U] [V]; Z : (A|B)+ ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");
		Variable v1 = _environment.getVariable("fm1");
		assertNotNull(v1);
		assertTrue(v1 instanceof FeatureModelVariable);
		FeatureModelVariable fm1 = (FeatureModelVariable) v1;
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		Set<String> features = new HashSet<String>();
		features.add("X");
		features.add("Y");
		features.add("Z");
		features.add("U");
		features.add("V");

		for (String ft : features)
			assertTrue(FeatureNodeUtils.isOptional(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isMandatory(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInOR(fg1, fg1.findVertex(ft)));

		for (String ft : features)
			assertFalse(FeatureNodeUtils.isInXOR(fg1, fg1.findVertex(ft)));

	}

	@Test
	public void testOPT2() throws Exception {

		String strfm1 = "FM ( W: [X] [Y] [Z] ; X : (I|J); Y : U [V]; Z : (A|B)+ ; (X <-> Y); )";
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureGraph<String> fg1 = fm1.getFm().getDiagram();

		assertTrue(FeatureNodeUtils.hasOptionalFeatures(fg1,
				fg1.findVertex("W")));
		assertFalse(FeatureNodeUtils.hasMandatoryFeatures(fg1,
				fg1.findVertex("W")));

		assertTrue(FeatureNodeUtils.hasOptionalFeatures(fg1,
				fg1.findVertex("Y")));
		assertTrue(FeatureNodeUtils.hasMandatoryFeatures(fg1,
				fg1.findVertex("Y")));

	}

	@Test
	public void testMultiGroup1() throws Exception {

		String strfm1 = "FM ( W: [X] [Y] [Z] ; X : (I|J) K [L]; Y : (M|N) (O|P); Z : (A1|A2)+ B ; )";
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureGraph<String> fg = fm1.getFm().getDiagram();

		FeatureNode<String> x = fg.findVertex("X");
		assertTrue(FeatureNodeUtils.isMultiGroup(fg, x));

		FeatureNode<String> y = fg.findVertex("Y");
		assertTrue(FeatureNodeUtils.isMultiGroup(fg, y));

		FeatureNode<String> z = fg.findVertex("Z");
		assertTrue(FeatureNodeUtils.isMultiGroup(fg, z));

		FeatureNode<String> w = fg.findVertex("W");
		assertFalse(FeatureNodeUtils.isMultiGroup(fg, w));

	}

	@Test
	public void testMultiGroup2() throws Exception {

		String strfm1 = "FM ( W: [X] [Y] [Z] ; X : (I|J|B1) ; Y : (M|N)+ (O|P); Z : (A1|A2) B ; )";
		_shell.parse("fm1 =" + strfm1 + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureGraph<String> fg = fm1.getFm().getDiagram();

		FeatureNode<String> x = fg.findVertex("X");
		assertFalse(FeatureNodeUtils.hasOptionalFeatures(fg, x));
		assertFalse(FeatureNodeUtils.isMultiGroup(fg, x));

		FeatureNode<String> y = fg.findVertex("Y");
		assertTrue(FeatureNodeUtils.isMultiGroup(fg, y));

		FeatureNode<String> z = fg.findVertex("Z");
		assertTrue(FeatureNodeUtils.isMultiGroup(fg, z));

		FeatureNode<String> w = fg.findVertex("W");
		assertFalse(FeatureNodeUtils.isMultiGroup(fg, w));

	}

}
