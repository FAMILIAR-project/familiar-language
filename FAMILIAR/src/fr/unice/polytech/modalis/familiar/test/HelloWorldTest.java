/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;

/**
 * @author macher1
 *
 */
public class HelloWorldTest extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1bis = FM("fm1", "A : B [C] ; ");
		ImplicationGraph<String> big1 = fm1bis.computeImplicationGraph() ;
		
		//System.err.println("big1=" + big1.edges().size());
		
		
		
		
		
	}

}
