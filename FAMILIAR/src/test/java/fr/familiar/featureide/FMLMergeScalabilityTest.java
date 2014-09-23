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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Ignore;
import org.junit.Test;

import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.familiar.FMLSlicerUtilityTest;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.fm.featureide.FMGenerator;
import fr.familiar.fm.featureide.FeatureIDEtoFML;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMLMergerBDDSPLOT;
import fr.familiar.operations.Mode;
import fr.familiar.operations.featureide.FeatureIDEReader;
import fr.familiar.parser.FMBuilder;
import fr.familiar.utils.MyLogger;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureModelSerializer;
import gsd.synthesis.Formula;

public class FMLMergeScalabilityTest extends FMLSlicerUtilityTest {

	public final static String PATH =

	// "/Users/mathieuacher/Downloads/nfm20/" ;
	// "/Users/mathieuacher/Downloads/nFM40/" ;
	// "/Users/mathieuacher/Desktop/PhD/DEV/workspace/SPLC2010-evaluation/" ;
	//"/Users/mathieuacher/Desktop/PhD/DEV/workspace/JOT2011/";
	"/Users/macher1/Documents/SANDBOX/MODELS13/" ; 

	static int m = 0;

	@Test
	public void test10() throws Exception {

		MyLogger logger = MyLogger.getLogger("output/nSPL5.dat", true);

		for (int j = 0; j < FMGenerator.nFeatures.length; j++) {
			int nComm = FMGenerator.nFeatures[j];
			for (int i = 0; i < FMGenerator.percents.length; i++) {
				int per = FMGenerator.percents[i];
				logger.trace("" + nComm);
				loadFMs(5, nComm, per, true);
			}
			logger.trace("\n");
		}
	}

	@Test
	public void testTunned() throws Exception {
		// loadFMs(100, 100, 100); // OK
		// loadFMs(200, 150, 100); // OK
		loadFMs(10, 10, 50, true);
		// loadFMs(10, 60, 60, true);
		// loadFMs(10, 100, 80, true); //~83 seconds
		// loadFMs(10, 100, 80, false); ~1300 seconds!
		// loadFMs(10, 100, 80, true); // ~42 seconds with 20 000 000 nodes
		// loadFMs(10, 100, 70, true); // does not scale
		// loadFMs(200, 200, 100, true);
		// loadFMs(10, 20, 60);
		// loadFMs(20, 40, 70, true);
		// loadFMs(20, 40, 65, true);
		// loadFMs(20, 40, 50, true); // scale (~1300/254 seconds)
		// loadFMs(40, 40, 70, true); // scale ~90seconds
		// loadFMs(40, 40, 60, true); // scale
		// loadFMsInstrCalc(40, 40, 85);
		// BDD b1 = loadFMsInstrCalc(40, 40, 85);
		// BDD b2 = loadFMs(40, 40, 60);
		// BDD b2 = loadFMs(40, 50, 70);
		// loadFMs(10, 100, 75, true); // scale ~875 seconds
		/*
		 * for (int i = 100; i > 50; i = i - 5) { System.err.println("i=" + i);
		 * loadFMs(10, 100, i, true); }
		 */

		// loadFMs(200, 200, 100); FAIL
	}

	@Test
	public void testMetrics() throws Exception {

		assertEquals(10, getNbUniqueVariables(40, 10, 100));
		assertEquals(210, getNbUniqueVariables(40, 10, 50));
		assertEquals(10, getNbUniqueVariables(40, 10, 100));
		assertEquals(840, getNbUniqueVariables(40, 40, 50));

		assertEquals(200, getNbUniqueVariables(10, 100, 90));
		assertEquals(250, getNbUniqueVariables(10, 100, 85));
		assertEquals(300, getNbUniqueVariables(10, 100, 80));

		assertEquals(680, getNbUniqueVariables(40, 40, 60));

		assertEquals(360, getNbUniqueVariables(10, 60, 50));
		assertEquals(250, getNbUniqueVariables(10, 100, 85));
		assertEquals(300, getNbUniqueVariables(10, 100, 80));

		assertEquals(500, getNbUniqueVariables(10, 100, 60));
		assertEquals(350, getNbUniqueVariables(10, 100, 75));
		assertEquals(400, getNbUniqueVariables(10, 100, 70));

		// FIXME
		// assertEquals(840, checkLoadFMs(40, 40, 50));

	}

