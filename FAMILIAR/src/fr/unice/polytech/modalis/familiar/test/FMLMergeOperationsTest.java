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
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.fm.FMLUtils;
import fr.unice.polytech.modalis.familiar.operations.AllConfigsBDD;
import fr.unice.polytech.modalis.familiar.operations.FMLMergerBDD;
import fr.unice.polytech.modalis.familiar.operations.FormulaAnalyzer;
import fr.unice.polytech.modalis.familiar.operations.Mode;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.Formula;

/**
 * @author mathieuacher
 * 
 */
public class FMLMergeOperationsTest extends FMLTest {

	@Test
	public void testECMFA1() throws Exception {

		String base = "FM (A : (B|C); )";
		String aspect = "FM (A : B [C]; )";

		String expected = "FM (A : B; )";

		String referenceBased = "FM (R : A Ap; A : (B|C); Ap : Bp [Cp]; B <-> Bp ; C <-> Cp; )";

		_shell.parse("base = " + base + "\n" + "aspect = " + aspect + "\n"
				+ "expected = " + expected + "\n" + "referenceBased = "
				+ referenceBased + "\n");

		// merging process
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add(getFMVariable("base"));
		lfms.add(getFMVariable("aspect"));

		Formula<String> fla = new FMLMergerBDD(lfms, _builder).calculateFormula(Mode.Intersection);

		FeatureModelVariable fmExpected = getFMVariable("expected");

		Formula<String> flaExpected = fmExpected.getFormula();
		assertEquals(flaExpected,
				new FormulaAnalyzer<String>(fla, _builder).removeDeadFeatures());

		/* reasoning about referenceBased and the dead features */

		FeatureModelVariable fmReference = getFMVariable("referenceBased");

		/*
		 * A dead feature is a non-instantiable feature, i.e., a feature that
		 * despite being deﬁned in a FM, it appears in no product in the SPL. C
		 * and C' are dead features. A child feature in a non-mandatory
		 * relationship is a full-mandatory feature if it has to be instantiated
		 * whenever its parent feature is, i.e., it is neither an optional nor
		 * an alternative feature. C is a full-mandatory feature since it
		 * belongs to a Xor-group but appears in every conﬁguration. Such
		 * features, acting as errors in the resulting FM, should be avoided.
		 */

		Set<String> deads = fmReference.deads();
		Set<String> expectedDeads = new HashSet<String>();
		expectedDeads.add("C");
		expectedDeads.add("Cp");

		assertEquals(expectedDeads.size(), deads.size());
		assertTrue(expectedDeads.containsAll(deads));

		Set<String> cores = fmReference.cores();
		Set<String> expectedCores = new HashSet<String>();
		expectedCores.add("R");
		expectedCores.add("A");
		expectedCores.add("Ap");
		expectedCores.add("B");
		expectedCores.add("Bp");
		assertEquals(expectedCores.size(), cores.size());
		assertTrue(expectedCores.containsAll(cores));

		Set<String> fulls = fmReference.fullMandatoryFeatures();
		Set<String> expectedFulls = new HashSet<String>();
		expectedFulls.add("B");

		assertEquals(expectedFulls.size(), fulls.size());
		assertTrue(expectedFulls.containsAll(fulls));

		double beforeCleanUp = fmReference.counting(); // TODO: use
														// fmReference.copy()
														// instead
		fmReference.cleanup();

		assertEquals(beforeCleanUp, fmReference.counting(), 0);

	}

	@Test
	public void testECMFA2() throws Exception {

		String base = "FM (A : [B] C F ; B : (D|E) ; )";
		String aspect = "FM (A : [B] C [F] ; B : [D] G ; D : (H|I); )";

		String expected = "FM (A : C F; )";

		_shell.parse("base = " + base + "\n" + "aspect = " + aspect + "\n"
				+ "expected = " + expected + "\n");

		// merging process
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add(getFMVariable("base"));
		lfms.add(getFMVariable("aspect"));
		Formula<String> fla = new FMLMergerBDD(lfms, _builder).calculateFormula(Mode.Intersection);

		FeatureModelVariable fmExpected = getFMVariable("expected");

		Formula<String> flaExpected = fmExpected.getFormula();
		assertEquals(flaExpected,
				new FormulaAnalyzer<String>(fla, _builder).removeDeadFeatures());

	}

