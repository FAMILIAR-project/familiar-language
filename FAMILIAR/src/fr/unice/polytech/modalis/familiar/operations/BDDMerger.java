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

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModelSerializer;
import gsd.synthesis.Formula;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;

import com.google.common.collect.Sets;

/*
 * BDD-based merge operations
 */

public class BDDMerger {
	
	private static Logger _LOGGER = Logger.getLogger(BDDMerger.class);


	private BDDBuilder<String> _builder;

	public BDDMerger() {
		_builder = new BDDBuilder<String>(FeatureGraphFactory.mkStringFactory());
	}

	public BDDMerger(int nvars) {
		_builder = new BDDBuilder<String>(nvars, 25000000, 2500000,
				FeatureGraphFactory.mkStringFactory());

	}

	public BDDMerger(int nnodes, int cache, int nvars) {
		this(nnodes, cache, nvars, FeatureGraphFactory.mkStringFactory());

	}

	public BDDMerger(int nnodes, int cache, int nvars,
			FeatureGraphFactory<String> _fgf) {
		_builder = new BDDBuilder<String>(nnodes, cache, nvars, _fgf);
	}

	public BDDMerger(BDDBuilder<String> builder) {
		_builder = builder;
	}

	public BDDBuilder<String> getBuilder() {
		return _builder;
	}

	public void setBuilder(BDDBuilder<String> builder) {
		_builder = builder;
	}

	public Formula<String> mergeFM(Collection<FeatureModelVariable> olfms, Mode mode) {

		// TODO -- no side effect (clone first!)
		if (mode == Mode.Diff)
			return mergeDiff(olfms);

		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		for (FeatureModelVariable fm : olfms) {
			// important to clone to avoid side effects
			// lfms.add(fmm.clone()); // buggy with constraints
			FeatureModelVariable clonedFM = (FeatureModelVariable) fm.copy();
			lfms.add(clonedFM);
		}

		/******** negated variables **********/

		// first pass: collect all features in each FM

		Set<String> allFeatures = new HashSet<String>();
		for (FeatureModelVariable fm : lfms) {
			Set<String> features = fm.features().names();
			allFeatures.addAll(features);
		}

		// second pass: for each FM fmm, negate features that are in other FMs
		// but not in fmm
		for (FeatureModelVariable fm : lfms) {
			Set<String> features = fm.features().names() ;
			Set<String> diff = Sets.difference(allFeatures, features);

			// _LOGGER.debug("\tbefore negated=" +
			// serializer.toString(fm));

			// _LOGGER.debug("\tBefore " +
			// ser.toString(fmm));

			for (String featNotIncluded : diff) {
				Expression<String> nf = new Expression<String>(
						ExpressionType.NOT, new Expression<String>(
								featNotIncluded), null);
				fm.addFreeVariableToRoot(featNotIncluded);
				fm.addConstraint(nf);
				// DONT REMOVE
				// the following line is deprecated but we observed speed up: 1000%!!!! when recompiling the formula
				//fm.getDiagram().addFreeVariable(featNotIncluded); 
			}
			
			//fm.fixFreeVariables() ; 

			// check
			// _LOGGER.debug("\tafter negated=" +
			// serializer.toString(fm));
			// _LOGGER.debug("\n");

		}
		/****** merging process: first, initialize currbdd with the first fm *********/

		BDD currbdd = null;
		if (mode == Mode.StrictUnion)
			currbdd = _builder.zero(); // false | A = A
		else if (mode == Mode.Intersection)
			currbdd = _builder.one(); // true & A = A
		Formula<String> mergedFla = new Formula<String>(currbdd, allFeatures,
				_builder);
		// list of formulas first... then merge!
		// int nFM = 0;

		List<Formula<String>> flas = new ArrayList<Formula<String>>();
		for (FeatureModelVariable fm : lfms) {
			// _LOGGER.debug("fm_toMerge_" + nFM++);
			// Set<Expression<String>> csts = fm.getConstraints();
			// _LOGGER.debug("#csts=" + csts.size());

			// _LOGGER.debug("#nodes=" +
			// _builder.getFactory().getNodeTableSize());
			Formula<String> fla = fm.getFormula() ; 
			flas.add(fla);

			// currbdd.simplify(currbdd.support());
			// fmla.free() ;
		}
		int nFla = 0;
		for (Formula<String> fla : flas) {
			_LOGGER.debug("nFla=" + nFla++);
			if (mode == Mode.StrictUnion)
				mergedFla.orWith(fla);
			else if (mode == Mode.Intersection)
				mergedFla.andWith(fla);
		}

		_LOGGER.debug(
				"bdd operations done for merging...");

		return mergedFla;

	}

