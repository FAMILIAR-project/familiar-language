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

import org.xtext.example.mydsl.fml.AtomicConstraintExpr;
import org.xtext.example.mydsl.fml.CNF;
import org.xtext.example.mydsl.fml.Command;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.CNFtoExpression;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.RType;
import gsd.synthesis.Expression;

public class AtomicConstraintExprAnalyzer extends FMLAbstractCommandAnalyzer {

	public AtomicConstraintExprAnalyzer(Command cmd, NameSpace ns,	FMLCommandInterpreter an) {
		super(cmd, ns, an);
		
	}

	public AtomicConstraintExprAnalyzer(Command cmd, String var, NameSpace ns,	FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	@Override
	public void eval() {
		FMLShell.getInstance().printDebugMessage("atomic constraint expression");
		assert (getCmd() instanceof AtomicConstraintExpr);
		AtomicConstraintExpr cstCmd = (AtomicConstraintExpr) getCmd();

		CNF cnf = cstCmd.getExpr() ; 
		Expression<String> expr = new CNFtoExpression(cnf).convert();
		
		// TODO:  new CNFtoExpression(cnf).convert(_environment); 
		// which replaces all occurences of a variable in the expression by its string value
		// we could write something like
		// a = "A"
		// assert (constraint (a -> B) eq constraint (A -> B))
		
		setVariable(new ConstraintVariable(expr, _assigner));
	}

	@Override
	public RType getType() {
		return RType.CONSTRAINT ; 
	}

}
