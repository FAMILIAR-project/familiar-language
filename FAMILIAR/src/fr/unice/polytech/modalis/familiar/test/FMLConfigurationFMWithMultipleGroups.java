package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.unice.polytech.modalis.familiar.parser.ConfigurationVariableFactory;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FMLConfigurationFMWithMultipleGroups extends FMLTest {

	@Test
	public void testConfigurationWithMultipleGroupsSelectsByDefaultMandatoryFeaturesWithFeatureIDE() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: B C D; B: (X|Y)+ [Z]; C: (E|F|G); D: (I|J) K;)");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		ConfigurationVariable c1 =ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, "c1");
		assertEquals (0, c1.getDeselected().size());
		assertEquals (5, c1.getSelected().size()); // 2 "fake" features actually 
	}
	
	@Test
	public void testConfigurationWithMultipleGroupsSelectsByDefaultMandatoryFeaturesWithSPLOT() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: B C D; B: (X|Y)+ [Z]; C: (E|F|G); D: (I|J) K;)");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		ConfigurationVariable c1 =ConfigurationVariableFactory.INSTANCE.mkSPLOT(fm1, "c1"); 
		assertEquals (0, c1.getDeselected().size());
		assertEquals (5, c1.getSelected().size());
	}
}
