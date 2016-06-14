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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;
import org.junit.After;
import org.junit.Before;
import org.xtext.example.mydsl.fml.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.fm.FMLUtils;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.AllConfigsBDD;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.ExpressionUtility;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.parser.DoubleVariable;
import fr.familiar.parser.FMBuilder;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.VariabilityOperatorVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

/**
 * @author mathieuacher Each FAMILIAR test case should extend FMLTest
 */
public abstract class FMLTest {

	protected FMLShell _shell;

	protected FMLCommandInterpreter _environment;

	protected BDDBuilder<String> _builder;

	// a complicated example
	public static final String FM_LAPTOP = "FM (Laptop: Screen CPU RAM CG HDD Battery Connectivity [Warranty] ;"
			+ " Screen: (s12|s15|s17) ;"
			+ " CPU: (LowTDP|HighTDP) ;"
			+ " LowTDP: (Atom270|AtomZ320) ;"
			+ " HighTDP: (Core2T6600|Core2P7350) ;"
			+ " RAM: (Kingstom2Go666|Kingstom4Go666|Corsair4Go1030) ;"
			+ " CG: (Integrated|Standalone) ;"
			+ " Integrated: GMA ;"
			+ " Standalone: (Nvidia8400M|Nvdia8600M|Nvidia3670|Nvidia9400M) ;"
			+ " HDD: (WD160Go5400tr|WD500Go) ;"
			+ " WD500Go: (S5400tr|S7200tr) ;"
			+ " Battery: (cells6|cells9) ;"
			+ " Connectivity: Wifi [Bluetooth] USB ;"
			+ " USB: (USB2|USB3)+ ;"
			+ " Wifi: (ABG|N) ;"
			+ " Warranty: (year2theft|year3theft) ;"
			+ " (Standalone -> HighTDP);" + " (LowTDP -> !Standalone); )";
	
	public static final String FM_LAPTOPbis = "FM (Laptop: Screen CPU RAM CG HDD Battery Connectivity [Warranty] ;"
		+ " Screen: (s12|s15|s17) ;"
		+ " CPU: (LowTDP|HighTDP) ;"
		+ " LowTDP: (Atom270|AtomZ320) ;"
		+ " HighTDP: (Core2T6600|Core2P7350) ;"
		+ " RAM: (Kingstom2Go666|Kingstom4Go666|Corsair4Go1030) ;"
		+ " CG: (Integrated|Standalone) ;"
		+ " Integrated: GMA ;"
		+ " Standalone: (Nvidia8400M|Nvdia8600M|Nvidia3670|Nvidia9400M) ;"
		+ " HDD: (WD160Go5400tr|WD500Go) ;"
		+ " WD500Go: (S5400tr|S7200tr) ;"
		+ " Battery: (cells6|cells9) ;"
		+ " Connectivity: Wifi [Bluetooth] ;"
		+ " Wifi: (ABG|N) ;"
		+ " Warranty: (year2theft|year3theft) ;"
		+ " (Standalone -> HighTDP);" + " (LowTDP -> !Standalone); )";

	/*
	 * properties to check when a feature model variable is loaded/imported
	 */
	public static final boolean FM_CHECK_SLICE = false;
	public static boolean FM_CHECK_PROPERTIES = false;
	public static boolean FM_CHECK_CLEANUP = false;

	public static boolean FM_CHECK_SPLOT_AND_DEPTH = false;
	public static boolean FM_CHECK_BDD = false;

	@Before
	public void setUp() throws Exception {
		_shell = FMLShell.instantiateStandalone(null);
		_environment = _shell.getCurrentEnv();
		_builder = FMLCommandInterpreter.getBuilder();

		//_shell.reset();
	}

	protected void stats(FeatureModelVariable fmv) {
		String fmIdentifier = fmv.getIdentifier();
		System.err.println("fm[" + fmIdentifier + "]="
				+ fmv.getSyntacticalRepresentation());
		System.err.println("#fm=" + fmv.counting());
		System.err.println("[[fm]]=" + FMLUtils.setConfigToSetStr(fmv.configs()));
		System.err.println("cores=" + fmv.cores());
	}

	/**
	 * @param fm1
	 *            string-based representation of fm1 (gsd.synthesis format)
	 * @param builder
	 *            the BDD builder
	 * @return number of valid solutions
	 */
	public static double countingBDD(String fm1, BDDBuilder<String> builder) {
		FeatureModel<String> fm = FMBuilder.getInternalFM(fm1);
		BDD b1 = builder.mkFeatureModel(fm).getBDD();
		BDD support = builder.mkSet(fm.features());
		double c = b1.satCount(support);
		b1.free();
		support.free();
		return c;
	}

