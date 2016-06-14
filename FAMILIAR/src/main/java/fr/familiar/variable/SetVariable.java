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
package fr.familiar.variable;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.ComparisonOperator;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;
import fr.familiar.parser.VariableComparator;

public class SetVariable extends VariableImpl {

	private Set<Variable> _vars;

	public SetVariable(Set<Variable> vars, String name, NameSpace ns) {

		this._vars = vars;
		this.vid = new VariableIdentifier(name, ns);
		setIdentifier(name);
		setNS(ns);

	}

	public SetVariable(Set<Variable> vars, String name) {

		this(vars, name, NSFactory.mkEmpty());

	}

	public SetVariable(Set<Variable> vars) {
		this(vars, null);
	}

	@Override
	public Variable copy() {
		Set<Variable> cvars = new HashSet<Variable>();
		for (Variable var : this._vars) {
			cvars.add(var.copy());
		}
		return new SetVariable(cvars, getIdentifier(), getNS());
	}

	@Override
	public RType getRType() {
		return RType.SET;
	}

	@Override
	public String getSpecificValue() {
		String r = "{";
		int l = this._vars.size();
		int i = 0;
		for (Variable var : this._vars) {
			i++;
			r += var.getValue();
			if (i != l)
				r += ";";
		}
		r += "}";
		return r;
	}

	@Override
	public void setValue(Variable vari) {
		if (vari instanceof SetVariable) {
			SetVariable sw = (SetVariable) vari;
			setVars(sw.getVars());

			return;
		}

		FMLShell.getInstance()
				.printWarning("Incompatible type (SET expected) ");
	}

	private void setVars(Set<Variable> vars) {
		this._vars = vars;
	}

	/**
	 * @return the set of FML variables
	 */
	public Set<Variable> getVars() {
		return _vars;
	}

	/**
	 * @return the number of elements in the set
	 */
	public int size() {
		return _vars.size();
	}

	/**
	 * @return whether the set of variables is empty (#variables=0)
	 */
	public boolean isEmpty() {
		return _vars.isEmpty();
	}

	/**
	 * originally designed to convert fm1.* (i.e., a set of features) as a set
	 * of string names
	 * 
	 * @return string-based representation of variables
	 */
	public Set<String> names() {

		Set<String> r = new HashSet<String>();
		for (Variable var : _vars) {
			r.add(var.getValue());
		}

		return r;
	}
	
	

	public SetVariable intersection(SetVariable oVar) {
		
		Set<Variable> vars = getVars() ; 
		Set<Variable> oVars = oVar.getVars() ; 
		
		Set<Variable> varsR = new HashSet<Variable>();
		
		// first pass
		for (Variable v : vars) {
			for (Variable oV : oVars) {
				 VariableComparator comparator = new VariableComparator(v, oV, ComparisonOperator.EQUAL) ;
				 try {
					if (comparator.eval())  {
						 varsR.add(v);
						 continue ; 
					}
				} catch (Exception e) {
					e.printStackTrace();
					FMLShell.getInstance().printError("Unable to compare variables when performing the union / intersection");
					return new SetVariable(varsR);
				} 
			}
		}
		
		
		// second pass
		for (Variable oV : oVars) {
			for (Variable v : vars) {
				 VariableComparator comparator = new VariableComparator(v, oV, ComparisonOperator.EQUAL) ;
				 try {
					if (comparator.eval()) { 
						 varsR.add(v);
						 continue ; 
					}
				} catch (Exception e) {
					e.printStackTrace();
					FMLShell.getInstance().printError("Unable to compare variables when performing the intersection");
					return new SetVariable(varsR);
				} 
			}
		}
		
		SetVariable svR = new SetVariable(varsR);
		return svR;
	}

	public SetVariable union(SetVariable oVar) {

		Set<Variable> vars = getVars() ;
		Set<Variable> oVars = oVar.getVars() ; 
		Set<Variable> varsR = new HashSet<Variable>(vars);
		
		// first pass
		for (Variable oV : oVars) {
			
			boolean found = false  ;
			
			for (Variable v : vars) {
				 VariableComparator comparator = new VariableComparator(v, oV, ComparisonOperator.EQUAL) ;
				 try {
					if (comparator.eval()) {
						found = true ;
						continue ; 
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
					FMLShell.getInstance().printError("Unable to compare variables when performing the union");
					return new SetVariable(varsR);
				} 
			}
			
			if (!found)
				varsR.add(oV);
			
		}
				
		
		SetVariable svR = new SetVariable(varsR);
		return svR;
	}

	public SetVariable difference(SetVariable oVar) {
		
		Set<Variable> vars = getVars() ; 
		Set<Variable> oVars = oVar.getVars() ; 
		
		Set<Variable> varsR = new HashSet<Variable>();

		for (Variable v : vars) {
			boolean found = false ; 
			for (Variable oV : oVars) {
				 VariableComparator comparator = new VariableComparator(v, oV, ComparisonOperator.EQUAL) ;
				 try {
					if (comparator.eval())  {
						 found = true ; 
						 continue ; 
					}
				} catch (Exception e) {
					e.printStackTrace();
					FMLShell.getInstance().printError("Unable to compare variables when performing the diff");
					return new SetVariable(varsR);
				} 
			}
			if (!found)
				varsR.add(v);
		}
		
			
		SetVariable svR = new SetVariable(varsR);
		return svR;
	}

}
