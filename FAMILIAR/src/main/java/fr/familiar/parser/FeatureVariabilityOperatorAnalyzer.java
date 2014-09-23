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
package fr.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.FeatureVariabilityOperator;

import fr.familiar.variable.RType;
import fr.familiar.variable.VariabilityOperatorVariable;

/**
 * @author mathieuacher FeatureVariabilityOperator : 'OP' '(' f=FeatureEdgeKind
 *         ')' ; Constructor to create a variable of type Feature Variability
 *         Operator deprecated since we use directly MAND/OPT/XOR/OR without OP
 *         ( ) construction
 */

public class FeatureVariabilityOperatorAnalyzer extends
		FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public FeatureVariabilityOperatorAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public FeatureVariabilityOperatorAnalyzer(Command cmd, String var,
			NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.VARIABILITY_OPERATOR; // TODO: or String?
	}

	@Override
	public void eval() {
		FeatureVariabilityOperator fvopCmd = (FeatureVariabilityOperator) _command;
		FeatureEdgeKind fek = fvopCmd.getVal();
		setVariable(new VariabilityOperatorVariable(_assigner, fek));

	}

}
