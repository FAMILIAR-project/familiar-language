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
package fr.familiar.standalone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;

import com.martiansoftware.jsap.FlaggedOption;
import com.martiansoftware.jsap.JSAP;
import com.martiansoftware.jsap.JSAPException;
import com.martiansoftware.jsap.JSAPResult;
import com.martiansoftware.jsap.QualifiedSwitch;
import com.martiansoftware.jsap.Switch;
import com.martiansoftware.jsap.UnflaggedOption;

import fr.familiar.interpreter.FMLShell;

/**
 * @author mathieuacher FAMILIAR in a standalone, command-line application
 */
public class FML {

	/**
	 * 
	 */
	public FML() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws JSAPException {

		JSAP jsap = new JSAP();

		// create a switch we'll access using the id "verbose".
		// it has the short flag "-v" and the long flag "--verbose"
		// this will govern whether we say "Hi" or "Hello".
		Switch sw1 = new Switch("verbose").setShortFlag('v').setLongFlag(
				"verbose");
		sw1.setHelp("Requests verbose output.");
		jsap.registerParameter(sw1);

		Switch sw2 = new Switch("help").setShortFlag('h').setLongFlag("help");

		sw2.setHelp("Help.");
		jsap.registerParameter(sw2);

		Switch sw3 = new Switch("version").setLongFlag("version");

		sw3.setHelp("Version of FAMILIAR");
		jsap.registerParameter(sw3);

		QualifiedSwitch qsw1 = (QualifiedSwitch) (new QualifiedSwitch("paths")
				.setShortFlag('p').setRequired(false).setLongFlag("path")
				.setList(true).setListSeparator(','));

		qsw1.setHelp("Paths to consider (FAMILIAR files should be located in those paths)");
		jsap.registerParameter(qsw1);

		FlaggedOption output1 = new FlaggedOption("output")
				.setStringParser(JSAP.STRING_PARSER).setRequired(false)
				.setShortFlag('o').setLongFlag("output");

		output1.setHelp("Output folder where FAMILIAR files are produced.");
		jsap.registerParameter(output1);

		// Create an unflagged option called "name"
		UnflaggedOption opt2 = new UnflaggedOption("filename").setStringParser(
				JSAP.STRING_PARSER).setRequired(false);

		opt2.setHelp("FAMILIAR file to interpret.");
		jsap.registerParameter(opt2);

		JSAPResult config = jsap.parse(args);

		// check whether the command line was valid, and if it wasn't,
		// display usage information and exit.
		if (!config.success()) {

			// print out specific error messages describing the problems
			// with the command line, THEN print usage, THEN print full
			// help. This is called "beating the user with a clue stick."
			for (java.util.Iterator errs = config.getErrorMessageIterator(); errs
					.hasNext();) {
				System.err.println("Error: " + errs.next());
			}

			displayUsage(jsap, System.err);
			System.exit(1);
		}

		boolean help = config.getBoolean("help");
		if (help) {
			displayUsage(jsap, System.out);
			return;
		}

		// FML file to proceed
		String filename = config.getString("filename");

		InputStream in = null; // TODO
		if (filename == null)
			in = System.in;
		else {
			File file = new File(filename);
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.err.println("Unable to load the file "
						+ e.getLocalizedMessage());
				return;
			}
		}

		FMLShell shell = FMLShell.instantiateStandalone(in);

		boolean verbose = config.getBoolean("verbose");
		shell.setVerbose(verbose);

		boolean version = config.getBoolean("version");
		if (version) {
			System.out.println("version " + FMLShell.FML_VERSION);
			return;
		}

		String outputpath = config.getString("output");

		String[] paths = config.getStringArray("paths");
		for (int i = 0; i < paths.length; ++i) {
			String path = paths[i];
			File f = new File(path);
			if (!f.exists()) {
				System.err.println("Path " + path + " does not exist");
				return;
			}
			if (!f.isDirectory()) {
				System.err.println("Path " + path + " is not a directory");
				return;
			}
			shell.addPath(f);
		}

		shell.launch();

	}

	private static void displayUsage(JSAP jsap, PrintStream printer) {
		printer.println();

		printer.println("Usage: java " + FML.class.getSimpleName());
		printer.println("                " + jsap.getUsage());
		printer.println();
		printer.println(jsap.getHelp());

	}

}
