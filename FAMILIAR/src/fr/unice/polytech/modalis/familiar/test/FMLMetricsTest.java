/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test ;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import fr.unice.polytech.modalis.familiar.operations.StructuralMetricsFM;
import fr.unice.polytech.modalis.familiar.test.featureide.FMLSPLOTTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

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

