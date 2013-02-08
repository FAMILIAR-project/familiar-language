package fr.unice.polytech.modalis.familiar.test;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.fm.FeatureModelGraphvizRenderer;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.utils.FileSerializer;

public class FMLGeneralizedNotationTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		String _fm1Specification = 
			"A : [B] [C] ; B : E [F] ; F : (G|H|I)+ [KK]  ; C : Z ; Z : X [Y] ; H | C; KK | I ; " ;
		//	"A : [B] [C] ; B : E [F] ; F : (G|H|I) ; C : Z ; Z : X [Y] ;" ;
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1Specification);
		FeatureModelVariable fm1G = fmv1.toGeneralizedNotation();
		System.err.println("fm1G=" + fm1G);
		
		FeatureModelGraphvizRenderer<String> graphviz =
			new FeatureModelGraphvizRenderer<String>(fm1G.getFm().getDiagram()) ;
		
		FileSerializer.write("output/" + fmv1.getIdentifier() + ".dot", graphviz.toString());
		
	}

	

}
