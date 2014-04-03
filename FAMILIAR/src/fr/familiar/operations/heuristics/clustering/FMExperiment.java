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

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

import ch.usi.inf.sape.hac.experiment.Experiment;

public class FMExperiment implements Experiment {

	private String[] features;
	private ImplicationGraph<String> implicationGraph;
	private Set<FGroup> xorGroups;
	private Set<FGroup> orGroups;
	
	
	public FMExperiment(ImplicationGraph<String> implicationGraph, Set<FGroup> xorGroups, Set<FGroup> orGroups) {
		this.implicationGraph = implicationGraph;
		this.xorGroups = xorGroups;
		this.orGroups = orGroups;
		features = new String[implicationGraph.vertices().size()];
		int i=0;
		for (String feature : implicationGraph.vertices()) {
			features[i] = feature;
			i++;
		}
	}


	@Override
	public int getNumberOfObservations() {
		return features.length;
	}


	public String getFeature(int observation) {
		return features[observation];
	}
	
	public ImplicationGraph<String> getImplicationGraph() {
		return implicationGraph;
	}
	
	public Set<FGroup> getXorGroups() {
		return xorGroups;
	}


	public Set<FGroup> getOrGroups() {
		return orGroups;
	}

}
