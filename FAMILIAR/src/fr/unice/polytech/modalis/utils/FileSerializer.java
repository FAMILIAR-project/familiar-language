/**
 * 
 */
package fr.unice.polytech.modalis.utils;

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
