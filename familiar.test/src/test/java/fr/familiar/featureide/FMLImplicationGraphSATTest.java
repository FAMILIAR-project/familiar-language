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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;
import gsd.synthesis.Excludes;

public class FMLImplicationGraphSATTest extends SATBuilderTest {

	public FMLImplicationGraphSATTest(String fm) {
		super(fm);
	}

	@Test
	public void testSATBDDEquivalence() throws Exception {

		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");
		Set<String> d1 = fm1.deads();
		System.err.println("deads fm1=" + d1);

		SATFMLFormula satFla1 = new SATFMLFormula(fm1);
		//_shell.setVerbose(true) ; 
		ImplicationGraph<String> ig1 = satFla1.computeImplicationGraph(fm1
				.features().names());
		//_shell.setVerbose(false) ; 
		ImplicationGraph<String> ig1p = fm1.computeImplicationGraph(_builder);

		/*
		ImplicationGraphUtil.debugImplicationGraph(ig1);
		System.err.println("*******\n\n");
		ImplicationGraphUtil.debugImplicationGraph(ig1p);
		*/
		checkIGEqual(ig1, ig1p);

		ExclusionGraph<String> eg1 = satFla1.computeExclusionGraph(fm1
				.features().names());
		ExclusionGraph<String> eg1p = fm1.computeExclusionGraph(_builder);

		if (fm1.deads().size() == 0)
			checkEGEqual(eg1, eg1p);

	}

	private void checkIGEqual(ImplicationGraph<String> ig1,	ImplicationGraph<String> ig1p) {

		boolean verticesEqual = new HashSet<String>(ig1.vertices())
				.equals(new HashSet<Object>(ig1p.vertices()));

		if (!verticesEqual || ig1.edgeSet().size() != ig1p.edgeSet().size()) {
			assertFalse("verticesEqual=" + verticesEqual + "; #ig1="
					+ ig1.edgeSet().size() + "; #ig2=" + ig1p.edgeSet().size(),
					true);
		}

		for (SimpleEdge e1 : ig1.edges())
			if (ig1p.findEdge(ig1.getSource(e1), ig1.getTarget(e1)) == null) {
				assertFalse("", true);

			}

	}

	private void checkEGEqual(ExclusionGraph<String> eg1,
			ExclusionGraph<String> eg1p) {

		ImplicationGraphUtil.debugExclusionGraph(eg1);
		System.err.println("============");
		ImplicationGraphUtil.debugExclusionGraph(eg1p);
		System.err.println("*******\n\n");

		boolean verticesEqual = new HashSet<String>(eg1.vertexSet())
				.equals(new HashSet<Object>(eg1p.vertexSet()));

		if (!verticesEqual || eg1.edgeSet().size() != eg1p.edgeSet().size()) {
			assertFalse("verticesEqual=" + verticesEqual + "; #eg1="
					+ eg1.edgeSet().size() + "; #eg2=" + eg1p.edgeSet().size(),
					true);
		}

		for (Excludes<String> e1 : eg1.edgeSet()) {

			Set<Excludes<String>> sp = eg1p.edgeSet();
			boolean contained = false;
			for (Excludes<String> ep : sp) {
				if (checkExcludeEquality(e1, ep)) {
					contained = true;
					continue;
				}
			}
			assertTrue("e1 is missing: " + e1, contained);
		}

	}

	private boolean checkExcludeEquality(Excludes<String> e1,
			Excludes<String> ep) {
		String a1 = e1.getAntecedent();
		String c1 = e1.getConsequent();

		String ap1 = ep.getAntecedent();
		String cp1 = ep.getConsequent();

		return (a1.equals(ap1) && c1.equals(cp1))
				|| (a1.equals(cp1) && c1.equals(ap1));
	}

}
