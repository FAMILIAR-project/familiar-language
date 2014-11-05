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
package fr.familiar.operations;

import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

/**
 * @author mathieuacher
 * 
 */
public class AllConfigsBDD {

	private Set<Set<String>> _configs;

	private BDDBuilder<String> _builder;

	public AllConfigsBDD(BDDBuilder<String> builder) {
		_builder = builder;

	}

	@Deprecated
	public Set<Set<String>> processOldMethod(Formula<String> fla) {
		_configs = new HashSet<Set<String>>();

		@SuppressWarnings("unchecked")
		Iterator<byte[]> it = (Iterator<byte[]>) fla.getBDD().allsat()
				.iterator();

		int i = 1;
		while (it.hasNext()) {
			byte[] byteConfig = (byte[]) it.next();

			/*
			 * System.err.print("product" + i++ + "=") ; for (int j = 0; j <
			 * byteConfig.length; j++) { System.err.print("" + byteConfig[j] +
			 * "") ; //byteConfig[j] == 0 => not included //byteConfig[j] == -1
			 * => optionally included if (byteConfig[j] != 0 &&
			 * (_builder.feature(j) != null)) System.err.print("(" +
			 * _builder.feature(j) + ")" ); System.err.print(" "); }
			 * System.err.println();
			 */

			// saveConfig(byteConfig);
			recordConfig(byteConfig);
			// debugConfigs() ;

		}
		return _configs;
	}

	public void debugConfigs() {
		int i = 1;
		for (Set<String> config : _configs) {
			System.err.println("config" + i++ + "= " + config);
		}

	}

	private void saveConfig(byte[] sol) {

		Set<String> config = new HashSet<String>();
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] == -1) {

				// include feature
				sol[i] = 1;
				saveConfig(sol);

				// discard feature
				sol[i] = 0;
				saveConfig(sol);

			}
		}

		for (int i = 0; i < sol.length; i++) {
			if (sol[i] == 1) {
				String varName = _builder.feature(i);
				if (varName != null) {
					config.add(varName);
				}
			}
		}

		_configs.add(config);

	}

	private void recordConfig(byte[] sol) {

		// first pass
		Set<String> config = new HashSet<String>();
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] == 1) {
				String varName = _builder.feature(i);
				if (varName != null) {
					config.add(varName);
				}
				sol[i] = 2; // already visited
			}
		}
		// System.err.println("config=" + config);
		_configs.add(config);
		populateConfig(sol, 0, config);

	}

	private Set<String> populateConfig(byte[] sol, int start, Set<String> acc) {

		for (int i = start; i < sol.length; i++) {
			if (sol[i] == -1 && (_builder.feature(i) != null)) {

				// discard feature
				// sol[i] = 0 ;
				Set<String> configWithout = populateConfig(sol, i + 1,
						cloneAccConfig(acc));
				// System.err.println("configWithout=" + configWithout);
				_configs.add(configWithout);

				// include feature
				// sol[i] = 1 ;
				acc.add(_builder.feature(i));
				Set<String> configWith = populateConfig(sol, i + 1,
						cloneAccConfig(acc));
				// System.err.println("configWith=" + configWith);
				_configs.add(configWith);

			}
		}

		return acc;

	}

	private Set<String> cloneAccConfig(Set<String> acc) {
		Set<String> clone = new HashSet<String>();
		clone.addAll(acc);
		return clone;
	}

	public Set<Set<String>> process(Formula<String> fla) {
		
		_configs = new HashSet<Set<String>>();
		if (fla.isZero())
			return _configs;
		BDD.BDDIterator it2 = new BDD.BDDIterator(fla.getBDD().id(),
				_builder.mkSet(fla.getDomain()));

		// int i = 0 ;
		while (it2.hasNext()) {
			BDD b = (BDD) it2.next();
			Set<String> config = BDDOneToConfiguration(b);
			_configs.add(config);
			// System.out.println("config" + i++ + " = " + config.toString()) ;
			// //treatSet(b.scanAllVar()));
		}

		return _configs;

	}

	private Set<String> BDDOneToConfiguration(BDD bdd) {
		if (bdd.isOne())
			return new HashSet<String>();
		List<byte[]> solutions = (List<byte[]>) bdd.allsat();
		assert (solutions.size() == 1);
		byte[] sol = (byte[]) solutions.iterator().next();
		return treatBDDSolution(sol);
	}

	private Set<String> treatBDDSolution(byte[] sol) {

		// first pass
		Set<String> sb = new HashSet<String>();
		for (int i = 0; i < sol.length; i++) {
			if (sol[i] > 0) {
				String varName = _builder.feature(i);
				if (varName != null) {
					sb.add(varName);
				}
			}
		}
		return sb;
	}

	/**
	 * @param fla
	 * @param i
	 * @return the i first configurations of fla
	 */
	public Set<Set<String>> process(Formula<String> fla, int i) {
		_configs = new HashSet<Set<String>>();
		if (fla.isZero())
			return _configs;
		BDD.BDDIterator it2 = new BDD.BDDIterator(fla.getBDD().id(),
				_builder.mkSet(fla.getDomain()));

		int j = 0;
		while (it2.hasNext()) {
			if (j++ >= i)
				break;
			BDD b = (BDD) it2.next();
			Set<String> config = BDDOneToConfiguration(b);
			_configs.add(config);
		}

		return _configs;

	}

}
