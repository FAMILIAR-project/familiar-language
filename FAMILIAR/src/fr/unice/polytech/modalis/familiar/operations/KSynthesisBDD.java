package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.experimental.AmbigousGroup;
import fr.unice.polytech.modalis.familiar.experimental.ConstraintAdder;
import fr.unice.polytech.modalis.familiar.experimental.ConstraintSimplifierStrategy;
import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.experimental.MutexGroup;
import fr.unice.polytech.modalis.familiar.experimental.OrGroup;
import fr.unice.polytech.modalis.familiar.experimental.XorGroup;
import fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.featureide.FMLConstraintReasoner;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariableWithSynchronizedFormula;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;
import gsd.synthesis.Implicant;
import gsd.synthesis.PrimeImplicants;
import gsd.synthesis.ValidDomains;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import com.google.common.collect.Sets;

/**
 * @author macher
 * TODO: interactive mode (experimental branch)
 */

public class KSynthesisBDD extends KSynthesis  {
	
	private static Logger _LOGGER = Logger.getLogger(KSynthesisBDD.class);
	
	/**
	 * formula to synthesise
	 * TODO: we only support BDD but it is straightforward to use SAT then 
	 * (we have all the "basics" operations implemented)
	 */

	
	private BDDBuilder<String> _builder;
	
	/**
	 * Assigned in mkMutexGroups (should refactor this out)
	 */
	private Collection<Set<FeatureNode<String>>> _mutexCliques;
	
	protected static final ConstraintSimplifierStrategy DEFAULT_CST_SIMPLIFIER_STRATEGY = ConstraintSimplifierStrategy.NONE ; 
	protected ConstraintSimplifierStrategy _simplificatorStrategy = DEFAULT_CST_SIMPLIFIER_STRATEGY;
	

	public KSynthesisBDD(FeatureModelVariable fmv, BDDBuilder<String> builder) {
		this (fmv, new KnowledgeSynthesis(), builder) ;  
	}
	
	public KSynthesisBDD(FeatureModelVariable fmv, KnowledgeSynthesis kn, BDDBuilder<String> builder) {
		 this (fmv.getFormula().clone(),
				mkKnowledgeSynthesis (fmv, kn),
				 builder
		 ) ;
	}
	
	public KSynthesisBDD(Formula<String> formula, BDDBuilder<String> builder) {
		this (new BDDFormula(formula), new KnowledgeSynthesis(), builder) ;
	}

	public KSynthesisBDD(Formula<String> formula, KnowledgeSynthesis kn, BDDBuilder<String> builder) {
		_fla = new BDDFormula(formula);
		_kn = kn ; 
		_builder = builder ; 
	}

	

