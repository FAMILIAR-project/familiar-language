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
import fr.familiar.operations.heuristics.Heuristic;
import gsd.graph.ImplicationGraph;

import java.util.Set;

public class HybridMetric implements Heuristic {
	
	private Heuristic semanticalMetric;
	private TransitiveReductionMetric transitiveReductionMetric;

	public HybridMetric(Heuristic semanticalMetric) {
		this.semanticalMetric = semanticalMetric;
		transitiveReductionMetric = new TransitiveReductionMetric();
	}

	@Override
	public double similarity(String source, String target) {
		
		double logicalSimilarity = transitiveReductionMetric.similarity(source, target);
		double semanticalSimilarity = semanticalMetric.similarity(source, target);
		
		return logicalSimilarity > 0 ? semanticalSimilarity : semanticalSimilarity * 0.8;
	}

	@Override
	public boolean isMutexGroupsRequired() {
		return true;
	}

	@Override
	public boolean isXorGroupsRequired() {
		return true;
	}

	@Override
	public boolean isOrGroupsRequired() {
		return true;
	}

	@Override
	public void setImplicationGraph(ImplicationGraph<String> implicationGraph) {
		transitiveReductionMetric.setImplicationGraph(implicationGraph);
		semanticalMetric.setImplicationGraph(implicationGraph);
	}

	@Override
	public void setGroups(Set<FGroup> groups) {
		transitiveReductionMetric.setGroups(groups);
		semanticalMetric.setGroups(groups);
	}

}
