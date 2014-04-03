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

package fr.familiar.operations.heuristics.metrics;

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

import uk.ac.shef.wit.simmetrics.similaritymetrics.AbstractStringMetric;
import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;
import uk.ac.shef.wit.simmetrics.similaritymetrics.SmithWaterman;

public class SimmetricsMetric implements FeatureSimilarityMetric {

	private AbstractStringMetric metric;


	public SimmetricsMetric(AbstractStringMetric metric) {
		this.metric = metric;
	}

	public SimmetricsMetric(MetricName metricName) {
		setMetric(metricName);
	}

	@Override
	public double similarity(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups, String featureName1, String featureName2) {
		return metric.getSimilarity(featureName1, featureName2);
	}


	public AbstractStringMetric getMetric() {
		return metric;
	}

	public void setMetric(AbstractStringMetric metric) {
		this.metric = metric;
	}

	public void setMetric(MetricName metricName) {
		switch (metricName) {
		case SIMMETRICS_SMITHWATERMAN:
			metric = new SmithWaterman();
			break;
		case SIMMETRICS_LEVENSHTEIN:
			metric = new Levenshtein();
			break;
		default:
			metric = new SmithWaterman();
			break;
		}
	}

	@Override
	public String toString() {
		return "Simmetrics metric (" + metric.getShortDescriptionString() + ")";
	}

	@Override
	public boolean isXorGroupRequired() {
		return false;
	}

	@Override
	public boolean isOrGroupRequired() {
		return false;
	}

}
