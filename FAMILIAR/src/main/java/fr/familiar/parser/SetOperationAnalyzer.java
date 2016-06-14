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

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.IsEmptySet;
import org.xtext.example.mydsl.fml.SetAddOrRemove;
import org.xtext.example.mydsl.fml.SetBelongs;
import org.xtext.example.mydsl.fml.SetCard;
import org.xtext.example.mydsl.fml.SetCommand;
import org.xtext.example.mydsl.fml.SetEmpty;
import org.xtext.example.mydsl.fml.SetOperations;
import org.xtext.example.mydsl.fml.SetToNames;
import org.xtext.example.mydsl.fml.SetUnionOrIntersection;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;

/**
 * TODO we should refactor the Xtext grammar its really weird 
 * @author macher1
 *
 */
public class SetOperationAnalyzer extends SetOperationParser {
	
	private static Logger _LOGGER = Logger.getLogger(SetOperationAnalyzer.class);

	public SetOperationAnalyzer(Command cmd, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	public SetOperationAnalyzer(Command cmd, NameSpace ns, String var,
			FMLCommandInterpreter commandInterpreter) {
		super(cmd, var, ns, commandInterpreter);

	}

	@Override
	public RType getType() {
		return _type;
	}

	@Override
	public void eval() { // and add the variable

		assert (getCmd() instanceof SetOperations);

		Command cmd = getCmd();
		_LOGGER.debug(
				"@@@@@@@@========= \t add or remove?");
		_LOGGER.debug("" + cmd.toString());
		Variable sw = null;
		if (cmd instanceof SetAddOrRemove)
			sw = parseSetAddOrRemove();
		else if (cmd instanceof SetBelongs)
			sw = parseSetBelongs();
		else if (cmd instanceof SetCard) // size
			sw = parseSetCard();
		else if (cmd instanceof SetEmpty)
			sw = parseSetEmpty();
		else if (cmd instanceof SetUnionOrIntersection)
			sw = parseSetUnionIntersectionOrDiff();
		else if (cmd instanceof SetToNames)
			sw = parseSetToNames();
		else if (cmd instanceof IsEmptySet) {
			sw = parseIsEmptySet();
		} else {
			FMLShell.getInstance().setError("Unknown operation for SET");
			return;
		}

		if (sw == null) {
			FMLShell.getInstance().setError(
					"unable to compute (set operations)");
			return;
		}

		if (sw instanceof BooleanVariable)
			setType(RType.BOOLEAN);
		else if (sw instanceof SetVariable)
			setType(RType.SET);
		else if (sw instanceof IntegerVariable)
			setType(RType.INTEGER);
		else {
			FMLShell.getInstance().setError("Unknown value for SET operation");
			return;
		}

		setVariable(sw);

	}

	

	private BooleanVariable parseIsEmptySet() {

		IsEmptySet em = (IsEmptySet) getCmd();

		// TODO
		SetCommand setCmd = em.getSet();
		_LOGGER.debug("evaluating set: " + setCmd);
		Variable setVar = _environment.parseSetCommand(setCmd, null);
		assert (setVar != null);
		_LOGGER.debug("obtained " + setVar);

		if (!(setVar instanceof SetVariable)) {
			FMLShell.getInstance()
					.setError(
							"variable " + setVar + " is not a set:"
									+ setVar.getRType());
			return null;
		}

		SetVariable sw = (SetVariable) setVar;

		return new BooleanVariable(getAssigner(), sw.getVars().isEmpty());
	}
	
	private SetVariable parseSetToNames() {
		
		SetToNames namesCmd = (SetToNames) getCmd();
		
		SetCommand setCmd = namesCmd.getSet();
		Variable setVar = _environment.parseSetCommand(setCmd, null);
		assert (setVar != null);
		
		if (!(setVar instanceof SetVariable)) {
			FMLShell.getInstance()
					.setError(
							"variable " + setVar + " is not a set:"
									+ setVar.getRType());
			return null;
		}

		SetVariable sw = (SetVariable) setVar;
		Set<Variable> vars = new HashSet<Variable>();
		for (String stringValue : sw.names()) {
			vars.add(new StringVariable("", stringValue));
		}
				
		return new SetVariable(vars);
	}

	/**
	 * certainly deprecated (see SetOperationParser)
	 * 
	 * @return
	 */
	private SetVariable parseSetUnionIntersectionOrDiff() {
		SetUnionOrIntersection setUCmd = (SetUnionOrIntersection) getCmd();

		
		SetCommand lsetCmd = setUCmd.getSetl();
		SetVariable lsetVar = _environment.parseSetCommand(lsetCmd, null);
		
		
		SetCommand rsetCmd = setUCmd.getSetr();
		SetVariable rsetVar = _environment.parseSetCommand(rsetCmd, null);

		SetVariable sset = null;

		String setop = setUCmd.getOp();

		if (setop.equals("setUnion")) 
			sset = lsetVar.union(rsetVar); 
		else if (setop.equals("setIntersection")) 
			sset = lsetVar.intersection(rsetVar);
		else if (setop.equals("setDiff")) 
			sset = lsetVar.difference(rsetVar);		
		else {
			FMLShell.getInstance().setError(
					"unknown operator " + setop + " for manipulating SET");
			return null;
		}

		return sset ; 

	}

	private SetVariable parseSetEmpty() {
		SetVariable sw = new SetVariable(new HashSet<Variable>(), getAssigner());
		return sw;
	}

	private IntegerVariable parseSetCard() {

		SetCard cardCmd = (SetCard) getCmd();

		// TODO
		SetCommand setCmd = cardCmd.getSet();
		_LOGGER.debug(
				"evaluating set command: " + setCmd);
		Variable setVar = _environment.parseSetCommand(setCmd, null);
		assert (setVar != null);
		_LOGGER.debug("obtained " + setVar);

		if (!(setVar instanceof SetVariable)) {
			FMLShell.getInstance()
					.setError(
							"variable " + setVar + " is not a set:"
									+ setVar.getRType());
			return null;
		}

		SetVariable sw = (SetVariable) setVar;

		int v = sw.getVars().size();
		IntegerVariable iw = new IntegerVariable(getAssigner(), v);
		return iw;

	}

	private BooleanVariable parseSetBelongs() {
		SetBelongs setBelongsCmd = (SetBelongs) getCmd();

		String lsetStrVar = setBelongsCmd.getSetl();

		Variable lsetVar = null;
		try {
			lsetVar = _environment.getVariable(lsetStrVar);
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError("unknown variable " + lsetStrVar);
			return null;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError(
					"(ambigous) unknown variable " + lsetStrVar);
			return null;
		}

		assert (lsetVar != null);

		if (!(lsetVar instanceof SetVariable)) {
			FMLShell.getInstance().setError(
					"variable (left) " + lsetStrVar + " is not a set:"
							+ lsetVar.getRType());
			return null;
		}

		String rStrVar = setBelongsCmd.getSetl();

		Variable rVar = null;
		try {
			rVar = _environment.getVariable(rStrVar);
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError("unknown variable " + rStrVar);
			return null;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError(
					"(ambigous) unknown variable " + rStrVar);
			return null;
		}

		assert (rVar != null);

		SetVariable lsw = (SetVariable) lsetVar;

		Set<Variable> vars = lsw.getVars();
		for (Variable v : vars) {
			if (v.getVid().equals(rVar)) // TODO (to discuss)
				return new BooleanVariable(getAssigner(), true);
		}

		return new BooleanVariable(getAssigner(), false);

	}

	private BooleanVariable parseSetAddOrRemove() {

		SetAddOrRemove saddOrRemoveCmd = (SetAddOrRemove) getCmd();

		// TODO
		SetCommand lsetCmd = saddOrRemoveCmd.getSetl();
		_LOGGER.debug(
				"evaluating set for removing: " + lsetCmd);

		Variable lsetVar = _environment.parseSetCommand(lsetCmd, null);
		assert (lsetVar != null);
		_LOGGER.debug("obtained " + lsetVar);

		if (!(lsetVar instanceof SetVariable)) {
			FMLShell.getInstance().setError(
					"variable " + lsetVar + " is not a set:"
							+ lsetVar.getRType());
			return null;
		}

		assert (lsetVar != null);

		if (lsetVar instanceof RefVariable)
			lsetVar = ((RefVariable) lsetVar).getValueReference();

		// TODO
		Command toAddorRemove = saddOrRemoveCmd.getVar();
		_LOGGER.debug(
				"evaluating element to add or remove: " + toAddorRemove);

		Variable rVar = _environment.parse(toAddorRemove, null);

		assert (rVar != null);

		// SIDE EFFECT
		SetVariable sw = (SetVariable) lsetVar;
		Set<Variable> lsw = sw.getVars();

		String op = saddOrRemoveCmd.getOp();

		boolean correct = true;
		if (op.equals("setAdd"))
			correct = safeAdd (rVar, lsw); // lsw.add(rVar);
		else if (op.equals("setRemove"))
			try {
				correct = safeRemove (rVar, lsw);
			} catch (Exception e) {
				FMLShell.getInstance().setError("error when removing " + rVar + " in the set " + lsw);
				return null;
			}
		else {
			FMLShell.getInstance().setError("unknown operator " + op + " for adding/removing SET");
			return null;
		}

		if (!correct) {
			_LOGGER.debug("unable to remove/add variable " + rVar.getVid() + "");
		} else
			_LOGGER.debug(
					"succesfully adding/removing variable " + rVar.getVid()
							+ "");

		BooleanVariable bws = new BooleanVariable(getAssigner(), correct);
		return bws;

	}

}
