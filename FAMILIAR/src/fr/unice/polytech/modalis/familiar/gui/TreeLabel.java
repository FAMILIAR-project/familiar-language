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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class TreeLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	boolean isSelected;
    boolean hasFocus;

    public TreeLabel() {
    }

    public void setBackground(Color color) {
    	if (color instanceof ColorUIResource) {
    		color = null;
    	}
      	super.setBackground(color);
    }

    public void paint(Graphics g) {
    	String str;
    	if ((str = getText()) != null) {
    		if (0 < str.length()) {
    			if (isSelected) {
    				g.setColor(UIManager.getColor("Tree.selectionBackground"));
    			} else {
    				g.setColor(UIManager.getColor("Tree.textBackground"));
    			}
    		}
    	}
    	super.paint(g);
    }

    public Dimension getPreferredSize() {
    	Dimension retDimension = super.getPreferredSize();
    	if (retDimension != null) {
    		retDimension = new Dimension(retDimension.width+3, retDimension.height);
    	}
    	return retDimension;
    }

    public void setSelected(boolean isSelected) {
    	this.isSelected = isSelected;
    }

    public void setFocus(boolean hasFocus) {
    	this.hasFocus = hasFocus;
    }
} // end of class TreeLabel
