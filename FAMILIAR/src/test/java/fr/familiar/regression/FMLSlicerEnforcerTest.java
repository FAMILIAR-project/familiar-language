/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.regression;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.FMLSlicerUtilityTest;
import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.AllConfigsBDD;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Formula;

@RunWith(Parameterized.class)
public class FMLSlicerEnforcerTest extends FMLSlicerUtilityTest {

	public final static int N_TIMES = 1;

	@Parameterized.Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[N_TIMES][0]);
	}

	public FMLSlicerEnforcerTest() {
	}

	@Test
	public void repeatCorrecting() throws Exception {

		testCorrecting4();

		/*
		 * testCorrecting(); testCorrecting2(); testCorrecting3();
		 * testCorrecting4(); testCorrecting6(); testCorrecting5();
		 * testCorrecting7(); testCorrecting8(); testCorrecting6();
		 * testCorrecting10(); testCorrecting11(); testCorrecting12();
		 * testCorrecting9();
		 */
	}

	private void testCorrecting12() throws Exception {
		_shell.setVerbose(false);
		FeatureModelVariable fmvFoo = FM("foo", "FM (A : [B] E ; B : C; )");

		_shell.setVerbose(true);
		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fmvFoo, new HashSet<String>(Arrays.asList(new String[] { "B", "E" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		System.err.println("[[fm2]]=" + prettyConfigs(fm2));
		assertEquals(2, fm2.features().size()); // fake root

	}

	private void testCorrecting11() throws Exception {
		_shell.setVerbose(false);
		FeatureModelVariable fmvFoo = FM("foo", "FM (A : [B] ; B : C; )");

		_shell.setVerbose(true);
		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fmvFoo, new HashSet<String>(Arrays.asList(new String[] { "B", "C" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		System.err.println("[[fm2]]=" + prettyConfigs(fm2));
		assertEquals(3, fm2.features().size()); // fake root

	}

	private void testCorrecting10() throws Exception {
		_shell.setVerbose(false);
		FeatureModelVariable fmvFoo = FM("foo", "FM (A : B ; B : C;)");

		_shell.setVerbose(true);
		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fmvFoo, new HashSet<String>(Arrays.asList(new String[] { "C" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		System.err.println("[[fm2]]=" + prettyConfigs(fm2));
		assertEquals(1, fm2.features().size());

	}

	private void testCorrecting9() throws Exception {
		_shell.setVerbose(false);
		FeatureModelVariable fmvFoo = FM("foo", "FM (A : B C ;)");

		_shell.setVerbose(true);
		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fmvFoo, new HashSet<String>(Arrays.asList(new String[] { "B", "C" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		System.err.println("[[fm2]]=" + prettyConfigs(fm2));
		assertEquals(3, fm2.features().size()); // fake root needed

	}

	private void testCorrecting6() throws Exception {

		_shell.setVerbose(false);
		_shell.parse("fm1 = " + _fmASERunningExample);
		FeatureModelVariable fm1 = getFMVariable("fm1");
		System.err.println("#fm1=" + fm1.counting());
		_shell.setVerbose(true);
		FeatureModelVariable fm4 = new FMSlicerBDD(_builder).sliceFM(fm1, new HashSet<String>(Arrays.asList(new String[] { "S", "T", "D",
		"E" })), SliceMode.INCLUDING);
		System.err.println("fm4=" + fm4.toString());
		System.err.println("[[fm4]]=" + prettyConfigs(fm4));
		assertEquals(4, fm4.features().size());

	}

	private void testCorrecting7() throws Exception {

		_shell.setVerbose(false);
		_shell.parse("fm1 = " + _fmASERunningExample);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);
		FeatureModelVariable fm2Bis = new FMSlicerBDD(_builder).sliceFM(fm1, new HashSet<String>(Arrays
		.asList(new String[] { "W", "E", "F" })), SliceMode.INCLUDING);
		assertEquals(3, fm2Bis.features().size());
		System.err.println("fm2bis=" + fm2Bis);

		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fm1, new HashSet<String>(Arrays.asList(new String[] { "E", "F" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		assertEquals(3, fm2.features().size()); // + fakeRoot

	}

	private void testCorrecting8() throws Exception {

		_shell.setVerbose(false);
		_shell.parse("fm1 = " + _fmASERunningExample);
		FeatureModelVariable fm1 = getFMVariable("fm1");

		_shell.setVerbose(true);
		FeatureModelVariable fm2 = new FMSlicerBDD(_builder).sliceFM(fm1, new HashSet<String>(Arrays
		.asList(new String[] { "E", "D", "F" })), SliceMode.INCLUDING);
		System.err.println("fm2=" + fm2);
		System.err.println("[[fm2]]=" + prettyConfigs(fm2));
		assertEquals(4, fm2.features().size());

	}

	public void testCorrecting() {
		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : [B] [C] ; )\n"
				+ "fm2 = FM (P : (R|S) ; )\n"
				+ "cst12 = constraints (R implies (B and !C); S implies (C and !B) ; )\n"
				+ "fm3 = aggregate { fm1 fm2 } withMapping cst12\n"
				+ "\n"
				+ "// once fm1 is related to fm2 through constraints, its set of configurations is modified and reduced\n"
				+ "// we produce fm4, roughly a \"corrected\" view of fm1 and its set of configurations\n"
				+ "fm4 = slice fm3 including fm1.*   \n"
				+ "assert (fm4 eq FM (A : (B|C); ))");

	}

	public void testCorrecting2() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D] ; C : [E] [F] ; )\n"
				+ "fm2 = FM (P : (R|S)+ ; )\n"
				+ "cst12 = constraints (R implies (E and !F); S implies (F and !E) ; )\n"
				+ "fm3 = aggregate { fm1 fm2 } withMapping cst12\n"
				+ "\n"
				+ "// once fm1 is related to fm2 through constraints, its set of configurations is modified and reduced\n"
				+ "// we produce fm4, roughly a \"corrected\" view of fm1 and its set of configurations\n"
				+ "fm4 = slice fm3 including fm1.* \n");

		FeatureModelVariable fm4 = getFMVariable("fm4");
		stats(fm4);
		FeatureModelVariable fmvExpected = FM("fmSliceExpected",
				"FM (A : B C [D]; C : (E|F); )");

		// [[D, F, A, B, C], [D, E, A, B, C], [E, A, B, C], [A, B, C], [F, A, B,
		// C]]
		// [[D, F, A, B, C], [E, A, B, C], [D, E, A, B, C], [F, A, B, C]]
		assertFormulaEquals(fmvExpected.getFormula(), fm4.getFormula());
		stats(fm4);

		assertHierarchyEquals(fmvExpected, fm4);

	}

	public void testCorrecting3() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D] ; C : [E] [F] ; E implies D ; )\n"
				+ "fm2 = FM (P : (R|S)+ ; )\n"
				+ "cst12 = constraints (R implies E ; S implies (F and !E) ; D implies !F ; )\n"
				+ "fm3 = aggregate { fm1 fm2 } withMapping cst12\n"
				+ "\n"
				+ "// once fm1 is related to fm2 through constraints, its set of configurations is modified and reduced\n"
				+ "// we produce fm4, roughly a \"corrected\" view of fm1 and its set of configurations\n"
				+ "fm4 = slice fm3 including fm1.* \n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		stats(fm1);
		FeatureModelVariable fm4 = getFMVariable("fm4");
		stats(fm4);
		FeatureModelVariable fmvExpected = FM("fmSliceExpected",
				"FM (A : B C [D]; C : (E|F); E <-> D ; )");

		// [[D, F, A, B, C], [D, E, A, B, C], [E, A, B, C], [A, B, C], [F, A, B,
		// C]]
		// [[D, F, A, B, C], [E, A, B, C], [D, E, A, B, C], [F, A, B, C]]
		assertFormulaEquals(fmvExpected.getFormula(), fm4.getFormula());
		assertHierarchyEquals(fmvExpected, fm4);

		System.out.println("fm4=" + fm4);

	}

	public void testCorrecting4() throws Exception {

		_shell.setVerbose(false);
		_shell.parse("fm1 = " + _fmASERunningExample);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		String c1 = prettyConfigs(fm1);

		System.err.println("[[fm1]] = " + c1);

		_shell.parse("v1 = fm1.A.* ++ { fm1.A }\n");
		assertEquals(6, getSetVariable("v1").size());
		_shell.parse("fm2 = slice fm1 including v1\n");

		FeatureModelVariable fm2 = getFMVariable("fm2");
		String c2 = prettyConfigs(fm2);
		System.err.println("fm2 = " + fm2);
		System.err.println("[[fm2]] = " + c2);

		FeatureModelVariable fmvExpected = FM("fmSliceExpected",
				"FM (A : B C [D]; C : (E|F); E <-> D ; )");

		FileSerializer.write("output/" + fm1.getIdentifier() + ".html",
				fm1.toProtovis()); // _fmID

		assertFormulaEquals(fmvExpected.getFormula(), fm2.getFormula());
		assertHierarchyEquals(fmvExpected, fm2);

		_shell.parse("v2 = fm1.P.* ++ { fm1.P } \n");
		assertEquals(3, getSetVariable("v2").size());
		_shell.parse("fm3 = slice fm1 including v2\n");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		String c3 = prettyConfigs(fm3);
		System.err.println("fm3 = " + fm3);
		System.err.println("[[fm3]] = " + c3);

		Set<Set<String>> sFM1 = FMLUtils.setConfigToSetStr(fm1.configs());
		Set<Set<String>> sFM3 = new HashSet<Set<String>>();
		for (Set<String> s1 : sFM1) {
			Set<String> sRestr = Sets.intersection(s1, getSetVariable("v2")
					.names());
			sFM3.add(sRestr);
		}

		System.err.println("sFM3=" + sFM3);

		_shell.parse("v3 = fm1.P.* ++ { fm1.P } ++ { fm1.W }\n");
		assertEquals(4, getSetVariable("v3").size());
		_shell.parse("fm4 = slice fm1 including v3\n");

		FeatureModelVariable fm4 = getFMVariable("fm4");
		String c4 = prettyConfigs(fm4);
		System.err.println("fm4 = " + fm4);
		System.err.println("[[fm4]] = " + c4);

		/*
		 * _shell.parse("fm4 = slice fm1 including fm1.*\n");
		 * FeatureModelVariable fm4 = getFMVariable("fm4"); String c4 =
		 * prettyConfigs(fm4); System.err.println("fm4 = " + fm4);
		 * System.err.println("[[fm4]] = " + c4);
		 */

	}

	public void testCorrecting5() throws Exception {

		_shell.setVerbose(false);
		_shell.parse("fm1 = " + _fmASERunningExample);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		String c1 = prettyConfigs(fm1);

		System.err.println("[[fm1]] = " + c1);

		// FIXME: add a synthetic root, dude!
		// _shell.parse("v1 = { fm1.S } ++ { fm1.F } ++ { fm1.U } ++ { fm1.E }\n");
		// _shell.parse("v1 = { fm1.S } ++ { fm1.D } ++ { fm1.U } ++ { fm1.F }\n");

		// WORKS
		// _shell.parse("v1 = { fm1.W } ++ { fm1.S } ++ { fm1.D } ++ { fm1.U } ++ { fm1.F }\n");
		// _shell.parse("v1 = { fm1.W } ++ { fm1.S } ++ { fm1.F } ++ { fm1.U } ++ { fm1.E }\n");
		_shell.parse("v1 = { fm1.W } ++ { fm1.S } ++ { fm1.D } ++ { fm1.U } ++ { fm1.F }\n");
		assertEquals(5, getSetVariable("v1").size());
		Formula<String> fla = new FMSlicerBDD(_builder).sliceFormula(fm1,
				getSetVariable("v1").names(), SliceMode.INCLUDING);
		System.err.println("slicedFla="
				+ new AllConfigsBDD(_builder).process(fla));
		_shell.parse("fm2 = slice fm1 including v1\n");

		FeatureModelVariable fm2 = getFMVariable("fm2");
		System.err.println("isValid fm2 = " + fm2.isValid());
		String c2 = prettyConfigs(fm2);
		System.err.println("fm2 = " + fm2);
		System.err.println("[[fm2]] = " + c2);

	}

}
