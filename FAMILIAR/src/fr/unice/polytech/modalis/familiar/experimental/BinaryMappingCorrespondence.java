/**
 * 
 */
package fr.unice.polytech.modalis.familiar.experimental;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author mathieuacher
 * 
 */
public class BinaryMappingCorrespondence<V> extends MappingCorrespondence<V> {

	/*
	 * a bidirectional map is an associative data structure in which both types
	 * can be used as key. Apache commons collections offers a BidiMap interface
	 * with various implementations. but no support for generic Google
	 * collections has an alternative that uses generics: BiMap
	 */

	protected BiMap<V, V> _associations;

	private Set<V> _nomatches = new HashSet<V>();

	public BinaryMappingCorrespondence() {
		_associations = HashBiMap.create();
	}

	@Override
	public boolean associateElementTo(V key, V val) {
		if (_associations.containsKey(key))
			return true;
		if (_associations.containsValue(val))
			return false;
		return (_associations.put(key, val) != null);

	}

	@Override
	public V getCorrespondence(V key) {
		return _associations.get(key);
	}

	@Override
	public Set<V> getKeys() {
		return _associations.keySet();
	}

	@Override
	public int nbMappings() {
		return _associations.size();
	}

	@Override
	public boolean equals(MappingCorrespondence<V> mc) {
		Set<V> lkeys = getKeys();
		for (V key : lkeys) {
			V lmaps = getCorrespondence(key);
			Set<V> okeys = mc.getKeys();
			boolean found = false;
			for (V okey : okeys) {
				V omaps = mc.getCorrespondence(okey);
				if (lmaps.equals(omaps))
					found = true;
			}
			if (!found)
				return false;
		}

		return mc.nbMappings() == nbMappings();

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		Set<V> lkeys = getKeys();
		for (V key : lkeys) {
			V lmaps = getCorrespondence(key);
			sb.append(key + "=[");
			sb.append(lmaps + "");
			sb.append("]\n");
		}

		return sb.toString();

	}

	@Override
	public boolean noMatching(V val) {
		return _nomatches.add(val);
	}

	@Override
	public Set<V> getNonMatchingElements() {
		return _nomatches;
	}
}
