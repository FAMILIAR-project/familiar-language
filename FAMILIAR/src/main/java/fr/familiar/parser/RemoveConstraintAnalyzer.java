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

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.RemoveConstraint;

import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;

public class RemoveConstraintAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public RemoveConstraintAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public RemoveConstraintAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.BOOLEAN ; 
	}

	@Override
	/**
	 *  add an internal constraint to a feature model 
	 *  return false if some features do not long to the feature model
	 *  true otherwise
	 *  
	 *  TODO: allows such notation: fm1.C -> fm1.D
	 */
	public void eval() {

		assert (_command instanceof RemoveConstraint);

		RemoveConstraint rmCstCmd = (RemoveConstraint) _command;

		/************** bind features to the feature model **************/

		
		FMCommand fmCmd = rmCstCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);

		ConstraintVariable cv = _environment.parseConstraint(rmCstCmd.getCst(), null);
		boolean bound = fmv.removeConstraint(cv.getConstraint());
		/* DEPRECATED
		 * Expression<String> expression = cv.getConstraint() ;
		ConstraintInternBinder binder = new ConstraintInternBinder(expression);
		boolean bound = binder.remove(fmv);*/

		setVariable(new BooleanVariable(_assigner, bound));			
		

	}


}
