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


public class Excludes<T> extends BinaryExpression<T> {

	public Excludes(T ante, T cons) {
		super(false);
		_left = new Expression<T>(ante);
		_right = new Expression<T>(cons).not();
	}


	public T getAntecedent() {
		return _left.getFeature();
	}
	public T getConsequent() {
		return _right.getLeft().getFeature();
	}
	
	/* Added by AM
	 * A -> !B log eq B -> !A
	 * (non-Javadoc)
	 * @see gsd.synthesis.BinaryExpression#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Excludes<?>) {
			Excludes<?> objEx = (Excludes<?>) obj ; 
			
			return 
					(getAntecedent().equals(objEx.getAntecedent()) 
					&& getConsequent().equals(objEx.getConsequent()))
					
					||
					
					(getAntecedent().equals(objEx.getConsequent()) 
							&& getConsequent().equals(objEx.getAntecedent()))
							 ;
		}
		else if (obj instanceof Expression<?>) {
			Expression<?> expr = (Expression<?>) obj;
			return super.equals(mkCanonicalBinaryExpression(expr));
		}
		return false;
	}
	
	/* Added by AM
	 */
	@Override
	public int hashCode() {
		T ant = getAntecedent() ; 
		T cons = getConsequent() ;
		
		int antHash = (ant != null) ? ant.hashCode() : 2000 ; 
		int consHash = (cons != null) ? cons.hashCode() : 3000 ; 
		
		return antHash * consHash ;
	}

}