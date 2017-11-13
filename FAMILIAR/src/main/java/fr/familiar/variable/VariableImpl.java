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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.interpreter.VariableNotExistingException;
import fr.familiar.parser.ConfigurationVariableFactory;
import fr.familiar.parser.NameSpace;

public abstract class VariableImpl implements Variable {
	
	
	Map<String, Variable> _attributesToVariable = new HashMap<String, Variable>(); 

	protected String name;
	protected NameSpace ns;
	protected VariableIdentifier vid;

	private boolean _isNull;

	protected FMLShell _shell ;

	@Override
	public NameSpace getNS() {
		return ns;
	}

	private static int anonymizator = 0;

	@Override
	public String getIdentifier() {
		if (name == null || name.equals("")) {
			String s = "res" + anonymizator;
			anonymizator++;
			return s;
		}
		return name;
	}

	public String getCompleteIdentifier() {
		return VariableIdentifier.completeName(getVid());
	}

	@Override
	public abstract RType getRType();

	@Override
	public abstract Variable copy();

	@Override
	public abstract void setValue(Variable vari);

	@Override
	public String getType() {
		return getRType().name();
	}

	public abstract String getSpecificValue();

	@Override
	public String getValue() {
		if (!isNull())
			return getSpecificValue();
		else
			return "null";
	}

	@Override
	public void setVid(VariableIdentifier vid) {
		this.vid = vid;
	}

	@Override
	public VariableIdentifier getVid() {
		return vid;
	}

	@Override
	public void setNS(NameSpace ns) {
		this.ns = ns;
		vid.setNs(ns);
	}

	@Override
	public void setIdentifier(String s) {
		this.name = s;
		if (this.vid == null) {
			this.vid = new VariableIdentifier(this.name, NSFactory.mkEmpty());
		}
		this.vid.setName(this.name);
	}

	@Override
	public boolean equals(Object v) {
		if (v instanceof VariableImpl) {
			Variable var = (Variable) v;
			if ((var instanceof RefVariable) && (this instanceof RefVariable)) {
				// FIXME
			}
			VariableIdentifier cvid = getVid();
			String oname = VariableIdentifier.completeName(var.getVid());
			String cname = VariableIdentifier.completeName(cvid);
			return cname.equals(oname);
		}
		return false;
	}

	@Override
	public boolean isNull() {
		return _isNull;
	}

	public void setAsNull() {
		_isNull = true;
	}

	/***
	 * should be overrided!
	 * 
	 * @param a_name
	 * @return
	 */
	public static VariableImpl mkDSNull(String a_name, RType type) {

		if (type == RType.BOOLEAN) {
			return new BooleanVariable(a_name, false);
		} else if (type == RType.CONFIGURATION) {
			return ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(null, a_name);
		} else if (type == RType.FEATURE_MODEL) {
			return new FeatureModelVariable(a_name, null, null);
		} else if (type == RType.VARIABILITY_OPERATOR) {
			return new VariabilityOperatorVariable(a_name, null, null);
		}
		// else if (type == RType.CONSTRAINTS) { // TODO
		// return null ;
		// }

		else if (type == RType.FEATURE) {
			return new FeatureVariable(a_name, null, null);
		}

		else if (type == RType.INTEGER) {
			return new IntegerVariable(a_name, -1);
		}

		// TODO: RefVariable

		else if (type == RType.SET) {
			return new SetVariable(null, a_name);
		}

		else if (type == RType.STRING) {
			return new StringVariable(a_name, (String) null);
		}

		else {
			System.err.println("mkDSNull returns null : type not recognized "
					+ type);
			return null;
		}
	}

	public static VariableImpl mkNull(String a_name, RType type) {
		VariableImpl v = mkDSNull(a_name, type);
		v.setAsNull();
		return v;
	}

	@Override
	public void free() {
		// nothing
	}
	

	
	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableAttributeHandler#lookup(java.lang.String)
	 */
	@Override
	public Variable lookup(String attributeID) throws VariableNotExistingException {
		if (!_attributesToVariable.containsKey(attributeID))
			throw new VariableNotExistingException(attributeID);
		return _attributesToVariable.get(attributeID) ; 
	}
	
	
	@Override
	public Variable put(String attributeID, Variable var) {
		return _attributesToVariable.put(attributeID, var);
	}
	
	@Override
	public Collection<Variable> getAttributes() {
		return _attributesToVariable.values();
	}
	
	public void setShell(FMLShell shell) {
		_shell  = shell ; 
	}
	
	public FMLShell getShell() {
		return _shell ; 
	}

}
