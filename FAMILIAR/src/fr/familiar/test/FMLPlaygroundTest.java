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

package fr.familiar.test;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.junit.Test;

import com.google.common.collect.Sets;

import fr.familiar.operations.AllConfigsBDD;
import gsd.synthesis.Formula;

public class FMLPlaygroundTest extends FMLTest {

	@Test
	public void testExistentialQ() throws Exception {

		BDD f1 = _builder.get("f1");
		BDD f2 = _builder.get("f2");
		BDD f3 = _builder.get("f3");
		BDD f4 = _builder.get("f4");

		// \phi = (f1 \lor \lnot f3) \land (f1 \lor f2) \land (f2 \lor \lnot f4
		// \lor f3) \land (\lnot f1 \lor f4 \lor \lnot f3)
		BDD phi = f1.or(f3.not()).and(f1.or(f2)).and(f2.or(f4.not()).or(f3))
				.and(f1.not().or(f4).or(f3.not()));

		Set<String> fts = new HashSet<String>();
		fts.add("f1");
		fts.add("f2");
		fts.add("f3");
		fts.add("f4");
		System.err.println("phi="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(phi,
						fts, _builder)));

		Set<String> exfts = new HashSet<String>();
		exfts.add("f1");
		exfts.add("f2");
		BDD phiEx = phi.exist(_builder.mkSet(exfts));
		System.err.println("phiEx="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(
						phiEx, Sets.difference(fts, exfts), _builder)));

		// \equiv (\lnot f3 \land (f4 \lor \lnot f3)) \lor
		// ((\lnot f4 \lor f3) \land (f4 \lor \lnot f3))
		// \lor (f4 \lor \lnot f3)
		BDD phiManual = f3.not().and(f4.or(f3.not()))
				.or(f4.not().or(f3).and(f4.or(f3.not()))).or(f4.or(f3.not()));
		System.err.println("phiManual="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(
						phiManual, Sets.difference(fts, exfts), _builder)));

	}

	@Test
	public void testExistentialQ2() throws Exception {

		BDD f1 = _builder.get("f1");
		BDD f2 = _builder.get("f2");
		BDD f3 = _builder.get("f3");
		BDD f4 = _builder.get("f4");

		// phi = (f1 v f3) ^ (f2 v !f4) ^ (!f2 v f1 v f4)
		BDD phi = f1.or(f3).and(f2.or(f4.not())).and(f2.not().or(f1).or(f4));

		Set<String> fts = new HashSet<String>();
		fts.add("f1");
		fts.add("f2");
		fts.add("f3");
		fts.add("f4");
		System.err.println("phi="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(phi,
						fts, _builder)));

		// \phi_{ex} = \exist f1, \phi
		// = \phi[f1=0] v \phi[f1=1]
		// = (f3 ^ (f2 v !f4) ^ (!f2 v f4)) v (f2 v !f4)

		Set<String> exfts = new HashSet<String>();
		exfts.add("f1");
		BDD phiEx = phi.exist(_builder.mkSet(exfts));
		System.err.println("phiEx="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(
						phiEx, Sets.difference(fts, exfts), _builder)));

		BDD phiExpected = (f3.and(f2.or(f4.not())).and(f2.not().or(f4))).or(f2
				.or(f4.not()));

		System.err.println("phiExpected="
				+ new AllConfigsBDD(_builder).process(new Formula<String>(
						phiExpected, Sets.difference(fts, exfts), _builder)));

		assertTrue(phiExpected.equals(phiEx));

	}

}
