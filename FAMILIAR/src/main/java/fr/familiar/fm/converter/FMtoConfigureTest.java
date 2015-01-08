package fr.familiar.fm.converter;
import static org.junit.Assert.assertEquals;

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
	String resul;
	FMtoConfigure fmConf;
	


	public FMtoConfigureTest(String fm, String res) {
		chaine = fm;
		resul = res;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B C [D]; I : (L|K)+; E : G [Z]; D : (H|I|J) ; C : [F] E; )","{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"+o\",\"children\":[{\"name\":\"L\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[{\"name\":\"Z\",\"specif\":\"!\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"F\",\"specif\":\"!\",\"children\":[]}]}]}" },
				{ "FM (A : B [C] ; C : I J ;)", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]}]}]}" },
				{ "FM (A : B [C] ; C : (I|J)+ ;)", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"+o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]}]}]}" },
				{ "FM (A : (B|C);)", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A : (B|C)+ ; B: D E; E : (J|H);)", "{\"name\":\"A\",\"specif\":\"+o\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A: B C [D] ; (D -> !C); )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A: B C [E] ; (E -> C); )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"!\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"+o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"!\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]}]}" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"+o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )", "{\"name\":\"A\",\"specif\":\"+o\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]}]}" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"+o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"X\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Y\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"+o\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Z\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"X\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Y\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Z\",\"specif\":\"null\",\"children\":[]}]}" },
				{ "FM (A : B C [D] ; (!B -> !C) ; )", "{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}" },
				{ FMLTest.FM_LAPTOP, "{\"name\":\"Laptop\",\"specif\":\"null\",\"children\":[{\"name\":\"Screen\",\"specif\":\"null\",\"children\":[{\"name\":\"s15\",\"specif\":\"null\",\"children\":[]},{\"name\":\"s12\",\"specif\":\"null\",\"children\":[]},{\"name\":\"s17\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Warranty\",\"specif\":\"null\",\"children\":[{\"name\":\"year3theft\",\"specif\":\"null\",\"children\":[]},{\"name\":\"year2theft\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"CG\",\"specif\":\"null\",\"children\":[{\"name\":\"Integrated\",\"specif\":\"null\",\"children\":[{\"name\":\"GMA\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Standalone\",\"specif\":\"!\",\"children\":[{\"name\":\"Nvidia9400M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvdia8600M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvidia8400M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvidia3670\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"Battery\",\"specif\":\"null\",\"children\":[{\"name\":\"cells6\",\"specif\":\"null\",\"children\":[]},{\"name\":\"cells9\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HDD\",\"specif\":\"null\",\"children\":[{\"name\":\"WD160Go5400tr\",\"specif\":\"null\",\"children\":[]},{\"name\":\"WD500Go\",\"specif\":\"null\",\"children\":[{\"name\":\"S7200tr\",\"specif\":\"null\",\"children\":[]},{\"name\":\"S5400tr\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"RAM\",\"specif\":\"null\",\"children\":[{\"name\":\"Corsair4Go1030\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Kingstom4Go666\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Kingstom2Go666\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"CPU\",\"specif\":\"null\",\"children\":[{\"name\":\"LowTDP\",\"specif\":\"null\",\"children\":[{\"name\":\"Atom270\",\"specif\":\"null\",\"children\":[]},{\"name\":\"AtomZ320\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HighTDP\",\"specif\":\"null\",\"children\":[{\"name\":\"Core2T6600\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Core2P7350\",\"specif\":\"null\",\"children\":[]}]}],\"children\":[{\"name\":\"LowTDP\",\"specif\":\"null\",\"children\":[{\"name\":\"Atom270\",\"specif\":\"null\",\"children\":[]},{\"name\":\"AtomZ320\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HighTDP\",\"specif\":\"null\",\"children\":[{\"name\":\"Core2T6600\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Core2P7350\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"Connectivity\",\"specif\":\"null\",\"children\":[{\"name\":\"Bluetooth\",\"specif\":\"!\",\"children\":[]},{\"name\":\"USB\",\"specif\":\"+o\",\"children\":[{\"name\":\"USB3\",\"specif\":\"null\",\"children\":[]},{\"name\":\"USB2\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Wifi\",\"specif\":\"null\",\"children\":[{\"name\":\"N\",\"specif\":\"null\",\"children\":[]},{\"name\":\"ABG\",\"specif\":\"null\",\"children\":[]}]}]}]}" }
			};
		return Arrays.asList(data);
	}
	
	
	@Test
	public void testFMtoConfigure() throws Exception {
		fmConf = new FMtoConfigure();
		_shell.parse("fm1 =" + chaine);
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		
		assertEquals("JSON and Feature Model doesn't match",fmConf.getConfigureFromFML(fm1),resul);
		
	}
}
