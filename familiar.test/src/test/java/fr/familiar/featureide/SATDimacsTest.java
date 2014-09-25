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

package fr.familiar.featureide;


import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.FMLTest;
import fr.familiar.fm.basic.FMLFeatureModel;
import fr.familiar.operations.FMSlicer;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.fm.featureide.SATFeatureIDEFormula;

/**
 * @author mathieuacher
 * 
 */
@RunWith(value = Parameterized.class)
public class SATDimacsTest extends FMLTest {

	protected String _fm;

	public SATDimacsTest(String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				{ "FM (A: B [C] [E] ; E : (F|G); )" },
				{ "FM (A: B [C] ; )" },
				{ "FM (A: B [C] [E] ;)" },
				{ "FM (A: B C ; )" },
				{ "FM (A: B [C] D ; C : E F G ; G : (H|I) ; (D -> !C); )" },
				{ "FM (A: (D|E|F) ; )" },
				{ "FM (A: B C ; C : E [F]; F : [G]; G : H I [J]; )" },
				{ "FM (A: B C [D] ; (D -> !C); )" },
				{ "FM (A: B C [D] ; (D <-> C); )" },
				{ "FM (A: B C D ; (D -> !C); )" }, // non valid should be zero
				{ "FM (A: B C [E] ; (E -> C); )" },
				{ "FM (A: B C [E] ; B: (F|G|H)+ ; )" },
				{ "FM (A: B C [E] ; C: (F|G|H) ; (F | !E); )" },
				{ "FM (A: B C [E] D ; B: (F|G|H)+ ; ((D | C) -> E); )" },
				{ "FM (A: (B|C|E)+ ; C: (F|G|H) ; (F & !E); )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G)+ ; D : (H|I|J|K)+; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; )" },
				{ "FM (A : B C [D] ; (!B -> !C) ;)\n" },
				{ "FM (A: (D|E|F)+ ; )" },
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D;  )" },
				{ "FM (A: (D|E|F|G)+ ; F -> G ; G <-> D; !D ; )" },
				{ "FM (A : Z X Y [B] [C] [D]; C : (E|F|G) ; D : (H|I|J|K) ; J : [J1] J2 [J3] [J4] J5; K : [K1] K2 [K3] [K4] K5;  )" },
				{ "FM (A : B C [D] ; (!B | !C) ;)\n" }, // non valid should be
														// zero

		};
		return Arrays.asList(data);
	}
	
	@Test
	public void testDimacsSlicing() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		SATFeatureIDEFormula fla1 = new SATFeatureIDEFormula(fm1) ;
		String dimacs1 = fla1.toDIMACS() ;
		FMLFeatureModel fm1BDD = FMBuilder.parseDimacsWithBDD(dimacs1, _builder) ;
		FMLFeatureModel fm1SAT = FMBuilder.parseDimacsWithSAT(dimacs1) ;

		// let us slice the different feature models
		Set<String> randomizedFts = _selectNFirst(fm1.features().names()) ; 
		System.err.println("randomizedFts=" + randomizedFts);
		_shell.setVerbose(true);
		FeatureModelVariable fm1SATSliced = fm1SAT.slice(SliceMode.EXCLUDING, randomizedFts);
		_shell.setVerbose(false);
		System.err.println("" + fm1SATSliced);
		Set<String> satCores = fm1SATSliced.cores() ; 
		System.err.println("satcores=" + satCores);
		
		((FeatureModelVariable) fm1BDD).setBuilder(_builder) ; 
		FeatureModelVariable fm1BDDSliced = fm1BDD.slice(SliceMode.EXCLUDING, randomizedFts);
		System.err.println("" + fm1BDDSliced);
		Set<String> bddCores = fm1BDDSliced.cores() ; 
		System.err.println("bddcores=" + bddCores);
		
		fm1.setBuilder(_builder) ;
		FeatureModelVariable ofm1BDDSliced = fm1.slice(SliceMode.EXCLUDING, randomizedFts);
		System.err.println("" + ofm1BDDSliced);
		Set<String> oCores = ofm1BDDSliced.cores() ; 
		System.err.println("ocores=" + oCores);
		
		oCores.remove(FMSlicer.FAKE_ROOT_NAME) ;
		assertEquals (oCores, bddCores); 
		assertEquals (oCores, satCores); 
	}


	@Test
	public void testDimacs() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		SATFeatureIDEFormula fla1 = new SATFeatureIDEFormula(fm1) ;
		String dimacs1 = fla1.toDIMACS() ;
		
		//System.err.println("dimacs1=" + dimacs1);
		
		FMLFeatureModel fm1BDD = FMBuilder.parseDimacsWithBDD(dimacs1, _builder) ;
		
		/*double n1 = fm1.counting() ;
		double n1bis = fm1bis.counting() ; 
		assertEquals (n1, n1bis, 0);	*/	
		
		assertEquals (fm1.cores(), fm1BDD.cores());
		assertEquals (fm1.deads(), fm1BDD.deads());
		assertEquals(fm1.isValid(), fm1BDD.isValid());
		
		assertEquals (fm1.features().names(), fm1BDD.features().names());
		
		FMLFeatureModel fm1SAT = FMBuilder.parseDimacsWithSAT(dimacs1) ;

		/*double n1bisbis = fm1bisbis.counting() ; 
		assertEquals (n1, n1bisbis, 0);*/		
		
		assertEquals (fm1.cores(), fm1SAT.cores());
		assertEquals (fm1.deads(), fm1SAT.deads());
		assertEquals(fm1.isValid(), fm1SAT.isValid());
		
		assertEquals (fm1.features().names(), fm1SAT.features().names());
		
		//FMLFeatureModel fm1I = FMBuilder.parseDimacs(dimacs1) ;

	}
	
	private Set<String> _selectNFirst(Set<String> names) {
		int N_FIRST = 4 ; 
		int i = 0 ;  
		Set<String> selected = new HashSet<String>() ; 
		for (String ft : names) {
			if (i++ > N_FIRST)
				selected.add(ft);
			
		}
		
		return selected;
	}

	private Set<String> _selectRandomly(Set<String> names) {
		Set<String> selected = new HashSet<String>() ; 
		for (String ft : names) {
			int r = new Random().nextInt() ;
			if (r % 2 == 0) {
				selected.add(ft);
			}
		}
		
		return selected;
	}

}

