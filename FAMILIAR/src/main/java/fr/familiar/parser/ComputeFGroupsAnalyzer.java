/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ComputeFGroups;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.GetFGroups;
import org.xtext.example.mydsl.fml.KindOfComputeGroups;

import fr.familiar.experimental.FGroup;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

public class ComputeFGroupsAnalyzer extends FMLAbstractCommandAnalyzer {

	public ComputeFGroupsAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
	}

	public ComputeFGroupsAnalyzer(Command cmd, NameSpace ns, String var, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}
	
	@Override
	public void eval() {
		assert (getCmd() instanceof GetFGroups);
		ComputeFGroups gCmd = (ComputeFGroups) getCmd();

		FMCommand fmCmd = gCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
	
		KindOfComputeGroups kindOf = gCmd.getKindOfGroups() ; 
		Set<FGroup> fgroups = new HashSet<FGroup>();
		if (kindOf == KindOfComputeGroups.OR) {
			fgroups = fmv.computeOrGroups() ; 
		}
		else if (kindOf == KindOfComputeGroups.XOR) {
			fgroups = fmv.computeXorGroups() ; 
		}
		else if (kindOf == KindOfComputeGroups.MUTEX) {
			fgroups = fmv.computeMutexGroups() ; 
		}
		else {
			FMLShell.getInstance().printError("Unable to determine the kind of compute (feature groups) " + kindOf);
			return ; 
		}
		
		// FIXME polyphormism issues?
		Set<Variable> vars = new HashSet<Variable>();
		for (FGroup fgroup : fgroups) {
			vars.add(fgroup);
		}		
		
		setVariable(new SetVariable(vars));

	}

	@Override
	public RType getType() {
		return RType.SET ; 
	}

}