	private int checkLoadFMs(int nFM, int nFeatures, int percent)
			throws Exception {

		Map<Integer, FeatureModelVariable> iTofmvs = getFMsFromFMCalcDirectory(PATH
				+ FMGenerator.targetPathStr("", nFM, nFeatures, percent)
				+ "fmcalc/");
		assertEquals(iTofmvs.size(), nFM);
		Collection<FeatureModelVariable> fmvs = iTofmvs.values();
		Set<String> allFts = new HashSet<String>();
		for (FeatureModelVariable fmv : fmvs) {
			allFts.addAll(fmv.features().names());
		}

		int nFts = allFts.size();

		return nFts;
	}

	@Test
	@Ignore
	public void testMergeSUnion1() throws Exception {

		// for (int i = 0; i < FMGenerator.nFMs.length; i++) {
		// int nFM = FMGenerator.nFMs[i];
		int nFM = 40;
		for (int j = 0; j < FMGenerator.nFeatures.length; j++) {
			int nFeature = FMGenerator.nFeatures[j];
			for (int k = 0; k < FMGenerator.percents.length; k++) {
				int percent = FMGenerator.percents[k];
				loadFMs(nFM, nFeature, percent);

				System.err.println("nFM=" + nFM + "nFeature=" + nFeature
						+ "nPercent=" + percent);

			}
		}
		// }

	}

	@Test
	@Ignore
	public void testMergeNFM20() throws Exception {

		int nFM = 20;
		for (int j = 0; j < FMGenerator.nFeatures.length; j++) {
			int nFeature = FMGenerator.nFeatures[j];
			for (int k = 0; k < FMGenerator.percents.length; k++) {
				int percent = FMGenerator.percents[k];
				loadFMs(nFM, nFeature, percent);

				System.err.println("nFM=" + nFM + "nFeature=" + nFeature
						+ "nPercent=" + percent);

			}
		}

	}

	private BDD loadFMsInstrCalc(int nFM, int nFeatures, int percent)
			throws Exception {

		File f = new File(PATH
				+ FMGenerator.targetPathStr("", nFM, nFeatures, percent)
				+ "fmcalc/" + "instrfmcalc");
		assertTrue(f.exists());

		BufferedReader br = new BufferedReader(new FileReader(f));
		StringBuilder sb = new StringBuilder();
		String strLine = new String();
		while ((strLine = br.readLine()) != null) {
			sb.append(strLine);
		}

		// treat

		String fmSpecifications = sb.toString();

		int j = 0;
		List<String> strFMs = new ArrayList<String>();
		while (true) {
			int start = fmSpecifications.indexOf("{");
			if (start == -1)
				break;
			int end = fmSpecifications.indexOf("}");
			String fmSpecification = fmSpecifications.substring(start + 1, end);
			strFMs.add(fmSpecification);
			fmSpecifications = fmSpecifications.substring(end + 1);
			++j;

		}

		assertEquals(nFM, j);
		assertEquals(nFM, strFMs.size());

		BDD currbdd = _builder.zero(); // false | A = A
		int k = 0;
		for (String strFM : strFMs) {
			FeatureModel<String> fm = FMBuilder.getInternalFM(strFM); // FIXME

			FeatureModelSerializer<String> serializer = new FeatureModelSerializer<String>(
					FeatureGraphFactory.mkStringFactory(), false);
			System.err.println("str" + "=" + strFM);
			System.err.println("fm" + ++k + "=" + serializer.toString(fm));

			Set<Expression<String>> csts = fm.getConstraints();
			System.err.println("#csts=" + csts.size());
			assertNotNull(fm);
			Formula<String> fla = _builder.mkFeatureModel(fm);
			currbdd.orWith(fla.getBDD());
		}

		return currbdd;
	}

	private BDD loadFMs(int nFM, int nFeatures, int percent) throws Exception {
		return loadFMs(nFM, nFeatures, percent, false);
	}

