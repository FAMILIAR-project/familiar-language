package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.xtext.example.mydsl.fML.OpSelection;

import fr.unice.polytech.modalis.familiar.parser.ConfigurationVariableFactory;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FMMergeSunionKeepSemanticInConfiguration extends FMLTest {

	private FeatureModelVariable fm1, fm2, fm3;
	
	
	@Test
	public void testMergeAndConfigurationS1() throws Exception {
		this.fm1 = FM("s1","Source: TI Criteria; TI: Forecast; Forecast: WeatherTwo; Criteria: Date Location;");
		this.fm2 = FM("s2","Source: TI Criteria; TI: Ephem; Criteria: Date; Date: Beginning; Beginning: Now;");
		this.fm3 = FM("s3","Source: TI Criteria; TI: Calendar; Criteria: Location Duration Date;");
		
		_shell.parse("fmMerge = merge sunion s*");
		FeatureModelVariable fmMerge = (FeatureModelVariable) _environment.getVariable("fmMerge");
		
		assertEquals(3, fmMerge.counting(), 0);
		
		System.err.println("fmMerge=" + fmMerge);
		
		ConfigurationVariable c1 = ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fmMerge, "cMerge"); // mkSPLOT(fmMerge, "cMerge");
		c1.applySelection("WeatherTwo", OpSelection.SELECT);
		assertTrue(c1.getSelected().contains("Date"));
		assertTrue(c1.getSelected().contains("Location")); // dont understand why Location should be selected !? (not in fm2)
		assertFalse(c1.getSelected().contains("Duration"));
	}
	
	@Test
	public void testSimpleMerge() throws Exception {
		
		
		_shell.parse("fm1 = FM (T: A; A: C;)\n" +
				"fm2 = FM (T: B; B: C;)") ;

		_shell.setVerbose(true);
		FeatureModelVariable fm3 = (FeatureModelVariable) _shell.parse("merge union { fm1  fm2 }"); 

		System.err.println("fm3=" + fm3);
		
	}
}
