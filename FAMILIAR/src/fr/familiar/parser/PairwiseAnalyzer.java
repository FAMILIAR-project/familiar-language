package fr.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.PairwiseCommand;

import fr.familiar.operations.PacogenLauncher;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;

public class PairwiseAnalyzer extends FMLAbstractCommandAnalyzer {
	


	public PairwiseAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void eval() {
		int _min ;
		int _partial ;
	PairwiseCommand currpwCmd = (PairwiseCommand) _command ;
	
	FeatureModelVariable currmodel = 	_environment.parseFMCommand(currpwCmd.getFm(), null, null)	;	
	if(currpwCmd.getMinimization() == null)
	{
		_min = 0 ;
	} else
	{
		IntegerVariable min =	_environment.parseIntegerCmd(currpwCmd.getMinimization(), null) ;	
		_min = min.getV() ;
	}



	if(currpwCmd.getPartial() == null)
	{
		_partial = 0 ;
	}else
	{
		IntegerVariable partial =	_environment.parseIntegerCmd(currpwCmd.getPartial(), null) ;	
		_partial = partial.getV();
	}
		
	PacogenLauncher pc = new PacogenLauncher(currmodel,_min, _partial) ;
	pc.launchPacogen() ;
	
	//FMLShell.getInstance().prin
	setVariable(pc.getTestConfig());
	}

	@Override
	public RType getType() {
		
		return RType.SET;
	}

}
