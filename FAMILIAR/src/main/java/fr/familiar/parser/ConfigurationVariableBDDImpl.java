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

package fr.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fml.AutoConfMode;
import org.xtext.example.mydsl.fml.OpSelection;

import com.google.common.collect.Sets;

import fr.familiar.gui.Tab2EnvVar;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.interpreter.NSFactory;
import fr.familiar.operations.FormulaAnalyzer;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.Variable;
import fr.familiar.variable.VariableIdentifier;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

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
				
		if (!_builder.contains(ftName)) {
			_LOGGER.warn("Unable to " + op + " the feature " + ftName + " since not in the builder") ; 
			return false ; 
		}
		
				
		if (op.equals(OpSelection.SELECT)) {
			_selected.add(ftName);
			_deselected.remove(ftName);
			
		}
		
		else if (op.equals(OpSelection.DESELECT)) {
			_deselected.add(ftName);
			_selected.remove(ftName);
		}
		
	    else if (op.equals(OpSelection.UNSELECT)) {
	    	_deselected.remove(ftName);
	    	_selected.remove(ftName);
		}
	    else {
	    	FMLShell.getInstance().printError("Unknown " + op + " when configuring");
	    	return false ; 
	    }
		
		
		Formula<String> cFla = _fla.id() ; 
		
		
		
		/*
		 *  
		 * Conflicting decisions may arise
		 * say fm1 = FM (A : (B|C) ; )
		 * you can select B first
		 * then decide to change your mind and select C
		 * automatic mode for resolving conflicts
		 * the basic strategy here is to find conflicting decisions that have been previously made by the user, 
		 * being as a deselection or a selection    
		 * 
		 */
		
		Formula<String> conflictFla = _fla.id() ;
		Formula<String> flaFt = mkFormula (ftName);	
		if (op == OpSelection.DESELECT) {
			flaFt = new Formula<String>(flaFt.getBDD().not(), flaFt.getDomain(), _builder);
		}
		
		conflictFla.andWith(flaFt); 
		
		// inspect previous selected decisions
		Set<String> manualSelectedDecisionsToRemove = new HashSet<String>() ; 
		for (String selected : _selected) {
			
			
			
			Set<String> domain = new HashSet<String>();
			BDD bddFt = _builder.get(selected) ;
			conflictFla.andWith(new Formula<String>(bddFt, domain, _builder));
			// valid
			if (conflictFla.isZero()) {
				manualSelectedDecisionsToRemove.add(selected);
			}
			//conflictFla.free() ;
		}		
		_selected.removeAll(manualSelectedDecisionsToRemove);
		
		
		// inspect previous deselected decisions
		Set<String> manualDeselectedDecisionsToRemove = new HashSet<String>() ; 
		for (String deselected : _deselected) {
						
			
			Set<String> domain = new HashSet<String>();
			BDD bddFt = _builder.get(deselected).not() ;
			conflictFla.andWith(new Formula<String>(bddFt, domain, _builder));
			// valid
			if (conflictFla.isZero()) {
				manualDeselectedDecisionsToRemove.add(deselected);
			}
			//conflictFla.free() ;
		}		
		_deselected.removeAll(manualDeselectedDecisionsToRemove);
		conflictFla.free() ;
		
		
		
		
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
			BDD bddFt = _builder.get(deselected).not() ;
			cFla.andWith(new Formula<String>(bddFt, domain, _builder));
		}
		
		_updateWithValidDomains (cFla);
		return true ; 
				
	}

	private Formula<String> mkFormula(String ftName) {
		Set<String> domain = new HashSet<String>();
		BDD bddFt = _builder.get(ftName) ;
		return new Formula<String>(bddFt, domain, _builder);
	}

	/**
	 * Not efficient at all
	 * @param cFla
	 */
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
		
		// TODO hack we should refactor out
		if (!FMLShell.getInstance().isEclipseBased()) {
			Tab2EnvVar.INSTANCE.createNewConfigurationTab(this, true);			
		}
		
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
