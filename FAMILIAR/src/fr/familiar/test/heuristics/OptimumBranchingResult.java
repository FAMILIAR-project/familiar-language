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

package fr.familiar.test.heuristics;

import java.util.ArrayList;
import java.util.List;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.variable.FeatureModelVariable;

public class OptimumBranchingResult implements Comparable<OptimumBranchingResult> {

	private FeatureModelVariable referenceFM;
	private FeatureModelVariable synthesizedFM;
	private List<String> commonEdges;
	private Heuristic metric;
	private double ontologicalScore;
	
	public OptimumBranchingResult() {
		referenceFM = null;
		synthesizedFM = null;
		commonEdges = new ArrayList<String>();
	}
	
	public FeatureModelVariable getReferenceFM() {
		return referenceFM;
	}
	public void setReferenceFM(FeatureModelVariable referenceFM) {
		this.referenceFM = referenceFM;
	}
	public FeatureModelVariable getSynthesizedFM() {
		return synthesizedFM;
	}
	public void setSynthesizedFM(FeatureModelVariable synthesizedFM) {
		this.synthesizedFM = synthesizedFM;
	}
	public List<String> getCommonEdges() {
		return commonEdges;
	}
	public void setCommonEdges(List<String> commonEdges) {
		this.commonEdges = commonEdges;
	}
	public Heuristic getMetric() {
		return metric;
	}
	public void setMetric(Heuristic metric) {
		this.metric = metric;
	}
	public double getOntologicalScore() {
		return ontologicalScore;
	}
	public void setOntologicalScore(double ontologicalScore) {
		this.ontologicalScore = ontologicalScore;
	}

	public double getCommonEdgesPercentage() {
		if (referenceFM != null) {
			return commonEdges.size() / ((double) referenceFM.features().size());	
		} else {
			return 0;
		}
		
	}
	
	@Override
	public int compareTo(OptimumBranchingResult o) {
//		return Double.compare(getOntologicalScore(), o.getOntologicalScore());
		return Double.compare(getCommonEdgesPercentage(), o.getCommonEdgesPercentage());
	}
	
	
	
	@Override
	public String toString() {
		return synthesizedFM.getIdentifier() + "\n"
				+ "\t Metric : " + metric + "\n"
				+ "\t CommonEdges : " + getCommonEdgesPercentage() + "\n"
				+ "\t Onto score : " + ontologicalScore;
	}


}
