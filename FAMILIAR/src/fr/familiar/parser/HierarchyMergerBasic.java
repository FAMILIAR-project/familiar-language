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

package fr.familiar.parser;

import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author macher1
 *
 */
public class HierarchyMergerBasic extends HierarchyMerger {

	/**
	 * 
	 */
	public HierarchyMergerBasic() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return a "merged" hierarchy FIFO
	 */
	@Override
	public FeatureModel<String> build(Collection<FeatureModelVariable> lfms) {
	
		assert (lfms != null);
		assert (lfms.size() > 1);

		Iterator<FeatureModelVariable> it = lfms.iterator();
		FeatureModel<String> basis = FeatureGraphFactory.mkStringFactory().mkTopModel() ;
		FeatureGraph<String> diagBasis = basis.getDiagram();

		while (it.hasNext()) {

			FeatureModelVariable fm = it.next();

			// treat the feature model fm
			FeatureGraph<String> diag = fm.getHierarchy().getDiagram();
			Set<FeatureNode<String>> nodes = diag.vertices();

			Set<FeatureNode<String>> nodes2Add = new HashSet<FeatureNode<String>>();
			for (FeatureNode<String> node : nodes) {
				if (!diagBasis.addVertex(node))
					continue; // already there
				else
					nodes2Add.add(node);

			}

			for (FeatureNode<String> node : nodes2Add) {

				// node is the source which is possibly connected to many
				// parents
				Set<FeatureNode<String>> targets = diag.parents(node);

				for (FeatureNode<String> target : targets) {
					diagBasis.addEdge(node, target, FeatureEdge.HIERARCHY);
				}

			}

		}

		return basis;

	}

}
