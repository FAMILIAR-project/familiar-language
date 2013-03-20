/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.Leaves;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;

/**
 * @author macher1
 *
 */
public class LeavesAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public LeavesAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public LeavesAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Leaves);
		Leaves leavesCmd = (Leaves) _command;

		FMCommand fmCmd = leavesCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		SetVariable cls = fmv.leaves() ;
		setVariable(cls);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.SET ; 
	}

}
