/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

/**
 * @author macher1
 *
 */
public class HelloWorldTest extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1", "A : B [C] ; ");
		
		SetVariable cores1 = (SetVariable) _shell.parse("cores fm1");
		System.err.println("cores1=" + cores1.names());
		assertEquals(2, cores1.size());
		ImplicationGraph<String> big1 = fm1.computeImplicationGraph() ;
		Collection<SimpleEdge> e1 = big1.edges() ;
		for (SimpleEdge e : e1) {
			System.err.println("" + big1.getEdgeSource(e) + " => " + big1.getEdgeTarget(e));
		}
			
		
	}

}
