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
import java.util.Set;

/**
 * @author macher1
 *
 */
public class HierarchyMergerFlat extends HierarchyMerger {

	/**
	 * All features are basically added to the "top"
	 * TODO: independent of getFm
	 * @param lfms
	 * @param m
	 * @return
	 */
	@Override
	public FeatureModel<String> build(Collection<FeatureModelVariable> lfms) {
	
		FeatureModel<String> basis = FeatureGraphFactory.mkStringFactory().mkTopModel() ;
		FeatureGraph<String> fg = basis.getDiagram();
		FeatureNode<String> topVertex = fg.getTopVertex();

		Set<FeatureNode<String>> fnodes = new HashSet<FeatureNode<String>>();
		for (FeatureModelVariable fm : lfms) {
			fnodes.addAll(fm.getFm().getDiagram().vertices());
		}

		FeatureModelVariable firstFM = lfms.iterator().next() ; 
		
		
		String rootName = firstFM.root().name() ; 
		FeatureNode<String> rootVertex = null ; 
		for (FeatureNode<String> fn : fnodes) {
			if (fn.getFeature().equals(rootName)) {
				rootVertex = fn ;
				break ; 
			}
		}
	

		for (FeatureNode<String> featureNode : fnodes) {
			if (!featureNode.isBottom() && !featureNode.isTop()
					&& featureNode != topVertex)
				fg.addVertex(featureNode);
		}

		for (FeatureNode<String> featureNode : fnodes) {
			if (!featureNode.isBottom() && !featureNode.isTop()
					&& !featureNode.getFeature().equals(rootName))
				fg.addEdge (featureNode, rootVertex, FeatureEdge.HIERARCHY);
		}

		fg.addEdge (rootVertex, topVertex, FeatureEdge.HIERARCHY);
		fg.addEdge (rootVertex, topVertex, FeatureEdge.MANDATORY);

		return basis;
	}
}
