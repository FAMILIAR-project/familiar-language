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
import java.util.ArrayList;
import java.util.List;

import fr.familiar.interpreter.FMLShell;

/**
 * @author mathieuacher FAMILIAR in a standalone, command-line application
 */
public class FML {

	public static void main(String[] args) {
		boolean verbose = false;
		boolean help = false;
		boolean version = false;
		String filename = null;
		String outputPath = null;
		List<String> paths = new ArrayList<>();

		// Simple argument parsing
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equals("-v") || arg.equals("--verbose")) {
				verbose = true;
			} else if (arg.equals("-h") || arg.equals("--help")) {
				help = true;
			} else if (arg.equals("--version")) {
				version = true;
			} else if (arg.equals("-o") || arg.equals("--output")) {
				if (i + 1 < args.length) {
					outputPath = args[++i];
				} else {
					System.err.println("Error: -o/--output requires an argument");
					displayUsage(System.err);
					System.exit(1);
				}
			} else if (arg.equals("-p") || arg.equals("--path")) {
				if (i + 1 < args.length) {
					String pathList = args[++i];
					for (String p : pathList.split(",")) {
						paths.add(p.trim());
					}
				} else {
					System.err.println("Error: -p/--path requires an argument");
					displayUsage(System.err);
					System.exit(1);
				}
			} else if (!arg.startsWith("-")) {
				filename = arg;
			} else {
				System.err.println("Error: Unknown option: " + arg);
				displayUsage(System.err);
				System.exit(1);
			}
		}

		if (help) {
			displayUsage(System.out);
			return;
		}

		if (version) {
			System.out.println("version " + FMLShell.FML_VERSION);
			return;
		}

		InputStream in;
		if (filename == null) {
			in = System.in;
		} else {
			File file = new File(filename);
			try {
				in = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				System.err.println("Unable to load the file " + e.getLocalizedMessage());
				return;
			}
		}

		FMLShell shell = FMLShell.instantiateStandalone(in);
		shell.setVerbose(verbose);

		for (String path : paths) {
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

	private static void displayUsage(PrintStream printer) {
		printer.println();
		printer.println("Usage: java " + FML.class.getSimpleName() + " [options] [filename]");
		printer.println();
		printer.println("Options:");
		printer.println("  -v, --verbose     Enable verbose output");
		printer.println("  -h, --help        Display this help message");
		printer.println("  --version         Display version information");
		printer.println("  -o, --output DIR  Output folder for FAMILIAR files");
		printer.println("  -p, --path PATHS  Comma-separated list of paths to search");
		printer.println();
		printer.println("Arguments:");
		printer.println("  filename          FAMILIAR file to interpret (optional, reads stdin if not provided)");
		printer.println();
	}
}
