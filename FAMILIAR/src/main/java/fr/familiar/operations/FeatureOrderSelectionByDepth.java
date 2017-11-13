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

package fr.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.familiar.experimental.BreadthFirstFGIterator;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class FeatureOrderSelectionByDepth extends FeatureOrderSelection {

	/**
	 * @param fmv
	 * @param fts
	 */
	public FeatureOrderSelectionByDepth(FeatureModelVariable fmv,
			Collection<String> fts) {
		super(fmv, fts);
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.test.FeatureOrderSelection#compute()
	 */
	@Override
	public List<String> compute() {
		

		List<String> ftsOrdered = sortByDepths(_fmv.getHierarchy().getDiagram());
		if (ftsOrdered.isEmpty())
			return ftsOrdered;
		Set<String> ftsToRemove = new HashSet<String>();
		for (String ft : ftsOrdered) {
			if (!_fts.contains(ft))
				ftsToRemove.add(ft);
		}

		ftsOrdered.removeAll(ftsToRemove);

		return ftsOrdered;
	}
	
	private List<String> sortByDepths(FeatureGraph<String> g) {

		List<String> ordered = new ArrayList<String>();
		BreadthFirstFGIterator<String> iter = new BreadthFirstFGIterator<String>(
				g);
		while (iter.hasNext()) {
			FeatureNode<String> next = iter.next();
			ordered.add(next.getFeature());
		}

		return ordered;

	}

}
