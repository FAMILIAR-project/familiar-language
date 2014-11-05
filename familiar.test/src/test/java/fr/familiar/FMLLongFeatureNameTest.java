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

package fr.familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.VariabilityOperatorVariable;

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
