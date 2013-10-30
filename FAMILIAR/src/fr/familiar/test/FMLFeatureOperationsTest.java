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

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;

/**
 * @author mathieuacher
 * 
 */
public class FMLFeatureOperationsTest extends FMLTest {

	private String _fmSpecification = "FM (A : B [C] D ; B : (E|F|G); C : H I ; I : (J|K|L) ; L : M N O P ; )\n";

	@Test
	public void testAncestors1() throws Exception {

		String fmID = "fm1";
		_shell.parse(fmID + " = " + _fmSpecification);
		String sID = "s1";
		_shell.parse(sID + " = " + "ancestors fm1.P\n");

		SetVariable s1 = getSetVariable(sID);

		Set<String> s1Expected = new HashSet<String>();
		s1Expected.add("L");
		s1Expected.add("I");
		s1Expected.add("C");
		s1Expected.add("A");
		assertEquals(s1Expected, setVariabletoString(s1));

	}

	@Test
	public void testDescendants1() throws Exception {

		String fmID = "fm1";
		_shell.parse(fmID + " = " + _fmSpecification);
		String sID = "s1";
		_shell.parse(sID + " = " + "descendants fm1.C\n");
		String s2ID = "s2";
		_shell.parse(s2ID + " = " + " fm1.C.*\n");

		SetVariable s1 = getSetVariable(sID);
		SetVariable s2 = getSetVariable(s2ID);

		assertEquals(setVariabletoString(s1), setVariabletoString(s2));

	}

	@Test
	public void testChildren1() throws Exception {

		_shell.parse("// testing the 'children' operation\n"
				+ "\n"
				+ "// syntacs: b = children a\n"
				+ "\n"
				+ "// a is a variable of feature type\n"
				+ "\n"
				+ "// b is a set of features f1, f2, ..., fn child of a\n"
				+ "\n"
				+ "// b = children fm1.A\n"
				+ "\n"
				+ "// b is a set of features f1, f2, ..., fn child of A (present in the feature model fm1)\n"
				+ "\n"
				+ "fm1 = FM (A : B [C] ; B : E F ; C : (I|J) ; )\n"
				+ "\n"
				+ "root_fm1 = root fm1\n"
				+ "s = children root_fm1\n"
				+ "s1 = children fm1.A\n"
				+ "assert (s eq s1) // equality of the two sets\n"
				+ "\n"
				+ "\n"
				+ "// testing cards\n"
				+ "\n"
				+ "ns = size s\n"
				+ "ns1 = size s1 \n"
				+ "ns_expected = 2\n"
				+ "assert (ns eq ns1)\n"
				+ "assert (ns eq ns_expected)\n"
				+ "\n"
				+ "// another FM\n"
				+ "\n"
				+ "fm2 = FM (A : B C E ; C : (I|J)+ ; )\n"
				+ "root_fm2 = root fm2\n"
				+ "s2 = children root_fm2\n"
				+ "s21 = children fm2.A\n"
				+ "assert (s2 eq s21)\n"
				+ "ns2 = size s2\n"
				+ "ns2_expected = 3\n"
				+ "assert (ns2 eq ns2_expected)\n"
				+ "\n"
				+ "assert (s2 neq s1) // {B, C, E} vs {B, C}\n"
				+ "\n"
				+ "\n"
				+ "// ambiguity\n"
				+ "\n"
				+ "//sft = children A // fm1.A or fm2.A ?\n"
				+ "\n"
				+ "//bsft = isNull sft \n"
				+ "//TRUE = true\n"
				+ "//assert (bsft eq TRUE)\n"
				+ "\n"
				+ "\n"
				+ "fm3 = FM (Z: (W|X|Y)+; )\n"
				+ "fm4 = FM (Z: (W|X|Y)+; )\n"
				+ "\n"
				+ "s3 = children fm3.Z\n"
				+ "s4 = children fm4.Z\n"
				+ "assert (s3 eq s4) // though features do not refer to the same feature model\n"
				+ "\n"
				+ "\n"
				+ "fm5 = FM (Y: (H|J); J: [K] [L] M N;)\n"
				+ "\n"
				+ "s5 = children fm5.J\n"
				+ "ns5 = size s5\n"
				+ "ns5_expected = 4\n"
				+ "assert (ns5 eq ns5_expected)\n"
				+ "\n"
				+ "\n"
				+ "// ns5 contains M\n"
				+ "\n"
				+ "j = 0\n"
				+ "m_feature = \"M\"\n"
				+ "foreach (el in s5) do	\n"
				+ "	name_el = name el\n"
				+ "	if (name_el eq m_feature) then		\n"
				+ "		j = j + 1	\n"
				+ "	end \n"
				+ "end\n"
				+ "ONE = 1\n"
				+ "assert (j eq ONE)\n"
				+ "\n"
				+ "// ns5 does not contain O\n"
				+ "\n"
				+ "j = 0\n"
				+ "m_feature = \"O\"\n"
				+ "foreach (el in s5) do	\n"
				+ "	name_el = name el	\n"
				+ "	if (name_el eq m_feature) then\n"
				+ "		j = j + 1	\n"
				+ "	end \n"
				+ "end\n"
				+ "ZERO = 0\n"
				+ "assert (j eq ZERO)\n"
				+ "\n"
				+ "\n"
				+ "fm6 = FM (A : B C D E; E : (F|G) ; G: (I|J)+ ; J : L [M];)\n"
				+ "s6 = children fm6.G // I and J\n" + "\n" + "ns6 = size s6\n"
				+ "s6_expected = 2\n" + "assert (s6_expected eq ns6)");

	}

