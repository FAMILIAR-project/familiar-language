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
package fr.unice.polytech.modalis.utils;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<Person>();
		Person p1 = new Person("M");
		Person p2 = new Person("N");
		Person p3 = new Person("O");
		persons.add(p1);
		persons.add(p2);
		persons.add(p3);

		Person q = new Person("Q");

		for (Person p : persons) {
			System.out.println(p.getName());
		}

		for (Person p : persons) {
			p = new Person("K"); // q;
			// p.getName();
		}

		for (Person p : persons) {
			System.out.println(p.getName());
		}

		String firstStr = "first";
		String secondStr = firstStr.concat("second");
		System.out.println("str_1: " + firstStr + " str_2: " + secondStr);

	}

}
