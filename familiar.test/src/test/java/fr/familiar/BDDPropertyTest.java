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

import static org.junit.Assert.assertEquals;
import net.sf.javabdd.BDD;

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;

public class BDDPropertyTest extends FMLTest {

	@Test
	public void testOrBDD() throws Exception {

		FeatureModelVariable fm1 = FM("fm1",
				"FM (A : B [C] [D] ; D : E F G ; E : (I|J|K); J : (M|N|O)+ ; )");

		BDD b1 = fm1.getFormula().getBDD();
		BDD b2 = b1.and(b1);
		assertEquals(b1, b2);

		BDD b3 = fm1.getSPLOTFormulaAligned(_builder).getBDD();
		BDD b4 = b3.and(b3);
		assertEquals(b3, b4);

		BDD b5 = fm1.getSPLOTFormula().getBDD();
		BDD b6 = b5.and(b5);
		assertEquals(b5, b6);

	}

}