	@Test
	public void testECMFA3() throws Exception {

		String base = "FM (A : [B] C [F] ; B : (D|E) ; D <-> F; E <-> F; )";
		String aspect = "FM (A : [B] C [F] [I] ; B : (G|H) ; )";

		String expected = "FM (A : C ; )";

		_shell.parse("base = " + base + "\n" + "aspect = " + aspect + "\n"
				+ "expected = " + expected + "\n");

		// merging process
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add(getFMVariable("base"));
		lfms.add(getFMVariable("aspect"));
		Formula<String> fla = new FMLMergerBDD(lfms, _builder).calculateFormula(Mode.Intersection);

		FeatureModelVariable fmExpected = getFMVariable("expected");

		Formula<String> flaExpected = fmExpected.getFormula();
		assertEquals(flaExpected,
				new FormulaAnalyzer<String>(fla, _builder).removeDeadFeatures());

	}

	@Test
	public void testECMFA4() throws Exception {

		String fmep1 = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT) ; Format : Anonymized [Header]; Header : (DICOM|Nifti); )";
		String fmep2 = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT|PET) ; Format : [Anonymized] [Header]; Header : DICOM ; )";
		String fmep3 = "FM (MedicalImage : Modality Format ; Modality : (SPEC|PET) ; Format : [Anonymized] [Header]; Header : (DICOM|Nifti)+ ; ) ";

		String fmep2bis = "FM (MedicalImage : Modality Format ; Modality : (MRI|CT) ; Format : Anonymized [Header]; Header : DICOM ; ) ";
		String fmep3bis = "FM (MedicalImage : Modality Format ; Modality : PET ; Format : [Anonymized] [Header]; Header : DICOM ; ) ";

		_shell.parse("fmep1 = " + fmep1 + "\n" + "fmep2 = " + fmep2 + "\n"
				+ "fmep3 = " + fmep3 + "\n" + "fmep2bis = " + fmep2bis + "\n"
				+ "fmep3bis = " + fmep3bis + "\n");

		// merging process btw fmep1 and fmep2
		List<FeatureModelVariable> lfm12 = new ArrayList<FeatureModelVariable>();
		lfm12.add(getFMVariable("fmep1"));
		lfm12.add(getFMVariable("fmep2"));
		Formula<String> fla12 = new FMLMergerBDD(lfm12, _builder).calculateFormula(Mode.Intersection);

		FeatureModelVariable fm2bis = getFMVariable("fmep2bis");
		Formula<String> fla2Expected = fm2bis.getFormula();

		assertEquals(fla2Expected,
				new FormulaAnalyzer<String>(fla12, _builder)
						.removeDeadFeatures());

		// merging process btw fmep2 and fmep3
		List<FeatureModelVariable> lfm23 = new ArrayList<FeatureModelVariable>();
		lfm23.add(getFMVariable("fmep2"));
		lfm23.add(getFMVariable("fmep3"));
		Formula<String> fla23 = new FMLMergerBDD(lfm23, _builder).calculateFormula(Mode.Intersection); // merge intersection { fmep1
												// fmep2 }

		FeatureModelVariable fm3bis = getFMVariable("fmep3bis");
		Formula<String> fla3Expected = fm3bis.getFormula();
		assertEquals(fla3Expected,
				new FormulaAnalyzer<String>(fla23, _builder)
						.removeDeadFeatures());

		// merging process btw fmep1, fmep2 and fmep3
		List<FeatureModelVariable> lfm123 = new ArrayList<FeatureModelVariable>();
		lfm123.add(getFMVariable("fmep1"));
		lfm123.add(getFMVariable("fmep2"));
		lfm123.add(getFMVariable("fmep3"));
		Formula<String> fla123 = new FMLMergerBDD(lfm123, _builder).calculateFormula(Mode.Intersection); // merge intersection { fmep1
												// fmep2 fmep3 }
		Formula<String> simplifiedFla = new FormulaAnalyzer<String>(fla123,
				_builder).removeDeadFeatures();
		assertTrue(simplifiedFla.isZero());

	}

	@Test
	public void testSC1() throws Exception {

		String fmop1 = "FM (MedicalImage : ModalityAcquisition Format [Anonymized] ; ModalityAcquisition : (T1|T2)+ ; Format : (DICOM|INRIMAGE); )";
		String fmip2 = "FM (MedicalImage : ModalityAcquisition Format ; ModalityAcquisition : (T1|T2) ; Format :  (DICOM|Nifi|Analyze) ; )";

		String fmo1 = "FM (MedicalImage : ModalityAcquisition Format [Anonymized] ; ModalityAcquisition : (T1|T2)+ ; Format : DICOM ; )";
		String fmi2 = "FM (MedicalImage : ModalityAcquisition Format ; ModalityAcquisition : (T1|T2) ; Format :  (DICOM|Nifi) ; )";

		String strfmr = "FM (MedicalImage : ModalityAcquisition Format ; ModalityAcquisition : (T1|T2) ; Format :  DICOM ; )";

		_shell.parse("fmop1 = " + fmop1 + "\n" + "fmip2 = " + fmip2 + "\n"
				+ "co1 = " + "configuration fmop1" + "\n"
				+ "select DICOM in co1" + "\n" + "ci2 = "
				+ "configuration fmip2" + "\n" + "deselect Analyze in ci2"
				+ "\n" + "fmo1 = " + fmo1 + "\n" + "fmi2 = " + fmi2 + "\n"
				+ "fmr = " + strfmr + "\n");

		// merging process btw fmo1 and fmi2
		List<FeatureModelVariable> lfmo1i2 = new ArrayList<FeatureModelVariable>();
		lfmo1i2.add(getFMVariable("fmo1"));
		lfmo1i2.add(getFMVariable("fmi2"));
		Formula<String> flao1i2 = new FMLMergerBDD(lfmo1i2, _builder).calculateFormula(Mode.Intersection); // merge intersection { fmo1 fmi2
												// }

		FeatureModelVariable fmr = getFMVariable("fmr");
		Formula<String> flar = fmr.getFormula();

		assertEquals(flar,
				new FormulaAnalyzer<String>(flao1i2, _builder)
						.removeDeadFeatures());

		// as FMs
		ConfigurationVariable co1 = getConfigurationVariable("co1");
		FeatureModelVariable co1tofm = new FeatureModelVariable("", co1.asFM());
		System.err.println("co1tofm=" + co1tofm);
		assertFMsEqual(getFMVariable("fmo1").getFm(), co1tofm.getFm());

		ConfigurationVariable ci2 = getConfigurationVariable("ci2");
		FeatureModelVariable ci2tofm = new FeatureModelVariable("", ci2.asFM());
		assertFMsEqual(getFMVariable("fmi2").getFm(), ci2tofm.getFm());

		// check now the merging process btw configurations converted to FMs
		List<FeatureModelVariable> lfmco1ci2 = new ArrayList<FeatureModelVariable>();
		lfmco1ci2.add(co1tofm);
		lfmco1ci2.add(ci2tofm);

		Formula<String> flaco1ci2 = new FMLMergerBDD(lfmco1ci2, _builder).calculateFormula(Mode.Intersection); // merge intersection { co1tofm
												// ci2tofm }

		assertEquals(flar,
				new FormulaAnalyzer<String>(flaco1ci2, _builder)
						.removeDeadFeatures());

	}

	@Test
	public void testSC2() throws Exception {

		String fmep2 = "FM (A : B F ; B : (C|D); F : G [H]; H : I ; )";
		String fmep3 = "FM (A : B F ; B : E ; F : [G] [H] ; H : I ; ) ";

		_shell.parse("fmep2 = " + fmep2 + "\n" + "fmep3 = " + fmep3 + "\n");

		// merging process btw fmep2 and fmep3
		List<FeatureModelVariable> lfm23 = new ArrayList<FeatureModelVariable>();
		lfm23.add(getFMVariable("fmep2"));
		lfm23.add(getFMVariable("fmep3"));
		Formula<String> fla23 = new FMLMergerBDD(lfm23, _builder).calculateFormula(Mode.Intersection); // merge intersection { fmep2
												// fmep3 }

		assertTrue(fla23.isZero());

	}

	@Test
	public void testSLE1() throws Exception {

		String base = "FM (transport : (car|other|urbanTransport); urbanTransport : (bike|twoWheeledVehicle); )";
		String aspect = "FM (transport : (car|other|publicService)+ ; )";

		String expected = "FM (transport : (car|other); )";

		_shell.parse("base = " + base + "\n" + "aspect = " + aspect + "\n"
				+ "expected = " + expected + "\n");

		// merging process
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add(getFMVariable("base"));
		lfms.add(getFMVariable("aspect"));
		Formula<String> fla = new FMLMergerBDD(lfms, _builder).calculateFormula(Mode.Intersection);

		FeatureModelVariable fmExpected = getFMVariable("expected");

		Formula<String> flaExpected = fmExpected.getFormula();
		assertEquals(flaExpected,
				new FormulaAnalyzer<String>(fla, _builder).removeDeadFeatures());

	}

	@Test
	public void testJOTCrossProduct1() throws Exception {

		String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
		String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
		String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

		String crossproduct = "FM (MedicalImage : [Anonymized] MRI [Header] [DICOM] ; MRI : [T1] [T2] ; "
				+ "Header -> Anonymized ; " + ")"; // strict union mode

		_shell.parse("fmsupp1 = " + fmsupp1 + "\n" + "fmsupp2 = " + fmsupp2
				+ "\n" + "fmsupp3 = " + fmsupp3 + "\n" + "fmcp = "
				+ crossproduct + "\n" + "cexpected = " + "configuration fmcp"
				+ "\n");

		FeatureModelVariable fmcp = getFMVariable("fmcp");
		Set<Variable> configsSupp1 = getFMVariable("fmsupp1").configs();
		Set<Variable> configsSupp2 = getFMVariable("fmsupp2").configs();
		Set<Variable> configsSupp3 = getFMVariable("fmsupp3").configs();

		Set<Set<String>> configsSupp123UNION = Sets.union(Sets.union(
				FMLUtils.setConfigToSetStr(configsSupp1),
				FMLUtils.setConfigToSetStr(configsSupp2)),
				FMLUtils.setConfigToSetStr(configsSupp3));
		Set<Set<String>> configsSupp123CP = setCP(
				setCP(FMLUtils.setConfigToSetStr(configsSupp1),
						FMLUtils.setConfigToSetStr(configsSupp2)),
				FMLUtils.setConfigToSetStr(configsSupp3));

		Set<Set<String>> configsSupp12CP = setCP(
				FMLUtils.setConfigToSetStr(configsSupp1),
				FMLUtils.setConfigToSetStr(configsSupp2));
		Set<Set<String>> configsSupp23CP = setCP(
				FMLUtils.setConfigToSetStr(configsSupp2),
				FMLUtils.setConfigToSetStr(configsSupp3));
		Set<Set<String>> configsSupp13CP = setCP(
				FMLUtils.setConfigToSetStr(configsSupp1),
				FMLUtils.setConfigToSetStr(configsSupp3));

		Set<Set<String>> pairByPair123 = Sets.union(
				Sets.union(configsSupp12CP, configsSupp23CP), configsSupp13CP);

		System.err.println("configsSupp123=" + configsSupp123CP);
		System.err.println("#configsSupp123=" + configsSupp123CP.size());

		Set<Set<String>> MSPLSupp123 = Sets.union(pairByPair123,
				Sets.union(configsSupp123UNION, configsSupp123CP));
		System.err.println("MSPLSupp123=" + MSPLSupp123);
		System.err.println("#MSPLSupp123=" + MSPLSupp123.size());

		Set<Variable> configsBDD = fmcp.configsBDD();
		// assertEquals (MSPLSupp123.size(), configsBDD.size());
		assertSetEquals(MSPLSupp123, FMLUtils.setConfigToSetStr(configsBDD));

		/*
		 * AllConfigsBDD allBDD = new
		 * AllConfigsBDD(FMLCommandInterpreter.getBuilder()) ; Set<Set<String>>
		 * configsFla = allBDD.process(fmCP.getFormula());
		 * 
		 * assertSetEquals (configsSupp123, configsFla);
		 */
	}

	/**
	 * @param setConfigToSetStr1
	 * @param setConfigToSetStr2
	 * @return cross products of the two sets
	 */
	private Set<Set<String>> setCP(Set<Set<String>> setConfigToSetStr1,
			Set<Set<String>> setConfigToSetStr2) {
		Set<Set<String>> r = new HashSet<Set<String>>();
		for (Set<String> set1 : setConfigToSetStr1) {
			Set<Set<String>> ri = new HashSet<Set<String>>();
			for (Set<String> set2 : setConfigToSetStr2) {
				ri.add(Sets.union(set1, set2));
			}
			r.addAll(ri);
		}

		return r;
	}

	@Ignore
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

		_shell.parse("fmsupp1 = " + fmsupp1 + "\n" + "fmsupp2 = " + fmsupp2
				+ "\n" + "fmsupp3 = " + fmsupp3 + "\n" + "expected = "
				+ expected + "\n" + "cexpected = " + "configuration expected"
				+ "\n");

		// merging process
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		lfms.add(getFMVariable("fmsupp1"));
		lfms.add(getFMVariable("fmsupp2"));
		lfms.add(getFMVariable("fmsupp3"));
		Formula<String> fla = new FMLMergerBDD(lfms, _builder).calculateFormula(Mode.StrictUnion);

		// note: there is no need to remove dead features in union mode

		FeatureModelVariable fmExpected = getFMVariable("expected");
		Formula<String> flaExpected = fmExpected.getFormula();

		assertFormulaEquals(flaExpected, fla); // does not work!

		assertConfigsEquals(flaExpected, fla);

		assertEquals(fmExpected.counting(), countingFormula(fla), 0);
		assertEquals(fmExpected.counting(), countingFormula(flaExpected), 0);

		double n1 = getFMVariable("fmsupp1").counting();
		assertEquals(8, n1, 0);
		double n2 = getFMVariable("fmsupp2").counting();
		assertEquals(8, n2, 0);
		double n3 = getFMVariable("fmsupp3").counting();
		assertEquals(12, n3, 0);

		assertEquals(28, n1 + n2 + n3, 0);
		assertTrue((n1 + n2 + n3) >= countingFormula(fla));

		// merge intersection { fmsupp1 fmsupp2 }
		List<FeatureModelVariable> lfm12 = new ArrayList<FeatureModelVariable>();
		lfm12.add(getFMVariable("fmsupp1"));
		lfm12.add(getFMVariable("fmsupp2"));
		Formula<String> fla12 = new FMLMergerBDD(lfm12, _builder).calculateFormula(Mode.Intersection);
		double common12 = countingFormula(fla12);

		assertEquals(2, common12, 0);

		// merge intersection { fmsupp1 fmsupp3 }
		List<FeatureModelVariable> lfm13 = new ArrayList<FeatureModelVariable>();
		lfm13.add(getFMVariable("fmsupp1"));
		lfm13.add(getFMVariable("fmsupp3"));
		Formula<String> fla13 = new FMLMergerBDD(lfm13, _builder).calculateFormula(Mode.Intersection);
		double common13 = countingFormula(fla13);

		assertEquals(6, common13, 0);

		// merge intersection { fmsupp2 fmsupp3 }
		List<FeatureModelVariable> lfm23 = new ArrayList<FeatureModelVariable>();
		lfm23.add(getFMVariable("fmsupp2"));
		lfm23.add(getFMVariable("fmsupp3"));
		Formula<String> fla23 = new FMLMergerBDD(lfm23, _builder).calculateFormula(Mode.Intersection);
		double common23 = countingFormula(fla23);

		assertEquals(4, common23, 0);

		// merge intersection { fmsupp1 fmsupp2 fmsupp3 }
		List<FeatureModelVariable> lfm123 = new ArrayList<FeatureModelVariable>();
		lfm123.add(getFMVariable("fmsupp1"));
		lfm123.add(getFMVariable("fmsupp2"));
		lfm123.add(getFMVariable("fmsupp3"));
		Formula<String> fla123 = new FMLMergerBDD(lfm123, _builder).calculateFormula(Mode.Intersection);
		double common123 = countingFormula(fla123);

		assertEquals(2, common123, 0);

		// check the following property:
		// |fmsupp1 sunion fmsupp2 sunion fmsupp3| =
		// |fmsupp1| + |fmsupp2| + |fmsupp3| -
		// |fmsupp1 intersection fmsupp2| - |fmsupp1 intersection fmsupp3| -
		// |fmsupp2 intersection fmsupp3|
		// + |fmsupp1 intersection fmsupp2 intersection fmsupp3|

		// => |fmsupp1 sunion fmsupp2 sunion fmsupp3| = nexpected
		// n1 + n2 + n3 - (common12) - (common13) - (common23) + common123

		double nexpected = (n1 + n2 + n3) - (common12) - (common13)
				- (common23) + common123;

		assertEquals(18, nexpected, 0);
		assertEquals(nexpected, fmExpected.counting(), 0);

		/***** BUGS with FeatureIDE *****/

		// Set<Variable> configsExpected = fmExpected.configsSAT();
		// System.err.println(new
		// de.ovgu.featureide.fm.core.io.guidsl.FeatureModelWriter(
		// fmExpected.toFeatureIDE()).writeToString()) ;
		// Set<String> configs = configsToSetString(configsExpected);
		// assertEquals (fmExpected.counting(), fmExpected.countingSAT(), 0);
		// assertEquals (fmExpected.counting(), configs.size(), 0);

		// ConfigurationVariable cexpected =
		// getConfigurationVariable("cexpected");

		// assertTrue (cexpected.applySelection("T1", OpSelection.DESELECT));
		// assertTrue (cexpected.applySelection("T2", OpSelection.DESELECT));

		// assertEquals (nexpected, countingFormula(flaExpected), 0) ;
		// assertEquals (nexpected, countingFormula(fla), 0) ;

		// TODO merging process -- associativity
		// List<FeatureModel<String>> lfm12Then3 = new
		// ArrayList<FeatureModel<String>>();

		// TODO setUnion { (configs fm1) (configs fm2) configs fm3 } eq (configs
		// fmExpected)

		Set<Variable> configsBDD = fmExpected.configsBDD();
		assertEquals(nexpected, configsBDD.size(), 0);
		assertEquals(configsBDD.size(), fmExpected.counting(), 0);
		assertEquals(configsBDD.size(), countingFormula(fla), 0);
		assertEquals(countingFormula(flaExpected), countingFormula(fla), 0);

		/*
		 * System.err.println("\n\n\rconfigsBDD=" + configsBDD);
		 * debugConfigs(configsBDD) ;
		 */

		Set<Variable> configsSupp1 = getFMVariable("fmsupp1").configs();
		Set<Variable> configsSupp2 = getFMVariable("fmsupp2").configs();
		Set<Variable> configsSupp3 = getFMVariable("fmsupp3").configs();

		Set<Set<String>> configsSupp123 = Sets.union(Sets.union(
				FMLUtils.setConfigToSetStr(configsSupp1),
				FMLUtils.setConfigToSetStr(configsSupp2)),
				FMLUtils.setConfigToSetStr(configsSupp3));

		assertEquals(nexpected, configsSupp123.size(), 0);

		assertSetEquals(configsSupp123, FMLUtils.setConfigToSetStr(configsBDD));
		assertSetEquals(configsSupp123, FMLUtils.setConfigToSetStr(configsBDD));

		AllConfigsBDD allBDD = new AllConfigsBDD(
				FMLCommandInterpreter.getBuilder());
		Set<Set<String>> configsFla = allBDD.process(fla);

		assertSetEquals(configsSupp123, configsFla);

	}

	// @Test
	public void testCSVMerging1() throws Exception {

		// _shell.setVerbose(true);

		_shell.parse("run \"CSVmerging.fml\"" + "\n");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		statisticsFM(fm1);
		// StringVariable s1 = getStringVariable("s1");
		// System.out.println("fm1= { " + s1.getVal() + " }");

		FeatureModelVariable fm2 = getFMVariable("fm2");
		statisticsFM(fm2);
		// StringVariable s2 = getStringVariable("s2");
		// System.out.println("fm2= { " + s2.getVal() + " }");

		FeatureModelVariable fm3 = getFMVariable("fm3");
		statisticsFM(fm3);

		FeatureModelVariable fm4 = getFMVariable("fm4");
		statisticsFM(fm4);

		Set<String> nonComm = Sets.union(Sets.union(
				setVariabletoString(fm1.features()),
				setVariabletoString(fm2.features())), Sets.union(
				setVariabletoString(fm3.features()),
				setVariabletoString(fm4.features())));
		int nNonComm = nonComm.size();

		Set<String> comm = Sets.intersection(Sets.intersection(
				setVariabletoString(fm1.features()),
				setVariabletoString(fm2.features())), Sets.intersection(
				setVariabletoString(fm3.features()),
				setVariabletoString(fm4.features())));

		int nComm = comm.size();

		int total = setVariabletoString(fm1.features()).size()
				+ setVariabletoString(fm2.features()).size()
				+ setVariabletoString(fm3.features()).size()
				+ setVariabletoString(fm4.features()).size();

		System.err.println("nNonComm=" + nNonComm);
		System.err.println("nComm=" + nComm);
		System.err.println("total=" + total);

		System.err.println("per=" + 100
				* (double) (((double) nComm) / ((double) total)));

		System.err.println("\n\ncomm=" + comm);

		System.err.println("\n\n#nodes="
				+ _builder.getFactory().getNodeTableSize());

		// _shell.parse("finalFM = merge sunion fm*" + "\n");

	}

	private void statisticsFM(FeatureModelVariable fm) {
		System.out.println("size (fm.*) = " + fm.features().size());
		// System.out.println("#fm = " + fm.counting());
		
	}

}
