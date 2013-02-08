/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test.featureide;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.unice.polytech.modalis.familiar.fm.FMLUtils;
import fr.unice.polytech.modalis.familiar.operations.CountingStrategy;
import fr.unice.polytech.modalis.familiar.operations.SatisfiableStrategy;
import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */

public class FMLConstraintsFMVTest extends FMLTest {

	

	@Test
	public void testConstraints() throws Exception {
		FeatureModelVariable cstFmv = FMBuilder.parseConstraints("A ; B -> A ; C -> A ; ", _builder) ; 
		assertEquals (3, cstFmv.features().size()); 
		double cn = cstFmv.counting() ;
		assertNotNull(cn); 
		System.err.println("#" + cn);
		Set<Variable> configs = cstFmv.configs() ; 
		assertEquals (cn, configs.size(), 0);
		System.err.println("[[configs]]=" + FMLUtils.setConfigToSetStr(configs));
		
		Set<String> cores = cstFmv.cores() ;
		assertEquals(1, cores.size(), 0);
		Set<String> deads = cstFmv.deads() ;
		assertEquals(0, deads.size(), 0);
		
		double cn2 = cstFmv.counting(CountingStrategy.SAT_FML) ;
		assertNotNull(cn2); 
		System.err.println("#" + cn2);
		
		
		boolean b = cstFmv.isValid(SatisfiableStrategy.SAT_FML) ; 
		assertTrue (b);
		
		boolean b1 = cstFmv.isValid() ; 
		assertTrue (b1);
		
		boolean b2 = cstFmv.isValid(SatisfiableStrategy.BDD_FML) ; 
		assertTrue (b2);
		
		try { 
			cstFmv.isValid(SatisfiableStrategy.BDD_SPLOT) ; // not allowed (since it relies on a synthesised FM)
			assertTrue(false);
		}
		catch (Exception e) {
			assertTrue(true);
		}
		
		cstFmv.setBuilder(_builder) ; 
		FeatureModelVariable sliceCst = cstFmv.slice(SliceMode.EXCLUDING, "C"); 
		assertNotNull(sliceCst);
		
		System.err.println("#" + sliceCst.counting());
		System.err.println("[[configs]]" + FMLUtils.setConfigToSetStr(sliceCst.configs()));
		

	}

}


