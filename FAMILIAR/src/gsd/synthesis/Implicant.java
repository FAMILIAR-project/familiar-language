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

import java.util.ArrayList;

import net.sf.javabdd.BDD;

/**
 * An implicant is just a list of literals. The convention is that a positive
 * integer n represents a positive literal with variable having index n. A
 * negative integer n represents a negative literal with variable having index
 * -n.
 * 
 * @author Wasowski
 * 
 */
public class Implicant extends ArrayList<Integer> {

/**
 * 
 */
private static final long serialVersionUID = 1L;



public boolean add(Integer v) {

	assert v != null;
	assert v != 0;
	return super.add (v);
}



public Implicant() {

	super ();
}



public String toString() {

	StringBuffer result = new StringBuffer ("{");

	for (Integer i : this) {

		result.append (i).append (".");
	}

	return result.append ("}").toString ();
}



/**
 * This constructor from min term is not tested
 * 
 * @param u
 */
public Implicant(BDD u) {

	super ();

	u.printDot ();
	assert u.satCount () == 1;

	BDD v = u;
	while (!(v.isOne () || v.isZero ())) {

		assert v.var () % 2 == 0;

		if (v.high ().isZero ()) {

			assert v.low ().high ().isZero ();
			assert !v.low ().isOne ();
			v = v.low ().low ();

		} else {

			assert v.low ().isZero ();
			assert v.high ().high ().isZero (); // negative
			// literals
			// only;

			this.add (-v.var () / 2);
			v = v.high ().low ();
		}
	}
}



/**
 * TODO: explain the encoding of implicants in the BDD
 * 
 * @param u
 *                metaproduct encoding of the implicant
 * @param support
 *                the support of the PRIMES bdd
 * @param metaproduct
 *                true if using Coudert's encoding, false if using a direct
 *                encoding, which just stores the presence of literals (not the
 *                sign, as all are negative anyway)
 */
public Implicant(byte[] u, int[] support, boolean metaproduct) {

	super ();

	assert u != null;
	if (support == null)
		return;

	if (metaproduct) {

		for (int i = 0; i < support.length; ++i) {

			if (u[2 * support[i]] == 0)
				continue;

			assert u[2 * support[i] + 1] == 0 : "expected negative literals only";

			this.add (-support[i]);
		}
	} else
		for (int i = 0; i < support.length; ++i)
			if (u[i] != 0)
				this.add (-support[i]);

}



public Implicant removeNegations() {

	Implicant result = new Implicant ();

	for (Integer i : this)
		result.add (-i);
	return result;
}
}
