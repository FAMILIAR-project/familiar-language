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

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.xtext.example.mydsl.fML.FMFormat;

import fr.unice.polytech.modalis.familiar.parser.FMLSaveAnalyzer;

public class ConcreteAction extends AbstractAction {
	private static final long serialVersionUID = 1L;
	
	protected String fileType;
	protected String fileTypeLabel;
	protected String longName;
	protected String desc;
	
	protected void initialize(FMFormat format, String iOre) {
		fileType = FMLSaveAnalyzer.formatToFileExtension(format);
    	fileTypeLabel = "(*." + fileType + ")";
    	longName = FMLSaveAnalyzer.formatToToolName(format);
    	desc = iOre + " " + longName + " file";
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
	}
} // end of class ConcreteAction