	@Override
	public FeatureModelVariable build() {
		// 1. PREPROCESSING STEP (of the formula)
		// Note that we actually assume that "fla" has no dead features
		Formula<String> lFla = new FormulaAnalyzer<String>((BDDFormula) _fla, _builder).uniformize() ; 
		Set<String> support = lFla.getDomain() ;
		
		
		if (!_kn.isHierarchySpecified()) {
			FMLShell.getInstance().printTODO("Currently, we expect that at least the hierarchy is specified... (will be fixed soon)") ;
			return null ; 
		}
		
		
		FeatureGraph<String> fmHierarchy = _kn.getHierarchy() ; 
		// FIXME could be a pre-condition 
		// in some situations (merge intersection, slice with "anomalies", cleanup, etc.) 
		// we should remove the features included in the intended hierarchy but actually dead features 
		Set<String> deadFtsHiearchy = Sets.difference(fmHierarchy.features(), support);
		_LOGGER.debug("deadFtsHiearchy: " + deadFtsHiearchy);
		cleanHierarchy(new FeatureModel<String>(fmHierarchy), deadFtsHiearchy);
		
		assert (support.equals(fmHierarchy.features()));
		
		FeatureModel<String> synthesisedFM = new FeatureModel<String>(fmHierarchy.clone());
		FeatureGraph<String> synthesisedFD = synthesisedFM.getDiagram();

		mkSyntheticRoot(synthesisedFD);
		
		// 2. IMPLICATION/EXCLUSION (aka MUTEX) GRAPH

		_LOGGER.debug("Implication graph");
		ImplicationGraph<String> impl = IGBuilder.build(lFla, _builder);
		
		
		
		_LOGGER.debug("Exclusion graph");
		ExclusionGraph<String> excl = EGBuilder.build(lFla, _builder) ; 

		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		_LOGGER.debug("Cliques: " + cliques);

		
		// 3. FIXING mandatory features
		_LOGGER.debug("And-groups");
		mkHierarchyAndGroups(synthesisedFD, cliques); 


		// 4. FIXING groups
		_LOGGER.debug("Mutex graph / groups");
		Set<MutexGroup> mutexes = computeMutexGroups(synthesisedFD, lFla); // replace by excl!
		_LOGGER.debug("Mutex computation (based on hierarchy)=" + mutexes);
		
		Set<FGroup> allGroups = new HashSet<FGroup>();
		if (!hasOrGroupSupport()) {
			Set<XorGroup> xors = computeXorBasedOnMutexGroups(lFla, mutexes);
			_LOGGER.debug("xors=" + xors);
			allGroups = Sets.union(xors, mutexes);
		} else {
			_LOGGER.debug("Prime implicants / Or/Xor groups");
			Set<FGroup> xOrOrs = computeXorOrGroups(synthesisedFD, lFla);
			_LOGGER.debug("Xor/Or computation (based on hierarchy)=" + xOrOrs);
			allGroups = Sets.union(xOrOrs, mutexes);
		}

		// normalization
		_LOGGER.debug("All groups computation (based on hierarchy)=" + allGroups);
				
		Set<FGroup> allGroupsNormalized = simplifyMutexXor(allGroups);
		_LOGGER.debug("After Xor/Mtx subsume: " + allGroupsNormalized);
		
		// sort ambigous groups by "feature"
		Set<AmbigousGroup> ambigousGroups = ambigousGroups(allGroupsNormalized);
		
		
		Set<FGroup> resolvedGroups = allGroupsNormalized ;
		Set<FGroup> unsynthesisedGroups = new HashSet<FGroup> () ; 
		if (ambigousGroups.size() > 0) {
			// TODO: knowledge needed!
			Set<FGroup> knGroups = _kn.getGroups() ;
			// TODO implement the resolving strategy
			if (knGroups.size() > 0) {
					// TODO: it can be not sufficient!
			}
			else { // no knowledge related to groups!
				setConflictingGroups(new HashSet<AmbigousGroup>(ambigousGroups)); 				
				resolvedGroups = performBasicResolvingGroupStrategy(allGroupsNormalized, ambigousGroups) ;
				
				// unsynthesised feature groups can be restitued as arbitary set of constraints
				unsynthesisedGroups = Sets.difference(allGroupsNormalized, resolvedGroups) ;
				
			}
			// if knowledge is not sufficient
		}
		else {
			_LOGGER.debug("No ambiguity!");
		}
				
		
		_LOGGER.debug("All groups (elected)=" + resolvedGroups);
		
		_LOGGER.debug("Setting variability information");
		setFGroupsInformation(synthesisedFD, resolvedGroups);

		_LOGGER.debug("-- end of feature DIAGRAM synthesis -- ");
		_LOGGER.debug("resulting FD without implies/excludes constraints: " + synthesisedFD);
		
		
		
		
		ConstraintAdder cAdder = getAdderConstraint(_fdAddingCstStrategy, synthesisedFM);
		_LOGGER.debug("unsynthesized feature groups=" + unsynthesisedGroups);
		for (FGroup fGroup : unsynthesisedGroups) {
			Expression<String> expr = fGroup.toExpression() ;
			Collection<Expression<String>> exprs = ExpressionUtil.splitConjunction(expr) ;
			for (Expression<String> e : exprs) {
				boolean b = cAdder.addNonEntailedConstraint(e) ;
				_LOGGER.debug("Adding " + e + "? " + b); 
			}
			

			
		}
			
		//cAdder.apply() ; 
	

		// 5. SETTING CONSTRAINTS 
		_LOGGER.debug("Implies / Excludes");
	
		synthesisedFM = complementWithImpliesAndExcludes(synthesisedFM, impl, excl); 
		
		_LOGGER.debug("...Eliminate redundant if any (FIXME since opt)...");
		// simplify (not necessary normally since we add incrementally the edges)
		if (_simplificatorStrategy != ConstraintSimplifierStrategy.NONE) {
		// FIXME @FeatureIDE
			synthesisedFM = new FMLConstraintReasoner(synthesisedFM, _builder).eliminateRedundantConstraints();
		}
		_LOGGER.debug("End synthesis");
	
		
		
		return new FeatureModelVariableWithSynchronizedFormula("", synthesisedFM, lFla);
	}
	
	

	

