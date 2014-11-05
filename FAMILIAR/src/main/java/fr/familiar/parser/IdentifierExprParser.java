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

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.IdentifierExpr;
import org.xtext.example.mydsl.fML.StringExpr;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class IdentifierExprParser extends FMLAbstractCommandAnalyzer {

	public static final String SEPARATOR = ".";

	private RType type = null;

	private String _attributeID = null ;

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public IdentifierExprParser(Command cmd2, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, ns, an);

	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public IdentifierExprParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);

	}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#parse()
	 */
	@Override
	public void eval() {
		assert (_command instanceof IdentifierExpr);

		IdentifierExpr idExpr = (IdentifierExpr) _command;
		String id = idExpr.getVal();
		
		Variable variable;
		try {
			variable = _environment.getVariable(id);
		} catch (VariableNotExistingException e) {
			FMLShell.getInstance().setError("" + e.getMessage());
			return;
		} catch (VariableAmbigousConflictException e) {
			FMLShell.getInstance().setError("(ambigous) " + e.getMessage());
			return;
		}
		StringExpr metaID = idExpr.getMetaID() ; 
		if (metaID != null) {
			String attributeID = metaID.getVal() ; 
			Variable rVariable;
			try {
				rVariable = variable.lookup(attributeID);
				// set type
				type = rVariable.getRType();
				
				// set value
				setVariable(rVariable);
			} catch (VariableNotExistingException e) {
				FMLShell.getInstance().printError("Unable to retrieve the attribute " + _attributeID + " associated to variable " + variable);
				return ; 
			}
			
			
			
			return ;
		}
		
		

		else {
			// set type
			type = variable.getRType();

			// set value
			setVariable(variable);
		}

	}

	private boolean isComposed(String id) {
		return id.indexOf(SEPARATOR) != -1;
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
		return type;
	}

}
