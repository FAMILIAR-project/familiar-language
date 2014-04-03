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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;

@RunWith(Parameterized.class)
public class FMLComparisonTest extends FMLSlicerUtilityTest {

	protected static final String fmsupp1 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : (T1|T2) ;  )";
	protected static final String fmsupp2 = "FM (MedicalImage : Anonymized MRI [Header] ; MRI : [T1] [T2] ; )";
	protected static final String fmsupp3 = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; T1 -> Anonymized; )";

	protected static final String fmsupp3b = "FM (MedicalImage : [Anonymized] MRI [DICOM] ; MRI : [T1] [T2] ; )";

	protected static final String fmExpectedSupp = "FM (MedicalImage : Anonymized MRI Header DICOM ; MRI : T1 T2 ; )";
	private static final boolean WITH_SAT_FLA_STRATEGY = true ;

	private String _fm1Specification;
	private String _fm2Specification;
	private Comparison _cmpExpected;

	private FeatureModelVariable _fmv1;
	private FeatureModelVariable _fmv2;

	/**
	 * determine if the test is supposed to succeed with SAT (FeatureIDE)
	 */
	private boolean _featureIDE;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		_fmv1 = FM("fm1", _fm1Specification);
		_fmv2 = FM("fm2", _fm2Specification);
	}

	public FMLComparisonTest(String fm1, String fm2, Comparison cmp,
			boolean featureIDE) {
		_fm1Specification = fm1;
		_fm2Specification = fm2;
		_cmpExpected = cmp;
		_featureIDE = featureIDE;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "FM (A : (B|C|D)+; ) ", "FM (A : (B|C|D)+; ) ",
						Comparison.REFACTORING, true },
				{ "FM (A : (B|C|D)+; ) ", "FM (A : B C D ; )",
						Comparison.GENERALIZATION, true },
				{ "FM (A : (B|C|D)+; ) ", "FM (A : B C D E ; )",
						Comparison.ARBITRARY, true },
				{ "FM (A : R ; R : (B|C)+; ) ", "FM (A : R ; R : [B] [C] ; )",
						Comparison.SPECIALIZATION, false }, // fail with
															// FeatureIDE
				{ "FM (A : (B|C)+; ) ", "FM (A : [B] [C] ; )",
						Comparison.SPECIALIZATION, false }, // fail with
															// FeatureIDE
				{ "FM (A : R ; R : (B|C)+; ) ",
						"FM (A : R ; R : [B] [C] [D] ; )",
						Comparison.SPECIALIZATION, true },
				{ "FM (A : R S ; R : (B|C)+; ) ",
						"FM (A : R ; R : [B] [C] [D] ; )",
						Comparison.ARBITRARY, true },
				{ "FM (A : R S ; R : (B|C)+; ) ",
						"FM (A : R [S] ; R : [B] [C] [D] ; )",
						Comparison.SPECIALIZATION, true },
				{ "FM (A : R [S] ; R : (B|C)+; ) ",
						"FM (A : R S ; R : [B] [C] [D] ; )",
						Comparison.ARBITRARY, true },
				{ "FM (A : R [S] ; R : (B|C)+; ) ",
						"FM (A : R S ; R : [B] [C] ; )", Comparison.ARBITRARY,
						false }, // fail with FeatureIDE
				{ "FM (A : R [S] ; R : (B|C)+; ) ",
						"FM (A : R S ; R : (B|C) ; )",
						Comparison.GENERALIZATION, true },
				{ "FM (A : B [C] ; ) ", "FM (A : B ; B : [C] ; )",
						Comparison.REFACTORING, true },
				{ fmsupp1, fmsupp2, Comparison.ARBITRARY, true },
				{ fmsupp2, fmsupp3, Comparison.ARBITRARY, true },
				{ fmsupp1, fmsupp3, Comparison.ARBITRARY, true },
				{ fmsupp1, fmsupp3b, Comparison.SPECIALIZATION, true },
				{ "FM (A : B [C] D ; ) ", "FM (A : B ; B : D [C] ; )",
						Comparison.REFACTORING, true },
				{ "FM (A : B [C] D ; ) ", "FM (A : B ; B : [D] [C] ; )",
						Comparison.SPECIALIZATION, false }, // fail with
															// FeatureIDE
				{ "FM (A : B [C] [D] ; ) ", "FM (A : B ; B : D [C] ; )",
						Comparison.GENERALIZATION, false }, // fail with
															// FeatureIDE
				{ "FM (A : B [C] [D] ; ) ", "FM (E : F G ; G : D [C] ; )",
						Comparison.ARBITRARY, true },
				{ "FM (A : B [C] [D] ; ) ", "FM (E : F G ; G : H [I] ; )",
						Comparison.ARBITRARY, true },
				{ "FM (A : B ; ) ", "FM (B : A ; )", Comparison.REFACTORING,
						true },
				{ "FM (A : [B] [C] [D]; ) ", "FM (A : (B|C|D)+ ; )",
						Comparison.GENERALIZATION, false }, // fail with
															// FeatureIDE
				{ "FM (A : [B] [C] [D]; B | C | D ; ) ",
						"FM (A : (B|C|D)+ ; )", Comparison.REFACTORING, true },
				{ "FM (A : [B] [C] [D]; B | C | D ; ) ",
						"FM (A : (B|C|D)+ ; B -> D; )",
						Comparison.GENERALIZATION, true },
				{ "FM (A : [B] [C] ; B | C ; ) ",
						"FM (A : (B|C|D)+ ; B -> D; )", Comparison.ARBITRARY,
						true },

		});
	}

	@Test
	public void testCompare() throws Exception {

		assertEquals(_cmpExpected, _fmv1.compareBDD(_fmv2, _builder));
		assertEquals(inverse(_cmpExpected), _fmv2.compareBDD(_fmv1, _builder));

		if (_featureIDE) {
			assertEquals(_cmpExpected, _fmv1.compareSAT(_fmv2, true));
			assertEquals(inverse(_cmpExpected),
					_fmv2.compareSAT(_fmv1, true));
		}
		
		_shell.setVerbose(true);
		if (WITH_SAT_FLA_STRATEGY) {
			assertEquals(_cmpExpected, _fmv1.compareSAT_Formula(_fmv2));
			assertEquals(inverse(_cmpExpected),
					_fmv2.compareSAT_Formula(_fmv1));
		}
	}

	private Comparison inverse(Comparison cmp) {
		switch (cmp) {
		case REFACTORING:
			return Comparison.REFACTORING;
		case SPECIALIZATION:
			return Comparison.GENERALIZATION;
		case GENERALIZATION:
			return Comparison.SPECIALIZATION;
		case ARBITRARY:
			return Comparison.ARBITRARY;
		default:
			return null ;
		}

	}

}
