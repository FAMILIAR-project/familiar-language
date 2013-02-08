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

import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

import com.google.common.collect.Sets;

/**
 * @author mathieuacher
 * 
 */
public class FormulaAnalyzer<T extends Comparable<T>> {

	private Formula<T> _f;
	private BDDBuilder<T> _builder;

	public FormulaAnalyzer(Formula<T> formula, BDDBuilder<T> builder) {
		this._builder = builder;
		this._f = formula;
	}

	public Set<T> computeDeadFeatures() {

		Formula<T> fla = _f.id();
		if (fla.isOne()) 
			return new HashSet<T>();
		if (fla.isZero())
			return fla.getDomain();
		BDD problem = fla.getBDD();
		BDD sup = problem.support();
		int[] support = sup.scanSet();

		sup.free();

		Set<T> dead = new HashSet<T>();

		/*
		 * Reasoning can be applied to check if f is dead: simply check
		 * satisfiability of phi ^ f , concluding that f is dead if the result
		 * is negative, or concluding that f not dead otherwise.
		 */

		for (int v : support) {

			// restricts the variables in this BDD to constant true
			// if they are included in their positive form
			// and constant false if they are included in their negative form
			BDD varToRestrict = problem.id().getFactory().ithVar(v);
			BDD check = problem.id().restrictWith(varToRestrict);
			if (check.isZero()) {
				dead.add(_builder.feature(v));
			}
			check.free();

		}

		return dead;
	}

	/**
	 * @return a new formula with no dead features but an equivalent "domain"
	 */
	public Formula<T> removeDeadFeatures() {
		Set<T> domain = _f.getDomain() ; 
		Set<T> deads = computeDeadFeatures();
		return removeFeatures(deads, domain);
	}
	
	/** sometimes we already know the deads features it avoids costly re-computation 
	 * @param deads
	 * @return
	 */
	public Formula<T> removeDeadFeatures(Set<T> deads) {
		Set<T> domain = _f.getDomain() ; 
		return removeFeatures(deads, domain);
	}

	public Set<T> computeCoreFeatures() {
		/*if (_f.isOne())
			return _f.getDomain();*/
		if (_f.isZero())
			return new HashSet<T>();
		BDD problem = _f.getBDD();
		BDD sup = problem.support();
		int[] support = sup.scanSet();
		sup.free();
		
		if (support == null)
			return new HashSet<T>();

		Set<T> core = new HashSet<T>();

		/*
		 * mendonca2009: Similar reasoning can be applied to check if f is
		 * common: simply check satisfiability of phi ^ !f , concluding that f
		 * is common if the result is negative, or concluding that f not common
		 * otherwise.
		 */

		for (int v : support) {
			BDD check = problem.id().restrictWith(
					problem.getFactory().ithVar(v).not());
			if (check.isZero()) {
				core.add(this._builder.feature(v));
			}
			check.free();
		}

		return core;
	}

	/**
	 * @param fla
	 *            the formula/BDD to process
	 * @param fts
	 *            the set of features to remove
	 * @return a new formula "cleaned" from dead features
	 */
	public Formula<T> removeFeatures(Set<T> fts, Set<T> oDomain) {
		
		// first, collect dead features within a BDD
		int[] deadArr = new int[fts.size()];
		int i = 0;
		for (T d : fts) {
			deadArr[(i++)] = _builder.variable(d);
		}
		BDD problem = _f.getBDD().id();
		BDD exist = problem.getFactory().makeSet(deadArr);
		// second, remove all occurrences of the BDD in variables in the set var
		// by existential quantification.
		BDD result = problem.exist(exist);
		exist.free();

		// finally, remove those features from the domain
		Collection<T> newFeatures = Sets.difference(oDomain, fts);
		return new Formula<T>(result, newFeatures, _builder);
	}

	public double counting() {
		BDD b1 = _f.id().getBDD(); //_f.id().getBDD();
		BDD support = _builder.mkSet(_f.getDomain()); 
		double c = b1.satCount(support);
		b1.free();
		support.free();
		return c;
		
	}

	public Formula<T> uniformize() {
		Set<T> oDomain = _f.getDomain() ; 
		Set<T> support = new HashSet<T>(oDomain); 
		if (oDomain.contains("True") || oDomain.contains("False")) {
			support.remove("False");
			support.remove("True");					
			BDD rBDD = _cleanUp(_f.getBDD()) ; 		
			return new Formula<T>(rBDD, support, _builder);
		}
		else {
			return new Formula<T>(_f.getBDD(), oDomain, _builder);
		}
	}

	
	private BDD _cleanUp(BDD bdd) {
		Set<T> reservedValues = new HashSet<T>();
		reservedValues.add((T) "True");
		reservedValues.add((T) "False");
		
		BDD rBDD = bdd.exist(_builder.mkSet(reservedValues));
		return rBDD ;
	}
}
