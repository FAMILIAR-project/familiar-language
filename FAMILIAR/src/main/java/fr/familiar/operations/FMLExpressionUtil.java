/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations;

import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;

public class FMLExpressionUtil {

	public static <T> Expression<T> toCNF(Expression<T> e) {
		// eliminate biconditionals and implications:
		Expression<T> res = elimImplication(e);
		// eliminate neg
		res = elimNeg(res);
		// Applying De Morgan Law
		res = distributeOrOverAnd(res);

		return res;
	}

	/**
	 * @param <T>
	 * @param e
	 * @return implication and biimplication (a <=> b ---> a => b ^ b => a) (a
	 *         => b) ---> !a v b
	 */
	private static <T> Expression<T> elimImplication(Expression<T> e) {

		ExpressionType type = e.getType();
		switch (type) {
		case IFF:
			return elimImplication(e.getLeft().implies(e.getRight())).and(
					elimImplication(e.getRight().implies(e.getLeft())));
		case IMPLIES:
			return elimImplication(e.getLeft()).not().or(
					elimImplication(e.getRight()));
		case AND:
		case OR:
			return new Expression<T>(type, elimImplication(e.getLeft()),
					elimImplication(e.getRight()));
		default:
			return e;
		}

	}

	private static <T> Expression<T> elimNeg(Expression<T> e) {

		// Move NOTs inwards by repeatedly applying DeMorgan's Law.
		// Specifically,
		// replace \neg (x \vee y) with (\neg x) \wedge (\neg y);
		// replace \neg (x \wedge y) with (\neg x) \vee (\neg y);
		// and replace \neg\neg x with x.

		ExpressionType type = e.getType();
		switch (type) {

		case NOT:
			Expression<T> notExpr = e.getLeft();

			ExpressionType notExprType = notExpr.getType();
			if (notExprType.equals(ExpressionType.NOT)) // !!a = a
				return elimNeg(notExpr.getLeft());
			else if (notExprType.equals(ExpressionType.AND)) { // !(a^b)=!a v !b
				return elimNeg(notExpr.getLeft().not()).or(
						elimNeg(notExpr.getRight().not()));
			} else if (notExprType.equals(ExpressionType.OR)) { // !(avb)=!a ^
																// !b
				return elimNeg(notExpr.getLeft().not()).and(
						elimNeg(notExpr.getRight().not()));
			}
			return e;
		case AND:
		case OR:
			return new Expression<T>(type, elimNeg(e.getLeft()),
					elimNeg(e.getRight()));
		default:
			return e;
		}

	}

	private static <T> Expression<T> distributeOrOverAnd(Expression<T> e) {

		ExpressionType type = e.getType();
		switch (type) {

		case OR:
			// (a ^ b) v c ---> (a v c ) ^ (b v c ).
			Expression<T> andExpr = distributeOrOverAnd(e.getLeft());
			if (andExpr.getType() == ExpressionType.AND) {
				Expression<T> distrExpr = distributeOrOverAnd(e.getRight());
				Expression<T> rleft = distributeOrOverAnd(andExpr.getLeft().or(
						distrExpr));
				Expression<T> rright = distributeOrOverAnd(andExpr.getRight()
						.or(distrExpr));
				return rleft.and(rright);
			} else { // c v (a ^ b) ---> (a v c ) ^ (b v c ).
				Expression<T> andRExpr = distributeOrOverAnd(e.getRight());
				if (andRExpr.getType() == ExpressionType.AND) {
					Expression<T> distrLExpr = distributeOrOverAnd(e.getLeft());
					Expression<T> rleft = distributeOrOverAnd(andRExpr
							.getLeft().or(distrLExpr));
					Expression<T> rright = distributeOrOverAnd(andRExpr
							.getRight().or(distrLExpr));
					return rleft.and(rright);
				} else
					return new Expression<T>(type, e.getLeft(), e.getRight());

			}
		case AND:
			return new Expression<T>(type, distributeOrOverAnd(e.getLeft()),
					distributeOrOverAnd(e.getRight()));
		default:
			return e;
		}

	}

}
