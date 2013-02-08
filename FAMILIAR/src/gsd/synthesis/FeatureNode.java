package gsd.synthesis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Objects of this class should be immutable.
 *
 * @author shshe
 */
public class FeatureNode<T> implements Cloneable {
  public static final int NONE = 0x0000;

  public static final int TOP = 0x0001;
  public static final int BOTTOM = 0x0002;

  public static final int FREE = 0x0004;
  public static final int DEAD = 0x0008;

  public static final int SYNTHETIC = 0x0010;

  protected int _properties = NONE;

  protected final Collection<T> _features = new ArrayList<T>();
  protected final FeatureType _type;

  public FeatureNode(T feature) {
    _features.add(feature);
    _type = FeatureType.SOLITARY;
  }

  public FeatureNode(T feature, int properties) {
    this(feature);
    _properties = properties;
  }


  public FeatureNode(Collection<T> features) {
    assert features.size() > 0;
    _features.addAll(features);

    if (features.size() == 1)
      _type = FeatureType.SOLITARY;
    else
      _type = FeatureType.AND_GROUP;
  }

  public FeatureNode(Collection<T> features, int properties) {
    this(features);
    _properties = properties;
  }

  public boolean isFree() {
    return (_properties & FREE) == FREE;
  }


  public boolean isDead() {
    return (_properties & DEAD) == DEAD;
  }

  public boolean isTop() {
    return (_properties & TOP) == TOP;
  }

  public boolean isBottom() {
    return (_properties & BOTTOM) == BOTTOM;
  }

  public int getProperties() {
    return _properties;
  }

  public FeatureNode<T> clone() {
    return new FeatureNode<T>(_features, _properties);
  }

  public FeatureNode<T> add(T f) {
    FeatureNode<T> result = new FeatureNode<T>(_features);
    result._features.add(f);
    return result;
  }

  public FeatureNode<T> remove(T f) {
    if (_features.size() == 1)
      throw new IllegalArgumentException("Cannot remove " + f + " from solitary feature!");

    assert  _features.contains(f);

    HashSet<T> removed = new HashSet<T>(_features);
    removed.remove(f);
    FeatureNode<T> result = new FeatureNode<T>(removed);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof FeatureNode) {
      return _features.equals(((FeatureNode<?>)obj)._features);
    }
    return false;
  }


  public Set<T> features() {
    return Collections.unmodifiableSet(new HashSet<T>(_features));
  }

  public T getFeature() {
    if (_features.size() > 1)
      throw new IllegalArgumentException("Cannot obtain single feature from AND-Group!");

    return _features.iterator().next();
  }

  @Override
  public int hashCode() {
    return 1619 * _features.hashCode() - 3;
  }

  @Override
  public String toString() {
    if (isTop())
      return "[TOP] " + _features.iterator().next();
    else if (isBottom())
      return "[BOT] " + _features.iterator().next();

    StringBuilder builder = new StringBuilder();
    ArrayList<String> sorted = new ArrayList<String>();

    for (T f : _features)
      sorted.add(f.toString());
    Collections.sort(sorted);

    for(String s : sorted)
      builder.append(s).append("&");

    builder.deleteCharAt(builder.length()-1);
    return builder.toString();
  }

  public FeatureType getType() {
    return _type;
  }

}