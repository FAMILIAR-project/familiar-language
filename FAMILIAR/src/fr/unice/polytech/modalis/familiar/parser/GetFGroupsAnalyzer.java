package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.GetFGroups;
import org.xtext.example.mydsl.fML.KindOfGetGroups;

import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

public class GetFGroupsAnalyzer extends FMLAbstractCommandAnalyzer {

	public GetFGroupsAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
	}

	public GetFGroupsAnalyzer(Command cmd, NameSpace ns, String var, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	@Override
	public void eval() {
		assert (getCmd() instanceof GetFGroups);
		GetFGroups gCmd = (GetFGroups) getCmd();

		FMCommand fmCmd = gCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);
	
		KindOfGetGroups kindOf = gCmd.getKindOfGroups() ; 
		Set<FGroup> fgroups = new HashSet<FGroup>();
		if (kindOf == KindOfGetGroups.OR) {
			fgroups = fmv.getOrGroups() ; 
		}
		else if (kindOf == KindOfGetGroups.XOR) {
			fgroups = fmv.getXorGroups() ; 
		}
		else if (kindOf == KindOfGetGroups.MUTEX) {
			fgroups = fmv.getMutexGroups() ; 
		}
		else {
			FMLShell.getInstance().printError("Unable to determine the kind of get (feature groups) " + kindOf);
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
