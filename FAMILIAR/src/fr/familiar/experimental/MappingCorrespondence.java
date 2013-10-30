/**
 * 
 */
package fr.familiar.experimental;

import java.util.Set;

/**
 * @author mathieuacher
 * 
 */
public abstract class MappingCorrespondence<V> {

	public abstract boolean associateElementTo(V key, V val);

	public abstract V getCorrespondence(V key);

	public abstract Set<V> getKeys();

	public abstract int nbMappings();

	public abstract boolean equals(MappingCorrespondence<V> mc);

	public abstract boolean noMatching(V val);

	public abstract Set<V> getNonMatchingElements();

}
