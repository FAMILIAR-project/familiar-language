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
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import prefuse.data.Tree;
import prefuse.data.io.TreeMLReader;
import prefuse.data.io.TreeMLWriter;
import prefuse.util.io.SimpleFileFilter;
import fr.familiar.variable.FeatureModelVariable;

public class TreeMLAction {
	
	private static final String treemlFileType = "treeml";
	private static final String treemlFileTypeLabel = "(*." + treemlFileType + ")";
	
	private static HashMap<String, File> mapLastSaved = new HashMap<String, File>();

	public static int gID = 0;
	
	// Called each time after 'Open FAMILIAR FM' or 'Save FAMILIAR FM As...' were successfully used
	private static void setSaveAsFile(String fmvName, File f) {
		mapLastSaved.put(fmvName, f);
	}
	
    public static class OpenTree extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public static final String desc = "Open FAMILIAR FM";
		private File file = null;

        public OpenTree() {
            this.putValue(AbstractAction.NAME, desc + " " + treemlFileTypeLabel);
            this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl O"));
            this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/fileopen.gif"));
        }
        public void actionPerformed(ActionEvent e) {
        	Tree t = readXMLTree();
        	if (null == t) {
        		return;
        	}
        	String fmvName = file.getName().replace("."+ treemlFileType, "");
        	FeatureModelVariable fmv = Converter.INSTANCE.prefuseTree2Fmv(t, fmvName);
        	Tab2EnvVar.INSTANCE.createNewTab(fmv);
        	setSaveAsFile(fmv.getIdentifier(), file);
        }
        
