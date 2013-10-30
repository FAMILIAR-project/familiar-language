package fr.familiar.test.regression;

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