	/**
	 * @param fm1
	 *            string-based representation of fm1 (gsd.synthesis format)
	 * @param builder
	 *            the BDD builder
	 * @return number of valid solutions
	 */
	@Deprecated
	public static double countingBDD(FeatureModel<String> fm,
			BDDBuilder<String> builder) {
		BDD b1 = builder.mkFeatureModel(fm).getBDD();
		BDD support = builder.mkSet(fm.features());
		double c = b1.satCount(support);
		b1.free();
		support.free();
		return c;
	}

	/**
	 * @param fm
	 *            feature model variable
	 * @param builder
	 *            the BDD builder
	 * @return number of valid solutions
	 */
	public static double countingBDD(FeatureModelVariable fm,
			BDDBuilder<String> builder) {
		if (FM_CHECK_PROPERTIES)
			assertEquals(fm.counting (CountingStrategy.BDD_FML), fm.counting (CountingStrategy.BDD_SPLOT), 0);
		return fm.counting (CountingStrategy.BDD_FML) ;
	}

	/**
	 * Utility function for testing
	 * 
	 * @param name
	 *            name of the feature supposed to be included in fts
	 * @param fts
	 *            set variable which represents a set of feature variables
	 * @return true if a feature with name @name is contained in the set of
	 *         feature variable @fts
	 */
	public static boolean isContained(String name, SetVariable fts) {

		boolean contained = false;
		for (Variable v : fts.getVars()) {
			assertTrue(v instanceof FeatureVariable);
			FeatureVariable ft = (FeatureVariable) v;
			if (ft.getFtName().equals(name)) {
				contained = true;
				break;
			}
		}

		return contained;
	}

	/**
	 * Determine if two feature models are equals (see BDDBuilderTest)
	 * 
	 * @param fm1
	 *            string-based representation of feature model
	 * @param fm2
	 *            string-based representation of feature model
	 */
	protected void assertFMsEqual(String fm1, String fm2) {
		assertFMsEqual(FMBuilder.getInternalFM(fm1), FMBuilder.getInternalFM(fm2));
	}

	/**
	 * Determine if two feature models are equals (see BDDBuilderTest)
	 * 
	 * @param fm1
	 *            feature model
	 * @param fm2
	 *            feature model
	 */
	protected void assertFMsEqual(FeatureModel<String> fm1,
			FeatureModel<String> fm2) {
		BDD b1 = _builder.mkFeatureModel(fm1).getBDD();
		BDD b2 = _builder.mkFeatureModel(fm2).getBDD();
		boolean result = b1.equals(b2);
		b1.free();
		b2.free();
		assertTrue(result);
	}

	/**
	 * @param flaExpected
	 *            formula expected
	 * @param fla
	 *            formula supposed to be equal to flaExpected Determine if two
	 *            formulas/BDDs are equals
	 */
	protected void assertFormulaEquals(Formula<String> flaExpected,
			Formula<String> fla) {
		BDD b1 = flaExpected.getBDD();
		BDD b2 = fla.getBDD();

		/*
		 * Formula<String> flaExpDiff = MergeAnalyzer.diff(flaExpected, fla) ;
		 * Set<Set<String>> sflaExpDiff = new
		 * AllConfigsBDD(_builder).process(flaExpDiff);
		 * System.err.println("[[ flaExpected -- fla ]]" + sflaExpDiff);
		 * 
		 * Formula<String> flaDiff = MergeAnalyzer.diff(fla, flaExpected) ;
		 * Set<Set<String>> sflaDiff = new
		 * AllConfigsBDD(_builder).process(flaDiff);
		 * System.err.println("[[ fla -- flaExpected ]]" + sflaDiff);
		 */
		assertTrue(b1.equals(b2));

	}

	/**
	 * @param flaExpected
	 *            formula expected
	 * @param fla
	 *            formula supposed to be equal to flaExpected Determine if two
	 *            formulas/BDDs are equals (not using equals of JavaBDD)
	 */
	protected void assertFormulaTunedEquals(Formula<String> flaExpected,
			Formula<String> fla) {
		Formula<String> properFlaExpected = new FormulaAnalyzer<String>(
				flaExpected, _builder).removeDeadFeatures();
		Formula<String> properFla = new FormulaAnalyzer<String>(fla, _builder)
				.removeDeadFeatures();

		BDD b1 = properFlaExpected.getBDD();
		BDD b2 = properFla.getBDD();
		// better equivalence checking!?
		assertBDDTunedEquals(b1, b2);
		assertTrue(b1.equals(b2));
	}

	protected void assertBDDTunedEquals(BDD b1, BDD b2) {
		BDD b3 = (b1.id().biimp(b2.id())).not(); // tautology
		assertTrue(b3.isZero()); // not satisfiable
	}

