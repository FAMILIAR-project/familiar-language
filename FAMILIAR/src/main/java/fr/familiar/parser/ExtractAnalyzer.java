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
import org.xtext.example.mydsl.fml.Extract;
import org.xtext.example.mydsl.fml.FTCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import gsd.synthesis.FeatureModel;

/**
 * @author mathieuacher
 * 
 *         extract operator (returns a feature model given a specified feature
 *         in a targeted feature model) see:
 * 
 *         examples/testing/ftoperations/extract.fmm
 * 
 */
public class ExtractAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public ExtractAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ExtractAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}

	@Override
	public void eval() {
		assert (_command instanceof Extract);
		Extract extractCmd = (Extract) _command;

		// TODO
		FTCommand ftToExtract = extractCmd.getRootfeature();
		FMLShell.getInstance().printDebugMessage(
				"evaluating feature to extract: " + ftToExtract);

		FeatureVariable fw = _environment.parseFTCommand(ftToExtract, null);
		FMLShell.getInstance().printDebugMessage("\t\t\t ft=" + fw);
		if (fw == null) {
			FMLShell.getInstance().printWarning("feature not found");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}
		FeatureModelVariable fmv = fw.getFeatureModel();
		assert (fmv != null);
		assert (fw != null);

		FeatureModel<String> newFM = fmv.extract(fw.getFtName());
		if (newFM == null) {
			// strange no? boolean and FM?
			setVariable(new BooleanVariable(_assigner, false));
		}

		final FeatureModelVariable fmwr = new FeatureModelVariable(_assigner,
				newFM);

		setVariable(fmwr);

	}

}
