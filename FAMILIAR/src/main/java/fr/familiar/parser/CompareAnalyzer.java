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

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.Compare;
import org.xtext.example.mydsl.fml.FMCommand;

import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.StringVariable;

/**
 * @author mathieuacher compare two feature models see:
 *         examples/testing/compare/compare.fmm
 */
public class CompareAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public CompareAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public CompareAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.STRING; // to simplify
	}

	@Override
	public void eval() {
		assert (_command instanceof Compare);
		Compare cmpCmd = (Compare) _command;

		FMCommand fmLCmd = cmpCmd.getFm_left();
		FeatureModelVariable lfmw = _environment.parseFMCommand((Command) fmLCmd, null, null);

		FMCommand fmRCmd = cmpCmd.getFm_right();
		FeatureModelVariable rfmw = _environment.parseFMCommand((Command) fmRCmd, null, null);

		Comparison rcomp =  lfmw.compare(rfmw);
		setVariable(new StringVariable(_assigner, rcomp));

	}

	

}
