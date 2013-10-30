/**
 * 
 */
package fr.familiar.operations;

import java.util.Map;

import splar.core.fm.FeatureModel;
import splar.plugins.reasoners.sat.sat4j.FMReasoningWithSAT;

/**
 * @author mathieuacher
 * 
 */
public class MyFMReasoningWithSAT extends FMReasoningWithSAT {

	// TODO
	protected Map<String, Integer> _myVarName2IndexMap = null;

	/**
	 * @param solverName
	 * @param featureModel
	 * @param timeout
	 */
	public MyFMReasoningWithSAT(String solverName, FeatureModel featureModel,
			int timeout) {
		super(solverName, featureModel, timeout);
	}

	public MyFMReasoningWithSAT(String solverName, FeatureModel featureModel,
			int timeout, Map<String, Integer> imposedIntToVar) {
		super(solverName, featureModel, timeout);
		_myVarName2IndexMap = imposedIntToVar;
	}

}