	@Test
	public void testName1() throws Exception {

		_shell.parse("// testing the 'name' operation\n"
				+ "// syntacs: b = name a\n"
				+ "// a is a variable of feature type\n"
				+ "// b is a string whose value is the name of the feature 'a' \n"
				+ "// b = name fm1::A\n"
				+ "// b is a string whose value is the name of the feature A (present in the feature model fm1)\n"
				+ "// equivalent to write : b = \"A\"\n"
				+ "\n"
				+ "\n"
				+ "fm1 = FM (A : B [C] ; B : E F ; C : (I|J) ; )\n"
				+ "fm2 = FM (A : B C E ; C : (I|J)+ ; )\n"
				+ "root_fm1 = root fm1\n"
				+ "b = name root_fm1\n"
				+ "b2 = \"A\"\n"
				+ "assert (b eq b2)\n"
				+ "b3 = name fm1.A\n"
				+ "b4 = name fm2.A\n"
				+ "assert (b eq b3) // b3 (aka \"A\") is the root name of fm1\n"
				+ "assert (b3 eq b2) // b3: \"A\"\n"
				+ "assert (b2 eq b4) // b4: \"A\"\n" + "assert (b3 eq b4)\n"
				+ "f = name fm1.F // equivalent \n"
				+ "f2 = name F // not ambigous: F is only present in fm1\n"
				+ "assert (f eq f2)\n" + "i = name fm1.I\n"
				+ "i2 = name fm2.I\n" + "assert (i eq i2)\n"
				+ "i3 = name I // ambigous! i3 = null\n"
				+ "bi3 = isNull i3 // bi3 is true\n" + "bi2 = isNull i2 \n"
				+ "bi = isNull i\n" + "run \"assertTrue\" {bi3}\n"
				+ "run \"assertFalse\" {bi2}\n" + "run \"assertFalse\" {bi}\n"
				+ "foreach (bval in bi*) do \n" + "	print {bval} \n" + "end\n"
				+ "\n" + "");

	}

