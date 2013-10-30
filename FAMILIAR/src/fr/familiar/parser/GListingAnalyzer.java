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

import java.util.List;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.GListing;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * I think the original idea was to display a multiplicy of feature models in one shot
 */
@Deprecated
public class GListingAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public GListingAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public GListingAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.VOID;
	}

	@Override
	public void eval() {
		assert (_command instanceof GListing);

		GListing glistingCmd = (GListing) _command;
		glistingCmd.getCmd();

		// generate the graphical bar with the list of variables

		List<Variable> lvars = _environment.getVariables();

		for (Variable variable : lvars) {

			variable.getIdentifier();
			variable.getRType();
			variable.getNS();

			// variable

			variable.getValue();

			if (variable instanceof FeatureModelVariable) {

				// find the configurations associated
				// List<ConfigurationVariable> ...

			}

		}

	}

}
