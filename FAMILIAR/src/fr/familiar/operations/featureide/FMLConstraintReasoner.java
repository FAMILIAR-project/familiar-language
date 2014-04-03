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

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.prop4j.And;
import org.prop4j.Node;
import org.sat4j.specs.TimeoutException;

import de.ovgu.featureide.fm.core.editing.ModelComparator;
import fr.familiar.experimental.featureide.FMComparator;
import fr.familiar.fm.FeatureModelCloner;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.operations.IConstraintReasoner;
import fr.familiar.variable.Comparison;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Excludes;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.Requires;

public class FMLConstraintReasoner implements IConstraintReasoner {
	
	
	private static Logger _LOGGER = Logger.getLogger(FMLConstraintReasoner.class);
	
	private BDDBuilder<String> _builder;
	private FeatureModel<String> _fm;
	private de.ovgu.featureide.fm.core.FeatureModel _oModel = null; // syncrhonized with _fm
	
	
	public FMLConstraintReasoner(FeatureModelVariable fmv, BDDBuilder<String> builder) {
			this ((FeatureModel<String>) fmv.getFm(), builder);
	}
	
	public FMLConstraintReasoner(FeatureModel<String> fm, BDDBuilder<String> builder) {
		_builder = builder ; 
		_fm = fm ; 
	}

	@Override
	public boolean hasRedundantConstraints() {
		
		// first we check implies
		Set<Requires<String>> implies = _fm.getRequires() ;
		for (Requires<String> impl : implies) {
			if (isRendundant(impl))
				return true ; 
		}
		
		// second we check excludes
		Set<Excludes<String>> excludes = _fm.getExcludes() ;
		for (Excludes<String> excl : excludes) {
			if (isRendundant(excl))
				return true ;
		}
		
		// other kind of constraints
		// Set<Expression<String>> exprs = fm.getConstraints() ; // TODO diff with implies / excludes
		
		
		return false;
	}

	@Override
	public Set<Expression<String>> computeRedundantConstraints() {
		
		Set<Expression<String>> rExprs = new HashSet<Expression<String>>();
		
		
		
		// first we check implies
		Set<Requires<String>> implies = _fm.getRequires() ;
		for (Requires<String> impl : implies) {
			if (isRendundant(impl))
				rExprs.add(impl); 
		}
		
		// second we check excludes
		Set<Excludes<String>> excludes = _fm.getExcludes() ;
		for (Excludes<String> excl : excludes) {
			if (isRendundant(excl))
				rExprs.add(excl) ;
		}
		
		return rExprs;
	}

	/* 
	 * Very naive implementation
	 * (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.IConstraintReasoner#eliminateRedundantConstraints()
	 */
	@Override
	public FeatureModel<String> eliminateRedundantConstraints() {
		return _eliminateLoopRedundantConstraints() ; 		
	}
	
	
	
	public FeatureModel<String> _eliminateLoopRedundantConstraints() {
		FeatureModelVariable fmv = new FeatureModelVariable("", (FeatureModel<String>) _fm);
		// FIXME @FeatureIDE
		_oModel = new FMLtoFeatureIDE(fmv).convert() ; 
		Expression<String> redundantExpr = null ; 
		int redund = 0 ; 
		int max = fmv.getAllConstraints().size() ;
		while ( (redundantExpr = (Expression<String>) getNextRedundantConstraint()) != null) {
			fmv.removeConstraint( redundantExpr);
			_oModel.removePropositionalNode(new SATFMLFormula(redundantExpr).getNode()); // update the representation
			redund++ ; 
			_LOGGER.debug("" + redund + "/" + max + " : " + redundantExpr);
		}
		return fmv.getFm() ; 
	}
	

	public FeatureModel<String> _eliminateRedundantConstraints() {
		
		FeatureModelVariable fmv = new FeatureModelVariable("", (FeatureModel<String>) _fm);
		Set<Expression<String>> red = computeRedundantConstraints() ;
		for (Expression<String> redundantExpr : red) {
			fmv.removeConstraint((Expression<String>) redundantExpr);
		}
		return fmv.getFm() ; 
		
	}
	
	
	private Expression<String> getNextRedundantConstraint() {
		// first we check implies
		Set<Requires<String>> implies = _fm.getRequires() ;
		for (Requires<String> impl : implies) {
			if (isRendundant(impl))
				return impl ; 
		}
		
		// second we check excludes
		Set<Excludes<String>> excludes = _fm.getExcludes() ;
		for (Excludes<String> excl : excludes) {
			if (isRendundant(excl))
				return excl ;
		}
		
		return null ; 
	}

