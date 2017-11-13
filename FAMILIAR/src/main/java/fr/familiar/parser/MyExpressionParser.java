/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.parser;

import fr.familiar.interpreter.FMLAssertionError;
import fr.familiar.interpreter.FMLBasicInterpreter;
import fr.familiar.interpreter.FMLFatalError;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.ConstraintVariable;
import gsd.synthesis.Expression;

import java.util.HashSet;
import java.util.Set;

/**
 * @author macher1
 *
 */
public class MyExpressionParser {

	public static Expression<String> parseString(String eStr) {
		
		if (eStr == null || eStr.isEmpty())
			return null ; 
		try {
			FMLShell.getInstance().setToNonInteractiveMode() ; 
			FMLBasicInterpreter interpreter = new FMLBasicInterpreter(FMLShell.getInstance()) ;
			ConstraintVariable cstVariable = (ConstraintVariable) 
					interpreter.eval("constraint ( " + eStr + " )");
			FMLShell.getInstance().setToInteractiveMode() ; 
			return cstVariable.getConstraint() ; 
		} catch (FMLFatalError e) {
			FMLShell.getInstance().printError("Impossible to parse constraint " + eStr + "\n" + 
											e.getMessage());
			
		} catch (FMLAssertionError e) {
			FMLShell.getInstance().printError("Impossible to parse constraint " + eStr + "\n" +  e.getMessage());
		}
		return null ; 
	}

	public static Set<Expression<String>> parseConstraints(String expr) {
		String[] exprs = expr.split(";");
		Set<Expression<String>> le = new HashSet<Expression<String>>();
		for (int i = 0; i < exprs.length; i++) {
			String strExpr = exprs[i];
			if (strExpr != null && !strExpr.isEmpty()) {
				
				Expression<String> e = MyExpressionParser.parseString(strExpr.trim());
				if (e != null)
					le.add(e);
			}
		}
		return le ; 
	}

}
