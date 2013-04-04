/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.VariabilityOperatorVariable;

/**
 * Moving to long "feature name" means that we have to test the new parsing infrastructure
 * @author macher1
 *
 */
public class FMLLongFeatureNameTest extends FMLTest {

	@Test
	public void test1() throws Exception {
		FeatureModelVariable fm1 = FM ("fm1", "A : \"B baby \" C [\"D\"] ; \"D\" : [E] \"F\" ; C : (\"H H H\"|I) ; ");
		Set<String> fts1 = fm1.features().names() ;
		FeatureModelVariable fm1bis = FM ("fm1bis", "A : B C [D] ; D : [E] F ; C : (H|I) ; ");
		assertEquals (fm1bis.counting(), fm1.counting(), 0);
		
		System.err.println("fts1=" + fts1);
		FeatureVariable d = (FeatureVariable) _shell.parse("d = fm1.\"D\"");
		System.err.println("d=" + d.getFtName());
		System.err.println("d=" + d.parent().getFtName());
		VariabilityOperatorVariable dVop = fm1.getVOP(d.getFtName()) ;
		assertEquals(dVop.getFek(), FeatureEdgeKind.OPTIONAL) ;
		System.err.println("d=" + dVop.getFek());
		
		assertEquals(2, d.children().size()) ; 
		
		FeatureVariable b = (FeatureVariable) _shell.parse("b = fm1.\"B baby \"");
		System.err.println("b=" + b.getFtName());
		System.err.println("b=" + b.parent().getFtName());
		VariabilityOperatorVariable bVop = fm1.getVOP(b.getFtName()) ;
		assertEquals(bVop.getFek(), FeatureEdgeKind.MANDATORY) ;
		System.err.println("b=" + bVop.getFek());
		
		ConstraintVariable c1 = (ConstraintVariable) _shell.parse("c1 = " + "constraint (B -> C) ") ;
		assertFalse(fm1.addConstraint(c1)); 
		ConstraintVariable c2 = (ConstraintVariable) _shell.parse("c1 = " + "constraint (\"B baby \" -> C) ") ;
		assertTrue(fm1.addConstraint(c2)); 
		
		ConstraintVariable c3 = (ConstraintVariable) _shell.parse("c1 = " + "constraint (\"F\" or \"H H H\") ") ;
		assertTrue(fm1.addConstraint(c3)); 
		assertEquals(2, fm1.getAllConstraints().size()); 
		
		System.err.println("fm1=" + fm1);
		
		String splot1 = fm1.convert(FMFormat.FSPLOT);
		//String fide1 = fm1.convert(FMFormat.FIDE);
		String s2t21 = fm1.convert(FMFormat.S2T2);
		String tvl1 = fm1.convert(FMFormat.FTVL);
		
		FeatureModelVariable idFm1 = fm1.slice(SliceMode.INCLUDING, fm1.features().names()) ;
		assertEquals(fm1.features().names(), idFm1.features().names());
		System.err.println("fm1=" + idFm1);
		
	}

}
