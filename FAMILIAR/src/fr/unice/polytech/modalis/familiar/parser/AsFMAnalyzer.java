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

import org.xtext.example.mydsl.fML.AsFM;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ConfigurationCommand;

import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import gsd.synthesis.FeatureModel;

/**
 * @author mathieuacher cast a configuration as a feature model
 */
public class AsFMAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public AsFMAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public AsFMAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public void eval() {
		assert (_command instanceof AsFM);
		AsFM asCmd = (AsFM) _command;

		ConfigurationCommand confCmd = asCmd.getConf();
		ConfigurationVariable var = _environment
				.parseConfigurationCommand(confCmd, null);

		ConfigurationVariable cv = (ConfigurationVariable) var;

		FeatureModel<String> fm = cv.asFM();
		
		setVariable(new FeatureModelVariable(getAssigner(), fm));

	}

}
