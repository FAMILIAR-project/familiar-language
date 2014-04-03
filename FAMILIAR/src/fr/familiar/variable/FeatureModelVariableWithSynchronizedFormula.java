/**
 * 
 */
package fr.familiar.variable;

import fr.familiar.fm.FeatureModelCloner;
import fr.familiar.parser.NameSpace;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Formula;

/**
 * @author macher
 * contrary to the default implementation, the idea is to keep up to date the formula
 */
public class FeatureModelVariableWithSynchronizedFormula extends FeatureModelVariable {
	
	
	

	@Override
	public Formula<String> getFormula() {
		return _formula ; 
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 */
	public FeatureModelVariableWithSynchronizedFormula(String name,
			FeatureModel<String> fm, Formula<String> formula) {
		super(name, fm, formula);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 * @param fm
	 * @param formula
	 * @param ns
	 */
	public FeatureModelVariableWithSynchronizedFormula(String name,
			FeatureModel<String> fm, Formula<String> formula, NameSpace ns) {
		super(name, fm, formula, ns);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param assigner
	 * @param fm
	 */
	public FeatureModelVariableWithSynchronizedFormula(String assigner,
			FeatureModel<String> fm) {
		super(assigner, fm);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Variable copy() {

		// clone of the feature model and the formula
		FeatureModel<String> fmCopy = FeatureModelCloner.clone(_fm);

		return new FeatureModelVariableWithSynchronizedFormula(name, fmCopy, this.getFormula().clone(), ns);
	}

}