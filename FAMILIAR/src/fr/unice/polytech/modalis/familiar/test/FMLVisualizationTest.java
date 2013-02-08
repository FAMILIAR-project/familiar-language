/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.utils.FileSerializer;

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
