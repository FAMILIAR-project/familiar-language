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

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.Insert;
import org.xtext.example.mydsl.fML.VariabilityOpCommand;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.VariabilityOperatorVariable;

/**
 * @author mathieuacher insert a feature model into a feature model (target)
 *         return true if the insertion succeeds (and precondition respected)
 *         see: examples/testing/insert.fmm examples/testing/insert2.fmm (should
 *         be fixed)
 */

public class InsertAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public InsertAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public InsertAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

	@Override
	public void eval() {
		assert (_command instanceof Insert);
		Insert insertCmd = (Insert) _command;

		// insert (aspectFeature: FeatureModel, joinpointFeature: Feature,
		// operator: Operator)

		FMCommand concernFM = insertCmd.getAspectfm();
		FMLShell.getInstance().printDebugMessage(
				"evaluating FM - aspect to insert: " + concernFM);
		FeatureModelVariable aspectFMW = _environment.parseFMCommand((Command) concernFM, null, null);

		// FMLShell.getInstance().printDebugMessage("aspectFMW=" +
		// aspectFMW.getSpecificValue()) ;

		FTCommand jpFeature = insertCmd.getBaseft();
		FMLShell.getInstance().printDebugMessage(
				"evaluating feature in the base: " + jpFeature);
		// retrieve baseFM (using jpFeature)

		FeatureVariable fnw = _environment.parseFTCommand(jpFeature, null);
		FeatureModelVariable baseFMW = fnw.getFeatureModel();
		// FMLShell.getInstance().printDebugMessage("fnw= " +
		// fnw.getSpecificValue()) ;
		if (baseFMW == null) {
			FMLShell.getInstance()
					.setError(
							"Insertion failed since base feature model has not been recognized");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}
		
		
		VariabilityOpCommand vop = insertCmd.getOp();
		VariabilityOperatorVariable varVOP = _environment.parseVOP(vop, null);

		assert (varVOP instanceof VariabilityOperatorVariable);
		FeatureEdgeKind operator = varVOP.getFek();
		
		
		assert (baseFMW != null);
		assert (aspectFMW != null);
		
		boolean b = baseFMW.insert(aspectFMW, fnw, operator);

	
		setVariable(new BooleanVariable(_assigner, b));

	}

}
