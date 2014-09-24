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

package fr.familiar.featureide;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.prop4j.Node;

import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */
@RunWith(value = Parameterized.class)
public class SATOperationsTest extends SATBuilderTest {

	/**
	 * @param fm
	 */
	public SATOperationsTest(String fm) {
		super(fm);
	}

	@Test
	public void testCores1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> fm1Cores = fm1.cores();

		Node n1 = new SATBuilder().mkNode(fm1);
		SATFMLFormula sat1 = new SATFMLFormula(n1);
		_shell.setVerbose(true) ; 
		Set<String> satFm1Cores = sat1.cores(fm1.features().names());
		assertEquals(fm1Cores, satFm1Cores);

	}

	@Test
	public void testDeads1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> fm1Deads = fm1.deads();

		Node n1 = new SATBuilder().mkNode(fm1);
		SATFMLFormula sat1 = new SATFMLFormula(n1);
		Set<String> satFm1Deads = sat1.deads(fm1.features().names());

		assertEquals(fm1Deads, satFm1Deads);

	}

}
