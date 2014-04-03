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

package fr.familiar.test.featureide;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDDException;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.fm.FMComparisonReport;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;

/**
 * @author macher1
 *
 */


public class FMLKSyntehsisingSPLOTTest extends FMLSPLOTTest {
	
	private Set<String> differentFMs = new HashSet<String>() ;
	
	
	// time-consuming test
	@Ignore
	@Test
	public void testSPLOTReader() throws Exception {
		
		
		List<FeatureModelVariable> fms = collectSPLOTFMs() ;  
		
		for (FeatureModelVariable fm : fms) {
			try {
			assertKSynthesisIdentity(fm);
			freeBDDBuilder(_builder);
			}
			catch (BDDException e) {
				System.err.println("e=" + e.getLocalizedMessage());
				e.printStackTrace();
				return ; 
			}
		}
		
		System.err.println("\n\n===== different FMs======\n\n");
		for (String differentFM : differentFMs) {
			System.err.println(differentFM);
		}
		
		
	}
	
	@Ignore
	@Test
	public void testBIG() throws Exception {
		
		
		List<FeatureModelVariable> fms = collectSPLOTFMs() ;  
		
		
		for (FeatureModelVariable fm : fms) {
			/*
			System.err.println(fm.getIdentifier() + " = ");
			System.err.println(fm);*/
			FeatureModelVariableSATFormula satFla = new FeatureModelVariableSATFormula("", new SATFMLFormula(fm)) ;
			ImplicationGraph<String> big = satFla.computeImplicationGraph() ;
			String viz = big.toString() ;			
			FileSerializer.write("output/SPLOT-big/" + fm.getIdentifier() + ".dot", viz);
		
		}
			
	}
	
	
	


	private void freeBDDBuilder(BDDBuilder<String> builder) {
		builder.reset() ; 
		//builder.getFactory().reset();
		//builder.getFeatureMap().clear();
		
	}


	@Test
	public void testMisc() throws Exception {
			
		/*
		assertKSynthesisIdentity(mkFMVariableByFileName("cfdp_library_fm.xml")) ; // OK
		assertKSynthesisIdentity(mkFMVariableByFileName("car_fm.xml")) ; // OK
		assertKSynthesisIdentity(mkFMVariableByFileName("connector_fm.xml")); // OK
		assertKSynthesisIdentity(mkFMVariableByFileName("model_20091109_376438407.xml")); // (TP | Watercraft)
		assertKSynthesisIdentity(mkFMVariableByFileName("model_20110925_62365838.xml")) ; 
		assertKSynthesisIdentity(mkFMVariableByFileName("model_20120328_361613983.xml"));	*/
		
		// constraints and knowledge needed (discussable)
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100213_206550190.xml")); // order matters
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100308_1032655961.xml")); // first excludes
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110318_225131257.xml")); // biimplies / implies (knowledge needed)
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110401_868452735.xml")); // knowledge needed: set of implies are replaced by (arcondicionado | OneZeroZero); (OneTwoZero | Four);
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110525_1619420630.xml")); // knowledge needed
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110919_127780100.xml")); // knowledge needed (arbitrary)
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20111027_668940289.xml")); KST needed (other set of implies)
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20111029_1654942500.xml")); // KST needed 'arbitrary
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20111220_1184087779.xml")); // KST needed arbitrary
		// model_20120110_1754443954.xml // KST needed arbitrary
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20120113_1803352101.xml")); // different set (discussable)
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20120113_1950870026.xml")); // different set (discussable)
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20120201_1444061231.xml")); // different set (discussable)
		// model_20120201_865979127.xml // discussable
		// model_20120201_899753062.xml // discussable
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120202_114979109.xml")); // discussable
		// model_20120328_573707444.xml // discussable
		// 20120328_663906927 // discussable
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120522_87989914.xml"));// arbirarty
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20120627_1512389149.xml")); // order matters
		
		// actually no problem
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100326_770562654.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100415_240595643.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100415_947132043.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100624_2076952989.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20110406_1047532859.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20110915_1159959623.xml"));
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20111201_1252018702.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120109_1808102333.xml")); // biimp ~ imp
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120110_1094246588.xml")); // exclude in the other side
		// model_20120110_1256867454.xml
		// model_20120110_139114401.xml
		// model_20120110_1719396361.xml
		// model_20120110_855603964.xml
		// model_20120122_258977494.xml
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120328_1357539597.xml"));
		// model_20120411_196667302.xml
		// model_20120515_1862354569.xml
		// false positives
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20101110_1749898936.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20101111_1790887308.xml"));
		// model_20110527_1847306763.xml (biimplies)
		
		
		
		
		// conflicts
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20120419_1397354511.xml"));
		
		
		
		// we simplify
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100730_1366577700.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20101117_2128796258.xml")); // + false positives (biimpl)
		// model_20110116_381192414.xml
		// model_20110323_789959080.xml // false optional
		// model_20110328_1177934440.xml // constraints
		// model_20110407_1283128166.xml // removal of an imply constraint
		// model_20110428_553405269.xml // removal of lots of redundant constraints
		// _model_20110330_919429247.xml // false optional
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20110214_751339643.xml")); // XOR
		// OR groups
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100719_944437142.xml"));
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20110329_10104623.xml"));
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110507_674768061.xml")); // false optional
		// _model_20110516_1331478109.xml // false optional / OR groups / etc.
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20110525_564104612.xml")); redundant constraints
		// model_20110704_328391695.xml // false optionals, Or-groups
		// model_20110826_1574631601.xml 
		// model_20110925_62365838.xml (false optional)
		// model_20110926_400852996.xml (false optional)
		// model_20110926_608554224.xml (false OR-group)
		// model_20120111_1667229430.xml (false optional)
		// model_20120202_1596034358.xml (redundant constraint)
		// model_20120205_24117969.xml (false optional)
		// model_20120328_1227899142.xml (false optional)
		// model_20120328_1373483522.xml (false optional)
		// model_20120328_1667195252.xml (false optional)
		// model_20120328_288933955.xml (false optional)
		// model_20120328_361613983.xml (OR-groups forgotten)
		// assertKSynthesisIdentity(mkFMVariableByFileName("model_20120529_2101702978.xml")); // false optionals
		// _model_20120722_2065280700.xml // OR groups not identified
		
		// recheck
		// model_20110124_2116036381.xml (corrective)
		// model_20110207_906076676.xml (corrective)
		// model_20110301_216655728.xml (corrective)
		
		// MTX
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100805_1484639410.xml"));
		// model_20101119_1472596180.xml
		// model_20110601_1103941862.xml
		
		
		// TODO
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100822_281357717.xml"));
		// model_20101120_2091447559.xml
		// model_20101123_920943759.xml // seems error
		// model_20101124_661702924.xml
		// model_20110207_1623721536.xml

		
		
		//assertKSynthesisIdentity(mkFMVariableByFileName("model_20100719_944437142.xml"));
		
		
		
		
		
		
		
		
	}
	
	
	private FeatureModelVariable mkFMVariableByFileName(String fileName) {
		FeatureModelVariable fm = (FeatureModelVariable) _shell.parse("fm_" + fileName + "" + " = " + "FM (" + "\"" +  fileName + "\"" + ")");
		return fm ; 
	}


