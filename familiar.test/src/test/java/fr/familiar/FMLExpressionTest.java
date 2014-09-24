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
import static org.junit.Assert.assertTrue;
import fr.familiar.operations.FMLExpressionUtil;
import fr.familiar.parser.MyExpressionParser;
import fr.familiar.variable.ConstraintVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;

import org.junit.Test;

/**
 * @author mathieuacher
 * 
 */
public class FMLExpressionTest extends FMLTest {

	@Test
	public void testCNF1() {
		Expression<String> e = MyExpressionParser.parseString("!(A|B)");
		Expression<String> cnfe = FMLExpressionUtil.toCNF(e);
		Expression<String> expected = MyExpressionParser.parseString("!A & !B");
		assertEquals(ExpressionType.AND, cnfe.getType());
		assertEquals(expected, cnfe);
	}

	@Test
	public void testCNF2() {
		Expression<String> e = MyExpressionParser.parseString("!((B|J) & C)");
		Expression<String> cnfe = FMLExpressionUtil.toCNF(e);
		assertEquals(ExpressionType.AND, cnfe.getType());
	}

	@Test
	public void testCNF3() {
		Expression<String> e = MyExpressionParser
				.parseString("(B & !E) <-> (D | C)");
		Expression<String> cnfe = FMLExpressionUtil.toCNF(e);
		assertEquals(ExpressionType.AND, cnfe.getType());
		System.err.println("cnfe=" + cnfe);

	}

	
	@Test
	public void testEqualityExcludes() throws Exception {
		
		// eq constraints (excludes)
		ConstraintVariable cst1 = (ConstraintVariable) _shell.parse("constraint (Pink -> !LOST)") ; 
		ConstraintVariable cst1bis = (ConstraintVariable) _shell.parse("constraint (Pink -> !LOST)") ;
		ConstraintVariable cst2 = (ConstraintVariable) _shell.parse("constraint (LOST -> !Pink)") ;
		// other constraints
		ConstraintVariable cst3 = (ConstraintVariable) _shell.parse("constraint (LOST -> Pink)") ;
		ConstraintVariable cst4 = (ConstraintVariable) _shell.parse("constraint (Pink -> LOST)") ;
		
		_shell.setVerbose(true);
		assertTrue(cst1.getConstraint().equals(cst1bis.getConstraint())) ;
		
		assertTrue(cst1.getConstraint().equals(cst2.getConstraint())) ;
		assertTrue(cst2.getConstraint().equals(cst1bis.getConstraint())) ;
		
		assertFalse(cst2.getConstraint().equals(cst3.getConstraint())) ;
		assertFalse(cst4.getConstraint().equals(cst3.getConstraint())) ;
		assertFalse(cst2.getConstraint().equals(cst4.getConstraint())) ;
	}
	

}
