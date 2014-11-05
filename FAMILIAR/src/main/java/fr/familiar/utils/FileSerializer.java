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

package fr.familiar.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * @author mathieuacher
 * 
 */
public class FileSerializer {

	/**
	 * FIXME deprecated just a shortcu to apache facilities
	 * @param filename
	 *            locate the file where we want to write
	 * @param dot
	 *            string stream to write
	 * @throws IOException
	 */
	public static void write(String filename, String content)
			throws IOException {
		FileUtils.writeStringToFile(new File(filename), content);
		
	}

	/**
	 * Creates a directory if it does not exist (and parent directory if
	 * necessary)
	 * 
	 * @param dir
	 *            relative location of the directory
	 */
	public static void mkDir(String dir) {
		File f = new File(dir);
		if (f.isDirectory())
			return;
		if (f.exists()) {
			System.err.println(dir + "already exists but is not a directory ");
			return;
		}

		f.mkdirs();

	}

}
