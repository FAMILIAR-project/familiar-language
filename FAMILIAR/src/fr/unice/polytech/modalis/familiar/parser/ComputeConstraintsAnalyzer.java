package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComputeConstraints;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.KindOfCompute;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
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
