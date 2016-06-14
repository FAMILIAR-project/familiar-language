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

package fr.familiar.regression.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.prop4j.Node;
import org.xtext.example.mydsl.fml.SliceMode;

import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModelException;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.XMLFeatureModel;

import com.google.common.collect.Sets;

import fr.familiar.FMLTest;
import fr.familiar.experimental.featureide.Node4JUtil;
import fr.familiar.fm.FMLUtils;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.SlicerBDDFormula;
import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.operations.featureide.SlicerSATFormula;
import fr.familiar.parser.FMBuilder;
import fr.familiar.parser.MyExpressionParser;
import fr.familiar.regression.FileUtils;
import fr.familiar.regression.SetUtility;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;


@Ignore
public class FMLSlicerIndempotent extends FMLTest {

	private String _folderLocation = "/Users/mathieuacher/Documents/workspaceScala/SPLOT-SVN-live/WebContent/models";

	private String _folderGenModelLocation = "/Users/mathieuacher/Desktop/SPLOT-gen/ASE2011";

	private static boolean INTEROP = true;

	@Test
	public void testElectronicShopping() throws Exception {
		// testSPLOT_FMIdempotent(new File(_folderLocation + "/" +
		// "REAL-FM-4.xml"));
		testSPLOT_FMIdempotent(new File(
				"/Users/mathieuacher/Desktop/FM-ElectronicShopping-May2011.xml"));
	}

	@Test
	public void testSupposedToBeDead() throws Exception {
		testSPLOT_FMIdempotent(new File(_folderLocation + "/"
				+ "model_20100213_206550190.xml"));
	}

	@Test
	public void testGraph() throws Exception {
		// testSPLOT_FMIdempotent(new File(_folderLocation + "/" +
		// "REAL-FM-5.xml"));
		testSPLOT_FMIdempotent(new File("/Users/mathieuacher/Desktop/"
				+ "REAL-FM-5.xml"));
	}

	@Test
	public void testScalability() throws Exception {
		// testSPLOT_FMIdempotent(new File(_folderLocation + "/" +
		// "REAL-FM-5.xml"));
		INTEROP = false;

		String templateFileName = // "s100/" +
									// "s100\\MyCollection-3CNF-FM-100-20-1,00-SAT-"
									// ;
		// "s200/" + "s200\\MyCollection-3CNF-FM-200-40-1,00-SAT-" ;
		"s200-30/" + "s200-30\\MyCollection-3CNF-FM-200-60-1,00-SAT-";
		// "s200-40/" + "s200-40\\MyCollection-3CNF-FM-200-80-1,00-SAT-" ;
		// "s200-50/" + "s200-50\\MyCollection-3CNF-FM-200-100-1,00-SAT-" ;
		// "s700/" + "s700\\MyCollection-3CNF-FM-700-140-1,00-SAT-" ; // works
		// "s500/" + "s500\\MyCollection-3CNF-FM-500-100-1,00-SAT-" ;
		// "s1000/" + "s1000\\MyCollection-3CNF-FM-1000-200-1,00-SAT-" ;
		// "s1000b/" + "SPLOT-3CNF-FM-1000-100-1.00-SAT-" ;
		_shell.reset();

		for (int i = 3; i <= 3; i++) {
			System.err.println("\n\n\n\t\t########### I=" + i
					+ "##########\n\n\n");
			testSPLOT_FMIdempotent(new File(_folderGenModelLocation + "/"
					+ templateFileName + i + ".xml"));
			_shell.reset();
		}
	}

