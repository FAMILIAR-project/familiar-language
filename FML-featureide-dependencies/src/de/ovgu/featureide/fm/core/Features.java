/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2015  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 * 
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class Features {

	public static final String FEATURE_SUFFIX = "(Feature)";

	public static final Collection<String> extractOperatorNamesFromFeatuers(final Set<String> features) {
		List<String> result = new ArrayList<>();
		for (String feature : features) {
			final String str = feature.toLowerCase().trim();
			if (Operator.isOperatorName(str))
				result.add(str);
		}
		return result;
	}

	public static Feature getCommonAncestor(Collection<Feature> features) {
		List<Feature> commonAncestorList = null;
		for (Feature feature : features) {
			commonAncestorList = Features.getCommonAncestor(commonAncestorList, feature.getParent());
		}
		return commonAncestorList.get(commonAncestorList.size() - 1);
	}

	public static List<Feature> getCommonAncestor(List<Feature> commonAncestorList, Feature parent) {
		if (commonAncestorList == null) {
			commonAncestorList = new LinkedList<>();
			while (parent != null) {
				commonAncestorList.add(0, parent);
				parent = parent.getParent();
			}
		} else if (parent != null) {
			LinkedList<Feature> parentList = new LinkedList<>();
			while (parent != null) {
				parentList.addFirst(parent);
				parent = parent.getParent();
			}
			final Iterator<Feature> iterator1 = parentList.iterator();
			final Iterator<Feature> iterator2 = commonAncestorList.iterator();
			int i = 0;
			while (iterator1.hasNext() && iterator2.hasNext()) {
				if (!iterator1.next().equals(iterator2.next())) {
					break;
				}
				i++;
			}
			commonAncestorList = commonAncestorList.subList(0, i);
		}
		return commonAncestorList;
	}

}
