/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import fr.unice.polytech.modalis.familiar.interpreter.FMLAssertionError;
import fr.unice.polytech.modalis.familiar.interpreter.FMLBasicInterpreter;
import fr.unice.polytech.modalis.familiar.interpreter.FMLFatalError;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
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
