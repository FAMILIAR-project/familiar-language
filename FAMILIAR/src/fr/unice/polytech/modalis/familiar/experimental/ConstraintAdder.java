package fr.unice.polytech.modalis.familiar.experimental;

import fr.unice.polytech.modalis.familiar.fm.SimpleExtendedEdge;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.operations.ImplicationGraphUtil;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.parser.FMLCommandInterpreter;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.IGBuilder;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public abstract class ConstraintAdder {
	
	private static Logger _LOGGER = Logger.getLogger(ConstraintAdder.class);

	
	protected Set<Expression<String>> _biimplies = null ; 
	
	protected FeatureModel<String> _fm ;

	protected ImplicationGraph<String> _impl = null ; 
	
	protected ExclusionGraph<String> _excl1 = null ;

	
	public ConstraintAdder(FeatureModel<String> fm, 
			Set<Expression<String>> biimplies, 
			ImplicationGraph<String> impl, 
			ExclusionGraph<String> excl1
			) {
	_fm = fm ; 
	_biimplies = biimplies ; 
	_impl = impl ; 
	_excl1 = excl1 ; 
	}
	
	public ConstraintAdder(FeatureModel<String> fm) {
		_fm = fm ; 
	}

	 
	public abstract boolean addNonEntailedConstraint(Expression<String> expr) ;
	
	
	public FeatureModel<String> apply() {
		assert(_biimplies != null) ;
		assert(_impl != null) ; 
		assert(_excl1 != null);
		
		int nBI = _biimplies.size() ; 
		int bi = 0; 
		for (Expression<String> biimply : _biimplies) {
			boolean b1 = addNonEntailedConstraint(biimply);
			// time consuming: 
			_LOGGER.debug("expr(" + bi++ + "/" + nBI + ") =" + biimply + " (" + b1 + ")");			
		}
		
		_LOGGER.debug("Computing IE");
		_impl = FGBuilderUtils._INSTANCE.transitiveReductions(_impl);
		Set<Expression<String>> implies2 = computeImpliesEdge(_fm.clone(), _impl);
		_LOGGER.debug("Now adding implies");

		int nI = implies2.size() ; 
		int i = 0 ; 
		for (Expression<String> imply : implies2) {
			boolean b1 = addNonEntailedConstraint(imply);
			// time consuming: 
			_LOGGER.debug("expr(" + i++ + "/" + nI + ") =" + imply + " (" + b1 + ")");			
		}

		_LOGGER.debug("Computing EX");
		Set<Expression<String>> excludes2 = 
			computeExcludesEdge(new FeatureModel<String>(_fm.getDiagram().clone()), _excl1);
		_LOGGER.debug("Now adding excludes");
		int nE = excludes2.size() ; 
		int e = 0 ; 
		for (Expression<String> exclude : excludes2) {
			boolean b1 = addNonEntailedConstraint(exclude);
			// time consuming: 
			_LOGGER.debug("expr(" + e++ + "/" + nE + ") =" + exclude + " (" + b1 + ")");
			
		}
		return _fm ; 
	}
	
	protected Set<Expression<String>> computeExcludesEdge(FeatureModel<String> fm, ExclusionGraph<String> excl1) {
		
		
		ExclusionGraph<String> exclE = getExclusionGraphFromFeatureHierarchy(fm);

		Set<SimpleExtendedEdge<String>> des = ImplicationGraphUtil.diffExclEdges(
				excl1, exclE);
		
		//findMutexCliques(g);
		
		Set<Expression<String>> excludes = new HashSet<Expression<String>>();
		for (SimpleExtendedEdge<String> de : des) {
			String source = de.getSource();
			String target = de.getTarget();
			excludes.add(new Expression<String>(ExpressionType.IMPLIES,
					new Expression<String>(target), new Expression<String>(source).not()));
		}
		
		

		return excludes; //
	}
	
	private ExclusionGraph<String> getExclusionGraphFromFeatureHierarchy(
			FeatureModel<String> fm) {
		// FIXME @FeatureIDE
		return new SATFMLFormula(new FeatureModelVariable("",
		 (FeatureModel<String>) fm)).computeExclusionGraph(fm.features());
		
		// TODO: parameterize as well (SAT vs BDD)
		/*
		BDDBuilder<String> builder = FMLCommandInterpreter.getBuilder() ; 
		return EGBuilder.build(builder.mkFeatureModel(fm), builder);
		*/
	}
	
	/**
	 * the implies edges not expressed by g but by impl
	 * 
	 * @param g
	 *            the current feature diagram
	 * @param impl
	 *            implication graph of the formula
	 * @return
	 */
	protected Set<Expression<String>> computeImpliesEdge(FeatureModel<String> fm,
			ImplicationGraph<String> impl) {
		
		
		ImplicationGraph<String> implE = _getImplicationGraphFromFeatureHierarchyWithSAT(fm);
	
		//implE = transitiveReductions(implE);
		
		Set<SimpleExtendedEdge<String>> des = ImplicationGraphUtil.diffEdges(impl, implE);
		Set<Expression<String>> imply = new HashSet<Expression<String>>();
		for (SimpleExtendedEdge<String> de : des) {
			String source = de.getSource();
			String target = de.getTarget();
			imply.add(new Expression<String>(ExpressionType.IMPLIES,
					new Expression<String>(source), new Expression<String>(target)));
		}
		return imply;
	}
	
	private ImplicationGraph<String> _getImplicationGraphFromFeatureHierarchyWithSAT(
			FeatureModel<String> fm) {
		// FIXME @FeatureIDE
		 return new SATFMLFormula(new FeatureModelVariable("",
		 (FeatureModel<String>) fm)).computeImplicationGraph(fm.features());
		
		// TODO: parameterize as well (SAT vs BDD)
		// BDDBuilder<String> builder = FMLCommandInterpreter.getBuilder() ; 
		// return IGBuilder.build(builder.mkFeatureModel(fm), builder);
	}
	

	
	

}
