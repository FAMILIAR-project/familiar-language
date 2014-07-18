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

package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.VariableImpl;

/**
 * @author macher
 * problem with SAT4J versions (SPLAR requires older version than FeatureIDE)
 */
@Ignore
public class FMLConfiguration3Test extends FMLTest {
	
	@Test
	public void testSPLOTImpl() throws Exception {

		_shell.setVerbose(true);
		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); )");
		
		//assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
		
		
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");
		assertNotNull(c1);
		assertEquals (0, c1.getDeselected().size());
		assertEquals (1, c1.getSelected().size()); 
		
		boolean b1 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
	}
	
	@Test
	public void testSPLOTImpl2() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");
		assertEquals (0, c1.getDeselected().size());
		assertEquals (1, c1.getSelected().size()); 
		
		boolean b1 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		
		boolean b2 = c1.applySelection("D", OpSelection.DESELECT);
		assertTrue(b2);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		boolean b3 = c1.applySelection("C", OpSelection.DESELECT);
		assertTrue(b3);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
			
	}
	
	@Test
	public void testSPLOTImplConflicts() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");
		assertEquals (0, c1.getDeselected().size());
		assertEquals (1, c1.getSelected().size()); 
		assertTrue(c1.isValid()); 
		
		boolean b1 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		assertTrue(c1.isValid()); 
		assertEquals(b1, c1.isValid()); 
		
		assertEquals(0, c1.getConflicts().size()); 
		
		// handle conflicts
		boolean b2 = c1.applySelection("D", OpSelection.SELECT);
		assertFalse(b2);
		assertEquals (1, c1.getDeselected().size());
		assertEquals (3, c1.getSelected().size()); // - root
		
		assertFalse(c1.isValid()); 
		assertEquals(b2, c1.isValid()); 
		
		assertTrue(c1.getConflicts().size() == 1); 
		System.err.println("conflicts=" + c1.getConflicts());
		
		// handle conflicts
		System.err.println("c1=" + c1.getSpecificValue()); 
		boolean b3 = c1.applySelection("C", OpSelection.SELECT);
		assertFalse(b3);
		assertFalse(c1.isValid()); 
		assertEquals(b3, c1.isValid()); 
		
		assertTrue(c1.getConflicts().size() == 1); 
		System.err.println("conflicts=" + c1.getConflicts());
		
		System.err.println("c1=" + ((VariableImpl)c1).getSpecificValue());
		
	}
	
	@Test
	public void testSPLOTImplConflicts2() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); D : (E|F|G); B : (I|J) ; C : (U|T) ; )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");
		assertEquals (0, c1.getDeselected().size());
		assertEquals (1, c1.getSelected().size()); 
		assertTrue(c1.isValid()); 
		
		boolean b1 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (7, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		assertTrue(c1.isValid()); 
		assertEquals(b1, c1.isValid()); 
		
		assertEquals(0, c1.getConflicts().size()); 
		
		
		boolean b2 = c1.applySelection("I", OpSelection.SELECT);
		assertTrue(b2);
		assertEquals (8, c1.getDeselected().size());
		assertEquals (3, c1.getSelected().size()); // - root
		
		assertTrue(c1.isValid()); 
		assertEquals(b2, c1.isValid()); 
		
		assertTrue(c1.getConflicts().size() == 0); 
		
		// handle conflicts
		
		boolean b3 = c1.applySelection("C", OpSelection.SELECT);
		assertFalse(b3);
		assertEquals (7, c1.getDeselected().size());
		assertEquals (4, c1.getSelected().size()); // - root
		
		assertFalse(c1.isValid()); 
		assertEquals(b3, c1.isValid()); 
		
		assertTrue(c1.getConflicts().size() > 0); 
		System.err.println("conflicts=" + c1.getConflicts());
		System.err.println("c1=" + ((VariableImpl)c1).getSpecificValue());
			
	}
	
	@Test
	public void testSPLOTImplConflicts3() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); D : (E|F|G); B : (I|J) ; C : (U|T) ; )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");

		
		c1.applySelection("B", OpSelection.SELECT);
		// changing decision (D and fixing inconsistency)
		c1.applySelection("D", OpSelection.SELECT);
		boolean b3 = c1.applySelection("B", OpSelection.DESELECT);
		
		System.err.println("conflicts=" + c1.getConflicts());
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.isValid()); 		
		assertTrue(c1.getConflicts().size() == 0); 
		assertEquals(b3, c1.isValid()); 

			
	}
	
	@Test
	public void testSPLOTImplValid() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); B & C & D ;)");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		assertFalse(fm1.isValid());
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
								mkConfigurationVariableSPLOT(fm1, "c1");
		assertFalse(c1.isValid()); 
				
		
			
	}
	
	@Test
	public void testSPLOTSabine() throws Exception {

		FeatureModelVariable fm1 = FM ("fm1", "FM (A: (B|C|D); )");
		assertNotNull(fm1.mkSPLOTFla(fm1.toSPLOT(), _builder));
	
		ConfigurationVariable c1 = //mkConfigurationVariableFeatureIDE(fm1, "c1") ; 
		mkConfigurationVariableSPLOT(fm1, "c1");
		assertEquals (0, c1.getDeselected().size());
		assertEquals (1, c1.getSelected().size()); 
		
		boolean b1 = c1.applySelection("B", OpSelection.SELECT);
		assertTrue(b1);
		assertEquals (2, c1.getDeselected().size());
		assertEquals (2, c1.getSelected().size()); 
		
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "A", "B" })), c1.getSelected()) ; 
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "C", "D" })), c1.getDeselected()) ; 
		
		
		boolean b2 = c1.applySelection("C", OpSelection.SELECT);
		assertFalse(b2);
		
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "A", "B", "C" })), c1.getSelected()) ; 
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "D" })), c1.getDeselected()) ; 
		
		
		Set<String> conflicts = c1.getConflicts() ;
		System.err.println("conflicts=" + conflicts);
		for (String conflict : conflicts) {
			c1.applySelection(conflict, OpSelection.DESELECT);
		}
				
		assertTrue(c1.isValid());
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "B", "D" })), c1.getDeselected()) ; 
		assertEquals(new HashSet<String>(Arrays.asList(new String[] { "A", "C" })), c1.getSelected()) ; 
	
		
			
	}
	
	
	@Test
	public void testSimon() throws Exception {
		
		_shell.parse("a = FM (A : B [C] D ;)\n conf_a = configuration a\nselect C in conf_a\n");
		
		assertNotNull(new FMLtoFeatureIDE(getFMVariable("a")).convert()); 
		
		assertNotNull( mkConfigurationVariableSPLOT(getFMVariable("a"), "conf_a2"));
		ConfigurationVariable confA = getConfigurationVariable("conf_a");
		System.err.println("" + confA.getSelected()); 
	}

	private ConfigurationVariable mkConfigurationVariableSPLOT(FeatureModelVariable fm1, String fmID) {
		return ConfigurationVariableFactory.INSTANCE.mkSPLOT(fm1, fmID);
	}
	
	private ConfigurationVariable mkConfigurationVariableFeatureIDE(FeatureModelVariable fm1, String fmID) {
		return ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fm1, fmID);
	}

}
