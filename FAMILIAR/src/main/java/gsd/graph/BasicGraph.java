/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.graph;

import java.util.Collection;
import java.util.Set;

/**
 * This interface is incomplete and should be extended as needed.
 */
public interface BasicGraph<V,E> {
  public E addEdge(V v1, V v2);
  public Set<V> children(V v);
  public Set<V> parents(V v);
  public E findEdge(V v1, V v2);
  public Collection<E> edges();
  public Collection<V> vertices();
  public Collection<E> outgoingEdges(V v);
  public Collection<E> incomingEdges(V v);
  public V getSource(E e);
  public V getTarget(E e);
  public boolean removeEdge(E e);
}
