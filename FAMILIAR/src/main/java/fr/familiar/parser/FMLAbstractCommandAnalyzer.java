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

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher abstract class to analyze a command
 * 
 */
public abstract class FMLAbstractCommandAnalyzer {

	protected boolean hasBeenParsed;
	protected NameSpace ns;

	/*
	 * the command to analyze and eval()
	 */
	protected Command _command;

	/*
	 * return type of the FML command (for type checking)
	 */
	protected RType _type;

	/*
	 * variable identifier assignment
	 */
	protected String _assigner = null;

	/*
	 * resulting variable from the eval()
	 */

	protected Variable _variable = null;

	/*
	 * environment in which the interpreter proceeds
	 */
	protected FMLCommandInterpreter _environment;
	
	

	/**
	 * @param cmd
	 *            the FML command to analyze
	 * @param ns
	 *            current namespace
	 * @param an
	 *            current environment within the command is evaluated
	 */
	public FMLAbstractCommandAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		this._command = cmd;
		this.ns = ns;
		this._environment = an;
	}

	/**
	 * @param cmd
	 *            the FML command to analyze
	 * @param assigner
	 *            the FML command is eventually assigned to a variable
	 *            identifier
	 * @param ns
	 *            current namespace
	 * @param an
	 *            current environment within the command is evaluated
	 */
	public FMLAbstractCommandAnalyzer(Command cmd, String assigner,	NameSpace ns, FMLCommandInterpreter an) {
		this(cmd, ns, an);
		this._assigner = assigner;
	}


	/*
	 * generic function eval() -> type checking -> assign() -> update views
	 */

	public void parse() {

		// eval and assign

		eval(); // evaluate

		// check that the valuation has been performed
		Variable v = getVariable();
		if (v == null) { // no value computed
			if (getType() != RType.VOID) { // and we expect some thing
				FMLShell.getInstance().setError("NULL ");
				return;
			}
		}



	}

	

	

	

	/**
	 * Simply evaluate an FML command (the right side) (the result is then
	 * eventually assigned to a variable identifier)
	 */
	public abstract void eval();

	/**
	 * @return the return type of the expression
	 */
	public abstract RType getType(); // return types can be deduced at runtime,
										// e.g., load *** (rule or fm)

	public void setHasBeenParsed(boolean hasBeenParsed) {
		this.hasBeenParsed = hasBeenParsed;
	}

	public boolean isHasBeenParsed() {
		return hasBeenParsed;
	}

	protected void setType(RType type) {
		this._type = type;
	}

	public void setCmd(Command cmd) {
		this._command = cmd;
	}

	public Command getCmd() {
		return _command;
	}




	/**
	 * @return the var
	 */
	public String getAssigner() {
		return _assigner;
	}

	/** get parsed variable */
	public Variable getVariable() {
		return this._variable;
	}

	public void setVariable(Variable v) {
		this._variable = v;
	}

	public void setEnvironment(FMLCommandInterpreter an) {
		this._environment = an;
	}

	public FMLCommandInterpreter getEnvironment() {
		return _environment;
	}

}
