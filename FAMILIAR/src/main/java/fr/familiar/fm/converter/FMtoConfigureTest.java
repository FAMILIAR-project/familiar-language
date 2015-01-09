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
	private final static String[] jsonResul = new String[]{
			"{\"tree\":[",
			"],\"constraint\":[",
			"]}"
	};

	public FMtoConfigureTest(String fm, String res) {
		chaine = fm;
		resul = res;
	}

	@Parameters
	public static List<String[]> data() {
		String[] jsonResulTree = new String[]{
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
		String[] jsonResulConstraint = new String[]{
				"",
				"",
				"",
				"",
				"",
				"{\"cons\":\"->\",\"param1\":[{\"name\":\"D\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"C\",\"specif\":\"!\"}]}",
				"{\"cons\":\"->\",\"param1\":[{\"name\":\"E\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"C\",\"specif\":\"FEATURE\"}]}",
				"",
				"{\"cons\":\"->\",\"param1\":[{\"name\":\"E\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"F\",\"specif\":\"FEATURE\"}]}",
				"{\"cons\":\"->\",\"param1\":[{\"cons\":\"|\",\"param1\":[{\"name\":\"D\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"C\",\"specif\":\"FEATURE\"}]}],\"param2\":[{\"name\":\"E\",\"specif\":\"FEATURE\"}]}",
				"{\"cons\":\"&\",\"param1\":[{\"cons\":\"&\",\"param1\":[{\"cons\":\"&\",\"param1\":[{\"name\":\"F\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"E\",\"specif\":\"!\"}]}],\"param2\":[{\"name\":\"C\",\"specif\":\"FEATURE\"}]}],\"param2\":[{\"name\":\"H\",\"specif\":\"FEATURE\"}]}",
				"",
				"",
				"{\"cons\":\"->\",\"param1\":[{\"name\":\"B\",\"specif\":\"!\"}],\"param2\":[{\"name\":\"C\",\"specif\":\"!\"}]}",
				"{\"cons\":\"->\",\"param1\":[{\"name\":\"LowTDP\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"Standalone\",\"specif\":\"!\"}]},{\"cons\":\"->\",\"param1\":[{\"name\":\"Standalone\",\"specif\":\"FEATURE\"}],\"param2\":[{\"name\":\"HighTDP\",\"specif\":\"FEATURE\"}]}"
		};
		String[][] data = new String[][] {
				{ "FM (A: B C [D]; I : (L|K)+; E : G [Z]; D : (H|I|J) ; C : [F] E; )", jsonResul[0]+jsonResulTree[0]+jsonResul[1]+jsonResulConstraint[0]+jsonResul[2] },
				{ "FM (A : B [C] ; C : I J ;)", jsonResul[0]+jsonResulTree[1]+jsonResul[1]+jsonResulConstraint[1]+jsonResul[2] },
				{ "FM (A : B [C] ; C : (I|J)+ ;)", jsonResul[0]+jsonResulTree[2]+jsonResul[1]+jsonResulConstraint[2]+jsonResul[2] },
				{ "FM (A : (B|C);)", jsonResul[0]+jsonResulTree[3]+jsonResul[1]+jsonResulConstraint[3]+jsonResul[2] },
				{ "FM (A : (B|C)+ ; B: D E; E : (J|H);)", jsonResul[0]+jsonResulTree[4]+jsonResul[1]+jsonResulConstraint[4]+jsonResul[2] },
				{ "FM (A: B C [D] ; (D -> !C); )", jsonResul[0]+jsonResulTree[5]+jsonResul[1]+jsonResulConstraint[5]+jsonResul[2] },
				{ "FM (A: B C [E] ; (E -> C); )", jsonResul[0]+jsonResulTree[6]+jsonResul[1]+jsonResulConstraint[6]+jsonResul[2] },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )", jsonResul[0]+jsonResulTree[7]+jsonResul[1]+jsonResulConstraint[7]+jsonResul[2] },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )", jsonResul[0]+jsonResulTree[8]+jsonResul[1]+jsonResulConstraint[8]+jsonResul[2] },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )", jsonResul[0]+jsonResulTree[9]+jsonResul[1]+jsonResulConstraint[9]+jsonResul[2] },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E & C & H); )", jsonResul[0]+jsonResulTree[10]+jsonResul[1]+jsonResulConstraint[10]+jsonResul[2] },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )", jsonResul[0]+jsonResulTree[11]+jsonResul[1]+jsonResulConstraint[11]+jsonResul[2] },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )", jsonResul[0]+jsonResulTree[12]+jsonResul[1]+jsonResulConstraint[12]+jsonResul[2] },
				{ "FM (A : B C [D] ; (!B -> !C); )", jsonResul[0]+jsonResulTree[13]+jsonResul[1]+jsonResulConstraint[13]+jsonResul[2] },
				{ FMLTest.FM_LAPTOP, jsonResul[0]+jsonResulTree[14]+jsonResul[1]+jsonResulConstraint[14]+jsonResul[2] }
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
