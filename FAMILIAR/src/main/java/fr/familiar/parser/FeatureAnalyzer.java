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
import org.xtext.example.mydsl.fml.FeatureOperation;
import org.xtext.example.mydsl.fml.IdentifierExpr;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author macher1
 *
 */
public class FeatureAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public FeatureAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public FeatureAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		Variable v = null;
		FMLAbstractCommandAnalyzer pars = null;
		
		Command ftCmd = _command ; 
		String var = _assigner ; 
				
		if (ftCmd instanceof IdentifierExpr) {
			v = _environment.parse((Command) ftCmd, var);
		} else if (ftCmd instanceof CopyVariable) {
			v = _environment.parse((Command) ftCmd, var);
		}

		/*************** Feature Operation ***************/
		else if (ftCmd instanceof FeatureOperation) {
			// TODO
			// only parent operation
			pars = new FeatureOperationAnalyzer((Command) ftCmd, var, ns, _environment);
			pars.parse();
			v = pars.getVariable();
		}

		else {
			FMLShell.getInstance().printTODO("unknown FTCommand " + ftCmd);
			return ;
		}

		if (v instanceof RefVariable) {
			v = ((RefVariable) v).getValueReference();
		}

		if (!(v instanceof FeatureVariable)) {
			FMLShell.getInstance().setError("feature expected but v=" + v);
			return ;
		}
		FeatureVariable ft = (FeatureVariable) v;
		setVariable (ft);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE ; 
	}

}
