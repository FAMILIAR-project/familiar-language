/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.operations.featureide;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;

import com.google.common.collect.Sets;

import fr.familiar.experimental.AmbigousGroup;
import fr.familiar.experimental.FGroup;
import fr.familiar.experimental.MutexGroup;
import fr.familiar.experimental.XorGroup;
import fr.familiar.experimental.featureide.FGroupUtil;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.KSynthesis;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.FeatureModelVariableSATFormula;
import gsd.graph.DirectedCliqueFinder;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class KSynthesisSAT extends KSynthesis {
	
	private Logger _LOGGER = Logger.getLogger(KSynthesisSAT.class) ; 

	/**
	 * 
	 */
	public KSynthesisSAT(SATFormula fla, KnowledgeSynthesis knowledgeSynthesis) {
		_fla = fla ; 
		_kn = knowledgeSynthesis ; 
	}


	public KSynthesisSAT(FeatureModelVariable fmv1,
			KnowledgeSynthesis knowledgeSynthesis) {
		this (new SATFMLFormula(fmv1), knowledgeSynthesis);
	}


	@Override
	public FeatureModelVariable build() {
		// 1. PREPROCESSING STEP (of the formula)
		// Note that we actually assume that "fla" has no dead features
		SATFormula lFla = (SATFormula) _fla ; 
		Set<String> support = _fla.getDomain() ;
		// if any
		support.remove("False");
		support.remove("True");
		
		support.removeAll(lFla.deads(support));
		
		if (!_kn.isHierarchySpecified()) {
			FMLShell.getInstance().printTODO("Currently, we expect that at least the hierarchy is specified... (will be fixed soon)") ;
			return null ; 
		}
		
		
		FeatureGraph<String> fmHierarchy = _kn.getHierarchy() ; 
		// in some situations (merge intersection, slice with "anomalies", cleanup, etc.) 
		// we should remove the features included in the intended hierarchy but actually dead features 
		Set<String> deadFtsHiearchy = Sets.difference(fmHierarchy.features(), support);
		_LOGGER.debug("deadFtsHiearchy: " + deadFtsHiearchy);
		cleanHierarchy(new FeatureModel<String>(fmHierarchy), deadFtsHiearchy);
		
		assert (support.equals(fmHierarchy.features()));
		
		/* 
		 * The idea is to populate the intended hierarchy with the variability information we can synthesise
		 */
		
		FeatureModel<String> synthesisedFM = new FeatureModel<String>(fmHierarchy.clone()); // it plays this role (FD + imp/excl)
		FeatureGraph<String> synthesisedFD = synthesisedFM.getDiagram(); // it plays this role (pure FD part)

		mkSyntheticRoot(synthesisedFD);
		
		// 2. IMPLICATION/EXCLUSION (aka MUTEX) GRAPH

		_LOGGER.debug("Implication graph");
		ImplicationGraph<String> impl = lFla.computeImplicationGraph(support);
		_LOGGER.debug("Exclusion graph");
		ExclusionGraph<String> excl = lFla.computeExclusionGraph(support);

		List<Set<String>> cliques = DirectedCliqueFinder.INSTANCE.findAll(impl);
		_LOGGER.debug("Cliques: " + cliques);

		
		// 3. FIXING mandatory features
		_LOGGER.debug("And-groups");
		mkHierarchyAndGroups(synthesisedFD, cliques); 


		// 4. FIXING groups
		_LOGGER.debug("Mutex graph / groups");
		Set<MutexGroup> mutexes = computeMutexGroups(synthesisedFD, lFla);
		_LOGGER.debug("Mutex computation (based on hierarchy)=" + mutexes);
		
		Set<FGroup> allGroups = new HashSet<FGroup>();
		// (!hasOrGroupSupport()) 
		Set<XorGroup> xors = computeXorBasedOnMutexGroups(lFla, mutexes);
		_LOGGER.debug("xors=" + xors);
		allGroups = Sets.union(xors, mutexes);
		 

		// normalization
		_LOGGER.debug("All groups computation (based on hierarchy)=" + allGroups);
				
		Set<FGroup> allGroupsNormalized = simplifyMutexXor(allGroups);
		_LOGGER.debug("After Xor/Mtx subsume: " + allGroupsNormalized);
		
		// sort ambigous groups by "feature"
		Set<AmbigousGroup> ambigousGroups = ambigousGroups(allGroupsNormalized);
		if (ambigousGroups.size() > 0) {
			// TODO: knowledge needed!
			Set<FGroup> knGroups = _kn.getGroups() ;
			// TODO implement the resolving strategy
			if (knGroups.size() > 0) {
					// TODO: it can be not sufficient!
			}
			else { // no knowledge related to groups!
				setConflictingGroups(new HashSet<AmbigousGroup>(ambigousGroups)); 				
				allGroupsNormalized = performBasicResolvingGroupStrategy(allGroupsNormalized, ambigousGroups) ;
			}
			// if knowledge is not sufficient
		}
		else {
			_LOGGER.debug("No ambiguity!");
		}
				
		
		_LOGGER.debug("All groups (normalization)=" + allGroupsNormalized);
		
		_LOGGER.debug("Setting variability information");
		setFGroupsInformation(synthesisedFD, allGroupsNormalized);

		_LOGGER.debug("-- end of feature DIAGRAM synthesis -- ");
		_LOGGER.debug("resulting FD without implies/excludes constraints: " + synthesisedFD);

		
		// 5. SETTING CONSTRAINTS 
		_LOGGER.debug("Implies / Excludes");
	
		
		synthesisedFM = complementWithImpliesAndExcludes(synthesisedFM , impl, excl); 
		
		/*
		complementWithImpliesAndExcludes(synthesisedFD, 
				lFla,				
				impl, excl); 
		
		_LOGGER.debug("...Eliminate redundant if any (FIXME since opt)...");
		// simplify (not necessary normally since we add incrementally the edges)
		if (_simplificatorStrategy != ConstraintSimplifierStrategy.NONE)
			synthesisedFM = new FMLConstraintReasoner(synthesisedFM, _builder).eliminateRedundantConstraints();
		_LOGGER.debug("End synthesis");*/
		
		
		
		return new FeatureModelVariableSATFormula ("", synthesisedFM, lFla);
	}
	
	
	
	public Set<MutexGroup> computeMutexGroups(FeatureGraph<String> g,
			SATFormula lFla) {

		// TODO: replace by

		Set<MutexGroup> mutexed = new HashSet<MutexGroup>();

		// Make Mutex Graph
		UndirectedGraph<FeatureNode<String>, DefaultEdge> mutex = new SimpleGraph<FeatureNode<String>, DefaultEdge>(
				DefaultEdge.class);

		for (FeatureNode<String> v : g.vertices())
			mutex.addVertex(v);

		for (FeatureNode<String> v : g.vertices()) {

			FeatureNode<String>[] siblings = g.children(v).toArray(new FeatureNode[0]);

			for (int i = 0; i < siblings.length; i++) {

				FeatureNode<String> s1 = siblings[i];
				Node v1 = new And (lFla.getNode().clone(), new And (s1.getFeature()));

				if (!new SATFMLFormula(v1).isValid())
					throw new UnsupportedOperationException(
							"Dead features should have been removed!");

				for (int j = i + 1; j < siblings.length; j++) {
					FeatureNode<String> s2 = siblings[j];
					// Only need to check one of the features (if it's an // and-group)
					Node v2 = new Literal(s2.getFeature());
					if (exclusion (v1, v2))
						mutex.addEdge(s1, s2);
				}
				
			}
		}

		// Create Mutex Groups
		_LOGGER.debug("mutex:" + mutex);
		Collection<Set<FeatureNode<String>>> mutexCliques = findMutexCliques(mutex);
		_LOGGER.debug("cliques in the mutex graph: " + mutexCliques);
		for (Set<FeatureNode<String>> clique : mutexCliques) {

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
	
	private boolean exclusion(Node mx, Node v2) {
		And ch = new And(mx, v2); // ! (u -> !v) = u & v
		return !new SATFMLFormula(ch).isValid() ;			
	}
	
	/**
	 * an Xor-group is a special kind of Mutex-group (avoid the Czarnecki et al.
	 * 2007 prime implicants methods)
	 * 
	 * @param f
	 * @param mutexes
	 * @return
	 */
	public Set<XorGroup> computeXorBasedOnMutexGroups(SATFormula fla,
			Set<MutexGroup> mutexes) {

		Set<XorGroup> xors = new HashSet<XorGroup>();
		for (MutexGroup mutexGroup : mutexes) {
			if (FGroupUtil.isXorGroup(mutexGroup, fla)) {
				XorGroup x = new XorGroup(mutexGroup.getSources(),
						mutexGroup.getTarget());
				xors.add(x);

			}

		}

		return xors;
	}
	


	@Override
	public boolean hasOrGroupSupport() {
		return false ; // yep, at this step, we have not integrated support for OR-groups with SAT (SPLC'12 of Andersen et al. is an efficient alternative)
	}


	@Override
	public FeatureModelVariable buildOver(Set<String> fts) {
		FMLShell.getInstance().printError("Synthesis 'over' is not currently supported in SAT");
		return null ; 
	}

}
