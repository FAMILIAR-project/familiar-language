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

import fr.familiar.operations.measures.cliques.ComputeFeatureFrequency;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

import java.util.Map;

public class FrequencyMetric implements FeatureFrequencyMetric{
	private Map<SimpleEdge, Double> suppMap;
	 private Map<SimpleEdge, Double> confMap;
	 private ImplicationGraph<String> big;
	 
	    public FrequencyMetric(FeatureModelVariable phiR) {
	    	ComputeFeatureFrequency compute = new ComputeFeatureFrequency(phiR);
			compute.computeSupportConfidenceMeasures(phiR);
			this.big = compute.getBig();
			suppMap= compute.getSuppMap();
			confMap= compute.getConfMap();
		}
		@Override
		public double support(String featureName1, String featureName2){
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		return suppMap.get(cle);    
		}
	
		public double confidence(String featureName1, String featureName2){
		SimpleEdge cle = big.getEdge(featureName1, featureName2);
		return confMap.get(cle);   
		}
}
