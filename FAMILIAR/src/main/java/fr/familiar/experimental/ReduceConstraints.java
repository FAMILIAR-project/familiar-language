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

package fr.familiar.experimental;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Requires;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.graph.SimpleDirectedGraph;

/**
 * @author mathieuacher
 * 
 */
public class ReduceConstraints<T> {

	private FeatureModel<T> _fm;

	public ReduceConstraints(FeatureModel<T> fm) {
		_fm = fm;
	}

	public void reduceRequiresConstraints() {
		SimpleDirectedGraph<T, Requires<T>> implG = _fm.getRequiresGraph();

		// TransitiveReduction.INSTANCE.reduce(_fm.getDiagram());

		List<Requires<T>> toRemove = new ArrayList<Requires<T>>(implG.edgeSet()
				.size());

		FeatureGraph<T> diagram = _fm.getDiagram();
		for (Requires<T> req : implG.edgeSet()) {
			FeatureNode<T> source = diagram.findVertex(req.getAntecedent());
			FeatureNode<T> target = diagram.findVertex(req.getConsequent());
			if ((!(diagram.containsEdge(source, target, FeatureEdge.HIERARCHY)))
					&& (!(diagram.containsEdge(target, source,
							FeatureEdge.MANDATORY)))
					&& (!(containsPath(source, target))))
				continue;
			toRemove.add(req);
		}
		implG.removeAllEdges(toRemove);
	}

	private boolean containsPath(FeatureNode<T> source, FeatureNode<T> target) {
		Set<FeatureNode<T>> ancSource = getTransitiveConnected(source);
		return (ancSource.contains(target));
	}

	private Set<FeatureNode<T>> getTransitiveConnected(FeatureNode<T> v) {
		Set<FeatureNode<T>> result = new HashSet<FeatureNode<T>>();
		result.add(v);

		for (FeatureEdge e : _fm.getDiagram().outgoingEdges(v,
				FeatureEdge.HIERARCHY)) {
			FeatureNode<T> target = _fm.getDiagram().getTarget(e);
			result.addAll(getTransitiveConnected(target));
		}

		collectMandatoryChildren(v, result);
		return result;
	}

	private void collectMandatoryChildren(FeatureNode<T> v,
			Set<FeatureNode<T>> result) {
		for (FeatureEdge e : _fm.getDiagram().incomingEdges(v,
				FeatureEdge.MANDATORY)) {
			FeatureNode<T> source = _fm.getDiagram().getSource(e);
			result.add(source);
			collectMandatoryChildren(source, result);
		}
	}

}
