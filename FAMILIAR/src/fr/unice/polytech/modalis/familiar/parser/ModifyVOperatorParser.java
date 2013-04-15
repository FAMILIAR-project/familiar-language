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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.xtext.example.mydsl.fML.AlternativeEdit;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.MandatoryEdit;
import org.xtext.example.mydsl.fML.ModifyVOperator;
import org.xtext.example.mydsl.fML.OptionalEdit;
import org.xtext.example.mydsl.fML.OrEdit;
import org.xtext.example.mydsl.fML.SetCommand;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class ModifyVOperatorParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public ModifyVOperatorParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ModifyVOperatorParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (getCmd() instanceof ModifyVOperator);
		ModifyVOperator vopCmd = (ModifyVOperator) getCmd();

		if (vopCmd instanceof MandatoryEdit) {
			setMandatory(((MandatoryEdit) vopCmd).getFt());
		} else if (vopCmd instanceof OptionalEdit) {
			setOptional(((OptionalEdit) vopCmd).getFt());
		} else if (vopCmd instanceof AlternativeEdit) {
			SetCommand setCmd = ((AlternativeEdit) vopCmd).getFts();
			SetVariable sv = _environment.parseSetCommand(setCmd, null);

			Set<Variable> fts = sv.getVars();
			FeatureModelVariable fmInput = null;
			Set<String> features = new HashSet<String>();
			for (Variable v : fts) {
				if (v instanceof RefVariable)
					v = ((RefVariable) v).getReference();
				if (!(v instanceof FeatureVariable)) {
					FMLShell.getInstance().printError("No feature! v=" + v);
					return;
				}
				FeatureVariable fv = (FeatureVariable) v;
				fmInput = fv.getFeatureModel();
				features.add(fv.getFtName());
			}

			boolean b = ModifyVOperatorParser.setAlternative(features,
					fmInput.getFm());
			setVariable(new BooleanVariable(getAssigner(), b));
		} else if (vopCmd instanceof OrEdit) {
			SetCommand setCmd = ((OrEdit) vopCmd).getFts();
			SetVariable sv = _environment.parseSetCommand(setCmd, null);

			Set<Variable> fts = sv.getVars();
			FeatureModelVariable fmInput = null;
			Set<String> features = new HashSet<String>();
			for (Variable v : fts) {
				if (v instanceof RefVariable)
					v = ((RefVariable) v).getReference();
				if (!(v instanceof FeatureVariable)) {
					FMLShell.getInstance().printError("No feature! v=" + v);
					return;
				}
				FeatureVariable fv = (FeatureVariable) v;
				fmInput = fv.getFeatureModel();
				features.add(fv.getFtName());
			}

			boolean b = ModifyVOperatorParser.setOr(features, fmInput.getFm());
			setVariable(new BooleanVariable(getAssigner(), b));

		} else {
			// should not be reached
			FMLShell.getInstance().setError(
					"Unexpected operation about variability operators "
							+ vopCmd);
			return;
		}

	}

	public static boolean setAlternative(Set<String> fts,
			FeatureModel<String> fm) {
		return setGroup(fts, fm, FeatureEdge.XOR);
	}

	public static boolean setOr(Set<String> fts, FeatureModel<String> fm) {
		return setGroup(fts, fm, FeatureEdge.OR);
	}

	private void setOptional(FTCommand ftCmd) {
		final FeatureVariable fv = _environment.parseFTCommand(ftCmd, null);
		boolean b = fv.setOptionalStatus();
		setVariable(new BooleanVariable(getAssigner(), b));

		// at this step, fv has been set up
		/*************** gdisplay *************/
		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (fv.getFeatureModel() == null)
						FMLShell.getInstance()
								.printDebugMessage(
										"Unable to display after setting optional (null)");
					fv.getFeatureModel().gdisplay();

				}
			});
	}

	private void setMandatory(FTCommand ftCmd) {
		final FeatureVariable fv = _environment.parseFTCommand(ftCmd, null);
		boolean b = fv.setMandatoryStatus();
		setVariable(new BooleanVariable(getAssigner(), b));

		// at this step, fv has been set up
		/*************** gdisplay *************/
		if (FMLShell.getInstance().isEclipseInteractiveMode()
				|| FMLShell.getInstance().isStepByStep())
			Display.getDefault().asyncExec(new Runnable() {

				@Override
				public void run() {
					if (fv.getFeatureModel() == null)
						FMLShell.getInstance()
								.printDebugMessage(
										"Unable to display after setting mandatory (null)");
					fv.getFeatureModel().gdisplay();

				}
			});
	}

	/**
	 * Set the alternative status (Xor-group) to a set of features that belong
	 * to fm
	 * 
	 * @param fts
	 * @param fm
	 *            TODO Unit testing
	 */
	public static boolean setGroup(Set<String> fts, FeatureModel<String> fm,
			int type) {

		assert (type == FeatureEdge.XOR || type == FeatureEdge.OR);

		FeatureGraph<String> fdiagram = fm.getDiagram();
		FeatureNode<String> ftParent = commonParent(fts, fm);

		if (ftParent == null)
			return false;

		Set<FeatureNode<String>> sources = new HashSet<FeatureNode<String>>();

		for (String ft : fts) {

			try {
				FeatureNode<String> fnode = fdiagram.findVertex(ft);
				sources.add(fnode);
				Collection<FeatureEdge> edges = fdiagram.outgoingEdges(fnode,
						FeatureEdge.MANDATORY | FeatureEdge.GROUPS);
				fdiagram.removeAllEdges(edges);

			} catch (Exception e) {
				return false;
			}
		}

		assert (ftParent != null);
		fdiagram.addEdge(sources, ftParent, type);

		return true;

	}

	/**
	 * @param fts
	 * @param fm
	 * @return common parent of features fts
	 */
	private static FeatureNode<String> commonParent(Set<String> fts,
			FeatureModel<String> fm) {
		for (String ft : fts) {
			return fm.getDiagram().findVertex(
					new FeatureVariable(ft, new FeatureModelVariable("", fm))
							.parent().getFtName());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

}
