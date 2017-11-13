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

package gsd.synthesis;

public class Expression<T> {
	protected Expression<T> _left, _right;
	protected T _feature;

	private ExpressionType _type;

	public Expression(ExpressionType type) {
		_type = type;
		_left = null;
		_right = null;
	}

	public Expression(ExpressionType type, Expression<T> left, Expression<T> right) {
		_type = type;
		_left = left;
		_right = right;
	}

	public Expression(ExpressionType type, T leftFeat, T rightFeat) {
		assert type != ExpressionType.FEATURE;
		_type = type;
		_left = new Expression<T>(leftFeat);
		_right = new Expression<T>(rightFeat);
	}

	public Expression(T feature) {
		assert feature != null;
		_type = ExpressionType.FEATURE;
		_feature = feature;
	}

	public T getFeature() {
		return _feature;
	}

	public void setFeature(T feature) {
		_feature = feature;
	}

	public Expression<T> getLeft() {
		return _left;
	}
	public void setLeft(Expression<T> left) {
		_left = left;
	}


	public Expression<T> getRight() {
		return _right;
	}
	public void setRight(Expression<T> right) {
		_right = right;
	}

	public ExpressionType getType() {
		return _type;
	}


	public Expression<T> implies(Expression<T> other) {
		return new Expression<T>(ExpressionType.IMPLIES, this, other);
	}

	public Expression<T> or(Expression<T> other) {
		return new Expression<T>(ExpressionType.OR, this, other);
	}

	public Expression<T> and(Expression<T> other) {
		return new Expression<T>(ExpressionType.AND, this, other);
	}

	public Expression<T> not() {
		return new Expression<T>(ExpressionType.NOT, this, null);
	}


	@Override
	public int hashCode() {
		switch (_type) {
		case TRUE:
			return 6713;
		case FALSE:
			return 42;
		case FEATURE:
			return _feature.hashCode() * -13;
		case NOT:
			return 3 * _left.hashCode();
		default:
			return _left.hashCode() + 2 * _right.hashCode();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Expression) {
			Expression<?> other = (Expression<?>) obj;
			if (_type == other._type) {
				switch (_type) {
				case TRUE:
				case FALSE:
					return true;
				case FEATURE:
					return _feature.equals(other._feature);
				case IMPLIES:
					
					// added by Acher, M
					if (_right.getType() == ExpressionType.NOT && (other._right.getType() == ExpressionType.NOT)) { // two excludes
						return (_left.equals(other._left) &&
								_right.equals(other._right)) // normal 
								
								||
								// A -> !B eq B -> !A
								(_right.getLeft().equals(other._left) &&
										_left.equals(other._right.getLeft())) ;
					}
					
					return _left.equals(other._left)
					&& _right.equals(other._right);
				case NOT:
					return _left.equals(other._left);
				case AND:
				case IFF:
				case OR:
					return (_left.equals(other._left)
						&& _right.equals(other._right))
						|| (_left.equals(other._right)
						&& _right.equals(other._left));
				default:
					assert false;
				}
			}
		}
		return false;
	}
//
//	public Expression<T> getCNF() {
//		switch (_type) {
//		case FEATURE:
//		case TRUE:
//		case FALSE:
//			return this;
//		case IMPLIES:
//			return _left.getCNF().not().or(_right.getCNF());
//		case OR:
//			//Must distribute OR over AND
//
//		case AND:
//			return _left.getCNF().and(_right.getCNF());
//		default:
//			assert false;
//		}
//	}

	@Override
	public String toString() {
		switch (_type) {
		case FEATURE:
			return _feature.toString();
		case TRUE:
			return "1";
		case FALSE:
			return "0";
		case NOT:
			return "!" + _left;
		default:
			StringBuffer sb = new StringBuffer();
			sb.append('(');
			sb.append(_left);
			sb.append(" " + _type + " ");
			sb.append(_right);
			sb.append(')');
			return sb.toString();
		}
	}
}