package fr.familiar.operations.heuristics.clustering;

import java.util.HashSet;
import java.util.Set;

import ch.usi.inf.sape.hac.experiment.DissimilarityMeasure;
import ch.usi.inf.sape.hac.experiment.Experiment;
import fr.familiar.experimental.FGroup;
import fr.familiar.operations.heuristics.Heuristic;

public class HeuristicDissimilarityMeasure implements DissimilarityMeasure{
	
	private Heuristic metric;
	
	public HeuristicDissimilarityMeasure(Heuristic metric) {
		this.metric= metric;
	}

	@Override
	public double computeDissimilarity(Experiment experiment, int observation1,
			int observation2) {
		FMExperiment fmExperiment = (FMExperiment) experiment;
		String feature1 = fmExperiment.getFeature(observation1);
		String feature2 = fmExperiment.getFeature(observation2);
//		return 1-metric.similarity(fmExperiment.getImplicationGraph(), fmExperiment.getXorGroups(), fmExperiment.getOrGroups(), feature1, feature2);
		metric.setImplicationGraph(fmExperiment.getImplicationGraph());
		Set<FGroup> groups = new HashSet<FGroup>();
		groups.addAll(fmExperiment.getXorGroups());
		groups.addAll(fmExperiment.getOrGroups());
		metric.setGroups(groups);
		return 1-metric.similarity(feature1, feature2);
	}

}
