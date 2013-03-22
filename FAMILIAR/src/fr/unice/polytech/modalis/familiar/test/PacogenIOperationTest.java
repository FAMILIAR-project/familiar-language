/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import java.util.Set;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.operations.PacogenLauncher;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;


/**
 * @author aymeric hervieu
 * 
 */
public class PacogenIOperationTest extends FMLTest {
	
	

	@Test
	public void testPaco1() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; D -> I ;)");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<Variable> sv = fm1.features().getVars();
		
		for (Variable variable : sv) {
			System.out.println(variable.getValue());
		}
		System.out.println();
		PacogenLauncher pacop = new PacogenLauncher(fm1) ;
	
	//	String dot = fm1.toDOT();
		pacop.launchPacogen() ;
		

		
	}
	

	@Test
	public void testPaco2() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; D -> I ;)");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<Variable> sv = fm1.features().getVars();
		
		for (Variable variable : sv) {
			System.out.println(variable.getValue());
		}
		System.out.println();
		PacogenLauncher pacop = new PacogenLauncher(fm1) ;
	
	//	String dot = fm1.toDOT();
		//pacop.launchPacogenBug() ;
		

		
	}
	
	
	
	/*
	@Test
	public void testPaco2() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; D -> I ;)");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		PacogenLauncher pacop = new PacogenLauncher(fm1,1,1) ;
	//	String dot = fm1.toDOT();
		pacop.launchPacogen() ;
		

	}
	*/
	@Test
	public void testPaco3() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; D -> I ;)");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		PacogenLauncher pacop = new PacogenLauncher(fm1,0,0) ;
	//	String dot = fm1.toDOT();
		pacop.launchPacogen() ;
		

	}
/*	
	@Test
	public void testPaco4() throws Exception {
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.arch"));
	}
	
*/
	/*



	public void testDot2() throws Exception {
		_shell.parse("fm_laptop = " + FM_LAPTOP);

		FeatureModelVariable fm_laptop = getFMVariable("fm_laptop");
		String dot = fm_laptop.toDOT();
		assertNotNull(dot);
		System.err.println("fmlaptopdot=" + dot);

		FileSerializer.write("output/" + fm_laptop.getIdentifier() + ".dot",
				dot);

	}


	public void testZest1() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; E -> I ;)");
		FeatureModelVariable fm1 = getFMVariable("fm1");
		String dot = fm1.toDOT();
		assertNotNull(dot);
		// Eclipse-version
		// fm1.gdisplay2() ;
	}
*/
}
