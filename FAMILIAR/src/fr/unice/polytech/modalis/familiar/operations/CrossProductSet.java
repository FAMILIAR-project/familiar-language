/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CrossProductSet {

	public static void main(String[] args) {

		new CrossProductSet().crossproduct();

	}

	public Set<List<String>> crossproduct() {

		Set<String> supp1 = readFile(new File(
				"/Users/mathieuacher/Desktop/Supp1.txt"));
		Set<String> supp2 = readFile(new File(
				"/Users/mathieuacher/Desktop/Supp2.txt"));
		Set<String> supp3 = readFile(new File(
				"/Users/mathieuacher/Desktop/Supp3.txt"));
		Set<String> sifm = readFile(new File(
				"/Users/mathieuacher/Desktop/SIFM.txt"));

		Set<Set<String>> ssfim = strToSet(sifm);
		System.out.println("Size: " + ssfim.size());
		System.out.println(ssfim);

		System.out.println("Supp2: " + supp1);

		System.out.println("Supp1: " + supp2);

		Set<Set<String>> supp12 = crossProduct2by2(supp1, supp2);
		Set<Set<String>> supp13 = crossProduct2by2(supp1, supp3);
		Set<Set<String>> supp23 = crossProduct2by2(supp2, supp3);
		Set<Set<String>> supp123 = crossProduct3by3(supp1, supp2, supp3);

		System.out.println("Supp123 : " + supp123.size());
		System.out.println(supp123);

		Set<Set<String>> un = new HashSet<Set<String>>(supp12);
		un.addAll(supp13);
		un.addAll(supp23);

		System.out.println("Union (" + un.size() + ")");
		System.out.println(un);

		Set<Set<String>> unComplete = new HashSet<Set<String>>(un);
		unComplete.addAll(strToSet(supp1));
		unComplete.addAll(strToSet(supp2));
		unComplete.addAll(strToSet(supp3));
		System.out.println("Union with competing (" + unComplete.size() + ")");
		System.out.println(unComplete);

		// toCNF(unComplete);
		System.out.println();

		Set<Set<String>> diff = new HashSet<Set<String>>(ssfim);
		diff.removeAll(unComplete);
		System.out.println(" ** " + diff);
		toNeg(diff);

		System.out.println("++");
		Set<Set<String>> difference = new HashSet<Set<String>>(supp123);
		difference.removeAll(un);
		System.out.println(difference);

		System.out.println("--");
		Set<Set<String>> differenceMinus = new HashSet<Set<String>>(un);
		differenceMinus.removeAll(supp123);
		System.out.println(differenceMinus);

		return null;

	}

	private void toNeg(Set<Set<String>> diff) {

		for (Set<String> s : diff) {

			// disj. clause
			System.out.print("~(");
			for (Iterator<String> iterator = s.iterator(); iterator.hasNext();) {
				String str = iterator.next();
				System.out.print(str);
				if (iterator.hasNext())
					System.out.print(" & ");
			}
			System.out.print(") ");
			System.out.print("& ");
		}
	}

	private void toCNF(Set<Set<String>> unComplete) {

		for (Set<String> s : unComplete) {

			// disj. clause
			System.out.print("(");
			for (Iterator<String> iterator = s.iterator(); iterator.hasNext();) {
				String str = iterator.next();
				System.out.print(str);
				if (iterator.hasNext())
					System.out.print(" & ");
			}
			System.out.print(") ");
			System.out.print("| ");
		}

	}

	public Set<Set<String>> crossProduct3by3(Set<String> p1, Set<String> p2,
			Set<String> p3) {
		Set<Set<String>> cp = new HashSet<Set<String>>();
		for (String c1 : p1) {
			for (String c2 : p2) {
				for (String c3 : p3) {
					cp.add(unionString(c1, c2, c3));
				}

			}
		}

		return cp;
	}

	private Set<String> unionString(String c1, String c2, String c3) {

		String[] sc1 = c1.split(" ");
		String[] sc2 = c2.split(" ");
		String[] sc3 = c3.split(" ");

		Set<String> res = new HashSet<String>();
		for (int i = 0; i < sc1.length; i++) {
			res.add(sc1[i]);
		}

		for (int i = 0; i < sc2.length; i++) {
			String c = sc2[i];
			boolean in = false;
			for (int j = 0; j < sc1.length; j++) {
				if (c.equals(sc1[j]))
					in = true;

			}
			if (!in)
				res.add(c);

		}

		for (int i = 0; i < sc3.length; i++) {
			String c = sc3[i];
			boolean in = false;
			for (int j = 0; j < sc1.length; j++) {
				if (c.equals(sc1[j]))
					in = true;
			}
			for (int j = 0; j < sc2.length; j++) {
				if (c.equals(sc2[j]))
					in = true;
			}

			if (!in)
				res.add(c);

		}

		return res;
	}

	public Set<Set<String>> crossProduct2by2(Set<String> p1, Set<String> p2) {
		Set<Set<String>> cp = new HashSet<Set<String>>();
		for (String c1 : p1) {
			for (String c2 : p2) {
				cp.add(unionString(c1, c2));
			}
		}

		System.out.println(cp.size());
		System.out.println(cp);

		/*
		 * Set<Set<String>>setSupp1 = strToSet(p1); Set<Set<String>>setSupp2 =
		 * strToSet(p2);
		 * 
		 * Set<Set<String>> setSuppUnion = new HashSet<Set<String>>(setSupp1);
		 * setSuppUnion.addAll(setSupp2);
		 * 
		 * System.out.println("++"); Set<Set<String>> difference = new
		 * HashSet<Set<String>>(cp); difference.removeAll(setSuppUnion);
		 * System.out.println(difference);
		 * 
		 * System.out.println("--"); Set<Set<String>> differenceMinus = new
		 * HashSet<Set<String>>(setSuppUnion); differenceMinus.removeAll(cp);
		 * System.out.println(differenceMinus);
		 */

		return cp;

	}

	public Set<Set<String>> strToSet(Set<String> p1) {
		Set<Set<String>> scp1 = new HashSet<Set<String>>();

		for (String c1 : p1) {
			String[] sc1 = c1.split(" ");
			Set<String> ssc1 = new HashSet<String>();
			for (int i = 0; i < sc1.length; i++) {
				if (!ssc1.add(sc1[i]))
					System.err.println(sc1[i]);
			}
			if (!scp1.add(ssc1))
				System.err.println(c1);
		}

		return scp1;
	}

	private Set<String> unionString(String c1, String c2) {

		String[] sc1 = c1.split(" ");
		String[] sc2 = c2.split(" ");

		Set<String> res = new HashSet<String>();
		for (int i = 0; i < sc1.length; i++) {
			res.add(sc1[i]);
		}

		for (int i = 0; i < sc2.length; i++) {
			String c = sc2[i];
			boolean in = false;
			for (int j = 0; j < sc1.length; j++) {
				if (c.equals(sc1[j]))
					in = true;

			}
			if (!in)
				res.add(c);

		}

		return res;

	}

	public Set<String> readFile(File aFile) {

		Set<String> r = new HashSet<String>();

		try {

			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					r.add(line);
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return r;

	}

}
