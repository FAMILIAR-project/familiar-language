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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */

@RunWith(value = Parameterized.class)
public class FMLVisualizationTest extends FMLTest {

	/**
	 * value of the feature model
	 */
	private String _fmValue;

	/**
	 * variable identifier
	 */
	private String _fmID;

	public FMLVisualizationTest(String fmID, String fmValue) {
		_fmID = fmID;
		_fmValue = fmValue;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "fm1",
						"FM (A : B [C] [D] ; C : (E|F|G); D : (I|J|K)+ ; B : Z [O] ; )" },
				{ "fm2", "FM (A: B C [D] ; (D -> !C); )" },
				{ "fm3", "FM (ZZZ: [B] [C] [E] ; (E -> C); )" },
				{ "fm4", "FM (ZZZ: [B] [C] [E] ; B : I J [K] ; (E -> C); )" },
				{ "fm5",
						"FM (ZZZ: B [C] [E] ; B : I J [K] ; C : [X] Y; (E -> C); )" },
				{ "fm6", "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "fm7",
						"FM (A: B C [E] ; C: (F|G|H)+ ; B : (I|J) ; (F -> !E); )" },
				{ "fm8",
						"FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "fm9",
						"FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "fm10",
						"FM (fm4: A D G ; A : B C; D : E F ; G : (H|I|J)+ ; (E -> H) ; (F -> !I) ; )" },
				{ "fm11", "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "fmLaptop", FMLTest.FM_LAPTOP } };
		return Arrays.asList(data);
	}

	@Test
	public void testProtovis() throws Exception {

		_shell.parse(_fmID + " = " + _fmValue);

		FeatureModelVariable fmv = getFMVariable(_fmID);
		String protovis = fmv.toProtovis();
		FileSerializer.write("output/" + fmv.getIdentifier() + ".html",
				protovis); // _fmID

	}

}
