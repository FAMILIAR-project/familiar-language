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

import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.FTCommand;
import org.xtext.example.mydsl.fml.RemoveFeature;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher remove a feature in a feature model returns true if the
 *         removal is successul, false otherwise
 * 
 *         see: examples/testing/ftoperations/remove.fmm
 */
public class RemoveAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public RemoveAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public RemoveAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.ft.parser.AbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.ft.parser.AbstractCommandAnalyzer#parse()
	 */
	@Override
	public void eval() {
		assert (_command instanceof RemoveFeature);

		RemoveFeature removeCmd = (RemoveFeature) _command;

		// TODO
		FTCommand ftToRemove = removeCmd.getFeature();
		FMLShell.getInstance().printDebugMessage(
				"evaluating feature to remove: " + ftToRemove);

		FeatureVariable fw = _environment.parseFTCommand(ftToRemove, null);
		FMLShell.getInstance().printDebugMessage("\t\t\t ft=" + fw);
		if (fw == null) {
			FMLShell.getInstance().printWarning("feature not found");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}
		final FeatureModelVariable fmv = fw.getFeatureModel();
		assert (fmv != null);
		assert (fw != null);

		FeatureModel<String> fm = fmv.getFm(); // .clone(); // very important!
		FeatureGraph<String> fgraph = fm.getDiagram();

		/****** we can perform renaming! *******/

		// or at least try to do it (features should exist!)
		Set<String> features = fm.features();
		String feature2Remove = "";
		FeatureNode<String> node2Remove = null;
		if (fw != null)
			feature2Remove = fw.getFtName();

		try {
			node2Remove = fgraph.findVertex(feature2Remove);
		} catch (IllegalArgumentException e) {
			FMLShell.getInstance().printWarning(
					"(old) " + feature2Remove + " does not exist (" + features
							+ ")");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}

		if (FeatureOperationAnalyzer.isRoot(node2Remove, fgraph)) {
			FMLShell.getInstance().printWarning(
					"It has no sense to remove the root feature ");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}

		/****** we can actually perform the removal *******/

		FMLShell.getInstance()
				.printDebugMessage(
						"Removing... " + node2Remove.toString() + " in "
								+ fmv.getVid());

		boolean b1 = fmv.removeFeature(node2Remove);

		setVariable(new BooleanVariable(_assigner, b1));

		// at this step, fmw has been removed

		/************** all configurations should be updated *******/
		Set<ConfigurationVariable> configurations = _environment
				.retrieveAllConfigurations(fmv);
		for (ConfigurationVariable cv : configurations)
				cv.changeFeatureModel(fmv);
			

		/*************** gdisplay *************/
		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (fmv == null)
						FMLShell.getInstance().printDebugMessage(
								"Unable to display after renaming (null)");
					fmv.gdisplay();

				}
			});

	}

}
