/**
 * 
 */
package fr.familiar.operations;

import fr.familiar.fm.FMLFormula;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

/**
 * @author mathieuacher Experimental branch
 * 
 */
public class SlicerBDDFormula extends SlicerFormula {
	
	private static Logger _LOGGER = Logger.getLogger(SlicerBDDFormula.class);

	protected BDDBuilder<String> _builder; // we just need that!
	

	public SlicerBDDFormula(BDDBuilder<String> builder) {
		_builder = builder;
	}
	
	/**
	 * @param oformula
	 *            original FM to be filtered
	 * @param features
	 *            set of features to be quantified away
	 * @param sliceMode
	 *            including or exclusive slicing criterion strategy
	 * @return a "filtered" formula (see TR for formal description)
	 */
	@Override
	public FMLFormula sliceFormula(FeatureModelVariable fmv,
			Collection<String> features, SliceMode sliceMode) {
		return sliceFormula (fmv.getFormula(), features, sliceMode) ; 
	}
	
	/**
	 * @param oformula
	 *            original formula to be filtered
	 * @param features
	 *            set of features to be quantified away
	 * @param sliceMode
	 *            including or exclusive slicing criterion strategy
	 * @return a "filtered" formula (see TR for formal description)
	 */
	public BDDFormula sliceFormula(Formula<String> oformula,
			Collection<String> features, SliceMode sliceMode) {
		

		if (sliceMode == SliceMode.EXCLUDING)
			return sliceExclusiveFormula(oformula, features);
		else if (sliceMode == SliceMode.INCLUDING) {

			Set<String> ftsToExclude = Sets.difference(oformula.getDomain(),
					new HashSet<String>(features));
			return sliceExclusiveFormula(oformula, ftsToExclude);
		} else {
			FMLShell.getInstance().printError(
					"Unexepected slice mode=" + sliceMode);
			return null;
		}

	}

	/**
	 * @param oformula
	 *            original formula to be filtered
	 * @param features
	 *            set of features to be quantified away
	 * @return a "filtered" formula (see TR for formal description) using
	 *         exclusive strategy
	 */
	private BDDFormula sliceExclusiveFormula(Formula<String> oformula,
			Collection<String> features) {

		Set<String> odomain = oformula.getDomain();
		Set<String> realDomain = Sets.difference(odomain, new HashSet<String>(
				features));
		
		
		if (!features.isEmpty() && realDomain.isEmpty()) { // FIXME
			FMLShell.getInstance().printWarning(
					"The original domain: " + odomain
							+ " is not modified (features=" + features
							+ ") and not equals to the restricted domain");
			return null;
		}


		BDD rbdd = oformula.getBDD().id();
		BDD rbddDomain = _builder.mkSet(features);
		BDD srbdd = rbdd.exist(rbddDomain); 
		Formula<String> rformula = new Formula<String>(srbdd, realDomain,
				_builder);

		FormulaAnalyzer<String> flaSlicedAnalyzer = new FormulaAnalyzer<String>(rformula, _builder);
		
		Formula<String> properSlicedFla = flaSlicedAnalyzer.removeDeadFeatures();
		return new BDDFormula (properSlicedFla);
		
	}
	
	
	// DEPRECRATED (old projection and hierarchy computation)
	
