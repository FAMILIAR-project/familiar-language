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
