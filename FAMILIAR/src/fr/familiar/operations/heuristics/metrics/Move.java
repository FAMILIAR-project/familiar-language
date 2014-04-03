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

package fr.familiar.operations.heuristics.metrics;

import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

import java.util.Set;

public class Move {

	private FeatureGraph<String> fmHierarchy;
	private Set<FeatureNode<String>> children;
	private FeatureNode<String> oldParent;
	private FeatureNode<String> newParent;

	public Move(FeatureGraph<String> fmHierarchy, Set<FeatureNode<String>> children, FeatureNode<String> oldParent, FeatureNode<String> newParent) {
		this.fmHierarchy = fmHierarchy;
		this.children = children;
		this.oldParent = oldParent;
		this.newParent = newParent;
	}

	public void perform() {
		for (FeatureNode<String> child : children) {
			// Remove old parent
			fmHierarchy.removeEdge(fmHierarchy.findEdge(child, oldParent, FeatureEdge.HIERARCHY));
			
			// Add the new parent
			fmHierarchy.addEdge(child, newParent, FeatureEdge.HIERARCHY);
		}
	}

	public int getCost() {
		return 1;
	}

	@Override
	public String toString() {
		return children + " : " + oldParent + " -> " + newParent;
	}
}
