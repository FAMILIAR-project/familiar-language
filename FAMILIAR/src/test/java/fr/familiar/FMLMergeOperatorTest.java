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
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.AllConfigsBDD;
import fr.familiar.operations.FDOverApproximationStrategy;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.Mode;
import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.StringVariable;
import gsd.synthesis.Formula;

/**
 * @author mathieuacher
 * 
 */
public class FMLMergeOperatorTest extends FMLTest {

	@Test
	public void testECMFA1() throws Exception {

		String strFM1 = "FM (A : (B|C); )";
		String strFM2 = "FM (A : B [C]; )";
		String strExpected = "FM (A : B; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		_shell.setVerbose(true);
		assertMerge(lfms, strExpected, "intersection");

	}

	@Test
	public void testECMFA2() throws Exception {
		String base = "FM (A : [B] C F ; B : (D|E) ; )";
		String aspect = "FM (A : [B] C [F] ; B : [D] G ; D : (H|I); )";

		String expected = "FM (A : C F; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(base);
		lfms.add(aspect);
		assertMerge(lfms, expected, "intersection");

	}

	@Test
	public void testECMFA3() throws Exception {
		String base = "FM (A : [B] C [F] ; B : (D|E) ; D <-> F; E <-> F; )";
		String aspect = "FM (A : [B] C [F] [I] ; B : (G|H) ; )";

		String expected = "FM (A : C ; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(base);
		lfms.add(aspect);
		assertMerge(lfms, expected, "intersection");

	}