	/**
	 * @param flaExpected
	 *            formula expected
	 * @param fla
	 *            formula supposed to be equal to flaExpected Determine if two
	 *            formulas/BDDs are equals
	 */
	protected void assertFormulaNotEquals(Formula<String> flaExpected,
			Formula<String> fla) {
		BDD b1 = flaExpected.getBDD();
		BDD b2 = fla.getBDD();

		// assertFalse("formula not equal regarding counting support",
		// countingWithSupport(b1, flaExpected.getDomain()) ==
		// countingWithSupport(b2, fla.getDomain()));
		// better equivalence checking!?
		BDD b3 = (b1.id().biimp(b2.id())).not(); // tautology
		assertFalse(b3.isZero()); // not satisfiable
		assertFalse("formula not equal regarding BDD", b1.equals(b2));

	}

	/**
	 * @param b
	 * @param supports
	 * @return given a BDD and a set of features/variables, returns the number
	 *         of valid solution (aka domain)
	 */
	private double countingWithSupport(BDD bdd, Set<String> supports) {
		BDD support = _builder.mkSet(supports);
		double c = bdd.satCount(support);
		support.free();
		return c;
	}

	/**
	 * @param fla
	 * @return given a formula, return the number of valid solutions in the
	 *         domain
	 */
	protected double countingFormula(Formula<String> fla) {
		return countingWithSupport(fla.getBDD().id(), fla.getDomain());
	}

	/**
	 * @param sv
	 *            the set of variables to process
	 * @return a set of strings based representation of sv
	 */
	public static Set<String> setVariabletoString(SetVariable sv) {
		Set<String> result = new HashSet<String>();
		if (sv.size() == 0)
			return result;

		return setVariabletoString(sv.getVars());
	}

	/**
	 * @param sv
	 *            the set of variables to process
	 * @return a set of strings based representation of sv
	 */
	public static Set<String> setVariabletoString(Set<Variable> sv) {
		Set<String> result = new HashSet<String>();
		if (sv.size() == 0)
			return result;
		for (Variable variable : sv) {
			result.add(variable.getValue());
		}

		return result;
	}

	/**
	 * @param name
	 *            the feature name supposed to be in the set of propositional
	 *            constraints (expressions)
	 * @param constraints
	 *            the set of expression to consider
	 * @return determine whether name is involved in any expression
	 */
	public static boolean inExpressions(String name,
			Set<Expression<String>> constraints) {
		if (constraints.size() == 0)
			return false;
		for (Expression<String> cst : constraints) {
			if (ExpressionUtility.inExpression(name, cst))
				return true;
		}
		return false;
	}