	/**
	 * rather than loading SPLOT, serialize the feature models directly in FML
	 * then we can directly use FML format and speed up the process
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCompile() throws Exception {

		INTEROP = false;
		String templateFileName = // mkTemplateFileName(5, 100, 0.3);
		// mkTemplateFileName(10, 100, 0.3);
		// mkTemplateFileName(20, 100, 0.3);
		// mkTemplateFileName(50, 100, 0.3);
		// mkTemplateFileName(100, 100, 0.7); // scale

		// mkTemplateFileName(300, 100, 0.35); // scale
		// mkTemplateFileName(400, 100, 0.3); // BDD fails
		// mkTemplateFileName(400, 70, 0.3); // scale
		// mkTemplateFileName(400, 90, 0.3); // scale
		// mkTemplateFileName(2000, 20, 0.4); // only 1, 2 and 3 (files)
		// "s10000/s10000-1000/SPLOT-3CNF-FM-10000-1000-0.10-SAT-";
		// "sLinux/s1000/s1000\\MyCollection-3CNF-FM-1000-200-0,10-SAT-";
		// "sLinux/s2000/sLinux\\MyCollection-3CNF-FM-2000-400-0,30-SAT-";
		"sLinux/s6000/s6000\\MyCollection-3CNF-FM-6000-1200-0,30-SAT-";
		// mkTemplateFileName(3000, 20, 0.2);
		// mkTemplateFileName(5000, 20, 0.3);
		// mkTemplateFileName(500, 80, 0.3); // ??
		// mkTemplateFileName(1000, 10, 0.7);
		// mkTemplateFileName(800, 20, 0.4); // scale 80
		// mkTemplateFileName(800, 40, 0.4); // only 1 (files)
		// mkTemplateFileName(800, 60, 0.3); // only 1 (files)

		// mkTemplateFileName(1000, 20, 0.4);
		// mkTemplateFileName(1000, 80, 0.2); // only 1, 2 and 3 (files)
		// mkTemplateFileName(1000, 70, 0.2);
		// mkTemplateFileName(1000, 60, 0.2);

		// mkTemplateFileName(800, 10, 0.4); // scale

		_shell.reset();

		for (int i = 1; i <= 10; i++) {
			System.err.println("\n\n\n\t\t########### compiling I=" + i
					+ " ##########\n\n\n");

			String filename = _folderGenModelLocation + "/" + templateFileName
					+ i;

			File file = new File(filename + ".xml");
			splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
					file.getAbsolutePath(),
					XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
			try {
				featureModelSPLOT.loadModel();
			} catch (FeatureModelException e) {
				e.printStackTrace();
				return;
			}

			String strFM_SPLOT = new SPLOTtoFML(true)
					.convert(featureModelSPLOT);
			System.err.println("strFM_SPLOT=" + strFM_SPLOT);
			FeatureModelVariable fmvBefore = FM("fmvBefore", strFM_SPLOT);
			FileSerializer.write(filename + ".fml", strFM_SPLOT);
			FeatureModelVariable fmvAfter = FM("fmvAfter",
					FileUtils.read(filename + ".fml"));

			// assertEquals(Comparison.REFACTORING,
			// fmvBefore.compare(fmvAfter));

			_shell.reset();
		}

	}

	/**
	 * cost consuming test, does not scale for large feature models
	 * 
	 * @throws Exception
	 */
	@Test
	public void testCrossCheckingSATEnum() throws Exception {

		INTEROP = false;

		int PER = 25; // 75; // 95 works for all cases
		String[] templateFileName = new String[] {
				mkTemplateFileName(5, 100, 0.3),
				mkTemplateFileName(10, 100, 0.3),
				mkTemplateFileName(20, 100, 0.3), };
		// mkTemplateFileName(50, 100, 0.3);
		// mkTemplateFileName(100, 100, 0.7); // scale

		String fileExtension = ".fml"; // ".xml";

		_shell.reset();

		for (int j = 0; j < templateFileName.length; j++) {
			for (int i = 1; i <= 10; i++) {
				System.err.println("\n\n\n\t\t########### I=" + i
						+ " ##########\n\n\n");
				File f = new File(_folderGenModelLocation + "/"
						+ templateFileName[j] + i + fileExtension);
				checkCrossCheckingSATEnum(f, PER);
				_shell.reset();
			}
		}
	}

	private void checkCrossCheckingSATEnum(File file, int per) throws Exception {

		String strFM = FileUtils.read(file);
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(strFM);
		} catch (Exception e) {
			System.err.println("\tImpossible to parse");
			return;
		}
		if (fm == null) {
			System.err.println("\t\tImpossible to parse");
			return;
		}

		FeatureModelVariable fmv = new FeatureModelVariable("", fm);
		Set<String> ftsToSlice = SetUtility.selectRandomly(fmv.features()
				.names(), per);

		System.err.println("Building the node");
		Node nodeFmv = new SATBuilder().mkNode(fmv);
		System.err.println("# original node = ");
		System.err.println(Node4JUtil.countSizeOfNode(nodeFmv));
		System.err
				.println("\n\n==== SAT formula is ready -- now slicing! =====");

