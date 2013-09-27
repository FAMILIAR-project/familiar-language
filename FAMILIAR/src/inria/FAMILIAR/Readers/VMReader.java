package inria.FAMILIAR.Readers;

import inria.FAMILIAR.Model.AttributedFeatureModel;
import inria.FAMILIAR.Model.Domain.Cardinality;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

//import inria.FAMILIAR.Model.Relation;
import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;
import fr.inria.lang.vm.VMStandaloneSetup;
import fr.inria.lang.vm.vM.Feature;
import fr.inria.lang.vm.vM.FeatureDefinition;
import fr.inria.lang.vm.vM.FeatureHierarchy;
import fr.inria.lang.vm.vM.FeaturesGroup;
import fr.inria.lang.vm.vM.Model;
import fr.inria.lang.vm.vM.Orgroup;
import fr.inria.lang.vm.vM.Relationships;
import fr.inria.lang.vm.vM.Xorgroup;

public class VMReader implements IReader {

	@Override
	public AttributedFeatureModel parseFile(String fileName) throws Exception {
		// AttributedFeatureModel res = new AttributedFeatureModel();
		// new
		// org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
		Injector injector = new VMStandaloneSetup()
				.createInjectorAndDoEMFRegistration();
		XtextResourceSet resourceSet = injector
				.getInstance(XtextResourceSet.class);
		resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL,
				Boolean.TRUE);
		Resource resource = resourceSet.getResource(URI.createURI(fileName),
				true);
		Model model = (Model) resource.getContents().get(0);
		System.out.println("Obtaining relationships...");
		Relationships rel = model.getVm().getRelationships();

		FeatureHierarchy fhs = rel.getRoot();
		inria.FAMILIAR.Model.Feature ffeat = new inria.FAMILIAR.Model.Feature(
				fhs.getParent().getName());
		visitFeatureHierarchy(ffeat, fhs);

		inria.FAMILIAR.Model.AttributedFeatureModel fm = new AttributedFeatureModel();
		fm.setRoot(ffeat);
		return fm;
	}

	/**
	 * @param fh
	 */
	private void visitFeatureHierarchy(inria.FAMILIAR.Model.Feature ffeatroot,
			FeatureHierarchy fh) {

		System.out.println("FeatureHierarchy Parent:"+ fh.getParent().getName());
		EList<FeatureDefinition> fhchildren = fh.getChildren();
		for (FeatureDefinition fd : fhchildren) {

			inria.FAMILIAR.Model.Relation frel = new inria.FAMILIAR.Model.Relation();

			if (fd instanceof Feature) {
				fr.inria.lang.vm.vM.Feature f = (fr.inria.lang.vm.vM.Feature) fd;
				if (f.isOptional()) {
					frel.addCardinality(new Cardinality(0, 1));
				} else if (!f.isOptional()) {// isMandatory
					frel.addCardinality(new Cardinality(1, 1));
				}
				frel.addDestination(new inria.FAMILIAR.Model.Feature(f
						.getName()));
				ffeatroot.addRelation(frel);

			} else if (fd instanceof FeaturesGroup) {
				frel.addCardinality(new Cardinality(1, 1));

				FeaturesGroup group = (FeaturesGroup) fd;
				EList<FeatureDefinition> groupedFeatures = group
						.getGroupedFeatures();
				int maxCard = 0;
				for (FeatureDefinition fdef : groupedFeatures) {
					maxCard++;

					if (fdef instanceof Feature) {
						inria.FAMILIAR.Model.Feature feat = new inria.FAMILIAR.Model.Feature(
								((Feature) fdef).getName());
						frel.addDestination(feat);
					} else if (fdef instanceof FeatureHierarchy) {
						inria.FAMILIAR.Model.Feature feat = new inria.FAMILIAR.Model.Feature(
								((FeatureHierarchy) fdef).getParent().getName());
						frel.addDestination(feat);
						visitFeatureHierarchy(feat, (FeatureHierarchy) fdef);
					}
				}
				if (fd instanceof Xorgroup) {
					frel.addCardinality(new Cardinality(1, 1));
				} else if (fd instanceof Orgroup) {
					frel.addCardinality(new Cardinality(1, maxCard));
				}
				ffeatroot.addRelation(frel);

			} else if (fd instanceof FeatureHierarchy) {
				String dest = ((FeatureHierarchy) fd).getParent().getName();
				inria.FAMILIAR.Model.Feature fdest = new inria.FAMILIAR.Model.Feature(
						dest);

				frel.addDestination(fdest);
				ffeatroot.addRelation(frel);

				if (((FeatureHierarchy) fd).getParent().isOptional()) {
					frel.addCardinality(new Cardinality(0, 1));

				} else if (!((FeatureHierarchy) fd).getParent().isOptional()) {
					frel.addCardinality(new Cardinality(1, 1));

				}

				visitFeatureHierarchy(fdest, (FeatureHierarchy) fd);
			}
		}
	}

	@Override
	public AttributedFeatureModel parseString(String data) throws Exception {
		// Not implemented currently.
		return null;
	}

	@Override
	public boolean canParse(String fileName) {
		// TODO Auto-generated method stub
		return true;
	}

}
