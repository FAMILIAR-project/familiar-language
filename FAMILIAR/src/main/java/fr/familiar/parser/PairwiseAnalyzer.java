/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

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
