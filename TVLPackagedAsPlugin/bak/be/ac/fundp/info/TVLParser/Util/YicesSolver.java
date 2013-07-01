package be.ac.fundp.info.TVLParser.Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import yices.YicesLite;

public class YicesSolver {

	YicesLite yices;
	int ctx;

	public YicesSolver() {
		yices = new YicesLite();
		ctx = yices.yicesl_mk_context();
		exec("set-evidence! true");

	}

	public boolean isSatisfiable() {
		try {
			yices.yicesl_set_output_file("/tmp/yices.out");
			exec("check");
			FileReader fr = new FileReader(new File("/tmp/yices.out"));
			char[] chars = new char[1];
			fr.read(chars);
			return (chars[0] == 's');

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void exec(String command) {
		System.out.println("(" + command + ")");
		yices.yicesl_read(ctx, "(" + command + ")");

	}

	public void defineVar(String name, String type) {
		exec("define " + name + "::" + type);
	}

	public void defineAssert(String assertion) {
		exec("assert " + assertion);
	}

	public void deleteContext() {
		yices.yicesl_del_context(ctx);
	}
}
