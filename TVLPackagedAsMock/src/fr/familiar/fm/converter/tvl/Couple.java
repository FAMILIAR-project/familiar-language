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

package fr.familiar.fm.converter.tvl;

/**
 * 
 * Generic type representing couples.<br />
 * <br />
 * 
 * Ex :<br />
 * new Couple("first", 0)<br />
 * <br />
 * 
 * Is a couple with a string and an integer.<br />
 * 
 * @author Charles Vanbeneden
 * 
 * @param <T1>
 *            Type of the first element of the couple.
 * @param <T2>
 *            Type of the second element of the couple.
 */
public class Couple<T1, T2> {

	private T1 _first;
	private T2 _second;

	/**
	 * 
	 * @param first
	 *            First element of the couple.
	 * @param second
	 *            Second element of the couple.
	 */
	public Couple(T1 first, T2 second) {
		_first = first;
		_second = second;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;

		if (!(other instanceof Couple))
			return false;

		@SuppressWarnings("unchecked")
		Couple<T1, T2> other2 = (Couple<T1, T2>) other;

		if (this.getFirst().equals(other2.getFirst()) && this.getSecond().equals(other2.getSecond()))
			return true;
		return false;
	}

	@Override
	public int hashCode() {
		return getFirst().hashCode() + getSecond().hashCode();
	}

	/**
	 * 
	 * @return The first element.
	 */
	public T1 getFirst() {
		return _first;
	}

	/**
	 * 
	 * @return The second element.
	 */
	public T2 getSecond() {
		return _second;
	}
}
