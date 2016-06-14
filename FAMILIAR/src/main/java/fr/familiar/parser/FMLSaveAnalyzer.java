/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
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
package fr.familiar.parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.ConfigurationCommand;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.FMFormat;
import org.xtext.example.mydsl.fml.FMLSave;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.utils.URIUtils;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;

/**
 * @author mathieuacher
 * 
 */
public class FMLSaveAnalyzer extends FMLAbstractCommandAnalyzer {

	public FMLSaveAnalyzer(Command cmd2, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd2, ns, an);

	}

	public FMLSaveAnalyzer(Command cmd, NameSpace ns, String var,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof FMLSave);
		FMLSave saveCmd = (FMLSave) _command;

		EObject eo = saveCmd.getV();

		if (eo instanceof FMCommand) {
			FMCommand fmToConvert = (FMCommand) eo;

			FeatureModelVariable fmv = _environment.parseFMCommand(fmToConvert, null, null);
			FMFormat format = saveCmd.getFormat();
			String convertion = ConvertAnalyzer.convert(fmv, format);
			FMLShell.getInstance()
					.printDebugMessage("convertion=" + convertion);

			String name = fmv.getIdentifier(); // saveCmd.getFilename();

			if (FMLShell.getInstance().isEclipseBased()) {
				IFile path = FMLShell.getInstance().getOutputPath();

				String directory = path.getLocationURI().toString();

				String filename = directory
						+ System.getProperty("file.separator") + name + "." +
						// SCALA:(modalis.polytech.unice.fr.utils.FMFormat2Extension.apply(format).
						formatToFileExtension(format);

				URI uri = URI.create(filename);
				IFile file = URIUtils.getIFileFromURI(uri); // where to
															// serialize
				// write in file the content of convertion
				try {
					writeToFile(file, convertion);
				} catch (CoreException e) {
					FMLShell.getInstance().setError(
							"Unable to serialize " + e.getLocalizedMessage());
					return;
				}
			} else {
				File outputDirectory = FMLShell.getInstance()
						.getStandaloneOutputPath();

				String filename = outputDirectory.getAbsolutePath()
						+ System.getProperty("file.separator") + name + "."
						+ formatToFileExtension(format);

				File file = new File(filename);
				try {
					writeToFile(file, convertion);
				} catch (IOException e) {
					FMLShell.getInstance().setFatalError(
							"Unable to serialize " + e.getMessage());
					return;
				}

			}

		} else if (eo instanceof ConfigurationCommand) {
			FMLShell.getInstance().printTODO(
					"currently unable to serialize configurations like " + eo);
		} else {
			FMLShell.getInstance().setError(
					"unexpected error: feature models or configurations expected but "
							+ eo);
		}

	}

	/**
	 * @param format
	 * @return the file extension associated to the format @format
	 */
	public static String formatToFileExtension(FMFormat format) {
		if (format == FMFormat.FCALC)
			return "fcalc";
		else if (format == FMFormat.FIDE)
			return "m";
		else if (format == FMFormat.FTVL)
			return "tvl";
		else if (format == FMFormat.FFML)
			return "fml";
		else if (format == FMFormat.FFML2) // XMI
			return "xmi";
		else if (format == FMFormat.FSPLOT)
			return "xml";
		else if (format == FMFormat.S2T2)
			return "fmprimitives";
		else if (format == FMFormat.FMLBDD)
			return "fmlbdd";
		else if (format == FMFormat.FMLBDD_ONLY)
			return "bdd";
		else if (format == FMFormat.FMLCONSTRAINT)
			return "constraints";
		else
			return "unknown";
	}
	
	/**
	 * @param format
	 * @return the file extension associated to the format @format
	 */
	public static String formatToToolName(FMFormat format) {
		if (format == FMFormat.FIDE)
			return "FeatureIDE";
		else if (format == FMFormat.FTVL)
			return "TVL";
		else if (format == FMFormat.FFML)
			return "FAMILIAR";
		else if (format == FMFormat.FFML2) // XMI
			return "xmi";
		else if (format == FMFormat.FSPLOT)
			return "SPLOT/SXFM";
		else if (format == FMFormat.S2T2)
			return "S2T2";
		else if (format == FMFormat.FMLCONSTRAINT)
			return "FML constraints";
		else
			return "unknown";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return RType.VOID;
	}

	/**
	 * writing facilities to serialize a feature model variable (Eclipse version
	 * uses IFile, not File)
	 * 
	 * @param file
	 * @param writeToString
	 * @throws CoreException
	 */
	public void writeToFile(IFile file, String writeToString)
			throws CoreException {
		InputStream source = new ByteArrayInputStream(writeToString.getBytes());
		if (file.exists()) {
			file.setContents(source, false, true, null);
		} else {
			file.create(source, false, null);
		}
	}

	/**
	 * writing facilities to serialize a feature model variable (standalone
	 * version uses File, not IFile)
	 * 
	 * @param file
	 * @param writeToString
	 * @throws CoreException
	 * @throws IOException
	 */

	public void writeToFile(File file, String writeToString) throws IOException {
		InputStream source = new ByteArrayInputStream(writeToString.getBytes());
		if (!file.exists())
			file.createNewFile();

		BufferedReader br = new BufferedReader(new InputStreamReader(source));
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		String s = null;
		while ((s = br.readLine()) != null)
			bw.write(s + "\n");
		bw.flush();
		br.close();
		bw.close();
	}

}
