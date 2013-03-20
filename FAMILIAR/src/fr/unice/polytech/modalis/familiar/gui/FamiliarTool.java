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
package fr.unice.polytech.modalis.familiar.gui;

import javax.swing.JFrame;

import prefuse.util.ui.UILib;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;

public class FamiliarTool {
	
    public final static String shortAppName = "FAMILIAR Tool";
	public final static String longAppName = "FAMILIAR (FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) Tool";
	
	public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
    		UILib.setPlatformLookAndFeel();
            buildAndLanchToolFrame();
    	}});
    }

    private static void buildAndLanchToolFrame() {
    	// Initialize FAMILIAR shell/interpreter
    	FamiliarConsole.INSTANCE.initializeFMLShell();
    	
        // Launch window
        final JFrame frame = new JFrame(shortAppName + " | Version " + FMLShell.FML_VERSION);
        frame.setJMenuBar(Menu.INSTANCE.createMenuBar());
        frame.setContentPane(FamiliarEditor.INSTANCE);
        frame.setIconImage(Menu.createImageIcon("images/binary-tree.gif").getImage());
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FamiliarConsole.INSTANCE.displayHeader();
    }

} // end of class FamiliarTool