	/**
	 * @param id
	 * @return a variable of type integer whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected IntegerVariable getIntegerVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof IntegerVariable);
		return (IntegerVariable) v;
	}

	/**
	 * @param id
	 * @return a variable of type double whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected DoubleVariable getDoubleVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof DoubleVariable);
		return (DoubleVariable) v;
	}

	/**
	 * @param id
	 * @return a variable of type feature model whose identifier is id in the
	 *         current environment
	 * @throws Exception
	 */
	protected FeatureModelVariable getFMVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof FeatureModelVariable);

		FeatureModelVariable fmv = (FeatureModelVariable) v;

		// after getting the feature model, we can test some implementation
		// properties of FML
		// (the idea is that this is a good opportunity to test FML framework)
		if (FM_CHECK_PROPERTIES)
			assertFMProperties(fmv);

		if (FM_CHECK_CLEANUP)
			assertFMCleanUp((FeatureModelVariable) fmv.copy()); // side effect

		if (FM_CHECK_SLICE)
			assertFMSlice(fmv); // no side effect

		if (FM_CHECK_SPLOT_AND_DEPTH)
			assertFMSPLOTandDepth(fmv);

		if (FM_CHECK_BDD)
			assertFMBDD(fmv);

		return fmv;

	}

	private void assertFMBDD(FeatureModelVariable fmv) {
		assertTrue(fmv.getFormula().equals(fmv.getFormula()));
	}

	/**
	 * check that 'depth' function is correctly implemented in FML and SPLOT
	 * actually, it is mostly to ensure the correctness of SPLOT conversion
	 * 
	 * @param fmv
	 */
	private void assertFMSPLOTandDepth(FeatureModelVariable fmv) {
		splar.core.fm.FeatureModel splotFmv = fmv.toSPLOT();
		if (splotFmv != null)
			assertEquals(splotFmv.depthFeatures(), fmv.depth());

	}

	/**
	 * The purpose is to check the following property. Let fmv be a feature
	 * model variable Does fmv' = fmv where fmv' = slice fmv including fmv.*? In
	 * terms of set of configuration but also in terms of hierarchy respect
	 * 
	 * @param fmv
	 */
	private void assertFMSlice(FeatureModelVariable fmv) {
		String fmSlicedID = fmv.getIdentifier() + "fmPrimed";
		FeatureModelVariable fmvPrime = new FMSlicerBDD(_builder).sliceFM(fmv, fmv
				.features().names(), SliceMode.INCLUDING);
		if (!fmv.isValid())
			return;
		// we slice only non void FMs
		fmvPrime.setIdentifier(fmSlicedID);
		System.err.println("fmv'=" + fmvPrime + "\n" + "fmv=" + fmv);
		assertFormulaTunedEquals(fmv.getFormula(), fmvPrime.getFormula());

		assertParentChildConformity(fmv, fmvPrime);
		// FIXME much more difficult
		// assertHierarchyTunedEquals(fmv, fmvPrime);
	}

	/**
	 * facility to write tests
	 * 
	 * @param fmID
	 * @param fmSpecification
	 * @return a feature model variable whose identifier is fmID and whose value
	 *         is fmSpecification (FML syntax)
	 * @throws Exception
	 */
	protected FeatureModelVariable FM(String fmID, String fmSpecification)
			throws Exception {

		String actualFmSpecification = "";
		if (!fmSpecification.startsWith("FM (")) {
			actualFmSpecification = "FM (" + fmSpecification + " )";
		} else
			actualFmSpecification = fmSpecification;

		_shell.parse(fmID + " = " + actualFmSpecification + "\n");
		return getFMVariable(fmID);

	}
	
	/**
	 * facility to write tests
	 * 
	 * @param fmID
	 * @param fmSpecification
	 * @return a feature model variable whose identifier is fmID and whose value
	 *         is fmSpecification (FML syntax)
	 * @throws Exception
	 */
	protected FeatureModelVariable FM(String fmSpecification)
			throws Exception {

		String fmID = "fm" + new Random().nextInt(100000);
		String actualFmSpecification = "";
		if (!fmSpecification.startsWith("FM (")) {
			actualFmSpecification = "FM (" + fmSpecification + " )";
		} else
			actualFmSpecification = fmSpecification;

		_shell.parse(fmID + " = " + actualFmSpecification + "\n");
		return getFMVariable(fmID);

	}

	/**
	 * ensure that cleanup operation does not alterate the set of configurations
	 * of FM
	 * 
	 * @param fmv
	 *            feature model variable to process
	 */
	private void assertFMCleanUp(FeatureModelVariable fmv) {
		if (fmv.isValid()) {
			Formula<String> oFla = fmv.getFormula();
			assertNotNull(oFla);
			fmv.cleanup();
			Formula<String> cleanupFla = fmv.getFormula();
			assertNotNull(cleanupFla);

			// domain may differ
			Set<String> diffDomain = Sets.difference(oFla.getDomain(),
					cleanupFla.getDomain());
			if (diffDomain.size() > 0) {
				BDD restrictedDomain = _builder.mkSet(diffDomain);
				BDD restrictedBDD = oFla.getBDD().exist(restrictedDomain);
				Formula<String> restrictedOFla = new Formula<String>(
						restrictedBDD, cleanupFla.getDomain(), _builder);
				assertFormulaEquals(restrictedOFla, cleanupFla);
			} else {
				assertFormulaEquals(oFla, cleanupFla);
			}
			/*
			 * Formula<String> diffFla = MergeAnalyzer.diff(restrictedOFla,
			 * cleanupFla, _builder); System.err.println("diffFla=" + diffFla);
			 * System.err.println("#diffFla=" + countingFormula(diffFla));
			 * System.err.println("13=" + _builder.feature(13));
			 * System.err.println("[[diffFla]]=" + new
			 * AllConfigsBDD(_builder).process(diffFla));
			 */

		}

	}

	public static int compareVariabilityOperators(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {
		System.err.println("\n\t===== comparison ======\n\n");
		System.err.println("fmv1=\n\t"
				+ fmv1.getSyntacticalRepresentationWithoutCst());
		System.err.println("fmv2=\n\t"
				+ fmv2.getSyntacticalRepresentationWithoutCst());

		Set<String> fts1 = fmv1.features().names();
		fts1.remove(fmv1.root().name());
		Set<String> fts2 = fmv2.features().names();
		fts2.remove(fmv2.root().name());

		int difference = 0;
		Set<String> deads1 = fmv1.deads();
		for (String ft1 : fts1) {
			VariabilityOperatorVariable vop1 = fmv1.getVOP(ft1);
			if (!fts2.contains(ft1)) {
				// strange!
				// except if ft1 is dead
				if (!deads1.contains(ft1))
					assert (false);

				continue;
			}
			VariabilityOperatorVariable vop2 = fmv2.getVOP(ft1);

			if (vop1.getFek() != vop2.getFek()) {
				difference++;
				System.err.println("feature_" + difference + ":" + ft1
						+ "\tvop1=" + vop1.getSpecificValue() + "\tvop2="
						+ vop2.getSpecificValue());

			}
		}

		System.err.println("\n\t===== end comparison ======\n\n");
		return difference;

	}

	/**
	 * ensure that some operations on the feature models are correctly performed
	 * using FML/FeatureIDE/SPLAR frameworks
	 * 
	 * @param fmv
	 *            feature model variable to process
	 */
	private void assertFMProperties(FeatureModelVariable fmv) {
		// test interoperability
		// if (fmv.isValid()) { // TODO: semantically and *syntactically* (e.g.,
		// constraints refer to existing features!) valid
		// assertNotNull(fmv.toFeatureIDE());
		// assertNotNull(fmv.toSPLOT());
		// assertEquals(fmv.countingBDD(), fmv.countingBDDSPLOT(), 0);
		// }
		if (fmv.isValid()) {

			assertFMCounting(fmv);
			// double splotCounting = fmv.countingBDDSPLOT();
			// double oCounting = fmv.counting();
			// System.err.println("#fmv=" + oCounting + " (splot) " +
			// splotCounting);
			// assertEquals(oCounting, splotCounting, 0);
			Formula<String> oFla = fmv.getFormula();
			// need to convert BDD splot into BDD FML (names of variable should
			// coincide)
			assertNotNull(oFla);

			Formula<String> splotFla = fmv.getSPLOTFormulaAligned(_builder);
			assertNotNull(splotFla);

			assertFormulaEquals(oFla, splotFla);
			// test reasoning operations interoperability
			// assertEquals(fmv.countingBDD(), fmv.countingBDDSPLOT(), 0);
		}

	}

	private void assertFMCounting(FeatureModelVariable fmv) {
		double splotCounting = fmv.counting (CountingStrategy.BDD_SPLOT); // SPLOT
		// double oCounting = fmv.countingBDD(); // FML
		// assertEquals(oCounting, splotCounting, 0);
		double satSPLOT = fmv.countingSATSPLOT();
		assertEquals(splotCounting, satSPLOT, 0);

	}

	/**
	 * @param id
	 * @return a variable of type variability operator whose identifier is id in
	 *         the current environment
	 * @throws Exception
	 */
	protected VariabilityOperatorVariable getVOPVariable(String id)
			throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof VariabilityOperatorVariable);
		return (VariabilityOperatorVariable) v;

	}

	/**
	 * @param id
	 * @return a variable of type configuration whose identifier is id in the
	 *         current environment
	 * @throws Exception
	 */
	protected ConfigurationVariable getConfigurationVariable(String id)
			throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof ConfigurationVariable);
		return (ConfigurationVariable) v;
	}

	/**
	 * @param id
	 * @return a variable of type string whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected StringVariable getStringVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof StringVariable);
		return (StringVariable) v;

	}

	/**
	 * @param id
	 * @return a variable of type Boolean whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected BooleanVariable getBooleanVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof BooleanVariable);
		return (BooleanVariable) v;
	}

	/**
	 * @param id
	 * @return a variable of type feature whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected FeatureVariable getFeatureVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof FeatureVariable);
		return (FeatureVariable) v;
	}
	
	
		
	
	protected ConstraintVariable mkConstraintVariable(String cstID, String exprString) throws Exception {
		_shell.parse(cstID + " = " + "constraint (" + exprString + ")");
		return getConstraintVariable(cstID);
	}
	
	/**
	 * @param id
	 * @return a variable of type feature whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected ConstraintVariable getConstraintVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof ConstraintVariable);
		return (ConstraintVariable) v;
	}
	

	

	/**
	 * @param id
	 * @return a variable of type set whose identifier is id in the current
	 *         environment
	 * @throws Exception
	 */
	protected SetVariable getSetVariable(String id) throws Exception {
		Variable v = _environment.getVariable(id);
		assertNotNull(v);
		assertTrue(v instanceof SetVariable);
		return (SetVariable) v;

	}

	
	/**
	 * Check that _shell is not executed with fatal errors (e.g., parsing
	 * errors) or assertion errors
	 */
	@After
	public void tearDown() {

		List<String> assertionErrors = _shell.getAssertionErrors();
		boolean successAssertion = _shell.hasAssertionErrors();

		List<String> fatalErrors = _shell.getFatalErrors();
		boolean successFatal = _shell.hasFatalErrors();

		_shell.reset();
		_shell.setVerbose(false);

		assertFalse("Fatal errors: " + fatalErrors, successFatal);
		assertFalse("Assertion errors: " + assertionErrors, successAssertion);
		
		
	}

	/**
	 * @param configs
	 * @return a set of string representation of configs
	 */
	protected Set<String> configsToSetString(Set<Variable> configs) {
		Set<String> r = new HashSet<String>();
		for (Variable fts : configs) {
			assertTrue(fts instanceof SetVariable);
			SetVariable sv = (SetVariable) fts;
			Set<Variable> vs = sv.getVars();
			for (Variable ft : vs) {
				assertTrue(ft instanceof FeatureVariable);
			}
			r.add(vs.toString());
		}
		return r;
	}

	/**
	 * @param flaExpected
	 * @param fla
	 *            determine whether two formulas are equal based on their sets
	 *            of configurations represented
	 */
	protected void assertConfigsEquals(Formula<String> flaExpected,
			Formula<String> fla) {
		AllConfigsBDD allBDD = new AllConfigsBDD(
				FMLCommandInterpreter.getBuilder());
		Set<Set<String>> configsExpected = allBDD.process(flaExpected);

		Set<Set<String>> configsFla = allBDD.process(fla);

		boolean b1 = configsExpected.containsAll(configsFla);
		if (!b1) {
			// diff
			System.err.println("Processing diff");
			Set<Set<String>> diff1 = Sets.difference(configsFla,
					configsExpected);
			System.err.println("(too much) diff1=" + diff1);
		}

		boolean b2 = configsFla.containsAll(configsExpected);
		if (!b2) {
			// diff
			System.err.println("Processing diff");
			Set<Set<String>> diff2 = Sets.difference(configsExpected,
					configsFla);
			System.err.println("(missing) diff2=" + diff2);
		}

		assertTrue(b1);
		assertTrue(b2);

	}

	protected <T> void assertSetEquals(Set<T> set1, Set<T> set2) {
		assertTrue(
				"\tset1=" + set1 + "\n is not equals to \n\tset2=" + set2
						+ "\n\t\tdiff set1 set2" + Sets.difference(set1, set2)
						+ "\n\t\tdiff set2 set1" + Sets.difference(set2, set1),
				Sets.difference(set1, set2).isEmpty()
						&& Sets.difference(set2, set1).isEmpty()
						&& set1.containsAll(set2) && set1.containsAll(set2));
	}

	/**
	 * hashcode issue for set equality
	 * @param expected
	 * @param constraints
	 */
	protected void assertMySetInclusion(Set<Expression<String>> expected,
			Set<Expression<String>> constraints) {
		for (Expression<String> exp : expected) {
			boolean found = false ;
			for (Expression<String> cstFM : constraints) {
				if (exp.equals(cstFM))
					found = true ; 
			}
			assertTrue(found);
		}
		
	}

	/**
	 * Check (based on sets of configurations) that the feature model whose
	 * identifier is strfm1 is an arbitrary edit of the feature model whose
	 * identifier is strfm2
	 * 
	 * @param strfm1
	 *            identifier of the first feature model
	 * @param strfm2
	 *            identifier of the second feature model
	 * @throws Exception
	 */
	protected void assertArbitrary(String strfm1, String strfm2)
			throws Exception {
		FeatureModelVariable fm1 = getFMVariable(strfm1);
		Set<Variable> sf1 = fm1.configs();
		Set<Set<String>> sf1Str = FMLUtils.setConfigToSetStr(sf1);

		FeatureModelVariable fm2 = getFMVariable(strfm2);
		Set<Variable> sf2 = fm2.configs();
		Set<Set<String>> sf2Str = FMLUtils.setConfigToSetStr(sf2);

		assertTrue("[[" + strfm1 + "]] ~ [[" + strfm2 + "]]: "
				+ "(diff fm1 fm2=" + Sets.difference(sf1Str, sf2Str)
				+ ", diff fm2 fm1=" + Sets.difference(sf2Str, sf1Str) + ")",
				!sf2Str.containsAll(sf1Str) && !sf1Str.containsAll(sf2Str));
	}

	/**
	 * Check (based on sets of configurations) that the feature model whose
	 * identifier is strfm1 is a refactoring of the feature model whose
	 * identifier is strfm2
	 * (of course this is not efficient)
	 * 
	 * @param strfm1
	 *            identifier of the first feature model
	 * @param strfm2
	 *            identifier of the second feature model
	 * @throws Exception
	 */
	protected void assertRefactoring(String strfm1, String strfm2)
			throws Exception {
		FeatureModelVariable fm1 = getFMVariable(strfm1);
		Set<Variable> sf1 = fm1.configs();
		Set<Set<String>> sf1Str = FMLUtils.setConfigToSetStr(sf1);

		FeatureModelVariable fm2 = getFMVariable(strfm2);
		Set<Variable> sf2 = fm2.configs();
		Set<Set<String>> sf2Str = FMLUtils.setConfigToSetStr(sf2);

		assertTrue("[[" + strfm1 + "]] not equals to [[" + strfm2 + "]]: "
				+ "(diff fm1 fm2=" + Sets.difference(sf1Str, sf2Str)
				+ ", diff fm2 fm1=" + Sets.difference(sf2Str, sf1Str) + ")",
				sf2Str.containsAll(sf1Str) && (sf1Str.containsAll(sf2Str)));
	}

	/**
	 * Check (based on sets of configurations) that the feature model whose
	 * identifier is strfm1 is a specialization of the feature model whose
	 * identifier is strfm2
	 * 
	 * @param strfm1
	 *            identifier of the first feature model
	 * @param strfm2
	 *            identifier of the second feature model
	 * @throws Exception
	 */
	protected void assertSpecialization(String strfm1, String strfm2)
			throws Exception {
		FeatureModelVariable fm1 = getFMVariable(strfm1);
		Set<Variable> sf1 = fm1.configs();
		Set<Set<String>> sf1Str = FMLUtils.setConfigToSetStr(sf1);

		FeatureModelVariable fm2 = getFMVariable(strfm2);
		Set<Variable> sf2 = fm2.configs();
		Set<Set<String>> sf2Str = FMLUtils.setConfigToSetStr(sf2);

		assertTrue("[[" + strfm1 + "]] not included in [[" + strfm2 + "]]: "
				+ "(diff fm1 fm2=" + Sets.difference(sf1Str, sf2Str)
				+ ", diff fm2 fm1=" + Sets.difference(sf2Str, sf1Str) + ")",
				sf2Str.containsAll(sf1Str) && !(sf1Str.containsAll(sf2Str)));
	}

	/**
	 * Check (based on sets of configurations) that the feature model whose
	 * identifier is strfm1 is a generalization of the feature model whose
	 * identifier is strfm2
	 * 
	 * @param strfm1
	 *            identifier of the first feature model
	 * @param strfm2
	 *            identifier of the second feature model
	 * @throws Exception
	 */
	protected void assertGeneralization(String strfm1, String strfm2)
			throws Exception {
		FeatureModelVariable fm1 = getFMVariable(strfm1);
		Set<Variable> sf1 = fm1.configs();
		Set<Set<String>> sf1Str = FMLUtils.setConfigToSetStr(sf1);

		FeatureModelVariable fm2 = getFMVariable(strfm2);
		Set<Variable> sf2 = fm2.configs();
		Set<Set<String>> sf2Str = FMLUtils.setConfigToSetStr(sf2);

		assertTrue("[[" + strfm1 + "]] not super set of [[" + strfm2 + "]]: "
				+ "(diff fm1 fm2=" + Sets.difference(sf1Str, sf2Str)
				+ ", diff fm2 fm1=" + Sets.difference(sf2Str, sf1Str) + ")",
				sf1Str.containsAll(sf2Str) && !(sf2Str.containsAll(sf1Str)));
	}

	/**
	 * check parent-child relations AND variability information on the features
	 * *common* to both FMs
	 * 
	 * @param fmv1
	 * @param fmv2
	 */
	protected void assertHierarchyTunedEquals(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {
		final FeatureGraph<String> hierarchy1 = fmv1.getFm().getDiagram();
		final FeatureGraph<String> hierarchy2 = fmv2.getFm().getDiagram();

		final Set<FeatureNode<String>> commonVertices = Sets.intersection(
				hierarchy1.vertices(), hierarchy2.vertices());

		int matches = CollectionUtils.countMatches(hierarchy1.edges(),
				new Predicate<FeatureEdge>() {

					public boolean evaluate(FeatureEdge e) {

						Set<FeatureNode<String>> sources1 = hierarchy1
								.getSources(e);
						if (!commonVertices.containsAll(sources1))
							return true;
						FeatureNode<String> target1 = hierarchy1.getTarget(e);
						if (!commonVertices.contains(target1))
							return true;

						return hierarchy2.findEdge(sources1, target1,
								e.getType()) != null;
					}

				});

		assertTrue(matches == hierarchy1.edges().size());
	}

	/**
	 * check parent-child relations AND variability information
	 * 
	 * @param fmv1
	 * @param fmv2
	 */
	protected void assertHierarchyEquals(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {
		final FeatureGraph<String> hierarchy1 = fmv1.getFm().getDiagram();
		final FeatureGraph<String> hierarchy2 = fmv2.getFm().getDiagram();

		int matches = CollectionUtils.countMatches(hierarchy1.edges(),
				new Predicate<FeatureEdge>() {

					public boolean evaluate(FeatureEdge e) {

						return hierarchy2.findEdge(hierarchy1.getSources(e),
								hierarchy1.getTarget(e), e.getType()) != null;
					}

				});

		assertTrue(matches == hierarchy1.edges().size()
				&& hierarchy1.vertices().equals(hierarchy2.vertices()));
	}

	/**
	 * @param hierarchy1
	 * @param hierarchy2
	 */
	protected void assertParentChildConformityTuned(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {

		assertParentChildConformityTuned(fmv1.getFm().getDiagram(), fmv2
				.getFm().getDiagram());
	}

	/**
	 * for common features
	 * 
	 * @param hierarchy1
	 * @param hierarchy2
	 */
	private void assertParentChildConformityTuned(
			final FeatureGraph<String> hierarchy1,
			final FeatureGraph<String> hierarchy2) {
		final Set<FeatureEdge> parentChildEdges1 = hierarchy1
				.selectEdges(FeatureEdge.HIERARCHY);
		final Set<FeatureEdge> parentChildEdges2 = hierarchy2
				.selectEdges(FeatureEdge.HIERARCHY);

		final Set<FeatureNode<String>> commonVertices = Sets.intersection(
				hierarchy1.vertices(), hierarchy2.vertices());
		Set<FeatureEdge> commonEdges1 = new HashSet<FeatureEdge>();
		for (FeatureEdge fe : hierarchy1.edges()) {
			Set<FeatureNode<String>> sources1 = hierarchy1.getSources(fe);
			if (!commonVertices.containsAll(sources1))
				continue;
			FeatureNode<String> target1 = hierarchy1.getTarget(fe);
			if (!commonVertices.contains(target1))
				continue;

			commonEdges1.add(fe);
		}

		int matches = CollectionUtils.countMatches(parentChildEdges1,
				new Predicate<FeatureEdge>() {

					public boolean evaluate(FeatureEdge e) {

						Set<FeatureNode<String>> sources1 = hierarchy1
								.getSources(e);
						if (!commonVertices.containsAll(sources1))
							return true;
						FeatureNode<String> target1 = hierarchy1.getTarget(e);
						if (!commonVertices.contains(target1))
							return true;

						return hierarchy2.findEdge(sources1, target1,
								e.getType()) != null;
					}

				});

		System.err.println("(tuned) comparing parent-child relations in \t h1="
				+ hierarchy1 + " vs\t hierarchy2=" + hierarchy2);
		assertEquals("(tuned) parentChildEdges1=" + parentChildEdges1 + "\t"
				+ "parentChildEdges2=" + parentChildEdges2,
				commonEdges1.size(), matches);

	}

	/**
	 * @param hierarchy1
	 * @param hierarchy2
	 */
	protected void assertParentChildConformity(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {

		assertTrue(checkParentChildConformity(fmv1.getFm().getDiagram(), fmv2
				.getFm().getDiagram()));
	}

	/**
	 * @param hierarchy1
	 * @param hierarchy2
	 */
	protected boolean checkParentChildConformity(FeatureModelVariable fmv1,
			FeatureModelVariable fmv2) {
		return checkParentChildConformity(fmv1.getFm().getDiagram(), fmv2
				.getFm().getDiagram());
	}

	/**
	 * @param hierarchy1
	 * @param hierarchy2
	 */
	protected boolean checkParentChildConformity(
			final FeatureGraph<String> hierarchy1,
			final FeatureGraph<String> hierarchy2) {

		final Set<FeatureEdge> parentChildEdges1 = hierarchy1
				.selectEdges(FeatureEdge.HIERARCHY);

		// if (parentChildEdges1.size () != parentChildEdges2.size ())
		// Assert.fail("size of parent-child edges differ");

		int matches = CollectionUtils.countMatches(parentChildEdges1,
				new Predicate<FeatureEdge>() {

					public boolean evaluate(FeatureEdge e) {

						return hierarchy2.findEdge(hierarchy1.getSources(e),
								hierarchy1.getTarget(e), e.getType()) != null;
					}

				});

		// System.err.println("comparing parent-child relations in \t h1=" +
		// hierarchy1 + " vs\t hierarchy2=" + hierarchy2);
		return (parentChildEdges1.size() == matches)
				&& (hierarchy1.vertices().equals(hierarchy2.vertices()));

	}

}