		Set<String> allFtsDomain = fmv.features().names();
		SlicerSATFormula satSlicer = new SlicerSATFormula();
		Set<String> ftsToExclude = SlicerSATFormula.calculateFtsToExclude(
				allFtsDomain, ftsToSlice, SliceMode.INCLUDING);
		satSlicer.setReplacingMapAbstractFts(SATBuilder.calculateReplacingMap(
				fmv, ftsToExclude));

		SATFormula satFla = (SATFormula) satSlicer.runSliceFormulaSAT(fmv,
				new SATBuilder().mkNode(fmv), allFtsDomain, ftsToSlice,
				SliceMode.INCLUDING);

		/*
		 * SATFormula satFla = new SATNode4JSlicer(fmv).runSliceFormulaSAT(
		 * nodeFmv, fmv.features().names(), ftsToSlice, SliceMode.INCLUDING, "")
		 * ;
		 */

		System.err.println("# (node) = ");
		System.err.println(Node4JUtil.countSizeOfNode(satFla.getNode()));
		System.err.println("Satisfiability?");

		// cost consuming test, does not scale for large feature models
		Set<Set<String>> enumExpectedSetConfigs = new HashSet<Set<String>>();
		Set<Set<String>> enumSetConfigs = FMLUtils.setConfigToSetStr(fmv.configs());
		Set<String> ftsNotIncluded = Sets.intersection(fmv.features().names(),
				ftsToSlice);
		for (Set<String> config : enumSetConfigs) {
			enumExpectedSetConfigs.add(Sets
					.intersection(config, ftsNotIncluded));
		}
		double countEnumerative = new Double(enumExpectedSetConfigs.size());
		double n2 = satFla.counting();
		System.err.println("n2=" + n2);
		assertEquals(countEnumerative, n2, 0);

