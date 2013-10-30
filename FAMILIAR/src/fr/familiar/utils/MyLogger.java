package fr.familiar.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLogger {

	private static Map<String, MyLogger> _loggers = new HashMap<String, MyLogger>();

	private BufferedWriter _bw;
	private FileWriter _fw;

	private String _fileName;

	private MyLogger(String fileName, boolean concatenate) throws IOException {
		_fileName = fileName;
		File file = new File(_fileName);
		if (!concatenate) {
			if (file.exists()) {
				file.delete();
				file = new File(_fileName);
			}
		}
		_fw = new FileWriter(file, concatenate);
		_bw = new BufferedWriter(_fw);
		;
	}

	public static MyLogger getLogger(String fileName, boolean concatenate)
			throws IOException {
		if (_loggers.containsKey(fileName))
			return _loggers.get(fileName);
		MyLogger myLogger = new MyLogger(fileName, concatenate);
		_loggers.put(fileName, myLogger);
		return myLogger;
	}

	public static MyLogger getLogger(String fileName) throws IOException {
		return getLogger(fileName, false);
	}

	public void trace(Object o) {

		try {
			_bw.write(o.toString() + "");
			_bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
