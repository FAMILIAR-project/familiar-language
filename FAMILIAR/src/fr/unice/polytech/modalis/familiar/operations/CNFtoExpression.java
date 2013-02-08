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
package fr.unice.polytech.modalis.familiar.operations;

import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;

import org.xtext.example.mydsl.fML.And_expr;
import org.xtext.example.mydsl.fML.Biimpl_expr;
import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.CNFExpression;
import org.xtext.example.mydsl.fML.Impl_expr;
import org.xtext.example.mydsl.fML.Neg_expr;
import org.xtext.example.mydsl.fML.Or_expr;
import org.xtext.example.mydsl.fML.impl.CNFExpressionImpl;

public class CNFtoExpression {

	private CNF _cnf;

	public CNFtoExpression(CNF cnf) {
		_cnf = cnf;
	}

	public Expression<String> convert() {
		CNFExpressionImpl expr = (CNFExpressionImpl) _cnf;
		return treatCNFExpr(expr);

	}

	private Expression<String> treatCNFExpr(CNFExpression expr) {

		if (expr instanceof Or_expr) {
			Or_expr oexpr = (Or_expr) expr;
			CNFExpression lcnf = oexpr.getLeft();
			Expression<String> lexpr = treatCNFExpr(lcnf);
			CNFExpression rcnf = oexpr.getRight();
			if (rcnf == null)
				return lexpr;
			else {
				Expression<String> rexpr = treatCNFExpr(rcnf);
				return new Expression<String>(ExpressionType.OR, lexpr, rexpr);
			}
		} else if (expr instanceof And_expr) {
			And_expr aexpr = (And_expr) expr;
			CNFExpression lcnf = aexpr.getLeft();
			Expression<String> lexpr = treatCNFExpr(lcnf);
			CNFExpression rcnf = aexpr.getRight();
			if (rcnf == null)
				return lexpr;
			else {
				Expression<String> rexpr = treatCNFExpr(rcnf);
				return new Expression<String>(ExpressionType.AND, lexpr, rexpr);
			}

		} else if (expr instanceof Impl_expr) {

			Impl_expr impexpr = (Impl_expr) expr;
			CNFExpression lcnf = impexpr.getLeft();
			Expression<String> lexpr = treatCNFExpr(lcnf);
			CNFExpression rcnf = impexpr.getRight();
			if (rcnf == null)
				return lexpr;
			else {
				Expression<String> rexpr = treatCNFExpr(rcnf);
				return new Expression<String>(ExpressionType.IMPLIES, lexpr,
						rexpr);
			}

		} else if (expr instanceof Biimpl_expr) {

			Biimpl_expr biexpr = (Biimpl_expr) expr;
			CNFExpression lcnf = biexpr.getLeft();
			Expression<String> lexpr = treatCNFExpr(lcnf);
			CNFExpression rcnf = biexpr.getRight();
			if (rcnf == null)
				return lexpr;
			else {
				Expression<String> rexpr = treatCNFExpr(rcnf);
				return new Expression<String>(ExpressionType.IFF, lexpr, rexpr);
			}

		} else if (expr instanceof Neg_expr) {
			Neg_expr nexpr = (Neg_expr) expr;
			return new Expression<String>(ExpressionType.NOT,
					treatCNFExpr(nexpr.getExpr()), null);
		} else {
			Expression<String> lexpr = new Expression<String>(mkName(expr.getName()));
			return lexpr;
		}

	}

	private String mkName(String name) {
		// TODO: document it in the grammar
		if (name.equals("true")) { 
			return "1" ;
		}			
		else if (name.equals("false")) {
			return "0" ; 
		}
		return name ; 
	}

}
