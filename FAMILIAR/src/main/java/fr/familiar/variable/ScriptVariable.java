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
package fr.familiar.variable;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.xtext.example.mydsl.fml.ScriptCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableManager;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.parser.NameSpace;

/**
 * @author mathieuacher
 * 
 */
public class ScriptVariable extends VariableImpl {

	private List<ScriptCommand> lcmds;

	/**
	 * @return the commands to be executed
	 */
	public List<ScriptCommand> getLcmds() {
		return lcmds;
	}

	/**
	 * @param lcmds
	 *            the lcmds to set
	 */
	public void setLcmds(List<ScriptCommand> lcmds) {
		this.lcmds = lcmds;
	}

	/**
	 * 
	 */
	public ScriptVariable(String name, NameSpace ns, List<ScriptCommand> lcmds) {
		this.name = name;
		this.ns = ns;
		this.lcmds = lcmds;

		this.vid = new VariableIdentifier(name, ns);
	}

	/**
	 * 
	 */
	public ScriptVariable(String name, EList<ScriptCommand> eList) {
		this(name, NSFactory.mkEmpty(), eList);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.familiar.variable.VariableImpl#getRType()
	 */
	@Override
	public RType getRType() {
		return RType.SCRIPT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.familiar.variable.VariableImpl#copy()
	 */
	@Override
	public Variable copy() {
		List<ScriptCommand> cmds = new ArrayList<ScriptCommand>();
		for (ScriptCommand cmd : getLcmds()) {
			cmds.add(cmd);
		}
		return new ScriptVariable(name, ns, cmds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.variable.VariableImpl#setValue(modalis
	 * .polytech.unice.fr.familiar.variable.Variable)
	 */
	@Override
	public void setValue(Variable vari) {
		if (vari instanceof ScriptVariable) {
			ScriptVariable sw = (ScriptVariable) vari;
			setLcmds(sw.getLcmds());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.variable.VariableImpl#getSpecificValue
	 * ()
	 */
	@Override
	public String getSpecificValue() {
		StringBuilder sb = new StringBuilder();
		sb.append("" + name + "=[");
		for (ScriptCommand cmd : getLcmds()) {
			sb.append(cmd.toString() + " ; ");
		}
		sb.append("]");
		return sb.toString();
	}

	public void run() {
		run("");
	}

	public VariableManager run(String nsID) {
		
		return run (nsID, new ArrayList<Variable>());


	}

	public VariableManager run(String nsID, List<Variable> paramsVar) {

		FMLCommandInterpreter newEnv = new FMLCommandInterpreter(ns);
		for (Variable param : paramsVar) {
			newEnv.addVariable(param);
		}
		for (ScriptCommand scmd : getLcmds()) {
			newEnv.parse(scmd);
		}

		// change namespace
		NameSpace newNS = NSFactory.mkNS(nsID);
		for (Variable v : newEnv.getVariables()) {
			v.setNS(NSFactory.concatenateNS(newNS, v.getNS()));
		}

		// eradicate arguments
		return newEnv.getEnvironment();
		
		
		
	}

}
