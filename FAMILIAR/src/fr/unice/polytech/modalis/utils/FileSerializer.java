/**
 * 
 */
package fr.unice.polytech.modalis.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author mathieuacher
 * 
 */
public class FileSerializer {

	/**
	 * @param filename
	 *            locate the file where we want to write
	 * @param dot
	 *            string stream to write
	 * @throws IOException
	 */
	public static void write(String filename, String content)
			throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				new File(filename)));
		bw.write(content + "\n");
		bw.flush();
		bw.close();

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
