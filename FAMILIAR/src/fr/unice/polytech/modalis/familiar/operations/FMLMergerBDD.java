/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.experimental.KSynthesisConfiguration;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelCloner;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMerger;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMergerFactory;
import fr.unice.polytech.modalis.familiar.parser.HierarchyMergerStrategy;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelLazyVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableBDDFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableWithSynchronizedFormula;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

/**
 * @author macher1
 *
 */
public class FMLMergerBDD extends FMLMerger {
	

	private static Logger _LOGGER = Logger.getLogger(FMLMergerBDD.class);
	
	private HierarchyMergerStrategy _hierarchyMergerStrategy = _DEFAULT_HIERARCHY_MERGER ;
	
		
	protected BDDBuilder<String> _builder ; 
		
	/**
	 * strategy to encode the resulting formula (i.e., when the FD is not sufficient)
	 */
	private FDOverApproximationStrategy _flaStrategy;
	
	
	public FMLMergerBDD(Collection<FeatureModelVariable> lfvms) {
		this (lfvms, FDOverApproximationStrategy.SYNCHRONIZED_FLA);
	}

	public FMLMergerBDD(Collection<FeatureModelVariable> lfvms, FDOverApproximationStrategy synchronizedFla) {
		this (lfvms, synchronizedFla, FMLCommandInterpreter.getBuilder()) ; 
	}
	
	public FMLMergerBDD(Collection<FeatureModelVariable> lfvms, BDDBuilder<String> builder) {
		this (lfvms, FDOverApproximationStrategy.SYNCHRONIZED_FLA, builder);  
	}
	
	public FMLMergerBDD(Collection<FeatureModelVariable> lfvms, FDOverApproximationStrategy synchronizedFla, BDDBuilder<String> builder) {
		super(lfvms) ; 
		_flaStrategy = synchronizedFla ; 
		_builder = builder ; 
	}

	public FMLMergerBDD(FeatureModelVariable... fm1) {
		this (java.util.Arrays.asList(fm1), FDOverApproximationStrategy.SYNCHRONIZED_FLA);
	}

	@Override
	public FeatureModelVariable union() {
		return mergeFMs(Mode.StrictUnion);
	}
	
	public FeatureModelVariable union(KSynthesisConfiguration kSynthesisConfiguration) {
		_kSynthesisConfiguration = kSynthesisConfiguration ; 
		return union() ;
	}
	
