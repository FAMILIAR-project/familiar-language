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

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComputeConstraints;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.KindOfCompute;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

public class ComputeConstraintsAnalyzer extends FMLAbstractCommandAnalyzer {

	public ComputeConstraintsAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
	}

	public ComputeConstraintsAnalyzer(Command cmd, NameSpace ns, String var, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	@Override
	public void eval() {
		assert (getCmd() instanceof ComputeConstraints);
		ComputeConstraints cstCmd = (ComputeConstraints) getCmd();

		FMCommand fmCmd = cstCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		
		Set<Expression<String>> exprs = new HashSet<Expression<String>>();
		KindOfCompute kindOf = cstCmd.getKindOfCompute() ; 
		
		fmv.setBuilder(FMLCommandInterpreter.getBuilder()); 
		
		boolean isOver = cstCmd.isOver() ;
		if (isOver) {
			SetVariable setFts = _environment.parseSetCommand(cstCmd.getFts(), null) ;
			
			Set<String> fts = new HashSet<String>();
			Set<Variable> svars = setFts.getVars();
			for (Variable var : svars) {

				if (var instanceof RefVariable)
					var = ((RefVariable) var).getValueReference();

				if (!(var instanceof FeatureVariable)) {
					FMLShell.getInstance().printError(
							"var=" + var + " is not a feature in the set feature");
					return;
				}

				assert (var instanceof FeatureVariable);
				FeatureVariable ftv = (FeatureVariable) var;

				// TODO: check that ftv truly belongs to variables of the formula
				fts.add(ftv.getFtName());

			}
			
			if (kindOf == KindOfCompute.IMPLIES) {
				exprs = ImplicationGraphUtil.toExpressions(fmv.computeImplicationGraph(fts));
			}
			else if (kindOf == KindOfCompute.EXCLUDES) {
				exprs = fmv.computeExcludesEdge(fts);
			}
			else if (kindOf == KindOfCompute.BIIMPLIES) {
				FMLShell.getInstance().printTODO("BIIMPLIES");
				return ; 
			}
			else {
				FMLShell.getInstance().printError("Unable to determine the kind of get (constraints) " + kindOf);
				return ; 
			}
			
		}
		
		else {
			if (kindOf == KindOfCompute.IMPLIES) {
				exprs = ImplicationGraphUtil.toExpressions(fmv.computeImplicationGraph());
			}
			else if (kindOf == KindOfCompute.EXCLUDES) {
				exprs = fmv.computeExcludesEdge();
			}
			else if (kindOf == KindOfCompute.BIIMPLIES) {
				FMLShell.getInstance().printTODO("BIIMPLIES");
				return ; 
			}
			else {
				FMLShell.getInstance().printError("Unable to determine the kind of get (constraints) " + kindOf);
				return ; 
			}
		}		
		
		
		Set<Variable> vars = new HashSet<Variable>();
		for (Expression<String> expr : exprs) {
			vars.add(new ConstraintVariable(expr, null));
		}		
		
		setVariable(new SetVariable(vars));

	}

	@Override
	public RType getType() {
		return RType.SET ; 
	}

}
