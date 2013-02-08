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

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FTCommand;
import org.xtext.example.mydsl.fML.FeatureEdgeKind;
import org.xtext.example.mydsl.fML.FeatureOperation;
import org.xtext.example.mydsl.fML.impl.AncestorFeatureImpl;
import org.xtext.example.mydsl.fML.impl.ChildrenFeatureImpl;
import org.xtext.example.mydsl.fML.impl.DescendantFeatureImpl;
import org.xtext.example.mydsl.fML.impl.FMFeatureImpl;
import org.xtext.example.mydsl.fML.impl.FeatureOperatorImpl;
import org.xtext.example.mydsl.fML.impl.NameFeatureImpl;
import org.xtext.example.mydsl.fML.impl.ParentFeatureImpl;
import org.xtext.example.mydsl.fML.impl.SiblingFeatureImpl;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableBDDFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.StringVariable;
import fr.unice.polytech.modalis.familiar.variable.VariabilityOperatorVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.familiar.variable.VariableIdentifier;
import fr.unice.polytech.modalis.familiar.variable.VariableImpl;
import fr.unice.polytech.modalis.familiar.variable.featureide.FeatureModelVariableConstraints;
import fr.unice.polytech.modalis.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

public class FeatureOperationAnalyzer extends FMLAbstractCommandAnalyzer {

