/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fml.OpSelection;

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
