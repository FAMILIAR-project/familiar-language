/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test.featureide;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableBDDFormula;

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

