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

package fr.familiar.test.featureide;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import fr.familiar.experimental.MappingCorrespondence;
import fr.familiar.experimental.featureide.FMMatcher;
import fr.familiar.experimental.featureide.StringSimilarityContainment;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;

public class FMLMatchTest extends FMLTest {

	@Test
	public void testMatch1() throws Exception {

		FeatureModelVariable fm1 = FM("fm1", "FM (A : B [C] D; )");
		FeatureModelVariable fm2 = FM("fm2", "FM (A : B; B : [C] ; C : D; )");

		FMMatcher matcher = new FMMatcher();
		MappingCorrespondence<String> mapping = matcher
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping=" + mapping);
		assertEquals(4, mapping.nbMappings());

		FMMatcher matcher2 = new FMMatcher(new StringSimilarityContainment());
		MappingCorrespondence<String> mapping2 = matcher2
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping2=" + mapping2);
		assertEquals(4, mapping2.nbMappings());

		assertTrue(mapping.equals(mapping2));

	}

	@Test
	public void testMatch2() throws Exception {

		FeatureModelVariable fm1 = FM("fm1", "FM (A : B [C] D; D : E; )");
		FeatureModelVariable fm2 = FM("fm2", "FM (A : B; B : [C] ; C : D; )");

		FMMatcher matcher = new FMMatcher();
		MappingCorrespondence<String> mapping = matcher
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping=" + mapping);
		assertEquals(4, mapping.nbMappings());

		FMMatcher matcher2 = new FMMatcher(new StringSimilarityContainment());
		MappingCorrespondence<String> mapping2 = matcher2
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping2=" + mapping2);
		assertEquals(4, mapping2.nbMappings());

		assertTrue(mapping.equals(mapping2));

	}

	@Test
	public void testMatch3() throws Exception {

		FeatureModelVariable fm1 = FM("fm1", "FM (A : B [C] D; D : E F G; )");
		FeatureModelVariable fm2 = FM("fm2",
				"FM (A : B; B : [C] ; C : D E I; )");

		FMMatcher matcher = new FMMatcher();
		MappingCorrespondence<String> mapping = matcher
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping=" + mapping);
		assertEquals(5, mapping.nbMappings());

		FMMatcher matcher2 = new FMMatcher(new StringSimilarityContainment());
		MappingCorrespondence<String> mapping2 = matcher2
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping2=" + mapping2);
		assertEquals(5, mapping2.nbMappings());

		assertTrue(mapping.equals(mapping2));

		Set<String> expectedFts = new HashSet<String>(
				Arrays.asList(new String[] { "F", "G" }));

		assertEquals(mapping2.getNonMatchingElements(), expectedFts);
		assertEquals(2, mapping2.getNonMatchingElements().size());
	}

	@Test
	public void testMatch4() throws Exception {

		FeatureModelVariable fm1 = FM("fm1", "FM (A : B [C] D; D : E F G; )");
		FeatureModelVariable fm2 = FM("fm2",
				"FM (_A : _B; _B : [_C] ; _C : _D _E _I; )");

		FMMatcher matcher = new FMMatcher();
		MappingCorrespondence<String> mapping = matcher
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping=" + mapping);
		assertEquals(0, mapping.nbMappings());

		FMMatcher matcher3 = new FMMatcher(new StringSimilarityContainment());
		MappingCorrespondence<String> mapping3 = matcher3
				.computeOneToOneMappingOrder(fm1, fm2);
		System.err.println("mapping3=" + mapping3);
		assertEquals(5, mapping3.nbMappings());

		assertEquals(2, mapping3.getNonMatchingElements().size());

		FMMatcher matcher4 = new FMMatcher(new StringSimilarityContainment());
		MappingCorrespondence<String> mapping4 = matcher4
				.computeOneToOneMappingOrder(fm2, fm1);
		System.err.println("mapping4=" + mapping4);
		assertEquals(5, mapping4.nbMappings());

		assertEquals(1, mapping4.getNonMatchingElements().size());

	}

}
