/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.experimental.featureide;

import java.util.HashMap;

import org.prop4j.And;
import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.specs.TimeoutException;

import de.ovgu.featureide.fm.core.FeatureModel;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.variable.FeatureModelVariable;

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
