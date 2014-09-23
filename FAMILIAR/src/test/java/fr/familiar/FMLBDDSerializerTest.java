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

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import net.sf.javabdd.BDD;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.familiar.fm.FMLBDDReader;
import fr.familiar.fm.FMLBDDWriter;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

@RunWith(Parameterized.class)
public class FMLBDDSerializerTest extends FMLTest {

	private String _fmSpecification;
	private String _fmID;

	public FMLBDDSerializerTest(String fmSpecification, String fmID) {
		_fmSpecification = fmSpecification;
		_fmID = fmID;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "FM (A : B [C] [D]; C : E F; F -> G; )", "fm1" },
				{ FMLTest.FM_LAPTOP, "fmLaptop" },

		});
	}

	@Test
	public void testWriter() throws Exception {

		final String FILENAME = "output/" + _fmID + "."
				+ FMLBDDWriter.FML_BDD_EXTENSION;

		FeatureModelVariable fmv1 = FM(_fmID, _fmSpecification);
		FileSerializer.write(FILENAME, fmv1.convert(FMFormat.FMLBDD));

		FMLBDDReader reader = new FMLBDDReader(FILENAME);
		/*
		 * String bddRepresentation = reader.getBDDRepresentation(); String
		 * builderRepresentation = reader.getBuilderRepresentation();
		 */

		Map<String, Integer> map = reader.getMapBuilder();
		assertTrue(map.equals(_builder.getFeatureMap()));

		BDD bdd = reader.getBDD();
		assertTrue(fmv1.getFormula().getBDD().equals(bdd));
		// new AllConfigsBDD(lbuilder).process(fla)

	}

}
