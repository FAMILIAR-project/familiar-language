package fr.familiar.operations.heuristics.clustering;

import ch.usi.inf.sape.hac.experiment.DissimilarityMeasure;
import ch.usi.inf.sape.hac.experiment.Experiment;
import fr.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;

public class HeuristicDissimilarityMeasure implements DissimilarityMeasure{
	
	private FeatureSimilarityMetric metric;
	
	public HeuristicDissimilarityMeasure(FeatureSimilarityMetric metric) {
		this.metric= metric;
	}

	@Override
	public double computeDissimilarity(Experiment experiment, int observation1,
			int observation2) {
		FMExperiment fmExperiment = (FMExperiment) experiment;
		String feature1 = fmExperiment.getFeature(observation1);
		String feature2 = fmExperiment.getFeature(observation2);
		return 1-metric.similarity(fmExperiment.getImplicationGraph(), fmExperiment.getXorGroups(), fmExperiment.getOrGroups(), feature1, feature2);
	}

}
