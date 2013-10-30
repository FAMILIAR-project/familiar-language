/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.fm;

import gsd.synthesis.FeatureNode;

import java.util.HashSet;
import java.util.Set;

public class SubTree {
	Set<SubTree> _children = new HashSet<SubTree>();
	private final FeatureNode<String> _root;
	private boolean _isMandatory = false;

	public SubTree(FeatureNode<String> root) {
		this._root = root;
	}

	public boolean isMandatory() {
		return this._isMandatory;
	}

	public Set<SubTree> children() {
		return this._children;
	}

	public Set<FeatureNode<String>> childrenAsFeatureNodes() {
		Set<FeatureNode<String>> result = new HashSet<FeatureNode<String>>();
		for (SubTree tree : this._children) {
			result.add(tree.featureNode());
		}
		return result;
	}

	public void setMandatory(boolean isMandatory) {
		this._isMandatory = isMandatory;
	}

	public FeatureNode<String> featureNode() {
		return this._root;
	}
}
