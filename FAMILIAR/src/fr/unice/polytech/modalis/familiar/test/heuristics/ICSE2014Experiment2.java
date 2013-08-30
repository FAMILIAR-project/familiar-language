package fr.unice.polytech.modalis.familiar.test.heuristics;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FMEditDistanceMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RefactoringEditDistance;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ZhangEditDistance;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;

/**
 * Experiment 2: our heuristics vs FASE
 * @author gbecan
 *
 */
public class ICSE2014Experiment2 extends KSynthesisTest {

	@Ignore
	@Test
	public void testFASEAlgorithm() {
		System.out.println("FASE'13 FMs edit distance");
		FMEditDistanceMetric zhangDistanceMetric = new ZhangEditDistance();
		FMEditDistanceMetric refactoringDistanceMetric = new RefactoringEditDistance();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		double sumCommonEdgesDistance = 0;
		double sumZhangDistance = 0;
		double sumRefactoringDistance = 0;
		double sumCommonEdgesGlobalDistance = 0;
		double totalNbEdges = 0;

		List<FeatureModelVariable> fms = getFASEFeatureModels();
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		int[] distribution = new int[11];

		for (FeatureModelVariable fm : fms) {

			FeatureModelVariable referenceFM = findReferenceFM(fm, referenceFMs);

			double nbCommonEdges = commonEdgesMetric.commonEdges(referenceFM, fm);
//			double nbEdges = computedFM.getFm().getDiagram().edges().size();
			double nbEdges = fm.features().size();
			
			sumCommonEdgesGlobalDistance += nbCommonEdges;
			totalNbEdges += nbEdges;
			double commonEdgesDistance =  nbCommonEdges / nbEdges;
			sumCommonEdgesDistance += commonEdgesDistance;
			distribution[(int) (commonEdgesDistance*10)]++;
			
			double zhangDistance = (zhangDistanceMetric.editDistance(referenceFM, fm) / ((double) fm.features().size()));
			sumZhangDistance += zhangDistance;
			double refactoringDistance = (refactoringDistanceMetric.editDistance(referenceFM, fm) / ((double) fm.features().size()));
			sumRefactoringDistance += refactoringDistance;
		}

		System.out.println("FASE algorithm");
		System.out.println("common edges for all fms : " + sumCommonEdgesGlobalDistance / totalNbEdges);
		System.out.println("common edges by fm : " + sumCommonEdgesDistance / fms.size());
		System.out.println("zhang distance : " + sumZhangDistance / fms.size());
		System.out.println("refactoring distance : " + sumRefactoringDistance / fms.size());
		System.out.println("distribution : ");
		int sum = 0; 
		for (int i =0; i<11; i++) {
			sum += distribution[i];
			System.out.println(i + " : " + distribution[i] + "\t" + sum);
		}
		System.out.println();

	}



	@Test
	public void testCompareFASEandFAMILIAR() {
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (FeatureSimilarityMetric metric : metrics) {
			System.out.println(metric);
			
			int nbFaseAndFamiliar = 0;
			int nbFaseOnly = 0;
			int nbFamiliarOnly = 0;
			int nbEdges = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
//				System.out.println(fm.getCompleteIdentifier());
				
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					String faseRoot = faseFM.root().getFtName();
					
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, -1);
 					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
 					writeFMToFile(metric, fm, familiarFM);
 					String familiarRoot = familiarFM.root().getFtName();
					
					
					
					
					if (!familiarRoot.equals(faseRoot)) {
						System.out.println("\"" + fm.getCompleteIdentifier().substring(fm.getCompleteIdentifier().indexOf("_") +1) + ".xml\",");
//						System.out.println("fase : " + faseRoot);
//						System.out.println("familiar : " + familiarRoot);
//						System.out.println();
					}
					
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);

					Set<String> faseAndFamiliarCommonEdges = new HashSet<String>(faseCommonEdges);
					faseAndFamiliarCommonEdges.retainAll(familiarCommonEdges);

					faseCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					familiarCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					
					nbEdges += fm.features().size();
					nbFaseAndFamiliar += faseAndFamiliarCommonEdges.size();
					nbFaseOnly += faseCommonEdges.size();
					nbFamiliarOnly += familiarCommonEdges.size();
					
					if (!faseCommonEdges.isEmpty() || !familiarCommonEdges.isEmpty()) {
						System.out.println("\"" + fm.getCompleteIdentifier().substring(fm.getCompleteIdentifier().indexOf("_") +1) + ".xml\",");
						System.out.println(faseCommonEdges.size());
						System.out.println(familiarCommonEdges.size());
					}
//					System.out.println();
				}
				
			}
			
			System.out.println("Nb of edges : " + nbEdges);
			System.out.println("FASE and FAMILIAR : " + nbFaseAndFamiliar);
			System.out.println("FASE only : " + nbFaseOnly);
			System.out.println("FAMILIAR only : " + nbFamiliarOnly);
			System.out.println();	
		}
		
		
	}
	
	/**
	 * Competition between FASE and FAMILIAR on each FM
	 */
	@Ignore
	@Test
	public void testFASEvsFAMILIAR() {
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModels();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (FeatureSimilarityMetric metric : metrics) {
			System.out.println(metric);
			int faseWins = 0;
			int familiarWins = 0;
			int draw = 0;
			int sumGap = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
//				System.out.print(fm.getIdentifier() + " : ");
				
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, 0);
					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);
					
					int gap = faseCommonEdges.size() - familiarCommonEdges.size();
					

					if (gap > 0) {
						faseWins++;
						sumGap += gap;
					} else if (gap < 0) {
						familiarWins++;
					} else {
						draw++;
					}
				}
				
			}
			
			System.out.println("fase : " + faseWins);
			System.out.println("familiar : " + familiarWins);
			System.out.println("draw : " + draw);
			System.out.println("gap : " + sumGap / ((double) faseWins));
			System.out.println();
		}
	}
	
	@Test
	public void testCheckFASEFeatureModels() {
		System.out.println("Checking FASE FMs");
		for (FeatureModelVariable fm : getFASEFeatureModels()) {
			FeatureModelVariable referenceFM = findReferenceFM(fm, getSPLOTFeatureModels());
			FeatureGraph<String> fmDiagram = fm.getFm().getDiagram();
			FeatureGraph<String> referenceDiagram = referenceFM.getFm().getDiagram();
			if (fmDiagram.vertices().size() != referenceDiagram.vertices().size()) {
				System.out.println(fm.getCompleteIdentifier() + " (different set of features)");
			}
			
			if (fm.compare(referenceFM) != Comparison.REFACTORING) {
				System.out.println(fm.getCompleteIdentifier() + " (not a refactoring)");
//				System.out.println(fm.counting());
//				System.out.println(referenceFM.counting());
			}
		}
	}
	
}
