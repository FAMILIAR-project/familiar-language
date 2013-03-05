/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import java.util.Collection;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Expression;

/**
 * @author macher1
 *
 */
public class FMLSupportTest extends FMLTest {
	
	@Test
	public void testSupp1() throws Exception {
						
		FeatureModelVariable phiR = FMBuilder.parseConstraints("(A and B and C and !D) or " + // T1
				"(!A and !B and !C and D) or " + // T2
				"(!A and !B and C and D) or  " + // T3
				"(A and !B and !C and !D) ;", // T4				
				_builder);
		ImplicationGraph<String> big = phiR.computeImplicationGraph() ;
		System.err.println("phiR (BIG)=" + big);
		
		
		
		
		System.err.println("#phiR=" + phiR.counting());
		phiR.addConstraint(new ConstraintVariable(new Expression<String>("A"), ""));
		phiR.addConstraint(new ConstraintVariable(new Expression<String>("B"), ""));
		
		System.err.println("#phiR'=" + phiR.counting());
		
		
	}
	
	@Test
	public void testBIG1() throws Exception {
						
		FeatureModelVariable phiR = FMBuilder.parseConstraints("A and (B -> C) and (B -> A) ;",			
				_builder);
		
		ImplicationGraph<String> big = phiR.computeImplicationGraph() ; 
		Collection<SimpleEdge> ed = big.edges() ;
		for (SimpleEdge e : ed) {
			String s = big.getSource(e);
			String t = big.getTarget(e);
			System.err.println(s + " => " + t);
		}
		System.err.println("phiR (BIG)=" + big);

		
		System.err.println("#phiR'=" + phiR.counting());
		
		
	}

}
