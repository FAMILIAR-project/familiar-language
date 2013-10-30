/**
 * 
 */
package fr.familiar.fm.converter;

import java.io.File;

import be.cleve.anthony.FormatConverter;

/**
 * @author mathieuacher
 * 
 */
public class S2T2toFML {

	public String parseFile(File file) {
		String convertion = FormatConverter.toFamiliarFeatureModel(file.getAbsolutePath());
		return convertion;

	}

}
