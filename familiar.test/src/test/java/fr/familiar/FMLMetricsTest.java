/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.familiar.featureide.FMLSPLOTTest;
import fr.familiar.operations.StructuralMetricsFM;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */

@Ignore
public class FMLMetricsTest extends FMLSPLOTTest {
	
	
	
	@Test
	public void testSPLOTMetrics() throws Exception {
		
		
		List<FeatureModelVariable> fms = collectSPLOTFMs() ;  
		int i = 0 ; 
		for (FeatureModelVariable fm : fms) {
			i++ ; 
			System.err.println("fm(" + i + ")");
			
			StructuralMetricsFM metricsifier = new StructuralMetricsFM(fm); 
			System.err.println("" + metricsifier.toString());
			
			
			System.err.println("\n\n\n\n");
		}			
		
	}
	
	@Test
	public void testGPL() throws Exception {
		
		
			FeatureModelVariable fm = FM ("FMGraphProductLine", "GPL : GraphType [Search] Algorithms ; " +
					"GraphType : (Directed|Undirected) (Weighted|UnWeighted) ; " +
					"Search : (DFS|BFS) ; " +
					"Algorithms : (ShortestPath|Coloring|CycleDetection|MST|StronglyConnected)+ ; " +
					"Coloring : (Approximation|BruteForce) ; " +
					"CycleDetection -> !BFS ; " +
					"CycleDetection -> DFS ; " + 
					"StronglyConnected -> DFS ; " + 
					"StronglyConnected -> Directed ;"
					) ; 
			
			StructuralMetricsFM metricsifier = new StructuralMetricsFM(fm); 
			System.err.println("" + metricsifier.toString());
			
			
			System.err.println("\n\n\n\n");
		
		
	}
	

}

