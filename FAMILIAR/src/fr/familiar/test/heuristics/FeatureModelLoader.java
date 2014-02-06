package fr.familiar.test.heuristics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMBuilder;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;

public class FeatureModelLoader {
	
	private static final String SPLOT_FOLDER = "inputFML/splot-models-2014-01-30";
	private static final String SPLOT_INCLUDE = "inputFML/splot-models-2014-01-30/ESE_meaningful_FMs.txt";
	private static final String SPLOT_EXCLUDE = "inputFML/splot-models-2014-01-30/ESE_excluded_FMs.txt";

	private static final String SPLOT_FOLDER_ICSE = "inputFML/splot-models-2012-08-07";
	
	private static final String PCM_FOLDER = "inputFML/ICSE2014-PCMs";
	
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
					fm = FMBuilder.parseFMLBDD(file.getAbsolutePath(), _builder);
				} else { // For other files
					fmIdentifier = fmIdentifier.substring(0, fmIdentifier.lastIndexOf("."));
					String command = fmIdentifier.replaceAll("-", "_") + " = FM(\"" + file.getPath() + "\")";
					try {
						fm = (FeatureModelVariable) _shell.parse(command);	
					} catch (Exception e) {
						fm = null;
					}
					
				}
				

				if (fm == null) {
					System.out.println("Error with " + file.getPath());
				} else {
					fm.setIdentifier(fmIdentifier);
					fms.add(fm);	
				}
				
				_shell.reset();
			}
		}
		
		return fms;
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
			splotFMs = loadFeatureModelFolder(SPLOT_FOLDER, ".xml", includedFMs, excludedFMs, "");
			System.out.println(splotFMs.size() + " FMs loaded from SPLOT");
		}

		return splotFMs;
	}
	
	public List<FeatureModelVariable> getSPLOTFeatureModelsForICSE()  {
		if (splotFMsforICSE == null) {
			List<String> excludeFiles = new ArrayList<String>(KSynthesisTest.splotExcludeFiles);
			excludeFiles.addAll(KSynthesisTest.faseExcludeFiles);
			splotFMsforICSE = loadFeatureModelFolder(SPLOT_FOLDER_ICSE, ".xml", null, excludeFiles , "");
			System.out.println(splotFMsforICSE.size() + " FMs loaded from SPLOT for ICSE");
		}

		return splotFMsforICSE;
	}
	
	public List<FeatureModelVariable> getPCMFeatureModels() {
//		if (variCellFMs == null) {
			pcmFMs = loadFeatureModelFolder(PCM_FOLDER, ".fmlbdd", null, null, "");
			System.out.println(pcmFMs.size() + " FMs loaded from PCMs");
//		}

		return pcmFMs;
	}
	
	
}
