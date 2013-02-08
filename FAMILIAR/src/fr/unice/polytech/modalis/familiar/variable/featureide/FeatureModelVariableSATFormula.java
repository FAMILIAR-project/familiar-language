/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable.featureide;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.SliceMode;

import fr.unice.polytech.modalis.familiar.fm.basic.IFeature;
import fr.unice.polytech.modalis.familiar.fm.converter.ExclusionGraph;
import fr.unice.polytech.modalis.familiar.operations.KnowledgeSynthesis;
import fr.unice.polytech.modalis.familiar.operations.featureide.KSynthesisSAT;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFormula;
import fr.unice.polytech.modalis.familiar.operations.featureide.SlicerSATFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.StringVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureModel;

/**
 * @author macher1
 *
 */
public class FeatureModelVariableSATFormula extends FeatureModelVariable {

	private SATFormula _fla;
	

	public FeatureModelVariableSATFormula(String identifier, SATFormula fla) {
		super (identifier, null, null);
		_synthesizedFM = false ; 
		_fla = fla ; 
	}

	public FeatureModelVariableSATFormula(String identifier, FeatureModel<String> synthesisedFM, SATFormula lFla) {
		super (identifier, synthesisedFM, null) ; 
		_synthesizedFM = true  ;
		_fla = lFla ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#root()
	 */
	@Override
	public FeatureVariable root() {
		if (_synthesizedFM) return super.root() ; 
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#isValid()
	 */
	@Override
	public boolean isValid() {
		return _fla.isValid() ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#features()
	 */
	@Override
	public SetVariable features() {
		Set<Variable> vars = new HashSet<Variable>() ;

		for (String dom : _fla.getDomain()) {
			if (dom.equals("True") || dom.equals("False"))
				continue ; 
			StringVariable sv = new StringVariable(null, dom);
			vars.add(sv);
		}
		
		return new SetVariable(vars);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#setMandatory(fr.unice.polytech.modalis.familiar.fm.basic.IFeature)
	 */
	@Override
	public boolean setMandatory(IFeature ft) {
		if (_synthesizedFM) return super.setMandatory(ft);
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#setOptional(fr.unice.polytech.modalis.familiar.fm.basic.IFeature)
	 */
	@Override
	public boolean setOptional(IFeature ft) {
		if (_synthesizedFM) return super.setOptional(ft);
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#setAlternative(fr.unice.polytech.modalis.familiar.fm.basic.IFeature)
	 */
	@Override
	public boolean setAlternative(IFeature ft) {
		if (_synthesizedFM) return super.setAlternative(ft);
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#setOr(fr.unice.polytech.modalis.familiar.fm.basic.IFeature)
	 */
	@Override
	public boolean setOr(IFeature ft) {
		if (_synthesizedFM) return super.setOr(ft);
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#counting()
	 */
	@Override
	public double counting() {
		return _fla.counting() ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#cores()
	 */
	@Override
	public Set<String> cores() {
		Set<String> cores = _fla.cores(_fla.getDomain());
		cores.remove("True");
		cores.remove("False");
		return cores ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#deads()
	 */
	@Override
	public Set<String> deads() {
		Set<String> deads = _fla.deads(_fla.getDomain());
		deads.remove("True");
		deads.remove("False");
		return deads ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModel#CTCR()
	 */
	@Override
	public double CTCR() {
		if (_synthesizedFM) return super.CTCR() ;
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
		return _fla + " (formula SAT, unsynthesized feature model)";
	}
	
	@Override 
	public ImplicationGraph<String> computeImplicationGraph() {
		return _fla.computeImplicationGraph(features().names());
	}
	
	@Override
	public ExclusionGraph<String> computeExclusionGraph(BDDBuilder<String> builder) {
		return _fla.computeExclusionGraph(features().names());

	}
	
	/** TODO: merge with getFormula() or move to FeatureModelVariable
	 * @return
	 */
	public SATFormula getSATFormula() {
		return _fla ; 
	}

	@Override
	public FeatureModelVariable slice(SliceMode sliceMode, Set<String> fts) {
		SATFormula slicedFormula = (SATFormula) 
				new SlicerSATFormula().sliceFormula(this, fts, sliceMode);
		        // new SlicerSATFormulaWithFeatureIDE()
				/*.runSliceFormulaSAT(this,
				_fla.getNode(), 
				_fla.getDomain(), // features().names() 
				fts,
				sliceMode); */
		return new FeatureModelVariableSATFormula("", slicedFormula) ; 
	}
	
	@Override
	public FeatureModelVariable _ksynthesis(KnowledgeSynthesis kn) {
		return new KSynthesisSAT(getSATFormula(), kn).build() ; 
	}
	
}
