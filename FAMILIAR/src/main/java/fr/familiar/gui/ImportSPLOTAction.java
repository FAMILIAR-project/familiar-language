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

import java.io.File;

import org.xtext.example.mydsl.fml.FMFormat;

import splar.core.fm.XMLFeatureModel;
import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.variable.FeatureModelVariable;

public class ImportSPLOTAction extends ImportAction {
	private static final long serialVersionUID = 1L;
	
	public ImportSPLOTAction() {
		super(FMFormat.FSPLOT);
    }
	
	@Override
	public FeatureModelVariable readSpecificFormat(File f) {
    	FeatureModelVariable fmv = null;
 		try {
 			splar.core.fm.FeatureModel fmSPLOT = new XMLFeatureModel(
 	    		f.getAbsolutePath(), XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
 			fmSPLOT.loadModel(); 		
 			fmv = Converter.INSTANCE.createNewFMV(
 					new SPLOTtoFML(true).convertToFeatureModel(fmSPLOT), fmSPLOT.getName());
 		} catch (Exception e) {
 			return null;
 		}
 		return fmv;
    }
} // end of class ImportSPLOTAction
