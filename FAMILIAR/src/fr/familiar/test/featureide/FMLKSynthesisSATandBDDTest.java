package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.fm.FMComparisonReport;
import fr.familiar.operations.FMLMergerBDD;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.KSynthesis;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.operations.featureide.KSynthesisSAT;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;

/**
 * @author mathieuacher
 * 
 */
@RunWith(value = Parameterized.class)
public class FMLKSynthesisSATandBDDTest extends FMLTest {

	protected String _fm;

	public FMLKSynthesisSATandBDDTest (String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B [C] [E] ; E : (F|G); )" },
				{ "FM (A: B [C] ; )" },
				{ "FM (A: B [C] [E] ;)" },
				{ "FM (A: B C ; )" },
				{ "FM (A: (D|E|F) ; )" },
				{ "FM (A: B C ; C : E [F]; F : [G]; G : H I [J]; )" },
				{ "FM (A: B C [D] ; (D <-> C); )" },
				{ "FM (A: B C [E] ; (E -> C); )" }, // anomalies baby
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ;)\n" },
				{ "FM (A: (D|E|F)+ ; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; J : [J1] J2 [J3] [J4] J5; K : [K1] K2 [K3] [K4] K5;  )" },
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D;  )" }, // FIXME (even with BDD)
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" }, // dead features baby
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D; !D ; )" }, // dead features baby
				{ "FM (A: B [C] D ; C : E F G ; G : (H|I) ; (D -> !C); )" }, // dead features baby
				{ "FM (A: B C [D] ; (D -> !C); )" }, // dead features baby
				{ "FM (A: B C D ; (D -> !C); )" }, // non valid should be zero
				{ "FM (A : B C [D] ; (!B | !C) ;)\n" }, // non valid should be
														// zero

		};
		return Arrays.asList(data);
	}

	@Test
	public void test1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fmv1 = getFMVariable("fm1");
		System.err.println("\n\n\n\n");
		System.err.println("fmv1=" + fmv1);
		
		if (!fmv1.isValid()) // it has no sense to synthesis an unsatisfiable FM
			return ; 

		KnowledgeSynthesis kn1 = new KnowledgeSynthesis() ;
		
		
		// FIXME this is how cleanup works actually
		Set<String> deads = new FormulaAnalyzer<String>(fmv1.getFormula(), _builder).computeDeadFeatures() ; 
		FeatureGraph<String> h1 = fmv1.getFm().getDiagram() ; 
		FeatureModel<String> h1p = new FeatureModel<String>(h1) ; 
		FMLMergerBDD.removeDeadFeaturesFromHierarchy(h1p, deads); 
		kn1.setHierarchy(h1p);
		
		KSynthesisBDD ksynthesis = new KSynthesisBDD(
				new FormulaAnalyzer<String>(fmv1.getFormula(), _builder).removeDeadFeatures(deads), 
				kn1, _builder) ; 
		assertTrue(kn1.isConsistent());
		assertTrue(kn1.isHierarchySpecified());
		
		
		FeatureModelVariable correctedFmv1 = ksynthesis.build() ;
		assertNotNull(correctedFmv1);
		
		_shell.setVerbose(true);
		
		
		
		KSynthesis ksynthesis2 = 
				new KSynthesisSAT(fmv1, new KnowledgeSynthesis(correctedFmv1.getHierarchy().getDiagram().clone())) ;
		
				
		FeatureModelVariable fmv2 = ksynthesis2.build() ;
		_shell.setVerbose(false);
		System.err.println("fmv2=" + fmv2);
		assertEquals(correctedFmv1.features().size(), fmv2.features().size());
		assertEquals(correctedFmv1.root().name(), fmv2.root().name());
		assertTrue(new FMComparisonReport(correctedFmv1, fmv2).reportDiffGroups(correctedFmv1.getXorGroups(), fmv2.getXorGroups(), "XOR"));
		assertTrue(new FMComparisonReport(correctedFmv1, fmv2).reportDiffCst(correctedFmv1.getImplicationConstraints(), fmv2.getImplicationConstraints(), "implications")) ; 
		assertTrue(new FMComparisonReport(correctedFmv1, fmv2).reportDiffCst(correctedFmv1.getExcludeConstraints(), fmv2.getExcludeConstraints(), "implications")) ; 
		
		// TODO: when OR-group support will be integrated
		// assertTrue(new FMComparisonReport(fmv1, fmv2).reportDiffGroups(fmv1.getOrGroups(), fmv2.getOrGroups(), "OR"));

	}
	
	
}

