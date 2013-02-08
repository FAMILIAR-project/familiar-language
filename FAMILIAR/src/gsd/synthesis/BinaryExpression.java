/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.synthesis;

import static gsd.synthesis.ExpressionType.FEATURE;
import static gsd.synthesis.ExpressionType.IMPLIES;
import static gsd.synthesis.ExpressionType.NOT;
import static gsd.synthesis.ExpressionType.OR;

public abstract class BinaryExpression<T> extends Expression<T> {

	private final boolean _isRequires;


	protected BinaryExpression(boolean isRequires) {
		super(ExpressionType.IMPLIES);
		_isRequires = isRequires;
	}

	public abstract T getAntecedent();
	public abstract T getConsequent();

	/**
	 * Returns a binary expression of canonical form
	 *
	 * TODO handle IFF (biimp) expressions
	 *
	 * @param e
	 * @return Requires or Excludes expression, or expr if the provided
	 *         expression is not a binary expression.
	 */
	public static <T> BinaryExpression<T> mkCanonicalBinaryExpression(Expression<T> e) {
		
		
		/*
		 * Check for Implies Edge
		 */
		if (e.getType() == IMPLIES
				&& e.getLeft().getType() == FEATURE
				&& e.getRight().getType() == FEATURE) {
			return new Requires<T>(e.getLeft().getFeature(),
					e.getRight().getFeature());
		}
		/* 
		original BUG!
		else if (e.getType() == OR
				&& e.getLeft().getType() == FEATURE
				&& e.getRight().getType() == NOT
				&& e.getRight().getLeft().getType() == FEATURE) {
			return new Requires<T>(e.getLeft().getFeature(), e.getRight()
					.getLeft().getFeature());
		}
		*/
		else if (e.getType() == OR
				&& e.getLeft().getType() == FEATURE
				&& e.getRight().getType() == NOT
				&& e.getRight().getLeft().getType() == FEATURE) {
			return new Requires<T>(e.getRight()
					.getLeft().getFeature(), e.getLeft().getFeature());
		} // correction!
		
		else if (e.getType() == OR
				&& e.getLeft().getType() == NOT
				&& e.getLeft().getLeft().getType() == FEATURE
				&& e.getRight().getType() == FEATURE) {
			return new Requires<T>(e.getLeft().getLeft().getFeature(),
					e.getRight().getFeature());
		}

		/*
		 * Check for Excludes edges
		 */
		else if (e.getType() == IMPLIES
				&& e.getLeft().getType() == FEATURE
				&& e.getRight().getType() == NOT
				&& e.getRight().getLeft().getType() == FEATURE)
			return new Excludes<T>(e.getLeft().getFeature(), e.getRight()
					.getLeft().getFeature());

		else if (e.getType() == OR
				&& e.getLeft().getType() == NOT
				&& e.getLeft().getLeft().getType() == FEATURE
				&& e.getRight().getType() == NOT
				&& e.getRight().getLeft().getType() == FEATURE)
			return new Excludes<T>(e.getLeft().getLeft().getFeature(), e
					.getRight().getLeft().getFeature());

		return null;
	}

	public boolean isRequires() {
		return _isRequires;
	}
	public boolean isExcludes() {
		return !_isRequires;
	}


	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BinaryExpression<?>) {
			return super.equals((Expression<?>)obj);
		}
		else if (obj instanceof Expression<?>) {
			Expression<?> expr = (Expression<?>) obj;
			return super.equals(mkCanonicalBinaryExpression(expr));
		}
		return false;
	}
}