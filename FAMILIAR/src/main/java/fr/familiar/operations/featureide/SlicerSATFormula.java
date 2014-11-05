/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations.featureide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;
import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Not;
import org.prop4j.Or;
import org.xtext.example.mydsl.fML.SliceMode;

import com.google.common.collect.Sets;

import fr.familiar.experimental.featureide.Node4JUtil;
import fr.familiar.fm.FMLFormula;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.FeatureOrderSelectionByDepth;
import fr.familiar.operations.FeatureOrderSelectionByDepthAndSiblings;
import fr.familiar.operations.PreprocessSliceStrategy;
import fr.familiar.operations.SlicerFormula;
import fr.familiar.operations.SortStrategySlicingCriterion;
import fr.familiar.variable.FeatureModelVariable;

public class SlicerSATFormula extends SlicerFormula {
	
	
	private static Logger _LOGGER = Logger.getLogger(SlicerSATFormula.class);


	private HashMap<Object, Node> replacingMapAbstractFts = new HashMap<Object, Node>();

	public HashMap<Object, Node> getReplacingMapAbstractFts() {
		return replacingMapAbstractFts;
	}

	public void setReplacingMapAbstractFts(
			HashMap<Object, Node> replacingMapAbstractFts) {
		this.replacingMapAbstractFts = replacingMapAbstractFts;
	}

	public SlicerSATFormula() {
		
	}

	
	@Override
	public FMLFormula sliceFormula(FeatureModelVariable fmv, Collection<String> fts, SliceMode mode) {

		Node node = new SATFMLFormula(fmv).getNode() ;  
		FMLFormula fla = runSliceFormulaSAT(fmv, node,
				fmv.features().names(), fts, mode);
		return fla ;
	}
	
	

	/**
	 * @param p
	 *            the node to process
	 * @param allFtsDomain
	 *            set of all features
	 * @param fts
	 *            slicing criterion
	 * @param mode
	 *            slicing mode
	 * @param id
	 *            identifier
	 * @return a new formula
	 */
	public FMLFormula runSliceFormulaSAT(FeatureModelVariable fmv, Node p, Collection<String> allFtsDomain,
			Collection<String> fts, SliceMode mode) {

		// _LOGGER.debug("p=" + p.toString());
		Collection<String> ftsToRemove = SlicerSATFormula.calculateFtsToExclude(
				allFtsDomain, fts, mode);
		/*
		 * _LOGGER.debug("allFtsDomain=" +
		 * allFtsDomain);
		 * _LOGGER.debug("ftsToRemove=" +
		 * ftsToRemove); _LOGGER.debug("fts=" + fts);
		 */
		Collection<String> actualFtsToRemove = preprocess(p, ftsToRemove,
				PreprocessSliceStrategy.SIMPLE); //

		
		List<String> orderedFtsToRemove = sortFts(fmv, actualFtsToRemove, 
				SortStrategySlicingCriterion.RANDOM);
				//SortStrategySlicingCriterion.UP) ;
	
		_LOGGER.debug(
				"#Fts=" + orderedFtsToRemove.size());

		if (!replacingMapAbstractFts.isEmpty())
			p = SATBuilder.replaceAbstractFeature(p, replacingMapAbstractFts);

		p = new And(p, SATBuilder.mkTrueNode(), new Not(
				SATBuilder.mkFalseNode()));

		int nFt = 0;
		for (String ft : orderedFtsToRemove) {

			_LOGGER.debug("\tft(" + nFt++ + ")=" + ft);
			List<Node> nochange = new LinkedList<Node>();
			List<Node> change = new LinkedList<Node>();
			List<Node> pConjuncts = Node4JUtil.splitConjunctions(p);

			calculateNodesToReplace(pConjuncts, ft, nochange, change);

			if (change.isEmpty()) {
				continue;
			}

			Node toChange = new And(change);

			// substitue (true)
			Node pTrue = replaceFeature(toChange.clone(), ft,
					SATBuilder.mkTrueNode());

			/*
			 * Node pTrueEvaluation = new And(pTrue, nochange) ; if (!new
			 * SATFMLFormula(pTrueEvaluation).isValid()) { // false pTrue = null
			 * ; }
			 */

			// substitute (false)
			Node pFalse = replaceFeature(toChange.clone(), ft,
					SATBuilder.mkFalseNode());

			/*
			 * Node pFalseEvaluation = new And(pFalse, nochange) ; if (!new
			 * SATFMLFormula(pFalseEvaluation).isValid()) { // false pFalse =
			 * null ; }
			 */

			Node nOr = mkNodeOr(pTrue, pFalse, orderedFtsToRemove);

			// connect to the common part
			Node nodeNoChange = null;
			if (nochange.isEmpty())
				nodeNoChange = SATBuilder.mkTrueNode();
			else
				nodeNoChange = new And(nochange);

			p = new And(nodeNoChange, nOr);
			// p = SATBuilder.simplify(p, orderedFtsToRemove);
			_LOGGER.debug(
					"#p:" + Node4JUtil.countSizeOfNode(p));

		}

		_LOGGER.debug("Slice formula computed!");

		// TODO: could be FeatureIDEFormula as well
		//SATFeatureIDEFormula oFormula = new SATFeatureIDEFormula(p);
		SATFMLFormula oFormula = new SATFMLFormula(p);  

		return oFormula;
	}

