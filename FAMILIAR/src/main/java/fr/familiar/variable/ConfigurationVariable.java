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

import java.util.Set;

import org.xtext.example.mydsl.fml.AutoConfMode;
import org.xtext.example.mydsl.fml.OpSelection;

import fr.familiar.interpreter.FMLShell;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureModel;

/**
 * @author macher
 *
 */
public abstract class ConfigurationVariable extends VariableImpl {
	
	
	/**
	 * select, deselect or unselect a feature in a configuration
	 * @param ftName name of the feature 
	 * @param op selection/deselection or unselection 
	 * @return true if ftName does exist
	 */
	public abstract boolean applySelection(String ftName, OpSelection op) ; 
	
	
	public boolean applySelection(String ftName, String op) {
		
		String oper = op.trim();
		
		OpSelection s = null;
		if (oper.equals(OpSelection.DESELECT.toString()))
			s = OpSelection.DESELECT ; 
		else if (oper.equals(OpSelection.SELECT.toString())) {
			s = OpSelection.SELECT ;
		} else if (oper.equals(OpSelection.UNSELECT.toString()))
			s = OpSelection.UNSELECT ; 
		else {
			FMLShell.getInstance().printTODO();
		}

		
		return applySelection(ftName, s);
		
	}
	
	
	/**
	 * @return set of features currently selected 
	 */
	public abstract Set<String> getSelected() ; 
	
	
	/**
	 * @return set of features currently unselected
	 */
	public abstract Set<String> getUnselected() ; 
	
	/**
	 * @return set of features currently deselected
	 */
	public abstract Set<String> getDeselected() ; 
	
	
	/**
	 *
	 * @return true if the configuration is *complete*: choices have been made for all features (no unselected feature, the
	 *         set of features is either selected or deselected)
	 */
	public abstract boolean isComplete() ; 
	
	
	/**
	 * @return true if there is no inconsistency
	 */
	public abstract boolean isValid() ; 
	
	
	
	/**
	 * @return set of conflicts (if any)
	 */
	public abstract Set<String> getConflicts() ; 
	
	
	
	/**
	 * @return associated feature model
	 */
	public abstract FeatureModelVariable getFmv() ;


	/**
	 * @param fmv new feature model variable associated to the configuration 
	 */
	public abstract boolean changeFeatureModel(FeatureModelVariable fmv) ;
	
	
	
	/**
	 * @return an equivalent feature model with the same set of valid
	 *         configurations The feature model is "cleanup"
	 */
	public FeatureModel<String> asFM() {

		// first, clone the feature model associated to the configuration
		FeatureModel<String> fmConstrained = getFmv().getFm().clone();

		Set<String> deselects = this.getDeselected();
		for (String des : deselects) {
			Expression<String> expr = new Expression<String>(des);
			expr = expr.not();
			fmConstrained.addConstraint(expr);
		}

		Set<String> selects = this.getSelected();

		for (String sel : selects) {
			Expression<String> expr = new Expression<String>(sel);
			fmConstrained.addConstraint(expr);
		}

		FeatureModelVariable fmvCleanedUp = new FeatureModelVariable(null, fmConstrained);
		fmvCleanedUp.cleanup();

		return fmvCleanedUp.getFm();

	}
	
	/**
	 * @return associated feature model
	 */
	public abstract void gdisplay() ;
	
	
	public abstract void autoselect(AutoConfMode mode) ;



	
	
	
	
}
