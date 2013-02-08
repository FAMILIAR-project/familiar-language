package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.RemoveConstraint;

import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;

public class RemoveConstraintAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public RemoveConstraintAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public RemoveConstraintAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.BOOLEAN ; 
	}

	@Override
	/**
	 *  add an internal constraint to a feature model 
	 *  return false if some features do not long to the feature model
	 *  true otherwise
	 *  
	 *  TODO: allows such notation: fm1.C -> fm1.D
	 */
	public void eval() {

		assert (_command instanceof RemoveConstraint);

		RemoveConstraint rmCstCmd = (RemoveConstraint) _command;

		/************** bind features to the feature model **************/

		
		FMCommand fmCmd = rmCstCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);

		ConstraintVariable cv = _environment.parseConstraint(rmCstCmd.getCst(), null);
		boolean bound = fmv.removeConstraint(cv.getConstraint());
		/* DEPRECATED
		 * Expression<String> expression = cv.getConstraint() ;
		ConstraintInternBinder binder = new ConstraintInternBinder(expression);
		boolean bound = binder.remove(fmv);*/

		setVariable(new BooleanVariable(_assigner, bound));			
		

	}


}
