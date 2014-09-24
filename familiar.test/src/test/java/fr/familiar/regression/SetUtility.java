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

package fr.familiar.regression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetUtility {

	public static Set<String> selectRandomly(Set<String> names, int per) {
		assert (per > 0 && per <= 100);

		int n = names.size(); // n -> 100
		int x = (per * n) / 100; // x -> per

		// hack, at least two
		if (x <= 1)
			x = 2;

		Set<String> r = selectRandomly_rec(names, x);
		return r;
	}

	public static Set<String> selectRandomly_rec(Set<String> names, int x) {
		Collections.shuffle(new ArrayList<String>(names));
		Set<String> r = new HashSet<String>();
		if (x == 0)
			return r;
		int i = 0;
		Iterator<String> it = names.iterator();
		while (it.hasNext() && (i++ < x)) {
			r.add(it.next());
		}
		return r;
	}
}
