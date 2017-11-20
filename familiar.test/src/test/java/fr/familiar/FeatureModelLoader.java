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

package fr.familiar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import fr.familiar.fm.converter.SPLOTtoFML;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import splar.core.fm.FeatureModelException;
import splar.core.fm.XMLFeatureModel;

public class FeatureModelLoader {
	
	private static final String absoluteFolder = "/Users/macher1/Documents/SANDBOX/FML-from-scratch/FOReverSE-KSynthesis/Evaluation/";
	
	private static final String SPLOT_FOLDER = absoluteFolder + "input/splot-models-2014-01-30";
	private static final String SPLOT_INCLUDE = absoluteFolder +"input/splot-models-2014-01-30/ESE_meaningful_FMs.txt";
	private static final String SPLOT_EXCLUDE = absoluteFolder + "input/splot-models-2014-01-30/ESE_excluded_FMs.txt";
	private static final String	FASE_EXCLUDE = absoluteFolder + "input/splot-models-2014-01-30/ESE_excluded_FMs_FASE.txt";
	
	private static final String SPLOT_FOLDER_ICSE = absoluteFolder + "input/splot-models-2012-08-07";
	
	private static final String PCM_FOLDER = absoluteFolder + "input/ICSE2014-PCMs";
	
	private static final String FASE_FOLDER = absoluteFolder + "input/FASE13-original-ESE";
	
	
	private FMLShell _shell;
	private BDDBuilder<String> _builder;
	private List<FeatureModelVariable> splotFMs;
	private List<FeatureModelVariable> splotFMsforICSE;
	private List<FeatureModelVariable> pcmFMs;

	
	public FeatureModelLoader(FMLShell _shell, BDDBuilder<String> _builder) {
		this._shell = _shell;
		this._builder = _builder;
		this.splotFMs = null;
	}

	/**
	 * Load all feature models from a specified folder
	 * @param folder
	 * @param extension : file extension
	 * @param includeFiles
	 * @param excludeFiles
	 * @param prefix : prefix of FMs' identifier
	 * @return
	 */
	public List<FeatureModelVariable> loadFeatureModelFolder(
			String folder, final String extension, final List<String> includedFiles, final List<String> excludeFiles, String prefix) {
		
		if (_shell == null) {
			_shell = FMLShell.instantiateStandalone(null);
		}
		

		ArrayList<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>();
		
		File fmsFolder = new File(folder);

		if (fmsFolder.exists() && fmsFolder.isDirectory()) {
			
			// Filter files
			File[] files = fmsFolder.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(extension) 
							&& (includedFiles == null || includedFiles.contains(name)) 
							&& (excludeFiles == null || !excludeFiles.contains(name));
				}
			});

			// Load feature models
			for (File file : files) {

				String fmIdentifier = file.getName();;
				if (prefix != null && !prefix.isEmpty()) {
					fmIdentifier = prefix + "_" + fmIdentifier;	
				}
				
				FeatureModelVariable fm = null;
				
				if (extension.equals(".fmlbdd")) { // For PCMs
					// fm = FMBuilder.parseFMLBDD(file.getAbsolutePath(), _builder);
				} else { // For other files
					fmIdentifier = fmIdentifier.substring(0, fmIdentifier.lastIndexOf("."));
					/*
					 * "search" file seems broken
					String command = fmIdentifier.replaceAll("-", "_") + " = FM(\"" + file.getPath() + "\")";
					try {
						fm = (FeatureModelVariable) _shell.parse(command);	
					} catch (Exception e) {
						fm = null;
					}*/
					
					fm = mkFMFromSPLOTFile(file, fmIdentifier);
					
					
				}
				

				if (fm == null) {
					System.out.println("Error with " + file.getPath());
				} else {
					fm.setIdentifier(fmIdentifier);
					fms.add(fm);	
				}
				
				//_shell.reset();
			}
		}
		
		return fms;
	}
	
	public FeatureModelVariable mkFMFromSPLOTFile(File splotFile, String varID) {
		splar.core.fm.FeatureModel featureModelSPLOT = new XMLFeatureModel(
				splotFile.getAbsolutePath(),
				XMLFeatureModel.USE_VARIABLE_NAME_AS_ID);
		try {
			featureModelSPLOT.loadModel();
		} catch (FeatureModelException e) {
			FMLShell.getInstance().printError(
					"Unable to load SPLOT feature model "
							+ e.getMessage());
			return null;
		}

		// strfm = new SPLOTtoFML().convert(featureModelSPLOT); // @Deprecated
	
		
		gsd.synthesis.FeatureModel<String> rFM = new SPLOTtoFML().convertToFeatureModel(featureModelSPLOT);
		FeatureModelVariable fmv = new FeatureModelVariable(varID, rFM) ; 
		return fmv;
		
	}

	/**
	 * Read a list of feature models' file names from a file
	 * @param file: list of file names on each line
	 * @return
	 */
	public List<String> readFMNamesFromFile(String file) {
		List<String> fmNames = new ArrayList<String>();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String filename = reader.readLine();
			while (filename != null) {
				fmNames.add(filename);
				filename = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fmNames;
	}
	
	public List<FeatureModelVariable> getSPLOTFeatureModels()  {
		if (splotFMs == null) {
			List<String> includedFMs = readFMNamesFromFile(SPLOT_INCLUDE);
			List<String> excludedFMs = readFMNamesFromFile(SPLOT_EXCLUDE);
			excludedFMs.addAll(readFMNamesFromFile(FASE_EXCLUDE));
			splotFMs = loadFeatureModelFolder(SPLOT_FOLDER, ".xml", includedFMs, excludedFMs, "");
			System.out.println(splotFMs.size() + " FMs loaded from SPLOT");
		}

		return splotFMs;
	}
	
	
	
	public List<FeatureModelVariable> getPCMFeatureModels() {
//		if (variCellFMs == null) {
			pcmFMs = loadFeatureModelFolder(PCM_FOLDER, ".fmlbdd", null, null, "");
			System.out.println(pcmFMs.size() + " FMs loaded from PCMs");
//		}

		return pcmFMs;
	}
	
	public List<FeatureModelVariable> getFASEFeatureModels() {
		List<String> includedFMs = readFMNamesFromFile(SPLOT_INCLUDE);
		List<String> excludedFMs = readFMNamesFromFile(SPLOT_EXCLUDE);
		excludedFMs.addAll(readFMNamesFromFile(FASE_EXCLUDE));
		List<FeatureModelVariable> pcmFASE = loadFeatureModelFolder(FASE_FOLDER, ".xml", includedFMs, excludedFMs, "");
		System.out.println(pcmFASE.size() + " FMs loaded from FASE");
		return pcmFASE;
	}
	
	public List<FeatureModelVariable> getAllSPLOTFeatureModels()  {
		List<String> includedFMs = readFMNamesFromFile(SPLOT_INCLUDE);
		List<String> excludedFMs = readFMNamesFromFile(SPLOT_EXCLUDE);
		List<FeatureModelVariable> allSplotFMs = loadFeatureModelFolder(SPLOT_FOLDER, ".xml", includedFMs, excludedFMs, "");
		System.out.println(allSplotFMs.size() + " FMs loaded from SPLOT");
		return allSplotFMs;
	}
	
	
	
	
}
