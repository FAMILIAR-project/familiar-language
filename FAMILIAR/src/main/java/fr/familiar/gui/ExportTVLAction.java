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

import fr.familiar.parser.ConvertAnalyzer;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

public class ExportTVLAction extends ExportAction {
	private static final long serialVersionUID = 1L;
	  
	public ExportTVLAction() {
		super(FMFormat.FTVL);
    }
	
	@Override
	public boolean saveSpecificFormat(File f, FeatureModelVariable fmv) {
		try {
      		String tvl = ConvertAnalyzer.convert(fmv, format);
      		FileSerializer.write(f.getAbsolutePath(), tvl);
        } catch (Exception e) {
        	return false;
        }
		return true;
    }
} // end of class ExportTVLAction