	private Collection<String> preprocess(Node p, Collection<String> ftsToRemove,
			PreprocessSliceStrategy strategy) {
		switch (strategy) {
		case SIMPLE:
			return _preprocessSimple(p, ftsToRemove);
		case CORE_DEADS:
			return _preprocessCoreDeads(p, ftsToRemove);
		default:
			return null;
		}
	}

	private Collection<String> _preprocessSimple(Node p, Collection<String> ftsToRemove) {
		p = new And(p, SATBuilder.mkTrueNode(), new Not(
				SATBuilder.mkFalseNode()));

		p = SATBuilder.simplify(p, ftsToRemove);
		return ftsToRemove;
	}

	/**
	 * replace core / dead features by True / False values (useful for
	 * simplifications)
	 * 
	 * @param p
	 * @param ftsToRemove
	 * @return the set of non core / dead features
	 */
	private Collection<String> _preprocessCoreDeads(Node p, Collection<String> ftsToRemove) {
		/*
		 * first we remove away core features
		 */
		Set<String> coresFt = new SATFMLFormula(p).cores(ftsToRemove);
		_LOGGER.debug(
				"****** DONE with core features #" + coresFt.size());

		for (String coreFt : coresFt) {
			p = replaceFeature(p.clone(), coreFt, SATBuilder.mkTrueNode());
		}

		Set<String> ftsToRemove2 = Sets.difference(new HashSet<String>(ftsToRemove), coresFt);

		/*
		 * second we remove away dead features
		 */
		Set<String> deadsFt = new SATFMLFormula(p.clone()).deads(ftsToRemove2);
		_LOGGER.debug(
				"****** DONE with dead features #" + deadsFt.size());

		for (String deadFt : deadsFt) {
			p = replaceFeature(p.clone(), deadFt, SATBuilder.mkFalseNode());
		}

		Set<String> actualFtsToRemove = Sets.difference(ftsToRemove2, deadsFt);

		p = new And(p, SATBuilder.mkTrueNode(), new Not(
				SATBuilder.mkFalseNode()));

		p = SATBuilder.simplify(p, actualFtsToRemove);

		return actualFtsToRemove;

	}

	private List<String> sortFts(FeatureModelVariable fmv, Collection<String> actualFtsToRemove,
			SortStrategySlicingCriterion strategy) {
			
		switch (strategy) {
			case RANDOM:
				List<String> randomFts = new ArrayList<String>(
						actualFtsToRemove);
				Collections.rotate(randomFts, new Random().nextInt());
				return randomFts;
			case UP:
				List<String> nFts = new FeatureOrderSelectionByDepth(fmv, actualFtsToRemove).compute() ;
				Collections.reverse(nFts);
				return nFts ; 
			case UP_AND_SIBLING:
				List<String> nFtsSiblings = new FeatureOrderSelectionByDepthAndSiblings(fmv, actualFtsToRemove).compute() ;
				Collections.reverse(nFtsSiblings);
				return nFtsSiblings ;
	
			case BOTTOM:
				return new FeatureOrderSelectionByDepth(fmv, actualFtsToRemove).compute() ;
	
			default:
				return new ArrayList<String>(actualFtsToRemove);
		}
	}

	
	



