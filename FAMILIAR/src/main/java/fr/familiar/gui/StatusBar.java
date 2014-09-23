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

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

import prefuse.util.FontLib;
import prefuse.util.ui.JFastLabel;

public class StatusBar {
	// A simple, fast, and thread-safe singleton implementation.
	public final static StatusBar INSTANCE = new StatusBar();
	
	private Box statusbar = new Box(BoxLayout.X_AXIS);
	private JFastLabel sbTitle = new JFastLabel(loadedFM);
	private static final String loadedFM = "FM name: ";
	public static final String loadedFMVars = "Loaded FM(s): ";
	
	private StatusBar() {
	}
	
	public Box createStatusBar() {
		sbTitle.setPreferredSize(new Dimension(300, 20));
		sbTitle.setVerticalAlignment(SwingConstants.TOP);
		sbTitle.setBorder(BorderFactory.createEmptyBorder(0, 5, 15, 15));
		sbTitle.setFont(FontLib.getFont("Tahoma", Font.PLAIN, 14));
      
        statusbar.add(Box.createHorizontalStrut(10));
        statusbar.add(sbTitle);
        return statusbar;
	}
	
	public void setLoadedFMVStatusBar(String text) {
		sbTitle.setText(text);
	}
} // end of class StatusBar