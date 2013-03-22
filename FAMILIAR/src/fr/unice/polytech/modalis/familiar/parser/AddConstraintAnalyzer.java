/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.AddConstraint;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;

import fr.unice.polytech.modalis.familiar.operations.ConstraintInternBinder;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.ConstraintVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import gsd.synthesis.Expression;

/**
 * @author mathieuacher add an internal constraint to a feature model raise an
 *         error if features belong to different feature models: use aggregate
 *         TODO: tests
 * 
 */
public class AddConstraintAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public AddConstraintAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public AddConstraintAnalyzer(Command cmd, String var, NameSpace ns,
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

		assert (_command instanceof AddConstraint);

		AddConstraint addCstCmd = (AddConstraint) _command;

		/************** bind features to the feature model **************/

		
		FMCommand fmCmd = addCstCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);

		ConstraintVariable cv = _environment.parseConstraint(addCstCmd.getCst(), null);
		
		boolean bound = fmv.addConstraint(cv);
		
		// DEPRECATED version
		/*
		Expression<String> expression = cv.getConstraint() ;
		ConstraintInternBinder binder = new ConstraintInternBinder(expression);
		boolean bound = binder.bind(fmv);
		*/
		
		

		setVariable(new BooleanVariable(_assigner, bound));
			
	
			
		

	}

}
