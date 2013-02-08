package fr.unice.polytech.modalis.familiar.fm.converter.tvl;

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
