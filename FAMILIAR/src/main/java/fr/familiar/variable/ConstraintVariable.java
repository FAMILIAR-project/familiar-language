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

package fr.familiar.variable;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;
import gsd.synthesis.Expression;

public class ConstraintVariable extends VariableImpl {
	
	private Expression<String> _constraint ;

 
	public ConstraintVariable(Expression<String> constraint,
			String name, NameSpace ns) {
		_constraint = constraint ; 
		this.name = name ;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
	}

	public ConstraintVariable(Expression<String> expr, String name) {
		this(expr, name, NSFactory.mkEmpty());
	}

	
	

	@Override
	public RType getRType() {
		return RType.CONSTRAINT ; 
	}

	@Override
	public Variable copy() {
		return new ConstraintVariable(_constraint, name);
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof ConstraintVariable) {
			ConstraintVariable cv = (ConstraintVariable) vari;
			setConstraint(cv.getConstraint());
			return;
		}
		FMLShell.getInstance().setError("Setting value type is not correct");
		
	}

	@Override
	public String getSpecificValue() {
		return _constraint.toString() ;  
	}
	
	public Expression<String> getConstraint() {
		return _constraint;
	}

	public void setConstraint(Expression<String> constraint) {
		_constraint = constraint;
	}
	
}
