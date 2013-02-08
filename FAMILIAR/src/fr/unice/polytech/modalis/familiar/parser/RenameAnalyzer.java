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

import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.RenameFeature;
import org.xtext.example.mydsl.fML.StrCommand;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.ExpressionUtility;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.StringVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 *         rename a feature in a feature model returns true if the renaming is
 *         successul, false otherwise
 * 
 *         see: examples/testing/ftoperations/rename.fmm
 */
public class RenameAnalyzer extends FMLAbstractCommandAnalyzer {

	private FeatureModelVariable fmw;

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public RenameAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public RenameAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

	@Override
	public void eval() {

		// TODO: move this code into fmv

		RenameFeature renameCmd = (RenameFeature) _command;
		FTCommand ftToRename = renameCmd.getFeature();
		FMLShell.getInstance().printDebugMessage(
				"evaluating feature to rename: " + ftToRename);

		FeatureVariable fw = _environment.parseFTCommand(ftToRename, null);
		FMLShell.getInstance().printDebugMessage("\t\t\t ft=" + fw);
		if (fw == null) {
			FMLShell.getInstance().printWarning("feature not found");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}
		fmw = fw.getFmw();
		assert (fmw != null);
		assert (fw != null);

		FeatureModel<String> fm = fmw.getFm(); // .clone(); // very important!
		FeatureGraph<String> fgraph = fm.getDiagram();

		/****** we can perform renaming! *******/

		// or at least try to do it (features should exist!)
		Set<String> features = fm.features();
		String feature = "";
		FeatureNode<String> oldNode = null;
		if (fw != null)
			feature = fw.getFtName();

		try {
			oldNode = fgraph.findVertex(feature);
		} catch (IllegalArgumentException e) {
			FMLShell.getInstance().printWarning(
					"(old) " + feature + " does not exist (" + features + ")");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}

		StrCommand strExpr = renameCmd.getFeatureNew();
		FMLShell.getInstance().printDebugMessage(
				"evaluating str command: " + strExpr);

		StringVariable newFeatureVar = _environment.parseStringCommand(strExpr,
				null);
		String newFeatureName = newFeatureVar.getValue();
		// an.stringOrVariable(null);

		try {
			fgraph.findVertex(newFeatureName);
			// should not be executed if does not exist
			FMLShell.getInstance().printWarning(
					"(new) " + newFeatureName + " already exists (" + features
							+ ")");
			setVariable(new BooleanVariable(_assigner, false));

			return;
		} catch (IllegalArgumentException e) {
			// not found, cool!
		}

		// we can actually perform the renaming

		FeatureNode<String> newNode = new FeatureNode<String>(newFeatureName);
		fgraph.replaceVertex(oldNode, newNode);

		Set<Expression<String>> constraints = fm.getConstraints();
		for (Expression<String> expression : constraints) {
			ExpressionUtility.replaceOccurenceInExpression(
					oldNode.getFeature(), newFeatureName, expression);
		}

		setVariable(new BooleanVariable(_assigner, true));

		// at this step, fmw has been renamed

		/************** all configurations should be updated *******/
		Set<ConfigurationVariable> configurations = _environment
				.retrieveAllConfigurations(fmw);
		for (ConfigurationVariable cv : configurations)
				cv.changeFeatureModel(fmw);
			

		/*************** gdisplay *************/
		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (fmw == null)
						FMLShell.getInstance().printDebugMessage(
								"Unable to display after renaming (null)");
					fmw.gdisplay();

				}
			});

	}

}
