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

package fr.familiar.experimental.featureide;

import java.util.Set;

import fr.familiar.experimental.BinaryMappingCorrespondence;
import fr.familiar.experimental.MappingCorrespondence;
import fr.familiar.variable.FeatureModelVariable;

public class FMMatcher {

	protected StringSimilarity _strSimilarity;

	public FMMatcher(StringSimilarity strSimilarity) {
		_strSimilarity = strSimilarity;
	}

	public FMMatcher() {
		this(new StringSimilarityStrictEquality());
	}

	@Deprecated
	public MappingCorrespondence<String> computeMatchFeatures(
			FeatureModelVariable fm1, FeatureModelVariable fm2) {
		MappingCorrespondence<String> mapping = new BinaryMappingCorrespondence<String>();

		Set<String> fts1 = fm1.features().names();
		Set<String> fts2 = fm2.features().names();
		for (String ft1 : fts1) {
			for (String ft2 : fts2) {
				if (similarity(ft1, ft2))
					mapping.associateElementTo(ft1, ft2);
			}

		}

		return mapping;
	}

	private String[] closedFt(String ft1, Set<String> fts2) {
		return _strSimilarity.computeCloserElement(ft1, fts2);
	}

	private boolean similarity(String ft1, String ft2) {
		return _strSimilarity.isSimilar(ft1, ft2);
	}

	public void setSimilarity(StringSimilarity strSimilarity) {
		_strSimilarity = strSimilarity;
	}

	public MappingCorrespondence<String> computeOneToOneMappingOrder(
			FeatureModelVariable fm1, FeatureModelVariable fm2) {

		MappingCorrespondence<String> mapping = new BinaryMappingCorrespondence<String>();

		Set<String> fts1 = fm1.features().names();
		Set<String> fts2 = fm2.features().names();
		for (String ft1 : fts1) {
			String[] closedFt = closedFt(ft1, fts2);
			if (closedFt.length > 0) {
				for (int i = 0; i < closedFt.length; i++)
					if (mapping.associateElementTo(ft1, closedFt[i]))
						break;
			} else
				mapping.noMatching(ft1);

		}

		return mapping;
	}

}
