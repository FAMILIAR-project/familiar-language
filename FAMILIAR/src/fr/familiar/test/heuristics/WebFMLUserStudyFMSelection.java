package fr.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;
import fr.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.variable.FeatureModelVariable;

public class WebFMLUserStudyFMSelection extends KSynthesisTest {

	@Test
	public void testCompareRandomAndOntoFM() {
		List<FeatureModelVariable> fms = getSPLOTFeatureModels();
		CommonEdgesMetric commonEdgesMetric = new CommonEdgesMetric();

		List<CommonEdgesDiff> diffs = new ArrayList<CommonEdgesDiff>();
		
		for (FeatureModelVariable fm : fms) {
			double randomScore = -1;
			double ontoFMScore = -1;
			String bestHeuristic = "";
			
			for (Heuristic metric : metrics) {
				double nbIterations = metric instanceof RandomMetric ? RANDOM_ITERATIONS : 1;
				double sumCommonEdges = 0;
				
				for (int i=0; i<nbIterations; i++) {
					InteractiveFMSynthesizer synthesizer = new InteractiveFMSynthesizer(fm, metric, null, null, -1);
					FeatureModelVariable generatedFM = synthesizer.computeCompleteFeatureModel();
					sumCommonEdges += percentageCommonEdges(commonEdgesMetric, fm, generatedFM);
				}
				
				double commonEdges = sumCommonEdges / nbIterations;
				
				if (metric instanceof RandomMetric) {
					randomScore = commonEdges;
				} else if (commonEdges > ontoFMScore){
					ontoFMScore = commonEdges;
					bestHeuristic = metric.toString();
				}
			}
			
			CommonEdgesDiff diff = new CommonEdgesDiff(fm, "Random", randomScore, bestHeuristic, ontoFMScore);
			diffs.add(diff);
		}
		
		Collections.sort(diffs);
		for (CommonEdgesDiff diff : diffs) {
			System.out.println(diff);
			System.out.println();
		}
 	}

	private double percentageCommonEdges(CommonEdgesMetric commonEdgesMetric, FeatureModelVariable fm, FeatureModelVariable randomFM) {
		double nbCommonEdges = commonEdgesMetric.commonEdges(randomFM, fm);
		double nbEdges = fm.features().size();
		return nbCommonEdges / nbEdges;
	}
}
