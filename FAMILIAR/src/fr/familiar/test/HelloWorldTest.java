/**
 * 
 */
package fr.familiar.test;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;
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
	
	@Test
	public void test2() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1", "A : B [C] D ; D : (E|F|G) ; C -> E ; ");
		ConfigurationVariable cf1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cf1");
		
		cf1.applySelection("C", OpSelection.SELECT);
		System.err.println("" + cf1.getSelected() + " " + cf1.getDeselected()); 
		
		
					
	}
	

}