	private Node mkNodeOr(Node pTrue, Node pFalse,
			Collection<String> ftsToRemove) {
		Node nOr = null;
		if (pTrue == null && pFalse == null) {
			FMLShell.getInstance()
					.printError(
							"Unexpected error during the slicing (pTrue and pFalse are not satisfiable)");
			return null;
		}
		if (pTrue == null)
			nOr = SATBuilder.simplify(pFalse, ftsToRemove); // SATBuilder.simplify(pFalse.toCNF(),
															// ftsToRemove) ;
															// //pFalse.toCNF()
															// ;
		else if (pFalse == null)
			nOr = SATBuilder.simplify(pTrue, ftsToRemove); // SATBuilder.simplify(pTrue.toCNF(),
															// ftsToRemove) ;
		else
			nOr = new Or(pTrue, pFalse);
		// new Or(SATBuilder.simplify(pTrue, ftsToRemove),
		// SATBuilder.simplify(pFalse, ftsToRemove)) ;
		// _LOGGER.debug("CNF or");

		// nOr = SATBuilder.simplify(nOr, ftsToRemove);
		if (pFalse != null && pTrue != null) { // otherwise already in CNF
			nOr = nOr.toCNF(); // mkCNF(nOr) ;

			// Node nO2 = new ConverterCNF(nOr).convert();

			// _LOGGER.debug("nOr (cnf)=" + nOr);
			nOr = SATBuilder.simplify(nOr, ftsToRemove);
			// if (!(nOr instanceof And))
			// nOr = new And(nOr);
		}
		// _LOGGER.debug("end CNF or");
		return nOr;
	}

	


//	private SATFormula _runSliceFormulaSAT(Node p, Set<String> allFtsDomain,
//			Set<String> ftsToRemove, Set<String> fts, SliceMode mode, String id) {
//
//		p = p.toCNF();
//		for (String ft : ftsToRemove) {
//
//			_LOGGER.debug("\tft=" + ft);
//
//			List<Node> nochange = new LinkedList<Node>();
//			List<Node> change = new LinkedList<Node>();
//
//			List<Node> pConjuncts = Node4JUtil.splitConjunctions(p);
//
//			calculateNodesToReplace(pConjuncts, ft, nochange, change);
//
//			if (change.isEmpty())
//				continue;
//
//			Node toChange = new And(change);
//
//			// substitue (true)
//			Node pTrue = replaceFeature(toChange.clone(), ft,
//					SATBuilder.mkTrueNode());
//			Node pFalse = replaceFeature(toChange.clone(), ft,
//					SATBuilder.mkFalseNode());
//
//			Node nodeNoChange = null;
//			if (nochange.isEmpty())
//				nodeNoChange = SATBuilder.mkTrueNode();
//			else
//				nodeNoChange = new And(nochange);
//
//			Node nOr = new Or(pTrue, pFalse);
//			_LOGGER.debug("CNF or");
//			// nOr = SATBuilder.simplify(nOr);
//			nOr = nOr.toCNF();
//			// nOr = SATBuilder.simplify(nOr) ;
//			_LOGGER.debug("end CNF or");
//			p = // simplify(
//			new And(nodeNoChange, nOr);
//		}
//
//		p = new And(p, SATBuilder.mkTrueNode(), new Not(
//				SATBuilder.mkFalseNode()));
//		SATFeatureIDEFormula oFormula = new SATFeatureIDEFormula(p);
//
//		return oFormula;
//	}
	
//	private Node mkConjuncts(List<Node> clauses) {
//		Node n = SATBuilder.mkTrueNode();
//		for (Node node : clauses) {
//			n = new And(n, node);
//		}
//		return n;
//	}



	private static void calculateNodesToReplace(List<Node> children,
			String abstractFeature, List<Node> nochange, List<Node> change) {
		for (Node node : children)
			if (nodeContains(node, abstractFeature)) {
				change.add(node);
			} else
				nochange.add(node);
	}

	private static boolean nodeContains(Node node, String abstractFeature) {
		if (node instanceof Literal) {
			Literal lit = (Literal) node;
			return lit.var.equals(abstractFeature);
		}
		for (Node child : node.getChildren()) // Not (...) is also in this case
			if (nodeContains(child, abstractFeature))
				return true;
		return false;
	}

	

	private Node replaceFeature(Node node, Object abstractFeature,
			Literal replacement) {
		if (node instanceof Literal) {
			Literal lit = (Literal) node;
			if (lit.var.equals(abstractFeature)) {
				Literal l = (Literal) replacement.clone();
				l.positive = lit.positive;
				return l;
			} else
				return node;
		}
		Node[] children = node.getChildren();
		for (int i = 0; i < children.length; i++) {
			children[i] = replaceFeature(children[i], abstractFeature,
					replacement);
		}
		return node;
	}

	public static Set<String> calculateFtsToExclude(Collection<String> allFtsDomain,
			Collection<String> fts, SliceMode mode) {
		Set<String> ftsToRemove = new HashSet<String>();
		if (mode.equals(SliceMode.EXCLUDING)) {
			ftsToRemove.addAll(fts);
			// we also slice the synthetic features
			/*
			 * Set<String> ftsSynthetic = allFtsDomain ; for (String ft :
			 * ftsSynthetic) { if
			 * (ft.startsWith(FMLtoFeatureIDE.SYNTHETIC_FT_NAME_START)) {
			 * ftsToRemove.add(ft); } }
			 */
		} else {
			ftsToRemove.addAll(Sets.difference(new HashSet<String>(allFtsDomain), new HashSet<String>(fts)));
		}
		return ftsToRemove;

	}

}
