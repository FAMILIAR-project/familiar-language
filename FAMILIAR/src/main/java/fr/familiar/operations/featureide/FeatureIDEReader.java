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

package fr.familiar.operations.featureide;

import java.io.File;
import java.io.FileNotFoundException;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.familiar.fm.featureide.FeatureIDEtoFML;
import fr.familiar.interpreter.FMLShell;

public class FeatureIDEReader {

	private File _file;

	public FeatureIDEReader(File file) {
		_file = file ; 
	}
	
	public FeatureModel parseFeatureModel() {
		FeatureModel fmFeatureIDE = new FeatureModel();
		GuidslReader fmR = new GuidslReader(fmFeatureIDE);
		try {
			fmR.readFromFile(_file);
		} catch (FileNotFoundException e) {
			FMLShell.getInstance().setError(
					"Unable to find the file " + _file);
			return null ;
		} catch (UnsupportedModelException e) {
			FMLShell.getInstance()
					.setError(
							"Unable to parse the file (FeatureIDE) "+ _file);
			return null ;
		}
		return fmFeatureIDE ; 
	}

	public String writeToString() {
		FeatureModel fmFeatureIDE = parseFeatureModel() ;

		// FIXME @FeatureIDE 
		FeatureIDEtoFML toFML = new FeatureIDEtoFML(fmFeatureIDE);
		return toFML.writeToString();
	}

}
