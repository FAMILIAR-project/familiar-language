/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package gsd.synthesis;

public class Util {

/**
 * returns Integer.MIN_VALUE for an empty or null array
 *
 * @param elements
 */
public static int max(int[] elements) {

	int result = Integer.MIN_VALUE;

	if (elements != null)
		for (int i : elements)
			result = (result >= i ? result : i);
	return result;
}



/**
 * returns Integer.MAX_VALUE for an empty or null array
 *
 * @param elements
 */
public static int min(int[] elements) {

	int result = Integer.MAX_VALUE;

	if (elements != null)
		for (int i : elements)
			result = (result <= i ? result : i);
	return result;
}



/**
 * returns Integer.MIN_VALUE for an empty or null array
 *
 * @param elements
 */
public static int max(Iterable<Integer> elements) {

	int result = Integer.MIN_VALUE;

	if (elements != null)
		for (int i : elements)
			result = (result >= i ? result : i);
	return result;
}



/**
 * returns Integer.MAX_VALUE for an empty or null array
 *
 * @param elements
 */
public static int min(Iterable<Integer> elements) {

	int result = Integer.MAX_VALUE;

	if (elements != null)
		for (int i : elements)
			result = (result <= i ? result : i);
	return result;
}



public static int[] asIntArray(Integer[] a) {

	int[] result = new int[a.length];
	for (int i = 0; i < a.length; i++)
		result[i] = a[i];
	return result;
}

public static Integer[] asIntegerArray(int[] a) {

	Integer[] result = new Integer[a.length];
	for (int i = 0; i < a.length; i++)
		result[i] = a[i];
	return result;
}

}