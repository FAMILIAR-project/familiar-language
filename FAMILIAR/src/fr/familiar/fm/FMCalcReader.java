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
package fr.familiar.fm;


@Deprecated

public class FMCalcReader {
/*
	private String strFile;
	private File file;
	private FMLShell shell;
	private URI uri;
	private String msg = null;

	public FMCalcReader(String strFile, FMLShell shell) {
		this.strFile = strFile;
		this.shell = shell;

		init();

	}

	private void init() {
		// searching recursively for a file 'filename' which is in the shell
		// path (directory tree)
		file = shell.searchFile(new File(strFile));
		uri = URI.createFileURI(file.getPath());

	}

	@Deprecated
	public FeatureModel parse() {
		msg = null; // no msg error when we start parsing

		// checking if file exists
		if (file == null || !file.isFile() || !file.exists()) {
			setErrorMsg("File (fm) " + strFile + " not found.");
			return null;
		}

		// Injector injector = new
		// FMMStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = new XtextResourceSet(); // .getInstance(XtextResourceSet.class);
		if (resourceSet == null) {
			setErrorMsg("Unable to construct ResourceSet.");
			return null;
		}

		// resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL,
		// Boolean.TRUE);

		Resource resource = resourceSet.getResource(uri, true);
		if (resource == null) {
			setErrorMsg("Resource not found.");
			System.err.println("Resource not found");
			return null;
		}

		if (resource.getErrors().size() > 0) {
			setErrorMsg(resource.getErrors().toString());
			return null;
		}
		if (resource.getContents().size() == 0) {
			setErrorMsg("No contents.");
			return null;
		}

		FeatureModel fm = (FeatureModel) resource.getContents().get(0);
		if (fm == null) {
			setErrorMsg("No model found.");
			return null;
		}

		return fm;
	}

	public FeatureModelVariable parseFM() {
		String strfm = parseStr();
		String name = strfm.substring(0, strfm.indexOf("=")).trim();

		strfm = strfm.substring(strfm.indexOf("{") + 1);
		strfm = strfm.substring(0, strfm.indexOf("}"));

		gsd.synthesis.FeatureModel<String> fm = FeatureModelParser
				.parseString(strfm);
		return new FeatureModelVariable(name, fm);
	}

	private void setErrorMsg(String str) {
		this.msg = str;
	}

	public String getErrorMsg() {
		return msg;
	}

	public boolean hasError() {
		return msg != null;
	}

	public void print(FeatureModel fm) {
		System.out.println(new FeatureModelCalcPrinter(fm).toString());
	}

	public String parseStr() {

		msg = null; // no msg error when we start parsing
		List<String> strfms = parseListStr();
		if (strfms.size() == 0) {
			System.err.println("No feature model found.");
			return null;
		}
		return strfms.get(0);

	}

	public List<FeatureModelVariable> parseFMs() {
		List<FeatureModelVariable> res = new ArrayList<FeatureModelVariable>();
		List<String> strfms = parseListStr();
		if (strfms == null || strfms.size() == 0) {
			return res;
		}

		for (String strfm : strfms) {

			String name = "";
			try {
				name = strfm.substring(0, strfm.indexOf("=")).trim();
				strfm = strfm.substring(strfm.indexOf("{") + 1);
				strfm = strfm.substring(0, strfm.indexOf("}"));
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				return res;
			}
			gsd.synthesis.FeatureModel<String> fm = FeatureModelParser
					.parseString(strfm);
			res.add(new FeatureModelVariable(name, fm));
		}

		return res;
	}

	private List<String> parseListStr() {
		msg = null; // no msg error when we start parsing
		if (file == null || !file.isFile() || !file.exists()) {
			setErrorMsg("File " + uri.toFileString() + " not found.");
			return null;
		}

		BufferedReader br;
		List<String> strs = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(file));
			String cstr = "";
			String str = "";
			while ((cstr = br.readLine()) != null) {
				str += cstr;
				strs.add(cstr);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return strs;
	}

	public boolean isValid() {
		boolean valid = parseFMs() != null && parseFMs().size() > 0;
		return valid;
	}*/

}
