/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable;

/**
 * @author macher1
 *
 */
public class FeatureAttribute {
	
	public FeatureVariable getFt() {
		return _ft;
	}

	public String getName() {
		return _name;
	}

	public Variable getValue() {
		return _value;
	}


	/**
	 * feature involved in the attribute
	 */
	private FeatureVariable _ft ;
	
	/**
	 * identifier of the attribute
	 */
	private String _name ; 
	
	/**
	 * valud if any of the attribute
	 */
	private Variable _value = null ; 
	
	public FeatureAttribute(FeatureVariable ft, String name) {
		_ft = ft ; 
		_name = name ; 
	}
	
	public FeatureAttribute(FeatureVariable ft, String name, Variable val) {
		_ft = ft ; 
		_name = name ; 
		_value = val ; 
	}

}
