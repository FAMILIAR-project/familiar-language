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
package fr.unice.polytech.modalis.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Recursive file listing under a specified directory.
 * 
 * @author Mathieu Acher
 */
public class FileListing {

	public static final String FAMILIAR_EXTENSION = "fml";
	private static final String XMI_EXTENSION = "xmi";
	private static final String TVL_EXTENSION = "tvl";
	private static final String FEATUREIDE_EXTENSION = "m";
	private static final String TRISKELL_EXTENSION = "fd";
	private static final String SPLOT_EXTENSION = "xml";

	private List<File> paths;

	public FileListing(String path) {
		this(new File(path));
	}

	public FileListing(File path) {

		paths = new ArrayList<File>();
		paths.add(path);
	}

	public FileListing(List<File> paths) {
		this.paths = paths;
	}

	/**
	 * Recursively walk a directory tree and return a List of all Files found;
	 * the List is sorted using File.compareTo().
	 * 
	 * @param startingDir
	 *            is a valid directory, which can be read.
	 */
	public List<File> getFileListing(File startingDir) {
		List<File> result = getFileListingNoSort(startingDir);
		Collections.sort(result);
		return result;
	}

	private List<File> getFileListingNoSort(File startingDir) {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = startingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for (File file : filesDirs) {
			result.add(file); // always add, even if directory
			if (isDirectory(file)) {
				// must be a directory
				// recursive call!
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, can be read,
	 * and is not .svn
	 */
	private boolean isDirectory(File aDirectory) {
		if (aDirectory == null)
			return false;
		if (aDirectory.exists() && aDirectory.isDirectory()
				&& aDirectory.canRead() && !aDirectory.isFile()) {
			if (aDirectory.getName().contains("svn"))
				return false;
			else
				return true;
		}

		return false;
	}

	public File searchFile(File aFile) throws FileNotFoundException,
			AmbigousFileNameException {

		List<File> filesFound = new ArrayList<File>();
		for (File p : paths) {
			File f = searchFileInPath(aFile, p);
			if (f != null)
				filesFound.add(f);
		}

		if (filesFound.size() == 0)
			throw new FileNotFoundException(aFile.getName());
		if (filesFound.size() > 1)
			throw new AmbigousFileNameException(aFile.getName(), filesFound);

		return filesFound.get(0);
	}

	public File searchFileInPath(File aFile, File path)
			throws AmbigousFileNameException {

		List<File> filesFound = new ArrayList<File>();
		List<File> files = getFileListing(path);
		for (File f : files) {
			if (f.getName().equals(aFile.getName())) // important!
				filesFound.add(f);
			else {
				String fileName = f.getName();
				String fileNameWithoutExtension = fileName;
				if (fileName.lastIndexOf(".") != -1) {
					fileNameWithoutExtension = fileName.substring(0,
							fileName.lastIndexOf("."));
					String extension = fileName.substring(fileName
							.lastIndexOf(".") + 1);
					// FMShell.getInstance().printDebugMessage("extension " +
					// extension );
					if (!checkExtension(extension))
						continue;
				}
				if (fileNameWithoutExtension.equals(aFile.getName()))
					filesFound.add(f);

			}

		}

		if (filesFound.size() == 0)
			return null;

		if (filesFound.size() > 1)
			throw new AmbigousFileNameException(aFile.getName(), filesFound);

		return filesFound.get(0);
	}

	private boolean checkExtension(String extension) {
		return extension.equals(FAMILIAR_EXTENSION)
				|| extension.equals(XMI_EXTENSION)
				|| extension.equals(TVL_EXTENSION)
				|| extension.equals(FEATUREIDE_EXTENSION)
				|| extension.equals(TRISKELL_EXTENSION)
				|| extension.equals(SPLOT_EXTENSION);
	}

	public File searchFileInPath(String aStrFile, File path)
			throws AmbigousFileNameException {

		String strPath = path.getAbsolutePath();
		String strCompleteFile = strPath + "/" + aStrFile;
		File cfile = new File(strCompleteFile);
		if (cfile.exists())
			return cfile;

		return searchFileInPath(cfile, path);
	}

	public File searchFile(String aStrFile) throws FileNotFoundException,
			AmbigousFileNameException {
		List<File> filesFound = new ArrayList<File>();
		for (File p : paths) {
			File f = searchFileInPath(aStrFile, p);
			if (f != null)
				filesFound.add(f);
		}

		if (filesFound.size() == 0)
			throw new FileNotFoundException(aStrFile);
		if (filesFound.size() > 1)
			throw new AmbigousFileNameException(aStrFile, filesFound);

		return filesFound.get(0);
	}

}
