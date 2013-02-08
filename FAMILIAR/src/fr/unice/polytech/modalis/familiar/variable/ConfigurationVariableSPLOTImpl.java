/**
 * 
 */
package fr.unice.polytech.modalis.familiar.variable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.xtext.example.mydsl.fML.AutoConfMode;
import org.xtext.example.mydsl.fML.OpSelection;

import splar.core.fm.FeatureGroup;
import splar.core.fm.FeatureModel;
import splar.core.fm.FeatureTreeNode;
import splar.core.fm.configuration.ConfigurationEngine;
import splar.core.fm.configuration.ConfigurationEngineException;
import splar.core.fm.configuration.ConfigurationStep;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.interpreter.NSFactory;
import fr.unice.polytech.modalis.familiar.operations.MySATConfigurationEngine;
import fr.unice.polytech.modalis.familiar.parser.NameSpace;

/**
 * @author macher
 *
 */
public class ConfigurationVariableSPLOTImpl extends ConfigurationVariable {
	
	
	private static Logger _LOGGER = Logger.getLogger(ConfigurationVariableSPLOTImpl.class);

	
	private FeatureModelVariable _fmv ; 
	
	private ConfigurationEngine _confEngine ;
	
	private boolean _isConflicting = false ;

	private List<FeatureTreeNode> _conflicts = new ArrayList<FeatureTreeNode>() ;
	
	private Set<String> _selected = new HashSet<String>() ; 
	private Set<String> _deselected = new HashSet<String>() ; 
	private Set<String> _unselected = new HashSet<String>() ; 

	public ConfigurationVariableSPLOTImpl(FeatureModelVariable fmv, String name, NameSpace ns) {
		this (mkConfEngine(fmv), fmv, name, ns) ; 
	}
	
	

	public ConfigurationVariableSPLOTImpl(FeatureModelVariable fmv, String name) {
		this (fmv, name, NSFactory.mkEmpty());
	}



	private ConfigurationVariableSPLOTImpl(ConfigurationEngine confEngine, FeatureModelVariable fmv, String name, NameSpace ns) {
		_fmv = fmv ; 
		_confEngine = confEngine ; 
		this.name = name;
		this.ns = ns;
		this.vid = new VariableIdentifier(name, ns);
		
		if (_confEngine != null) {
			_updateDecisions(_fmv.root().name(), OpSelection.SELECT) ;
			List<ConfigurationStep> steps = _confEngine.getSteps() ;
			for (ConfigurationStep cStep : steps) {
				_updateDecisionForAStep(cStep);				
			}
		}
		
		
	}
	
	private ConfigurationVariableSPLOTImpl(ConfigurationEngine confEngine, FeatureModelVariable fmv, String name) {
		this (confEngine, fmv, name, NSFactory.mkEmpty()) ; 
	}

	private ConfigurationVariableSPLOTImpl(FeatureModelVariable fmv, String name, Set<String> selected, Set<String> deselected, Set<String> unselected) {
		this(fmv,name);
		this._selected.addAll(selected);
		this._deselected.addAll(deselected);
		this._unselected.addAll(unselected);
	}

	private static ConfigurationEngine mkConfEngine(FeatureModelVariable fmv) {
		try {
			FeatureModel fmSplot = fmv.toSPLOT() ; 
			ConfigurationEngine confEngine = new MySATConfigurationEngine(fmSplot); // new BDDConfigurationEngine(fmv.toSPLOT());
			confEngine.reset() ; // needed :(
			return confEngine ; 
		} catch (ConfigurationEngineException e) {
			FMLShell.getInstance().printError("Unable to create a configuration " + e.getLocalizedMessage());
			return null ; 
		}	
		
	}

