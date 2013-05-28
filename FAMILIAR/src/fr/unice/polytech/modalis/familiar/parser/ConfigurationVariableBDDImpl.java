/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.AutoConfMode;
import org.xtext.example.mydsl.fML.OpSelection;

import com.google.common.collect.Sets;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.operations.FormulaAnalyzer;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable;
import fr.unice.polytech.modalis.familiar.variable.ConfigurationVariableSPLOTImpl;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.Variable;
import fr.unice.polytech.modalis.familiar.variable.VariableIdentifier;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;
import gsd.synthesis.ValidDomains;

/**
 * @author macher1
 *
 */
public class ConfigurationVariableBDDImpl extends ConfigurationVariable {
	
	private static Logger _LOGGER = Logger.getLogger(ConfigurationVariableBDDImpl.class);

	private FeatureModelVariable _fmv ; 
	
	private Formula<String> _fla ; 
	
	private BDDBuilder<String> _builder; 

	private Set<String> _selected = new HashSet<String>() ; 
	private Set<String> _deselected = new HashSet<String>() ; 
	private Set<String> _unselected = new HashSet<String>() ;

	private Set<String> _inferredTrues = new HashSet<String>() ;  

	private Set<String> _inferredFalses = new HashSet<String>() ; 

	private boolean _isValid = true ;

	
	
	public ConfigurationVariableBDDImpl(FeatureModelVariable fmv, String assigner) {
		_fmv = fmv ; 
		_fla = _fmv.getFormula() ;
		_builder = _fmv.getBuilder() ; 
		
		this.name = assigner;
		this.ns = NSFactory.mkEmpty() ;
		this.vid = new VariableIdentifier(name, ns);
		
		// FIXME auto-propagation by default
		_updateWithValidDomains(_fla);
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#applySelection(java.lang.String, org.xtext.example.mydsl.fML.OpSelection)
	 */
	@Override
	public boolean applySelection(String ftName, OpSelection op) {
		
		if (op.equals(OpSelection.SELECT) && _selected.contains(ftName))
			return true ;
		if (op.equals(OpSelection.DESELECT) && _deselected.contains(ftName))
			return true ;
		if (op.equals(OpSelection.UNSELECT) && _unselected.contains(ftName))
			return true ;
		
		if (!_builder.contains(ftName)) {
			_LOGGER.warn("Unable to " + op + " the feature " + ftName + " since not in the builder") ; 
			return false ; 
		}
		
		
	
		
		if (op.equals(OpSelection.SELECT)) {
			_selected.add(ftName);
				
			
		}
		
		else if (op.equals(OpSelection.DESELECT)) {
			_deselected.add(ftName);
			
		}
		
	    else if (op.equals(OpSelection.UNSELECT)) {
	    	_unselected.add(ftName);
		}
	    else {
	    	FMLShell.getInstance().printError("Unknown " + op + " when configuring");
	    	return false ; 
	    }
		
		
		Formula<String> cFla = _fla.id() ; 
		
		/*
		 * Applying user decisions
		 */
		
		for (String selected : _selected) {
			Set<String> domain = new HashSet<String>();
			domain.add(selected);
			BDD bddFt = _builder.get(selected) ;
			cFla.andWith(new Formula<String>(bddFt, domain, _builder));
		}
		for (String deselected : _deselected) {
			Set<String> domain = new HashSet<String>();
			domain.add(deselected);
			BDD bddFt = _builder.get(deselected) ;
			cFla.andWith(new Formula<String>(bddFt, domain, _builder));
		}
		
		_updateWithValidDomains (cFla);
		return true ; 
				
	}

	private void _updateWithValidDomains(Formula<String> cFla) {
		
		FormulaAnalyzer<String> flaAnalyzer = new FormulaAnalyzer<String>(cFla, _builder) ; 
		_isValid  = !cFla.isZero() ; 
		
		_inferredTrues = flaAnalyzer.computeCoreFeatures() ; 
		_inferredFalses = flaAnalyzer.computeDeadFeatures() ; 
		
		//ValidDomains vd = new ValidDomains(cFla.getBDD());
		
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#getSelected()
	 */
	@Override
	public Set<String> getSelected() {
		return Sets.union(_selected, _inferredTrues);
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#getUnselected()
	 */
	@Override
	public Set<String> getUnselected() {
		return Sets.difference(_fmv.features().names(), Sets.union(getSelected(), getDeselected()));
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#getDeselected()
	 */
	@Override
	public Set<String> getDeselected() {
		return Sets.union(_deselected, _inferredFalses);
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#isComplete()
	 */
	@Override
	public boolean isComplete() {
		return getUnselected().isEmpty() ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#isValid()
	 */
	@Override
	public boolean isValid() {
		return _isValid ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#getConflicts()
	 */
	@Override
	public Set<String> getConflicts() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#getFmv()
	 */
	@Override
	public FeatureModelVariable getFmv() {
		return _fmv ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#changeFeatureModel(fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable)
	 */
	@Override
	public boolean changeFeatureModel(FeatureModelVariable fmv) {
		FMLShell.getInstance().printTODO("Changing feature model in the configuration");
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#gdisplay()
	 */
	@Override
	public void gdisplay() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.ConfigurationVariable#autoselect(org.xtext.example.mydsl.fML.AutoConfMode)
	 */
	@Override
	public void autoselect(AutoConfMode mode) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableImpl#getRType()
	 */
	@Override
	public RType getRType() {
		return RType.CONFIGURATION ; 
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableImpl#copy()
	 */
	@Override
	public Variable copy() {
		return new ConfigurationVariableBDDImpl((FeatureModelVariable) _fmv.copy(), name) ;
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableImpl#setValue(fr.unice.polytech.modalis.familiar.variable.Variable)
	 */
	@Override
	public void setValue(Variable vari) {
		FMLShell.getInstance().printTODO("Setting value in configuration variable ");
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.variable.VariableImpl#getSpecificValue()
	 */
	@Override
	public String getSpecificValue() {
		Set<String> sel = getSelected();
		Set<String> desel = getDeselected();
		return "selected: " + sel.toString() + " \t deselected: "
				+ desel.toString() ; 
	}

}
