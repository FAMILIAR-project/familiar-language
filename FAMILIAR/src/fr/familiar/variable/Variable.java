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

import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.NameSpace;
import fr.familiar.parser.VariableAttributeHandler;

public interface Variable extends VariableAttributeHandler {
	
	

	public String getIdentifier();

	public void setIdentifier(String s);

	public String getValue();

	/**
	 * @return the type of the variable (string-based representation)
	 */
	public String getType();

	/**
	 * @return the type of the variable (RType enum-based representation)
	 */
	public RType getRType();

	/**
	 * free the memory, e.g., for a feature model variable, free the "formula"
	 */
	public void free();

	/**
	 * @return the namespace of the variable
	 */
	public NameSpace getNS();

	public void setNS(NameSpace ns);

	public VariableIdentifier getVid();

	public void setVid(VariableIdentifier vid);

	/**
	 * @return a copy of the variable (content-value based copy)
	 */
	public Variable copy();

	public void setValue(Variable vari);

	public boolean isNull();

	public void setAsNull();
	
	public void setShell(FMLShell shell) ;
	
	public FMLShell getShell() ;

}
