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

import java.util.Set;

import org.junit.Test;

import fr.familiar.operations.CountingStrategy;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class FMLCountingConfigsTest extends FMLTest {

	@Test
	public void testCountingFMLaptop() throws Exception {
		// FMLTest.FM_LAPTOP // takes a lot of time with SAT solvers
		_shell.parse("fm1 =" + FMLTest.FM_LAPTOP);

		FeatureModelVariable fm1 = getFMVariable("fm1");
		double withBDD = fm1.counting();
		Set<Variable> configsBDD = fm1.configsBDD(); // fm1.configsBDD() ;
		assertEquals(withBDD, configsBDD.size(), 0);

		double withBDDSPLOT = fm1.counting (CountingStrategy.BDD_SPLOT);
		assertEquals(withBDD, withBDDSPLOT, 0);
		// // double withSAT = fm1.countingSAT() ;
		// // assertEquals (withBDD, withSAT, 0);
	}

}
