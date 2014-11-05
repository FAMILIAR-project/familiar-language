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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.sat4j.specs.ISolver;
import org.sat4j.tools.ModelIterator;
import org.xtext.example.mydsl.fML.SliceMode;

import splar.core.fm.reasoning.FMReasoningException;
import fr.familiar.FMLSlicerUtilityTest;
import fr.familiar.fm.FMLUtils;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.operations.Mode;
import fr.familiar.operations.SATMerger;
import fr.familiar.operations.SATfMLMerger;
import fr.familiar.operations.SATsPLOTMerger;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.parser.FMLMergerWithConstraints;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Formula;

@RunWith(Parameterized.class)
public class FMLMergeSliceTest extends FMLSlicerUtilityTest {

	protected static final String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
	protected static final String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
	protected static final String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

	protected static final String fmsupp3b = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; )";

	protected static final String fmExpectedSupp = "FM (MedicalImage : Anonymized MRI Header DICOM ; MRI : T1 T2 ; )";

	protected List<FeatureModelVariable> _fmvPossibleHierarchies = new ArrayList<FeatureModelVariable>();

	/**
	 * possible hierarchy expected of the resulting, merged FM (more than one is
	 * possible)
	 */
	protected List<String> _possibleHierarchies;

	/**
	 * List of feature models specification
	 */
	protected List<String> _lfms = new ArrayList<String>();

	/**
	 * List of feature models variable (once parsed in FML)
	 */
	protected List<FeatureModelVariable> _lfmvs = new ArrayList<FeatureModelVariable>();

	protected static int t = 0;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		_shell.setCountingStrategy(CountingStrategy.BDD_SPLOT); // TODO: should work
															// well with FML or
															// SAT

		int i = 0;
		for (String fm : _lfms) {
			String fmIdentifier = "fm" + i++;
			_shell.parse(fmIdentifier + " = " + fm + " \n");
			_lfmvs.add(getFMVariable(fmIdentifier));
		}

		int j = 0;
		String fmHierarchyID = "hierarchyExpected";
		for (String possibleHierarchy : _possibleHierarchies)
			_fmvPossibleHierarchies.add(FM(fmHierarchyID + "" + j++,
					possibleHierarchy));

	}

	public FMLMergeSliceTest(List<String> lfms, List<String> possibleHierarchies)
			throws Exception {
		_lfms = lfms;
		_possibleHierarchies = possibleHierarchies;
	}

	@Parameters
	public static Collection<Object[]> data() {

		return Arrays
				.asList(new Object[][] {
						{
								Arrays.asList(new String[] {
										"FM (A : (B|C|D)+; ) ",
										"FM (A : (B|D|E) ; )",
										"FM (A : (B|D)+; )",
										"FM (A : (B|D|E|C) ; )",
										"FM (A : [B] [D] [E] ; )" }),
								Arrays.asList(new String[] { "FM (A : B C D E ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : (B|C); )", "FM (A : (B|C); )",
										"FM (A : [B] [C]; B | C ; !B | !C ; )" }),
								Arrays.asList(new String[] { "FM (A : B C ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : (B|C); )", "FM (A : (B|C); )",
										"FM (A : [B] [C]; B | C ; )" }),
								Arrays.asList(new String[] { "FM (A : B C ; )" }) },

						// spanning tree is not unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B [C] ;)\n",
										"FM (A : B ; B : [C]; )\n" }),
								Arrays.asList(new String[] { "FM (A : B C ; )",
										"FM (A : B ; B : C ; )" }) },

						// spanning tree is not unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n" }),
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : F ; )",
										"FM (A : B ; B : C; C : E; E : F ; )" }) },

						// spanning tree is unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },
						// spanning tree is unique here
						{
								Arrays.asList(new String[] {
										"FM (A : B C E ; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n",
										"FM (A : B ; B : C; C : E; E : F ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : B ; B : C; C : E; E : [F] ; )\n",
										"FM (A : B ; B : C; C : E; E : [F] ; )\n" }),
								Arrays.asList(new String[] { "FM (A : B ; B : C; C : E; E : F ; )" }) },

						{
								Arrays.asList(new String[] {
										"FM (A : B C [D]; D : (E|F|G); C : (I|J)+; )",
										"FM (A : B C [D]; D : (E|F|G)+; C : (I|J) ; )\n",
										"FM (A : B C [D]; D : (E|F|G)+; C : (I|J)+ ; )\n", }),
								Arrays.asList(new String[] { "FM (A : B C D; D : E F G; C : I J ; )" }) },

						{
								Arrays.asList(new String[] { fmsupp1, fmsupp2,
										fmsupp3b }),
								Arrays.asList(new String[] { fmExpectedSupp }) },

						{
								Arrays.asList(new String[] { fmsupp1, fmsupp2,
										fmsupp3 }),
								Arrays.asList(new String[] { fmExpectedSupp }) },

						{
								Arrays.asList(new String[] { "FM (A : B; ) ",
										"FM (A : C ; )", "FM (A : D ; )", }),
								Arrays.asList(new String[] { "FM (A : B C D ; )" }) },

				});
	}

	// @see FMLMergeSliceRepeatTest
	public void testMerge1() throws Exception {

		// expected merged FM
		
		//_shell.setCountingStrategy(CountingStrategy.BDD_FML);

		_shell.parse("expectedFM = merge sunion fm*");
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");

		System.err.println("expectedFM=" + expectedFM);
		double countExpected = expectedFM.counting();
		System.err.println("#expectedFM=" + countExpected);

		FeatureModelVariable fmvMerged = new FMLMergerWithConstraints(_lfmvs).union() ;

		System.err.println("#fmMerged=" + fmvMerged.counting());

		// FIXME
		// S2T2Converter converter = new S2T2Converter(true);
		// FileSerializer.write("output/fmCstMerged" + t++ + ".fmprimitives",
		// converter.fmlToS2T2XMI(fmvMerged));

		Set<Variable> configs = fmvMerged.configs();
		System.err.println("[fmMerged]=" + FMLUtils.setConfigToSetStr(configs));

		Set<String> ftsToSlice = new HashSet<String>();
		for (FeatureModelVariable featureModelVariable : _lfmvs) {
			ftsToSlice.addAll(featureModelVariable.getFm().features());
		}

		Set<String> allFts = fmvMerged.getFm().features();
		allFts.removeAll(ftsToSlice);

		System.err.println("ftsToSlice=" + allFts);

		Formula<String> flaSPLOTSliced = runSlicingSPLOT(fmvMerged, allFts,
				SliceMode.EXCLUDING); // runSlicing should work as well
		Formula<String> flaSliced = runSlicing(fmvMerged, allFts,
				SliceMode.EXCLUDING);
		// double countSliced = countingFormula(flaSliced) ;
		// System.err.println("#flaSliced=" + countSliced);
		// System.err.println("\t\t" + new
		// AllConfigsBDD(_builder).process(flaSliced));
		// assertEquals(countExpected, countSliced, 0);
		Formula<String> expectedFla = expectedFM.getFormula();
		assertFormulaEquals(expectedFla, flaSliced);
		assertFormulaEquals(expectedFla, flaSPLOTSliced);
		assertFormulaEquals(flaSliced, flaSPLOTSliced);

		FeatureModelVariable slicedFmv = runSliceFMV(fmvMerged, allFts,
				SliceMode.EXCLUDING, "");
		System.err.println("diff -- sliced / expected: "
				+ slicedFmv.diffFormula(expectedFM, _builder));
		assertSliceBasedFormulaEquals(expectedFla, slicedFmv.getFormula());
		// stats(slicedFmv);

		// strong property!
		// FIXME (this may fail since the "winning" hierarchy is currently the
		// first FM to be merged)
		// as an "hashset" is used, the first FM may change (hashCode of the
		// object is used) and this is quite random
		// we have to fix that by adopting a more ambitious strategy (like the
		// most "common" tree between input FMs)
		// see maximum common subgraph
		// assertParentChildConformity(expectedFM, slicedFmv);

		// seems to be fixed!
		boolean foundOne = false;
		for (FeatureModelVariable fmvHierarchyExpected : _fmvPossibleHierarchies) {
			if (checkParentChildConformity(fmvHierarchyExpected, slicedFmv)) {
				foundOne = true;
				break;
			}
		}
		assertTrue(foundOne);

		// assertHierarchyEquals(expectedFM, slicedFmv);

	}

	public void testNode4JSATMerge() throws Exception {

		_shell.parse("expectedFM = merge intersection fm*");
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");

		SetVariable fmvs = getSetVariable("fm*");
		Set<Variable> vars = fmvs.getVars();
		List<FeatureModelVariable> lfmvs = new ArrayList<FeatureModelVariable>();
		for (Variable var : vars) {
			assertTrue(var instanceof FeatureModelVariable);
			FeatureModelVariable fmv = (FeatureModelVariable) var;
			lfmvs.add(fmv);
		}
		_shell.setVerbose(true);
		SATMerger satMerger = new SATfMLMerger();
		assertTrue(satMerger.mergeFM(lfmvs, Mode.Intersection));
		ISolver solver = satMerger.getSolver();
		// Map<Integer, Object> intToVar = satMerger.getIntToVar();
		assertNotNull(solver);

		// List<List<String>> satSolutions =
		// AllConfigsSolver.getSolutions(solver, intToVar);
		// System.err.println("configs=" + satSolutions);
		// assertEquals(expectedFM.counting(), satSolutions.size(), 0);

		double satCounting = countSolutions(solver); // countSolutions(expectedFM.getSPLOTSATReasoner().getSolver());
														// //

		assertEquals(expectedFM.counting(), satCounting, 0);

	}

	// Issue with SAT4J
	@Ignore
	@Test
	public void testSPLOTSATMerge() throws Exception {

		_shell.parse("expectedFM = merge intersection fm*");
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");

		SetVariable fmvs = getSetVariable("fm*");
		Set<Variable> vars = fmvs.getVars();
		List<FeatureModelVariable> lfmvs = new ArrayList<FeatureModelVariable>();
		for (Variable var : vars) {
			assertTrue(var instanceof FeatureModelVariable);
			FeatureModelVariable fmv = (FeatureModelVariable) var;
			lfmvs.add(fmv);
		}
		_shell.setVerbose(true);
		SATMerger satMerger = new SATsPLOTMerger();
		assertTrue(satMerger.mergeFM(lfmvs, Mode.Intersection));
		ISolver solver = satMerger.getSolver();
		// Map<Integer, Object> intToVar = satMerger.getIntToVar();
		assertNotNull(solver);

		// List<List<String>> satSolutions =
		// AllConfigsSolver.getSolutions(solver, intToVar);
		// System.err.println("configs=" + satSolutions);
		// assertEquals(expectedFM.counting(), satSolutions.size(), 0);

		double satCounting = countSolutions(solver); // countSolutions(expectedFM.getSPLOTSATReasoner().getSolver());
														// //

		// assertEquals(satCounting, satSolutions.size(), 0);

		assertEquals(expectedFM.counting(), satCounting, 0);

	}

	@Ignore
	@Test
	public void testTunedEncodingForSATMerge() throws Exception {

		/*
		 * disjunction is the problem phi(result) = (phi(fm1) ^ not (fm2.* --
		 * fm1.*)) v (phi(fm2) ^ not (fm1.* -- fm2.*)) phi(result) is not in CNF
		 * convertion of phi(result) into CNF is costly
		 * 
		 * (1) first possible solution: let phi(fm1) be encoded as a CNF with
		 * R11, R12, ..., R1n where each R1i is a disjunction of literals let
		 * phi(fm2) be encoded as a CNF with R21, R22, ..., R2m where each R2i
		 * is a disjunction of literals #phi(fm1) = n (number of disjunction
		 * clauses in phi(fm1)) #phi(fm2) = m so basically we have (we do not
		 * consider negated features at the moment) phi(result_s) = phi(fm1) v
		 * phi(fm2) phi(result_s) = (R11 ^ R12 ^ ... ^ R1n) v (R21 ^ R22 ^ ... ^
		 * R2m) distribute ANDs over ORs phi(result_s) = (R11 v R21) ^ (R11 v
		 * R22) ^ ... ^ (R11 v R2m) ^ (R12 v R21) ^ (R12 v R22) ^ ... ^ (R12 v
		 * R2m) ^ ... ^ (R1n v R21) ^ (R1n v R22) ^ ... ^ (R1n v R2m) Since R1n
		 * and R21 are disjunctive clauses, then we are done?! #phi(result_s) =
		 * n * m
		 * 
		 * Now let us go back to phi(result)
		 * 
		 * Let phi(fm21) (resp. phi(fm12)) be the encoding of (not (fm2.* --
		 * fm1.*)) (resp. (not (fm2.* -- fm1.*))) in CNF phi(fm12) looks like
		 * !ft11 ^ !ft12 ^ ... ^ !ft1i with i the size of fm1.* -- fm2.*
		 * phi(fm21) looks like !ft21 ^ !ft22 ^ ... ^ !ft2j with j the size of
		 * fm2.* -- fm1.*
		 * 
		 * 
		 * phi(result) = (phi(fm1) ^ not (fm2.* -- fm1.*)) v (phi(fm2) ^ not
		 * (fm1.* -- fm2.*)) phi(result) = (R11 ^ R12 ^ ... ^ R1n ^ phi(fm21)) v
		 * (R21 ^ R22 ^ ... ^ R2m ^ phi(fm12)) distribute ANDs over ORs
		 * phi(result) = (R11 v R21) ^ (R11 v R22) ^ ... ^ (R11 v R2m) ^ (R11 v
		 * phi(fm12)) ^ (R12 v R21) ^ (R12 v R22) ^ ... ^ (R12 v R2m ^ (R12 v
		 * phi(fm12)) ^ ... ^ (R1n v R21) ^ (R1n v R22) ^ ... ^ (R1n v R2m) ^
		 * (R1n v phi(fm12)) ^ (phi(fm21) v R21) ^ (phi(fm21) v R22) ^ ... ^
		 * (phi(fm21) v R2m) ^ (phi(fm21) v phi(fm12))
		 * 
		 * We can rewrite as: phi(result) = phi(result_s) ^ (R11 v phi(fm12)) ^
		 * (R12 v phi(fm12)) ^ ... ^ (R1n v phi(fm12)) ^ (phi(fm21) v R21) ^
		 * (phi(fm21) v R22) ^ ... ^ (phi(fm21) v R2m) ^ (phi(fm21) v phi(fm12))
		 * 
		 * phi(result) = phi(result_s) ^ [r1] (R11 v (!ft11 ^ !ft12 ... ^
		 * !ft1i)) ^ (R12 v (!ft11 ^ !ft12 ... ^ !ft1i)) ^ ... ^ (R1n v (!ft11 ^
		 * !ft12 ... ^ !ft1i)) ^ [r2] ((!ft21 ^ !ft22 ^ ... ^ !ft2j) v R21) ^
		 * ((!ft21 ^ !ft22 ^ ... ^ !ft2j) v R22) ^ ... ^ ((!ft21 ^ !ft22 ^ ... ^
		 * !ft2j) v R2m) ^ [rn] (phi(fm21) v phi(fm12))
		 * 
		 * phi(result) = phi(result_s) ^ phi(r1) ^ phi(r2) ^ phi(rn)
		 * 
		 * where phi(rn) = phi(fm21) v phi(fm12) = (!ft21 ^ !ft22 ^ ... ^ !ft2j)
		 * v (!ft11 ^ !ft12 ^ ... ^ !ft1i) // distribute ANDs over ORs so:
		 * #phi(rn) = i * j #phi(r1) = n * i #phi(r2) = n * j so #phi(result) =
		 * (n * m) + (n * i) + (n * j) + (i * j)
		 * 
		 * so the key is clearly n, m, i and j From \cite{batory2009}, we
		 * learned that "if there are n features in a feature model, a crude
		 * upper bound on the worst case number of clauses is O(n2) and the
		 * number of disjuncts in a clause is O(n) In practice, the number of
		 * clauses and the average number of disjuncts per clause is much lower
		 * than these bounds." hence n and m depends on the number of features
		 * in input FMs - n <= size fm1.* - m <= size fm2.
		 */

		_shell.parse("expectedFM = merge sunion fm*");
		FeatureModelVariable expectedFM = getFMVariable("expectedFM");

		SetVariable fmvs = getSetVariable("fm*");
		Set<Variable> vars = fmvs.getVars();
		List<FeatureModelVariable> lfmvs = new ArrayList<FeatureModelVariable>();
		for (Variable var : vars) {
			assertTrue(var instanceof FeatureModelVariable);
			FeatureModelVariable fmv = (FeatureModelVariable) var;
			lfmvs.add(fmv);
		}
		_shell.setVerbose(true);
		SATMerger satMerger = new SATsPLOTMerger();
		assertTrue(satMerger.mergeFM(lfmvs, Mode.Intersection));
		ISolver solver = satMerger.getSolver();
		// Map<Integer, Object> intToVar = satMerger.getIntToVar();
		assertNotNull(solver);

		// List<List<String>> satSolutions =
		// AllConfigsSolver.getSolutions(solver, intToVar);
		// System.err.println("configs=" + satSolutions);
		// assertEquals(expectedFM.counting(), satSolutions.size(), 0);

		double satCounting = countSolutions(solver); // countSolutions(expectedFM.getSPLOTSATReasoner().getSolver());
														// //

		// assertEquals(satCounting, satSolutions.size(), 0);

		assertEquals(expectedFM.counting(), satCounting, 0);

	}

	private double countSolutions(ISolver solver) throws FMReasoningException {
		ISolver lsolver = new ModelIterator(solver);
		double countSat = 0;
		long start = System.nanoTime();
		try {
			while (lsolver.isSatisfiable()) {
				lsolver.model();
				countSat++;
				if ((System.nanoTime() - start) / 1E6 > SATFormula.SAT_TIMEOUT)
					return -1;
			}
		} catch (Exception e) {
			throw new FMReasoningException(e);
		}
		return countSat;

	}

}