	private BDD loadFMs(int nFM, int nFeatures, int percent, boolean splot)
			throws Exception {

		/*Map<Integer, FeatureModelVariable> iTofmvs = getFMsFromFMCalcDirectory(PATH
				+ FMGenerator.targetPathStr("", nFM, nFeatures, percent)
				+ "fmcalc/");*/
		
		Map<Integer, FeatureModelVariable> iTofmvs = getFMsFromEDirectory(PATH
				+ FMGenerator.targetPathStr("", nFM, nFeatures, percent)
				+ "splot/");
			//	+ "featureide/");

		assertEquals(iTofmvs.size(), nFM);
		Collection<FeatureModelVariable> fmvs = iTofmvs.values();

		/*
		 * 
		 * 
		 * 
		 * 
		 * Set<String> allFts = new HashSet<String>(); for (FeatureModelVariable
		 * fmv : fmvs) { allFts.addAll(fmv.features().names()); }
		 * 
		 * 
		 * int nFtsExpected = getNbUniqueVariables(nFM, nFeatures, percent); int
		 * nFts = allFts.size() ; double perNotShared = getPerNotShared(nFM,
		 * nFeatures, percent) ;
		 * 
		 * System.err.println("nFtsExpected=" + nFtsExpected + "\tnFts=" + nFts
		 * + "\tperNotShared=" + perNotShared);
		 */

		/*
		 * _builder.reset(); _builder = new BDDBuilder<String>(
		 * FMLShellConfiguration.getBDDnodes(),
		 * FMLShellConfiguration.getBDDcache(),
		 * FMLShellConfiguration.getBDDvar(),
		 * FeatureGraphFactory.mkStringFactory());
		 */

		// assertTrue (Math.abs(nFtsExpected - nFts) <= 1);

		/*
		 * for (FeatureModelVariable fmv : fmvs) { if (fmv.toSPLOT() == null)
		 * assertTrue(true); // may happen, OK. (constraint not in the FT tree)
		 * else { double nBDD = fmv.countingBDD(); double nSPLOT =
		 * fmv.countingBDDSPLOT() ; assertEquals(nBDD, nSPLOT, 0); } }
		 */

		// assertEquals(toks1.size(), toks2.size());
		// assertTrue(toks1.equals(toks2));

		// _shell.setVerbose(true);

		// HACK

		//List<FeatureModel<String>> lfms = fmvsToFms(fmvs);
		/*
		 * for (FeatureModel<String> fm : lfms) { FeatureGraph<String> fg =
		 * fm.getDiagram(); FeatureNode<String> topNode = fg.getTopVertex();
		 * Set<FeatureNode<String>> rootNodes = fg.children(topNode); if
		 * (rootNodes.size() == 2) { // remove... for (FeatureNode<String> fn :
		 * rootNodes) { if (fg.findEdge(topNode, fn, 2) != null) {
		 * _shell.printDebugMessage("fn=" + fn); fm.addConstraint(new
		 * Expression<String>(fn.getFeature()).not()); } } } }
		 */

		if (splot) {
			// _shell.setVerbose(true);
			Formula<String> flaUnionSPLOT = new FMLMergerBDDSPLOT(fmvs)
					.calculateFormula(Mode.StrictUnion);
			assertNotNull(flaUnionSPLOT);
			// System.err.println("============ SPLOT done ============");
			return flaUnionSPLOT.getBDD();
		} else {
			Formula<String> flaUnion = new FMLMergerBDD(fmvs, _builder).calculateFormula(
					Mode.StrictUnion);
			assertNotNull(flaUnion);
			System.err.println("============ FML done ============");
			return flaUnion.getBDD();
		}
		// assertTrue(flaUnionSPLOT.equals(flaUnion));

		// Formula<String> flaUnionSPLOT =
		// FMLMerger.calculateSPLOTFormula(fmvsToFms(fmvs),
		// Mode.StrictUnion);
		// assertNotNull(flaUnionSPLOT);
		/*
		 * try { Formula<String> flaUnionSPLOT =
		 * FMLMerger.calculateSPLOTFormula(fmvsToFms(fmvs),
		 * Mode.StrictUnion); assertNotNull(flaUnionSPLOT);
		 * flaUnionSPLOT.free();
		 * 
		 * } catch (NullPointerException e) { assertTrue (true); return ; } //
		 * may happen if free variable added to root with Xor/Or
		 */
		// assertEquals(flaUnion, flaUnionSPLOT);
		// flaUnion.free();
		// flaUnionSPLOT.free();
		// with slice
		/*
		 * FeatureModel<String> fmConstrained =
		 * FMLMerger.mergeFMVsWithConstraints(new
		 * ArrayList<FeatureModelVariable>(fmvs), Mode.StrictUnion);
		 * FeatureModelVariable fmvMerged = new FeatureModelVariable("",
		 * fmConstrained); Set<String> ftsToSlice = new HashSet<String>(); for
		 * (FeatureModelVariable featureModelVariable : fmvs) {
		 * ftsToSlice.addAll(featureModelVariable.getFm().features()); }
		 * Set<String> allFts = fmvMerged.getFm().features() ;
		 * allFts.removeAll(ftsToSlice) ; Formula<String> flaSliced =
		 * runSlicingSPLOT(fmvMerged, allFts, SliceMode.EXCLUDING);
		 * assertNotNull(flaSliced);
		 */
		/*
		 * if (flaUnion.getBDD() != flaSliced.getBDD()) {
		 * System.out.println("#flaUnion" + countingFormula(flaUnion));
		 * System.out.println("#flaSliced" + countingFormula(flaSliced));
		 * System.out.println("[[flaUnion -- flaSliced]]" +
		 * FMLMerger.diff(flaUnion, flaSliced));
		 * System.out.println("[[flaSliced -- flaUnion]]" + new
		 * AllConfigsBDD(_builder).process(FMLMerger.diff(flaSliced,
		 * flaUnion))); } // align the domains
		 * assertEquals(flaUnion.getDomain(), flaSliced.getDomain());
		 * assertEquals(flaUnion.getBDD().toString(),
		 * flaSliced.getBDD().toString()); assertFormulaEquals(flaUnion,
		 * flaSliced);
		 */
		/*
		 * FeatureModel<String> rfm = FMLMerger.mergeFMVsWithConstraints(new
		 * ArrayList<FeatureModelVariable>(fmvs), Mode.StrictUnion);
		 * 
		 * FeatureModelVariable rfmv = new FeatureModelVariable("", rfm) ;
		 * FMReasoningWithSAT satSPLOTreasoner = rfmv.getSPLOTSATReasoner() ;
		 * assertTrue(satSPLOTreasoner.isConsistent());
		 */

	}