	//private DOTExporter<FeatureNode<String>, DefaultEdge> _dotifier = new DOTExporter<FeatureNode<String>, DefaultEdge>(
		//	new StringNameProvider<FeatureNode<String>>(), null, null);
//	/**
//	 * project is an interesting term (database inspired)
//	 * @param fm
//	 *            the feature model to consider
//	 * @param abstractFTs
//	 *            abstractFTs a list of features that are "abstract"
//	 * @return the Boolean formula corresponding to set of configurations of fm
//	 *         where each configuration does not contain any feature of
//	 *         abstractFTs [Note: a very naive solution would be to evaluate all
//	 *         combinations of truth/false value assignment to abstract features
//	 *         and make the union of all valid domains. Problem: for 32 abstract
//	 *         features, 2^32 assignments to consider!]
//	 * 
//	 */
//	@Deprecated
//	public Formula<String> project(FeatureModel<String> fm,
//			Set<String> abstractFTs) {
//
//		Formula<String> fla = _builder.mkFeatureModel(fm);
//		BDD bdd = fla.getBDD();
//
//		// replace each occurence of "abstract" features by their *definition*
//		for (String ft : abstractFTs) {
//			if (!fm.features().contains(ft)) {
//
//				_LOGGER.debug("Error with ft=" + ft);
//				return null;
//			}
//			FeatureNode<String> fnode = fm.getDiagram().findVertex(ft);
//			BDD ftDefinition = definition(fnode, fm); // basically, the solution
//														// proposed by Thum et
//														// al.
//			int v = _builder.variable(fnode.getFeature());
//
//			_LOGGER.debug("ftDefinition=" + ftDefinition + " v=" + v);
//			// bdd.compose(ftDefinition, v);
//
//			BDDPairing repl = _builder.getFactory().makePair(v, ftDefinition);
//			// bdd = bdd.veccompose(repl);
//			// bdd = bdd.compose(ftDefinition, v);
//			// bdd = bdd.forAll(_builder.nget(fnode.getFeature()));
//			bdd = bdd.exist(_builder.nget(fnode.getFeature()));
//			// bdd = bdd.unique(_builder.nget(fnode.getFeature()));
//
//		}
//
//		Set<String> concreteFTs = Sets.difference(fm.features(), abstractFTs);
//		_LOGGER.debug("concreteFTs=" + concreteFTs);
//		return new Formula<String>(bdd, concreteFTs, _builder);
//
//	}
//
//	/**
//	 * @param ft
//	 *            feature node of the feature model
//	 * @param fm
//	 *            feature model to consider
//	 * @return the definition of a feature, intuitively, all child-features
//	 *         dependent from ft represent ft depends on the type of operator
//	 *         associated to ft see Thum et al. 2009 (ICSE)
//	 */
//	@Deprecated
//	private BDD definition(FeatureNode<String> ft, FeatureModel<String> fm) {
//
//		FeatureGraph<String> fg = fm.getDiagram();
//
//		// AND-groups
//		/*
//		 * See Table 3 of batory2009 very strange: (and to be related with bugs
//		 * found in FeatureIDE) Readers may note that the definition we use for
//		 * Alternative-groups lacks the atmost1() predicate. As this predicate
//		 * is already part of the feature model, there is no need to replicate
//		 * it unnecessarily. Hence, the definition we use for Alternative-groups
//		 * is the same as that for Or-groups.
//		 */
//		if (FeatureNodeUtils.hasOptionalFeatures(fg, ft)
//				|| FeatureNodeUtils.hasMandatoryFeatures(fg, ft)) {
//
//			// conjunction of children
//			BDD result = _builder.one();
//			for (FeatureEdge e : fg.incomingEdges(ft)) {
//				Set<FeatureNode<String>> sources = fg.getSources(e);
//
//				for (FeatureNode<String> v : sources) {
//					result.andWith(_builder.get(v.getFeature()));
//				}
//
//			}
//			return result;
//
//		}
//
//		// Alternative-groups // Or-groups
//		else if (FeatureNodeUtils.hasXorGroup(fg, ft)
//				|| FeatureNodeUtils.hasOrGroup(fg, ft)) {
//
//			// disjunction of children
//			assert (fg.incomingEdges(ft).size() == 1);
//			BDD result = _builder.zero();
//			for (FeatureEdge e : fg.incomingEdges(ft)) {
//				Set<FeatureNode<String>> sources = fg.getSources(e);
//
//				for (FeatureNode<String> v : sources) {
//					result.orWith(_builder.get(v.getFeature()));
//				}
//
//			}
//			return result;
//
//		}
//
//		else
//			// sould never be reached
//			_LOGGER.error("Unexpected error (neither Xor/Or/Mand/Opt)?");
//		// sould never be reached
//		return null;
//	}
//
//
//
//
//
//
//
//
//
//	private FeatureGraph<String> _sliceExclusiveHierarchy(
//			FeatureModelVariable fm, Collection<String> features) {
//		FeatureGraph<String> fg = fm.getFm().getDiagram().clone();
//		/*
//		 * We convert the feature graph to a simple directed graph (to reuse
//		 * jgrapht facilities/algorithms)
//		 */
//
//		SimpleDirectedGraph<FeatureNode<String>, DefaultEdge> sg = new SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>(
//				DefaultEdge.class);
//		for (FeatureNode<String> fn : fg.vertices()) {
//			/*
//			 if (FeatureNodeUtils.isTop(fn)) continue ; if
//			  (FeatureNodeUtils.isBottom(fn)) continue ;
//			 */
//			sg.addVertex(new FeatureNode<String>(fn.getFeature()));
//		}
//
//		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
//		for (FeatureEdge fe : edges) {
//			FeatureNode<String> ftSource = fg.getSource(fe);
//			FeatureNode<String> ftTarget = fg.getTarget(fe);
//			/*
//			  if (FeatureNodeUtils.isTop(ftSource) ||
//			  FeatureNodeUtils.isBottom(ftSource)) continue ;
//			  
//			  if (FeatureNodeUtils.isTop(ftTarget) ||
//			  FeatureNodeUtils.isBottom(ftTarget)) continue ;
//			  */
//			 
//			FeatureNode<String> source = getFtNodeFromGraph(ftSource, sg);
//			FeatureNode<String> target = getFtNodeFromGraph(ftTarget, sg);
//
//			assert (source != null);
//			assert (target != null);
//
//			sg.addEdge(source, target);
//
//		}
//
//		// we filter the set of vertices and keep only vertices not in features
//		Set<FeatureNode<String>> vertices = new HashSet<FeatureNode<String>>();
//		for (FeatureNode<String> fn : fg.vertices()) {
//			if (!features.contains(fn.getFeature())) // we dont want features
//				vertices.add(fn);
//		}
//	
//		
//		Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>> subg = new Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>>(
//				sg, vertices);
//
//		_LOGGER.debug("SG= " + sg);
//		_LOGGER.debug("vertices= " + vertices);
//		FeatureGraph<String> rfg = subgraphToFeatureGraph(subg);
//		
//		/*
//		 * fix the root
//		 * 
//		 */
//		
//		Set<FeatureNode<String>> cRoots = FGBuilderWithKnownHierarchy.findRoots(rfg);
//		FeatureNode<String> root = null ;
//		_LOGGER.debug("cRoots=" + cRoots);
//		int max = Integer.MIN_VALUE ; 
//		for (FeatureNode<String> cRoot : cRoots) {
//			int cDepth = fm.depth(cRoot) ; 
//			if (cDepth > max) {
//				max = cDepth ;  
//				root = cRoot ; 
//			}
//			//_LOGGER.debug("depth (" + featureNode + ") = " + cDepth); 
//		}
//		
//		/*
//		rfg.addVertex(new FeatureNode<String>(FeatureGraphFactory.DEFAULT_TOP_STRING));
//		rfg.addVertex(root);
//		rfg.addEdge(root, rfg.getTopVertex(), FeatureEdge.HIERARCHY);
//		*/
//		
//		_LOGGER.debug("(elected) root=" + root);
//		for (FeatureNode<String> cRoot : cRoots) {
//			if (cRoot != root) {
//				rfg.addEdge(cRoot, root, FeatureEdge.HIERARCHY);
//				//rfg.removeEdge(rfg.findEdge(cRoot, rfg.getTopVertex(), FeatureEdge.HIERARCHY));				
//			}
//			
//		}
//		
//		return rfg ; 
//		
//		
//	}
//
//	/** 
//	 * implement slice hierarchy with JGraph facilities
//	 * @param fm
//	 * @param features
//	 * @return 
//	 */
//	@Deprecated
//	private FeatureGraph<String> _sliceExclusiveHierarchyJGraph(
//			FeatureModelVariable fm, Collection<String> features) {
//		FeatureGraph<String> fg = fm.getFm().getDiagram().clone();
//
//		/*
//		 * We convert the feature graph to a simple directed graph (to reuse
//		 * jgrapht facilities/algorithms)
//		 */
//
//		SimpleDirectedGraph<FeatureNode<String>, DefaultEdge> sg = new SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>(
//				DefaultEdge.class);
//		for (FeatureNode<String> fn : fg.vertices()) {
//			/*
//			 * if (FeatureNodeUtils.isTop(fn)) continue ; if
//			 * (FeatureNodeUtils.isBottom(fn)) continue ;
//			 */
//			sg.addVertex(new FeatureNode<String>(fn.getFeature()));
//		}
//
//		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
//		for (FeatureEdge fe : edges) {
//			FeatureNode<String> ftSource = fg.getSource(fe);
//			FeatureNode<String> ftTarget = fg.getTarget(fe);
//			/*
//			 * if (FeatureNodeUtils.isTop(ftSource) ||
//			 * FeatureNodeUtils.isBottom(ftSource)) continue ;
//			 * 
//			 * if (FeatureNodeUtils.isTop(ftTarget) ||
//			 * FeatureNodeUtils.isBottom(ftTarget)) continue ;
//			 */
//			FeatureNode<String> source = getFtNodeFromGraph(ftSource, sg);
//			FeatureNode<String> target = getFtNodeFromGraph(ftTarget, sg);
//
//			assert (source != null);
//			assert (target != null);
//
//			sg.addEdge(source, target);
//
//		}
//
//		// much better!
//		org.jgrapht.experimental.dag.DirectedAcyclicGraph<FeatureNode<String>, DefaultEdge> dag = DAG(fg);
//
//		/**** subgraph (subset of vertex + deletion of other edges) ****/
//
//		// we filter the set of vertices and keep only vertices not in features
//		Set<FeatureNode<String>> vertices = new HashSet<FeatureNode<String>>();
//		for (FeatureNode<String> fn : fg.vertices()) {
//			if (!features.contains(fn.getFeature())) // we dont want features
//				vertices.add(fn);
//		}
//		// assert(vertices.size() == features.size());
//
//		// much better
//		/** debug **/
//		// debugWithDOT(fm, dag, "-oDAG") ;
//		for (FeatureNode<String> v : vertices) {
//			dag.removeVertex(v);
//		}
//		// assert(dag.removeAllVertices(vertices));
//		/** debug **/
//		// debugWithDOT(fm, dag, "-DAG") ;
//
//		/** debug **/
//		// debugWithDOT(fm, sg) ;
//
//		/** end debug **/
//
//		_LOGGER.debug("sg=" + sg.toString());
//		TransitiveClosure.INSTANCE.closeSimpleDirectedGraph(sg);
//		_LOGGER.debug(
//				"(transitive closure) sg=" + sg.toString());
//
//		// debugWithDOT(fm, sg, "-tclosure") ;
//
//		Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>> subg = new Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>>(
//				sg, vertices);
//
//		_LOGGER.debug(
//				"(subgraphing) subg=" + subg.toString());
//
//		// debugWithDOT(fm, subg, "-subg") ;
//
//		// transitive reduction
//
//		/*
//		 * Then, we convert back to FeatureGraph
//		 */
//
//		FeatureGraph<String> rfg = subgraphToFeatureGraph(subg);
//
//		TransitiveReduction.INSTANCE.reduce(rfg);
//
//		_LOGGER.debug(
//				"dag.vertexSet().size(), rfg.vertices().size() "
//						+ dag.vertexSet().size() + " vs "
//						+ rfg.vertices().size());
//
//		/*
//		 * DAG assert(dag.vertexSet().size() == rfg.vertices().size());
//		 * assert(dag.vertexSet().equals(rfg.vertices()));
//		 */
//		
//		return rfg;
//	}
//
//	private DirectedAcyclicGraph<FeatureNode<String>, DefaultEdge> DAG(
//			FeatureGraph<String> fg) {
//		org.jgrapht.experimental.dag.DirectedAcyclicGraph<FeatureNode<String>, DefaultEdge> dagFG = new DirectedAcyclicGraph<FeatureNode<String>, DefaultEdge>(
//				DefaultEdge.class);
//
//		/*
//		 * We convert the feature graph to a simple directed graph (to reuse
//		 * jgrapht facilities/algorithms)
//		 */
//
//		for (FeatureNode<String> fn : fg.vertices()) {
//			/*
//			 * if (FeatureNodeUtils.isTop(fn)) continue ; if
//			 * (FeatureNodeUtils.isBottom(fn)) continue ;
//			 */
//			dagFG.addVertex(new FeatureNode<String>(fn.getFeature()));
//		}
//
//		Set<FeatureEdge> edges = fg.selectEdges(FeatureEdge.HIERARCHY);
//		for (FeatureEdge fe : edges) {
//			FeatureNode<String> ftSource = fg.getSource(fe);
//			FeatureNode<String> ftTarget = fg.getTarget(fe);
//			FeatureNode<String> source = getFtNodeFromGraph(ftSource, dagFG);
//			FeatureNode<String> target = getFtNodeFromGraph(ftTarget, dagFG);
//
//			assert (source != null);
//			assert (target != null);
//
//			dagFG.addEdge(source, target);
//
//		}
//
//		return dagFG;
//	}
//
//	private FeatureGraph<String> subgraphToFeatureGraph(
//			Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>> subg) {
//		Set<FeatureNode<String>> ftRoots = getRootsFromSubGraph(subg);
//		assert (ftRoots != null);
//		assert (ftRoots.size() >= 1);
//		_LOGGER.debug("roots subg=" + ftRoots);
//
//		FeatureGraph<String> rfg = FeatureGraphFactory.mkStringFactory()
//				.mkTop();
//
//		for (FeatureNode<String> fn : subg.vertexSet()) {
//			rfg.addVertex(new FeatureNode<String>(fn.getFeature()));
//			// rfg.addVertex(fn);
//		}
//
//		// 1 -> root
//		for (FeatureNode<String> ftRoot : ftRoots) {
//			rfg.addEdge(rfg.findVertex(ftRoot.getFeature()),
//					rfg.getTopVertex(), FeatureEdge.HIERARCHY);
//
//		}
//
//		for (DefaultEdge e : subg.edgeSet()) {
//			FeatureNode<String> subgSource = subg.getEdgeSource(e);
//			FeatureNode<String> subgTarget = subg.getEdgeTarget(e);
//
//			FeatureNode<String> source = rfg
//					.findVertex(subgSource.getFeature());
//			FeatureNode<String> target = rfg
//					.findVertex(subgTarget.getFeature());
//			if (!FeatureNodeUtils.isTop(target))
//				rfg.addEdge(source, target, FeatureEdge.HIERARCHY);
//		}
//
//		return rfg;
//	}
//
//	private void debugWithDOT(FeatureModelVariable fm,
//			Graph<FeatureNode<String>, DefaultEdge> sg, String qName) {
//		// TODO Auto-generated method stub
//
//		try {
//			_dotifier.export(
//					new FileWriter(new File(mkDotFileName(fm.getIdentifier()
//							+ qName))), sg);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
//
//	private void debugWithDOT(FeatureModelVariable fm,
//			Graph<FeatureNode<String>, DefaultEdge> sg) {
//
//		debugWithDOT(fm, sg, "");
//
//	}
//
//	private String mkDotFileName(String id) {
//		String OUTPUT_DOT = "output/dot/";
//		String DOT_EXTENSION = ".dot";
//		return OUTPUT_DOT + id + DOT_EXTENSION;
//	}
//
//	private FeatureNode<String> getRootFromSubGraph(
//			Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>> subg) {
//		Set<FeatureNode<String>> ftNodes = subg.vertexSet();
//		for (FeatureNode<String> ftNode : ftNodes) {
//			Set<DefaultEdge> oEdges = subg.edgesOf(ftNode);
//			int nParent = 0;
//			FeatureNode<String> fnParent = null;
//			for (DefaultEdge e : oEdges) {
//				FeatureNode<String> t = subg.getEdgeTarget(e);
//				if (t != ftNode) {
//					nParent++;
//					fnParent = t;
//				}
//			}
//			if (nParent == 1) {
//				assert (fnParent != null);
//				if (FeatureNodeUtils.isTop(fnParent))
//					return ftNode;
//			}
//
//		}
//		return null;
//	}
//
//	private Set<FeatureNode<String>> getRootsFromSubGraph(
//			Subgraph<FeatureNode<String>, DefaultEdge, SimpleDirectedGraph<FeatureNode<String>, DefaultEdge>> subg) {
//		Set<FeatureNode<String>> roots = new HashSet<FeatureNode<String>>();
//		Set<FeatureNode<String>> ftNodes = subg.vertexSet();
//		for (FeatureNode<String> ftNode : ftNodes) {
//			Set<DefaultEdge> oEdges = subg.edgesOf(ftNode);
//			FeatureNode<String> fnParent = null;
//			for (DefaultEdge e : oEdges) {
//				FeatureNode<String> t = subg.getEdgeTarget(e);
//				if (t != ftNode) {
//					fnParent = t;
//					if (FeatureNodeUtils.isTop(fnParent)) {
//						roots.add(ftNode);
//					}
//				}
//			}
//
//		}
//		return roots;
//	}
//
//	private FeatureNode<String> getRootFromGraph(
//			SimpleDirectedGraph<FeatureNode<String>, DefaultEdge> sg) {
//		Set<FeatureNode<String>> ftNodes = sg.vertexSet();
//		for (FeatureNode<String> ftNode : ftNodes) {
//			Set<DefaultEdge> oEdges = sg.outgoingEdgesOf(ftNode);
//			if (oEdges.size() == 1) {
//				DefaultEdge oEdge = oEdges.iterator().next();
//				FeatureNode<String> ftTarget = sg.getEdgeTarget(oEdge);
//				if (FeatureNodeUtils.isTop(ftTarget))
//					return ftNode;
//			}
//
//		}
//		return null;
//
//	}
//
//	private FeatureNode<String> getFtNodeFromGraph(
//			FeatureNode<String> ftSource,
//			SimpleDirectedGraph<FeatureNode<String>, DefaultEdge> sg) {
//
//		Set<FeatureNode<String>> vertices = sg.vertexSet();
//		Iterator<FeatureNode<String>> it = vertices.iterator();
//		while (it.hasNext()) {
//			FeatureNode<String> lFtNode = it.next();
//			if (lFtNode.equals(ftSource))
//				return lFtNode;
//
//		}
//
//		return null;
//	}

}
