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

package fr.familiar.variable.featureide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.prop4j.And;
import org.prop4j.Node;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.BDDFormula;
import fr.familiar.operations.KSynthesis;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.operations.SlicerBDDFormula;
import fr.familiar.operations.SynthesisStrategy;
import fr.familiar.operations.featureide.KSynthesisSAT;
import fr.familiar.operations.featureide.NodeUtility;
import fr.familiar.operations.featureide.SATFMLFormula;
import fr.familiar.operations.featureide.SATFormula;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureModelVariableBDDFormula;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.StringVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.Formula;

/**
 * TODO: inherit from FMLFeatureModel
 * @author macher
 *
 */
public class FeatureModelVariableConstraints extends FeatureModelVariable {

	private static final int _MAX_SHOW_CST = 100 ;
	protected Collection<Expression<String>> _csts;

	public FeatureModelVariableConstraints(String assigner, Collection<Expression<String>> clauses) {
		super (assigner, null);
		_csts = clauses ; 
	}
	
	public FeatureModelVariableConstraints(Expression<String> expression) {
		super (null, null);
		_csts = new HashSet<Expression<String>>() ; 
		_csts.add (expression);
		
	}

	@Override
	public SetVariable features() {
		Set<Variable> varsR = new HashSet<Variable>();
		
		// we collect all features
		Set<String> fts = new HashSet<String> () ;
		for (Expression<String> expr : _csts) {
			fts.addAll(ExpressionUtil.getAllFeatures(expr)); 
		}
		
		for (String ft : fts) {
			varsR.add(new StringVariable("", ft));
		}
		
		SetVariable sv = new SetVariable(varsR);
		return sv;
	}
	
	@Override
	public Formula<String> getFormula() {
		// TODO lazy strategy for computing formula are feasible
		BDDBuilder<String> builder = getBuilder() ; 
		BDD rBDD = builder.one() ; 
		for (Expression<String> expr : _csts) {
			rBDD.andWith(builder.mkExpression(expr)); 
		}
		return new Formula<String> (rBDD, features().names(), builder) ; 
	}
	
	@Override
	public boolean addConstraint(Expression<String> e) {
		return _csts.add(e); 
	}
	
	@Override
	public boolean removeConstraint(Expression<String> e) {
		return _csts.remove(e); 
	}

	public SATFormula getSATFormula() {
		List<Node> nodes = new ArrayList<Node> () ; 
		for (Expression<String> e : _csts) {
			nodes.add(NodeUtility.toNode(e));		
		}		
		SATFMLFormula fla = new SATFMLFormula(new And(nodes));
		return fla ; 
	}

	
	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		// TODO: BDD by default
		BDDFormula fla = (BDDFormula) new SlicerBDDFormula(getBuilder()).sliceFormula(this, 
				fts, sliceMode) ; 
		return new FeatureModelVariableBDDFormula("", fla, getBuilder());  
	}
	
	@Override
	public String getSpecificValue() {
		StringBuilder sb = new StringBuilder() ;
		int i = 0 ; 
		for (Expression<String> expr : _csts) {
			sb.append(expr.toString() + " ");
			if (i++ > _MAX_SHOW_CST) {
				sb.append(" ... [only the first " + _MAX_SHOW_CST + " constraints are displayed]") ; 
				break;
			}
		}
		
		return sb + " (constraints, nor synthesized neither 'formulaed')" ;
	}
	
	@Override
	public FeatureModelVariable ksynthesis(KnowledgeSynthesis kn, SynthesisStrategy synthStrategy) {
		KSynthesis synth = null ; 
		if (synthStrategy == SynthesisStrategy.BDD)
			synth = new KSynthesisBDD(getFormula(), kn, getBuilder());
		else if (synthStrategy == SynthesisStrategy.SAT)
			synth = new KSynthesisSAT(getSATFormula(), kn) ;
		else {
			FMLShell.getInstance().printError("Unknown synthesis strategy: " + synthStrategy) ; 
			return null ; 
		}
		assert (synth != null);
		return synth.build() ; 
	}
	
	@Override
	public Set<Expression<String>> getAllConstraints() {
		return new HashSet<Expression<String>>(_csts); 
		
	}
	
	
	
	

}
