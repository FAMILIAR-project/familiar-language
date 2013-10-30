/**
 * 
 */
package fr.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;

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
	
	@Test
	public void testDeselectOrderShouldDeselectPicasa() throws Exception {
		FeatureModelVariable fmPA = FM("fmPA","Source: TypeInfo Product Criteria; TypeInfo: PictureAlbum; Product: (FlickR|Picasa); Criteria: [Sort]; Sort: (Date|Name)+; Picasa <-> Date;");
		ConfigurationVariable cPA = ConfigurationVariableFactory.INSTANCE.mkBDD(fmPA, "cPA");
		cPA.applySelection("Sort", OpSelection.DESELECT);
		System.err.println("cPA=" + cPA.getSpecificValue());
		assertTrue(cPA.getDeselected().contains("Sort"));
		assertFalse(cPA.getSelected().contains("Sort"));
		assertTrue(cPA.getDeselected().contains("Date"));
		assertTrue(cPA.getDeselected().contains("Name"));
		assertTrue(cPA.getDeselected().contains("Picasa"));
	}
	
	
	@Test
	public void testNewConfigurationIsNotComplete() throws Exception {
		FeatureModelVariable fmPA = FM("fmPA","A: (B|C|D)+;");
		ConfigurationVariable cPA = ConfigurationVariableFactory.INSTANCE.mkBDD(fmPA, "cPA");
		System.err.println("cPA=" + cPA.getSpecificValue());
		assertFalse(cPA.isComplete());
	}
	
	@Test
	public void testNewConfigurationIsComplete() throws Exception {
		FeatureModelVariable fmPA = FM("fmPA","A: (B|C|D)+;");
		ConfigurationVariable cPA = ConfigurationVariableFactory.INSTANCE.mkBDD(fmPA, "cPA");
		cPA.applySelection("B", OpSelection.SELECT);
		System.err.println("cPA=" + cPA.getSpecificValue());
		assertTrue(cPA.isComplete());
	}
	
	@Test
	public void testNewConfigurationIsValid() throws Exception {
		FeatureModelVariable fmPA = FM("fmPA","A: (B|C|D);");
		ConfigurationVariable cPA = ConfigurationVariableFactory.INSTANCE.mkBDD(fmPA, "cPA");
		System.err.println("cPA=" + cPA.getSpecificValue());
		assertTrue(cPA.isValid());
	}

	@Test
	public void testNewConfigurationIsNotCompleteNorValid() throws Exception {
		FeatureModelVariable fmPA = FM("fmPA","Source: TypeInfo Product Criteria; TypeInfo: PictureAlbum; Product: (FlickR|Picasa); Criteria: [Sort]; Sort: (Date|Name)+; Picasa <-> Date;");
		ConfigurationVariable cPA = ConfigurationVariableFactory.INSTANCE.mkBDD(fmPA, "cPA");
		System.err.println("cPA=" + cPA.getSpecificValue());
		assertFalse(cPA.isComplete());
		assertTrue(cPA.isValid());
	}
	
	
	@Test
	public void testMergeAndSyntacticRepresentationAreConsistent() throws Exception {
		FeatureModelVariable fmPA1 = FM("fmPA1","Zone: Product Elements Orientation Design; Product: MainZone_irsam; Elements: Logo Title Content; Content: Graphic Text; Graphic: Full ; Orientation: Landscape; Design: IRSAM;");
		FeatureModelVariable fmPA2 = FM("fmPA2","Zone: Product Elements Orientation Design;Product: MainZone_glc;Elements: Logo Title Content;Content: Graphic Text;Graphic: Full Thumbnails;Orientation: Landscape;Design: GLC;");
		FeatureModelVariable fmPA3 = FM("fmPA3","Zone: Product Elements Orientation Design;Product: RightZone_glc;Elements: Logo Title Content;Content: Graphic Text;Graphic:Thumbnails;Orientation: Portrait;Design: GLC;");
		FeatureModelVariable fmPA4 = FM("fmPA4","Zone: Product Elements Orientation Design;Product: BottomZone_glc;Elements: Logo Content;Content: Graphic Text;Graphic: Thumbnails;Orientation: Landscape;Design: GLC;");
		FeatureModelVariable fmPA5 = FM("fmPA5","Zone: Product Elements Orientation Design;Product: HeaderZone_glc;Elements: Content;Content: Graphic;Graphic: Thumbnails;Orientation: Landscape;Design: GLC;");
		
		_shell.parse("fmMerge = merge union {fmPA1 fmPA2 fmPA3 fmPA4 fmPA5}");
		FeatureModelVariable fmMerge = (FeatureModelVariable) _environment.getVariable("fmMerge");
		
		String fmByHand = "FM("+fmMerge.getSyntacticalRepresentation().replace('\n', ' ')+")";
		System.err.println("fmMergeByHand="+fmByHand);
		
		_shell.parse("fmMergeByHand = "+fmByHand);
		FeatureModelVariable fmMergeByHand = (FeatureModelVariable) _environment.getVariable("fmMergeByHand");
		
		
		double dMerge = fmMerge.counting();
		double dMergeByHand = fmMergeByHand.counting();
		assertTrue(dMerge == dMergeByHand);
		assertTrue(dMerge == 5);
		
		assertEquals(fmMerge, fmMergeByHand); 
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
	
	
	@Test
	public void testConflicts1() throws Exception {
		FeatureModelVariable fm1 = FM("fm1","A: (B|C|D);");
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "fm1");
				
		c1.applySelection("B", OpSelection.SELECT);		
		c1.applySelection("C", OpSelection.SELECT);
		
		System.err.println("c1=" + c1.getSpecificValue());
		assertFalse(c1.getSelected().contains("B"));
	}
	
	@Test
	public void testConflicts2() throws Exception {
		FeatureModelVariable fm1 = FM("fm1","A: (B|C);");
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "fm1");
				
		c1.applySelection("C", OpSelection.DESELECT);		
		c1.applySelection("B", OpSelection.DESELECT);
		
		System.err.println("c1=" + c1.getSpecificValue());
		assertFalse(c1.getDeselected().contains("C"));
	}
	
	@Test
	public void testConflicts3() throws Exception {
		FeatureModelVariable fm1 = FM("fm1","A: (B|C|D|E|F);");
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "fm1");
				
		c1.applySelection("C", OpSelection.SELECT);		
		c1.applySelection("B", OpSelection.SELECT);
		c1.applySelection("D", OpSelection.SELECT);
		
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("D"));
		assertEquals(c1.getSelected().size(), 2);
	}
	
	@Test
	public void testConflicts4() throws Exception {
		FeatureModelVariable fm1 = FM("fm1","A: (B|C|D|E|F);");
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkBDD(fm1, "fm1");
				
		c1.applySelection("C", OpSelection.DESELECT);		
		c1.applySelection("B", OpSelection.DESELECT);
		c1.applySelection("D", OpSelection.DESELECT);
		c1.applySelection("B", OpSelection.SELECT);
		
		System.err.println("c1=" + c1.getSpecificValue());
		assertTrue(c1.getSelected().contains("B"));
		assertEquals(c1.getSelected().size(), 2);
	}

}
