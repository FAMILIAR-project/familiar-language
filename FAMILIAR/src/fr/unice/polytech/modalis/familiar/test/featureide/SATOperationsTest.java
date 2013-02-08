/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test.featureide;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.prop4j.Node;

import fr.unice.polytech.modalis.familiar.operations.featureide.SATBuilder;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */
@RunWith(value = Parameterized.class)
public class SATOperationsTest extends SATBuilderTest {

	/**
	 * @param fm
	 */
	public SATOperationsTest(String fm) {
		super(fm);
	}

	@Test
	public void testCores1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> fm1Cores = fm1.cores();

		Node n1 = new SATBuilder().mkNode(fm1);
		SATFMLFormula sat1 = new SATFMLFormula(n1);
		_shell.setVerbose(true) ; 
		Set<String> satFm1Cores = sat1.cores(fm1.features().names());
		assertEquals(fm1Cores, satFm1Cores);

	}

	@Test
	public void testDeads1() throws Exception {
		_shell.parse("fm1 =" + _fm + "\n");
		FeatureModelVariable fm1 = getFMVariable("fm1");

		Set<String> fm1Deads = fm1.deads();

		Node n1 = new SATBuilder().mkNode(fm1);
		SATFMLFormula sat1 = new SATFMLFormula(n1);
		Set<String> satFm1Deads = sat1.deads(fm1.features().names());

		assertEquals(fm1Deads, satFm1Deads);

	}

}
