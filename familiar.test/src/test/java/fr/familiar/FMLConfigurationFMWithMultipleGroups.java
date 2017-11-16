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

import org.junit.Test;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;

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