	public FeatureOperationAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return _type;
	}

	@Override
	public void eval() {

		assert (getCmd() instanceof FeatureOperation);
		FeatureOperation ftopCmd = (FeatureOperation) getCmd();

		// TODO
		FTCommand fture = ftopCmd.getFeature();
		FMLShell.getInstance().printDebugMessage(
				"evaluating feature to OP: " + fture);
		FeatureVariable fv = _environment.parseFTCommand(fture, null);
		FMLShell.getInstance().printDebugMessage("\t\t\t ft=" + fv);

		if (fv == null) {
			FMLShell.getInstance().printWarning("feature not found");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}
		FeatureModelVariable fmw = fv.getFmw();
		assert (fmw != null);
		assert (fv != null);
		
		// FIXME refactor
		// FIXME @FeatureIDE
		
		if (fmw instanceof FeatureModelVariableSATFormula 
				|| fmw instanceof FeatureModelVariableBDDFormula
				|| fmw instanceof FeatureModelVariableConstraints)
			return ;

		final FeatureModel<String> fm = fmw.getFm(); // .clone(); // very
														// important!
		final FeatureGraph<String> fgraph = fm.getDiagram();

		/****** we can perform renaming! *******/

		// or at least try to do it (features should exist!)
		Set<String> features = fm.features();
		String feature = fv.getFtName();

		FeatureNode<String> oldNode = null;
		try {
			oldNode = fgraph.findVertex(feature);
		} catch (IllegalArgumentException e) {
			FMLShell.getInstance().printWarning(
					"(old) " + feature + " does not exist (" + features + ")");
			setVariable(new BooleanVariable(_assigner, false));
			return;
		}

		if (fmw == null) {
			operationFails(ftopCmd); // each (feature) operation has a specific
										// behaviour
			// (e.g., return values) when operations 'fail'
			return;
		}

		assert (fmw != null);

		FeatureNode<String> fn = null;
		if (fv == null)
			fn = fgraph.findVertex(feature);
		else
			fn = fgraph.findVertex(fv.getFtName());

		assert (fn != null);
		FMLShell.getInstance().printDebugMessage(
				"feature to treat: " + fn.getFeature());

		// String feature = null ;
		EObject eop = ftopCmd.getOp();
		FMLShell.getInstance().printDebugMessage(
				"feature operation OP: " + eop.toString());
		Variable v = null;

		if (eop instanceof ParentFeatureImpl) {

			setType(RType.FEATURE);
			v = fv.parent();
			// v = new FeatureVariable(var, parent.toString(), fmw);

		} else if (eop instanceof NameFeatureImpl) {
			String val = fn.getFeature();
			v = new StringVariable(getAssigner(), val);
			setType(RType.STRING);

		}

		else if (eop instanceof FMFeatureImpl) {

			v = new RefVariable(fmw, new VariableIdentifier(getAssigner()));

			// v = fmw.copy(); // TO DISCUSS
			// if (hasVar())
			// v.setName(getVar());
			setType(RType.REFERENCE);

		}

		else if (eop instanceof ChildrenFeatureImpl) {

			v = fv.children();
			setType(RType.SET);
		}

		else if (eop instanceof AncestorFeatureImpl) {

			v = fv.ancestors();
			setType(RType.SET);
		}

		else if (eop instanceof DescendantFeatureImpl) {

			v = fv.descendants();
			setType(RType.SET);
		}

		else if (eop instanceof SiblingFeatureImpl) {

			if (isRoot(fn, fgraph)) {
				FMLShell.getInstance().printWarning("root has no siblings!");
				operationFails(ftopCmd);
				return;
			}

			Set<FeatureNode<String>> parents = fgraph.parents(fn);

			if (parents.size() > 1)
				FMLShell.getInstance().printWarning(
						"more than one parent: " + parents.toString());
			if (parents.size() == 0) {
				FMLShell.getInstance().setError("No parent... ");
				return;
			}
			FeatureNode<String> parent = parents.iterator().next(); // first one

			setType(RType.SET);

			Set<FeatureNode<String>> chsParent = fgraph.children(parent);
			Set<Variable> sibling = new HashSet<Variable>();
			for (FeatureNode<String> fnode : chsParent) {
				if (!fnode.equals(fn)) {
					String explicitName = ""
							+ VariableIdentifier.completeName(fmw.getVid())
							+ FeatureVariable.SEPARATOR + fnode.getFeature();
					FeatureVariable ftw = new FeatureVariable(explicitName,
							fnode.getFeature(), fmw);
					sibling.add(ftw);
				}
			}

			v = new SetVariable(sibling, getAssigner());

		}

		else if (eop instanceof FeatureOperatorImpl) {

			/*
			 * Set<FeatureNode<String>> parents = fgraph.parents(fn);
			 * FMShell.getInstance().printDebugMessage(parents.toString()); if
			 * (parents.size() > 1)
			 * FMShell.getInstance().printWarning("more than one parent: " +
			 * parents.toString()); if (parents.size() == 0) {
			 * FMShell.getInstance().printError("No parent... "); return ; }
			 * FeatureNode<String> parent = parents.iterator().next(); // first
			 * one if (parent.isTop())
			 * FMShell.getInstance().printWarning("root has no parent!"); //
			 * manage error!
			 */

			if (isRoot(fn, fgraph)) {
				FMLShell.getInstance().printWarning("root has no operator!");
				operationFails(ftopCmd);
				return;
			}

			// TODO: REFACTORING needed

			Collection<FeatureEdge> edgesFromParents = fgraph.outgoingEdges(fn);
			FMLShell.getInstance().printDebugMessage(
					"outgoing: " + edgesFromParents);

			FeatureEdgeKind fk = FeatureEdgeKind.OPTIONAL; // default
			for (FeatureEdge fe : edgesFromParents) {

				if (fe.getType() == FeatureEdge.MANDATORY)
					fk = FeatureEdgeKind.MANDATORY;
				else if (fe.getType() == FeatureEdge.OR)
					fk = FeatureEdgeKind.OR;
				else if (fe.getType() == FeatureEdge.XOR)
					fk = FeatureEdgeKind.ALTERNATIVE;
			}

			v = new VariabilityOperatorVariable(getAssigner(), fk);

		}

		else {
			FMLShell.getInstance().printTODO();
			return;
		}

		setVariable(v);

	}

	public static boolean isRoot(FeatureNode<String> fn,
			FeatureGraph<String> fgraph) {
		if (fn.isTop())
			return true;

		Set<FeatureNode<String>> parents = fgraph.parents(fn);
		if (parents.size() == 0)
			return true;

		FeatureNode<String> parent = parents.iterator().next(); // first one
		if (parent.isTop())
			return true;

		return false;

	}

	private void operationFails(FeatureOperation ftopCmd) {

		FMLShell.getInstance().printDebugMessage(
				"operation fails (feature operation): " + ftopCmd);
		EObject eop = ftopCmd.getOp();

		Variable v = null;

		if (eop instanceof ParentFeatureImpl) {

			setType(RType.FEATURE);
			v = VariableImpl.mkNull(getAssigner(), RType.FEATURE);

		} else if (eop instanceof NameFeatureImpl) {
			setType(RType.STRING);
			v = VariableImpl.mkNull(getAssigner(), RType.STRING);
		}

		else if (eop instanceof FMFeatureImpl) {
			setType(RType.FEATURE_MODEL);
			v = VariableImpl.mkNull(getAssigner(), RType.FEATURE_MODEL);
		}

		else if (eop instanceof ChildrenFeatureImpl) {
			setType(RType.SET);
			v = VariableImpl.mkNull(getAssigner(), RType.SET);
		}

		else if (eop instanceof FeatureOperatorImpl) {
			setType(RType.VARIABILITY_OPERATOR);
			v = VariableImpl.mkNull(getAssigner(), RType.VARIABILITY_OPERATOR);
		}

		else {
			FMLShell.getInstance().printTODO();
			return;
		}

		if (v == null) {
			FMLShell.getInstance().setError(
					"unable to perform feature operation... ");
			return;
		}

		setVariable(v);

	}

}
