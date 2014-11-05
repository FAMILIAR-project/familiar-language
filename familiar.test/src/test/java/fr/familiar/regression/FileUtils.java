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

package fr.familiar.regression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	/**
	 * quick and dirty method
	 * 
	 * @param file
	 *            The file to process
	 * @return string-based content of file
	 * @throws IOException
	 */
	public static String read(File file) throws IOException {

		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str = "";

		while ((str = br.readLine()) != null) {
			sb.append(str);
		}

		return sb.toString();

	}

	/**
	 * quick and dirty method
	 * 
	 * @param filename
	 * @return string-based content of the file located in filename
	 * @throws IOException
	 */
	public static String read(String filename) throws IOException {
		File file = new File(filename);
		return read(file);
	}

}
