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

import org.xtext.example.mydsl.fml.FMFormat;

import prefuse.util.io.SimpleFileFilter;
import fr.familiar.variable.FeatureModelVariable;

public class ImportAction extends ConcreteAction {
	private static final long serialVersionUID = 1L;
	
	private File f = null;
	
    public ImportAction(FMFormat format) {
    	initialize(format, "Import");
        this.putValue(AbstractAction.NAME, desc + " " + fileTypeLabel);
        this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/fileopen.gif"));
    }
    
    public void actionPerformed(ActionEvent e) {
    	FeatureModelVariable fmv = readFile();
    	if (null == fmv) return;
    	Tab2EnvVar.INSTANCE.createNewTab(fmv);
    }
    
    // Must override
    public FeatureModelVariable readSpecificFormat(File file) {
    	return null;
    }
    
    private FeatureModelVariable readFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogType(JFileChooser.OPEN_DIALOG);
        jfc.setDialogTitle(desc);
        jfc.setAcceptAllFileFilterUsed(false);
        jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        SimpleFileFilter ff;
        ff = new SimpleFileFilter(fileType, longName + " File " + fileTypeLabel);
        jfc.setFileFilter(ff);
        
        int retval = jfc.showOpenDialog(FamiliarEditor.INSTANCE);
        if (retval != JFileChooser.APPROVE_OPTION) return null;
        
        String msg = "Loaded file is not valid FM.";
        f = jfc.getSelectedFile();
        if (null == f) {
        	return null;
        }
        
        FeatureModelVariable fmv = readSpecificFormat(f);
		if (null == fmv) {
			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
		        "Error loading FM from " + f.getAbsolutePath() + "\nError Message: " + msg,
		        desc, JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return fmv;
    }
} // end of class ImportAction
