/**
 * 
 */
package fr.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.CopyVariable;
import org.xtext.example.mydsl.fML.FeatureOperation;
import org.xtext.example.mydsl.fML.IdentifierExpr;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author macher1
 *
 */
public class FeatureAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public FeatureAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public FeatureAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		
		Command ftCmd = _command ; 
		String var = _assigner ; 
				
		if (ftCmd instanceof IdentifierExpr) {
			v = _environment.parse((Command) ftCmd, var);
		} else if (ftCmd instanceof CopyVariable) {
			v = _environment.parse((Command) ftCmd, var);
		}

		/*************** Feature Operation ***************/
		else if (ftCmd instanceof FeatureOperation) {
			// TODO
			// only parent operation
			pars = new FeatureOperationAnalyzer((Command) ftCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();
		}

		else {
			FMLShell.getInstance().printTODO("unknown FTCommand " + ftCmd);
			return ;
		}

		if (v instanceof RefVariable) {
			v = ((RefVariable) v).getValueReference();
		}

		if (!(v instanceof FeatureVariable)) {
			FMLShell.getInstance().setError("feature expected but v=" + v);
			return ;
		}
		FeatureVariable ft = (FeatureVariable) v;
		setVariable (ft);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE ; 
	}

}
