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

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class is used to store arbitrary key/value attributes for vertices and
 * edges in a GraphvizGraph.
 * 
 * @author Steven She <shshe@uwaterloo.ca>
 * 
 * @param <V>
 * @param <E>
 */
public class GraphvizProperties<V, E> {

	private Map<V, Map<String, String>> mVertexProps = new HashMap<V, Map<String, String>>();
	private Map<E, Map<String, String>> mEdgeProps = new HashMap<E, Map<String, String>>();

	/**
	 * Should only be called by GraphvizGraph.
	 */
	public String getEdgePropertiesString(E e) {
		Map<String, String> props = edgeProperties(e);
		if (props.size() == 0)
			return "";
		
		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : props.entrySet()) {
			builder.append(entry.getKey());
			builder.append("=");
			builder.append(entry.getValue());
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	
	/**
	 * Should only be called by GraphvizGraph.
	 */
	public String getVertexPropertiesString(V v) {
		Map<String, String> props = vertexProperties(v);
		if (props.size() == 0)
			return "";

		StringBuilder builder = new StringBuilder();
		for (Entry<String, String> entry : props.entrySet()) {
			builder.append(entry.getKey());
			builder.append("=");
			builder.append(entry.getValue());
			builder.append(",");
		}
		builder.deleteCharAt(builder.length() - 1);
		return builder.toString();
	}

	/**
	 * Can be overridden by a client.
	 */
	public Map<String, String> vertexProperties(V v) {
		Map<String, String> props = mVertexProps.get(v);
		if (props == null) {
			props = new HashMap<String, String>();
			mVertexProps.put(v, props);
		}
		return props;
	}

	/**
	 * Can be overridden by a client.
	 */
	public Map<String, String> edgeProperties(E e) {
		Map<String, String> props = mEdgeProps.get(e);
		if (props == null) {
			props = new HashMap<String, String>();
			mEdgeProps.put(e, props);
		}
		return props;		
	}
}