	private boolean isRendundant(Expression<String> expr) {
		
		return isRedundantWithDiagram(expr) ; //isTautology(expr) || isRedundantWithDiagram(expr) ; 
	}

	/**
	 * by diagram I mean feature hierarchy + feature groups + other constraints!
	 * @param fmv
	 * @param expr
	 * @return
	 */
	@Deprecated
	private boolean isRedundantWithDiagram(Expression<String> expr) {
		
				
		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		/*SATFormula flaDiagram = new SATFeatureIDEFormula(fmvDiagram) ;
		SATFMLFormula exprFla = new SATFMLFormula((Expression<String>)expr);
		
		Node newNode = removeClause (flaDiagram.getNode(), exprFla.getNode());
	
		boolean b = new SATFormulaComparator(SATFMLFormula.SAT_TIMEOUT).compare (
				new SATFMLFormula(newNode), flaDiagram) == Comparison.REFACTORING ;
				*/
		if (_oModel == null) {
			FeatureModelVariable fmvDiagram = new FeatureModelVariable("", FeatureModelCloner.clone((FeatureModel<String>) _fm)) ; 
			// FIXME @FeatureIDE 
			_oModel = new FMLtoFeatureIDE(fmvDiagram).convert()  ;
		}
		de.ovgu.featureide.fm.core.FeatureModel dirtyModel = _oModel.clone() ;
		Node exprNode = exprFla.getNode() ;
		dirtyModel.removePropositionalNode(exprNode);
		ModelComparator comparator = new ModelComparator(SATFMLFormula.SAT_TIMEOUT);
		Comparison comparison = FMComparator.convert(comparator.compare(_oModel, dirtyModel));
		boolean b = comparison == Comparison.REFACTORING ; 
			
		//fmvDiagram.removeConstraint((Expression<String>) expr);
		//FeatureModelVariable fmv = new FeatureModelVariable("", (FeatureModel<String>) _fm);
		//boolean b = fmv.compareSAT_Formula(fmvDiagram) == Comparison.REFACTORING ; 
		//boolean b = fmv.compareSAT(fmvDiagram, (BDDBuilder<String>) _builder, false) == Comparison.REFACTORING ; 
		//boolean b = fmv.compareBDD(fmvDiagram, (BDDBuilder<String>) _builder) == Comparison.REFACTORING ; 
		//_LOGGER.debug("expr=" + expr + " (" + b + ")") ;
		return b;
	}
	
	private boolean _isRedundantWithDiagram(Expression<String> expr) {
		
		
		FeatureModelVariable fmvDiagram = new FeatureModelVariable("" , FeatureModelCloner.clone((FeatureModel<String>) _fm)); 
		SATFMLFormula flaDiagram = new SATFMLFormula(fmvDiagram);
		Node nodeDiagram = flaDiagram.getNode() ; 
		Node nExpr = new SATFMLFormula((Expression<String>)expr).getNode();
		boolean b;
		try {
			b = biimplies (nodeDiagram, nExpr);
		} catch (TimeoutException e) {
			b = false ; 
			_LOGGER.debug("Unable to check redundacy");
			e.printStackTrace();
		}
//		SATFMLFormula flaNew = new SATFMLFormula(nWithout);
		/*boolean b = new SATFormulaComparator(SATFormula.SAT_TIMEOUT).
								compare(flaDiagram, flaNew) == Comparison.REFACTORING;*/
		_LOGGER.debug("expr" + expr + " (" + b + ")");
		return b ;

	}

	private boolean biimplies(Node nWithout, Node nodeDiagram) throws TimeoutException {
		SATFormulaComparator cmp = new SATFormulaComparator(SATFMLFormula.SAT_TIMEOUT) ; 
		return cmp.implies(nodeDiagram, nWithout) && cmp.implies(nWithout, nodeDiagram);
	}

	private Node removeClause(Node node, Node nClause) {
		LinkedList<Node> updatedNodes = new LinkedList<Node>();
		for (Node child : node.getChildren())
			if (!nClause.equals(node))
				updatedNodes.add(child);
		return updatedNodes.isEmpty() ? null : new And(updatedNodes);
		
	}
	
	private boolean containedIn(Node node, Node[] nodes) {
		for (Node child : nodes)
			if (node.equals(child))
				return true;
		return false;
	}

	private boolean isTautology(Expression<String> expr) {
		SATFMLFormula satsolverTAU = new SATFMLFormula((Expression<String>)expr.not());
		if (!satsolverTAU.isValid())
			return true ; 
		else
			return false ; 	
	}

}
