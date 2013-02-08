package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.GetConstraints;
import org.xtext.example.mydsl.fML.KindOfGet;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
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
