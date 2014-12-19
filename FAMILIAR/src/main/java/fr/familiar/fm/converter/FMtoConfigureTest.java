package fr.familiar.fm.converter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.FeatureModelVariable;

@RunWith(value = Parameterized.class)
public class FMtoConfigureTest extends FMLTest{
	String affichage;
	String chaine;
	FMtoConfigure fmConf;
	


	public FMtoConfigureTest(String fm) {
		chaine = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B C [D]; I : (L|K)+; E : G [Z]; D : (H|I|J) ; C : [F] E; )" },
				{ "FM (A : B [C] ; C : I J ;)" },
				{ "FM (A : B [C] ; C : (I|J)+ ;)" },
				{ "FM (A : (B|C);)" },
				{ "FM (A : (B|C)+ ; B: D E; E : (J|H);)" },
				
				
				
				{ "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ; )" }, { FMLTest.FM_LAPTOP}, };
		return Arrays.asList(data);
	}
	
	
	@Test
	public void testFMtoConfigure() throws Exception {
		fmConf = new FMtoConfigure();
		_shell.parse("fm1 =" + chaine);
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		
		affichage = fm1.getFm().toString();
		
		affichage = fmConf.getConfigureFromFML(fm1);
		
	}
}
