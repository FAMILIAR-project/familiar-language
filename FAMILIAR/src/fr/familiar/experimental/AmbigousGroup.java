package fr.familiar.experimental;

import gsd.synthesis.FeatureNode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AmbigousGroup implements Iterable<FGroup> {

	/**
	 * groups in which a feature node occurs
	 */
	protected Set<FGroup> _ambigs;

	/**
	 * feature node that is present in more than one group
	 */
	protected FeatureNode<String> _fnode;

	public AmbigousGroup(FeatureNode<String> ft) {
		_fnode = ft;
		_ambigs = new HashSet<FGroup>();
	}

	@Override
	public Iterator<FGroup> iterator() {
		return _ambigs.iterator();
	}

	public FeatureNode<String> getFeatureNode() {
		return _fnode;
	}

	public boolean add(FGroup group) {
		return _ambigs.add(group);
	}

	public int size() {
		return _ambigs.size();
	}

	public FGroup getMaxGroup() {
		int max = 0;
		FGroup maxGroup = null;
		for (FGroup ambig : _ambigs) {
			Set<FeatureNode<String>> sources = ambig.getSources();
			int s = sources.size();
			if (s >= max) {
				maxGroup = ambig;
			}
		}
		assert (maxGroup != null);
		return maxGroup;
	}

	public int getSizeMaxGroup() {
		int max = 0;
		for (FGroup ambig : _ambigs) {
			Set<FeatureNode<String>> sources = ambig.getSources();
			int s = sources.size();
			max = Math.max(s, max);
		}
		assert (max > 0);
		return max;
	}

	public AmbigousGroup removeGroups(Set<FeatureNode<String>> fnodes) {

		AmbigousGroup amb = new AmbigousGroup(_fnode);
		for (FGroup ambig : _ambigs) {
			
			boolean toRemove = false ; 
			for (FeatureNode<String> fnode : fnodes) {
				Set<FeatureNode<String>> sources = ambig.getSources() ;
				
				if (sources.contains(fnode)) {
					toRemove = true ;
					continue ; 
				}
			}
			
			if (!toRemove)
				amb.add(ambig);
		}

		return amb;
	}

	public Set<FGroup> getAmbigousFGroups() {
		return _ambigs;
	}

}
