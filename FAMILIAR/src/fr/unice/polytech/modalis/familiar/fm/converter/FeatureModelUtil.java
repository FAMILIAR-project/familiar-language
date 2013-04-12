package fr.unice.polytech.modalis.familiar.fm.converter;

import fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

import java.util.Set;

import org.apache.log4j.Logger;

public class FeatureModelUtil {
	
	private static Logger _LOGGER = Logger.getLogger(FeatureModelUtil.class);
	
	
	public static final String SYNTHETIC_FT_NAME_START = "synth";
	
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
			String groupType = (groupStatus == FeatureEdge.XOR) ? "Xor"
					: (groupStatus == FeatureEdge.OR) ? "Or" : "Mutex"; // string
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

}