	@Test
	public void testParent1() throws Exception {

		_shell.parse("// testing the 'parent' operation\n"
				+ "\n"
				+ "// syntacs: b = parent a\n"
				+ "\n"
				+ "// a is a variable of feature type\n"
				+ "\n"
				+ "// b is a reference to a feature\n"
				+ "\n"
				+ "fm1 = FM (Z: (W|X|Y)+; )\n"
				+ "fm2 = FM (Z: (X|Y); )\n"
				+ "\n"
				+ "f1 = parent W\n"
				+ "f2 = parent fm1.W\n"
				+ "f3 = parent W\n"
				+ "assert (f1 eq f2)\n"
				+ "assert (f3 eq f1)\n"
				+ "root_fm1 = root fm1\n"
				+ "assert (root_fm1 eq f1)\n"
				+ "assert (root_fm1 eq f3)\n"
				+ "assert (root_fm1 eq f2)\n"
				+ "\n"
				+ "//fX = parent X // ambigous\n"
				+ "\n"
				+ "//ambigous_X = isNull fX\n"
				+ "//TRUE = true\n"
				+ "//assert (ambigous_X eq TRUE)\n"
				+ "\n"
				+ "fm3 = FM (Z: Y U; U: V X [W]; )\n"
				+ "fu = parent fm3.W\n"
				+ "name_U = \"U\"\n"
				+ "name_fu = name fu\n"
				+ "assert (name_U eq name_fu)\n"
				+ "\n"
				+ "fm4 = FM (Z: (Y|S)+; S: [V] X W; )\n"
				+ "fs = parent fm4.V // S\n"
				+ "\n"
				+ "fz = parent fs // Z\n"
				+ "\n"
				+ "root_fm4 = root fm4 // Z\n"
				+ "\n"
				+ "assert (root_fm4 eq fz)\n"
				+ "ref_fm4 = whichfm fz\n"
				+ "ref2_fm4 = whichfm root_fm4\n"
				+ "assert (ref_fm4 eq ref2_fm4)\n"
				+ "assert (fm4 eq ref_fm4)\n"
				+ "assert (ref2_fm4 eq fm4)\n"
				+ "\n"
				+ "fZ1 = parent fm1.X // Z (fm1)\n"
				+ "\n"
				+ "fZ2 = parent fm2.X // Z (fm2)\n"
				+ "\n"
				+ "fZ3 = parent fm3.U // Z (fm3)\n"
				+ "\n"
				// TODO (discuss, related to features equality)
				+ "assert (fZ1 eq fZ2) // though features do not belong to the same feature model\n"
				+ "\n"
				+ "assert (fZ2 eq fZ3) // though features do not belong to the same feature model\n"
				+ "\n"
				+ "assert (fZ1 eq fZ3) // though features do not belong to the same feature model\n"
				+ "\n"
				+ "namefZ1 = name fZ1\n"
				+ "namefZ2 = name fZ2\n"
				+ "assert (namefZ1 eq namefZ2) // equality between feature's names\n"
				+ "\n" + "// ...\n" + "");

	}

	@Test
	public void testRoot1() throws Exception {
		_shell.parse("// testing the 'root' operation\n"
				+ "\n"
				+ "// syntacs: b = root fm \n"
				+ "\n"
				+ "// fm is a variable of feature model type\n"
				+ "\n"
				+ "// b is a reference of the (root) feature of 'fm'\n"
				+ "\n"
				+ "// b = root fm <=> ((whichfm b) eq fm) <=> ((root (whichfm b)) eq b)\n"
				+ "\n"
				+ "fm1 = FM (A : [B] [C] X Y; B : E [F] ; C : (I|J) ; )\n"
				+ "\n" + "root_fm1 = root fm1\n"
				+ "name_root_fm1 = name root_fm1\n" + "faa = \"A\"\n"
				+ "assert (faa eq name_root_fm1)\n" + "ftA = name fm1.A \n"
				+ "assert (ftA eq name_root_fm1) \n"
				+ "fm2 = whichfm root_fm1\n" + "assert (fm2 eq fm1)\n"
				+ "root_fm2 = root fm2\n" + "assert (root_fm2 eq root_fm1)\n"
				+ "");

	}

	@Test
	public void testSibling1() throws Exception {
		_shell.parse("// testing the 'sibling' operation\n"
				+ "\n"
				+ "// syntacs: b = sibling a\n"
				+ "\n"
				+ "// a is a variable of feature type\n"
				+ "\n"
				+ "// b is a set of features f1, f2, ..., fn sibling of a\n"
				+ " \n"
				+ "// b = sibling fm1.A\n"
				+ "\n"
				+ "// b is a set of features f1, f2, ..., fn sibling of A (present in the feature model fm1)\n"
				+ "\n" + "fm1 = FM (A : B [C] ; B : E F ; C : (I|J|K) ; )\n"
				+ "\n" + "sIK = sibling J\n" + "nsibling_J = size sIK\n"
				+ "nexpected = 2\n" + "assert (nsibling_J eq nexpected)\n"
				+ "fC = parent fm1.J\n" + "\n" + "sB = sibling fC\n"
				+ "nexpected = 1\n" + "nsB = size sB \n"
				+ "assert (nsB eq nexpected)\n" + "\n"
				+ "fm2 = FM (A : B [C] ; B : E ; )\n" + "sE = sibling fm2.E\n"
				+ "bsE = setIsEmpty sE\n" + "TRUE = true\n"
				+ "assert (bsE eq TRUE)");

	}