	private void assertKSynthesisIdentity(FeatureModelVariable fm) throws Exception {
		
		System.err.println("Synthesising ===> " + fm.getIdentifier() + " = " + fm);
		System.err.println("-------");
		
		KSynthesisBDD ksynt = new KSynthesisBDD(fm.getFormula(), _builder);
		KnowledgeSynthesis kn = new KnowledgeSynthesis() ;
		kn.setHierarchy(fm.getHierarchy().getDiagram());
		ksynt.setKST(kn);
		
		
		
		//_shell.setVerbose(true);
		FeatureModelVariable fmBis ;
		try {
			fmBis = ksynt.build() ; 
		}
		catch (NullPointerException e) { // may be caused by FeatureIDE conversion
			return ; 
		}
		
		System.err.print("conflicts ====>"); 
		
		boolean conflicts = ksynt.hasConflictingChoices() ;
		System.err.println("" + conflicts);
		System.err.println(ksynt.getConflictReport()); 
		
		System.err.println("\nfmBis = " + fmBis);
		
		// only the FD part
		FeatureModelVariable fmBiBis = new FeatureModelVariable("", fmBis.getFm());
		
		String outputFileName = "output/" + "splot-models-2012-08-09synthesized/" + fm.getIdentifier() + ".fml"; 
		//FileSerializer.write(outputFileName, fmBiBis.convert(FMFormat.FFML));
		Comparison cmp = fmBiBis.compareBDD(fm, _builder) ; 
		if (cmp != Comparison.REFACTORING) {
			System.err.println("********* need arbitrary cross-tree constraints ************"); // \phi can be the reason
		}
		//assertEquals(Comparison.REFACTORING, cmp);
		
		System.err.println("\nComparison report: " + fm.getIdentifier() + "\n");
		boolean b1 = new FMComparisonReport (fmBis, fm).perform();
		 
		if (!b1) {
			differentFMs.add(fm.getIdentifier());
			System.err.println("\n** not equals **");
			
		}
		
		System.err.println("\n\n\n");
		
		
		fm.free() ;
		fmBis.free(); 
		fmBiBis.free() ;
		
	}


	
	

	@Override
	@After
	public void tearDown() {


	}

}
