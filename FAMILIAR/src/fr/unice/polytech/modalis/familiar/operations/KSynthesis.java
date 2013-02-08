/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;

import fr.unice.polytech.modalis.familiar.experimental.AmbigousGroup;
import fr.unice.polytech.modalis.familiar.experimental.ConstraintAdder;
import fr.unice.polytech.modalis.familiar.experimental.ConstraintAdderSyntactic;
import fr.unice.polytech.modalis.familiar.experimental.FDAddingConstraintStrategy;
import fr.unice.polytech.modalis.familiar.experimental.FGroup;
import fr.unice.polytech.modalis.familiar.experimental.MutexGroup;
import fr.unice.polytech.modalis.familiar.experimental.OrGroup;
import fr.unice.polytech.modalis.familiar.experimental.XorGroup;
import fr.unice.polytech.modalis.familiar.experimental.featureide.ConstraintAdderFeatureIDE;
import fr.unice.polytech.modalis.familiar.experimental.featureide.ConstraintAdderSafeSAT;
import fr.unice.polytech.modalis.familiar.fm.FMLFormula;
import fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.CliquesOperationFactory;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public abstract class KSynthesis {
	
	private Logger _LOGGER = Logger.getLogger(KSynthesis.class) ; 
	
	protected FMLFormula _fla ; 
	
	
	protected KnowledgeSynthesis _kn;
	
	/**
	 * TODO: refactor as "preferences" and customizable preference
	 */
	private boolean _orGroupSupport = true ;

	protected Set<FGroup> _conflictingGroups = new HashSet<FGroup>();
	
	public abstract FeatureModelVariable build() ;
	
	protected static final FDAddingConstraintStrategy DEFAUL_ADDING_STRATEGY =  	
			FDAddingConstraintStrategy.ADD_WITH_SAFE_ENTAILMENT_FEATURE_IDE ; // FIXME @FeatureIDE highly preferable
	protected FDAddingConstraintStrategy _fdAddingCstStrategy =  DEFAUL_ADDING_STRATEGY ;

	protected static final CliquesComputation DEFAULT_CLIQUES_COMPUTATION = CliquesComputation.BDD ;
	protected CliquesComputation _cliquesHierarchyComputation = DEFAULT_CLIQUES_COMPUTATION  ;

	
	
	/**
	 * FIXME
	 * fmv may contain information about hierarchy, feature groups, etc. 
	 * we exploit this information to reuse it and confront it to a knowledge specified
	 * kn > fmv (basically it is an opportunity for a use to override "fmv")
	 * @param fmv
	 * @param kn
	 * @return
	 */
	protected static KnowledgeSynthesis mkKnowledgeSynthesis(FeatureModelVariable fmv, KnowledgeSynthesis kn) {
		// TODO Auto-generated method stub
		return kn ;
	}
	
	/**
	 * @return the _kn
	 */
	public KnowledgeSynthesis getKST() {
		return _kn;
	}

	/**
	 * @param _kn the _kn to set
	 */
	public void setKST(KnowledgeSynthesis kn) {
		this._kn = kn;
	}

	/**
	 * KST is sometimes not sufficient to produce an unique FM and some arbitrary choices have to be made
	 * @return
	 */
	public boolean hasConflictingChoices() {
		return _conflictingGroups.size() >= 1 ; // TODO: other cases
	}

	/**
	 * 
	 * @return
	 */
	public String getConflictReport() {
		 // TODO: other cases
		return "Conflicting Groups: " + _conflictingGroups ;
	}

	public Set<FGroup> getConflictingGroups() {
		return _conflictingGroups ; 
	}
	
	protected void setConflictingGroups(Set<AmbigousGroup> ambigousGroups) {
		for (AmbigousGroup ambigousGroup : ambigousGroups) {
			
			Set<FGroup> fGroups = ambigousGroup.getAmbigousFGroups() ;
			for (FGroup fGroup : fGroups) {
				_conflictingGroups.add(fGroup);
				
			}
		}		
	}
	
	
	public boolean hasOrGroupSupport() {
		return _orGroupSupport;
	}

	public void setOrGroupSupport(boolean _withoutOrGroup) {
		this._orGroupSupport = _withoutOrGroup;
	}
	
	
	/**
	 * ROOT fixing
	 */



	/**
	 * Finds roots excluding the top and bottom nodes
	 * 
	 * @param g
	 * @return
	 */
	public static Set<FeatureNode<String>> findMyRoots(FeatureGraph<String> g) {
	
		Set<FeatureNode<String>> roots = new HashSet<FeatureNode<String>>();
		Set<FeatureNode<String>> cand = new HashSet<FeatureNode<String>>(g.vertices());
		for (FeatureNode<String> featureNode : cand) {
			if (FeatureNodeUtils.isRoot(featureNode, g)) {
				roots.add(featureNode);
			}
		}
	
		return roots;
	}

	
	/**
	 * Finds roots excluding the top and bottom nodes
	 * 
	 * @param g
	 * @return
	 */
	public static <T> Set<FeatureNode<T>> findRoots(FeatureGraph<T> g) {
		Set<FeatureNode<T>> cand = new HashSet<FeatureNode<T>>(g.vertices());
		for (FeatureEdge e : g.edges()) {
			cand.removeAll(g.getSources(e));
		}
		cand.remove(g.getTopVertex());
		cand.remove(g.getBottomVertex());
		return cand;
	}
	

	
	
	/**
	 * Adds all trees to the synthetic root (ie. TOP). This has the effect of
	 * moving free variables to be children of TOP.
	 */
	public void mkSyntheticRoot(FeatureGraph<String> g) {
		Set<FeatureNode<String>> roots = KSynthesis.findMyRoots(g);
		_LOGGER.debug("roots (to synthetic)=" + roots);
		FeatureNode<String> synth = g.getTopVertex();

		for (FeatureNode<String> v : roots) {
			g.addEdge(v, synth, FeatureEdge.HIERARCHY);
			g.addEdge(v, synth, FeatureEdge.MANDATORY);
		}
	}

	
	/**
	 * 
	 *  MANDATORY fix
	 * 
	 */
	
	public void mkHierarchyAndGroups(FeatureGraph<String> g, List<Set<String>> cliques) {

		Set<FeatureEdge> hierarchies = g.selectEdges(FeatureEdge.HIERARCHY);
		for (FeatureEdge fe : hierarchies) {
			FeatureNode<String> source = g.getSource(fe);
			FeatureNode<String> target = g.getTarget(fe);
			if (KSynthesis.biImplied(source, target, cliques)) {
				_LOGGER.debug(
						"Setting Mandatory:" + source + " : " + target);
				g.addEdge(source, target, FeatureEdge.MANDATORY);
			}
		}

	}


	public static <T> boolean biImplied(FeatureNode<T> source, FeatureNode<T> target,
			Collection<Set<T>> cliques) {
		T sourceName = source.getFeature();
		T targetName = target.getFeature();
		Set<T> sourceTarget = new HashSet<T>();
		sourceTarget.add(sourceName);
		sourceTarget.add(targetName);
		for (Set<T> clique : cliques) {
			if (clique.containsAll(sourceTarget))
				return true;
		}
	
		return false;
	}
	
	
	/**
	 * 
	 * 
	 * GROUPS
	 * 
	 */
	
	public Set<FGroup> simplifyMutexXor(Set<FGroup> allGroups) {
		Set<FGroup> groups = new HashSet<FGroup>(allGroups);

		Set<XorGroup> xors = selectXor(groups);
		Set<MutexGroup> mutexes = selectMutex(groups);
		for (MutexGroup mutexGroup : mutexes) {
			for (FGroup xorGroup : xors) {

				if (mutexGroup.sameSources(xorGroup)
						&& mutexGroup.sameTarget(xorGroup)) {
					_LOGGER.debug(
							"Or/Xor / Mutex subsumes: " + mutexGroup);
					groups.remove(mutexGroup);
				}

			}
		}
		return groups;
	}

	private Set<XorGroup> selectXor(Set<FGroup> groups) {
		Set<XorGroup> xors = new HashSet<XorGroup>();
		for (FGroup group : groups) {
			if (group.isXor())
				xors.add((XorGroup) group);
		}

		return xors;

	}

	private Set<OrGroup> selectOr(Set<FGroup> groups) {
		Set<OrGroup> ors = new HashSet<OrGroup>();
		for (FGroup group : groups) {
			if (group.isOr())
				ors.add((OrGroup) group);
		}

		return ors;

	}

	private Set<MutexGroup> selectMutex(Set<FGroup> groups) {
		Set<MutexGroup> mutexes = new HashSet<MutexGroup>();
		for (FGroup group : groups) {
			if (group.isMutex())
				mutexes.add((MutexGroup) group);
		}

		return mutexes;

	}
	
	
	public Set<AmbigousGroup> ambigousGroups(Set<FGroup> groups) {
		Set<AmbigousGroup> ambigs = new HashSet<AmbigousGroup>();
		Set<FeatureNode<String>> fts = collectFeatureNodes(groups);
		for (FeatureNode<String> ft : fts) {
			AmbigousGroup ambig = new AmbigousGroup(ft);
			for (FGroup group : groups) {
				Set<FeatureNode<String>> sources = group.getSources();
				if (sources.contains(ft)) {
					ambig.add(group);
				}
			}
			if (ambig.size() > 1)
				ambigs.add(ambig);
		}

		return ambigs;
	}
	
	private Set<FeatureNode<String>> collectFeatureNodes(Set<FGroup> groups) {
		Set<FeatureNode<String>> nodes = new HashSet<FeatureNode<String>>();
		for (FGroup fGroup : groups) {
			nodes.add(fGroup.getTarget());
			nodes.addAll(fGroup.getSources());
		}
		return nodes;
	}
	
	protected Set<FGroup> performBasicResolvingGroupStrategy(Set<FGroup> allGroups, Set<AmbigousGroup> ambigousGroups) {
		
		
		Set<FGroup> groups = new HashSet<FGroup>(allGroups);
		if (allGroups.size() == 0) {
			return groups ; // perfect, no ambiguity
		}
		
		Set<FGroup> r = new HashSet<FGroup>();
		Set<FeatureNode<String>> fnodes = new HashSet<FeatureNode<String>>();
		for (AmbigousGroup ambigousGroup : ambigousGroups) {

			AmbigousGroup ambigousGroupCleaned = ambigousGroup
					.removeGroups(fnodes);
			if (ambigousGroupCleaned.size() == 0)
				continue;
			FeatureNode<String> feature = ambigousGroupCleaned.getFeatureNode();
			FMLShell.getInstance()
					.printDebugMessage("ambigous for: " + feature);
			FGroup fGroup = selectAmbigousGroup(ambigousGroupCleaned);
			_LOGGER.debug(
					"group selected (winner): " + fGroup);
			r.add(fGroup);
			fnodes.addAll(fGroup.nodes());
		}
		Set<FGroup> nonAmbigousGroups = nonAmbigousGroups(ambigousGroups, groups);
		r.addAll(nonAmbigousGroups);
		
		return r;
		
	}
	
	private FGroup selectAmbigousGroup(AmbigousGroup ambigousGroup) {
		for (FGroup fGroup : ambigousGroup) {
			_LOGGER.debug("\t\t" + fGroup);
		}
		if (ambigousGroup.size() == 1)
			return ambigousGroup.iterator().next();

		// select the "maximum" group
		return ambigousGroup.getMaxGroup();
	}
	
	protected Set<FGroup> nonAmbigousGroups(
			Set<AmbigousGroup> ambigousGroups, Set<FGroup> groups) {

		Set<FGroup> r = new HashSet<FGroup>();
		Set<FGroup> collectAmbigousGroups = new HashSet<FGroup>();
		for (AmbigousGroup ambigousGroup : ambigousGroups) {
			collectAmbigousGroups.addAll(ambigousGroup.getAmbigousFGroups());
		}

		// diff between collectAmbigousGroups and groups
		for (FGroup fGroup : groups) {
			if (!collectAmbigousGroups.contains(fGroup)) {
				r.add(fGroup);
			}
		}

		return r;
	}
	
	protected void setFGroupsInformation(FeatureGraph<String> g,
			Set<FGroup> allGroupsNormalized) {

		for (FGroup fGroup : allGroupsNormalized) {
			Set<FeatureNode<String>> sources = fGroup.getSources();
			FeatureNode<String> target = fGroup.getTarget();
			int edgeType = FeatureEdge.HIERARCHY;
			if (fGroup.isMutex())
				edgeType = FeatureEdge.MUTEX;
			else if (fGroup.isXor())
				edgeType = FeatureEdge.XOR;
			else if (fGroup.isOr())
				edgeType = FeatureEdge.OR;
			else {
				_LOGGER.debug(
						"Unknown edge type=" + edgeType);
			}
			g.addEdge(sources, target, edgeType); // TODO: control
		}

	}
	
	
	protected Collection<Set<FeatureNode<String>>> findMutexCliques(UndirectedGraph<FeatureNode<String>, DefaultEdge> g) {

		BronKerboschCliqueFinder<FeatureNode<String>, DefaultEdge> finder = new BronKerboschCliqueFinder<FeatureNode<String>, DefaultEdge>(
				g);

		Collection<Set<FeatureNode<String>>> cliques = finder.getAllMaximalCliques();
		Iterator<Set<FeatureNode<String>>> iter = cliques.iterator();
		while (iter.hasNext()) {
			if (iter.next().size() < 2)
				iter.remove();
		}
		return cliques;
	}
	
	/**
	 * CONSTRAINTS 
	 * 
	 */
	/**
	 * the biimplies edges not expressed by g but by impl
	 * 
	 * @param g
	 * @param impl
	 * @return
	 */
	protected Set<Expression<String>> computeBiImpliesEdge(FeatureGraph<String> g,
			ImplicationGraph<String> impl) {
		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		List<Set<String>> cliquesHierarchy = computeCliquesHierarchy(g);

		Set<Expression<String>> cliquesE = cliquesToExpressions(cliques);
		_LOGGER.debug("cliquesE" + cliquesE);
		Set<Expression<String>> cliquesEH = cliquesToExpressions(cliquesHierarchy);
		_LOGGER.debug("cliquesEH" + cliquesEH);

		Set<Expression<String>> r = new HashSet<Expression<String>>();
		// diffing
		for (Expression<String> e1 : cliquesE) {
			boolean alreadyExpressed = false;
			for (Expression<String> e2 : cliquesEH) {
				if (checkBiExpressionEquality(e1, e2)) {
					alreadyExpressed = true;
					continue;
				}
			}
			if (!alreadyExpressed)
				r.add(e1);
		}

		return r;
	}
	

	private boolean checkBiExpressionEquality(Expression<String> e1, Expression<String> e2) {
		if (e1.getType() != e2.getType() && e1.getType() != ExpressionType.IFF)
			return false;
		if (e1.getType() == ExpressionType.FEATURE)
			return e1.getFeature().equals(e2.getFeature());
		return ((checkBiExpressionEquality(e1.getLeft(), e2.getLeft()) && checkBiExpressionEquality(
				e1.getRight(), e2.getRight())) || (checkBiExpressionEquality(
				e1.getLeft(), e2.getRight()) && checkBiExpressionEquality(
				e1.getRight(), e2.getLeft())));
	}
	
	

	private Set<Expression<String>> cliquesToExpressions(List<Set<String>> cliques) {
		Set<Expression<String>> r = new HashSet<Expression<String>>();
		for (Set<String> clique : cliques) {
			Set<Expression<String>> e = mkBiImplies(clique);
			r.addAll(e);
		}
		return r;
	}

	private Set<Expression<String>> mkBiImplies(Set<String> clique) {
		Set<Expression<String>> exprs = new HashSet<Expression<String>>();
		String previous = null;
		for (String ft : clique) {
			if (previous != null) {
				Expression<String> e = new Expression<String>(ExpressionType.IFF,
						previous, ft);
				exprs.add(e);
			}
			previous = ft;
		}

		return exprs;
	}
	
	
	private List<Set<String>> computeCliquesHierarchy(FeatureGraph<String> g) {
		

		
		List<Set<String>> r = new ArrayList<Set<String>>();
		Collection<Set<String>> rS = CliquesOperationFactory.mkCliques(
				new FeatureModelVariable("", new FeatureModel<String>(g)), 
						_cliquesHierarchyComputation);
		for (Set<String> cl : rS) {
			r.add((Set<String>) cl);
			
		}
		// FIXME: weird and wrong solution, not the clique
		/*
		Set<FeatureEdge> eMan = g.selectEdges(FeatureEdge.MANDATORY);
		for (FeatureEdge e : eMan) {
			FeatureNode<String> s = g.getSource(e);
			FeatureNode<String> t = g.getTarget(e);
			Set<String> nodes = new HashSet<String>();
			nodes.add(s.getFeature());
			nodes.add(t.getFeature());
			r.add(nodes);
		}
		*/
		/*
		 * FIXME: should work List<Set<FeatureNode<String>>> cliquesHierarchy =
		 * DirectedCliqueFinder.INSTANCE.findAll(g);
		 * _LOGGER.debug("cliquesHierarchy" +
		 * cliquesHierarchy);
		 * 
		 * for (Set<FeatureNode<String>> fns : cliquesHierarchy) { Set<String> s = new
		 * HashSet<String>(); for (FeatureNode<String> fn : fns) { s.add(fn.getFeature());
		 * } r.add(s); }
		 */
		return r;
		
	}
	
	/** 
	 * @param g
	 * @param f
	 * @param impl
	 * @return
	 */
	public FeatureModel<String> complementWithImpliesAndExcludes(FeatureModel<String> iFM, 
			ImplicationGraph<String> impl, ExclusionGraph<String> excl1) {
			
		FeatureModel<String> fm = iFM.clone();
		_LOGGER.debug("Computing BIE");
		Set<Expression<String>> biimplies = computeBiImpliesEdge(fm.getDiagram(), impl);
		_LOGGER.debug("biimplies (2ADD)=" + biimplies);
		
		ConstraintAdder cAdder = getAdderConstraint(_fdAddingCstStrategy,
				fm,
				biimplies, 
				impl, 
				excl1
				); 
		
		if (cAdder == null) {
			FMLShell.getInstance().printError("Unknown constraint adder strategy: " + _fdAddingCstStrategy);
			return null ; 
		}
		return cAdder.apply() ;
			
		
	}
	
	protected ConstraintAdder getAdderConstraint(FDAddingConstraintStrategy fdAddingCstStrategy,
			FeatureModel<String> fm) {
		
			
		if (fdAddingCstStrategy == FDAddingConstraintStrategy.ADD_WITH_SAFE_ENTAILMENT_FEATURE_IDE) {
			// FIXME @FeatureIDE 
			return new ConstraintAdderFeatureIDE(fm) ;
		}
		else if (fdAddingCstStrategy == FDAddingConstraintStrategy.ADD_WITH_SAFE_ENTAILMENT) {
			// FIXME @FeatureIDE 
			return new ConstraintAdderSafeSAT(fm) ;
		}
		
		else if (fdAddingCstStrategy == FDAddingConstraintStrategy.BASIC_ADD) {
			return new ConstraintAdderSyntactic(fm) ;
		}
		
		else {
		}
		return null ; 
	}
	
	private ConstraintAdder getAdderConstraint(FDAddingConstraintStrategy fdAddingCstStrategy,
			FeatureModel<String> fm, 
			Set<Expression<String>> biimplies, 
			ImplicationGraph<String> impl, 
			ExclusionGraph<String> excl1
			) {
		
			
		if (fdAddingCstStrategy == FDAddingConstraintStrategy.ADD_WITH_SAFE_ENTAILMENT_FEATURE_IDE) {
			// FIXME @FeatureIDE 
			return new ConstraintAdderFeatureIDE(fm, biimplies, impl, excl1) ;

			
		}
		else if (fdAddingCstStrategy == FDAddingConstraintStrategy.ADD_WITH_SAFE_ENTAILMENT) {
			// FIXME @FeatureIDE 
			return new ConstraintAdderSafeSAT(fm, biimplies, impl, excl1) ;
		}
		
		else if (fdAddingCstStrategy == FDAddingConstraintStrategy.BASIC_ADD) {
			return new ConstraintAdderSyntactic(fm, biimplies, impl, excl1) ;
		}
		
		else {
				
		}
		return null ; 
	}
	
	
	
	
	
	
	/*
	 * UTILITY
	 */
	
	protected void cleanHierarchy(FeatureModel<String> fmHierarchy, Set<String> ftsToRemove) {
		// TODO assume this is a subset
		FeatureModelVariable fmvH = new FeatureModelVariable("", fmHierarchy) ; 
		for (String ftToRemove : ftsToRemove) {
			fmvH.removeFeature((String) ftToRemove);
		}		
	}
	
	/**
	 * @return null if the group doesn't have a common parent
	 */
	protected FeatureNode<String> commonParent(FeatureGraph<String> g,
			Set<FeatureNode<String>> group) {
		Set<FeatureNode<String>> parents = new HashSet<FeatureNode<String>>(g.vertices());
		for (FeatureNode<String> v : group) {
			parents.retainAll(g.parents(v));
			if (parents.size() == 0)
				return null;
		}

		return parents.iterator().next();
	}

	
	
	
	
	
	
	

}
