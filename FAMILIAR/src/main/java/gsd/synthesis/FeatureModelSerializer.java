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
package gsd.synthesis;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.collections15.Closure;
import org.apache.commons.collections15.Factory;
import org.apache.commons.collections15.map.LazyMap;

public class FeatureModelSerializer<T extends Comparable<T>> {

	Map<FeatureNode<T>, String> groupToId
		= LazyMap.decorate(new HashMap<FeatureNode<T>, String>(),
			new Factory<String>() {

		private int groupId = 1;

		public String create() {
			return "G" + groupId++;
		}

	});
	private final boolean _newLine;
	private final FeatureGraphFactory<T> _fgf;

	public FeatureModelSerializer(FeatureGraphFactory<T> fgf, boolean newLine) {
		_fgf = fgf;
		_newLine = newLine;
	}

	/**
	 * FIXME allow user to specify a Transformer to transform feature object to
	 * string. Currently uses {@link #toString()} method.
	 *
	 * @param fm
	 * @return
	 */
	public String toString(FeatureModel<T> fm) {
		final StringBuilder sb = new StringBuilder();

		final FeatureGraph<T> g = fm.getDiagram();
		if (g.isTop())
			return FeatureGraphFactory.DEFAULT_TOP_STRING;
		else if (g.isBottom())
			return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

		final Queue<FeatureNode<T>> rest = new LinkedList<FeatureNode<T>>();

		Closure<FeatureNode<T>> processNode = new Closure<FeatureNode<T>>() {

			public void execute(FeatureNode<T> v) {
				Collection<FeatureNode<T>> children = g.children(v);
				if (children.size() == 0) return;

				rest.addAll(children);

				if (v.equals(g.getTopVertex()))
					sb.append(_fgf.getTopFeature() + ": ");
				else
					sb.append((v.getType() == FeatureType.AND_GROUP
						? groupToId.get(v) : v.getFeature()) + ": ");

				//First, process AND-Groups
				Iterator<FeatureNode<T>> iter = children.iterator();
				while (iter.hasNext()) {
					FeatureNode<T> child = iter.next();
					if (child.getType() == FeatureType.AND_GROUP) {
						String groupId = groupToId.get(child);
						groupToId.put(child, groupId);

						sb.append(groupId).append("=(");
						for (T f : child.features()) {
							sb.append(f.toString()).append("&");
						}
						sb.deleteCharAt(sb.length()-1);
						sb.append(")");

						iter.remove();
					}
				}

				//Process Groups
				for (FeatureEdge e : g.incomingEdges(v)) {
					Set<FeatureNode<T>> sources = g.getSources(e);

					if (sources.size() == 1)
						continue;

					sb.append("(");
					for (FeatureNode<T> m : g.getSources(e)) {
						sb.append(m.getType() == FeatureType.AND_GROUP
								? groupToId.get(m)
								: m.getFeature()).append("|");
					}
					sb.deleteCharAt(sb.length()-1);
					sb.append(")");
					switch (e.getType()) {
					case FeatureEdge.MUTEX:
						sb.append("?");
						break;
					case FeatureEdge.OR:
						sb.append("+");
						break;
					}
					sb.append(" ");
					children.removeAll(sources);
				}

				//Process remaining children
				for (FeatureNode<T> child : children) {
					sb.append(child.getFeature());
					if (g.findEdge(child, v, FeatureEdge.MANDATORY) == null) {
							sb.append("?");
					}
					sb.append(" ");
				}
				sb.append("; ");
				if (_newLine)
					sb.append("\n");
			}
		};

		rest.add(g.getTopVertex());
		while (!rest.isEmpty())
			processNode.execute(rest.poll());

		//process Constraints
		for (Expression<T> e : fm.getConstraints()) {
			sb.append(e.toString()).append(";");
			if (_newLine)
				sb.append("\n");
		}

		return sb.toString();
	}

}