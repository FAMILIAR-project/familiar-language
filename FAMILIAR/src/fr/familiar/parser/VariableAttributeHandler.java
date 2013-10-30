package fr.familiar.parser;

import java.util.Collection;

import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.variable.Variable;


public interface VariableAttributeHandler {
		
	
	/**
	 * @param attributeID
	 * @return a variable corresponding to identifier attributeID (variable is created if needs be)
	 */
	public Variable lookup(String attributeID) throws VariableNotExistingException; 
	
	
	public Variable put(String attributeID, Variable var);


	public Collection<Variable> getAttributes(); 
	
	

}
