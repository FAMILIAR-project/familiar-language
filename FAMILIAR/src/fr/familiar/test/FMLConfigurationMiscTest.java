package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;

public class FMLConfigurationMiscTest extends FMLTest {

	@Test
	public void testSelectingMandatoryFeaturesWontRaiseErrorsInConfigUsingSplot() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: B; )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkSPLOT(fm1, "c1"); // works fine as well: ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, "c1"); //
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		boolean b1 = c1.applySelection("A", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		boolean b2 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b2);
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
	}
	
	@Test
	public void testSelectingMandatoryFeaturesWontRaiseErrorsInConfigUsingFeatureIDE() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: B; )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, "c1"); // works fine as well: ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, "c1"); //
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		boolean b1 = c1.applySelection("A", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		boolean b2 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b2);
		assertEquals (0, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
	}
	
	@Test
	public void testCountingConfigurationNumberWorksAsWellOnFMAndConfigurations() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: [B] [C] [D]; B -> C;)");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		assertEquals(6, fm1.counting(), 0.01);
		
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, "c1");
		assertEquals(6, new FeatureModelVariable(null, c1.asFM()).counting(), 0.01);
	}
}
