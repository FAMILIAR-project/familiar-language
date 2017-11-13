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

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import prefuse.util.ui.UILib;
import fr.familiar.interpreter.FMLShell;

public class FamiliarTool {
	
    public final static String shortAppName = "FAMILIAR Tool";
	public final static String longAppName = "FAMILIAR (FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) Tool";
	
	public static void main(String[] args) {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() { public void run() {
    		UILib.setPlatformLookAndFeel();
    		try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
