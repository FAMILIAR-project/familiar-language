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

package fr.familiar.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fML.CNF;

import fr.familiar.operations.CNFtoExpression;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

public class ConstraintsBuilder {
	
	public static SetVariable createFromCNF(EList<CNF> constraints,
			String var) {
		List<Expression<String>> csts = new ArrayList<Expression<String>>();
		for (CNF cnf : constraints) {
			Expression<String> expr = new CNFtoExpression(cnf).convert();
			csts.add(expr);
		}
		return createFromCNF(csts, var); 
	 
	}
	
	public static SetVariable createFromCNF(Collection<Expression<String>> csts,
			String var) {
	
		
		Set<Variable> cstVars = new HashSet<Variable>();
		for (Expression<String> cst : csts) {
			cstVars.add(new ConstraintVariable(cst, ""));
		}
		return new SetVariable(cstVars, var);
	}

}
