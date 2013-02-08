package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import net.sf.javabdd.BDD;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class BDDPropertyTest extends FMLTest {

	@Test
	public void testOrBDD() throws Exception {

		FeatureModelVariable fm1 = FM("fm1",
				"FM (A : B [C] [D] ; D : E F G ; E : (I|J|K); J : (M|N|O)+ ; )");

		BDD b1 = fm1.getFormula().getBDD();
		BDD b2 = b1.and(b1);
		assertEquals(b1, b2);

		BDD b3 = fm1.getSPLOTFormulaAligned(_builder).getBDD();
		BDD b4 = b3.and(b3);
		assertEquals(b3, b4);

		BDD b5 = fm1.getSPLOTFormula().getBDD();
		BDD b6 = b5.and(b5);
		assertEquals(b5, b6);

	}

}