	@Test
	public void testSibling2() throws Exception {
		_shell.parse("// mysibling\n"
				+ "// emulate 'sibling' operation considering fm3\n"
				+ "// see mysibling for a macro which generalizes the example\n"
				+ "fm3 = FM (A : B [C] ; B : (E|F|G)+ ; )\n" + "\n" + "\n"
				+ "se3 = sibling fm3.E // sibling fm3.E = {F, G}\n" + "\n"
				+ "name_ft = name fm3.E\n" + "parent_ft = parent fm3.E\n"
				+ "children_parent_ft = children parent_ft\n"
				+ "result = setEmpty\n"
				+ "foreach (c in children_parent_ft) do \n"
				+ "	name_c = name c \n"
				+ "	if (not (name_c eq name_ft)) then \n"
				+ "	    println  \"adding \", c, \" to set \", result\n"
				+ "		setAdd result c \n" + "	end \n" + "end\n"
				+ "print result\n" + "nresult = size result\n" + "TWO = 2\n"
				+ "assert (nresult eq TWO)\n" + "assert (se3 eq result)\n"
				+ "\n");

		SetVariable children_parent_ft = getSetVariable("children_parent_ft");
		assertEquals(3, children_parent_ft.size());

		IntegerVariable nresult = getIntegerVariable("nresult");
		assertEquals(2, nresult.getV());

	}

	@Test
	public void testSibling3() throws Exception {
		_shell.parse("// testing the 'sibling' operation\n"
				+ "// syntacs: b = sibling a\n"
				+ "// a is a variable of feature type\n"
				+ "// b is a set of features f1, f2, ..., fn sibling of a \n"
				+ "// b = sibling fm1::A\n"
				+ "// b is a set of features f1, f2, ..., fn sibling of A (present in the feature model fm1)\n"
				+ "\n"
				+ "fm1 = FM (A : B [C] ; B : E F ; C : (I|J|K) ; )\n"
				+ "\n"
				+ "sIK = sibling J\n"
				+ "nsibling_J = size sIK\n"
				+ "nexpected = 2\n"
				+ "assert (nsibling_J eq nexpected)\n"
				+ "fC = parent fm1.J\n"
				+ "\n"
				+ "sB = sibling fC\n"
				+ "nexpected = 1\n"
				+ "nsB = size sB \n"
				+ "assert (nsB eq nexpected)\n"
				+ "\n"
				+ "fm2 = FM (A : B [C] ; B : E ; )\n"
				+ "\n"
				+ "sE = sibling fm2.E\n"
				+ "bsE = setIsEmpty sE\n"
				+
				// "run \"assertTrue\" {bsE} into devnull\n" +
				"\n"
				+ "\n"
				+ "// test mysbling macro\n"
				+ "// see also sibling2.fmm\n"
				+ "\n"
				+ "fm4 = FM (A : B [C] ; B : (E|F|G|H)+ ; H : (I|J|K); )\n"
				+ "fH = parent fm4.I // H\n"
				+ "sexpected = sibling fH // {E, F, G}\n"
				+
				// "run \"mysibling\" {fH} into sibl \n" +
				// "nres = size sibl.res\n" +
				// "THREE = 3\n" +
				// "assert (nres eq THREE)\n" +
				// "assert (sexpected eq sibl.res)\n" +
				"\n"
				+ "//print_var children_parent_ft\n"
				+ "//if (not (parent_ft neq parent_ft)) then print_var parent_ft end\n"
				+ "");

	}

