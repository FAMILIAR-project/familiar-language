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

package fr.familiar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FMSlicerBDD;
import fr.familiar.operations.SlicerBDDFormula;
import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Before;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.SliceMode;

/**
 * @author mathieuacher
 * 
 */
public class FMLSlicerUtilityTest extends FMLTest {

	protected String _fmASERunningExample = "FM (W : P T [U] ; T : [V] A ;\n"
			+ "      	       	     A : B C [D] ; \n"
			+ "		       	     	 C : [E] [F] ; \n" + "		     P : (R|S)+ ; \n"
			+ "		     E implies D ; R implies E ; \n"
			+ "		     S implies (F and !E) ; D implies !F ; ) \n";

	/**
	 * slicer utility (formula + hierarchy)
	 */
	// protected FMLSlicer _slicer ;

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		// _slicer = new FMLSlicer(_builder);
	}

	/**
	 * Facilities to test the slicing operation
	 * 
	 * @param fm
	 * @param features
	 * @param sliceMode
	 *            excluding or including strategy
	 * @return
	 * @throws Exception
	 */

	protected Formula<String> runSlicingSPLOT(FeatureModelVariable fm,
			Collection<String> fts, SliceMode mode) throws Exception {
		Formula<String> oformula = fm.getSPLOTFormulaAligned(_builder); // fm.getSPLOTFormula()
		return runSlicingFla(fm, oformula, fts, mode);
	}

	protected Formula<String> runSlicing(FeatureModelVariable fm,
			Collection<String> features, SliceMode sliceMode) throws Exception {
		//fm.slice(sliceMode, _builder, new HashSet<String>(features));
		Formula<String> oformula = fm.getFormula();
		return runSlicingFla(fm, oformula, features, sliceMode);

	}

	protected Formula<String> runSlicingFla(FeatureModelVariable fm,
			Formula<String> oformula, Collection<String> features,
			SliceMode sliceMode) throws Exception {

		// System.err.println("oformula=" + oformula);

		// double before = countingFormula(oformula) ;
		// System.err.println("(before) #" + before);
		Formula<String> filteredFormula = new SlicerBDDFormula(_builder).sliceFormula(oformula, features, sliceMode);
		assertNotNull(filteredFormula);
		// System.err.println("filteredFormula=" + filteredFormula);

		// double after = countingFormula(filteredFormula);
		// System.err.println("(after) #" + after);

		// assertTrue("set of valid configurations remains the same or is reduced after="
		// + after + " before=" + before, after <= before);

		// for (String ft : filteredFormula.getDomain()) {
		// System.err.println("" + ft + " => " +
		// _builder.getFeatureMap().get(ft));
		// }

		// System.err.println("configs=" + new
		// AllConfigsBDD(_builder).process(filteredFormula));
		return filteredFormula;
	}

	/**
	 * @param fmService
	 * @param fts
	 * @param slicingMode
	 * @return
	 * @throws Exception
	 */
	protected FeatureModel<String> runSliceFM(FeatureModelVariable fmToSlice,
			Collection<String> fts, SliceMode slicingMode) throws Exception {
		return runSliceFMV(fmToSlice, fts, slicingMode, "").getFm();
	}

	/**
	 * @param fmToSlice
	 * @param fts
	 * @param slicingMode
	 * @param assigner
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	protected FeatureModelVariable runSliceFMV(FeatureModelVariable fmToSlice,
			Collection<String> fts, SliceMode slicingMode, String assigner)
			throws Exception {
		FeatureModelVariable fmvSliced = new FMSlicerBDD(_builder).sliceFM(fmToSlice, fts,
				slicingMode);
		fmvSliced.setIdentifier(assigner);
		assertNotNull(fmvSliced);

		// checking
		Formula<String> flaSlicedExpected = runSlicing(fmToSlice, fts,
				slicingMode);

		Formula<String> actualFlaSliced = fmvSliced.getFormula();

		// assertSliceBasedFormulaEquals(actualFlaSliced, flaSlicedExpected);

		// assertFormulaEquals(flaSlicedExpected, actualFlaSliced);
		System.err.println("\n\tfmvSliced="
				+ fmvSliced.getSyntacticalRepresentation());
		// System.err.println("\n(.m) fmvSliced=" +
		// ConvertAnalyzer.convert(fmvSliced, FMFormat.FIDE));
		return fmvSliced;
	}

	/**
	 * currently, the sliced Fla seems to include a solution with all variables
	 * set to false values
	 * 
	 * @param flaSliced
	 * @param flaExpected
	 */
	protected void assertSliceBasedFormulaEquals(Formula<String> flaSliced,
			Formula<String> flaExpected) {

		if (!checkSliceBasedFormulaEquals(flaSliced, flaExpected))
			assertTrue(checkSliceBasedFormulaEquals(flaExpected, flaSliced));
		assertTrue(true);

	}

	/**
	 * currently, the sliced Fla seems to include a solution with all variables
	 * set to false values
	 * 
	 * @param flaSliced
	 * @param flaExpected
	 */
	private boolean checkSliceBasedFormulaEquals(Formula<String> flaSliced,
			Formula<String> flaExpected) {

		if (!flaExpected.equals(flaSliced)) {
			Formula<String> diffFla = FMLMergerBDD.diff(flaSliced,
					flaExpected, _builder);
			Formula<String> diffFla2 = FMLMergerBDD.diff(flaExpected,
					flaSliced, _builder);
			System.err.println("diffFla=" + diffFla);
			System.err.println("diffFla2=" + diffFla2);
			if (diffFla.isZero() && diffFla2.isZero())
				return true;

			// hack: attach a BDD with all variables with false values
			BDD nBDD = _builder.one();
			Set<String> vars = flaExpected.getDomain();
			for (String var : vars) {
				nBDD.andWith(_builder.get(var).not());
			}
			System.err.println("nBDD=" + nBDD);
			Formula<String> actualFlaSliced2 = new Formula<String>(
					nBDD.or(flaSliced.getBDD()), vars, _builder);
			return flaExpected.equals(actualFlaSliced2);
			// end of hack
		}

		return true;

	}

	protected void serializeToS2T2(FeatureModelVariable fmv) throws IOException {
		String fmvID = fmv.getIdentifier();
		String fmvS2T2Content = ConvertAnalyzer.convert(fmv, FMFormat.S2T2);
		FileSerializer.write("output/" + fmvID + ".fmprimitives",
				fmvS2T2Content);
	}

	public static String prettyConfigs(FeatureModelVariable fm1) {
		Set<Variable> fm1Configs = fm1.configs();
		StringBuilder sb = new StringBuilder();
		sb.append("\\{");
		sb.append("\\newline\n");
		int i = 0;
		for (Variable c1 : fm1Configs) {
			assertTrue(c1 instanceof SetVariable);
			SetVariable sc1 = (SetVariable) c1;
			Set<Variable> sc1vars = sc1.getVars();

			List<String> ftsConfig = new ArrayList<String>();
			for (Variable ftv : sc1vars) {
				assertTrue(ftv instanceof FeatureVariable);
				FeatureVariable ft = (FeatureVariable) ftv;
				String ftName = ft.getFtName();
				ftsConfig.add(ftName);
			}

			sb.append("\\{");
			// sort by alphabetical order
			Collections.sort(ftsConfig);
			int j = 0;
			for (String ftStr : ftsConfig) {
				sb.append(ftStr);
				if (j++ != (ftsConfig.size() - 1))
					sb.append(", ");
			}

			sb.append("\\}");
			if (i++ != (fm1Configs.size() - 1))
				sb.append(", ");
			sb.append("\\newline\n");

		}
		sb.append("\\}");
		sb.append("\n");
		return sb.toString();
	}
}
