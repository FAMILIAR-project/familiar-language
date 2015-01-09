package fr.familiar.fm.converter;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConstraintVariable;
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
		String[] jsonResul = new String[]{
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"+o\",\"children\":[{\"name\":\"L\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[{\"name\":\"Z\",\"specif\":\"!\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"F\",\"specif\":\"!\",\"children\":[]}]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]}]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"+o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]}]}]}",
				"{\"name\":\"A\",\"specif\":\"o\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"+o\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"o\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"!\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"+o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"!\",\"children\":[]},{\"name\":\"C\",\"specif\":\"o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"+o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"+o\",\"children\":[{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"o\",\"children\":[{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"+o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"X\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Y\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"+o\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Z\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"o!\",\"children\":[{\"name\":\"J\",\"specif\":\"null\",\"children\":[]},{\"name\":\"I\",\"specif\":\"null\",\"children\":[]},{\"name\":\"H\",\"specif\":\"null\",\"children\":[]},{\"name\":\"K\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"X\",\"specif\":\"null\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Y\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"o\",\"children\":[{\"name\":\"E\",\"specif\":\"null\",\"children\":[]},{\"name\":\"G\",\"specif\":\"null\",\"children\":[]},{\"name\":\"F\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Z\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"A\",\"specif\":\"null\",\"children\":[{\"name\":\"D\",\"specif\":\"!\",\"children\":[]},{\"name\":\"B\",\"specif\":\"null\",\"children\":[]},{\"name\":\"C\",\"specif\":\"null\",\"children\":[]}]}",
				"{\"name\":\"Laptop\",\"specif\":\"null\",\"children\":[{\"name\":\"Screen\",\"specif\":\"o\",\"children\":[{\"name\":\"s15\",\"specif\":\"null\",\"children\":[]},{\"name\":\"s12\",\"specif\":\"null\",\"children\":[]},{\"name\":\"s17\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Warranty\",\"specif\":\"o\",\"children\":[{\"name\":\"year3theft\",\"specif\":\"null\",\"children\":[]},{\"name\":\"year2theft\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"CG\",\"specif\":\"o\",\"children\":[{\"name\":\"Integrated\",\"specif\":\"null\",\"children\":[{\"name\":\"GMA\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Standalone\",\"specif\":\"o!\",\"children\":[{\"name\":\"Nvidia9400M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvdia8600M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvidia8400M\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Nvidia3670\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"Battery\",\"specif\":\"o\",\"children\":[{\"name\":\"cells6\",\"specif\":\"null\",\"children\":[]},{\"name\":\"cells9\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HDD\",\"specif\":\"o\",\"children\":[{\"name\":\"WD160Go5400tr\",\"specif\":\"null\",\"children\":[]},{\"name\":\"WD500Go\",\"specif\":\"o\",\"children\":[{\"name\":\"S7200tr\",\"specif\":\"null\",\"children\":[]},{\"name\":\"S5400tr\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"RAM\",\"specif\":\"o\",\"children\":[{\"name\":\"Corsair4Go1030\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Kingstom4Go666\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Kingstom2Go666\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"CPU\",\"specif\":\"o\",\"children\":[{\"name\":\"LowTDP\",\"specif\":\"o\",\"children\":[{\"name\":\"Atom270\",\"specif\":\"null\",\"children\":[]},{\"name\":\"AtomZ320\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HighTDP\",\"specif\":\"o\",\"children\":[{\"name\":\"Core2T6600\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Core2P7350\",\"specif\":\"null\",\"children\":[]}]}],\"children\":[{\"name\":\"LowTDP\",\"specif\":\"o\",\"children\":[{\"name\":\"Atom270\",\"specif\":\"null\",\"children\":[]},{\"name\":\"AtomZ320\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"HighTDP\",\"specif\":\"o\",\"children\":[{\"name\":\"Core2T6600\",\"specif\":\"null\",\"children\":[]},{\"name\":\"Core2P7350\",\"specif\":\"null\",\"children\":[]}]}]},{\"name\":\"Connectivity\",\"specif\":\"null\",\"children\":[{\"name\":\"Bluetooth\",\"specif\":\"!\",\"children\":[]},{\"name\":\"USB\",\"specif\":\"+o\",\"children\":[{\"name\":\"USB3\",\"specif\":\"null\",\"children\":[]},{\"name\":\"USB2\",\"specif\":\"null\",\"children\":[]}]},{\"name\":\"Wifi\",\"specif\":\"o\",\"children\":[{\"name\":\"N\",\"specif\":\"null\",\"children\":[]},{\"name\":\"ABG\",\"specif\":\"null\",\"children\":[]}]}]}]}"
		};
		String[][] data = new String[][] {
				{ "FM (A: B C [D]; I : (L|K)+; E : G [Z]; D : (H|I|J) ; C : [F] E; )", jsonResul[0] },
				{ "FM (A : B [C] ; C : I J ;)", jsonResul[1] },
				{ "FM (A : B [C] ; C : (I|J)+ ;)", jsonResul[2] },
				{ "FM (A : (B|C);)", jsonResul[3] },
				{ "FM (A : (B|C)+ ; B: D E; E : (J|H);)", jsonResul[4] },
				{ "FM (A: B C [D] ; (D -> !C); )", jsonResul[5] },
				{ "FM (A: B C [E] ; (E -> C); )", jsonResul[6] },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )", jsonResul[7] },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )", jsonResul[8] },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )", jsonResul[9] },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E & C & H); )", jsonResul[10] },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )", jsonResul[11] },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )", jsonResul[12] },
				{ "FM (A : B C [D] ; (!B -> !C) ; )", jsonResul[13] },
				{ FMLTest.FM_LAPTOP, jsonResul[14] }
			};
		return Arrays.asList(data);
	}
	
	
	@Test
	public void testFMtoConfigure() throws Exception {
		fmConf = new FMtoConfigure();
		_shell.parse("fm1 =" + chaine);
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		System.out.println(fm1.getAllConstraints());
		assertEquals("JSON and Feature Model doesn't match",fmConf.getConfigureFromFML(fm1),resul);
		
	}
}
