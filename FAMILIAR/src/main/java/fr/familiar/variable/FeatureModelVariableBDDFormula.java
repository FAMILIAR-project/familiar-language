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

package fr.familiar.variable;

import fr.familiar.operations.BDDFormula;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.operations.KSynthesisBDD;
import fr.familiar.operations.KnowledgeSynthesis;
import fr.familiar.operations.SlicerBDDFormula;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.Formula;
import gsd.synthesis.IGBuilder;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.SliceMode;

public class FeatureModelVariableBDDFormula extends FeatureModelVariable  {

	
	
	
	public FeatureModelVariableBDDFormula(String assigner, Formula<String> fla, BDDBuilder<String> builder) {
		super (assigner, null, fla) ; 
		_synthesizedFM = false ; 
		setBuilder(builder); 
	}

	@Override
	public Formula<String> getFormula() {
		return _formula ; 
	}
	

	@Override
	public FeatureVariable root() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean addConstraint(Expression<String> e) {
		BDDBuilder<String> builder = getBuilder() ; 
		Set<String> domain = ExpressionUtil.getAllFeatures(e) ; 
		_formula.andWith(new Formula<String>(builder.mkExpression(e), domain, builder));
		return features().names().containsAll(domain) ; 
	}

	@Override
	public boolean isValid() {
		return !_formula.isZero();
	}

	@Override
	public SetVariable features() {
		Set<Variable> vars = new HashSet<Variable>() ;

		for (String dom : _formula.getDomain()) {
		    if (dom.equals("True") || dom.equals("False"))
				continue ; // special values
			
			StringVariable sv = new StringVariable(null, dom);
			vars.add(sv);
		}
		
		return new SetVariable(vars);
	}

	@Override
	public boolean setMandatory(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOptional(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setAlternative(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean setOr(FeatureVariable ft) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double counting() {
		if (_formula.isZero())
			return 0;
		return new FormulaAnalyzer<String>(_formula, getBuilder()).counting();
	}

	@Override
	public double CTCR() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RType getRType() {
		return RType.FEATURE_MODEL ; 
	}

	@Override
	public Variable copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(Variable vari) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSpecificValue() {
		if (_synthesizedFM) return super.getSpecificValue() ;
		return _formula.getDomain() + " (formula BDD, unsynthesized feature model)";
	}

	@Override
	public Set<String> cores() {
		FormulaAnalyzer<String> fop = new FormulaAnalyzer<String>(_formula, getBuilder()); 
		Set<String> cores = fop.computeCoreFeatures();
		cores.remove("True");
		cores.remove("False");
		return cores;
	}

	@Override
	public Set<String> deads() {
		FormulaAnalyzer<String> fop = new FormulaAnalyzer<String>(_formula, getBuilder()); 
		Set<String> deads = fop.computeDeadFeatures();
		deads.remove("True");
		deads.remove("False");
		return deads;
	}
	
	@Override 
	public ImplicationGraph<String> computeImplicationGraph() {
		return IGBuilder.build(getFormula(), getBuilder());
	}
	
	
	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		
		
		BDDFormula slicedFormula = (BDDFormula) new SlicerBDDFormula(getBuilder()).sliceFormula(this, fts, sliceMode);
		return new FeatureModelVariableBDDFormula("", slicedFormula, getBuilder()) ; 
	}
	
	@Override
	public FeatureModelVariable _ksynthesis(KnowledgeSynthesis kn) {
		return new KSynthesisBDD(getFormula(), kn, getBuilder()).build() ; 
	}
}
