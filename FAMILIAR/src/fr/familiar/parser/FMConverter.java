package fr.familiar.parser;

import java.io.File;

import fr.familiar.fm.converter.FDModelErrorException;
import fr.familiar.fm.converter.FDUnsupportedModelException;

public abstract class FMConverter {

	public abstract String parseFile(File searchFile)
			throws FDModelErrorException, FDUnsupportedModelException;

}
