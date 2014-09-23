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

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

/**
 * Represents a BDD and its domain.
 *
 * @author Steven She
 */
public class Formula<T extends Comparable<T>> implements Cloneable {
	private final Set<T> _domain;
	private BDD _expr;
	//TODO not sure if this is needed
	private final BDDBuilder<T> _builder;

	public Formula(BDD expr, Collection<T> domain, BDDBuilder<T> builder) {
		_expr = expr;
		_builder = builder;
		_domain = new HashSet<T>(domain);
	}

	@Override
	public Formula<T> clone() {
		return new Formula<T>(_expr.id(), new HashSet<T>(_domain), _builder);
	}

	public Formula<T> andWith(Formula<T> other) {
		_expr.andWith(other.getBDD());
		_domain.addAll(other.getDomain());
		return this;
	}

	public Formula<T> orWith(Formula<T> other) {
		_expr.orWith(other.getBDD());
		_domain.addAll(other.getDomain());
		return this;
	}

	public Formula<T> notWith() {
		BDD not = _expr.not();
		_expr.free();
		_expr = not;
		return this;
	}

	public boolean isZero() {
		return _expr.isZero();
	}

	public boolean isOne() {
		return _expr.isOne();
	}


	public Formula<T> id() {
		return clone();
	}

	public void free() {
		_expr.free();
	}

	public Set<T> getDomain() {
		return _domain;
	}
	public BDD getBDD() {
		return _expr;
	}

	@Override
	public int hashCode() {
		return 103 * _domain.hashCode() + 13 * _expr.hashCode();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Formula) {
			Formula<T> f = (Formula<T>) obj;
			return f._domain.equals(_domain) && f._expr.equals(_expr);
		}
		return false;
	}

	@Override
	public String toString() {
		if (_expr.isZero())
			return "False";
		else if (_expr.isOne())
			return "True";

		return _expr + " d:" + _domain;
	}

}