        private Tree readXMLTree() {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogType(JFileChooser.OPEN_DIALOG);
            jfc.setDialogTitle(desc);
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            
            SimpleFileFilter ff;
            ff = new SimpleFileFilter(treemlFileType, "TreeML File " + treemlFileTypeLabel);
            jfc.setFileFilter(ff);
            
            int retval = jfc.showOpenDialog(FamiliarEditor.INSTANCE);
            if (retval != JFileChooser.APPROVE_OPTION) return null;
            
            String msg = "Loaded tree is not valid FM.";
            file = jfc.getSelectedFile();
            if (null == file) {
            	return null;
            }
            
            Tree t = null;
            try {
                t = (Tree)new TreeMLReader().readGraph(file);
            } catch (Exception e) {
            	JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
            		"Error loading FM from " + file.getAbsolutePath() + "\nError Message: " + msg,
                    desc, JOptionPane.ERROR_MESSAGE);
            }
            return t;
        }
    }
    
    public static class SaveAsTree extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public static final String desc = "Save FAMILIAR FM As...";

        public SaveAsTree() {
            this.putValue(AbstractAction.NAME, desc + " " + treemlFileTypeLabel);
            this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/filesave.gif"));
        }
        public void actionPerformed(ActionEvent e) {
            saveXMLTree();
        }
        
        private void saveXMLTree() {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogType(JFileChooser.SAVE_DIALOG);
            jfc.setDialogTitle(desc);
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            jfc.setFileFilter(new SimpleFileFilter(treemlFileType, "TreeML File " + treemlFileTypeLabel));
           
            int retval = jfc.showSaveDialog(FamiliarEditor.INSTANCE);
            if (retval != JFileChooser.APPROVE_OPTION) {
            	return;
            }
            File f = jfc.getSelectedFile();
          	if(!f.getName().toLowerCase().endsWith(treemlFileType)) {
          		f = new File(f.getAbsolutePath() + "." + treemlFileType);
          	}
          
            try {
            	// Before saving the FM, add (or replace) it to the FAMILIAR environment, and validate it
            	FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
            	if (null == fmv) {
              		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
              	            "Error saving FM as " + f.getAbsolutePath() + "\nError: No current FeatureModelVariable.",
              	            desc, JOptionPane.ERROR_MESSAGE);
              		return;
              	}
            	
              	if (fmv.isValid()) {
              		Tree t = Converter.INSTANCE.fmv2PrefuseTree(fmv);
              		new TreeMLWriter().writeGraph(t, f);
              		FamiliarConsole.INSTANCE.addOrReplaceFMVariable(fmv);
              		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                      	"FM " + fmv.getCompleteIdentifier() + " has been successfully saved  to " + 
                      	f.getAbsolutePath(),
                      	desc, JOptionPane.INFORMATION_MESSAGE);
              		setSaveAsFile(fmv.getIdentifier(), f);
              	} else {
              		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                    	"Error saving FM as " + f.getAbsolutePath() + "\nError: FeatureModelVariable is not valid.",
                    	desc, JOptionPane.ERROR_MESSAGE);
              	}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
            		"Error saving FM as " + f.getAbsolutePath() + "\nError: " + e.getMessage(),
            		desc, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static class SaveTree extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public static final String desc = "Save FAMILIAR FM";

        public SaveTree() {
            this.putValue(AbstractAction.NAME, desc + " " + treemlFileTypeLabel);
            this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl S"));
            this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/filesave.gif"));
        }
        public void actionPerformed(ActionEvent e) {
        	String fmvName = Tab2EnvVar.INSTANCE.getCurrentFMVName();
        	if (null == fmvName) {
        		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                    "Error: File not saved. No current FeatureModelVariable.",
                    desc, JOptionPane.ERROR_MESSAGE);
        		return;
        	} 
        	
        	if (null != mapLastSaved.get(fmvName)) {
        		saveXMLTree();
        	} else {
          		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                	"Error: File not saved. 'Open FAMILIAR FM' or 'Save FAMILIAR FM As...' not previously used.",
                	desc, JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private void saveXMLTree() {
            try {
            	// Before saving the FM, add (or replace) it to the FAMILIAR environment, and validate it
            	FeatureModelVariable fmv = FamiliarConsole.INSTANCE.getLoadedFMV();
              	if (null != fmv && fmv.isValid()) {
              		Tree t = Converter.INSTANCE.fmv2PrefuseTree(fmv);
              		new TreeMLWriter().writeGraph(t, 
              				mapLastSaved.get(fmv.getIdentifier()));
              		FamiliarConsole.INSTANCE.addOrReplaceFMVariable(fmv);
              		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                  		"FM " + fmv.getCompleteIdentifier() + " has been successfully saved  to " + 
                  				mapLastSaved.get(fmv.getIdentifier()).getAbsolutePath(),
                  	    desc, JOptionPane.INFORMATION_MESSAGE);
              	} else {
              		JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
                    	"Error saving " + mapLastSaved.get(fmv.getIdentifier()).getAbsolutePath() + 
                    		"\nError: FeatureModelVariable is not valid.",
                    	desc, JOptionPane.ERROR_MESSAGE);
              	}
            } catch (Exception e) {
                JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
            		"Error saving " + mapLastSaved.get(mapLastSaved.get(Tab2EnvVar.INSTANCE.getCurrentFMVName())).getAbsolutePath() 
            			+ "\nError: " + e.getMessage(),
            		desc, JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    public static class RunScriptAction extends AbstractAction {
		private static final long serialVersionUID = 1L;
		public static final String desc = "Run FAMILIAR Script";
		public static final String fileType = "(*.fml)";

        public RunScriptAction() {
            this.putValue(AbstractAction.NAME, desc + " " + fileType + "...");
            this.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke("ctrl R"));
            this.putValue(AbstractAction.SMALL_ICON, Menu.createImageIcon("images/fileopen.gif"));
        }
        
        public void actionPerformed(ActionEvent e) {
        	File f = getScriptPath();
            if (null == f) return;
            FamiliarConsole.INSTANCE.runScript(f);
        }
        
        private File getScriptPath() {
            JFileChooser jfc = new JFileChooser();
            jfc.setDialogType(JFileChooser.OPEN_DIALOG);
            jfc.setDialogTitle(desc);
            jfc.setAcceptAllFileFilterUsed(false);
            jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
            jfc.setFileFilter(new SimpleFileFilter("fml", "Familiar File " + fileType));
            
            int retval = jfc.showOpenDialog(FamiliarEditor.INSTANCE);
            if (retval != JFileChooser.APPROVE_OPTION) return null;
            return jfc.getSelectedFile();
        }
    }
} // end of class TreeMLAction
