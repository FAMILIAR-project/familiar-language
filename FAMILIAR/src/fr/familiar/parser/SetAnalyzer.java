/**
 * 
 */
package fr.familiar.parser;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.Cliques;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComputeConstraints;
import org.xtext.example.mydsl.fML.ComputeFGroups;
import org.xtext.example.mydsl.fML.ConstraintExpr;
import org.xtext.example.mydsl.fML.CopyVariable;
import org.xtext.example.mydsl.fML.Cores;
import org.xtext.example.mydsl.fML.Deads;
import org.xtext.example.mydsl.fML.FeatureOperation;
import org.xtext.example.mydsl.fML.FullMandatorys;
import org.xtext.example.mydsl.fML.GetConstraints;
import org.xtext.example.mydsl.fML.GetFGroups;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.Leaves;
import org.xtext.example.mydsl.fML.PairwiseCommand;
import org.xtext.example.mydsl.fML.SetExpr;
import org.xtext.example.mydsl.fML.SetOperations;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * @author macher1
 *
 */
public class SetAnalyzer extends FMLAbstractCommandAnalyzer {
	
	protected static Logger _LOGGER = Logger.getLogger(SetAnalyzer.class) ; 

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public SetAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public SetAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		FMLAbstractCommandAnalyzer pars = null;
		Variable v = null;
		
		Command setCmd = _command ; 
		String var = _assigner ; 

		if (setCmd instanceof IdentifierExpr) {
			v = _environment. parse((Command) setCmd, var);
		}

		else if (setCmd instanceof CopyVariable) {
			v = _environment. parse((Command) setCmd, var);
		}

		else if (setCmd instanceof FeatureOperation) {
			v = _environment. parse((Command) setCmd, var);
		}

		else if (setCmd instanceof SetExpr) {
			_LOGGER.debug("set expression: ");
			pars = new SetExprParser((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}

		else if (setCmd instanceof Deads) {
			_LOGGER.debug("Deads feature: ");
			pars = new DeadsAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}

		else if (setCmd instanceof Cores) {
			_LOGGER.debug("Cores feature: ");
			pars = new CoresAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}
		
		else if (setCmd instanceof ConstraintExpr) {
			_LOGGER.debug("Set of constraints: ");
			pars = new ConstraintExprAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();
		}
		
		else if (setCmd instanceof GetConstraints) {
			_LOGGER.debug("(get) constraints: ");
			pars = new GetConstraintsAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();
		}
		
		else if (setCmd instanceof ComputeConstraints) {
			_LOGGER.debug("(compute) constraints: ");
			pars = new ComputeConstraintsAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();
		}
		
		else if (setCmd instanceof GetFGroups) {
			_LOGGER.debug("(get) feature groups: ");
			pars = new GetFGroupsAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();
		}
		
		else if (setCmd instanceof ComputeFGroups) {
			_LOGGER.debug("(compute) feature groups: ");
			pars = new ComputeFGroupsAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();
		}
		
		else if (setCmd instanceof Cliques) {
			_LOGGER.debug("Cliques/Atomic feature: ");
			pars = new CliquesAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}
		
		else if (setCmd instanceof Leaves) {
			_LOGGER.debug("Leaves feature: ");
			pars = new LeavesAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}
		
		else if (setCmd instanceof PairwiseCommand) {
			_LOGGER.debug("Pariwise: ");
			pars = new PairwiseAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();
		}

		else if (setCmd instanceof FullMandatorys) {
			_LOGGER.debug(
					"Full mandatory features: ");
			pars = new FullMandatorysAnalyzer((Command) setCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();

		}

		/*********** SET operations **************/

		else if (setCmd instanceof SetOperations) { // weird: size
			pars = new SetOperationAnalyzer((Command) setCmd, ns, var, _environment);
			pars.parse();
			v = pars.getVariable();

		}
		
		if (v instanceof RefVariable)
			v = ((RefVariable) v).getValueReference();

		if (!(v instanceof SetVariable)) {
			FMLShell.getInstance().setError("set expected but v=" + v);
			return ;
		}
		
			
		SetVariable sv = (SetVariable) v;
		setVariable (sv) ; 

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.SET ; 
	}

}
