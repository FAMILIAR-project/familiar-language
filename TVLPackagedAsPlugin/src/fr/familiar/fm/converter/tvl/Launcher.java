/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

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
