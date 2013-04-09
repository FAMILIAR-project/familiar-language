/**
 * 
 */
package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.prop4j.Literal;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.SliceMode;

import de.ovgu.featureide.fm.core.Constraint;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.variable.Comparison;
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
		System.err.println("fts1=" + fts1);
		
		assertEquals (fm1bis.counting(), fm1.counting(), 0);
		
		_shell.setVerbose(true);
		FeatureVariable d = (FeatureVariable) _shell.parse("d = fm1.\"D\"");
		_shell.setVerbose(false);
		assertNotNull(d);
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
		System.err.println("splot1=" + splot1);
		String fide1 = fm1.convert(FMFormat.FIDE);
		System.err.println("fide1=" + fide1);
		String s2t21 = fm1.convert(FMFormat.S2T2);
		String tvl1 = fm1.convert(FMFormat.FTVL);
		
		FeatureModelVariable idFm1 = fm1.slice(SliceMode.INCLUDING, fm1.features().names()) ;
		assertEquals(fm1.features().names(), idFm1.features().names());
		System.err.println("fm1=" + fm1);
		System.err.println("idFm1=" + idFm1);
		
		assertEquals(Comparison.REFACTORING, idFm1.compare(fm1));
		assertEquals(Comparison.REFACTORING, fm1.compare(idFm1));
		
	}
	
	@Test
	public void test2() throws Exception {
		
		FeatureModelVariable fm1 = FM ("fm1", "A : \"B baby \" C [\"D\"] ; \"D\" : [E] \"F\" ; C : (\"H H H\"|I) ; ");
		assertTrue(fm1.renameFeature("D", "Dd"));
		assertTrue(fm1.renameFeature("B baby ", "B "));
		assertTrue(fm1.renameFeature("A", "A "));
		assertTrue(fm1.renameFeature("E", "E @ 123 "));
		
		
		_shell.parse("renameFeature fm1.\"H H H\" as \"Serie H Saison 1 @ \"");
		
		System.err.println("" + fm1.features().names());
		
		de.ovgu.featureide.fm.core.FeatureModel fmIDE1 = new FMLtoFeatureIDE(fm1).convert();
		System.err.println("" + fmIDE1.getFeatureNames()); 
		//fmIDE1.addConstraint(new Constraint(fmIDE1, new Literal("Bjjjjj", true)));
		
	}

}
