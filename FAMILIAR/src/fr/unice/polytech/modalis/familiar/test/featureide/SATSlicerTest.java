package fr.unice.polytech.modalis.familiar.test.featureide;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.sat4j.specs.TimeoutException;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import de.ovgu.featureide.fm.core.Feature;
import de.ovgu.featureide.fm.core.FeatureModel;
import fr.unice.polytech.modalis.familiar.fm.FMLUtils;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFormula;
import fr.unice.polytech.modalis.familiar.operations.featureide.SlicerSATFormula;
import fr.unice.polytech.modalis.familiar.operations.featureide.SlicerSATFormulaWithFeatureIDE;
import fr.unice.polytech.modalis.familiar.test.FMLSlicerUtilityTest;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class SATSlicerTest extends FMLSlicerUtilityTest {

	private String _thumSpecification = "G : E [A] ; E : (D|U) ; A : (N|S|C)+ ; (S | C) -> D ; ";

	/**
	 * true if we use internally the bridge with FeatureIDE otherwise we use the
	 * translation FML to Prop4J directly (fully interoperable)
	 */
	private boolean _withFeatureIDE = false ;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * 
	 * the test does not pass if executed with others (but works individually)
	 * @throws Exception
	 *             Just to check some salient properties of BDD based
	 *             implementation
	 * 
	 */
	@Ignore
	@Test
	public void ThumSPLC2011WithBDD() throws Exception {

		// _shell.setVerbose(true);
		FeatureModelVariable fm0 = FM("fm0", _thumSpecification);
		Set<Set<String>> s0 = FMLUtils.setConfigToSetStr(fm0.configs()) ; 
		System.err.println("fm0=" + s0);

		double n0 = fm0.counting();
		assertEquals(n0, 10, 0);
//		System.err.println("\t" + prettyConfigs(fm0));
		fm0.setBuilder(_builder);  
		FeatureModelVariable fm1 = fm0.slice(SliceMode.EXCLUDING, 
				new HashSet<String>(Arrays.asList(new String[] { "A" }))); // abstract
																			// features

				
		double n1 = fm1.counting();
		assertEquals(10, n1, 0);
		
		Set<Set<String>> s1 = new HashSet<Set<String>>() ;
		for (Set<String> s : s0) {
			Set<String> sP = new HashSet<String>(s);
			sP.remove("A") ; 
			s1.add(sP);
		}
		assertEquals(s1.size(), n1, 0);

		FeatureModelVariable fm2 =
				fm0.slice(SliceMode.EXCLUDING,  
						new HashSet<String>(Arrays.asList(new String[] { "S" }))) ; // abstract	// features)

		double n2 = fm2.counting();
		Set<Set<String>> s2 = new HashSet<Set<String>>() ;
		for (Set<String> s : s0) {
			Set<String> sP = new HashSet<String>(s);
			sP.remove("S") ; 
			s2.add(sP);
		}
		System.err.println("s2=" + s2);
		assertEquals(s2.size(), n2, 0);
		assertEquals(7, n2, 0);
		
		
		

		FeatureModelVariable fm3 = 
			fm0.slice(SliceMode.EXCLUDING,  
					new HashSet<String>(Arrays.asList(new String[] { "E" })));
	

		double n3 = fm3.counting();
		assertEquals(10, n3, 0);

		FeatureModelVariable fm4 = fm0.slice(SliceMode.EXCLUDING,  
				new HashSet<String>(Arrays.asList(new String[] { "E", "A" }))); // abstract features
	
		double n4 = fm4.counting();
		assertEquals(10, n4, 0);

		FeatureModelVariable fm5 = fm0.slice(SliceMode.EXCLUDING,  
				new HashSet<String>(Arrays.asList(new String[] { "E", "S" }))); // abstract features

		double n5 = fm5.counting();
		System.err.println("\t" + prettyConfigs(fm5));
		assertEquals(7, n5, 0);

		/*
			 * 
			 * 
			 */

		FeatureModelVariable fmThum = fm0.slice(SliceMode.EXCLUDING,  
				new HashSet<String>(Arrays.asList(new String[] { "E", "A", "S" }))); // abstract features

		System.err.println("fmThum" + fmThum); // TODO see Figure 8

		double n = fmThum.counting();
		assertEquals(6, n, 0);
		System.err.println("\t" + prettyConfigs(fmThum));

		// FIXME
		// currently the result is: G: (U|D) [N] ; D: [C] ;
		// this is not correct, we want...
		FeatureModelVariable fmThumExpected = FM("fmThumExpected",
				"G: (U|D) [N] [C] ; C -> D ;");
		assertEquals(Comparison.REFACTORING, fmThumExpected.compare(fmThum));

	}

	/**
	 * @throws Exception
	 *             We basically want the same result
	 * 
	 */
	@Test
	public void ThumSPLC2011WithSAT() throws Exception {

		// _shell.setVerbose(true);
		FeatureModelVariable fm0 = FM("fm0", _thumSpecification);

		SATFeatureIDEFormula fla0 = new SATFeatureIDEFormula(fm0);
		double n0 = fla0.counting();
		assertEquals(10, n0, 0);

		SATFMLFormula fMLfla0 = new SATFMLFormula(fm0);
		double n00 = fMLfla0.counting();
		// FIXME if needs be
		// assertEquals(10, n00, 0);

		_shell.setVerbose(true);
		SATFormula fla1 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(new String[] { "S" })) // "E",
																			// "A",
																			// "S"//
																			// abstract
																			// features
				, SliceMode.EXCLUDING);

		double n1 = fla1.counting();
		System.err.println("[[configs]]=" + fla1.configs());
		assertEquals(7, n1, 0);

		checkBDDandSATSlicing(fm0, "S");

		_shell.setVerbose(true);
		SATFormula fla2 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(new String[] { "A" })) // "E",
																			// "A",
																			// "S"//
																			// abstract
																			// features
				, SliceMode.EXCLUDING);

		double n2 = fla2.counting();
		System.err.println("[[configs]]=" + fla2.configs());

		assertEquals(10, n2, 0);

		checkBDDandSATSlicing(fm0, "A");

		SATFormula fla3 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(new String[] { "E" })) // "E",
																			// "A",
																			// "S"//
																			// abstract
																			// features
				, SliceMode.EXCLUDING);

		double n3 = fla3.counting();
		System.err.println("[[configs]]=" + fla3.configs());
		assertEquals(10, n3, 0);

		SATFormula fla4 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(new String[] { "E", "A" })) // //
																				// abstract
																				// features
				, SliceMode.EXCLUDING);

		double n4 = fla4.counting();
		System.err.println("[[configs]]=" + fla4.configs());
		assertEquals(10, n4, 0);

		SATFormula fla5 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(new String[] { "S", "E" })) // //
																				// abstract
																				// features
				, SliceMode.EXCLUDING);

		double n5 = fla5.counting();
		System.err.println("[[configs]]=" + fla5.configs2());

		checkBDDandSATSlicing(fm0, "S", "E");

		assertEquals(7, n5, 0);

		SATFormula fla6 = runSliceFormulaSAT(
				fm0,
				new HashSet<String>(Arrays
						.asList(new String[] { "E", "A", "S" })) // // abstract
																	// features
				, SliceMode.EXCLUDING);

		double n6 = fla6.counting();
		System.err.println("[[configs]]=" + fla6.configs());
		assertEquals(6, n6, 0);

		// to experiment
		System.err.println("D and U are mutually exclusive, right?:"
				+ mutuallyExclusive(fm0, "D", "U"));
		System.err.println("D and E are mutually exclusive, right?:"
				+ mutuallyExclusive(fm0, "D", "E"));
	}

	@Test
	public void test1() throws Exception {

		FeatureModelVariable fm0 = FM("fm0",
				"R : (g_1_4 | g_1_1 | g_1_2 | g_1_3) ; g_1_1 | g_1_2 | g_1_3 ;");
		checkBDDandSATSlicing(fm0, "g_1_3", "g_1_4");

	}

	@Test
	public void test2() throws Exception {

		FeatureModelVariable fm0 = FM("fm0",
				"R: (g_3_2|g_3_1)+ [o_2] m_1 ; ! g_3_1 ; ");

		checkBDDandSATSlicing(fm0, "g_3_1", "g_3_2");
		checkBDDandSATSlicing(fm0, "o_2", "m_1");
		checkBDDandSATSlicing(fm0, "o_2");
		checkBDDandSATSlicing(fm0, "R", "o_2", "m_1");

		checkBDDandSATSlicing(fm0, "R", "g_3_2");
		checkBDDandSATSlicing(fm0, "R", "g_3_1", "g_3_2");

		// FIXME: the empty set is not counted! (BDD I guess)
		checkBDDandSATSlicing(fm0, "R", "o_2");
	}

	@Test
	public void test3() throws Exception {

		FeatureModelVariable fm0 = FM("fm0",
				"R: [o_2] [o_4] [o_1] [o_3] ; o_2 -> o_1 ; ");

		checkBDDandSATSlicing(fm0, "R", "o_1", "o_2"); // FIXME: BDD is faulty
														// as well as version
														// with FeatureIDE but
														// not FML one!
		checkBDDandSATSlicing(fm0, "o_3", "o_2");
		checkBDDandSATSlicing(fm0, "o_1", "o_3", "o_2");
		checkBDDandSATSlicing(fm0, "o_1", "o_2");
		checkBDDandSATSlicing(fm0, "o_1", "o_3");
		checkBDDandSATSlicing(fm0, "o_1", "o_4");
		checkBDDandSATSlicing(fm0, "o_4", "o_3");
		checkBDDandSATSlicing(fm0, "R", "o_2"); // FIXME: BDD is faulty!
	}

	/**
	 * @param fm0
	 * @param fts
	 *            set of features to *exclude*
	 * @throws Exception
	 */
	private void checkBDDandSATSlicing(FeatureModelVariable fm0, String... fts)
			throws Exception {

		_shell.setVerbose(true);
		FeatureModelVariable fm1BDD = runSliceFMV(fm0, new HashSet<String>(
				Arrays.asList(fts)), SliceMode.EXCLUDING, "fm1BDD");

		double nBDD = fm1BDD.counting (CountingStrategy.BDD_FML);
		System.err.println("nBDD=" + Double.valueOf(nBDD).toString());

		_shell.setVerbose(true);
		SATFormula fla1 = runSliceFormulaSAT(fm0,
				new HashSet<String>(Arrays.asList(fts)), SliceMode.EXCLUDING);

		double nSAT = fla1.counting();
		System.err.println("nSAT=" + Double.valueOf(nSAT).toString());
		Set<Set<String>> enumExpectedSetConfigs = new HashSet<Set<String>>();
		Set<Set<String>> enumSetConfigs = FMLUtils.setConfigToSetStr(fm0.configs());
		Set<String> ftsNotIncluded = Sets.difference(fm0.features().names(),
				new HashSet<String>(Arrays.asList(fts)));
		for (Set<String> config : enumSetConfigs) {
			enumExpectedSetConfigs.add(Sets
					.intersection(config, ftsNotIncluded));
		}
		double countEnumerative = new Double(enumExpectedSetConfigs.size());
		System.err.println("#set (enumerative)=" + countEnumerative);
		System.err.println("set (enumerative)=" + enumExpectedSetConfigs);

		System.err.println("BDD=" + FMLUtils.setConfigToSetStr(fm1BDD.configs()));
		System.err.println("SAT=" + fla1.configsDebug());

		assertEquals(Double.valueOf(countEnumerative), Double.valueOf(nSAT));

		// assertEquals(Double.valueOf(nBDD), Double.valueOf(nSAT), 0.1);
		// assertTrue("nBDD=" + nBDD + " vs countEnumerative=" +
		// countEnumerative,
		// nBDD == countEnumerative);

	}

	private boolean mutuallyExclusive(FeatureModelVariable fm0, String... fts)
			throws TimeoutException {
		FeatureModel fm0IDE = new FMLtoFeatureIDE(fm0).convert();
		ArrayList<Feature> lfts = new ArrayList<Feature>();
		for (String ft : fts) {
			lfts.add(fm0IDE.getFeature(ft));
		}

		List<Set<Feature>> mutually = new ArrayList<Set<Feature>>();
		mutually.add(new HashSet<Feature>(lfts));
		return fm0IDE.areMutualExclusive(new HashSet<Feature>(), mutually);

	}

	private SATFormula runSliceFormulaSAT(FeatureModelVariable fmToSlice,
			Collection<String> fts, SliceMode mode) {

		if (_withFeatureIDE)
			return (SATFormula) new SlicerSATFormulaWithFeatureIDE().sliceFormula(fmToSlice, fts, mode) ;
		else {
			return (SATFormula) new SlicerSATFormula().sliceFormula(fmToSlice, fts, mode) ;
	
		}

	}

}
