package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.AtomicConstraintExpr;
import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.Command;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.CNFtoExpression;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
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
