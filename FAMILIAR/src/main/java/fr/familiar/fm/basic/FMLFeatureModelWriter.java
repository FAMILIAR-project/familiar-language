/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */
package fr.familiar.fm.basic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.xtext.parsetree.reconstr.Serializer;
import org.eclipse.xtext.resource.XtextResource;
import org.xtext.example.mydsl.FmlStandaloneSetup;
import org.xtext.example.mydsl.fml.FeatureModel;

import com.google.inject.Injector;

/**
 * @author mathieuacher
 * 
 */
public class FMLFeatureModelWriter {

	private FeatureModel fm;

	/**
	 * 
	 */
	public FMLFeatureModelWriter(FeatureModel fm) {
		this.fm = fm;

	}

	/*
	 * @return an XMI resource conformance to metamodel automatically generated
	 * by Xtext
	 */

	public XMIResource toXMI(String output) {

		new FmlStandaloneSetup().createInjectorAndDoEMFRegistration();
		URI domainModelURI = URI.createURI(output);
		if (domainModelURI.fileExtension() == null)
			domainModelURI = domainModelURI.appendFileExtension("xmi");

		XMIResource xmiRes = (XMIResource) new XMIResourceFactoryImpl()
				.createResource(domainModelURI);

		try {
			xmiRes.getContents().add(fm);
		} catch (Exception ex) {
		}

		try {
			xmiRes.save(null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return xmiRes;

	}

	/*
	 * textual conversion using Xtext facilities to translate back to text
	 * source
	 */

	@Deprecated
	public Resource toFile(String output, EObject eo) {

		Injector injector = new FmlStandaloneSetup()
				.createInjectorAndDoEMFRegistration();

		ResourceSet rs = injector.getInstance(ResourceSet.class); // new
																	// ResourceSetImpl();
		URI domainModelURI = URI.createURI(output);
		if (domainModelURI.fileExtension() == null)
			domainModelURI = domainModelURI.appendFileExtension("fml");

		eo = fm;

		XtextResource res = (XtextResource) rs.createResource(domainModelURI);
		// rs.getResource(domainModelURI, true);
		System.out.println("res=" + res);
		res.getContents().add(eo);
		System.out.println("res=" + res);

		res.setSerializer(injector.getInstance(Serializer.class));
		try {
			res.load(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("res=" + res);

		if (res.getErrors().size() > 0)
			System.err.println(res.getErrors().toString());

		OutputStream sb = null;
		try {
			sb = new ByteArrayOutputStream();
			res.save(sb, null);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("sb=" + sb);
		/*
		 * try { resource.save(null); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		return res;

	}

	@Override
	public String toString() {
		return new FeatureModelStringBuilder(fm).toString();
	}

	public String toStringXMI() {

		new FmlStandaloneSetup().createInjectorAndDoEMFRegistration();
		URI domainModelURI = URI.createURI("dummy:/foo.xmi");
		if (domainModelURI.fileExtension() == null)
			domainModelURI = domainModelURI.appendFileExtension("xmi");

		XMIResource xmiRes = (XMIResource) new XMIResourceFactoryImpl()
				.createResource(domainModelURI);

		try {
			xmiRes.getContents().add(fm);
		} catch (Exception ex) {
		}

		OutputStream outputStream = new ByteArrayOutputStream();

		try {
			xmiRes.save(outputStream, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return outputStream.toString();

	}

}
