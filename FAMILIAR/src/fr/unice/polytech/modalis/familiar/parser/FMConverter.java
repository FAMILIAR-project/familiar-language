package fr.unice.polytech.modalis.familiar.parser;

import java.io.File;

import fr.unice.polytech.modalis.familiar.fm.converter.FDModelErrorException;
import fr.unice.polytech.modalis.familiar.fm.converter.FDUnsupportedModelException;

public abstract class FMConverter {

	public abstract String parseFile(File searchFile)
			throws FDModelErrorException, FDUnsupportedModelException;

}
