/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.utils.FileSerializer;

/**
 * @author mathieuacher
 * 
 */
public class FMLRendererTest extends FMLTest {

	@Test
	public void testDot1() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; D -> I ;)");

		FeatureModelVariable fm1 = getFMVariable("fm1");
		String dot = fm1.toDOT();
		assertNotNull(dot);
		System.err.println("fm1dot=" + dot);

		FileSerializer.write("output/" + fm1.getIdentifier() + ".dot", dot);

	}

	@Test
	public void testDot2() throws Exception {
		_shell.parse("fm_laptop = " + FM_LAPTOP);

		FeatureModelVariable fm_laptop = getFMVariable("fm_laptop");
		String dot = fm_laptop.toDOT();
		assertNotNull(dot);
		System.err.println("fmlaptopdot=" + dot);

		FileSerializer.write("output/" + fm_laptop.getIdentifier() + ".dot",
				dot);

	}

	@Test
	public void testZest1() throws Exception {
		_shell.parse("fm1 = FM (A : B C [D]; D : (E|F|G); C : (H|I|J|K)+; E -> I ;)");
		FeatureModelVariable fm1 = getFMVariable("fm1");
		String dot = fm1.toDOT();
		assertNotNull(dot);
		// Eclipse-version
		// fm1.gdisplay2() ;
	}

}