	@Override
	public boolean applySelection(String ftName, OpSelection op) {
		
		int decision = _mkDecision(op);

				
		String ftID = null ; 
		FeatureTreeNode cftNode = null ; 
		Collection<FeatureTreeNode> ftNodes = _confEngine.getModel().getNodes();
		for (FeatureTreeNode ftNode : ftNodes) {
			if (!(ftNode instanceof FeatureGroup))
				if (ftNode.getName().equals(ftName)) {
					ftID = ftNode.getID() ; 
					cftNode = ftNode ; 
					break ; 
				}
		}
		if (ftID == null) 
			return false ; 
			
		
		try {
			
			if (op.equals(OpSelection.SELECT) && _selected.contains(ftName))
				return true ;
			if (op.equals(OpSelection.DESELECT) && _deselected.contains(ftName))
				return true ;
			if (op.equals(OpSelection.UNSELECT) && _unselected.contains(ftName))
				return true ;
						
			if (cftNode.isInstantiated() && cftNode.getValue() == decision) {
				_confEngine.toggleDecision(ftID);
				_updateDecisions(cftNode.getName(), op);
			}
					
			
			ConfigurationStep cStep = _confEngine.configure(ftID, decision);
			
			_updateDecisionForAStep(cStep);
			_updateDecisions(cftNode.getName(), op); 
			
						
			try {
				List<FeatureTreeNode> conflicts = _confEngine.detectConflicts(ftID);
				_LOGGER.debug("conflicts (if any): " + conflicts) ; 
				if (conflicts.size() > 0) {				
					_isConflicting = true ; 
					_conflicts  = conflicts ; 
					return false ; // we return false when conflicts occur
				}
				else {
					_conflicts = new ArrayList<FeatureTreeNode>();
					_isConflicting = false ; 
				}
			}
			catch (ConfigurationEngineException e2) {
			}
			
			return true ; 
		} catch (ConfigurationEngineException e) {	
			return false ; 
		} 
		 
		
	}
	
public boolean applyBasicDecisions(String ftName, OpSelection op) {
		
		int decision = _mkDecision(op);

				
		String ftID = null ; 
		FeatureTreeNode cftNode = null ; 
		Collection<FeatureTreeNode> ftNodes = _confEngine.getModel().getNodes();
		for (FeatureTreeNode ftNode : ftNodes) {
			if (!(ftNode instanceof FeatureGroup))
				if (ftNode.getName().equals(ftName)) {
					ftID = ftNode.getID() ; 
					cftNode = ftNode ; 
					break ; 
				}
		}
		if (ftID == null) 
			return false ; 
			
		
		_updateDecisions(ftName, op) ;
		
		return true ;
		
		/*
		try {
			
			if (cftNode.isInstantiated() && cftNode.getValue() == decision) {
				_confEngine.toggleDecision(ftID);
				_updateDecisions(cftNode.getName(), op);
			}
					
			
			ConfigurationStep cStep = _confEngine.configure(ftID, decision);
			
			_updateDecisionForAStep(cStep);
			_updateDecisions(cftNode.getName(), op); 
			
		} catch (ConfigurationEngineException e) {	
			return false ; 
			
		}
		return true ;
			/*			
			try {
				List<FeatureTreeNode> conflicts = _confEngine.detectConflicts(ftID);
				_LOGGER.debug("conflicts (if any): " + conflicts) ; 
				if (conflicts.size() > 0) {				
					_isConflicting = true ; 
					_conflicts  = conflicts ; 
					return false ; // we return false when conflicts occur
				}
				else {
					_conflicts = new ArrayList<FeatureTreeNode>();
					_isConflicting = false ; 
				}
			}
			catch (ConfigurationEngineException e2) {
			}
			
			return true ; 
	
		} */
		 
		
	}
	

	private void _updateDecisionForAStep(ConfigurationStep cStep) {
		Set<FeatureTreeNode> inducedDecisions = cStep.getPropagations() ;
		for (FeatureTreeNode ftDecision : inducedDecisions) {
			if (ftDecision.getValue() == FeatureTreeNode.SELECTED) 
				_updateDecisions(ftDecision.getName(), OpSelection.SELECT);
			else if (ftDecision.getValue() == FeatureTreeNode.DESELECTED) 
				_updateDecisions(ftDecision.getName(), OpSelection.DESELECT);
		}		
	}



	private void _updateDecisions(String name, OpSelection op) {
		
		if (_selected.contains(name))
			_selected.remove(name);
		if (_deselected.contains(name))
			_deselected.remove(name);
		if (_unselected.contains(name))
			_unselected.remove(name);
		
		switch (op) {
		case SELECT:
			_selected.add(name);
			break;
		case DESELECT:
			_deselected.add(name);
			break;
		case UNSELECT:
			_unselected.add(name);
			break;
		default:
			break;
		}
		
	}



	private int _mkDecision(OpSelection op) {
		switch (op) {
		case SELECT:
			return FeatureTreeNode.SELECTED ; 
		case DESELECT:
			return FeatureTreeNode.DESELECTED ; 
		case UNSELECT:
			return FeatureTreeNode.UNKNOWN ;
		}
		return FeatureTreeNode.UNKNOWN ; 
	}





