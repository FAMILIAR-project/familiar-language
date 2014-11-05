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

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.xtext.example.mydsl.fML.FMFormat;

import prefuse.util.io.SimpleFileFilter;
import fr.familiar.variable.FeatureModelVariable;

public class ExportAction extends ConcreteAction {
	private static final long serialVersionUID = 1L;
	
	protected FMFormat format = null;
	
    public ExportAction(FMFormat inFormat) {
    	format = inFormat;
    	initialize(format, "Export");
        this.putValue(AbstractAction.NAME, desc + " " + fileTypeLabel);
        this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/filesave.gif"));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        saveFile();
    }
    
    // Must override
    public boolean saveSpecificFormat(File file, FeatureModelVariable fmv) {
    	return false;
    }
    
    private void saveFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogType(JFileChooser.SAVE_DIALOG);
        jfc.setDialogTitle(desc);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        jfc.setFileFilter(new SimpleFileFilter(fileType, longName + " File " + fileTypeLabel));

        int retval = jfc.showSaveDialog(FamiliarEditor.INSTANCE);
        if (retval != JFileChooser.APPROVE_OPTION) {
        	return;
        }
        File f = jfc.getSelectedFile();
      	if(!f.getName().toLowerCase().endsWith(fileType)) {
      		f = new File(f.getAbsolutePath() + "." + fileType);
      	}
      	// Before saving the FM, add (or replace) it to the FAMILIAR environment, and validate it
      	FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
      	if (null == fmv) {
      		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
      	            "Error exporting FM to " + longName + " File " + f.getAbsolutePath() 
      	            + "\nError: No current FeatureModelVariable.",
      	            desc, JOptionPane.ERROR_MESSAGE);
      		return;
      	}
      	if (fmv.isValid()) {
      		if (saveSpecificFormat(f, fmv)) {
      			FamiliarConsole.INSTANCE.addOrReplaceFMVariable(fmv);
          		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
          			"FM " + fmv.getCompleteIdentifier() + " has been successfully saved  to " + 
          			f.getAbsolutePath(),
          	        desc, JOptionPane.INFORMATION_MESSAGE);
      			return;
      		}
      	}
      	JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
            "Error exporting FM to " + longName + " File " + f.getAbsolutePath() 
            + "\nError: FeatureModelVariable is not valid.",
            desc, JOptionPane.ERROR_MESSAGE);
    }
} // end of class ExportAction