	@Override
	public FeatureModelVariable intersection() {
		return mergeFMs(Mode.Intersection);
	}
	
	
	public static void removeDeadFeaturesFromHierarchy(
			FeatureModel<String> fmProj, Set<String> deads) {

		Set<FeatureNode<String>> nodes = new HashSet<FeatureNode<String>>();
		Set<FeatureEdge> edgesToRemove = new HashSet<FeatureEdge>();
		for (String dead : deads) {
			FeatureNode<String> fNode = fmProj.getDiagram().findVertex(dead);
			if (fNode != null) {
				Collection<FeatureEdge> e = fmProj.getDiagram().outgoingEdges(
						fNode, FeatureEdge.HIERARCHY);
				edgesToRemove.addAll(e);
				nodes.add(fNode);
			}
		}
		fmProj.getDiagram().removeAllEdges(edgesToRemove);
		fmProj.getDiagram().removeAllVertices(nodes);

	}

	
	/**
	 * FIXME we have to make a restriction related to core features
	 * Otherwise very naive: neither efficient nor readable
	 * @param oFormula
	 * @param fmv
	 */
	public static FeatureModelVariable complement(
			Formula<String> originalFormula, FeatureModelVariable fmv,
			BDDBuilder<String> lbuilder) {

		Formula<String> actualFormula = fmv.getFormula().id();
		Formula<String> oFormula = originalFormula.id();

		if (!(oFormula.getBDD().equals(actualFormula.getBDD()))) {

			_LOGGER.debug(
					"Diff formula needed -- constraints are missing");
			_LOGGER.debug(
					"original_domain=" + oFormula.getDomain());
			_LOGGER.debug(
					"actual_domain=" + actualFormula.getDomain());

			// _LOGGER.debug("[original] => " +
			// new
			// AllConfigsBDD(FMLCommandInterpreter.getBuilder()).process(originalFormula));
			// _LOGGER.debug("[actual] => " +
			// new
			// AllConfigsBDD(FMLCommandInterpreter.getBuilder()).process(actualFormula));
			/*
			 * DEBUG FIXME double noriginal = countingFormula(originalFormula) ;
			 * double nactual = countingFormula(actualFormula) ; double
			 * actualMissing = nactual - noriginal; assert (actualMissing > 0);
			 * _LOGGER.debug("#original=" + noriginal
			 * + " #actual=" + nactual);
			 */
			Formula<String> diffFormula = diff(actualFormula, oFormula, lbuilder); // BDD
			// diff

			// double missingProducts = countingFormula(diffFormula);
			// assert (actualMissing == missingProducts);
			// _LOGGER.debug("#diff=" +
			// missingProducts);
			if (diffFormula.getBDD().isZero()) {
				_LOGGER.debug(
						"\t\t (slicing, right?)");
				return fmv;
			}
			// if (actualMissing != missingProducts)
			// _LOGGER.debug("\t\t Unexpected diff!");

			// TODO
			// restrict diffFormuala (no core features already expressed in the
			// hierarchy
			// or even better: prime implicants

			// TODO: CNF/DNF

			FeatureGraph<String> fg = fmv.getFm().getDiagram();
			BDD diffBDD = diffFormula.getBDD();
			Set<String> cores = new FormulaAnalyzer<String>(originalFormula,
					lbuilder).computeCoreFeatures();
			_LOGGER.debug(
					"cores not to consider when writing constraints=" + cores);
			BDD coresBDD = lbuilder.mkSet(cores);
			// coresBDD.andWith(lbuilder.one());
			BDD simplifiedDiffBDD = diffBDD.exist(coresBDD);

			Formula<String> diffSimplifiedFormula = // diffFormula ;
			new Formula<String>(simplifiedDiffBDD, Sets.difference(
					diffFormula.getDomain(), cores), lbuilder);

			AllConfigsBDD allBDD = new AllConfigsBDD(lbuilder);
			Set<Set<String>> sols = allBDD.process(diffSimplifiedFormula);
			Set<Expression<String>> sexpr = new HashSet<Expression<String>>();
			Set<String> allFts = fmv.getFm().features();
			_LOGGER.debug("allFts=" + allFts);
			for (Set<String> sol : sols) {

				Set<FeatureNode<String>> fnodes = new HashSet<FeatureNode<String>>();
				for (String fsol : sol) {
					// if (!ftCores.contains(fsol)) // we do not add core
					// features: DANGEROUS!
					fnodes.add(fg.findVertex(fsol));
				}

				if (fnodes.size() == 0) {
					for (String core : cores) {
						sexpr.add(new Expression<String>(core));
					}
					_LOGGER.debug(
							"strange? We leave it since fnodes.size() == 0 ; sol="
									+ sol);

					// dangerous?

					Set<String> ftsNotIn = new HashSet<String>(allFts);
					ftsNotIn.removeAll(cores);

					if (ftsNotIn.size() == 1)
						sexpr.add(new Expression<String>(ftsNotIn.iterator()
								.next()));
					else if (!ftsNotIn.isEmpty()) { // >= 2
						Expression<String> eNotIn = new Expression<String>(
								ExpressionType.FALSE);
						for (String ftNotIn : ftsNotIn) {
							eNotIn = eNotIn.or(new Expression<String>(ftNotIn));
						}
						sexpr.add(eNotIn);

						/*
						 * Expression<String> eNotIn = new
						 * Expression<String>(ExpressionType.TRUE); for (String
						 * ftNotIn : ftsNotIn) { eNotIn = eNotIn.and(new
						 * Expression<String>(ExpressionType.NOT, new
						 * Expression<String>(ftNotIn), null)); }
						 * sexpr.add(eNotIn.not());
						 */
					} else { // 0??

					}

					continue;
				}

				else {
					Expression<String> esol = ExpressionUtility.mkCNF(fnodes);
					esol = esol.not();
					Set<String> ftsNotIn = new HashSet<String>(allFts);
					ftsNotIn.removeAll(sol);
					ftsNotIn.removeAll(cores);
					// _LOGGER.debug("ftsNotIn=" +
					// ftsNotIn);
					Expression<String> eNotIn = new Expression<String>(
							ExpressionType.TRUE); // ExpressionUtil.mkConjunction(ftCores);
					// //
					for (String ftNotIn : ftsNotIn) {
						eNotIn = eNotIn.and(new Expression<String>(
								ExpressionType.NOT, new Expression<String>(
										ftNotIn), null));
					}

					// _LOGGER.debug("solution=" +
					// sol);
					// _LOGGER.debug("esol=" + esol);
					sexpr.add(new Expression<String>(ExpressionType.IMPLIES,
							eNotIn, esol));
				}
				// sexpr.add(esol);

			}

			_LOGGER.debug("#sexpr=" + sexpr.size());
			_LOGGER.debug("sexpr=" + sexpr);
			if (sexpr.size() != 0) {

				Expression<String> diffExpression = ExpressionUtility.mkConjunction(sexpr); // mkDisjunction(sexpr);
				fmv.getFm().addConstraint(diffExpression);
				// redundant, right!?
				/*
				 * for (Expression<String> expression : sexpr) { if
				 * (!fmv.getFm().addConstraint(expression)) {
				 * _LOGGER.debug(
				 * "\t\tOuuuups, constraint implies by the feature model"); } }
				 */
			}

		}

		return fmv;

	}


