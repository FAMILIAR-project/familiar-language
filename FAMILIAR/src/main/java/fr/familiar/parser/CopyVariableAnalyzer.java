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

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.CopyVariable;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class CopyVariableAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public CopyVariableAnalyzer(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public CopyVariableAnalyzer(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {

		FMLShell.getInstance()
				.printDebugMessage("copying variable " + getCmd());
		CopyVariable manageCmd = (CopyVariable) getCmd();

		String vid = manageCmd.getVid();
		Variable v = null;
		try {
			v = _environment.getVariable(vid);
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError(
					"unable to copy - variable does not exist: " + vid);
			return;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError(
					"unable to copy (ambigous) - variable does not exist: "
							+ vid);
			return;
		}

		if (v instanceof RefVariable) // free variable
			v = ((RefVariable) v).getValueReference();
		Variable newVar = v.copy();
		newVar.setIdentifier(_assigner);
		newVar.setNS(this.ns); // TODO: testing
		// addVariable(newVar);
		setType(newVar.getRType());

		setVariable(newVar);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return _type;
	}

	public void setType(RType type) {
		this._type = type;
	}

}