	private BDD simpleMergeSUnion(Collection<FeatureModelVariable> fmvs) {

		BDD bStart = _builder.zero();
		for (FeatureModelVariable fmv : fmvs) {
			bStart.orWith(fmv.getFormula().getBDD());
		}
		return bStart;

	}

	private double getPerNotShared(int nFM, int nFeatures, int percent) {
		double perNotShared = ((double) (100 - percent)) / 100;
		return perNotShared;
	}

	private int getNbUniqueVariables(int nFM, int nFeatures, int percent) {
		double perNotShared = getPerNotShared(nFM, nFeatures, percent);
		int nFts = new Double(nFeatures
				+ (nFM * ((double) nFeatures * perNotShared))).intValue();
		return nFts;
	}

	private List<FeatureModel<String>> fmvsToFms(
			Collection<FeatureModelVariable> fmvs) {
		List<FeatureModel<String>> fms = new ArrayList<FeatureModel<String>>();
		for (FeatureModelVariable fmv : fmvs)
			fms.add(fmv.getFm());
		return fms;
	}

	public static Map<Integer, FeatureModelVariable> getFMsFromFMCalcDirectory(
			String directoryTargeted) {

		File f = new File(directoryTargeted);
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile()
						&& pathname.getName().endsWith(".fmcalc");
			}
		};
		File[] files = f.listFiles(filter);
		Map<Integer, FeatureModelVariable> lfms = new HashMap<Integer, FeatureModelVariable>();
		int i = 0;
		for (File file : files) {
			lfms.put(i++, getFMfromFMCalc(file));
		}
		return lfms;

	}

	public static FeatureModelVariable getFMfromFMCalc(File file) {

		assert (file.getAbsolutePath().endsWith(".fmcalc"));

		gsd.synthesis.FeatureModel<String> fm = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = "";
			StringBuilder strFM = new StringBuilder();
			while ((str = br.readLine()) != null) {
				strFM.append(str + "\n");
			}

			String properStrFM = strFM.toString();
			properStrFM = properStrFM.substring(properStrFM.indexOf("{") + 1);
			properStrFM = properStrFM.replace("}", "");
			// System.out.println("strfm(" + m++ + ")=" + properStrFM);

			fm = FMBuilder.getInternalFM(properStrFM); // FIXME
		} catch (Exception e) {
			assertFalse(true);
		}

		return new FeatureModelVariable(null, fm);

	}

	public static FeatureModelVariable getFMfromFMCalc(
			de.ovgu.featureide.fm.core.FeatureModel fmFeatureIDE) {

		FeatureIDEtoFML toFML = new FeatureIDEtoFML(fmFeatureIDE); // buggy!
		String strfm = toFML.writeToString();

		strfm = interoperableStrFMLFM(strfm);
		gsd.synthesis.FeatureModel<String> fm = null;

		System.out.println("strfm(" + m++ + ")=" + strfm);

		try {
			fm = FMBuilder.getInternalFM(strfm); // FIXME
		} catch (Exception e) {
			assertFalse(true);
		}

		return new FeatureModelVariable(null, fm);
	}

	@Deprecated
	public static Map<Integer, FeatureModelVariable> getFMsFromEDirectory(
			String directoryTargeted) {

		File f = new File(directoryTargeted);
		FileFilter filter = new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getName().endsWith(".xml") ; //(".m");
			}
		};
		File[] files = f.listFiles(filter);
		Map<Integer, FeatureModelVariable> lfms = new HashMap<Integer, FeatureModelVariable>();
		int i = 0;
		for (File file : files) {
			System.err.println("file="  + file);
			FeatureModelVariable fmv = 
					new FeatureModelVariable(null, FMBuilder.getInternalFM(new SPLOTtoFML().convert(file))); //getFMfromFeatureIDE(file) ;
			if (fmv != null)
				lfms.put(i++, fmv);
					//getFMfromFeatureIDE(getFeatureIDEFM(file)));
		}

		return lfms;

	}

	private static FeatureModelVariable getFMfromFeatureIDE(File file) {
		
		String strfm = new FeatureIDEReader(file).writeToString() ;
		gsd.synthesis.FeatureModel<String> fm = null;

		System.out.println("strfm(" + m++ + ")=" + strfm);

		try {
			fm = FMBuilder.getInternalFM(strfm);
		} catch (Exception e) {
			return null ; 
			//assertFalse(true);
		}

		return new FeatureModelVariable(null, fm);
	}

	@Deprecated
	public static FeatureModelVariable getFMfromFeatureIDE(
			de.ovgu.featureide.fm.core.FeatureModel fmFeatureIDE) {
		

	
		FeatureIDEtoFML toFML = new FeatureIDEtoFML(fmFeatureIDE); // buggy!
		String strfm = toFML.writeToString();

		strfm = interoperableStrFMLFM(strfm);
		gsd.synthesis.FeatureModel<String> fm = null;

		System.out.println("strfm(" + m++ + ")=" + strfm);

		try {
			fm = FMBuilder.getInternalFM(strfm);
		} catch (Exception e) {
			assertFalse(true);
		}

		return new FeatureModelVariable(null, fm);
	}

	private static String interoperableStrFMLFM(String strfm) {
		strfm = strfm.replace("[", "");
		strfm = strfm.replace("]", "?");

		strfm = strfm.replace("true", "1");
		strfm = strfm.replace("false", "0");

		strfm = strfm.substring(strfm.indexOf("(") + 1);
		strfm = strfm.substring(0, strfm.lastIndexOf(")"));
		return strfm;
	}

	public static de.ovgu.featureide.fm.core.FeatureModel getFeatureIDEFM(
			File file) {

		de.ovgu.featureide.fm.core.FeatureModel fmFeatureIDE = new de.ovgu.featureide.fm.core.FeatureModel();
		assert (file.getAbsolutePath().endsWith(".m"));

		GuidslReader fmr = new GuidslReader(fmFeatureIDE);
		try {
			fmr.readFromFile(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			assertTrue(false);
		} catch (UnsupportedModelException e) {
			e.printStackTrace();
			assertTrue(false);
		}

		return fmFeatureIDE;

	}

}