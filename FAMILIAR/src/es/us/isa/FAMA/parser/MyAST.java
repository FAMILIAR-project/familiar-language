/**
 * 	This file is part of FaMaTS.
 *
 *     FaMaTS is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     FaMaTS is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.us.isa.FAMA.parser;

import antlr.CommonAST;
import antlr.Token;
import antlr.collections.AST;

public class MyAST extends CommonAST {
	/**
	 * La clave para evitar el tema de los clonados esta en esta clase, y en
	 * algunos de sus atributos/metodos (los relativos, sobre todo, a down y
	 * right)
	 */

	private static final long serialVersionUID = -628197363392001830L;
	private int col = 0, line = 0;

	public void initialize(Token tok) {
		super.initialize(tok);
		line = tok.getLine();
		col = tok.getColumn();
	}

	public void initialize(AST ast) {
		super.initialize(ast);
		if (ast instanceof MyAST) {
			col = ((MyAST) ast).getColumn();
			line = ((MyAST) ast).getLine();
		}
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return col;
	}

	public void setLine(int l) {
		line = l;
	}

	public void setCol(int c) {
		col = c;
	}
}
