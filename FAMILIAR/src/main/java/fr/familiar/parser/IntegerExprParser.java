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
import org.xtext.example.mydsl.fml.IntegerExpr;
import org.xtext.example.mydsl.fml.impl.IntLiteralImpl;

import fr.familiar.variable.IntegerVariable;
import fr.familiar.variable.RType;

/**
 * @author mathieuacher
 * 
 */
public class IntegerExprParser extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public IntegerExprParser(Command cmd2, NameSpace ns,
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
	public IntegerExprParser(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#parse()
	 */
	@Override
	public void eval() {
		assert (_command instanceof IntegerExpr);

		IntLiteralImpl intLiteral = (IntLiteralImpl) _command;

		int value = intLiteral.getValue();

		setVariable(new IntegerVariable(null, value, ns));

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
		return RType.INTEGER;
	}

}
