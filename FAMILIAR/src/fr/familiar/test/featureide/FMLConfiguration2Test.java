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
package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import de.ovgu.featureide.fm.core.FeatureModel;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class FMLConfiguration2Test extends FMLTest {

	@Test
	public void testConfiguration1() throws Exception {

		_shell.parse("fm1 = FM (A : (B|C); B : D; C -> D ;)\n"
				+ "bf1 = isValid fm1\n" + "c = configuration fm1\n"
				+ "nexpected = counting fm1\n" + "s = configs fm1");

	}

	@Test
	public void testConfiguration2() throws Exception {

		_shell.parse("fm1 = FM (A: B [C] [D] E; D : (F|G)+; E : (I|J|K|L)+ ; )\n"
				+ "c1 = configuration fm1\n"
				+ "select C in c1\n"
				+ "deselect D in c1\n"
				+ "dc1 = deselectedF c1 // contains D, F and G\n"
				+ "dc1Expected = { fm1.D fm1.F fm1.G }\n"
				+ "assert (dc1 eq dc1Expected)\n") ;
		
		_shell.parse("\n"
				+ "\n"
				+ "deselect J in c1\n"
				+ "deselect K in c1\n"
				+ "deselect L in c1\n"
				+ "sc1 = selectedF c1 // contains I\n"
				+ "sc1Expected = { fm1.A fm1.B fm1.C fm1.E fm1.I }\n"
				+ "assert (sc1 eq sc1Expected)\n") ;
		
		_shell.parse("\n"
				+ "// at this step all features are either selected or deselected\n"
				+ "\n"
				+ "bcomplete = isComplete c1\n"
				+ "TRUE = true\n"
				+ "assert (bcomplete eq TRUE)\n");
		
		
		
		
		_shell.parse("\n"
				+ "// stated differently: there is no feature unselected\n"
				+ "\n"
				+ "uc1 = unselectedF c1 // empty\n"
				+ "uc1isEmpty = setIsEmpty uc1\n"
				+ "assert (uc1isEmpty eq TRUE)\n");
		
		ConfigurationVariable c1 = getConfigurationVariable("c1") ; 
		System.err.println("c1=" + c1.getSpecificValue());
		c1.applySelection("C", OpSelection.UNSELECT);
		System.err.println("c1=" + c1.getUnselected());
		
		_shell.parse("\n"
				+ "unselect C in c1\n"
				+ "u2c1 = unselectedF c1\n"
				+ "nu2c1 = size u2c1\n"
				+ "ONE = 1\n"
				+ "assert (nu2c1 eq ONE)\n");
		
		
		_shell.parse("bcomplete2 = isComplete c1\n"
				+ "// TODO: fails?\n"
				+ "//assert (bcomplete2 neq TRUE)\n" + "");

		SetVariable sc1 = getSetVariable("sc1");
		Set<Variable> sc1vars = sc1.getVars();

		for (Variable ft : sc1vars)
			assertTrue(ft instanceof FeatureVariable);

		Set<String> sc1ToStr = setVariabletoString(sc1);
		sc1ToStr.contains("I");

	}

	
	/**
	 * the test does not pass if executed with others (but works individually / for all tests of the class)
	 * @throws Exception
	 */
	@Ignore
	@Test
	public void testConfiguration3() throws Exception {

		_shell.parse("fm1 = FM (A : [B] [C] D [E] ; )\n"
				+ "sfm1 = configs fm1\n" + "\n" + "n1 = size sfm1\n"
				+ "n2 = counting fm1\n" + "assert (n1 eq n2) \n" + "\n"
				+ "fm2 = FM (A : [C] D [E] [B] ; )\n" + "sfm2 = configs fm2\n"
				+ "n3 = size sfm2\n" + "n4 = counting fm2\n"
				+ "assert (n3 eq n4)\n" + "assert (n1 eq n4)\n"
				+ "assert (n3 eq n1)\n" + "\n" + "fm3 = FM (A : B C [D] ; )\n"
				+ "sfm3 = configs fm3\n"
				+ "firstConfig = { fm3.A fm3.B fm3.C }\n"
				+ "secondConfig = { fm3.A fm3.B fm3.C fm3.D }\n"
				+ "sfm3Expected = setEmpty\n"
				+ "setAdd sfm3Expected firstConfig \n"
				+ "setAdd sfm3Expected secondConfig\n" + "\n"
				+ "assert (sfm3Expected eq sfm3)\n" + "\n"
				+ "fm4 = FM (A : B C [D] ; )\n"
				+ "map fm4 with constraints (!B -> !C ;)\n"
				+ "bf4 = isValid fm4\n" + "//FALSE = false\n"
				+ "//assert (bf4 eq FALSE)\n" + "convert fm4 into featureide\n"
				+ "sfm4 = configs fm4\n" + "//bfm4 = setIsEmpty sfm4\n"
				+ "//TRUE = true\n" + "//assert (bfm4 eq TRUE)\n" + "\n"
				+ "fm5 = FM (A : B C [D] ; )\n"
				+ "map fm5 with constraints (B -> D ; )\n"
				+ "nfm5 = counting fm5\n" + "ONE = 1\n"
				+ "assert (ONE eq nfm5)");

	}

	@Test
	public void testConfiguration4() throws Exception {

		_shell.parse("fm1 = FM (A : [B] C [D] E ; E : (J|K|L); D : (W|X|Y)+ ; )\n"
				+ "fm2 = copy fm1\n"
				+ "fm3 = copy fm1\n"
				+ "\n"
				+ "c1 = configuration fm1\n"
				+ "select B J in c1\n"
				+ "\n"
				+ "c2 = configuration fm2\n"
				+ "deselect B in c2\n"
				+ "c3 = configuration fm3");

	}

	@Test
	public void testAutoSelect1() throws Exception {

		_shell.parse("fm1 = FM (A: [B] [C] [D] E ; )\n"
				+ "c1 = configuration fm1\n"
				+ "\n"
				+ "sc1 = selectedF c1 // A, E\n"
				+ "\n"
				+ "nsc1 = size sc1 \n"
				+ "nsc1Expected = 2\n"
				+ "assert (nsc1 eq nsc1Expected)\n"
				+ "\n"
				+ "uc1 = unselectedF c1 // B, C, D\n"
				+ "\n"
				+ "nuc1 = size uc1 \n"
				+ "nuc1Expected = 3 \n"
				+ "assert (nuc1 eq nuc1Expected)\n"
				+ "\n"
				+ "autoSelect c1 MAX // A, B, C, D, E\n"
				+ "\n"
				+ "sc11 = selectedF c1\n"
				+ "nsc11 = size sc11\n"
				+ "nftfm1 = size fm1.*\n"
				+ "assert (nsc11 eq nftfm1)\n"
				+ "\n"
				+ "fm2 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; )\n"
				+ "\n"
				+ "c2 = configuration fm2\n"
				+ "\n"
				+ "// TODO!\n"
				+ "//autoSelect c2 MIN // U, X, Y, F or G or H, I or J or K or L are selected\n"
				+ "\n"
				+ "sc2 = selectedF c2\n"
				+ "nsc2 = size sc2\n"
				+ "nsc2Expected = 5\n"
				+ "//assert (nsc2 eq nsc2Expected) \n"
				+ "\n"
				+ " \n"
				+ "c3 = configuration fm2\n"
				+ "autoSelect c3 MAX // U, X, Y, Z, W, F or G or H, I, J, K, L are selected\n"
				+ "\n"
				+ "sc3 = selectedF c3\n"
				+ "nsc3 = size sc3\n"
				+ "nsc3Expected = 10\n"
				+ "assert (nsc3 eq nsc3Expected) \n"
				+ "\n"
				+ "c6 = configuration fm2\n"
				+ "select Z in c6\n"
				+ "select I in c6\n"
				+ "deselect W in c6\n"
				+ "autoSelect c6 RANDOM // populate c6 with current selected/deselected features\n"
				+ "\n"
				+ "\n"
				+ "c7 = configuration fm2\n"
				+ "deselect Z in c7\n"
				+ "deselect W in c7\n"
				+ "select F in c7\n"
				+ "autoSelect c7 // by default, autoSelect uses the RANDOM mode\n"
				+ "\n"
				+
				// TODO
				"bc7 = isComplete c7\n"
				+ "bbc7 = isValid c7\n"
				+ "assert (bc7)\n"
				+ "assert (bbc7)\n"
				+ "\n"
				+ "fm3 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; W : [T] [A] [B]; )\n"
				+ "\n"
				+ "c10 = configuration fm3\n"
				+ "deselect Z W J K L in c10 // deselect several features at the same time\n"
				+ "\n"
				+ "select F I in c10 // select several features at the same time\n"
				+ "\n" + "bc10 = isComplete c10\n" + "assert (bc10)");

	}

	@Test
	public void testAutoSelect2() throws Exception {

		_shell.parse("fm1 = FM (A: [B] [C] [D] E ; )\n"
				+ "c1 = configuration fm1\n"
				+ "\n"
				+ "sc1 = selectedF c1 // A, E\n"
				+ "nsc1 = size sc1 \n"
				+ "nsc1Expected = 2\n"
				+ "assert (nsc1 eq nsc1Expected)\n"
				+ "\n"
				+ "uc1 = unselectedF c1 // B, C, D\n"
				+ "\n"
				+ "nuc1 = size uc1 \n"
				+ "nuc1Expected = 3 \n"
				+ "assert (nuc1 eq nuc1Expected)\n"
				+ "\n"
				+ "autoSelect c1 MAX // A, B, C, D, E\n"
				+ "nsc11 = selectedF c1\n"
				+ "nftfm1 = size fm1.*\n"
				+ "assert (size nsc11 eq nftfm1)\n"
				+ "\n"
				+ "fm2 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; )\n"
				+ "\n"
				+
				// "c2 = configuration fm2\n" +
				// "autoSelect c2 MIN // U, X, Y, F or G or H, I or J or K or L\n"
				// +
				// "sc2 = selectedF c2\n" +
				// "nsc2 = size sc2\n" +
				// "nsc2Expected = 5\n" +
				// "assert (nsc2 eq nsc2Expected) \n" +
				"\n"
				+ " \n"
				+ "c3 = configuration fm2\n"
				+ "autoSelect c3 MAX // U, X, Y, Z, W, F or G or H, I, J, K, L\n"
				+ "sc3 = selectedF c3\n"
				+ "nsc3 = size sc3\n"
				+ "nsc3Expected = 10\n"
				+ "assert (nsc3 eq nsc3Expected) \n"
				+ "\n"
				+ "c6 = configuration fm2\n"
				+ "select Z in c6\n"
				+ "select I in c6\n"
				+ "deselect W in c6\n"
				+ "autoSelect c6 RANDOM // populate c6 with current selected/deselected features\n"
				+ "\n"
				+ "c7 = configuration fm2\n"
				+ "deselect Z in c7\n"
				+ "deselect W in c7\n"
				+ "select F in c7\n"
				+ "autoSelect c7 // by default, autoSelect uses the RANDOM mode\n"
				+ "bc7 = isComplete c7\n"
				+ "bbc7 = isValid c7\n"
				+ "assert (bc7)\n"
				+ "assert (bbc7)\n"
				+ "\n"
				+ "c8 = configuration fm2\n"
				+ "deselect W in c7 \n"
				+ "bc8 = isComplete c8 \n"
				+ "FALSE = false\n"
				+ "assert (bc8 eq FALSE)\n"
				+ "\n"
				+ "fm3 = FM (U: X Y [Z] [W]; X : (F|G|H); Y : (I|J|K|L)+ ; W : [T] [A] [B]; )\n"
				+ "\n" + "c10 = configuration fm3\n" + "deselect Z W in c10");

		SetVariable nsc11 = getSetVariable("nsc11");
		IntegerVariable nftfm1 = getIntegerVariable("nftfm1");

		FeatureModelVariable fm1 = getFMVariable("fm1");

		assertEquals(fm1.features().size(), nftfm1.getV());
		assertEquals(fm1.features().size(), nsc11.size());

	}
	
	@Test
	public void testConfigurationConflict() throws Exception {

		// _shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : [B] [C] D ; D : (E|F|G) ; C : (I|J|K|L)+ ; )\n"
				+ "\n"
				+ "c1 = configuration fm1\n" // everything goes well!
				+ "select B in c1\n"
				+ "fm2 = FM (A : [B] [C] D ; D : (E|F|G) ; C : (I|J|K|L) ; )\n" +
				"c2 = configuration fm1\n") ; 
		
		_shell.setVerbose(true);
		_shell.parse("select B in c2\n" + // conflicts here since B may refer to fm1.B and fm2.B
				"") ; 
				 
		
		ConfigurationVariable c1 = getConfigurationVariable("c1");
		System.err.println("c1=" + c1.getValue());
		ConfigurationVariable c2 = getConfigurationVariable("c2");
		System.err.println("c2=" + c2.getValue());
				
	}

	@Test
	public void testAsFM1() throws Exception {

		// _shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : [B] [C] D ; D : (E|F|G) ; C : (I|J|K|L)+ ; )\n"
				+ "\n"
				+ "c1 = configuration fm1\n"
				+ "deselect C in c1\n"
				+ "deselect B in c1\n"
				+ "fmConvert = asFM c1\n"
				+ "\n"
				+ "println fmConvert, c1\n"
				+ "\n"
				+ "fmExpected = FM (A : D ; D : (E|F|G) ; )\n"
				+ "cmp = compare fmExpected fmConvert\n"
				+ "assert (cmp eq REFACTORING)\n"
				+ "\n"
				+ "\n"
				+ "// another example\n"
				+ "\n"
				+ "fm2 = FM (A : [B] [C] D ; D : (E|F|G) ; C : (I|J|K|L) ; )\n"
				+ "\n"
				+ "c2 = configuration fm2\n"
				+ "select C in c2\n"
				+ "select E in c2\n"
				+ "deselect B in c2\n"
				+ "fmConvert2 = asFM c2\n"
				+ "\n"
				+ "fmExpected2 = FM (A : C D ; D : E ; C : (I|J|K|L) ; )\n"
				+ "cmp2 = compare fmExpected2 fmConvert2\n"
				+ "println c2\n"
				+ "assert (cmp2 eq REFACTORING)\n"
				+ "\n"
				+ "\n"
				+ "// yet another example\n"
				+ "\n"
				+ "fm3 = FM (A : [B] [C] D ; D : (E|F|G)+ ; C : (I|J|K|L)+ ; )\n"
				+ "\n"
				+ "c3 = configuration fm3\n"
				+ "select B C in c3 // B, C and D are selected\n"
				+ "deselect E F in c3 // only G\n"
				+ "deselect J K L in c3 // only I\n"
				+ "fmConvert3 = asFM c3\n"
				+ "\n"
				+ "fmExpected3 = FM (A : B C D ; D : G ; C : I ; )\n"
				+ "cmp3 = compare fmExpected3 fmConvert3\n" 
				+ "assert (cmp3 eq REFACTORING)"
				);

		FeatureModelVariable fmConvert = getFMVariable("fmConvert");
		FeatureModel fm = new FMLtoFeatureIDE(fmConvert).convert();
		assertNotNull(fm);

		assertRefactoring("fmExpected", "fmConvert");
		assertRefactoring("fmConvert", "fmExpected");
/*
		assertRefactoring("fmExpected2", "fmConvert2");
		assertRefactoring("fmConvert2", "fmExpected2");
*/
		/*
		assertRefactoring("fmExpected3", "fmConvert3");
		assertRefactoring("fmConvert3", "fmExpected3");*/

	}

}
