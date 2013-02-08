/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.Cliques;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.Cores;
import org.xtext.example.mydsl.fML.FMCommand;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;

/**
 * @author macher
 *
 */
public class CliquesAnalyzer extends FMLAbstractCommandAnalyzer {

	public CliquesAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}
	
	public CliquesAnalyzer(Command cmd, String var, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Cores);
		Cliques cliquesCmd = (Cliques) _command;

		FMCommand fmCmd = cliquesCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
		SetVariable cls = fmv.cliques() ; 
		
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
