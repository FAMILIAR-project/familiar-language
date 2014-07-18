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

package fr.familiar.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.parser.HierarchyMergerFactory;
import fr.familiar.parser.HierarchyMergerStrategy;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureModel;

@RunWith(Parameterized.class)
public class FMLMergeHierarchyTest extends FMLTest {

	protected static final String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
	protected static final String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
	protected static final String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

	protected static final String fmsupp3b = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; )";

	protected static final String fmExpectedSupp = "FM (MedicalImage : Anonymized MRI Header DICOM ; MRI : T1 T2 ; )";

	private static final int N_TIMES = 10;

	private static int i = 0;

	/**
	 * List of feature models specification
	 */
	protected List<String> _lfms = new ArrayList<String>();

	/**
	 * List of feature models variable (once parsed in FML)
	 */
	protected List<FeatureModelVariable> _lfmvs = new ArrayList<FeatureModelVariable>();

	protected List<FeatureModelVariable> _fmvPossibleHierarchies = new ArrayList<FeatureModelVariable>();

	/**
	 * possible hierarchy expected of the resulting, merged FM (more than one is
	 * possible)
	 */
	protected List<String> _possibleHierarchies;

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

	public FMLMergeHierarchyTest(List<String> lfms,
			List<String> possibleHierarchies) throws Exception {
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
								Arrays.asList(new String[] { fmExpectedSupp }) }, });
	}

	@Test
	public void repeat() throws Exception {
		for (int i = 0; i < N_TIMES; i++)
			testMergeHierarchy1();
	}

	public void testMergeHierarchy1() throws Exception {

		System.err.println("======== test(" + i++ + ")======");
		SetVariable sfmvs = getSetVariable("fm*");
		Set<Variable> svars = sfmvs.getVars();
		List<FeatureModelVariable> fmvs = new ArrayList<FeatureModelVariable>();
		for (Variable var : svars) {
			assertTrue(var instanceof FeatureModelVariable);
			FeatureModelVariable fmv = (FeatureModelVariable) var;
			fmvs.add(fmv);
		}
		
		
		FeatureModel<String> fmMergedHierarchy = HierarchyMergerFactory.mkMerger(HierarchyMergerStrategy.MST, null, null).build(fmvs);
		assertNotNull(fmMergedHierarchy);

		boolean foundOne = false;
		for (FeatureModelVariable fmvHierarchyExpected : _fmvPossibleHierarchies) {
			if (checkParentChildConformity(fmvHierarchyExpected,
					new FeatureModelVariable("", fmMergedHierarchy))) {
				foundOne = true;
				break;
			}
		}
		assertTrue(foundOne);
		System.err.println("========\n");

	}

}
