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

package fr.familiar;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import fr.familiar.operations.CliquesComputation;
import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.CliquesOperationFactory;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.SetVariable;

/**
 * FIXME: instantiate the test with CliquesComputation.SAT as well
 * @author macher1
 *
 */
public class FMLCliquesTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B C ; ") ;
		Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.BDD) ; 
				//SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		assertEquals(cls.size(), 1);
		
		_shell.parse("cls1 = cliques fm1");
		SetVariable cls1 = getSetVariable("cls1");
		
		assertEquals(cls1.size(), cls.size());
		
		
	}
	
	
	@Test
	public void test2() throws Exception {
		
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : B [C] ; C : D E [F] ; ") ;
		Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.BDD) ; 
				//SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		assertEquals(2, cls.size());
		
		_shell.parse("cls1 = cliques fm1");
		SetVariable cls1 = getSetVariable("cls1");
		
		assertEquals(cls.size(), cls1.size());
		
		
	}
	
	/**
	 * Benavides et al. 2010
	 * @throws Exception
	 */
	@Test
	public void testBenavides() throws Exception {
		
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : [B] C D ; B : E ; D : (F|G)+ ; E -> F ; ") ;
		Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.BDD) ; 
				//SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		assertEquals(2, cls.size());
		
		_shell.parse("cls1 = cliques fm1");
		SetVariable cls1 = getSetVariable("cls1");
		
		assertEquals(cls.size(), cls1.size());
		
		System.err.println("generalized notation:" + fmv1.toGeneralizedNotation()); 
		System.err.println("fmv1:" + fmv1); 
		SetVariable as1 = fmv1.atomicSet();
		
		System.err.println("as1=" + as1.names());
		
		fmv1.contractWithAtomicSet();
		// TODO: contract with cliques
		
		System.err.println("atomicSet contraction:" + fmv1);
		
	}
	
	
	/**
	 * Benavides et al. 2010 (subtle modification)
	 * @throws Exception
	 */
	@Test
	public void testBenavides2() throws Exception {
		
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : [B] C D ; B : E ; D : (F|G)+ ; E <-> F ; ") ; // note the biimplication
		Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.BDD) ; 
				//SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
		assertEquals(2, cls.size());
		
		_shell.parse("cls1 = cliques fm1");
		SetVariable cls1 = getSetVariable("cls1");
		
		assertEquals(cls.size(), cls1.size());
		
		System.err.println("generalized notation:" + fmv1.toGeneralizedNotation()); 
		System.err.println("cliques:" + cls1.names()); 
		
		SetVariable as1 = fmv1.atomicSet();
		
		System.err.println("as1=" + as1.names());
		
		fmv1.contractWithAtomicSet();
		// TODO: contract with cliques
		
		System.err.println("atomicSet contraction:" + fmv1);
		
	}
	
	
	/** Example taken from "Automated Analysis of Feature Models using Atomic Sets" 
	 * @throws Exception
	 */
	@Test
	public void testSegura() throws Exception {
		
		FeatureModelVariable fm1 = FM ("fm1", "MobilePhone: UtilityFunctions Settings [Media] [Connectivity] ;" +
				"UtilityFunctions: Calls Messaging [Games] AlarmClock RingingTones ;" +
				"Messaging : (SMS|MMS)+ ; " +
				"Settings : OS [JavaSupport] ; " +
				"OS : (Symbian|WinCE) ; " +
				"Media : [Camera] MP3 ; " +
				"Connectivity: USB [Bluetooth] ; " + 
				"Games -> JavaSupport ; "
		);
		
		SetVariable cls1 = fm1.cliques() ;
		assertEquals(3, cls1.size());
		System.err.println("cls1=" + cls1.names());
				
	}
	
	@Test
	public void testCliqueFar() throws Exception {
		
		FeatureModelVariable fmv1 = FM ("fm1", "A : [B] D ; B : C ; D : [E] ; E : F ; C <-> F; ");
		SetVariable cls1 = fmv1.cliques() ; 
		System.err.println("cls1=" + cls1.names()) ; 
	}
	
	@Test
	public void test3() throws Exception {
		
		Object[][] specifications = new Object[][] { { "A : B [C] ; C : D E [F] ; ", 2 }, 
							{ "A : B [C] ; ", 1 }, 
							{ "A : [B] [C] ; C : D E [F] ; ", 1 }, 
							{ "A : [B] [C] ; C : [D] [E] [F] ; ", 0 }, 
							{ "A : B C ; C : [D] [E] [F] ; !B & !C ; ", 0 }, // non valid 
							{ "A : [B] [C] ; C : [D] [E] [F] ; B & C ; ", 1 }, // cliques hidden
							{ "A : [B] [C] ; C : [D] [E] [F] ; !B & !C ; ", 0 }, // dead
		}  ;
		for (int i = 0; i < specifications.length; i++) {
			String specification = (String) specifications[i][0];
			int nCliques = (Integer) specifications[i][1];
			
			FeatureModelVariable fmv1 = FM ("fm1", specification) ;
			Collection<Set<String>> cls = CliquesOperationFactory.mkCliques(fmv1, CliquesComputation.BDD) ; 
					//SAT_WITH_BDD_SATISFIABILITY_CHECKS); 
			System.err.println("cliques: " + cls);
			assertEquals(nCliques, cls.size());
			
			
			
			_shell.parse("cls1 = cliques fm1");
			SetVariable cls1 = getSetVariable("cls1");
			
			assertEquals(cls.size(), cls1.size());
		}		
		
	}
	
	@Test
	public void testMutexCliques1() throws Exception {
		
		Object[][] specifications = new Object[][] { { "A : B [C] ; C : (D|E|F) ; ", 1 }, 
							{ "A : B [C] ; ", 0 }, 
							{ "A : [B] [C] ; C : (D|E|F|G) ; E -> !B ; ", 2 }, 
							{ "A : (B|C) ; C : (D|E|F) ; ", 2 }
		}  ;
		for (int i = 0; i < specifications.length; i++) {
			String specification = (String) specifications[i][0];
			int nCliques = (Integer) specifications[i][1];
			
			FeatureModelVariable fmv1 = FM ("fm1", specification) ;
			Collection<Set<String>> cls = fmv1.mutexCliques(_builder) ;
			System.err.println("cliques: " + cls);
			System.err.println("excludes: " + fmv1.computeExclusionGraph(_builder).edgeSet());
			assertEquals(nCliques, cls.size());
			
		}		
		
	}

}