	@Test
	public void testWhichfm() throws Exception {

		_shell.parse("// testing the 'whichfm' operation\n"
				+ "\n"
				+ "// syntacs: fmb = whichfm a\n"
				+ "\n"
				+ "// a is a variable of feature type\n"
				+ "\n"
				+ "// fmb is a reference of the feature model in which the feature 'a' belongs to\n"
				+ "\n" + "fm1 = FM (Z: (W|X|Y)+; Y: (U|V); )\n"
				+ "fm2 = FM (Z: (X|Y); )\n" + "\n" + "f1 = parent W\n"
				+ "ref_fm1_f1 = whichfm f1\n" + "root_fm1 = root fm1\n"
				+ "ref_fm_root_fm1 = whichfm root_fm1\n"
				+ "assert (ref_fm_root_fm1 eq ref_fm1_f1)\n"
				+ "assert (ref_fm1_f1 eq fm1)\n"
				+ "assert (ref_fm_root_fm1 eq ref_fm1_f1)\n" + "\n"
				+ "fZ = parent fm2.X\n" + "name_Z = \"Z\"\n"
				+ "name_fZ = name fZ\n" + "assert (name_Z eq name_fZ)\n"
				+ "ref_fm2 = whichfm fZ\n" + "assert (ref_fm2 eq fm2)\n"
				+ "assert (ref_fm2 neq fm1) // fX does not belong to fm1!\n"
				+ "\n" + "\n" + "fm4 = FM (Z: (Y|S)+; S: [V] X W; )\n"
				+ "ref_fm4 = whichfm fm4.V // obvious\n" + "\n"
				+ "assert (ref_fm4 eq fm4) \n" + "fS = parent fm4.X\n"
				+ "ref_fm4_fS = whichfm fS\n"
				+ "assert (ref_fm4_fS eq ref_fm4)\n"
				+ "assert (fm4 eq ref_fm4)\n" + "");

	}

	@Test
	public void testMisc1() throws Exception {

		_shell.parse("// misc\n" + "\n"
				+ "fm1 = FM ( A: B [C] [D] E; E : (F|G) ; D : I [J] ; )\n"
				+ "fa = name fm1.A\n" + "fm2 = whichfm fm1.A\n"
				+ "// asser eq ....\n" + "parentB = parent fm1.B\n"
				+ "rootFM1 = root fm1\n" + "assert (rootFM1 eq parentB)\n"
				+ "\n" + "name_parentB = name parentB\n" + "faa = \"A\"\n"
				+ "assert (faa eq name_parentB)\n" + "\n"
				+ "name_rootFM1 = name rootFM1\n"
				+ "assert (fa eq name_rootFM1)\n" + "\n"
				+ "parentJ = parent fm2.J\n" + "fD1 = parent fm1.J\n"
				+ "fD2 = parent J // ambigous\n" + "bfD2 = isNull fD2\n"
				+ "run \"assertTrue\" { bfD2 }\n" + "\n"
				+ "assert (fD1 eq parentJ)\n" + "\n" + "name_D = \"D\"\n"
				+ "name_fD1 = name fD1\n" + "name_parentJ = name parentJ\n"
				+ "assert (name_D eq name_parentJ)\n"
				+ "assert (name_parentJ eq name_fD1)\n"
				+ "assert (name_D eq name_fD1)\n" + "\n"
				+ "ref_fD1 = whichfm fD1\n" + "ref_parentJ = whichfm parentJ\n"
				+ "assert (ref_fD1 eq ref_parentJ)\n" + "\n"
				+ "debug = false\n" + "\n"
				+ "if (debug) then print ref_fD1, ref_parentJ, fm1, fm2  end\n"
				+ "\n");

	}

