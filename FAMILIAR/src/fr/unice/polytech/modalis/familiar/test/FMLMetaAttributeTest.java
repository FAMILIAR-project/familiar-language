/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.IntegerVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
/**
 * @author macher
 *
 */
public class FMLMetaAttributeTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(0, fm1.getAttributes().size());
	}
	
	@Test
	public void test2() throws Exception {
		
		_shell.setVerbose(true);
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		Collection<Variable> atts1 = fm1.getAttributes() ; 
		assertEquals(1, atts1.size());
		
		IntegerVariable f = (IntegerVariable) atts1.iterator().next();
		assertEquals(6, f.getV());
		
		Variable f2 = fm1.lookup("f");
		assertTrue(f2 instanceof IntegerVariable);
		assertTrue(f2 == f);
		
	}
	
	@Test
	public void test2bis() throws Exception {
		
		_shell.setVerbose(true);
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		// all attributes are integer
		Collection<Variable> atts = fm1.getAttributes() ;
		for (Variable att : atts) {
			assertTrue(att instanceof IntegerVariable);
		}
	}
	
	@Test
	public void test3() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"n = fm1[@\"f\"]\n" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(1, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}
	
	
	@Test
	public void test3bis() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"n = fm1[@\"f\"]\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}
	@Test
	public void test4() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"fm1[@\"g\"] = 7\n" +
				"n = fm1[@\"f\"]\n" +
				"m = n + 2\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}

}
