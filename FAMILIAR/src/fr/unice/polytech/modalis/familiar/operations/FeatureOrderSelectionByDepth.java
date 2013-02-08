/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.BreadthFirstVertexIterator;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
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
		BreadthFirstVertexIterator<String> iter = new BreadthFirstVertexIterator<String>(
				g);
		while (iter.hasNext()) {
			FeatureNode<String> next = iter.next();
			ordered.add(next.getFeature());
		}

		return ordered;

	}

}
