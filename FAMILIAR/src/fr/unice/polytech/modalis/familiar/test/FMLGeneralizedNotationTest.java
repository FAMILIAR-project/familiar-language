package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.*;

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
	
	@Test
	public void test2() throws Exception {
		
		String _fm1Specification = 
			"A : [B] [C] ; B : E [F] ; F : (G|H|I) ; C : Z ; Z : X [Y] ;" ;
		
		FeatureModelVariable fmv1 = FM ("fm1", _fm1Specification);
		FeatureModelVariable fm1G = fmv1.toGeneralizedNotationWithoutOR();
		assertEquals(1, fm1G.getXorGroups().size());
		assertEquals(0, fm1G.getMutexGroups().size());
		System.err.println("fm1G=" + fm1G);
		
		String _fm2Specification = 
				"A : [B] [C] ; B : E [F] ; F : (G|H|I)? ; C : Z ; Z : X [Y] ;" ;
			
		FeatureModelVariable fmv2 = FM ("fm2", _fm2Specification);
		FeatureModelVariable fm2G = fmv2.toGeneralizedNotationWithoutOR();
		assertEquals(0, fm2G.getXorGroups().size());
		assertEquals(1, fm2G.getMutexGroups().size());
		System.err.println("fm2G=" + fm2G);
		
		String _fm3Specification = 
				"A : [B] [C] ; B : E [F] ; F : (G|H|I)+ ; C : Z ; Z : X [Y] ;" ;
			
		FeatureModelVariable fmv3 = FM ("fm3", _fm3Specification);
		FeatureModelVariable fm3G = fmv3.toGeneralizedNotationWithoutOR();
		assertEquals(0, fm3G.getXorGroups().size());
		assertEquals(0, fm3G.getMutexGroups().size());
		System.err.println("fm3G=" + fm3G);
		
		String _fm4Specification = 
				"A : [B] [C] ; B : E [F] ; F : (G|H|I)+ ; C : Z ; Z : X [Y] ; G -> !H ; H -> !I ; G -> !I ;" ;
			
		FeatureModelVariable fmv4 = FM ("fm4", _fm4Specification);
		FeatureModelVariable fm4G = fmv4.toGeneralizedNotationWithoutOR();
		assertEquals(1, fm4G.getXorGroups().size());
		assertEquals(0, fm4G.getMutexGroups().size());
		System.err.println("fm4G=" + fm4G);
		
		String _fm5Specification = 
				"A : [B] [C] ; B : E [F] ; F : [G] [H] [I] ; C : Z ; Z : X [Y] ; G -> !H ; H -> !I ; G -> !I ;" ;
			
		FeatureModelVariable fmv5 = FM ("fm5", _fm5Specification);
		FeatureModelVariable fm5G = fmv5.toGeneralizedNotationWithoutOR();
		assertEquals(0, fm5G.getXorGroups().size());
		assertEquals(1, fm5G.getMutexGroups().size());
		System.err.println("fm5G=" + fm5G);

		
	}
	
	

	

}