	@Override
	public Set<String> getSelected() {
		return _selected ; 
	}

	@Override
	public Set<String> getUnselected() {
		return _unselected ; 
	}

	@Override
	public Set<String> getDeselected() {
		return _deselected ;
	}
	



	@Override
	public boolean isComplete() {
		return _confEngine.isDone() ; 
	}

	@Override
	public RType getRType() {
		return RType.CONFIGURATION ; 
	}

	@Override
	public Variable copy() {
		FMLShell.getInstance().printTODO("Copying configuration variable ");
		return new ConfigurationVariableSPLOTImpl(_confEngine, _fmv, name) ; // TODO
		
	}

	@Override
	public void setValue(Variable vari) {
		FMLShell.getInstance().printTODO("Setting value in configuration variable ");
		
	}

	@Override
	public String getSpecificValue() {
		Set<String> sel = getSelected();
		Set<String> desel = getDeselected();
		return "selected: " + sel.toString() + " \t deselected: "
				+ desel.toString() ; 
	}



	@Override
	public boolean isValid() {
		if (_confEngine == null)
			return false ; 
		if (_confEngine.getSteps().size() == 0) { // no selection/deselection
			return _fmv.isValid() ; 
		}
		/*
		Set<FeatureTreeNode> choices = collectDecisions();
		for (FeatureTreeNode choice : choices) {
			try {
				if (_confEngine.detectConflicts(choice.getID()).size() > 0)
					return false ;
			} catch (ConfigurationEngineException e) { 
				// no conflicts
			}
		}*/
		return !_isConflicting ; 
	}



	private Set<FeatureTreeNode> collectDecisions() {
		Set<FeatureTreeNode> r = new HashSet<FeatureTreeNode>() ;
		List<ConfigurationStep> allSteps = _confEngine.getSteps() ;
		for (ConfigurationStep configurationStep : allSteps) {
			r.addAll(configurationStep.getDecisions()); 
		}
		
		return r;
	}



	@Override
	public Set<String> getConflicts() {
		/*
		Set<String> r = new HashSet<String>() ;
		Set<FeatureTreeNode> choices = collectDecisions();
		for (FeatureTreeNode choice : choices) {
			try {
				List<FeatureTreeNode> cConflicts = _confEngine.detectConflicts(choice.getID()) ; 
				for (FeatureTreeNode conflict : cConflicts) {
					r.add(conflict.getName());
				}
			} catch (ConfigurationEngineException e) { 
				// no conflicts
				e.printStackTrace();
			}
		}
		return r ; 
		*/
		Set<String> r = new HashSet<String>() ;
		for (FeatureTreeNode ftNode : _conflicts) {
			r.add(ftNode.getName());
		}
		return r ; 
	}



	@Override
	public FeatureModelVariable getFmv() {
		return _fmv ; 
	}



	@Override
	public boolean changeFeatureModel(FeatureModelVariable fmv) {
		// first of all
		_fmv = fmv ; 

		
		// encapsulate for reasoning about
		ConfigurationVariable cv = new ConfigurationVariableSPLOTImpl(fmv, null);

		for (String selected : getSelected())
			if (!cv.applySelection(selected, OpSelection.SELECT)) {
				_LOGGER.debug(
						"Unable to apply the selection (FM has changed!) with feature: "
								+ selected);
				// e.g., the feature does not exist anymore
			}

		for (String deselected : getDeselected())
			if (!cv.applySelection(deselected, OpSelection.DESELECT)) {
				_LOGGER.debug(
						"Unable to apply the deselection (FM has changed!) with feature: "
								+ deselected);
				// e.g., the feature does not exist anymore
			}

		_confEngine = ((ConfigurationVariableSPLOTImpl)cv).getConfigurationEngine();

		return true;
		
	}



	private ConfigurationEngine getConfigurationEngine() {
		return _confEngine ; 
	}



	@Override
	public void gdisplay() {
		
	}



	@Override
	public void autoselect(AutoConfMode mode) {
		// FIXME
		try {
			_confEngine.autoComplete(true);
		} catch (ConfigurationEngineException e) {
			FMLShell.getInstance().printError("Unable to auto select: " + e.getMessage());
		}
		
	}

}