	public Set<MutexGroup> computeMutexGroups(FeatureGraph<String> g,
			Formula<String> fla) {

		// TODO: replace by

		Set<MutexGroup> mutexed = new HashSet<MutexGroup>();

		// Make Mutex Graph
		UndirectedGraph<FeatureNode<String>, DefaultEdge> mutex = new SimpleGraph<FeatureNode<String>, DefaultEdge>(
				DefaultEdge.class);

		for (FeatureNode<String> v : g.vertices())
			mutex.addVertex(v);

		List<Integer> support = support(fla);

		for (FeatureNode<String> v : g.vertices()) {

			FeatureNode<String>[] siblings = g.children(v).toArray(
					new FeatureNode[0]);

			for (int i = 0; i < siblings.length; i++) {

				FeatureNode<String> s1 = siblings[i];
				BDD mx = fla.getBDD().id().andWith(_builder.mkConjunction(s1));

				if (mx.isZero())
					throw new UnsupportedOperationException(
							"Dead features should have been removed!");

				ValidDomains vd = new ValidDomains(mx,
						Collections.min(support), Collections.max(support));

				for (int j = i + 1; j < siblings.length; j++) {
					FeatureNode<String> s2 = siblings[j];

					// Only need to check one of the features (if it's an
					// and-group)
					int v2 = _builder.variable(s2.features().iterator().next());
					if (!vd.canBeOne(v2) && vd.canBeZero(v2))
						mutex.addEdge(s1, s2);
				}
				mx.free();
			}
		}

		// Create Mutex Groups
		_LOGGER.debug("mutex:" + mutex);
		_mutexCliques = findMutexCliques(mutex);
		_LOGGER.debug("cliques:" + _mutexCliques);
		for (Set<FeatureNode<String>> clique : _mutexCliques) {

			FeatureNode<String> parent = commonParent(g, clique);

			// Mutex group doesn't have a common parent ?
			if (parent == null) {
				_LOGGER.debug(
						"Mutex group without a common parent: " + clique);
				continue;
			}

			mutexed.add(new MutexGroup(clique, parent));
		}

		return mutexed;

	}
	

	
	/**
	 * an Xor-group is a special kind of Mutex-group (avoid the Czarnecki et al.
	 * 2007 prime implicants methods)
	 * 
	 * @param f
	 * @param mutexes
	 * @return
	 */
	public Set<XorGroup> computeXorBasedOnMutexGroups(Formula<String> f,
			Set<MutexGroup> mutexes) {

		Set<XorGroup> xors = new HashSet<XorGroup>();
		for (MutexGroup mutexGroup : mutexes) {
			if (mutexGroup.isXorGroup(f, _builder)) {
				XorGroup x = new XorGroup(mutexGroup.getSources(),
						mutexGroup.getTarget());
				xors.add(x);

			}

		}

		return xors;
	}
	
	
	
