/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.parser;

import org.xtext.example.mydsl.fML.BooleanExpr;

public class BooleanExpressionEval {

	private BooleanExpr cmd;

	/****** environment ********/
	private FMLCommandInterpreter an;

	public BooleanExpressionEval(BooleanExpr cmd, FMLCommandInterpreter an) {
		this.cmd = cmd;
		this.an = an;
	}

	public boolean eval() {
		boolean b = evalExpression(this.cmd);

		// TODO
		boolean bnot = true; // this.cmd.isNot();
		if (bnot)
			return !b;
		return b;
	}

	private boolean evalExpression(BooleanExpr icmd) {

		/*
		 * if (icmd instanceof LiteralImpl) {
		 * 
		 * LiteralImpl rcmd = (LiteralImpl) icmd;
		 * 
		 * String value = rcmd.getValue();
		 * 
		 * // TODO: currently we have problems with the grammar if
		 * (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
		 * boolean b = Boolean.parseBoolean(value); return b; }
		 * 
		 * // can be an integer
		 * 
		 * // necessary a variable try { Variable var = an.getVariable(value);
		 * 
		 * if (var instanceof RefVariable) { RefVariable refVar = (RefVariable)
		 * var; var = refVar.getValueReference(); }
		 * 
		 * if (var instanceof BooleanVariable) { boolean val =
		 * ((BooleanVariable) var).isV(); return val; }
		 * 
		 * // if (var instanceof IntegerWrapper) { // return ((IntegerWrapper)
		 * var).getV(); // }
		 * 
		 * FMShell.getInstance().printError(
		 * "the variable is not a Boolean variable " + value); return false; //
		 * bof
		 * 
		 * } catch (VariableNotExistingException e1) {
		 * 
		 * } catch (VariableAmbigousConflictException e1) {
		 * 
		 * }
		 * 
		 * FMShell.getInstance().printError(
		 * "Boolean expected: unable to parse value or retrieve variable " +
		 * value); return false; // bof
		 * 
		 * }
		 * 
		 * else if (icmd instanceof ComposedLiteralImpl) {
		 * 
		 * ComposedLiteralImpl clcmd = (ComposedLiteralImpl) icmd;
		 * 
		 * String lstrVar = clcmd.getLassert(); String rstrVar =
		 * clcmd.getRassert();
		 * 
		 * Variable lvar = null; try { lvar = an.getVariable(lstrVar); } catch
		 * (VariableNotExistingException e) {
		 * FMShell.getInstance().printError(e.toString()); return false; } catch
		 * (VariableAmbigousConflictException e) {
		 * FMShell.getInstance().printError(e.toString()); return false; }
		 * 
		 * Variable rvar = null; try { rvar = an.getVariable(rstrVar); } catch
		 * (VariableNotExistingException e) {
		 * FMShell.getInstance().printError(e.toString()); return false; } catch
		 * (VariableAmbigousConflictException e) {
		 * FMShell.getInstance().printError(e.toString()); return false; }
		 * 
		 * assert (lvar != null); assert (rvar != null);
		 * 
		 * Operator op = clcmd.getOp();
		 * 
		 * VariableComparator comparator = new VariableComparator(lvar, rvar,
		 * op); return comparator.equal();
		 * 
		 * }
		 * 
		 * else if (icmd instanceof BooleanOperationImpl) { BooleanOperationImpl
		 * oicmd = (BooleanOperationImpl) icmd;
		 * 
		 * boolean ileft = evalExpression(oicmd.getLeft()); boolean iright =
		 * evalExpression(oicmd.getRight());
		 * 
		 * // FMShell.getInstance().printDebugMessage("left: " + ileft + //
		 * " right: " + iright);
		 * 
		 * String op = oicmd.getOp();
		 * 
		 * if (op.equals("&&")) return ileft && iright; else if
		 * (op.equals("||")) return ileft || iright; else {
		 * FMShell.getInstance().printError(
		 * "unable to parse Boolean value: operator " + op + " unknown"); return
		 * false; }
		 * 
		 * }
		 * 
		 * 
		 * 
		 * else if (icmd instanceof BooleanVarValueImpl) {
		 * 
		 * return evalExpression(icmd.getBexpr());
		 * 
		 * }
		 * 
		 * else { FMShell.getInstance().printError(
		 * "unable to parse Boolean value (unknown case)."); return false; }
		 */

		// TODO
		return true;
	}

}
