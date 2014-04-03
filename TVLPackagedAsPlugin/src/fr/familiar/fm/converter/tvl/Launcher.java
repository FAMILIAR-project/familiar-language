package fr.familiar.fm.converter.tvl;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Launcher & Temp test class
 * 
 * @author Charles Vanbeneden
 * 
 */

public class Launcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String inputFilePath = "test.tvl";

		System.out.print("Initialising FAMILIAR translator : ");
		TVLTranslator translator = null;
		try {
			translator = new TVLTranslator(new File(inputFilePath));
			System.out.println("OK");
		} catch (FeatureModelInvalidException e) {
			System.out.println("KO");
			System.out.println(e.getMessage());
			System.exit(1);
		} catch (FileNotFoundException e) {
			System.out.println("KO");
			System.out.println("The file " + inputFilePath + " could not be found or opened.");
			System.exit(1);
		} catch (Exception e) {
			System.out.println("KO");
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println("Translating TVL file " + inputFilePath + " to FAMILIAR Feature Model...");

		// For debug
		try {
			System.out.println("");
			System.out.println("DEBUG :");
			System.out.println(translator.getTVLNormalFormToString());
			System.out.println(translator.getTVLBooleanFormToString());
			System.out.println("TVL SOLUTIONS : " + translator.getTVLNumberSolutions());
			translator.getTVLFilteredSolutionsToString();
		} catch (Exception e) {
			System.out.println("Invalid FM.");
			e.printStackTrace();
			System.exit(1);
		}

		if (translator.hasWarnings()) {
			System.out.println("WARNINGS :");
			translator.getWarningsToString();
		}

		System.out.println("FAMILIAR FML Output :");
		System.out.println("//*****************BEGIN******************");
		try {
			String fOutput = translator.getFAMILIARFMLOutput();
			System.out.println(fOutput);

		} catch (Exception e1) {
			System.out.println("//KO - Invalid FM");
			e1.printStackTrace();
			System.exit(1);
		}
		System.out.println("//******************END*******************");
		System.out.println("");
		if (translator.getFAMILIARFMLOuputToFile("translationOutput.fml"))
			System.out.println("Output writen to translationOutput.fml");
		else
			System.out.println("Error writing output!");
	}
}
