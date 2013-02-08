package fr.unice.polytech.modalis.familiar.test.tvl;



import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher 
 *         
 *  fm1 in FML
 *  fm2 = tvl (fm1)
 *  fm3 = fml (fm2) 
 *  fm1 eq fm3
 *  
 *   fml ( tvl ( fm1 )) = fm1
 *   
 *   FIXME: uppercase needed in TVL (it works here)
 */


@RunWith(value = Parameterized.class)
public class TranslatorTVLAndBackTest extends FMLTest {

	protected String _fm;

	public TranslatorTVLAndBackTest(String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ;)\n" },
				{ "FM (A : (B|C|D) E [F] ; )\n" }, // multi groups
				{ "FM (A : B C [D] ; (!B | !C) ;)\n" }, // non satisfiable

		};
		return Arrays.asList(data);
	}

	

	
	@Test
	public void testConvert1() throws Exception {

		_shell.parse("fm1 =" + _fm );
		FeatureModelVariable fm1 = getFMVariable("fm1");
		String fm2TVL = fm1.convert(FMFormat.FTVL) ; 
		
		System.err.println("fm2TVL=" + fm2TVL);
		
		FeatureModelVariable fm3FML = FMBuilder.mkTVLModel(fm2TVL);
		System.err.println("fm3FML=" + fm3FML);
		
		assertEquals(fm1.features().names(), fm3FML.features().names());
		assertEquals(Comparison.REFACTORING, fm1.compareBDD(fm3FML, _builder));

	}



	

}