	/**
	 * in one pass, Or/Xor
	 * 
	 * @param g
	 * @param lFla 
	 * @return
	 */
	public Set<FGroup> computeXorOrGroups(FeatureGraph<String> g, Formula<String> lFla) {

		Set<FGroup> xOrOrs = new HashSet<FGroup>();
		Set<FeatureNode<String>> vertices = g.vertices() ; 
		
		/*Sets
				.difference(g.vertices(), g.leaves()); */ // opt

		for (FeatureNode<String> v : vertices) {
			// for the purpose of scalability, you can uncomment
			/*
			 * if (FeatureNodeUtils.isRoot(v, g)) {
			 * _LOGGER.debug("root=" + v + "");
			 * continue ; }
			 */
			_LOGGER.debug("v=" + v);

			BDD b = _builder.nget(v.getFeature());
			// project formula on v and its children
			// keep only 1 feature in an and-group
			Set<String> keep = new HashSet<String>();
			keep.add(v.getFeature());

			
			
			for (FeatureNode<String> child : g.children(v)) {
				if (!FeatureNodeUtils.isMandatory(g, child)) // a mandatory
																// feature
																// cannot be in
																// an Or-group
					keep.add(child.getFeature());
			}

			Collection<String> remove = CollectionUtils.subtract(lFla.getDomain(), keep);
			BDD exist = _builder.mkSet(remove);
			BDD frag = lFla.getBDD().id().exist(exist);

			// Debug code
			/*
			 * BDD supp = frag.support(); int[] var = supp.scanSet(); int sibs =
			 * var == null ? 0 : var.length; maxsib = Math.max(maxsib, sibs);
			 * maxnodesize = Math.max(maxnodesize, frag.nodeCount());
			 * supp.free();
			 */

			PrimeImplicants pi = new PrimeImplicants(b, frag);
			frag.free();
			exist.free();
			b.free();

			for (Implicant imp : pi) {
				Implicant imp1 = imp.removeNegations();

				Set<FeatureNode<String>> grouped = new HashSet<FeatureNode<String>>();
				for (int i : imp1) {
					// 24-Apr added to handle new domains passed with Formula

					if (lFla.getDomain().contains(_builder.feature(i)))
						grouped.add(g.findVertex(_builder.feature(i)));
				}

				if (grouped.size() < 2 || grouped.contains(v))
					continue;
				assert g.children(v).containsAll(grouped);

				// if (g.findEdge(grouped, v, FeatureEdge.XOR) != null)
				// continue;
				if (xOrOrs.contains(new XorGroup(grouped, v)))
					continue;
				// TODO maybe better to do a restrict on frag and calculate
				// ValidDomains

				if (_mutexCliques.contains(grouped)) {
					xOrOrs.add(new XorGroup(grouped, v));
				} else
					xOrOrs.add(new OrGroup(grouped, v));
			}

			pi.freeCache();
		}

		return xOrOrs;
	}
		
	
	
	
	public Set<MutexGroup> computeALLMutexGroups(FeatureGraph<String> g,
			Formula<String> fla, ImplicationGraph<String> impl) {

		// TODO: replace by

		Set<MutexGroup> mutexed = new HashSet<MutexGroup>();

		// Make Mutex Graph
		UndirectedGraph<FeatureNode<String>, DefaultEdge> mutex = new SimpleGraph<FeatureNode<String>, DefaultEdge>(
				DefaultEdge.class);

		for (FeatureNode<String> v : g.vertices())
			mutex.addVertex(v);

		List<Integer> support = support(fla);

		for (FeatureNode<String> v : g.vertices()) {

			Set<FeatureNode<String>> descV = new HashSet<FeatureNode<String>>() ; 
			descV.addAll(g.vertices());
			descV.remove(g.getBottomVertex()) ; 
			descV.remove(g.getTopVertex()) ; 
			descV.remove(v) ; 
			FeatureNode<String>[] siblings = descV.toArray(new FeatureNode[0]);

			for (int i = 0; i < siblings.length; i++) {

				FeatureNode<String> s1 = siblings[i];
				BDD mx = fla.getBDD().id().andWith(_builder.mkConjunction(s1));

				if (mx.isZero())
					throw new UnsupportedOperationException(
							"Dead features should have been removed!");

				ValidDomains vd = new ValidDomains(mx,
						Collections.min(support), Collections.max(support));

				for (int j = i + 1; j < siblings.length; j++) {
					FeatureNode<String> s2 = siblings[j];

					// Only need to check one of the features (if it's an
					// and-group)
					int v2 = _builder.variable(s2.features().iterator().next());
					if (!vd.canBeOne(v2) && vd.canBeZero(v2))
						mutex.addEdge(s1, s2);
				}
				mx.free();
			}
		}

		// Create Mutex Groups
		_mutexCliques = findMutexCliques(mutex);
		_LOGGER.debug("cliques:" + _mutexCliques);
		_LOGGER.debug("IG:" + impl.edgeSet());
		for (Set<FeatureNode<String>> clique : _mutexCliques) {
						
			Set<FeatureNode<String>> parents = Sets.difference(g.vertices(), clique);
			for (FeatureNode<String> parent : parents) {
				if (parent.isTop() || parent.isBottom())
					continue ; 
				
				boolean parentElected = true ;
				for (FeatureNode<String> cliqueEl : clique) {
					
					Set<String> acns = impl.ancestors((String) cliqueEl.getFeature());
					_LOGGER.debug("ancestors of " + cliqueEl + " is " + acns);
					
					if (acns.size() == 0 || !acns.contains(parent.getFeature())) {
						parentElected = false ;
						_LOGGER.debug("parent: " + parent + " not elected for clique: " + clique);
						break ; 
					}
				}
				if (parentElected)
					mutexed.add(new MutexGroup(clique, parent));
				
				
			}

			
		}

		return mutexed;

	}
	
