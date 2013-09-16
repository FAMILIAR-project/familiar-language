package inria.FAMILIAR.Readers;


import inria.FAMILIAR.Model.AttributedFeatureModel;
//import inria.FAMILIAR.Model.Relation;
import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import fr.inria.lang.vm.VMStandaloneSetup;
import fr.inria.lang.vm.vM.Feature;
import fr.inria.lang.vm.vM.FeatureDefinition;
import fr.inria.lang.vm.vM.FeatureHierarchy;
import fr.inria.lang.vm.vM.Model;
import fr.inria.lang.vm.vM.Relationships;


public class VMReader implements IReader {

	@Override
	public AttributedFeatureModel parseFile(String fileName) throws Exception {
		//AttributedFeatureModel res = new AttributedFeatureModel();
		//new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new VMStandaloneSetup().createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
		Resource resource = resourceSet.getResource(
		   URI.createURI(fileName), true);
		Model model = (Model) resource.getContents().get(0);
		System.out.println("Obtaining relationships...");
		Relationships rel = model.getVm().getRelationships();
		
		EList<FeatureHierarchy> fhs = rel.getFeatureHierarchies();
		
		for(FeatureHierarchy fh :fhs){			
			visitFeatureHierarchy(fh);
//		Feature feat = new Feature("");
//		Relation rel = new Relation();
//		feat.addRelation(rel);
//		rel.addDestination(new Feature());
		}
		return null;
	}

	/**
	 * @param fh
	 */
	private void visitFeatureHierarchy(FeatureHierarchy fh) {
		System.out.println("FeatureHierarchy Parent:"+fh.getParent().getName());
		EList<FeatureDefinition> fhchildren = fh.getChildren();
		for (FeatureDefinition fd : fhchildren) {
			if (fd instanceof Feature){
				fr.inria.lang.vm.vM.Feature f = (fr.inria.lang.vm.vM.Feature) fd;
				if (f.isOptional())
					System.out.println("\t?:"+f.getName());
				else
					System.out.println("\t"+f.getName());
			}
			else if (fd instanceof FeatureHierarchy){
				
				visitFeatureHierarchy ((FeatureHierarchy) fd);
			}
		}
	}
	
	
	

	@Override
	public AttributedFeatureModel parseString(String data) throws Exception {
		//Not implemented currently.
		return null;
	}

	@Override
	public boolean canParse(String fileName) {
		// TODO Auto-generated method stub
		return true;
	}

}
