/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import fr.unice.polytech.modalis.familiar.operations.KSynthesis;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
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
		Set<FeatureNode<String>> roots = KSynthesis
				.findMyRoots(firstFM.getHierarchy().getDiagram());
		assert (roots.size() == 1);
		FeatureNode<String> rootVertex = roots.iterator().next();

		for (FeatureNode<String> featureNode : fnodes) {
			if (!featureNode.isBottom() && !featureNode.isTop()
					&& featureNode != topVertex)
				fg.addVertex(featureNode);
		}

		for (FeatureNode<String> featureNode : fnodes) {
			if (!featureNode.isBottom() && !featureNode.isTop()
					&& featureNode != rootVertex)
				fg.addEdge(featureNode, rootVertex, FeatureEdge.HIERARCHY);
		}

		fg.addEdge(rootVertex, topVertex, FeatureEdge.HIERARCHY);
		fg.addEdge(rootVertex, topVertex, FeatureEdge.MANDATORY);

		return basis;
	}
}
