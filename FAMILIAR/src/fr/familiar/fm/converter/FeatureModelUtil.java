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

package fr.familiar.fm.converter;

import fr.familiar.fm.FeatureNodeUtils;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.Set;

import org.apache.log4j.Logger;

public class FeatureModelUtil {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureModelUtil.class);
	
	
	public static final String SYNTHETIC_FT_NAME_START = "synth";


	private static final String XOR_SUFFIX = "Xor";


	private static final String OR_SUFFIX = "Or";


	private static final String MUTEX_SUFFIX = "Mutex";
	
	/**
	 * normalize the feature model _fm (no multi-groups)
	 */
	public static void normalizeMultiGroups(FeatureModel<String> fm) {

		FeatureGraph<String> fg = fm.getDiagram();
		for (String ft : fg.features()) {

			FeatureNode<String> v = fg.findVertex(ft);
			assert (v != null);
			if (FeatureNodeUtils.isMultiGroup(fg, v)) {
				_LOGGER.debug(
						"Multi-group detected for v=" + v
								+ " -- refactoring needed");

				unfoldGroup(fg, v, FeatureEdge.XOR);
				unfoldGroup(fg, v, FeatureEdge.OR);
				unfoldGroup(fg, v, FeatureEdge.MUTEX);

			}

		}

	}
	
	
	/**
	 * for each Xor/Or groups, create a fake node and copy the subtree 1. delete
	 * the hierarchy/groups edge 2. create a fake node (mandatory status of v)
	 * 3. link the new fake node with the old group status (Xor/Or) at the end,
	 * we have only mandatory/optional features
	 * 
	 * @param fg
	 * @param v
	 * @param groupStatus
	 */
	public static void unfoldGroup(FeatureGraph<String> fg,
			FeatureNode<String> v, int groupStatus) {

		assert (groupStatus == FeatureEdge.OR || groupStatus == FeatureEdge.XOR
				|| groupStatus == FeatureEdge.MUTEX || (groupStatus == (FeatureEdge.XOR
				| FeatureEdge.OR | FeatureEdge.MUTEX)));

		// first we need to collect all nodes involved in Xor/Or-groups
		Set<Set<FeatureNode<String>>> groups = FeatureNodeUtils.selectGroups(
				fg, v, groupStatus);
		if (groups.isEmpty())
			return;
		_LOGGER.debug("Unfolding groups: " + groups);
		// 1.
		for (Set<FeatureNode<String>> group : groups) {
			// delete the parent-child relations
			for (FeatureNode<String> xorFt : group) {
				FeatureEdge fe = fg.findEdge(xorFt, v, FeatureEdge.HIERARCHY);
				fg.removeEdge(fe);
			}
			// delete the group as well
			FeatureEdge fe = fg.findEdge(group, v, groupStatus);
			if (fe != null)
				fg.removeEdge(fe);

			// 2.
			// name of the fake node: concatenation of features' names
			StringBuilder sbXor = new StringBuilder();
			for (FeatureNode<String> xorFt : group) {
				sbXor.append(xorFt + "");
			}
			String idGroup = "" + sbXor.hashCode();
			String groupType = (groupStatus == FeatureEdge.XOR) ? XOR_SUFFIX
					: (groupStatus == FeatureEdge.OR) ? OR_SUFFIX : MUTEX_SUFFIX; // string
																		// representation
			FeatureNode<String> fakeG = new FeatureNode<String>(
					SYNTHETIC_FT_NAME_START + idGroup + groupType);
			fg.addVertex(fakeG);

			if (groupStatus == FeatureEdge.MUTEX) { // optional if mutex, no
													// need to set mandatory
													// status
				fg.addEdge(fakeG, v, FeatureEdge.HIERARCHY);
			} else {
				fg.addEdge(fakeG, v, FeatureEdge.HIERARCHY);
				fg.addEdge(fakeG, v, FeatureEdge.MANDATORY);
			}

			// 3.
			if (groupStatus == FeatureEdge.MUTEX) { // A : (B|C)? ==> A :
													// MutexFake? ; MutexFake :
													// (B|C);
				for (FeatureNode<String> ft : group) {
					fg.addEdge(ft, fakeG, FeatureEdge.HIERARCHY);
				}

				fg.addEdge(group, fakeG, FeatureEdge.XOR);
			} 
			else if (groupStatus == FeatureEdge.XOR) { 
				for (FeatureNode<String> ft : group) {
					fg.addEdge(ft, fakeG, FeatureEdge.HIERARCHY);
				}

				fg.addEdge(group, fakeG, FeatureEdge.XOR);
			}
			else if (groupStatus == FeatureEdge.OR) { 
				for (FeatureNode<String> ft : group) {
					fg.addEdge(ft, fakeG, FeatureEdge.HIERARCHY);
				}

				fg.addEdge(group, fakeG, FeatureEdge.OR);
			}		
			
			else
				fg.addEdge(group, fakeG, groupStatus);

		}

	}


	public static boolean isSynthetic(String ftName) {
		return ftName != null 
				&& !ftName.isEmpty() 
				&& ftName.startsWith(SYNTHETIC_FT_NAME_START)
				&& (ftName.endsWith(XOR_SUFFIX)
						|| ftName.endsWith(OR_SUFFIX)
						|| ftName.endsWith(MUTEX_SUFFIX)) ; 
	}

}
