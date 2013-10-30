package fr.familiar.operations;

import java.util.List;

import org.sat4j.specs.ISolver;

import fr.familiar.variable.FeatureModelVariable;

public abstract class SATMerger {

	protected ISolver _iSolver;

	public SATMerger() {

	}

	public abstract boolean mergeFM(List<FeatureModelVariable> olfms, Mode mode);

	public ISolver getSolver() {
		return _iSolver;
	}

}
