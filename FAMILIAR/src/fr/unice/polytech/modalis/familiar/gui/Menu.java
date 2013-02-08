/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning)
 * project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2012
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
package fr.unice.polytech.modalis.familiar.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import prefuse.data.Tree;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;

public class Menu {
	// A simple, fast, and thread-safe singleton implementation.
	public final static Menu INSTANCE = new Menu();
	
	private Menu() {
	}
	
	public JMenuBar createMenuBar() {
		// Set up menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        final String menuTitle = "New FAMILIAR FM";
        JMenuItem newMenuitem = new JMenuItem(menuTitle);
        newMenuitem.setMnemonic(KeyEvent.VK_N);
        fileMenu.add(newMenuitem);
        newMenuitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	String rootName = Converter.INSTANCE.generateRootName();
    			JTextField fmName = new JTextField();
    			fmName.setText(rootName);
    			JTextField rootFeature = new JTextField();
    			rootFeature.setText(rootName);
    		
    			final JComponent[] inputs = new JComponent[] {
                    new JLabel("New FM Name:"), fmName,
                    new JLabel("New root feature:"), rootFeature
    			};
    			JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE,
    				inputs, menuTitle, JOptionPane.PLAIN_MESSAGE);
    			
    			String root = RuleEnforcer.onlyDigitsAndLetters(rootFeature.getText());
    			String fmvname = RuleEnforcer.onlyDigitsAndLetters(fmName.getText());
    			if (root.isEmpty() || fmvname.isEmpty() ||
    					(null != FamiliarConsole.INSTANCE.getFMVariableByName(fmvname))) {
    				JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
    					"Error creating new FM: Names are not valid or FM Name already exist. The change has not been committed.",
    					menuTitle, JOptionPane.ERROR_MESSAGE);
    				return;
    			}
            	Tree t = Converter.INSTANCE.buildStartupDisplayFromPrefuseTree(root);
            	FamiliarEditor.INSTANCE.visualizeFM(Converter.INSTANCE.prefuseTree2Fmv(t, fmvname));
            }
        });
        
        fileMenu.addSeparator();
        fileMenu.add(new TreeMLAction.OpenTree());
        fileMenu.add(new TreeMLAction.SaveTree());
        fileMenu.add(new TreeMLAction.SaveAsTree());
        fileMenu.addSeparator();
        
        JMenu otherNotationsSubMenu = new JMenu("Import/Export Other FM Notations");
        otherNotationsSubMenu.add(new ImportSPLOTAction());
        otherNotationsSubMenu.add(new ExportSPLOTAction());
        otherNotationsSubMenu.addSeparator();
        otherNotationsSubMenu.add(new ImportFeatureIDEAction());
        otherNotationsSubMenu.add(new ExportFeatureIDEAction());
        otherNotationsSubMenu.addSeparator();
        otherNotationsSubMenu.add(new ImportS2T2Action());
        otherNotationsSubMenu.add(new ExportS2T2Action());
        otherNotationsSubMenu.addSeparator();
        otherNotationsSubMenu.add(new ImportTVLAction());
        otherNotationsSubMenu.add(new ExportTVLAction());
        fileMenu.add(otherNotationsSubMenu);
        fileMenu.addSeparator();
        
        JMenuItem exitMenuitem = new JMenuItem("Exit");
        fileMenu.add(exitMenuitem);
        exitMenuitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
        
        JMenu scriptMenu = new JMenu("Script");
        scriptMenu.add(new TreeMLAction.RunScriptAction());
        
        JMenu displayMenu = new JMenu("Display");
        final String switchTitle = "Display another FM";
        final JMenuItem switchMenuItem = new JMenuItem(switchTitle);
     
        displayMenu.add(switchMenuItem);
        switchMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] fms = FamiliarConsole.INSTANCE.allFmvToStringArray();
				if (null == fms) {
					JOptionPane.showMessageDialog(FamiliarEditor.INSTANCE, 
				      "There should be at least two FM loaded before you can switch display among them.",
				      switchTitle, JOptionPane.ERROR_MESSAGE);
					return;
				}
			    String loadFM = (String) JOptionPane.showInputDialog(null, "Choose FM to switch display to...",
			    	switchTitle, JOptionPane.QUESTION_MESSAGE, null, fms, fms[1]); // Initial choice
			    if (null != loadFM) {
		            Tab2EnvVar.INSTANCE.switchToNewTab(loadFM);
			    }
			}
		});
        
        
        final JCheckBoxMenuItem infoMenuItem = new JCheckBoxMenuItem("Displayed FM Info");
        // FM variable info text is shown by default on display
        infoMenuItem.setSelected(true);
       
        StatusBar.INSTANCE.setDisplayText(infoMenuItem.isSelected());
        displayMenu.add(infoMenuItem);
        infoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatusBar.INSTANCE.setDisplayText(infoMenuItem.isSelected());
			}
		});
        
        JMenu consoleMenu = new JMenu("Console");
        JMenuItem cconsoleMenuItem = new JMenuItem("Clear Console");
        cconsoleMenuItem.setIcon(createImageIcon("images/clear.gif"));
        consoleMenu.add(cconsoleMenuItem);
        cconsoleMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FamiliarConsole.INSTANCE.ClearConsole();
			}
		});
        JMenuItem cFMVarsMenuItem = new JMenuItem("Unload FMs");
        consoleMenu.add(cFMVarsMenuItem);
        cFMVarsMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FamiliarConsole.INSTANCE.clearFMVariables();
			}
		});
        consoleMenu.addSeparator();
        
        JMenuItem fmlMenuItem = new JMenuItem("Visualized FM -> FM Textual Syntax");
        consoleMenu.add(fmlMenuItem);
        fmlMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FamiliarConsole.INSTANCE.setMessage(
					Converter.INSTANCE.Tree2String(FamiliarEditor.INSTANCE.getTree()));
			}
		});
        
        consoleMenu.addSeparator();
        final JCheckBoxMenuItem verboseLMenuItem = new JCheckBoxMenuItem("Verbose Logging");
        verboseLMenuItem.setMnemonic(KeyEvent.VK_C);
        consoleMenu.add(verboseLMenuItem);
        verboseLMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FamiliarConsole.INSTANCE.setVerboseLogging(verboseLMenuItem.isSelected());
            }
        });
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final String helpMenuItemTitle = "FAMILIAR Editor Quick Help";
        final JMenuItem helpMenuItem = new JMenuItem(helpMenuItemTitle);
        helpMenuItem.setMnemonic(KeyEvent.VK_H);
        helpMenuItem.setIcon(createImageIcon("images/info.gif"));
        helpMenu.add(helpMenuItem);
        helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String htmlContent =
			        "<html><u>View Feature Model - Visual Controls</u><ul>" +
			        "<li>Zoom-To-Fit: Middle-click once to zoom the display to fit the whole FM." +
			        "<li>Pan: Left-click and drag the background to pan the FM view." +
			        "<li>Zoom: Right-click and drag the mouse up or down or use the scroll wheel to zoom the FM view." +
					
			        "</ul><br><u>Edit Feature Model - Popup Menu Controls</u><br>" +
					"<li>Right click on any node you want to add/rename/update. Popup menus are context sensitive, " + 
					"easy to use and enable intuitive interaction with FMs." +
					"<li>Only ASCII characters that contain upper, lower case letters and digits are allowed. Max allowed length is 32 chars." +
					
			        "</ul><br><u>Visual FM Tree Representation</u><br><br>" +
			        "<u>Groups</u><ul>" +
			        "<li>AND group: Includes all the mandatory subfeatures from each of its And-groups; Represented by a parent node linking " + 
			        " to its mandatory and optional children." +
			        "<li>XOR group: Includes exactly one subfeature from each of its Xor-groups; Represented by " +
			        "<FONT COLOR=\"#ffffff\"><B>WHITE UP-POINTING TRIANGLE &#x25B2</B></FONT>." +
			        "<li>OR group: Includes at least one of its subfeatures from each of its Or-groups; Represented by " +
			        "<FONT COLOR=\"#333333\"><B>BLACK UP-POINTING TRIANGLE &#x25B2</B></FONT>." +
			        "</ul><u>Features</u><ul>" +
			        "<li>MANDATORY feature: Represented by <FONT COLOR=\"#333333\"><B>BLACK CIRCLE &#9679</B></FONT>." +
			        "<li>OPTIONAL feature: Represented by <FONT COLOR=\"#ffffff\"><B>WHITE CIRCLE &#9676</B></FONT>." +
			        "</ul><u>Constraints</u><ul>" +
			        "<li>Listed as a group under the root; Represented by " +
			        "<FONT COLOR=\"#ffffff\"><B>WHITE SQUARE &#9723</B></FONT>." +
			        "</ul></html>"; 
				
				JOptionPane.showMessageDialog(helpMenuItem, new JLabel(htmlContent), helpMenuItemTitle,
					JOptionPane.PLAIN_MESSAGE, createImageIcon("images/info.gif"));
			}
		});
        
        final JMenuItem aboutMenuItem = new JMenuItem("About " + FamiliarEditor.shortAppName + "...");
        aboutMenuItem.setMnemonic(KeyEvent.VK_A);
        aboutMenuItem.setIcon(createImageIcon("images/info.gif"));
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(aboutMenuItem,
					FamiliarEditor.longAppName + "\n" +
					"Version " + FMLShell.FML_VERSION + "\n\n" +
					"Authors:\n\n" +
					"     * Mathieu Acher\n" +
					"   University of Rennes, France\n\n" +
					"     * Aleksandar Jaksic\n     * Robert France\n" + 
					"   Colorado State University, Computer Science Department, USA\n\n" +
					"      * Philippe Collet\n      * Philippe Lahire\n" +
					"   University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory, France\n\n" +
					"FAMILIAR Web Site: http://familiar-project.github.com/\n\n",
			    "About " + FamiliarEditor.shortAppName,
			    JOptionPane.INFORMATION_MESSAGE,
			    createImageIcon("images/info.gif"));
			}
		});
               
        final JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);
        menubar.add(scriptMenu);
        menubar.add(displayMenu);
        menubar.add(consoleMenu);
        menubar.add(helpMenu);
        return menubar;
	}
	
	// Returns an ImageIcon, or null if the path was invalid.
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FamiliarEditor.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
} // end of class Menu
