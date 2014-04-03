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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import uk.ac.shef.wit.simmetrics.similaritymetrics.Levenshtein;

/**
 * @author mathieuacher
 * 
 */
public abstract class StringSimilarity {

	public abstract boolean isSimilar(String str1, String str2);

	public String[] computeCloserElement(String ft1, Set<String> fts2) {
		assert (fts2.size() > 0);
		Map<Float, Set<String>> levScores = new HashMap<Float, Set<String>>();

		Levenshtein lev = new Levenshtein();
		// based on Levenshtein Distance but we can implement another strategy

		boolean foundAtLeastOneCandidate = false;
		for (String ft2 : fts2) {
			if (isSimilar(ft1, ft2)) {
				foundAtLeastOneCandidate = true;
				float levScore = lev.getSimilarity(ft1.toLowerCase(),
						ft2.toLowerCase());
				if (levScores.containsKey(levScore)) {
					Set<String> sfts = levScores.get(levScore);
					sfts.add(ft1);
					levScores.put(levScore, sfts);
				} else {
					Set<String> sft1 = new HashSet<String>();
					sft1.add(ft1);
					levScores.put(levScore, sft1);
				}
			}
		}

		if (!foundAtLeastOneCandidate) {
			return new String[0];
		}

		Set<Float> keys = levScores.keySet();
		List<Float> lkeys = new ArrayList<Float>(keys);
		java.util.Collections.sort(lkeys);

		String[] candidates = new String[levScores.size()];
		int id = 0;
		for (Float key : lkeys) {
			Set<String> vals = levScores.get(key);
			for (String val : vals) {
				candidates[id++] = val;
			}
		}

		assert (id == lkeys.size());

		return candidates;
	}

}