	/**
	 * TODO: currently unused!
	 * 
	 * @param olfms
	 * @param mode
	 * @return
	 */
	public Formula<String> mergeFMSPLOT(Collection<FeatureModelVariable> olfms,
			Mode mode) {

		// TODO -- no side effect (clone first!)

		if (mode == Mode.Diff)
			return mergeDiff(olfms); // TODO in SPLOT

		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		for (FeatureModelVariable fmm : olfms) {
			// important to clone to avoid side effects
			// lfms.add(fmm.clone()); // buggy with constraints
			lfms.add((FeatureModelVariable) fmm.copy()); // free
																		// variables
																		// :(
			// lfms.add(fmm); // dangerous! (side effect!)
			// lfms.add(fmm);
		}

		/******** negated variables **********/

		// first pass: collect all features in each FM

		Set<String> allFeatures = new HashSet<String>();
		for (FeatureModelVariable fmm : lfms) {

			Set<String> features = fmm.features().names();
			allFeatures.addAll(features);
		}

		// second pass: for each FM fmm, negate features that are in other FMs
		// but not in fmm
		int i = 0;
		for (FeatureModelVariable fm : lfms) {
			Set<String> features = fm.features().names();
			Set<String> diff = new HashSet<String>(allFeatures);
			diff.removeAll(features);

			FeatureGraphFactory<String> gf = FeatureGraphFactory
					.mkStringFactory();
			FeatureModelSerializer<String> ser = new FeatureModelSerializer<String>(
					gf, false);

			// _LOGGER.debug("\tBefore " + i + "" +
			// ser.toString(fm));

			// TODO: free variable
			//String rootName = fm.root().name();
			for (String featNotIncluded : diff) {
				Expression<String> nf = new Expression<String>(featNotIncluded);
				nf = nf.not();

				// fm.getDiagram().addFreeVariable(featNotIncluded); // speed
				// up: 1000%!!!!

				// optional feature of the root (doesnt work is the root is a
				// Xor/Or group)


				// end

				// optional/ free variable feature of any leave feature (for
				// SPLOT compatibility)
				/*
				 * FeatureNode<String> ftSource = new
				 * FeatureNode<String>(featNotIncluded);
				 * fmm.getDiagram().addVertex(ftSource);
				 * Set<FeatureNode<String>> leaves = fmm.getDiagram().leaves();
				 * Iterator<FeatureNode<String>> leavesIt = leaves.iterator() ;
				 * while (leavesIt.hasNext()) { FeatureNode<String> ftTarget =
				 * leavesIt.next(); // first element if
				 * (!ftTarget.equals(ftSource)) {
				 * fmm.getDiagram().addEdge(ftSource, ftTarget,
				 * FeatureEdge.HIERARCHY); break ; }
				 * 
				 * }
				 */
				fm.addFreeVariableToRoot(featNotIncluded);
				fm.addConstraint(nf);

			}
			
			//fm.fixFreeVariables() ; 

			// fm = FeatureModelParser.parseString(fm.toString()) ;
			// check
			// _LOGGER.debug("\tAfter" + i++ + " " +
			// ser.toString(fm));

		}

		/****** merging process: first, initialize currbdd with the first fm *********/

		/*
		MyLogger logger = null;
		try {
			logger = MyLogger.getLogger("output/nSPL5.dat", true);
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	//	TimerLogger tl = new TimerLogger();

	//	tl.start();
		net.sf.javabdd.BDD currbdd = null;

		if (mode == Mode.StrictUnion)
			currbdd = _builder.zero(); // false | A = A
		else if (mode == Mode.Intersection)
			currbdd = _builder.one(); // true & A = A

		int nFM = 0;
		for (FeatureModelVariable fm : lfms) {
			// _LOGGER.debug("#nodes=" +
			// _builder.getFactory().getNodeTableSize());
			_LOGGER.debug("nFM=" + nFM++);
			Formula<String> fmla = mkSPLOTFormula(fm);
			if (mode == Mode.StrictUnion)
				currbdd.orWith(fmla.getBDD()); // currbdd =
												// currbdd.orWith(fmla.getBDD());
			else if (mode == Mode.Intersection)
				currbdd.andWith(fmla.getBDD()); // currbdd =
												// currbdd.andWith(fmla.getBDD());
			// currbdd.simplify(currbdd.support());
			// currbdd.simplify(currbdd.support());

			// currbdd.restrict(currbdd.support());
			// fmla.free() ;

		}

		//tl.stop();

		//logger.trace("\t" + (tl.amountOfTime()) + "");

		// TODO diff

		Formula<String> fmerged = new Formula<String>(currbdd.id(),
				allFeatures, getBuilder());
		// currbdd.free();
		return fmerged;

	}

	/**
	 * @param fm
	 * @return a formula that belongs to the same "universe" / BDDFactory
	 */
	private Formula<String> mkSPLOTFormula(FeatureModelVariable fmv) {
		return fmv.getSPLOTFormulaAligned(_builder);
	}

	private Formula<String> mergeDiff(Collection<FeatureModelVariable> olfms) {

		assert (olfms.size() == 2);

		// no side effect (clone first!)
		List<FeatureModelVariable> lfms = new ArrayList<FeatureModelVariable>();
		for (FeatureModelVariable fmm : olfms) {
			// lfms.add(fmm.clone());
			lfms.add((FeatureModelVariable) fmm.copy()); // TODO: problem with clone
		}

		assert (lfms.size() == 2);

		FeatureModelVariable lfm = lfms.get(0);
		FeatureModelVariable rfm = lfms.get(1);

		Formula<String> lfmla = lfm.getFormula() ;
		Formula<String> rfmla = rfm.getFormula() ; 

		return FMLMergerBDD.diff(lfmla, rfmla, _builder);

	}

	/*
	 * Naive implementations
	 */

	public Formula<String> naiveUnion(Formula<String> f1, Formula<String> f2) {
		return new Formula<String>(f1.getBDD().or(f2.getBDD()), Sets.union(
				f1.getDomain(), f2.getDomain()), this._builder);
	}

	public Formula<String> naiveIntersection(Formula<String> f1,
			Formula<String> f2) {
		return new Formula<String>(f1.getBDD().and(f2.getBDD()), Sets.union(
				f1.getDomain(), f2.getDomain()), this._builder);
	}

	public Formula<String> naiveCross(Formula<String> f1, Formula<String> f2) {
		net.sf.javabdd.BDD f1bdd = f1.getBDD();
		return new Formula<String>(f1bdd.and(f2.getBDD()), Sets.union(
				f1.getDomain(), f2.getDomain()), this._builder);
	}

	public Formula<String> mergeFormulas(List<Formula<String>> flas, Mode mode) {
		if (mode == Mode.Intersection || mode == Mode.StrictUnion) {

			Set<String> fts = computeDomain(flas, mode);
			BDD rBDD = null;
			if (mode == Mode.StrictUnion)
				rBDD = _builder.zero(); // false | A = A
			else if (mode == Mode.Intersection)
				rBDD = _builder.one(); // true & A = A
			Formula<String> mergedFla = new Formula<String>(rBDD, fts, _builder);

			for (Formula<String> fla : flas) {
				Formula<String> nFla = fla.id();
				Set<String> nFts = Sets.difference(fts, fla.getDomain());
				for (String nFt : nFts) {
					BDD nBDD = _builder.nget(nFt);
					nFla.getBDD().andWith(nBDD);
				}

				if (mode == Mode.StrictUnion)
					mergedFla.orWith(nFla);
				else if (mode == Mode.Intersection)
					mergedFla.andWith(nFla);
			}

			return new Formula<String>(rBDD, fts, _builder);
		} else {
			// TODO diff / cross
			return null;
		}

	}

	private Set<String> computeDomain(List<Formula<String>> flas, Mode mode) {

		Set<String> fts = new HashSet<String>();

		boolean firstFla = true;
		for (Formula<String> fla : flas) {
			if (firstFla) {
				firstFla = false;
				fts = fla.getDomain();
			}
			if (mode == Mode.Intersection) {
				fts = Sets.intersection(fts, fla.getDomain());
			}
			if (mode == Mode.StrictUnion) {
				fts = Sets.union(fts, fla.getDomain());
			}
		}

		return fts;
	}

}
