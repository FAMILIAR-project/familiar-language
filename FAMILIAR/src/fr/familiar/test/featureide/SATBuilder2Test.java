package fr.familiar.test.featureide;

import org.junit.Test;
import org.prop4j.Node;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.familiar.operations.featureide.SATBuilder;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;

public class SATBuilder2Test extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		
		FeatureModelVariable fm1 = FM ("fm1", "A : B [C] [D] ; C : (E|F|G) ; D : (I|J)+ ; I -> E ; ");
		Node n1 = new SATBuilder().mkNode(fm1);
		
		System.err.println("fm1=" + fm1);
		System.err.println("fm1 (splot)=" + fm1.convert(FMFormat.FSPLOT));
		System.err.println("n1=" + n1);
		
		
	}

}
