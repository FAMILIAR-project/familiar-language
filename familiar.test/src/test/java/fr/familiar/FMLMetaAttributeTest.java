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
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;
/**
 * @author macher
 *
 */
public class FMLMetaAttributeTest extends FMLTest {
	
	
	@Test
	public void test1() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(0, fm1.getAttributes().size());
	}
	
	@Test
	public void test2() throws Exception {
		
		_shell.setVerbose(true);
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		Collection<Variable> atts1 = fm1.getAttributes() ; 
		assertEquals(1, atts1.size());
		
		IntegerVariable f = (IntegerVariable) atts1.iterator().next();
		assertEquals(6, f.getV());
		
		Variable f2 = fm1.lookup("f");
		assertTrue(f2 instanceof IntegerVariable);
		assertTrue(f2 == f);
		
	}
	
	@Test
	public void test2bis() throws Exception {
		
		_shell.setVerbose(true);
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		// all attributes are integer
		Collection<Variable> atts = fm1.getAttributes() ;
		for (Variable att : atts) {
			assertTrue(att instanceof IntegerVariable);
		}
	}
	
	@Test
	public void test3() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"n = fm1[@\"f\"]\n" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(1, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}
	
	
	@Test
	public void test3bis() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"n = fm1[@\"f\"]\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}
	@Test
	public void test4() throws Exception {
		
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )" +
				"fm1[@\"f\"] = 6\n" +
				"fm1[@\"g\"] = 7\n" +
				"n = fm1[@\"f\"]\n" +
				"m = n + 2\n" +
				"fm1[@\"g\"] = 8" +
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		assertEquals(2, fm1.getAttributes().size());
		
		IntegerVariable n = getIntegerVariable("n");
		assertEquals(6, n.getV());
		
		
		
	}
	
	@Test
	public void test5() throws Exception {
		_shell.setVerbose(true);
		_shell.parse("" +
				"fm1 = FM (A : B [C] ; )\n" +
				"fm1[@\"author\"] = \"Mathieu\"\n" +
				"fm1.A[@\"color\"] = \"blue\"\n" +
				"fm1.A[@\"description\"] = \"A\"\n" +
				"fm1.B[@\"color\"] = \"red\"\n" +
				
				"") ;
		
		FeatureModelVariable fm1 = getFMVariable("fm1");
		System.err.println("fm1[author]=" + fm1.lookup("author").getValue());
		FeatureVariable ftA = fm1.getFeature("A");
		System.err.println("A[color]=" + ftA.lookup("color").getValue());
		Collection<Variable> ftAattrs = ftA.getAttributes() ;
		for (Variable ftAttrVal : ftAattrs) {
			System.err.println("A[*]=" + ftAttrVal.getValue());
		}
		
		FeatureVariable ftB = fm1.getFeature("B");
		System.err.println("B[color]=" + ftB.lookup("color").getValue());
		
		FeatureVariable ftAbis = fm1.getFeature("A");
		ftAbis.put("foo", new StringVariable("", "foooooo"));
		Collection<Variable> ftAattrsBis = ftAbis.getAttributes() ;
		for (Variable ftAattrVal : ftAattrsBis) {
			System.err.println("(bis) A[*]=" + ftAattrVal.getValue());
		}
			
		
	}
	
	@Test
	public void testProgrammativeWay1() throws Exception {
		FeatureModelVariable fm1 = (FeatureModelVariable) _shell.parse("fm1 = FM (A : B [C] ; )") ;
		FeatureVariable ftC = fm1.getFeature("C");
		fm1.setFeatureAttribute(ftC, "description", new StringVariable ("", "Description of the feature C"));
		fm1.setFeatureAttribute(ftC, "odescription", new StringVariable ("", "Other description of the feature C"));
		assertEquals(ftC.lookup("description").getValue(), "Description of the feature C");
		assertEquals(ftC.lookup("odescription").getValue(), "Other description of the feature C");
		
		// another style for setting attribute
		ftC.put("color", new StringVariable ("","red"));
		assertEquals(ftC.lookup("color").getValue(), "red");
		
		assertEquals(3, ftC.getAttributes().size());

		FeatureVariable ftCbis = fm1.getFeature("C");
		assertEquals(3, ftCbis.getAttributes().size());

	}

}
