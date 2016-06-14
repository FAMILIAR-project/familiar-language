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

import org.junit.Test;
import org.xtext.example.mydsl.fml.FMFormat;

import fr.familiar.FMLTest;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableBDDFormula;

/**
 * @author macher1
 *
 */
public class FMLDimacsSimpleTest extends FMLTest {

	
	@Test
	public void test1() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "A : (B | C) [D] ; ") ;
		System.err.println("fm1=" + fm1);
		System.err.println("" + new SATFMLFormula(fm1).getNode());
		System.err.println("" + fm1.convert(FMFormat.DIMACS));
		System.err.println("" + fm1.isValid());
		System.err.println("" + fm1.counting());
		System.err.println("" + setVariabletoString(fm1.configs()));
		
		
		
		FeatureModelVariableBDDFormula fm1S = FMBuilder.parseDimacsWithBDD("" +
				"c 3 D\n" +
				"c 2 A\n" +
				"c 1 C\n" +
				"p cnf 3 4\n" + 
				"1 2 3 0\n" + 
				"-1 -3 0\n" + 
				"-2 -3 0\n" + 
				"-1 -2 0", _builder);
		System.err.println("" + setVariabletoString(fm1S.configs()));
	}
		
}