		assertTrue(satFla.isValid());
		_shell.setVerbose(false);

	}

	@Test
	public void testLinux() throws Exception {

		File f = new File("linux-2.6.32.fml");
		int PER = 70;
		testSliceLinuxScalability(f, PER);
		_shell.reset();

	}

	private void testSliceLinuxScalability(File file, int per) throws Exception {

		String strFM = FileUtils.read(file);
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(strFM);
		} catch (Exception e) {
			System.err.println("\tImpossible to parse");
			return;
		}
		if (fm == null) {
			System.err.println("\t\tImpossible to parse");
			return;
		}
		System.err.println("Parsing done");
		FeatureModelVariable fmv = new FeatureModelVariable("", fm);
		_shell.setVerbose(true);

		/*
		 * splar.core.fm.FeatureModel fmSPLOT = fmv.toSPLOT() ;
		 * fmSPLOT.getAverageDepth() ;
		 */

		// fmv.fixFreeVariables();

		// System.err.println("fide:" + fmv.convert(FMFormat.FIDE));
		Set<String> ftsToSlice = SetUtility.selectRandomly(fmv.features()
				.names(), per);

		System.err.println("Building the node");
		Node nodeFmv = new SATBuilder().mkNode(fmv);
		System.err.println("# original node = ");
		System.err.println(Node4JUtil.countSizeOfNode(nodeFmv));
		System.err
				.println("\n\n==== SAT formula is ready -- now slicing! =====");
		// new SATNode4JSlicer(fmv).runSliceFormulaSAT(ftsToSlice,
		// SliceMode.INCLUDING, "") ;

		Set<String> allFtsDomain = fmv.features().names();
		SlicerSATFormula satSlicer = new SlicerSATFormula();
		Set<String> ftsToExclude = SlicerSATFormula.calculateFtsToExclude(
				allFtsDomain, ftsToSlice, SliceMode.INCLUDING);
		// satSlicer.setReplacingMapAbstractFts(SATBuilder.calculateReplacingMap(fmv,
		// ftsToExclude));

		SATFormula satFla = (SATFormula) satSlicer.runSliceFormulaSAT(fmv,
				new SATBuilder().mkNode(fmv), allFtsDomain, ftsToSlice,
				SliceMode.INCLUDING);

		System.err.println("# (node) = ");
		System.err.println(Node4JUtil.countSizeOfNode(satFla.getNode()));
		System.err.println("Satisfiability?");
		// new SATNode4JSlicer(fmv).runSliceFormulaSAT(
		// ftsToSlice, SliceMode.INCLUDING, "") ;

		// double n = countingFormula(flaSliced);

		assertTrue(satFla.isValid());
		_shell.setVerbose(false);

	}

	@Test
	public void testScalability2() throws Exception {
		// testSPLOT_FMIdempotent(new File(_folderLocation + "/" +
		// "REAL-FM-5.xml"));
		INTEROP = false;

		int PER = 5; // 75; // 95 works for all cases
		String templateFileName = // mkTemplateFileName(5, 100, 0.3);
		// mkTemplateFileName(10, 100, 0.3);
		// mkTemplateFileName(20, 100, 0.3);
		// mkTemplateFileName(50, 100, 0.3);
		 mkTemplateFileName(100, 100, 0.7); // scale

		// mkTemplateFileName(300, 100, 0.35); // scale
		// mkTemplateFileName(400, 100, 0.3); // BDD fails
		// mkTemplateFileName(400, 70, 0.3); // scale
		// mkTemplateFileName(400, 90, 0.3); // scale
		// mkTemplateFileName(2000, 20, 0.4); // only 1, 2 and 3 (files)
		// mkTemplateFileName(3000, 20, 0.2);
		// mkTemplateFileName(5000, 20, 0.3);
		// mkTemplateFileName(500, 80, 0.3); // ??
		// mkTemplateFileName(1000, 10, 0.7);
		// mkTemplateFileName(800, 20, 0.4); // scale 80
		// mkTemplateFileName(800, 40, 0.4); // only 1 (files)
		// mkTemplateFileName(800, 60, 0.3); // only 1 (files)
		// "s5000/s5000-500/" + "SPLOT-3CNF-FM-5000-500-0.30-SAT-" ;
		// "s2000/s2000-200/SPLOT-3CNF-FM-2000-200-0.50-SAT-" ;
		// "s10000/s10000-1000/SPLOT-3CNF-FM-10000-1000-0.10-SAT-";
		// "sLinux/s1000/s1000\\MyCollection-3CNF-FM-1000-200-0,10-SAT-" ;
		// "sLinux/s2000/sLinux\\MyCollection-3CNF-FM-2000-400-0,30-SAT-";
		//"sLinux/s6000/s6000\\MyCollection-3CNF-FM-6000-1200-0,30-SAT-";
		// mkTemplateFileName(1000, 20, 0.4);
		// mkTemplateFileName(1000, 80, 0.2); // only 1, 2 and 3 (files)
		// mkTemplateFileName(1000, 70, 0.2);
		// mkTemplateFileName(1000, 60, 0.2);

		// mkTemplateFileName(800, 10, 0.4); // scale

		String fileExtension = ".fml"; // ".xml";

		_shell.reset();

		for (int i = 3; i <= 3; i++) {
			System.err.println("\n\n\n\t\t########### I=" + i
					+ " ##########\n\n\n");
			File f = new File(_folderGenModelLocation + "/" +
						templateFileName + i + fileExtension ) ;
			// new
			// File("/Users/mathieuacher/Desktop/FM-ElectronicShopping-May2011.fml")
			// ;
			//new File("linux-2.6.32.fml");
			testSliceScalability(f, PER);
			_shell.reset();
		}
	}

	private String mkTemplateFileName(int nFeatures, int CTCR, double density) {

		double dratio = (Double.parseDouble("" + nFeatures) * Double
				.parseDouble("" + CTCR)) / 100;
		int ratio = new Double(dratio).intValue();
		String strDensity = new Double(density).toString().replace(".", ",");
		String afterComma = strDensity.substring(strDensity.indexOf(",") + 1);
		if (afterComma.length() != 2)
			strDensity = strDensity + "0";
		return "s" + nFeatures + "/" + "s" + nFeatures + "-" + CTCR + "/" + "s"
				+ nFeatures + "-" + CTCR + "\\MyCollection-3CNF-FM-"
				+ nFeatures + "-" + ratio + "-" + strDensity + "-SAT-";
	}

	private String mkTemplateFileName(int nFeatures, int CTCR) {
		return mkTemplateFileName(nFeatures, CTCR, 1.0);
	}

	@Test
	public void testConstraintParsing() throws Exception {

		String expr = "undirected | !mstkruskal;" + "unweighted | !mstkruskal;"
				+ "undirected | !mstprim;" + "undirected | !stronglyc;"
				+ "unweighted | !mstprim;" + "DFS | !cycle;"
				+ "!mstprim | !mstkruskal;" + "undirected | !connected;"
				+ "directed | !shortest;" + "Search | !connected;"
				+ "unweighted | !shortest;" + "Search | !number;";

		
		Set<Expression<String>> le = MyExpressionParser.parseConstraints(expr) ; 
		assertEquals(12, le.size());

		System.err.println("le:");
		int n = 0;
		for (Expression<String> ex : le) {
			System.err.println("ex" + n++ + ": " + ex);
		}

	}

	@Test
	public void testXorVsOr1() throws Exception {

		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : (B|C|D|E)+ ; B -> !C ; )"); // MUTEX actually
		_shell.setVerbose(true);
		FeatureModelVariable fmv1Sliced = new FMSlicerBDD(_builder).sliceFM(fmv1, fmv1
		.features().names(), SliceMode.INCLUDING);

		System.err.println("fmv1Sliced=" + fmv1Sliced);
	}

	@Test
	public void testXorVsOr2() throws Exception {

		_shell.setVerbose(true);
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : (B|C|D|E)+ ; B -> !C ; B | C ;)"); // XOR here!
		FeatureModelVariable fmv1Sliced = new FMSlicerBDD(_builder).sliceFM(fmv1, fmv1
		.features().names(), SliceMode.INCLUDING);

		System.err.println("fmv1Sliced=" + fmv1Sliced);
	}

	@Test
	public void testSPLOT_FMsIdempotent() throws Exception {

		File folder = new File(_folderLocation);
		File[] files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				return name.endsWith(".xml") && !name.equals("REAL-FM-4.xml");
			}
		});

		System.err.println("Treating " + files.length
				+ " FMs from SPLOT repository\n");

		int n = 1;
		for (File file : files) {
			System.err.println("#" + n + " " + file.getName());
			testSPLOT_FMIdempotent(file);

			n++;
		}

	}

	@Test
	public void testSPLOT_FMsScalability() throws Exception {

		int PER = 10;

		File folder = new File(_folderLocation);
		File[] files = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File file, String name) {
				// return name.equals("REAL-FM-4.xml") ;
				return name.endsWith(".xml") && !name.equals("REAL-FM-4.xml");
			}
		});

		System.err.println("Treating " + files.length
				+ " FMs from SPLOT repository\n");

		int n = 1;
		for (File file : files) {
			System.err.println("#" + n + " " + file.getName());
			try {
				testSliceScalability(file, PER);
			} catch (Exception e) {
				System.err.println("############ FAIL ###########");
				continue;
			}
			n++;
		}

	}

	private void testSliceScalability(File file, int per) throws Exception {

		String strFM = FileUtils.read(file);
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(strFM);
		} catch (Exception e) {
			System.err.println("\tImpossible to parse");
			return;
		}
		if (fm == null) {
			System.err.println("\t\tImpossible to parse");
			return;
		}
		System.err.println("Parsing done");
		FeatureModelVariable fmv = new FeatureModelVariable("", fm);
		_shell.setVerbose(true);

		// fmv.fixFreeVariables();

		// System.err.println("fide:" + fmv.convert(FMFormat.FIDE));
		Set<String> ftsToSlice = SetUtility.selectRandomly(fmv.features()
				.names(), per);

		System.err.println("Building the node");
		Node nodeFmv = new SATBuilder().mkNode(fmv);
		System.err.println("# original node = ");
		System.err.println(Node4JUtil.countSizeOfNode(nodeFmv));
		System.err
				.println("\n\n==== SAT formula is ready -- now slicing! =====");
		// new SATNode4JSlicer(fmv).runSliceFormulaSAT(ftsToSlice,
		// SliceMode.INCLUDING, "") ;

		Set<String> allFtsDomain = fmv.features().names();
		SlicerSATFormula satSlicer = new SlicerSATFormula();
		Set<String> ftsToExclude = SlicerSATFormula.calculateFtsToExclude(
				allFtsDomain, ftsToSlice, SliceMode.INCLUDING);
		// satSlicer.setReplacingMapAbstractFts(SATBuilder.calculateReplacingMap(fmv,
		// ftsToExclude));

		SATFormula satFla = (SATFormula) satSlicer.runSliceFormulaSAT(fmv,
				new SATBuilder().mkNode(fmv), allFtsDomain, ftsToSlice,
				SliceMode.INCLUDING);

		System.err.println("# (node) = ");
		System.err.println(Node4JUtil.countSizeOfNode(satFla.getNode()));
		System.err.println("Satisfiability?");
		// new SATNode4JSlicer(fmv).runSliceFormulaSAT(
		// ftsToSlice, SliceMode.INCLUDING, "") ;

		// double n = countingFormula(flaSliced);

		assertTrue(satFla.isValid());
		_shell.setVerbose(false);

	}

	private void _testSliceScalability(File file, int per) throws Exception {

		boolean _testNotScaling = true;

		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				file.getAbsolutePath(), XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			e.printStackTrace();
			return;
		}

		String strFM_SPLOT = new SPLOTtoFML(INTEROP).convert(featureModelSPLOT);
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(strFM_SPLOT);
		} catch (Exception e) {
			System.err.println("\tImpossible to parse");
			return;
		}
		if (fm == null) {
			System.err.println("\t\tImpossible to parse");
			return;
		}

		FeatureModelVariable fmv = new FeatureModelVariable("", fm);

		// System.err.println("fmv=" +
		// fmv.getSyntacticalRepresentationWithoutCst());
		// System.err.println("==== SPLOT fla =====\n\n");
		// Formula<String> oFla = fmv.mkSPLOTFlaAligned(featureModelSPLOT,
		// _builder);
		// System.err.println("\n\n==== end SPLOT fla =====");

		// System.err.println("deads fmv=" + new FormulaAnalyzer<String>(oFla,
		// _builder).computeDeadFeatures());
		// oFla = new FormulaAnalyzer<String>(oFla,
		// _builder).removeDeadFeatures() ;
		// System.err.println("\n\n==== dead features =====");
		// System.err.println("generating ftsToSlice=====");
		Set<String> ftsToSlice = SetUtility.selectRandomly(fmv.features()
				.names(), per);

		// System.err.println("\t* ftsToSlice (incl)=" + ftsToSlice);
		// System.err.println("\t* oFla domain=" + oFla.getDomain());

		// Formula<String> flaSliced = //SliceAnalyzer.sliceFormula(fmv,
		// fmv.features().names(), SliceMode.INCLUDING, _builder);
		// SliceAnalyzer.sliceFormula(oFla, ftsToSlice, SliceMode.INCLUDING,
		// _builder);
		/*
		 * 
		 * 
		 * System.err.println("fmv.features().names()=" +
		 * fmv.features().names()); System.err.println("flaSliced (domain)=" +
		 * flaSliced.getDomain()); System.err.println("ftsToSlice (fla)=" +
		 * Sets.difference(fmv.features().names(), flaSliced.getDomain()));
		 * 
		 * 
		 * System.err.println("==== sliced Fla done! =====");
		 */

		// double nFlaSliced = countingFormula(flaSliced) ;
		// System.err.println("#flaSliced=" + nFlaSliced);

		// System.err.println("fmv (featureIDE)=" + fmv.convert(FMFormat.FIDE));

		// double nSAT = fmv.countingSAT() ;
		// System.err.println("#fmv (featureIDE) =" + nSAT);
		// double nc = fmv.counting() ;
		// System.err.println("#fmv =" + nc);

		// assertEquals(nc, nSAT, 0);

		_shell.setVerbose(true);
		Node nodeFmv = new SATBuilder().mkNode(fmv);
		System.err.println("# original node = ");
		System.err.println(Node4JUtil.countSizeOfNode(nodeFmv));
		System.err
				.println("\n\n==== SAT formula is ready -- now slicing! =====");
		SATFormula satFla = (SATFormula) new SlicerSATFormula().runSliceFormulaSAT(fmv,
				nodeFmv, fmv.features().names(), ftsToSlice,
				SliceMode.INCLUDING);

		System.err.println("# (node) = ");
		System.err.println(Node4JUtil.countSizeOfNode(satFla.getNode()));
		System.err.println("Satisfiability?");
		// new SATNode4JSlicer(fmv).runSliceFormulaSAT(
		// ftsToSlice, SliceMode.INCLUDING, "") ;

		// double n = countingFormula(flaSliced);

		if (_testNotScaling) {
			// cost consuming test, does not scale for large feature models
			Set<Set<String>> enumExpectedSetConfigs = new HashSet<Set<String>>();
			Set<Set<String>> enumSetConfigs = FMLUtils.setConfigToSetStr(fmv.configs());
			Set<String> ftsNotIncluded = Sets.intersection(fmv.features()
					.names(), ftsToSlice);
			for (Set<String> config : enumSetConfigs) {
				enumExpectedSetConfigs.add(Sets.intersection(config,
						ftsNotIncluded));
			}
			double countEnumerative = new Double(enumExpectedSetConfigs.size());
			double n2 = satFla.counting();
			System.err.println("n2=" + n2);
			assertEquals(countEnumerative, n2, 0);
		}

		// System.err.println("n=" + n);
		/*
		 * AllConfigsBDD allBDD = new AllConfigsBDD(_builder) ; Set<Set<String>>
		 * sols = allBDD.process(flaSliced); int i = 0 ; for (Set<String> sol :
		 * sols) { System.err.println("sol" + i++ + "=" + sol); }
		 * 
		 * System.err.println();
		 * 
		 * int j = 0 ; for (String sol : satFla.configs2()) {
		 * System.err.println("SATsol" + j++ + "=" + sol); }
		 */

		assertTrue(satFla.isValid());
		_shell.setVerbose(false);

		// assertNotNull(flaSliced);
		//
		//
		// //double nOfla = countingFormula(oFla) ;
		// //System.err.println("#oFla" + nOfla);
		// System.err.println("begin hierarchy computation");
		// FeatureGraph<String> hierarchySliced =
		// SliceAnalyzer.sliceHierarchy(fmv, ftsToSlice, SliceMode.INCLUDING,
		// _builder);
		// assertNotNull(hierarchySliced);
		// System.err.println("end hierarchy computation");
		//
		// System.err.println("slicing the whole");
		// //_shell.setVerbose(true);
		// FeatureModelVariable fmvSliced =
		// SliceAnalyzer.sliceFMVlazyConstraint(flaSliced, fmv, ftsToSlice,
		// SliceMode.INCLUDING, _builder);//
		// SliceAnalyzer.sliceFMVlazyConstraint(flaSliced, fmv,
		// fmv.features().names(), SliceMode.INCLUDING, _builder);
		// //SliceAnalyzer.sliceFMVlazyConstraint(fmv, ftsToSlice,
		// SliceMode.INCLUDING, _builder);//
		// SliceAnalyzer.sliceFMVlazyConstraint(flaSliced, fmv,
		// fmv.features().names(), SliceMode.INCLUDING, _builder);
		//
		// assertNotNull(fmvSliced);
		//
		//
		// assertNotNull(fmvSliced.getSyntacticalRepresentationWithoutCst());
		// System.err.println("end of slicing the whole");
		// System.err.println("fmvSliced=" +
		// fmvSliced.getSyntacticalRepresentationWithoutCst());
		//

	}

	private void testSPLOT_FMIdempotent(File file) throws Exception {

		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				file.getAbsolutePath(), XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			e.printStackTrace();
			return;
		}
		/*
		 * System.err.println("deads=" +
		 * SPLOTUtility.getSPLOTSATReasoner(featureModelSPLOT
		 * ).allDeadFeatures(new HashMap<String, String>()));
		 * System.err.println("#=" +
		 * SPLOTUtility.getSPLOTSATReasoner(featureModelSPLOT
		 * ).countValidConfigurations());
		 */
		// ReasoningWithBDD splotBDDReasoner =
		// SPLOTUtility.getSPLOTBDDReasoner(featureModelSPLOT) ;
		// System.err.println("\n\n\n\nbuilding time=" +
		// splotBDDReasoner.getBDDBuildingTime() + "\n\n\n\n");
		// _builder =
		// BDDBuilderFactory.mkBuilder(splotBDDReasoner.getVarName2IndexMap());
		// System.err.println("#=" +
		// splotBDDReasoner.countValidConfigurations());

		String strFM_SPLOT = new SPLOTtoFML(INTEROP).convert(featureModelSPLOT);

		// System.err.println("strFM_SPLOT=" + strFM_SPLOT);
		_shell.setVerbose(true);
		FeatureModel<String> fm = null;
		try {
			fm = FMBuilder.getInternalFM(strFM_SPLOT);
		} catch (Exception e) {
			System.err.println("\tImpossible to parse");
			return;
		}
		if (fm == null) {
			System.err.println("\t\tImpossible to parse");
			return;
		}
		_shell.setVerbose(false);
		Set<String> ftNames = ftNamesInSplot(featureModelSPLOT);
		System.err.println("SPLOT ftNames=" + ftNames);

		FeatureModelVariable fmv = new FeatureModelVariable("", fm);
		// assertEquals(ftNames, fmv.features().names());

		// System.err.println("fmv=" + fmv);
		// System.err.println("#fmv=" + fmv.countingBDD());
		// System.err.println("(diagram) fmv=" + fmv.getFm().getDiagram());
		// System.err.println("deads fmv=" + fmv.deads());

		// Set<String> deadsSPLOT = fmv.deadsSPLOT() ;
		// System.err.println("(SPLOT) deads fmv=" + deadsSPLOT);
		// System.err.println("(SPLOT) #deads fmv=" + deadsSPLOT.size());
		/*
		 * Slicing Itself
		 */
		// assertNotNull(fmv.toSPLOT());

		System.err.println("==== SPLOT fla =====\n\n");
		Formula<String> oFla = fmv.mkSPLOTFlaAligned(featureModelSPLOT,
				_builder);
		System.err.println("\n\n==== end SPLOT fla =====");

		oFla = new FormulaAnalyzer<String>(oFla, _builder).removeDeadFeatures();
		// _shell.setVerbose(true);
		Formula<String> flaSliced = // SliceAnalyzer.sliceFormula(fmv,
									// fmv.features().names(),
									// SliceMode.INCLUDING, _builder);
				new SlicerBDDFormula(_builder).sliceFormula(oFla, fmv.features().names(),
				SliceMode.INCLUDING);
		System.err.println("==== sliced Fla done! =====");
		double nFlaSliced = countingFormula(flaSliced);
		System.err.println("#flaSliced=" + nFlaSliced);
		// assertEquals(0, Sets.intersection(deadsSPLOT,
		// flaSliced.getDomain()).size());
		// assertEquals(flaSliced.getDomain(), oFla.getDomain());
		// System.err.println("[[flaSliced]]=" + flaSliced.getDomain());
		assertNotNull(flaSliced);

		double nOfla = countingFormula(oFla);
		System.err.println("#oFla" + nOfla);

		// assertEquals(nOfla, nFlaSliced, 0);

		/*
		 * does not work for Electronic Shopping (bad implementation of
		 * transitive reduction)
		 */
		System.err.println("begin hierarchy computation");
		FeatureGraph<String> hierarchySliced = new FMSlicerBDD(_builder).sliceHierarchy(
				fmv, fmv.features().names(), SliceMode.INCLUDING);
		assertNotNull(hierarchySliced);
		System.err.println("end hierarchy computation");

		System.err.println("slicing the whole");
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmv, fmv.features().names(), SliceMode.INCLUDING);// SliceAnalyzer.sliceFMVlazyConstraint(flaSliced,
																			// fmv,
																			// fmv.features().names(),
																			// SliceMode.INCLUDING,
																			// _builder);
		assertNotNull(fmvSliced);
		assertNotNull(fmvSliced.getSyntacticalRepresentationWithoutCst());
		System.err.println("end of slicing the whole");
		// assertParentChildConformityTuned(fmv, fmvSliced);
		/*
		 * Set<String> diffDomain1 =
		 * Sets.difference(fmvSliced.getFormula().getDomain(),
		 * oFla.getDomain()); System.err.println("diffDomain1=" + diffDomain1);
		 * Set<String> diffDomain2 = Sets.difference(oFla.getDomain(),
		 * fmvSliced.getFormula().getDomain());
		 * System.err.println("diffDomain2=" + diffDomain2);
		 * System.err.println("#diffDomain2=" + diffDomain2.size());
		 * 
		 * assertEquals(fmvSliced.getFormula().getDomain(), oFla.getDomain());
		 */
		/*
		 * int cmp = compareVariabilityOperators(fmv, fmvSliced) ; if (cmp > 0)
		 * { System.err.println("************ errors detected ***************");
		 * }
		 */

		// Formula<String> flaDiff = fmvSliced.diffFormula(oFla, _builder);
		// System.err.println("[[flaDiff]]=" + new
		// AllConfigsBDD(_builder).process(flaDiff, 5));
		// assertTrue(fmvSliced.getFormula().equals(oFla));

		// System.err.println("#fmvSliced=" + fmvSliced.countingBDD());
		// assertEquals(0, cmp);

	}

	private Set<String> ftNamesInSplot(
			splar.core.fm.FeatureModel featureModelSPLOT) {
		Set<String> names = new HashSet<String>();

		Collection<FeatureTreeNode> ftNodes = featureModelSPLOT.getNodes();
		for (FeatureTreeNode ftNode : ftNodes) {
			if (!(ftNode instanceof FeatureGroup))
				names.add(ftNode.getName());
		}

		return names;
	}

}