	/**
	 * @param lfms
	 *            list of FMs
	 * @param mode
	 *            sunion, intersection, etc.
	 * @return new formula based on the mode and formulas associated to each FM
	 *         of lfms
	 */
	public Formula<String> calculateFormula(Mode mode) {
		BDDMerger merger = new BDDMerger(_builder);
		Formula<String> fmerged = merger.mergeFM(_lfms, mode);
		return fmerged;
	}

	

	
	/**
	 * @param formula2
	 * @param formula1
	 * @param builder
	 * @return the formula representing the set difference btw formula1 and
	 *         formula2
	 */
	public static Formula<String> diff(Formula<String> formula1,
			Formula<String> formula2, BDDBuilder<String> builder) {

		Set<String> diff12 = Sets.difference(formula2.getDomain(),
				formula1.getDomain());

		BDD negateFormula1 = formula1.getBDD();
		for (String featNotIncluded : diff12) {
			_LOGGER.debug(
					"diff feature (12)=" + featNotIncluded);
			negateFormula1 = negateFormula1.or(builder.get(featNotIncluded));

			BDD bddFT = builder.nget(featNotIncluded);
			negateFormula1 = negateFormula1.and(bddFT);
		}

		BDD bdd2 = formula2.getBDD();
		Set<String> diff21 = Sets.difference(formula1.getDomain(),
				formula2.getDomain());

		for (String featNotIncluded : diff21) {
			_LOGGER.debug(
					"diff feature (21)=" + featNotIncluded);
			BDD bFt = builder.get(featNotIncluded);
			assert (bFt != null);
			bdd2 = bdd2.or(bFt);
			BDD nbFt = builder.nget(featNotIncluded);
			assert (nbFt != null);
			bdd2 = bdd2.and(nbFt);
		}

		BDD r = negateFormula1.and(bdd2.not());

		Formula<String> flaMerged = new Formula<String>(r,
				formula1.getDomain(), builder); // FIXME domain should be the
		// non dead features of r

		return flaMerged; // .id() ;

	}

	

	/**
	 * @param mode
	 * @return wrapper/utility function
	 */
	public FeatureModelVariable mergeFMs(Mode mode) {		
		return mergeFMs(mode, false);
	}
	
	