	/**
	 * in one pass, Or/Xor
	 * 
	 * @param g
	 * @return
	 */
	public Set<FGroup> computeALLXorOrGroups(FeatureGraph<String> g, Formula<String> fla) {

		Set<FGroup> xOrOrs = new HashSet<FGroup>();
		Set<FeatureNode<String>> vertices = g.vertices() ; 
		
		/*Sets
				.difference(g.vertices(), g.leaves()); */ // opt

		for (FeatureNode<String> v : vertices) {
			// for the purpose of scalability, you can uncomment
			/*
			 * if (FeatureNodeUtils.isRoot(v, g)) {
			 * _LOGGER.debug("root=" + v + "");
			 * continue ; }
			 */
			_LOGGER.debug("v=" + v);
			if (v.isTop() || v.isBottom())
				continue ; 

			BDD b = _builder.nget(v.getFeature());
			// project formula on v and its children
			// keep only 1 feature in an and-group
			Set<String> keep = new HashSet<String>();
			keep.add(v.getFeature());

			
			
			for (FeatureNode<String> child : g.vertices()) {
				if (!FeatureNodeUtils.isMandatory(g, child) // a mandatory
						// feature
						// cannot be in
						// an Or-group
						&& v != child 
						&& !child.isTop()
						&& !child.isBottom()) 
					keep.add(child.getFeature());
			}

			Collection<String> remove = CollectionUtils.subtract(fla.getDomain(), keep);
			BDD exist = _builder.mkSet(remove);
			BDD frag = fla.getBDD().id().exist(exist);

			// Debug code
			/*
			 * BDD supp = frag.support(); int[] var = supp.scanSet(); int sibs =
			 * var == null ? 0 : var.length; maxsib = Math.max(maxsib, sibs);
			 * maxnodesize = Math.max(maxnodesize, frag.nodeCount());
			 * supp.free();
			 */

			PrimeImplicants pi = new PrimeImplicants(b, frag);
			frag.free();
			exist.free();
			b.free();

			for (Implicant imp : pi) {
				Implicant imp1 = imp.removeNegations();

				Set<FeatureNode<String>> grouped = new HashSet<FeatureNode<String>>();
				for (int i : imp1) {
					// 24-Apr added to handle new domains passed with Formula

					if (fla.getDomain().contains(_builder.feature(i)))
						grouped.add(g.findVertex(_builder.feature(i)));
				}

				if (grouped.size() < 2 || grouped.contains(v))
					continue;
				assert g.children(v).containsAll(grouped);

				// if (g.findEdge(grouped, v, FeatureEdge.XOR) != null)
				// continue;
				if (xOrOrs.contains(new XorGroup(grouped, v)))
					continue;
				// TODO maybe better to do a restrict on frag and calculate
				// ValidDomains

				if (_mutexCliques.contains(grouped)) {
					xOrOrs.add(new XorGroup(grouped, v));
				} else
					xOrOrs.add(new OrGroup(grouped, v));
			}

			pi.freeCache();
		}

		return xOrOrs;
	}
	
	private List<Integer> support(Formula<String> fla) {
		List<Integer> result = new ArrayList<Integer>();
		for (String f : fla.getDomain())
			result.add(_builder.variable(f));
		return result;
	}

}
