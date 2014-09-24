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

package fr.familiar;

import java.util.Set;

import org.junit.Test;

import fr.familiar.operations.PacogenLauncher;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;


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
