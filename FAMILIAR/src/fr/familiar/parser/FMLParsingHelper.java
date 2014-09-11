package fr.familiar.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.xtext.example.mydsl.FMLStandaloneSetup;
import org.xtext.example.mydsl.fML.FamiliarScript;

import com.google.inject.Injector;

/**
 * largely inspired from ParseHelper (Xtext/Junit utility)
 * @author macher  
 * @param <T>
 */
public class FMLParsingHelper {

	// TODO fix it and use dependency injection
	private static Injector injector = new FMLStandaloneSetup().createInjectorAndDoEMFRegistration();
	
	private IResourceFactory resourceFactory ;
	private XtextResourceSet resourceSet ;
	
	public FMLParsingHelper() {
		_injection() ;
	}
	
	private void _injection() {
		resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
		
	}

	public FamiliarScript parse(InputStream in, URI uriToUse, Map<?,?> options, ResourceSet resourceSet) {
		
		Resource resource = resourceFactory.createResource(uriToUse);
		resourceSet.getResources().add(resource);
		try {
			resource.load(in, options);
			final FamiliarScript root = (FamiliarScript) (resource.getContents().isEmpty()? null : resource.getContents().get(0));
			return root;
		} catch (IOException e) {
			throw new WrappedException(e);
		}
	}
	
	public FamiliarScript parse(String text) throws Exception {
		return parse(text, resourceSet);
	}
	
	public FamiliarScript parse(String text, ResourceSet resourceSetToUse) throws Exception {
		return parse(getAsStream(text), computeUnusedUri(resourceSetToUse),null, resourceSetToUse);
	}
	
	protected URI computeUnusedUri(ResourceSet resourceSet) {
		String name = "__synthetic";
		for (int i = 0; i < Integer.MAX_VALUE; i++) {
			URI syntheticUri = URI.createURI(name+i+".uri");
			if (resourceSet.getResource(syntheticUri, false)==null)
				return syntheticUri;
		}
		throw new IllegalStateException();
	}
	protected InputStream getAsStream(String text) {
		return new StringInputStream(text);
	}

}
