package fr.unice.polytech.modalis.familiar.test.regression;

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
