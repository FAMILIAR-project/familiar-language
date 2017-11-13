/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.variable;

import fr.familiar.interpreter.NSFactory;
import fr.familiar.parser.NameSpace;

public class VariableIdentifier {

	private String name;
	private NameSpace ns;

	public VariableIdentifier(String name, NameSpace ns) {
		if (ns == null)
			ns = NSFactory.mkEmpty() ; 
		this.name = name;
		this.ns = ns;
	}

	public VariableIdentifier(String name) {
		this(name, NSFactory.mkEmpty());
	}

	/**
	 * @return the name
	 */
	public String getName() {
		if (name == null)
			return "null";
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ns
	 */
	public NameSpace getNs() {
		return ns;
	}

	/**
	 * @param ns
	 *            the ns to set
	 */
	public void setNs(NameSpace ns) {
		this.ns = ns;
	}

	public boolean sameNameWithNS(String str) {
		return completeName(this).equals(str);
	}

	public boolean sameName(String str) {
		return str.equals(getName());
	}

	public boolean sameName(VariableIdentifier vid) {
		return sameName(vid.getName());
	}

	public boolean sameNameWithNS(VariableIdentifier vid) {
		return sameNameWithNS(completeName(vid));
	}

	/**
	 * @param vid
	 * @return transform a variable identifier into a full qualified identifier
	 *         e.g., ns1.ns2.a
	 */
	public static String completeName(VariableIdentifier vid) {
		if (NameSpace.isEmpty(vid.getNs()))
			return vid.getName();
		return vid.getNs() + NameSpace.DELIMITER + vid.getName();
	}

	public String toString() {
		return completeName(this);
	}

	public boolean same(VariableIdentifier vid) {
		return sameNameWithNS(vid) || sameName(vid);
	}

	@Override
	public boolean equals(Object o) {

		if (!(o instanceof VariableIdentifier))
			return false;

		VariableIdentifier vid = (VariableIdentifier) o;
		return getName().equals(vid.getName()) && ns.equals(vid.getNs());
	}

}
