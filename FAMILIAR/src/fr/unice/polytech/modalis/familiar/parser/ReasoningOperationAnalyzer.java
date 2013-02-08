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

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fML.AnalysisOperation;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.impl.AnalysisOperationImpl;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.variable.BooleanVariable;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.IntegerVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class ReasoningOperationAnalyzer extends FMLAbstractCommandAnalyzer {
	
	private static Logger _LOGGER = Logger.getLogger(ReasoningOperationAnalyzer.class);


	public ReasoningOperationAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	public ReasoningOperationAnalyzer(Command cmd, String var,
			FMLCommandInterpreter an) {
		this(cmd, var, NSFactory.mkEmpty(), an);
	}

	@Override
	public RType getType() {
		return _type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see modalis.polytech.unice.fr.ft.parser.CommandParser#parse()
	 */
	@Override
	public void eval() {

		assert (getCmd() instanceof AnalysisOperationImpl);
		parseAnalysisOperation((AnalysisOperationImpl) getCmd());

	}

	private void parseAnalysisOperation(AnalysisOperation cmd) {

		EObject ofm = cmd.getFm();
		String op = cmd.getOp();

		// can be a FMCommand or a ConfigurationCommand
		Variable v = _environment.parse((Command) ofm, null);

		if (v instanceof RefVariable)
			v = ((RefVariable) v).getValueReference();

		if (v instanceof FeatureModelVariable)
			performOperationsOnFM((FeatureModelVariable) v, op);
		else if (v instanceof ConfigurationVariable)
			performOperationsOnConfiguration((ConfigurationVariable) v, op);
		else {
			FMLShell.getInstance().setError(
					"feature model or configuration expected " + ofm);
			return;
		}

	}

	private void performOperationsOnFM(FeatureModelVariable fmv, String op) {

		

		// TODO : use directly fmw primitives
		if (op.equals("isValid")) {
			setType(RType.BOOLEAN);
			boolean validity = fmv.isValid();
			_LOGGER.debug(
					"satisfiable: " + validity + "");
			setVariable(new BooleanVariable(_assigner, validity));
			return;
		} else if (op.equals("counting")) {
			// implementation with FeatureIDE or can be directly
			// implemented using BDD (default)
			setType(RType.DOUBLE);
			double r = fmv.counting();
			setVariable(new DoubleVariable(_assigner, r));
			return;
		} else if (op.equals("configs")) {
			setType(RType.SET);
			Set<Variable> confs = fmv.configs();
			setVariable(new SetVariable(confs, getAssigner()));
			return;
		} else if (op.equals("nbFeatures")) {
			setType(RType.INTEGER);
			int n = fmv.nbFeatures();
			setVariable(new IntegerVariable(_assigner, n));
			return;
		}

		else if (op.equals("features")) {
			setType(RType.SET);
			SetVariable sv = fmv.features();

			setVariable(new SetVariable(sv.getVars(), _assigner));
			return;
		}

		else if (op.equals("root")) { // FIXME use root() of FMVAriable
			setType(RType.FEATURE);
			FeatureGraph<String> fgraph = fmv.getFm().getDiagram();
			FeatureNode<String> ntop = fgraph.getTopVertex();
			Set<FeatureNode<String>> childs = fgraph.children(ntop);
			FeatureNode<String> noderoot = childs.iterator().next(); // first
																		// one!
			String rootName = noderoot.toString();

			setVariable(new FeatureVariable(_assigner, rootName, fmv));
			return;
		} else {
			FMLShell.getInstance().setError("Unknown operation");
			return;
		}

	}

	private void performOperationsOnConfiguration(ConfigurationVariable cv,
			String op) {

		_LOGGER.debug(
				"Analyzing a configuration: " + cv);

		if (op.equals("isValid")) {
			setType(RType.BOOLEAN);
			boolean bv = cv.isValid() ; 
			setVariable(new BooleanVariable(_assigner, bv));
			return;
		} else if (op.equals("counting")) { // @deprecated: size now

			setType(RType.INTEGER);
			int r = (int) new FeatureModelVariable(null, cv.asFM()).counting() ; 
			_LOGGER.debug(
					"counting configuration: " + r);
			setVariable (new IntegerVariable(_assigner, r));
			return;
		} else if (op.equals("nbFeatures")) {
			// TODO
			// no sense
		} else if (op.equals("features")) {
			// TODO
			// no sense
		} else if (op.equals("root")) {
			// no sense
		}

		else {
			FMLShell.getInstance().setError("Unknown operation");
			return;
		}

	}

	@Deprecated
	private void performOperationsOnSet(String op, SetVariable v) {

		FMLShell.getInstance().setError(
				"deprecated operation: " + op + " on sets.");
		if (op.equals("isValid")) {

		} else if (op.equals("counting")) { // @deprecated: card/size before

			setType(RType.INTEGER);
			int r = v.getVars().size();
			_LOGGER.debug("card set: " + r);
			setVariable(new IntegerVariable(_assigner, r));
			return;

		} else if (op.equals("set")) {

		} else if (op.equals("nbFeatures")) {

		} else if (op.equals("root")) {

		} else if (op.equals("core")) {

		} else {
			FMLShell.getInstance().setError("Unknown operation");
			return;
		}

		FMLShell.getInstance().setError(
				"such an operation (" + op + ") cannot be performed on SET");
		return;

	}

}
