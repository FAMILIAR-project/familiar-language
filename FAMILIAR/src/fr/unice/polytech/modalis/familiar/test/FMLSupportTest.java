/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableBDDFormula;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Expression;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * @author macher1
 *
 */
public class FMLSupportTest extends FMLTest {
	
	 private Map<SimpleEdge, Double> suppMap = new HashMap<SimpleEdge, Double>();
	 private Map<SimpleEdge, Double> confMap = new HashMap<SimpleEdge, Double>();
	 private ImplicationGraph<String> big;
	@Test
	public void testSupp1() throws Exception {    
		
						
		FeatureModelVariable phiR = FMBuilder.parseConstraints("(A and !B and C and D and !E) or " + // T1
				"(!A and B and !C and !D and !E) or " + // T2
				"(A and B and C and D and !E) or  " + // T3
				"(A and !B and !C and D and E) or  " + // T4
				"(!A and !B and !C and !D and E) ;", // T5				
				_builder);
		
		ImplicationGraph<String> big = phiR.computeImplicationGraph() ;
		System.err.println("phiR (BIG)=" + big);
		double trans_nb = phiR.counting();
		System.err.println("Nb total transactions = " + trans_nb);	
		System.err.println("phiR d'origine=" + phiR + "\n");
		
		Collection<SimpleEdge> ed = big.edges() ;
		for (SimpleEdge e : ed) {
			
			phiR=new FeatureModelVariableBDDFormula("", phiR.getFormula().clone(), _builder);
			System.err.println("phiR before calcul support =" + phiR);
			getSupport(phiR, big, e, trans_nb);
			System.err.println("phiR after calcul support =" + phiR + "\n");
		
		}
		
		Set<SimpleEdge> suppKeys = suppMap.keySet();
		Iterator<SimpleEdge> suppItr = suppKeys.iterator();
		while (suppItr.hasNext()){
		   SimpleEdge cle = suppItr.next(); 
		   Double valeur = suppMap.get(cle); 
		   System.err.println("support of "+ big.getSource(cle) + big.getTarget(cle) +" is "+ valeur);
		}
		Set<SimpleEdge> confKeys = suppMap.keySet();
		Iterator<SimpleEdge> confItr = confKeys.iterator();
		while (confItr.hasNext()){
		   SimpleEdge cle = confItr.next(); 
		   Double valeur = confMap.get(cle); 
		   System.err.println("confidence of "+ big.getSource(cle) + big.getTarget(cle) +" is "+ valeur);
		}
		
		
		
	}
	public void getSupport(FeatureModelVariable phiR, ImplicationGraph<String> big, SimpleEdge e, double trans_nb) throws Exception {
				
		        System.out.println("before s : " + phiR.getFormula());
				phiR.addConstraint(new ConstraintVariable(new Expression<String>(big.getSource(e)), ""));
				System.out.println("after s : " + phiR.getFormula());
				double sourceSupp = phiR.counting()/trans_nb;
				
				System.out.println("before t : " + phiR.getFormula());
				phiR.addConstraint(new ConstraintVariable(new Expression<String>(big.getTarget(e)), ""));
				System.out.println("after t : " + phiR.getFormula());
				
				double supp = phiR.counting()/trans_nb;
				suppMap.put(e, supp);
				confMap.put(e, supp/sourceSupp);
				
				System.err.println("#phiR'=" + phiR.counting()); 
	}
}
