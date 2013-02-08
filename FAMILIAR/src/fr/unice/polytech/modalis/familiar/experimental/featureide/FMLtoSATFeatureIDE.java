package fr.unice.polytech.modalis.familiar.experimental.featureide;

import java.util.HashMap;

import org.prop4j.And;
import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.specs.TimeoutException;

import de.ovgu.featureide.fm.core.FeatureModel;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

@Deprecated
public class FMLtoSATFeatureIDE {

	private static final int TIMEOUT = 10000;

	private FeatureModelVariable _fmv;
	private FeatureModel _featureModelFeatureIDE = null;
	private Node _rootNode = null;

	public FMLtoSATFeatureIDE(FeatureModelVariable fmv) {
		_fmv = fmv;
	}

	public SatSolver getSATSolver() {
		return mkSatSolver(getRootNode());
	}

	private SatSolver mkSatSolver(Node node) {
		return new SatSolver(node, TIMEOUT);
	}

	public SatSolver simplify(HashMap<Object, Node> map) {
		And node = null; // NodeCreatorTuned.eliminateAbstractVariables((And)getRootNode().toCNF(),
							// map, getFMFeatureIDE());
		return mkSatSolver(node);
	}

	private Node getRootNode() {
		if (_rootNode == null)
			_rootNode = mkRootNode();
		return _rootNode;
	}

	private Node mkRootNode() {
		return null; // NodeCreatorTuned.createNodes(getFMFeatureIDE(), true);
	}

	public long counting() {
		return getSATSolver().countSolutions();
	}

	private FeatureModel getFMFeatureIDE() {
		if (_featureModelFeatureIDE == null)
			_featureModelFeatureIDE = new FMLtoFeatureIDE(_fmv).convert();
		return _featureModelFeatureIDE;
	}

	public String solution() throws TimeoutException {
		return getSATSolver().getSolutions((int) counting());
	}

}
