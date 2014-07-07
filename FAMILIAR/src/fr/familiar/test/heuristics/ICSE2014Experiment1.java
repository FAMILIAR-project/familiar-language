package fr.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.csvreader.CsvWriter;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;

/**
 * Experiment 1: our heuristics vs FASE
 * @author gbecan
 *
 */
public class ICSE2014Experiment1 extends KSynthesisTest {

	// SPLOT
	
	@Test
	public void testOptimumBranchingSPLOT() {
		System.out.println("Optimum branching SPLOT");
		reductionMetric.setOrRequired(true);
		testOptimumBranching(getSPLOTFeatureModelsForFASE(), false);
	}
	
	@Test
	public void testOptimumBranchingSPLOTReduced() {
		System.out.println("Optimum branching SPLOT (BIG reduced)");
		reductionMetric.setOrRequired(true);
		testOptimumBranching(getSPLOTFeatureModelsForFASE(), true);
	}

	
	
	
	
	// PCM 
	@Test
	public void testOptimumBranchingPCM() {
		System.out.println("Optimum branching PCM");
		reductionMetric.setOrRequired(false);
		testOptimumBranching(getPCMFeatureModels(), false);
	}
	
	@Test
	public void testOptimumBranchingPCMReduced() {
		System.out.println("Optimum branching PCM (BIG reduced)");
		reductionMetric.setOrRequired(false);
		testOptimumBranching(getPCMFeatureModels(), true);
	}
	
	// FASE
	@Ignore
	@Test
	public void testFASEAlgorithm() {
		System.out.println("FASE score");
		
		List<FeatureModelVariable> fms = getFASEFeatureModels();
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModelsForFASE();
		
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();


		List<Double> listNbCommonEdges = new ArrayList<Double>();
		
		for (FeatureModelVariable fm : fms) {

			FeatureModelVariable referenceFM = findReferenceFM(fm, referenceFMs);

			// Compute common edges
			double nbCommonEdges = commonEdgesMetric.commonEdges(fm, referenceFM);
			double nbEdges = fm.features().size();
			double meanCommonEdges =  nbCommonEdges / nbEdges;
			listNbCommonEdges.add(meanCommonEdges);
			
		}

		System.out.println("FASE algorithm");
		double sum = 0; 
		for (Double nbCommonEdges : listNbCommonEdges) {
			sum += nbCommonEdges;
		}
		double average = sum / fms.size();
		Collections.sort(listNbCommonEdges);
		double median = listNbCommonEdges.get(listNbCommonEdges.size()/2);
		
		System.out.println("Common edges");
		System.out.println("Average : " + average);
		System.out.println("Median : " + median);
		System.out.println(listNbCommonEdges);
		System.out.println();

	}


	@Ignore
	@Test
	public void testFASEvsFAMILIAR() {
		System.out.println("FASE vs FAMILIAR");
		
		List<FeatureModelVariable> referenceFMs = getSPLOTFeatureModelsForFASE();
		List<FeatureModelVariable> faseFMs = getFASEFeatureModels();
		
		for (Heuristic metric : metrics) {
			System.out.println(metric);
			
			int nbFaseAndFamiliar = 0;
			int nbFaseOnly = 0;
			int nbFamiliarOnly = 0;
			int nbEdges = 0;
			
			int faseWins = 0;
			int familiarWins = 0;
			int draw = 0;
			int sumGap = 0;
			
			for (FeatureModelVariable fm : referenceFMs) {
//				System.out.println(fm.getCompleteIdentifier());
				
				FeatureModelVariable faseFM = findReferenceFM(fm, faseFMs);
				if (faseFM != null) {
					List<String> faseCommonEdges = getCommonEdges(fm, faseFM);
					String faseRoot = faseFM.root().getFtName();
					
					
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, -1);
 					FeatureModelVariable familiarFM = synthesizer.computeCompleteFeatureModel();
 					writeFMToFile(OUTPUT_FOLDER, metric, fm.getCompleteIdentifier(), familiarFM);
 					String familiarRoot = familiarFM.root().getFtName();
					
					List<String> familiarCommonEdges = getCommonEdges(fm, familiarFM);

					// Who wins?
					int gap = faseCommonEdges.size() - familiarCommonEdges.size();

					if (gap > 0) {
						faseWins++;
						sumGap += gap;
					} else if (gap < 0) {
						familiarWins++;
					} else {
						draw++;
					}

					// Common edges
					Set<String> faseAndFamiliarCommonEdges = new HashSet<String>(faseCommonEdges);
					faseAndFamiliarCommonEdges.retainAll(familiarCommonEdges);

					faseCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					familiarCommonEdges.removeAll(faseAndFamiliarCommonEdges);
					
					nbEdges += fm.features().size();
					nbFaseAndFamiliar += faseAndFamiliarCommonEdges.size();
					nbFaseOnly += faseCommonEdges.size();
					nbFamiliarOnly += familiarCommonEdges.size();
					
					
//					if (!faseCommonEdges.isEmpty() || !familiarCommonEdges.isEmpty()) {
//						System.out.println("\"" + fm.getCompleteIdentifier().substring(fm.getCompleteIdentifier().indexOf("_") +1) + ".xml\",");
//						System.out.println(faseCommonEdges.size());
//						System.out.println(familiarCommonEdges.size());
//					}
//					System.out.println();
				}
				
			}
			
			System.out.println("Nb of edges : " + nbEdges);
			System.out.println("FASE and FAMILIAR : " + nbFaseAndFamiliar);
			System.out.println("FASE only : " + nbFaseOnly);
			System.out.println("FAMILIAR only : " + nbFamiliarOnly);
			System.out.println();	
			
			System.out.println("fase : " + faseWins);
			System.out.println("familiar : " + familiarWins);
			System.out.println("draw : " + draw);
			System.out.println("gap : " + sumGap / ((double) faseWins));
			System.out.println();
		}
		
		
	}
	
	@Ignore
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
			
			Comparison comparison = fm.compare(referenceFM);
			if (comparison != Comparison.REFACTORING && comparison != Comparison.GENERALIZATION) {
				System.out.println(fm.getCompleteIdentifier() + " (not a refactoring nor a generalization)");
//				System.out.println(fm.counting());
//				System.out.println(referenceFM.counting());
			}
		}
	}
	
	

	
}
