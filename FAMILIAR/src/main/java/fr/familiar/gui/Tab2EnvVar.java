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

import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;

public class Tab2EnvVar {
	// A simple, fast, and thread-safe singleton implementation.
	public final static Tab2EnvVar INSTANCE = new Tab2EnvVar();
	
	private static boolean needToSyncTabWhenSwitchingTo = true;
	private static JTabbedPane tabbedPane = new JTabbedPane();
	
	public void createNewTab(String name, Component component) {
		// Register a change listener
		tabbedPane.addChangeListener(new ChangeListener() {
 		    // This method is called whenever the selected tab changes
 		    public void stateChanged(ChangeEvent evt) {
 		    	if (needToSyncTabWhenSwitchingTo) {
 		    		Variable v = FamiliarConsole.INSTANCE.getVariableByName(getCurrentFMVName());
 		    		if (v instanceof FeatureModelVariable) {
 		    			Translator.INSTANCE.changedFmv((FeatureModelVariable)v);
 		    		}
 		    	}
 		    }
     	});
 		int index = findFreeIndex();
		tabbedPane.addTab(name, null, component, name);
		tabbedPane.setTabComponentAt(index, new ButtonTabComponent(tabbedPane));
		tabbedPane.setSelectedIndex(index);
	}

	private Tab2EnvVar() {
	}
	
	public void switchToNewTab(String name) {
		// Try first to find a tab with the existing FMV name. If it exists, switch to that tab
		int index = tabbedPane.indexOfTab(name);
		if (index != -1) {
			tabbedPane.setSelectedIndex(index);
			return;
		}
		
		Variable v = FamiliarConsole.INSTANCE.getVariableByName(name);
		
		if (v instanceof FeatureModelVariable) {
			// No existing tab found, need to create a new tab for FM
			FamiliarEditor.INSTANCE.visualizeFM((FeatureModelVariable)v);
		} else if (v instanceof ConfigurationVariable) {
			createNewConfigurationTab((ConfigurationVariable)v, false);
		}
		
		// Make sure to set this tab to current (because it was created from different tab, and
		// updateDiplay would use that tab index).
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(name));
	}
	
	public void createNewTab(FeatureModelVariable fmv) {
		needToSyncTabWhenSwitchingTo = false;
		
		// Try first to find a tab with the existing FMV name. If it exists, close that tab
		int index = tabbedPane.indexOfTab(fmv.getIdentifier());
		if (index != -1) {
			tabbedPane.remove(index);
		}
		
		// Now, create new tab for FM
		FamiliarEditor.INSTANCE.visualizeFM(fmv);
		
		// Make sure to set this tab to current (because it was created from different tab, and
		// updateDiplay would use that tab index).
		tabbedPane.setSelectedIndex(tabbedPane.indexOfTab(fmv.getIdentifier()));
		needToSyncTabWhenSwitchingTo = true;
	}
	
	public void createNewConfigurationTab(ConfigurationVariable cv, boolean ifExistDelete) {
		int index = tabbedPane.indexOfTab(cv.getIdentifier());
		if (index != -1) {
			if (ifExistDelete) {
				tabbedPane.remove(index);
			} else {
				// If ConfigurationVariable exists, don't do anything
				return;
			}
		}
		
		Configurator frame = new Configurator(cv);
		needToSyncTabWhenSwitchingTo = false;
		Tab2EnvVar.INSTANCE.createNewTab(cv.getIdentifier(), frame.getComponent(0));
		needToSyncTabWhenSwitchingTo = true;
		
	}
	
	public JTabbedPane getTab() {
		return tabbedPane;
	}
	
	public String getCurrentFMVName() {
		String name = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
		Variable v = FamiliarConsole.INSTANCE.getVariableByName(name);
 		if (v instanceof FeatureModelVariable) {
 			return name;
 		}
 		// Current tab is not FeatureModelVariable
 		return null;
	}
	
	public void renameCurrentFMVName(String newName) {
		tabbedPane.setTitleAt(tabbedPane.getSelectedIndex(), newName);
		tabbedPane.setToolTipTextAt(tabbedPane.getSelectedIndex(), newName);
	}
	
	private int findFreeIndex() {
		return tabbedPane.getTabCount();
	}
	
	public int getSelectedIndex() {
		return tabbedPane.getSelectedIndex(); 
	}
	
} // end of class Tab2EnvVar
