/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)
 * project (http://familiar-project.github.com/).
 *
 * Copyright (C) 2011 - 2013
 *     University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *     Colorado State University, Computer Science Department
 *     
 * Author: Aleksandar Jaksic
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.familiar.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.familiar.variable.FeatureModelVariable;

public class NewConfiguration {
	public static final String title = "New Configuration";
	
	 public NewConfiguration() {
		 FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
		 if (null == fmv) {
 			return;
 		 }
 		 JTextField newConf = new JTextField();
 		 newConf.setText("config" + fmv.getIdentifier());
 		
 	     JPanel panel = new JPanel();
 	     panel.setLayout(new GridLayout(2, 1, 5, 5));
 	     panel.add(new JLabel("New Configuration Name:"));
 	     panel.add(newConf);
 	    
 	     int result = JOptionPane.showConfirmDialog(FamiliarEditor.INSTANCE, panel, 
 	    		 title, JOptionPane.OK_CANCEL_OPTION);
 	     if (result == JOptionPane.OK_OPTION) {
 	    	if (newConf.getText().isEmpty()) {
 	    		return;
 	    	}
 	    	FamiliarConsole.INSTANCE.createNewConfig(
 	    			RuleEnforcer.onlyDigitsAndLetters(newConf.getText()), 
 	    			fmv.getIdentifier());
 	     }
     }

} // End of class NewConfiguration