	@Test
	public void testECMFA4() throws Exception {

		String fmep1 = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT) ; Format : Anonymized [Header]; Header : (DICOM|Nifti); )";
		String fmep2 = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT|PET) ; Format : [Anonymized] [Header]; Header : DICOM ; )";
		String fmep3 = "FM (MedicalImage : Modality Format ; Modality : (SPEC|PET) ; Format : [Anonymized] [Header]; Header : (DICOM|Nifti)+ ; ) ";

		String fmep2bis = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT) ; Format : Anonymized [Header]; Header : DICOM ; ) ";
		String fmep3bis = "FM (MedicalImage : Modality Format ; Modality : PET ; Format : [Anonymized] [Header]; Header : DICOM ; ) ";

		List<String> lfms = new ArrayList<String>();
		lfms.add(fmep1);
		lfms.add(fmep2);
		_shell.setVerbose(true);
		assertMerge(lfms, fmep2bis, "intersection");

		List<String> lfms2 = new ArrayList<String>();
		lfms2.add(fmep2);
		lfms2.add(fmep3);
		assertMerge(lfms2, fmep3bis, "intersection");

	}
	
	@Test
	public void testMergeThreeSteps() throws Exception {
		String f1 = "FM(widget:Name Comparison Proportion Value Discrete Dimension; Name:\"Pie Chart\"; Dimension:OneD;)";
		String f2 = "FM(widget:Name Comparison Proportion Value Discrete Dimension; Name:\"Funnel Chart\"; Dimension:OneD;)";
		String f3 = "FM(widget:Name Comparison Relationship Patterns DataOverTime Value Variations Extremum Dimension; Name:\"Line Chart\"; Dimension:TwoD;)";
		String f4 = "FM(widget:Name Comparison Relationship Patterns DataOverTime Value Variations Extremum Dimension; Name:\"Smoothed Line Chart\"; Dimension:TwoD;)";
		String f5 = "FM(widget:Name Proportion Relationship Patterns DataOverTime Value Variations Dimension; Name:\"Area Chart\"; Dimension:TwoD;)";
		String f6 = "FM(widget:Name Relationship Distribution Patterns Range DataOverTime Value Discrete Dimension; Name:\"Ohlc Chart\"; Dimension:TwoD;)";
		
		List<String> lf = new ArrayList<String>();
		lf.add(f1);
		lf.add(f2);
		lf.add(f3);
		lf.add(f4);
		lf.add(f5);
		lf.add(f6);
		
		List<FeatureModelVariable> lfm = new ArrayList<FeatureModelVariable>();
		
		for (String s : lf) {
			int i = 0;
			_shell.parse("f"+i+" = "+s);
			lfm.add(getFMVariable("f"+i));
			i++;
		}
		
		FeatureModelVariable fmvMergedF = new FMLMergerBDD(lfm, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion);
		
		assertEquals(6.0, fmvMergedF.counting(), 0.001);
		
		String r7 = "FM(widget:Name Comparison Relationship Patterns DataOverTime Value Discrete Variations Dimension; Name:\"Step Chart\"; Dimension:TwoD;)";
		String r8 = "FM(widget:Name Comparison Relationship Patterns DataOverTime Value Discrete Extremum Dimension; Name:\"Bar Chart\"; Dimension:TwoD;)";
		String r9 = "FM(widget:Name Comparison Relationship Probability Distribution Patterns Range DataOverTime Value Discrete Variations Extremum Dimension; Name:\"Column Chart\"; Dimension:TwoD;)";
		String r10 = "FM(widget:Name Proportion Relationship Probability Distribution Patterns DataOverTime Value Discrete Variations Extremum Dimension; Name:\"XY/Bubble Chart\"; Dimension:ThreeD;)";
		String r11 = "FM(widget:Name Patterns Range Value Dimension; Name:\"Angular Gauge\"; Dimension:OneD;)";
		String r12 = "FM(widget:Name Comparison Relationship Distribution Patterns Value Discrete Extremum Dimension; Name:\"Radar Chart\"; Dimension:OneD;)";
		
		List<String> lr = new ArrayList<String>();
		lr.add(r7);
		lr.add(r8);
		lr.add(r9);
		lr.add(r10);
		lr.add(r11);
		lr.add(r12);
		
		List<FeatureModelVariable> lrm = new ArrayList<FeatureModelVariable>();
		
		for (String s : lr) {
			int i = 0;
			_shell.parse("r"+i+" = "+s);
			lrm.add(getFMVariable("r"+i));
			i++;
		}
		
		FeatureModelVariable fmvMergedR = new FMLMergerBDD(lrm, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion);
		
		assertEquals(6.0, fmvMergedR.counting(), 0.001);
		
		List<FeatureModelVariable> lfinal = new ArrayList<FeatureModelVariable>();
		lfinal.add(fmvMergedF);
		lfinal.add(fmvMergedR);
		
		FeatureModelVariable fmvMergedFinal = new FMLMergerBDD(lfinal, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(Mode.StrictUnion);
		assertEquals(12.0, fmvMergedFinal.counting(), 0.001);
	}

	@Test
	public void testUnionOR1() throws Exception {

		String strFM1 = "FM (A : (B|C)+; )";
		String strFM2 = "FM (A : (B|C); )";
		String strExpected = "FM (A : (B|C)+; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		assertMerge(lfms, strExpected, "sunion");

	}
	
	@Test
	public void testUnionOR1bis() throws Exception {

		FeatureModelVariable fmv1 = FM ("fm1", "FM (A : (B|C)+; )") ; 
		FeatureModelVariable fmv2 = FM ("fm2", "FM (A : (B|C)+; )") ; 
		
		_shell.setVerbose(true);
		FeatureModelVariable fmv3 = fmv1.merge(fmv2, Mode.StrictUnion);
		assertEquals(Comparison.REFACTORING, fmv3.compare(fmv2));
		System.err.println("fmv3=" + fmv3);

	}

	@Test
	public void testUnionOR2() throws Exception {

		String strFM1 = "FM (A : (B|C)+; )";
		String strFM2 = "FM (A : (B|C); )";
		String strFM3 = "FM (A : [B] [C]; B | C ; )";
		String strExpected = "FM (A : (B|C)+; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		assertMerge(lfms, strExpected, "sunion");

	}

	@Test
	public void testUnionOR3() throws Exception {

		String strFM1 = "FM (A : (B|C); )";
		String strFM2 = "FM (A : (B|C); )";
		String strFM3 = "FM (A : [B] [C]; B | C ; )";
		String strExpected = "FM (A : (B|C)+; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		assertMerge(lfms, strExpected, "sunion");

	}

	@Test
	public void testUnionOR5() throws Exception {

		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C)+; )");
		FeatureModelVariable fmv2 = FM("fm2", "FM (A : (C|D)+; )");

		List<FeatureModelVariable> lfmvs = new ArrayList<FeatureModelVariable>();
		lfmvs.add(fmv1);
		lfmvs.add(fmv2);
		
		_shell.setVerbose(true);

		FeatureModelVariable fmvR = new FMLMergerBDD(lfmvs, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(
				Mode.StrictUnion);

		System.err.println("fmvR=" + fmvR);

	}

	@Test
	public void testUnionXOR1() throws Exception {

		String strFM1 = "FM (A : (B|C); )";
		String strFM2 = "FM (A : (B|C); )";
		String strFM3 = "FM (A : [B] [C]; B | C ; !B | !C ; )";
		String strExpected = "FM (A : (B|C); )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		_shell.setVerbose(true);
		assertMerge(lfms, strExpected, "sunion");

	}

	@Test
	public void testIntersectionXOR1() throws Exception {

		String strFM1 = "FM (A : (B|C)+; )";
		String strFM2 = "FM (A : (B|C); )";
		String strExpected = "FM (A : (B|C); )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		assertMerge(lfms, strExpected, "intersection");

	}

	@Test
	public void testIntersectionXOR2() throws Exception {

		String strFM1 = "FM (A : (B|C)+; )";
		String strFM2 = "FM (A : (B|C); )";
		String strFM3 = "FM (A : (B|C|E)+; )";
		String strFM4 = "FM (A : (B|C|D); )";
		String strExpected = "FM (A : (B|C); )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		lfms.add(strFM4);
		assertMerge(lfms, strExpected, "intersection");

	}

	@Test
	public void testIntersectionOR1() throws Exception {
		String strFM1 = "FM (A : (B|C)+; )";
		String strFM2 = "FM (A : (B|C)+; )";
		String strFM3 = "FM (A : (B|C|E)+; )";
		String strFM4 = "FM (A : (B|C|D)+; )";
		String strExpected = "FM (A : (B|C)+; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		lfms.add(strFM4);

		assertMerge(lfms, strExpected, "intersection");
	}

	@Test
	public void testIntersectionAND1() throws Exception {

		String strFM1 = "FM (A : B C; )";
		String strFM2 = "FM (A : B [C]; )";
		String strExpected = "FM (A : B C; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		assertMerge(lfms, strExpected, "intersection");

	}

	@Test
	public void testIntersectionAND2() throws Exception {

		String strFM1 = "FM (A : B C; )";
		String strFM2 = "FM (A : B [C]; )";
		String strFM3 = "FM (A : B [C] [D]; )";
		String strFM4 = "FM (A : B C [E]; )";
		String strExpected = "FM (A : B C ; )";

		List<String> lfms = new ArrayList<String>();
		lfms.add(strFM1);
		lfms.add(strFM2);
		lfms.add(strFM3);
		lfms.add(strFM4);
		assertMerge(lfms, strExpected, "intersection");

	}

	@Test
	public void testUnionOR4() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (I|J)+; )\n"
				+ "fm2 = FM (A : B C [D]; D : (E|F|G)+; C : (I|J) ; )\n"
				+ "fm3 = FM (A : B C [D]; D : (E|F|G)+; C : (I|J)+ ; )\n"
				+ "fm4 = merge sunion { fm1 fm2 fm3 }\n"
				+ "fm5 = FM (A : B C [D]; D : (E|F|G)+; C : (I|J)+ ; )\n"
				+ "cmp45 = compare fm4 fm5\n" + "b45 = fm4 eq fm5");

		FeatureModelVariable fm4 = getFMVariable("fm4");
		System.err.println("fm4=" + fm4.getFm());

		FeatureModelVariable fm5 = getFMVariable("fm5");
		assertFMsEqual(fm5.getFm(), fm4.getFm());

		StringVariable cmp45 = getStringVariable("cmp45");
		assertTrue("fm4 is a refactoring of fm5",
				cmp45.getVal().equals("REFACTORING"));

		BooleanVariable b45 = getBooleanVariable("b45");
		// note: this is not contrary to the "contract" of the merge
		// assertTrue ("fm4 is equal to fm5", b45.isTrue());

		_shell.parse("s4 = convert fm4 into featureide\n");
		StringVariable s4 = getStringVariable("s4");

		System.err.println("s4=" + s4);

	}

	@Test
	public void testUnionAND5() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B [C] ;)\n"
				+ "fm2 = FM (A : B ; B : [C]; )\n"
				+ "fm3 = merge sunion { fm1 fm2 }\n" + "");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");

		assertFMsEqual(fm1.getFm(), fm3.getFm());
		assertFMsEqual(fm2.getFm(), fm3.getFm());

		System.err.println("fm3=" + fm3.getFm().toString());

	}

	@Test
	public void testUnionAND6() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C E ; E : [F] ; )\n"
				+ "fm2 = FM (A : B ; B : C; C : E; E : [F] ; )\n"
				+ "fm3 = merge sunion { fm1 fm2 }\n" + "");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");

		assertFMsEqual(fm1.getFm(), fm3.getFm());
		assertFMsEqual(fm2.getFm(), fm3.getFm());

		System.err.println("fm3=" + fm3.getFm().toString());

	}

	@Test
	public void testUnionAND7() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C E ; E : F ; )\n"
				+ "fm2 = FM (A : B ; B : C; C : E; E : [F] ; )\n"
				+ "fm3 = merge sunion { fm1 fm2 }\n" + "");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");

		// NOPE: assertFMsEqual(fm1.getFm(), fm3.getFm());
		assertFMsEqual(fm2.getFm(), fm3.getFm());

		System.err.println("fm3=" + fm3.getFm().toString());

	}

	@Test
	public void testUnionAND8() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B C [E] ; E : F ; )\n"
				+ "fm2 = FM (A : B ; B : C; C : E; E : [F] ; )\n"
				+ "fm3 = FM (A : B ; B : C; )\n"
				+ "fm4 = merge sunion { fm1 fm2 fm3 fm2 fm1 fm1 fm3 fm3 fm3 }\n"
				+ "fm4expected = FM (A : B C [E] ; E : [F] ; )\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		FeatureModelVariable fm2 = getFMVariable("fm2");
		FeatureModelVariable fm3 = getFMVariable("fm3");
		FeatureModelVariable fm4 = getFMVariable("fm4");
		FeatureModelVariable fm4expected = getFMVariable("fm4expected");

		System.err.println("fm4=" + fm4.getFm().toString());

		// NOPE: assertFMsEqual(fm1.getFm(), fm3.getFm());
		assertFMsEqual(fm4expected.getFm(), fm4.getFm());

	}

	@Test
	public void testIntersectionAND3() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B [C] ;)\n"
				+ "fm2 = FM (A : B ; B : [C]; )\n"
				+ "fm3 = merge intersection { fm1 fm2 }\n"
				+ "fm3expected = FM (A : B C; )");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		FeatureModelVariable fm3expected = getFMVariable("fm3");

		assertFMsEqual(fm3expected.getFm(), fm3.getFm());

		System.err.println("fm3=" + fm3.getFm().toString());

	}

	@Test
	public void testIntersectionAND4() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A : B ;)\n" + "fm2 = FM (A : B ; B : [C]; )\n"
				+ "fm3 = merge intersection { fm1 fm2 }\n"
				+ "fm3expected = FM (A : B; )");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		FeatureModelVariable fm3expected = getFMVariable("fm3");

		assertFMsEqual(fm3expected.getFm(), fm3.getFm());

		System.err.println("fm3=" + fm3.getFm().toString());

	}

	@Test
	public void testNonValid1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("fm1 = FM (A: B [C] ; )\n"
				+ "fm2 = FM (U: X Y [Z] [W]; )\n"
				+ "fm3 = merge intersection fm*\n" + "assert (not isValid fm3)");

		FeatureModelVariable fm3 = getFMVariable("fm3");

	}

	@Test
	public void testJOTCE() throws Exception {

		FeatureModelVariable fmv1 = FM("fm1",
				"FM (MedicalImage : Format ; Format : (DICOM|Nifti); )");
		FeatureModelVariable fmv2 = FM("fm2",
				"FM (MedicalImage : Format ; Format : (DICOM|Analyze); )");

		_shell.parse("fmr = " + "merge sunion { fm1 fm2 }\n");
		FeatureModelVariable fmvr = getFMVariable("fmr");

		FeatureModelVariable fmvrExpected = FM("fmrExpected",
				"FM (MedicalImage : Format ; Format : (Analyze|DICOM|Nifti); )");
		System.err.println("fmvr=" + fmvr);
		assertEquals(Comparison.REFACTORING, fmvrExpected.compare(fmvr));

		BDD phiWrong = fmv1.getFormula().getBDD()
				.or(fmv2.getFormula().getBDD());
		Formula<String> flaWrong = new Formula<String>(phiWrong, Sets.union(
				fmv1.features().names(), fmv2.features().names()), _builder);
		Set<Set<String>> sFlaWrong = new AllConfigsBDD(_builder)
				.process(flaWrong);
		System.err.println("[[flaWrong]]=" + sFlaWrong);

		Set<Set<String>> sFla = new AllConfigsBDD(_builder).process(fmvr
				.getFormula());
		System.err.println("[[fla]]=" + sFla);

		System.err.println("flaWrong -- fla = "
				+ Sets.difference(sFlaWrong, sFla));
		System.err.println("fla -- flaWrong = "
				+ Sets.difference(sFla, sFlaWrong));

	}

	@Test
	public void testJOTInteractions() throws Exception {

		FeatureModelVariable fmv1 = FM("fm1", "FM (A : B [C]; )");
		FeatureModelVariable fmv2 = FM("fm2", "FM (A : B D [E] ; )");

		_shell.parse("fmr = " + "merge sunion { fm1 fm2 }\n");
		FeatureModelVariable fmvr = getFMVariable("fmr");
		System.err.println("fmvr=" + fmvr);

	}

	@Test
	public void testJOT1() throws Exception {

		String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
		String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
		String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

		String expected = "FM (MedicalImage : [Anonymized] MRI [Header] [DICOM] ; MRI : [T1] [T2] ; "
				+ "(!Header | !DICOM) ; "
				+ "(Header -> Anonymized); "
				+ "(Anonymized | Header | !DICOM | !T1 | !T2) ;"
				+ "(Anonymized | Header | DICOM | !T1 | !T2) ; " + ")"; // strict
																		// union
																		// mode

		_shell.setVerbose(true);
		_shell.parse("fmsupp1 = " + fmsupp1 + "\n" + "fmsupp2 = " + fmsupp2
				+ "\n" + "fmsupp3 = " + fmsupp3 + "\n"
				+ "mspl = merge sunion fmsupp*" + "\n" + "expected = "
				+ expected + "\n" + "cexpected = " + "configuration expected"
				+ "\n");

		FeatureModelVariable mspl = getFMVariable("mspl");
		FeatureModelVariable fm_expected = getFMVariable("expected");
		System.err.println("mspl=" + mspl.getFm());
		System.err.println("msplFeatureIDE="
				+ ConvertAnalyzer.convert(mspl, FMFormat.FIDE));
		assertEquals(fm_expected.counting(), mspl.counting(), 0);
		assertRefactoring("expected", "mspl");
		// won't pass since we operate on synthesized information (FD) and we have no proper solution to synthesize non requires/excludes constraints
		// assertFMsEqual(fm_expected.getFm(), mspl.getFm()); 
		
	}

	@Test
	public void testDiff1() throws Exception {
		_shell.setVerbose(true);
		testDiff("FM (A : (B|C|D); )", "FM (A : (B|E); )", "FM (A : (C|D); )");
	}

	@Test
	public void testDiff2() throws Exception {
		testDiff("FM (A : (B|C|D); )", "FM (A : (B|C); )", "FM (A : D; )");
	}

	@Test
	public void testDiff3() throws Exception {
		testDiff("FM (A : (B|C|D); )", "FM (A : (B|C|I)+; )", "FM (A : D; )");
	}

	@Test
	public void testDiff4() throws Exception {
		testDiff("FM (A : (B|C|D)+; )", "FM (A : (B|C); )",
				"FM (A : (B|C|D)+ ; ! (B -> A & C -> A & (!B | !C) & !D) ; )");
	}

	@Test
	public void testDiff5() throws Exception {
		testDiff("FM (A : (B|C|D)+; )", "FM (A : [B] [C] [I] ; )",
				"FM (A : (B|C|D)+ ; ! (B -> A & C -> A & I -> A & !D) ; !I ; )");
	}

	@Test
	public void testDiff6() throws Exception {
		testDiff("FM (A : (B|C|D|F)+; )", "FM (A : (B|I); )",
				"FM (A : (B|C|D|F)+ ; !I ; ! (B -> A & I -> A & (!B | !I) & !C & !D & !F) ; )");
	}
	
	
	@Test
	public void testPaaS() throws Exception {
		
		FeatureModelVariable fmvJelastic = FM ("fmJelastic", "FM (Service: Language ApplicationServer [Autoscaling] [HTTPS] [Database] [FSwriting] IaaS;" +			
			"Language: Java;" + 
			"ApplicationServer: (Tomcat | Jetty | GlassFish);" + 
			"					Tomcat: (Tomcat6 | Tomcat7);" +
			"Database: (SQL | NoSQL)+;" + 
						"SQL: (MySQL | PostgreSQL | MariaDB)+;" +
						"NoSQL: MongoDB;" + 	
		"IaaS: AmazonEC2;" +
		"AmazonEC2: (Europe | America);" + ")" ); 

			/*fmDotcloud = FM (Service: Language ApplicationServer [Autoscaling] [HTTPS] [Database] [FSwriting] IaaS;

Language: (Java | Scala | Grails | JRuby | ColdFusion | PHP | Python | Perl | NodeJs | Opa);

ApplicationServer: (Tomcat | Jetty | Nginx);

Database: (SQL | NoSQL)+;
			SQL: (MySQL | PostgreSQL)+;
			NoSQL: (MongoDB | CouchDB | Redis)+;

IaaS: AmazonEC2;
)

fmCloudbees = FM (Service: Language ApplicationServer [Autoscaling] [HTTPS] [Database] [FSwriting] IaaS;

Language: (Java | Scala | Grails | JRuby | ColdFusion);

ApplicationServer: Tomcat;
					
Database: (SQL | NoSQL)+;
			SQL: MySQL;
			NoSQL: (MongoDB | CouchDB)+;

IaaS: AmazonEC2;
)				

"fmCloudFoundry = FM (Service: Language ApplicationServer [Autoscaling] [HTTPS] [Database] [FSwriting] IaaS;

Language: (Java | Scala | Grails | Ruby | Spring | NodeJs | Sinatra | Python);

ApplicationServer: Nginx;
					
Database: (SQL | NoSQL)+;
			SQL: MySQL;
			NoSQL: (MongoDB | Redis)+;
)				
*/

			
	
		
		
		
	}

	private void testDiff(String strFM1, String strFM2, String strExpected)
			throws Exception {

		FMLTest.FM_CHECK_SPLOT_AND_DEPTH = false;
		String fm1ID = "fm1";
		FeatureModelVariable fmv1 = FM(fm1ID, strFM1);
		String fm2ID = "fm2";
		FeatureModelVariable fmv2 = FM(fm2ID, strFM2);

		String fmDiffID = "diff12";
		_shell.setVerbose(true);
		_shell.parse(fmDiffID + " = " + "merge diff {" + fm1ID + " " + fm2ID
				+ " }\n");
		_shell.setVerbose(false);

		FeatureModelVariable fmvDiff = getFMVariable(fmDiffID);
		FeatureModelVariable fmvExpected = FM("fmExpected", strExpected);

		Set<Set<String>> sDiff = FMLUtils.setConfigToSetStr(fmvDiff.configs());
		System.err.println("[[diff]]=" + sDiff);
		Set<Set<String>> sExpected = FMLUtils.setConfigToSetStr(fmvExpected.configs());
		System.err.println("[[expected]]=" + sExpected);
		
		System.err.println("[[diff -- expected]]="
				+ Sets.difference(sDiff, sExpected));
		System.err.println("[[expected -- diff]]="
				+ Sets.difference(sExpected, sDiff));

		

		_shell.setVerbose(true);
		Formula<String> diff12 = new FormulaAnalyzer<String>(FMLMergerBDD.diff(fmv1.getFormula(), fmv2.getFormula(), _builder), _builder).removeDeadFeatures();
		System.err.println("diff12=" + diff12);
		Formula<String> flaDiff = fmvDiff.getFormula() ;
		System.err.println("flaDiff=" + flaDiff);
		
		assertEquals(new FormulaAnalyzer<String>(diff12, _builder).removeDeadFeatures(), flaDiff);
		
		assertEquals(new FormulaAnalyzer<String>(fmvExpected.getFormula(), _builder).removeDeadFeatures(), fmvDiff.getFormula());
				

		FMLTest.FM_CHECK_SPLOT_AND_DEPTH = true;
	}

	/**
	 * @param lfms
	 *            list of input FMs to process (string-based representation)
	 * @param fmExpected
	 *            the FM supposed to represent the expected set of
	 *            configurations and the ideal hierarchy
	 * @param mode
	 *            sunion/intersection/diff/union
	 */
	protected void assertMerge(List<String> lfms, String fmExpected, String mode)
			throws Exception {

		assert (mode.equals("sunion") || mode.equals("intersection") || mode
				.equals("diff"));

		Mode mergeMode = mode.equals("sunion") ? Mode.StrictUnion : mode
				.equals("intersection") ? Mode.Intersection : mode
				.equals("diff") ? Mode.Diff : null;

		int n = 0;
		for (String strFM : lfms)
			_shell.parse("fm" + n++ + " = " + strFM + "\n");

		assert (n == lfms.size());

		_shell.parse("expected = " + fmExpected + "\n");

		StringBuilder sbFMs = new StringBuilder();
		for (int i = 0; i < lfms.size(); i++)
			sbFMs.append("fm" + i + " ");

		_shell.parse("resulting = " + "merge " + mode + " { "
				+ sbFMs.toString() + "}\n");
	
		// checkings

		List<FeatureModelVariable> lfmvs = new ArrayList<FeatureModelVariable>();
		// (1) control that the set of FMs is present in the environment
		for (int i = 0; i < lfms.size(); i++)
			lfmvs.add(getFMVariable("fm" + i));

		FeatureModelVariable expected = getFMVariable("expected");

		FeatureModelVariable resulting = getFMVariable("resulting");
		System.err.println("resulting=" + resulting.getFm().toString());
		
		// _shell.setVerbose(true);
		FeatureModelVariable fmvMerged = new FMLMergerBDD(lfmvs, FDOverApproximationStrategy.SYNCHRONIZED_FLA).mergeFMs(mergeMode);
		System.err
				.println("resulting (merged)=" + fmvMerged.getFm().toString());
		// _shell.setVerbose(false);
		
		

		assertEquals(expected.counting(), resulting.counting(), 0);
		assertRefactoring("expected", "resulting");
		assertRefactoring("resulting", "expected");
		assertFMsEqual(expected.getFm(), resulting.getFm());

	}

}
