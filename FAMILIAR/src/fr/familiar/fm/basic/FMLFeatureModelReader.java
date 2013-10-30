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
package fr.familiar.fm.basic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.xtext.example.mydsl.FMLStandaloneSetup;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.ScriptCommand;
import org.xtext.example.mydsl.fML.impl.ComplexCommandImpl;
import org.xtext.example.mydsl.fML.impl.FamiliarScriptImpl;

/**
 * @author mathieuacher
 * 
 */
public class FMLFeatureModelReader {

	public FMLFeatureModelReader() {
		this(true);
	}

	public FMLFeatureModelReader(boolean standalone) {
		if (standalone)
			initStandalone();
	}

	private void initStandalone() {
		new FMLStandaloneSetup().createInjectorAndDoEMFRegistration();

	}

	public EObject getRoot(String input) {

		ResourceSet rs = new ResourceSetImpl();
		Resource resource = rs.createResource(URI
				.createURI("file:/example.fml"));
		InputStream in = new ByteArrayInputStream(input.getBytes());
		try {
			resource.load(in, rs.getLoadOptions());
		} catch (IOException e) {
			System.err.println("Impossible to read input string "
					+ e.getLocalizedMessage());
		}

		EObject eobject = resource.getContents().get(0);
		if (!resource.getErrors().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Diagnostic error : resource.getErrors()) {
				sb.append("line(" + error.getLine() + ") "
						+ error.getLocation() + ": " + error.getMessage());

			}
			throw new IllegalArgumentException(sb.toString());
		}
		return eobject;
	}

	public FeatureModel parseString(String input) {

		if (!(input.startsWith("FM (")))
			return parseString("FM (" + input + ")");

		EObject eobject = getRoot(input);

		if (!(eobject instanceof FamiliarScriptImpl))
			throw new IllegalArgumentException(
					"Not a legal FML expression (eobject=" + eobject + ")"); // should
																				// not
																				// happen

		FamiliarScriptImpl script = (FamiliarScriptImpl) eobject;

		EList<ScriptCommand> commands = script.getCmds();
		if (commands.size() < 1) {
			throw new IllegalArgumentException(
					"Not a legal FML expression (size=" + 0 + ")");
		}
		assert (commands.size() == 1);
		ScriptCommand expression = commands.get(0);
		if (!(expression instanceof ComplexCommandImpl)) {
			throw new IllegalArgumentException(
					"Not a feature model expression (expression=" + expression
							+ ")");
		}
		ComplexCommandImpl FMLcommand = (ComplexCommandImpl) expression;
		Command cmd = FMLcommand.getLeft();

		if (cmd == null || !(cmd instanceof FeatureModel))
			throw new IllegalArgumentException(
					"Impossible to parse the input as a feature model (expression="
							+ cmd + ")");

		return (FeatureModel) cmd;

	}

	/*
	 * XMI file
	 */
	public FeatureModel parseXMIFile(File file) {
		assert (file.getName().endsWith("xmi"));
		return parseFile(file);
	}

	public FeatureModel parseXMIFile(URI uri) {
		assert (uri.fileExtension().equals("xmi"));
		return parseXMIFile(new File(uri.toFileString()));
	}

	public FeatureModel parseXMIFile(XMIResource xmi) {
		return parseXMIFile(xmi.getURI());
	}

	public FeatureModel parseFile(File file) {

		ResourceSet rs = new ResourceSetImpl();

		String uri = file.getAbsolutePath();
		Resource resource = rs.getResource(URI.createURI("file:" + uri), true);

		if (!resource.getErrors().isEmpty()) {
			StringBuilder sb = new StringBuilder();
			for (Diagnostic error : resource.getErrors()) {
				sb.append("line(" + error.getLine() + ") "
						+ error.getLocation() + ": " + error.getMessage());

			}
			throw new IllegalArgumentException(sb.toString());
		}

		EObject eobject = resource.getContents().get(0);

		if (eobject instanceof FeatureModel)
			return (FeatureModel) eobject;

		if (!(eobject instanceof FamiliarScriptImpl))
			throw new IllegalArgumentException(
					"Not a legal FML expression (eobject=" + eobject + ")"); // should
																				// not
																				// happen

		FamiliarScriptImpl script = (FamiliarScriptImpl) eobject;

		EList<ScriptCommand> commands = script.getCmds();
		if (commands.size() < 1) {
			throw new IllegalArgumentException(
					"Not a legal FML expression (size=" + 0 + ")");
		}
		assert (commands.size() == 1);
		ScriptCommand expression = commands.get(0);
		if (!(expression instanceof ComplexCommandImpl)) {
			throw new IllegalArgumentException(
					"Not a feature model expression (expression=" + expression
							+ ")");
		}
		ComplexCommandImpl FMLcommand = (ComplexCommandImpl) expression;
		Command cmd = FMLcommand.getLeft();

		if (cmd == null || !(cmd instanceof FeatureModel))
			throw new IllegalArgumentException(
					"Impossible to parse the input as a feature model (expression="
							+ cmd + ")");

		return (FeatureModel) cmd;
	}

}
