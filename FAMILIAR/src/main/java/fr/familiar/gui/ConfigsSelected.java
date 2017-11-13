/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.gui;

import org.xtext.example.mydsl.fml.OpSelection;

import fr.familiar.variable.ConfigurationVariable;

public class ConfigsSelected {
	// A simple, fast, and thread-safe singleton implementation.
	public final static ConfigsSelected INSTANCE = new ConfigsSelected();
	

	private ConfigsSelected() {
	}
	
	public boolean isSelected(String cvName, String featureName) {
		ConfigurationVariable cv = (ConfigurationVariable) FamiliarConsole.INSTANCE.getVariableByName(cvName);
		return cv.getSelected().contains(cvName);
	}
	
	public void applySelection(String cvName, String featureName, OpSelection op) {
		ConfigurationVariable cv = (ConfigurationVariable) FamiliarConsole.INSTANCE.getVariableByName(cvName);
		cv.applySelection(featureName, op) ; 			
	}

	public boolean isDeselected(String cvName, String featureName) {
		ConfigurationVariable cv = (ConfigurationVariable) FamiliarConsole.INSTANCE.getVariableByName(cvName);
		return cv.getDeselected().contains(cvName);
	}
	
} // end of class ConfigsSelected
