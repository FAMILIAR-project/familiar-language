/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable;

import fr.unice.polytech.modalis.familiar.parser.NameSpace;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

/**
 * @author macher
 * TODO: override many methods
 */
public class FeatureModelFalseVariable extends FeatureModelVariable {

	@Override
	public boolean isValid() {
		return false ; 
	}

	@Override
	public String getSpecificValue() {
		return "False"; 
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 */
	public FeatureModelFalseVariable(String name, FeatureModel<String> fm,
			Formula<String> formula) {
		super(name, fm, formula);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 * @param ns
	 */
	public FeatureModelFalseVariable(String name, FeatureModel<String> fm,
			Formula<String> formula, NameSpace ns) {
		super(name, fm, formula, ns);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param assigner
	 * @param fm
	 */
	public FeatureModelFalseVariable(String assigner, FeatureModel<String> fm) {
		super(assigner, fm);
		// TODO Auto-generated constructor stub
	}

}
