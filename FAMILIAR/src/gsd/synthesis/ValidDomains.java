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

import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

/**
 * @author Wasowski
 */
public class ValidDomains {

private BDD u;

private Set<Integer> mayBeFalse = new HashSet<Integer> ();
private Set<Integer> mayBeTrue = new HashSet<Integer> ();
private Set<BDD> visited = new HashSet<BDD> ();

int min_var = Integer.MAX_VALUE;
int max_var = -1;

/**
 * returns the number of consistent variables (so variables for which
 * valid domain contains some values). Note that it should be that
 * either all variables are consistent or none of them.
 * @return
 */
public int size() {

	Set<Integer> temp = new HashSet<Integer> ();
	temp.addAll (mayBeFalse);
	temp.addAll (mayBeTrue);

	return temp.size();
}

public boolean isEmpty() {

	assert !visited.isEmpty ()
		|| (mayBeFalse.isEmpty () && mayBeTrue.isEmpty ());
	assert visited.isEmpty ()
		|| !(mayBeFalse.isEmpty () && mayBeTrue.isEmpty ());

	return visited.isEmpty ();
}



private boolean longArc(BDD u, int level) {

	return (u.isOne () && level <= max_var)
		|| (!u.isZero () && level < u.var ());
}



private void f(BDD u, int level) {

	if (level > max_var || u.isZero ())
		return;

	if (longArc (u, level)) {

		mayBeFalse.add (level);
		mayBeTrue.add (level);
		f (u, level + 1);
		return;
	}

	if (visited.contains (u))
		return;

	assert level < min_var || level == u.var ();

	visited.add (u.id ());

	if (level == u.var ()) {

		if (!u.low ().isZero ())
			mayBeFalse.add (level);
		if (!u.high ().isZero ())
			mayBeTrue.add (level);
	}

	f (u.low (), level + 1);
	f (u.high (), level + 1);
}



public ValidDomains(BDD problem, int min_var, int max_var) {

	assert !problem.isZero ();
	this.min_var = min_var;
	this.max_var = max_var;
	compute (problem);
}



public ValidDomains(BDD problem) {

	assert !problem.isZero ();
	BDD s = problem.support ();
	int[] isup = s.scanSet ();
	if (isup != null && isup.length > 0) {
		max_var = Util.max (isup);
		min_var = Util.min (isup);
	}
	s.free ();
	compute (problem);
}



/**
 * Computes ValidDomains for the problem. The BDD has to be freed by the caller.
 */
private void compute(BDD problem) {

	u = problem; // this is *not* creating a reference in JavaBDD
	f (u, min_var);
	this.free ();
	u = null;
}



private void free() {

	for (BDD u : visited)
		u.free ();
	visited.clear ();
}



public boolean canBeOne(int var) {

	assert (var >= min_var) && (var <= max_var) : var + ": " + min_var + " - " + max_var;
	assert mayBeTrue.contains (var) || mayBeFalse.contains (var);
	return mayBeTrue.contains (var);
}



public boolean canBeZero(int var) {

	assert (var >= min_var) && (var <= max_var);
	assert mayBeTrue.contains (var) || mayBeFalse.contains (var);
	return mayBeFalse.contains (var);
}

}