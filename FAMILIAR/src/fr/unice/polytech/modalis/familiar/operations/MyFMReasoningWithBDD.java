package fr.unice.polytech.modalis.familiar.operations;

import java.util.HashMap;
import java.util.Map;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;
import net.sf.javabdd.BDDFactory.ReorderMethod;
import splar.core.fm.FeatureModel;
import splar.core.heuristics.VariableOrderingHeuristic;
import splar.plugins.reasoners.bdd.javabdd.BDDExceededBuildingTimeException;
import splar.plugins.reasoners.bdd.javabdd.FMReasoningWithBDD;

public class MyFMReasoningWithBDD extends FMReasoningWithBDD {

	private Map<String, Integer> _myVarName2IndexMap = null;
	private BDDFactory _bddFactory = null;

	public MyFMReasoningWithBDD(FeatureModel featureModel,
			VariableOrderingHeuristic voHeuristic, int nodeNum, int cacheSize,
			long maxBuildingtime, ReorderMethod reorderMethod,
			String orderingFormulasStrategy) {
		super(featureModel, voHeuristic, nodeNum, cacheSize, maxBuildingtime,
				reorderMethod, orderingFormulasStrategy);
	}

	public MyFMReasoningWithBDD(splar.core.fm.FeatureModel featureModel,
			VariableOrderingHeuristic heuristic, int bddNodeNum,
			int bddCacheSize, int i, String orderingFormulasStrategy) {
		super(featureModel, heuristic, bddNodeNum, bddCacheSize, i,
				orderingFormulasStrategy);

	}

	/**
	 * @param varToInt
	 *            we provide var to integer mappings
	 * @param aBDDFactory
	 *            we provide the factory
	 * @throws Exception
	 */
	public void init(Map<String, Integer> varToInt, BDDFactory aBDDFactory)
			throws Exception {
		_myVarName2IndexMap = new HashMap<String, Integer>(varToInt);
		this.varName2IndexMap = new HashMap<String, Integer>(
				_myVarName2IndexMap);
		_bddFactory = aBDDFactory;
		this.bddFactory = _bddFactory;
		init();
		/*
		 * this.varIndex2NameMap = new String[varName2IndexMap.size()]; for(
		 * String varName : varName2IndexMap.keySet() ) {
		 * this.varIndex2NameMap[varName2IndexMap.get(varName)] = varName; }
		 */
	}

	@Override
	protected BDD createBDDStructure(long startTime,
			String orderingFormulasStrategy)
			throws BDDExceededBuildingTimeException {
		if (_myVarName2IndexMap != null)
			this.varName2IndexMap = new HashMap<String, Integer>(
					_myVarName2IndexMap); // var2Int={_r0=0, _r2=1, _r1=2,
											// _r3=3}
		if (_bddFactory != null)
			this.bddFactory = _bddFactory;

		return super.createBDDStructure(startTime, orderingFormulasStrategy);
	}

}