	@Test
	public void testMisc2() throws Exception {

		_shell.parse("fStr = \"fooF\"\n"
				+ "fm1 = FM (A: B [C] D; D : (E|F)+; F : (I|J|K); E : [Z]; )\n"
				+ "fm1bis = copy fm1\n"
				+ "b = renameFeature fm1.B as \"Bbis\"\n"
				+ "b2 = renameFeature fm1.F as fStr\n" + "ftD = parent fm1.E\n"
				+ "ftA = parent parent fm1.E\n"
				+ "println \"ftA is null? \", (isNull ftA)\n"
				+ "rA = root fm1\n" + "ftNull = parent parent parent fm1.E\n"
				+ "println (children fm1.D)\n" + "nd = size children fm1.D\n"
				+ "//println (renameFeature fm1.B as \"Bbis\") // false\n"
				+ "println (removeFeature fm1.C)\n"
				+ "//fg = parent children fm1.D // not compiling\n"
				+ "println (removeFeature fm1.fooF)\n"
				+ "//println (removeFeature fm1.B) // false\n"
				+ "fm2 = copy fm1\n" + "println fm1bis\n" +
				// "println \"ftNull is really null? \", (isNull ftNull)\n" +
				"");

		IntegerVariable nd = getIntegerVariable("nd");
		int ind = nd.getV();
		System.err.println("nd=" + ind);
		assertEquals(2, ind);

	}

	@Test
	public void testMisc2bis() throws Exception {

		_shell.parse("fStr = \"fooF\"\n"
				+ "fm1 = FM (A: B [C] D; D : (E|F)+; F : (I|J|K); E : [Z]; )\n"
				+ "fm1bis = copy fm1\n"
				+ "b = renameFeature fm1.B as \"Bbis\"\n"
				+ "b2 = renameFeature fm1.F as fStr\n" + "ftD = parent fm1.E\n"
				+ "ftA = parent parent fm1.E\n"
				+ "println \"ftA is null? \", (isNull ftA)\n"
				+ "rA = root fm1\n" + "ftNull = parent parent parent fm1.E\n" +
				// the following line crashes the program
				// "println \"ftNull is really null? \", (isNull ftNull)\n" +
				"");

		// TODO: discuss isNull
	}

	@Test
	public void testMisc3() throws Exception {

		_shell.parse("fm1 = FM (A: B [C] D; D : (E|F)+; F : (I|J|K); E : [Z]; )\n"
				+ "fm1bis = copy fm1 // save the original version\n"
				+ "\n"
				+ "\n"
				+ "renameFeature fm1.B as \"Bbis\"\n"
				+ "\n"
				+ "bExist = isExisting fm1.B // B does not exist anymore");

		// TODO

	}

	@Test
	public void testOperator1() throws Exception {
		_shell.parse("// syntacs: op = operator ft\n"
				+ "\n"
				+ "// ft is a variable of feature type\n"
				+ "\n"
				+ "// op is the operator (mandatory, optional, Xor, Or) of feature 'ft' and its parent\n"
				+ "\n"
				+ "// => a leaf feature does have an operator\n"
				+ "\n"
				+ "// => a root feature has no operator\n"
				+ "\n"
				+ "// op = operator fm1.A\n"
				+ "\n"
				+ "// op is the operator of feature A (present in the feature model fm1)\n"
				+ "\n"
				+ "\n"
				+ "fm1 = FM (A : B [C] D; D : (E|F); C : (I|J|K)+; )\n"
				+ "\n"
				+ "op1 = operator fm1.D // edge between D and A: mandatory status\n"
				+ "\n"
				+ "assert (op1 eq MAND)\n"
				+ "\n"
				+ "op2 = operator fm1.F // edge between F and D: Xor \n"
				+ "\n"
				+ "assert (op2 eq XOR)\n"
				+ "\n"
				+ "op3 = operator C // edge between C and A : optional status\n"
				+ "\n" + "assert (op3 eq OPT)\n" + "\n"
				+ "op4 = operator K // edge between K and F: Or\n" + "\n"
				+ "assert (op4 eq OR)\n" + "\n" + "fC = parent fm1.I // C\n"
				+ "\n" + "op5 = operator fC \n" + "assert (op5 eq OPT)\n"
				+ "\n" + "fD = parent F  // D\n" + "\n" + "op6 = operator fD\n"
				+ "assert (op6 eq MAND)");
	}

	@Test
	public void testOperator2() throws Exception {
		_shell.parse("a = MAND\n" + "assert (a eq MAND)\n"
				+ "fm1 = FM (A : B C [D] ;)\n"
				+ "assert ((operator fm1.B) eq a)\n"
				+ "assert ((operator fm1.D) eq OPT)");
	}

}
