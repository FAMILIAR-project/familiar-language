/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.unice.polytech.modalis.familiar.parser.ConfigurationVariableFactory;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author macher1
 *
 */
public class FMLConfigurationBDDTest extends FMLTest {
	
	/**
	 * really simple unit tests
	 */
	
	
	@Test
	public void testInit1() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1","A : B [C] D ; ");
				
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cMerge"); 
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("A"));
		assertTrue(c1.getSelected().contains("B"));
		assertTrue(c1.getSelected().contains("D"));
		assertEquals(3, c1.getSelected().size());
		
		assertTrue(c1.getUnselected().contains("C"));
	}
	

	@Test
	public void testInit2() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1","A : B [C] D ; D : (E|F); ");
				
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cMerge"); 
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("A"));
		assertTrue(c1.getSelected().contains("B"));
		assertTrue(c1.getSelected().contains("D"));
		assertEquals(3, c1.getSelected().size());
		
		assertTrue(c1.getUnselected().contains("C"));
		assertTrue(c1.getUnselected().contains("E"));
		assertTrue(c1.getUnselected().contains("F"));
	}
	
	@Test
	public void testInit3() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1","A : (B|C|D)+ ; D : (E|F); ");
				
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cMerge"); 
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("A"));
		assertEquals(1, c1.getSelected().size());
		
		assertTrue(c1.getUnselected().contains("C"));
		assertTrue(c1.getUnselected().contains("E"));
		assertTrue(c1.getUnselected().contains("F"));
		assertTrue(c1.getUnselected().contains("B"));
		assertTrue(c1.getUnselected().contains("D"));
	}
	
	@Test
	public void test1() throws Exception {
		
		FeatureModelVariable fm1 = FM("fm1","A : (B|C|D)+ ; D : (E|F); ");
				
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "cMerge");
		c1.applySelection("E", OpSelection.SELECT);
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("A"));
		assertEquals(3, c1.getSelected().size()); // E, D, A
		assertTrue(c1.getSelected().contains("D"));
		assertTrue(c1.getSelected().contains("E"));
		
		assertTrue(c1.getUnselected().contains("C"));
		assertTrue(c1.getUnselected().contains("B"));
		
		assertTrue(c1.getDeselected().contains("F"));
		
	}
	
	
	/**
	 * This unit test is interesting since reasoning over SAT is (currently) limited by the CNF support 
	 * and most of the time the strategy is simply to translate the feature diagram to a CNF formula
	 * but the FD is an over-approximation 
	 * Also the merge computes a BDD formula... back to SAT world is not straightforward 
	 * So the use of a BDD implementation in this case (merge union) is the most adequate choice
	 * @throws Exception
	 */
	@Test
	public void testMerge() throws Exception {
		FeatureModelVariable fm1 = FM("fm1","Source: TI Criteria; TI: Forecast; Forecast: WeatherTwo; Criteria: Date Location;");
		FeatureModelVariable fm2 = FM("fm2","Source: TI Criteria; TI: Ephem; Criteria: Date; Date: Beginning; Beginning: Now;");
		FeatureModelVariable fm3 = FM("fm3","Source: TI Criteria; TI: Calendar; Criteria: Location Duration Date;");
		
		_shell.parse("fmMerge = merge union fm*");
		FeatureModelVariable fmMerge = (FeatureModelVariable) _environment.getVariable("fmMerge");

		
		System.err.println("fmMerge=" + fmMerge);
		
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fmMerge, "cMerge"); // mkSPLOT(fmMerge, "cMerge");
		c1.applySelection("WeatherTwo", OpSelection.SELECT);
		assertTrue(c1.getSelected().contains("Date"));
		assertTrue(c1.getSelected().contains("Location")); // dont understand why Location should be selected !? (not in fm2)
		assertFalse(c1.getSelected().contains("Duration"));
	}

}
