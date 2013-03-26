/**
 * 
 */
package fr.unice.polytech.modalis.familiar.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import fr.unice.polytech.modalis.familiar.fm.FeatureModelCloner;
import fr.unice.polytech.modalis.familiar.parser.FMBuilder;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author macher1
 *
 */
public class AggregatorFM {
	
	
	private static Logger _LOGGER = Logger.getLogger(AggregatorFM.class) ; 


	/**
	 * 
	 */
	public AggregatorFM() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param lfmvs
	 *            list of feature model variables
	 * @param constraints
	 *            list of constraints to be "mapped"
	 * @param fakeRootName
	 *            root name of the feature model
	 * @return an aggregated FM
	 */
	public FeatureModelVariable build(
			List<FeatureModelVariable> lfmvs,
			Set<Expression<String>> constraints, String fakeRootName) {

		List<FeatureModel<String>> lfms = new ArrayList<FeatureModel<String>>();
		for (FeatureModelVariable fmv : lfmvs) {
			lfms.add(FeatureModelCloner.clone(fmv.getFm())); // TODO perhaps we can do that at the formula level (very simple)
		}
		return aggregate (lfms, constraints, fakeRootName);
	}

	/**
	 * @param lfms
	 *            list of feature models
	 * @param constraints
	 *            list of constraints to be "mapped"
	 * @param fakeRootName
	 *            root name of the feature model
	 * @return an aggregated FM
	 */
	private FeatureModelVariable aggregate(
			List<FeatureModel<String>> lfms,
			Set<Expression<String>> constraints, String fakeRootName) {

		FeatureModel<String> aggregatedFM = FMBuilder.getInternalFM(fakeRootName + " ;");

		FeatureGraph<String> aggregatedGraph = aggregatedFM.getDiagram();
		FeatureNode<String> root = aggregatedGraph.findVertex(fakeRootName);

		for (FeatureModel<String> aspectFM : lfms) { // can be seen as an aspect
														// to be inserted into a
														// fake root

			FeatureGraph<String> aspectGraph = aspectFM.getDiagram();

			// clone
			for (FeatureNode<String> v : aspectGraph.vertices()) {
				aggregatedGraph.addVertex(v);
			}

			// clone
			for (FeatureEdge e : aspectGraph.edges()) {

				FeatureNode<String> target = aspectGraph.getTarget(e);
				if (!target.isTop()
						&& !(target.getFeature()
								.equals(FeatureGraphFactory.DEFAULT_TOP_STRING))) {

					// hack (Or is > Xor)
					if (e.getType() == FeatureEdge.XOR) {
						if (aggregatedGraph.findEdge(aspectGraph.getSources(e),
								aspectGraph.getTarget(e), FeatureEdge.OR) != null) {
							continue;
						}
					}
					if (e.getType() == FeatureEdge.OR) {
						FeatureEdge feXor = aggregatedGraph.findEdge(
								aspectGraph.getSources(e),
								aspectGraph.getTarget(e), FeatureEdge.XOR);
						if (feXor != null) {
							aggregatedGraph.removeEdge(feXor);
						}
					}

					// endHack

					aggregatedGraph.addEdge(aspectGraph.getSources(e),
							aspectGraph.getTarget(e), e.getType());

				}
			}

			FeatureNode<String> aspectRootNode = getRoot(aspectFM);

			// retrieve the feature in aggregatedGraph using the name
			FeatureNode<String> ftRoot = aggregatedGraph
					.findVertex(aspectRootNode.getFeature());

			aggregatedGraph.addEdge(ftRoot, root, FeatureEdge.HIERARCHY);
			aggregatedGraph.addEdge(ftRoot, root, FeatureEdge.MANDATORY);

			// constraints!
			aggregatedFM.addAllConstraints(aspectFM.getConstraints());

		}

		// TODO: very strange and dangerous!
		// remove 1 -> fakeRootName
		if (!aggregatedFM.getConstraints().isEmpty()) {

			// not only the first
			// Expression<String> cst =
			// aggregatedFM.getConstraints().iterator().next() ;
			for (Expression<String> cst : aggregatedFM.getConstraints()) {

				if (cst.getLeft() != null) {
					Expression<String> lcst = cst.getLeft();
					if (cst.getRight() != null) {
						Expression<String> rcst = cst.getRight();
						if ((cst.getType() == ExpressionType.IMPLIES)
								&& (rcst.getType() == ExpressionType.FEATURE)
								&& (lcst.getType() == ExpressionType.TRUE)) {
							if (rcst.getFeature().equals(fakeRootName))
								aggregatedFM.removeConstraint(cst);
						}

					}
				}

			}

		}

		FeatureModelVariable rFM = new FeatureModelVariable("", aggregatedFM);
		
		_LOGGER.debug("constraints=" + constraints);
	
		rFM.addAllConstraints(constraints); 
		
		FeatureModel<String> resultingFM = rFM.getFm() ; 
		//FeatureModelParser.parseString(fm.getFm().toString());
		resultingFM.getDiagram().addEdge(
				resultingFM.getDiagram().findVertex(fakeRootName),
				resultingFM.getDiagram().getTopVertex(), FeatureEdge.MANDATORY);
		
	
		return rFM; 

	}

	private static FeatureNode<String> getRoot(FeatureModel<String> fm) {
		FeatureGraph<String> fgraph = fm.getDiagram();
		return fgraph.children(fgraph.getTopVertex()).iterator().next();

	}

}
