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
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.GetConstraints;
import org.xtext.example.mydsl.fml.KindOfGet;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;

public class GetConstraintsAnalyzer extends FMLAbstractCommandAnalyzer {

	public GetConstraintsAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	public GetConstraintsAnalyzer(Command cmd, NameSpace ns, String var, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	@Override
	public void eval() {

		assert (getCmd() instanceof GetConstraints);
		GetConstraints cstCmd = (GetConstraints) getCmd();

		FMCommand fmCmd = cstCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		
		Set<Expression<String>> exprs = new HashSet<Expression<String>>();
		KindOfGet kindOf = cstCmd.getKindOfGet() ;
		if (kindOf == KindOfGet.HIERARCHY_IMPLIES) {
			exprs = ImplicationGraphUtil.toExpressions(fmv.getImplicationGraphFromPCEdges(FMLCommandInterpreter.getBuilder()));
		}
		else if (kindOf == KindOfGet.HIERARCHY_EXCLUDES) {
			FMLShell.getInstance().printTODO("HIERARCHY_EXCLUDES"); // TODO xor groups
			return ; 
		}
		else if (kindOf == KindOfGet.HIERARCHY_BIIMPLIES) {
			FMLShell.getInstance().printTODO("HIERARCHY_BIIMPLIES");
			return ; 
		}
		else if (kindOf == KindOfGet.CONSTRAINT_IMPLIES) {
			exprs = fmv.getImplicationConstraints();
		}
		else if (kindOf == KindOfGet.CONSTRAINT_EXCLUDES) {
			exprs = fmv.getExcludeConstraints();
		}
		else if (kindOf == KindOfGet.CONSTRAINT_BIIMPLIES) {
			exprs = fmv.getBiImplicationConstraints(); 
		}
		else {
			FMLShell.getInstance().printError("Unable to determine the kind of get (constraints) " + kindOf);
			return ; 
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
