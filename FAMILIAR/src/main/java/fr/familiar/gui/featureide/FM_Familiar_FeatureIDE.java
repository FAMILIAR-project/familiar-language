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
package fr.familiar.gui.featureide;

import java.io.File;
import java.net.URI;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.IFeatureModelWriter;
import de.ovgu.featureide.fm.core.io.xml.XmlFeatureModelWriter;
import fr.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.utils.URIUtils;
import fr.familiar.variable.FeatureModelVariable;

public class FM_Familiar_FeatureIDE {

	private static HashMap<FeatureModelVariable, IFile> hashmap = new HashMap<FeatureModelVariable, IFile>();
	private static IFeatureModelWriter fmWriter;

	// TODO: check whether SPLOT is not .xml too :(
	// FIXME: serialization / import for GUIDSL or FeatureIDEXML
	private final static String FILENAME_EXTENSION = ".xml"; // formerly: .m

	public static IFile get(FeatureModelVariable fmw) {
		de.ovgu.featureide.fm.core.FeatureModel fm = new FMLtoFeatureIDE(fmw).convert();
		IFile ifile = hashmap.get(fmw);
		fmWriter = new XmlFeatureModelWriter(fm); 
		File file = ifile.getRawLocation().makeAbsolute().toFile(); // workaround found here: http://stackoverflow.com/questions/8812413/convert-ifile-to-file
		fmWriter.writeToFile(file);
		return ifile;
	}

	private static IFile getSerializationFile(FeatureModelVariable fmw) {
		String directory = FMLShell.getInstance().getTemporaryPath();
		String name = fmw.getIdentifier();
		String filename = directory + name + FILENAME_EXTENSION;
		URI uri = URI.create(filename);
		IFile lfile = URIUtils.getIFileFromURI(uri);
		return lfile;
	}

	/*
	 * 
	 * @param fmw a feature model variable associate a FM variable to a FM in
	 * FeatureIDE formalism
	 */
	public static void register(FeatureModelVariable fmw) {
		de.ovgu.featureide.fm.core.FeatureModel fm = new FMLtoFeatureIDE(fmw).convert();
		IFile ifile = getSerializationFile(fmw);
		// TODO: why not directly return ifile?
		fmWriter = new XmlFeatureModelWriter(fm); 
		File file = ifile.getRawLocation().makeAbsolute().toFile(); // workaround found here: http://stackoverflow.com/questions/8812413/convert-ifile-to-file
		fmWriter.writeToFile(file);
		hashmap.put(fmw, ifile);

	}

	public static boolean isExists(FeatureModelVariable fmw) {
		return hashmap.containsKey(fmw);
	}

	public static FeatureModel updateFM(FeatureModelVariable fmw) {
		return new FMLtoFeatureIDE(fmw).convert() ;
	}
}