	/**
	 * @param mode
	 * @param lazy if true only the formula is computed
	 * @return
	 */
	public FeatureModelVariable mergeFMs(Mode mode, boolean lazy) {		


		
		/****** we have obtained the formula *****/
		Formula<String> flaMerged = calculateFormula(mode);
		
		if (flaMerged.isZero()) // false
			return FeatureModelVariable.mkFalse(flaMerged, 
					HierarchyMergerFactory.mkMerger(HierarchyMergerStrategy.BASIC, null, null).build(_lfms)); // FIXME parametereize HierarchyMergerStrategy.MST_IMPLICATION_GRAPH

		// first, we eliminate dead features (synthesis algorithm precondition)

		
		BDDBuilder<String> builder = FMLCommandInterpreter.getBuilder();
		//if (mode != Mode.StrictUnion)
			flaMerged = new FormulaAnalyzer<String>(flaMerged, builder)
					.removeDeadFeatures();
		
		// at this step, the formula represents *exactly* the set of
		// configurations expected
		Formula<String> originalFlaMerged = flaMerged ; //s.clone();
		if (lazy)
			return new FeatureModelVariableBDDFormula("", originalFlaMerged, builder);
		
		
		
		/******
		 * 3. now we want to build the FM hierarchy using the synthesis
		 * algorithm
		 *****/

		/****
		 * 3.1 First, we calculate the expected hierarchy (which tries to
		 * maximize the parent-child relation in input FMs)
		 ******/
		
		
		HierarchyMerger hMerger = HierarchyMergerFactory.mkMerger(_hierarchyMergerStrategy, mode, originalFlaMerged); // FIXME: side effect!?
		FeatureModel<String> fmProj = hMerger.build(_lfms) ;
		

		/*** now we actually use the synthesis algorithm ****/

		// the building process takes into account the hierarchy we want
		// (fmProj)

		KSynthesisBDD synthesizer = new KSynthesisBDD(flaMerged, new KnowledgeSynthesis(fmProj.getDiagram()), builder) ;
		//synthesizer.setSynthesisConfiguration(_kSynthesisConfiguration);

		_LOGGER.debug("domain of flaMerged:" + flaMerged.getDomain());
		FeatureModel<String> fgRender = synthesizer.build().getFm(); // resulting
		// FM

		_LOGGER.debug("...done...");
		FeatureModelVariable fmv = new FeatureModelVariable("", fgRender,
				flaMerged);


		if (mode != Mode.StrictUnion && mode != Mode.Union)
			originalFlaMerged = new FormulaAnalyzer<String>(originalFlaMerged,
					builder).removeDeadFeatures();
		if (_flaStrategy == FDOverApproximationStrategy.LAZY_WITH_DIFF_FLA) {
			_LOGGER.debug("...Lazy...");
			fmv = complementLazy(originalFlaMerged, fmv, builder);
		} else if (_flaStrategy == FDOverApproximationStrategy.BRUTE_FORCE_ENUMERATION) {
			fmv = complement(originalFlaMerged, fmv, builder);
		}
		else if (_flaStrategy == FDOverApproximationStrategy.SYNCHRONIZED_FLA) {
			fmv = new FeatureModelVariableWithSynchronizedFormula("", fmv.getFm(), originalFlaMerged);
		}
		else {
			// unsupported
			FMLShell.getInstance().printTODO("FD over approximation strategy unknown " + _flaStrategy);
		}
		// FIXME
		/*
		 * _LOGGER.debug("...cleaning (FIXME)...");
		 * 
		 * fmv = new FeatureModelVariable(assigner,
		 * FeatureModelParser.parseString(fmv.getFm().toString()));
		 * fmv.cleanup();
		 * 
		 * ReduceConstraints<String> implicator = new ReduceConstraints<String>(
		 * fmv.getFm()); implicator.reduceRequiresConstraints();
		 */

		return fmv;

	}



	

	public static Formula<String> calculateFormulas(List<Formula<String>> flas,
			Mode mode, BDDBuilder<String> builder) {
		
		BDDMerger merger = new BDDMerger(builder) ;
		return merger.mergeFormulas(flas, mode) ;
	}

	

	public static FeatureModelVariable complementLazy(
			Formula<String> originalFormula, FeatureModelVariable fmv,
			BDDBuilder<String> builder) {
		// FIXME
		_LOGGER.debug("" + fmv.getSyntacticalRepresentation());
		Formula<String> actualFormula = //new FeatureModelVariable("", 
				//FeatureModelParser.parseString("" + fmv.getFm())).getSPLOTFormula();
				builder.mkFeatureModel(FeatureModelCloner.clone(fmv.getFm())); // fmv.getFormula()
		_LOGGER.debug("...formula of FD computed...");
		// ;
		// //
		// does
		// not
		// work for FMLazyVariable

		Formula<String> oFormula = originalFormula ; //.id();

		Formula<String> diffFormula = diff(actualFormula, oFormula, builder); // BDD
		return new FeatureModelLazyVariable(fmv.getIdentifier(),
				fmv.getFm(), diffFormula, builder);
		/*
		if (!(oFormula.getBDD().equals(actualFormula.getBDD()))) {
			_LOGGER.debug(
					"Diff formula needed -- constraints are missing");
			_LOGGER.debug(
					"original_domain=" + oFormula.getDomain());
			_LOGGER.debug(
					"actual_domain=" + actualFormula.getDomain());
			Formula<String> diffFormula = diff(actualFormula, oFormula, builder); // BDD
			// diff
			// _LOGGER.debug("diffFormula=" +
			// diffFormula) ;
			return new FeatureModelLazyVariable(fmv.getIdentifier(),
					fmv.getFm(), diffFormula, builder);
		} else {
			_LOGGER.debug("No need to complement");
			return fmv;
		}*/
	}

	

}
