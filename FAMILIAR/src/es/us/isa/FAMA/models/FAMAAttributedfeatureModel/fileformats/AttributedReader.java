/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats;

import java.io.File;

import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;
import es.us.isa.FAMA.parser.FMFParser;
import fr.familiar.attributedfm.AttributedFeatureModel;

public class AttributedReader implements IReader {

	private FMFParser parser;
	
	public AttributedReader(){
		parser = new FMFParser();
	}
	
	
	public boolean canParse(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf('.'));
		File f = new File(fileName);
		boolean b = (f.exists() && (extension.equals(".afm") || extension.equals(".efm")));
		return b;
	}

	
	public AttributedFeatureModel parseFile(String fileName) throws Exception {
		AttributedFeatureModel res = parser.parseModel(fileName);
		return res;
	}

	
	public AttributedFeatureModel parseString(String data) throws Exception {
		AttributedFeatureModel res = parser.parseModelFromString(data);
		return res;
	}

}
