/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test ;

import java.util.List;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.test.featureide.FMLSPLOTTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */


public class FMLMetricsTest extends FMLSPLOTTest {
	
	
	
	@Test
	public void testSPLOTMetrics() throws Exception {
		
		
		List<FeatureModelVariable> fms = collectSPLOTFMs() ;  
		int i = 0 ; 
		for (FeatureModelVariable fm : fms) {
			i++ ; 
			System.err.println("fm(" + i + ") => " + fm);
			System.err.println("\n\n\n\n");
		}			
		
	}
	

}

