package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ComputeFGroups;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.GetFGroups;
import org.xtext.example.mydsl.fML.KindOfComputeGroups;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

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
