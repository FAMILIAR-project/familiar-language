package fr.familiar;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.familiar.operations.Mode;
import fr.familiar.variable.FeatureModelVariable;

public class FMLMergeUnionHTest extends FMLTest {
	
	@Test
	public void test1() throws Exception {
		
		
		FeatureModelVariable m1 = FM ("m1", "Visu: (RadialPosition|RectangleInclusionPosition|NodePosition) [Texture] [NominalLab] [Colour] [Size] [Shape] ; \n" + 
				"Texture: NominalT ; \n" + 
				"Colour: (Gradient|Change) ; \n" + 
				"Size: (OrdinalS|Ratio) ; \n" + 
				"Shape: NominalS ; \n" + 
				"Gradient: OrdinalG ; \n" + 
				"Change: NominalC ; \n" + 
				"(NodePosition -> !RadialPosition);\n" + 
				"(Shape -> !RadialPosition);\n" + 
				"(RadialPosition -> !NominalS);\n" + 
			//	"(NominalLab <-> Label);\n" + 
				"(OrdinalG -> !Change);\n" + 
				"(Change -> !Gradient);\n" + 
			//	"(Visu <-> Hierarchy);\n" + 
				"(OrdinalS -> !Ratio);\n" + 
				"(NodePosition -> !RectangleInclusionPosition);\n" + 
				"(NominalC -> !Gradient);\n" + 
				"(((Visu -> Size) | (Visu -> NodePosition)) | (Visu -> RadialPosition));\n" + 
				"(OrdinalG -> !NominalC);\n" + 
				"(RectangleInclusionPosition -> !NominalS);\n" + 
				"(Shape -> !RectangleInclusionPosition);\n");
		
		FeatureModelVariable m2 = FM ("m2", "Visu: (RadialPosition|Shape|RectangleInclusionPosition)? [Texture] [Colour] [Size] [NodePosition] ; \n" + 
		"Texture: NominalT ; \n" + 
		"Colour: (Gradient|Change) ; \n" + 
		"Size: (OrdinalS|Ratio) ; \n" + 
		"Shape: NominalS ; \n" + 
		"Gradient: OrdinalG ; \n" + 
		"Change: NominalC ; \n" + 
		"(NodePosition -> !RadialPosition);\n" + 
		"(Shape -> !RadialPosition);\n" + 
		"(RadialPosition -> !NominalS);\n" + 
		//"(NominalLab <-> Label);\n" + 
		"(OrdinalG -> !Change);\n" + 
		"(Change -> !Gradient);\n" + 
		//"(Visu <-> Hierarchy);\n" + 
		"(RectangleInclusionPosition -> Size);\n" + 
		"(((Visu -> NodePosition) | (Visu -> RectangleInclusionPosition)) | (Visu -> RadialPosition));\n" + 
		"(OrdinalS -> !Ratio);\n" + 
		"(NodePosition -> !RectangleInclusionPosition);\n" + 
		"(NominalC -> !Gradient);\n" + 
		"(OrdinalG -> !NominalC);\n" + 
		"(((Visu -> Size) | (Visu -> NodePosition)) | (Visu -> RadialPosition));\n" + 
		"(RectangleInclusionPosition -> !NominalS);\n" + 
		"(Shape -> !RectangleInclusionPosition);");
		
		assertNotNull(m1);
		assertNotNull(m2);
		
		FeatureModelVariable m3 = m1.merge(m2, Mode.Union);
		
		assertNotNull(m3);
		
		System.err.println("m3=" + m3);
				
		
		
	}

}
