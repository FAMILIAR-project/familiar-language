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

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

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
