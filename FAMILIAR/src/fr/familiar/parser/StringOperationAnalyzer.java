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

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.IntegerCommand;
import org.xtext.example.mydsl.fML.StrCommand;
import org.xtext.example.mydsl.fML.StringConcat;
import org.xtext.example.mydsl.fML.StringIndexOf;
import org.xtext.example.mydsl.fML.StringInit;
import org.xtext.example.mydsl.fML.StringLength;
import org.xtext.example.mydsl.fML.StringOperation;
import org.xtext.example.mydsl.fML.StringSubstring;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;

public class StringOperationAnalyzer extends FMLAbstractCommandAnalyzer {
	
	private static Logger _LOGGER = Logger.getLogger(StringOperationAnalyzer.class);

	public StringOperationAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return _type;
	}

	@Override
	public void eval() {

		assert (getCmd() instanceof StringOperation);
		StringOperation strCmd = (StringOperation) getCmd();

		Variable vari = null;
		if (strCmd instanceof StringConcat)
			vari = parseStringConcat(strCmd);
		else if (strCmd instanceof StringInit) {
			vari = parseStringInit(strCmd);
		} else if (strCmd instanceof StringIndexOf)
			vari = parseStringIndexOf(strCmd);
		else if (strCmd instanceof StringSubstring)
			vari = parseStringSubstring(strCmd);
		else if (strCmd instanceof StringLength)
			vari = parseStringLenght(strCmd);
		else {
			FMLShell.getInstance().setError("Unknown operation for String");
			return;
		}

		assert (vari != null);

		setVariable(vari);

	}

	private Variable parseStringLenght(StringOperation strCmd) {

		StringLength lengthCmd = (StringLength) strCmd;

		StrCommand strExpr = lengthCmd.getStr();
		_LOGGER.debug(
				"evaluating str to indexOf: " + strExpr);
		StringVariable strV = _environment.parseStringCommand(strExpr, null);

		return new IntegerVariable(_assigner, strV.getValue().length());
	}

	private Variable parseStringInit(StringOperation strCmd) {
		return new StringVariable(getAssigner(), "");
	}

	private Variable parseStringSubstring(StringOperation strCmd) {

		_LOGGER.debug("substring: " + strCmd);

		StringSubstring substrCmd = (StringSubstring) strCmd;

		IntegerCommand ibCmd = substrCmd.getBegin();
		IntegerCommand ieCmd = substrCmd.getEnd();

		IntegerVariable ibv = _environment.parseIntegerCmd(ibCmd, null);
		IntegerVariable ebv = _environment.parseIntegerCmd(ieCmd, null);

		int begin = ibv.getV();
		int end = ebv.getV();

		StrCommand strExpr = substrCmd.getStr();
		_LOGGER.debug(
				"evaluating str to substring: " + strExpr);
		StringVariable strV = _environment.parseStringCommand(strExpr, null);

		String result = strV.getValue().substring(begin, end);

		return new StringVariable(_assigner, result);
	}

	private Variable parseStringIndexOf(StringOperation strCmd) {
		_LOGGER.debug("indexOf: " + strCmd);

		StringIndexOf strIndex = (StringIndexOf) strCmd;

		StrCommand strExpr = strIndex.getStr();
		_LOGGER.debug(
				"evaluating str to indexOf: " + strExpr);
		StringVariable strV = _environment.parseStringCommand(strExpr, null);

		StrCommand indexStrExpr = strIndex.getSchar();
		_LOGGER.debug(
				"evaluating str to indexOf: " + indexStrExpr);
		StringVariable indexStr = _environment.parseStringCommand(indexStrExpr,
				null);

		int index = strV.getValue().indexOf(indexStr.getValue());

		return new IntegerVariable(_assigner, index);
	}

	private Variable parseStringConcat(StringOperation strCmd) {
		StringConcat concatCmd = (StringConcat) strCmd;

		StrCommand lstrExpr = concatCmd.getLstr();
		_LOGGER.debug(
				"evaluating str (left) to concat: " + lstrExpr);
		StringVariable lv = _environment.parseStringCommand(lstrExpr, null);
		String lstrValue = lv.getValue();

		StrCommand rstrExpr = concatCmd.getRstr();
		_LOGGER.debug(
				"evaluating str (right) to concat: " + rstrExpr);
		StringVariable rv = _environment.parseStringCommand(rstrExpr, null);
		String rstrValue = rv.getValue();

		return new StringVariable(getAssigner(), lstrValue.concat(rstrValue));
	}

}
