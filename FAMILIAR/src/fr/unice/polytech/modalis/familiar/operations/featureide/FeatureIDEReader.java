package fr.unice.polytech.modalis.familiar.operations.featureide;

import java.io.File;
import java.io.FileNotFoundException;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.io.UnsupportedModelException;
import de.ovgu.featureide.fm.core.io.guidsl.GuidslReader;
import fr.unice.polytech.modalis.familiar.fm.featureide.FeatureIDEtoFML;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;

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
