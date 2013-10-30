package fr.familiar.attributedfm.readers;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;



//import inria.FAMILIAR.Model.Relation;
import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;
import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.domain.Cardinality;
import fr.inria.lang.VMStandaloneSetup;
import fr.inria.lang.vM.AttrDef;
import fr.inria.lang.vM.Attributes;
import fr.inria.lang.vM.BooleanAttrDef;
import fr.inria.lang.vM.ComplexExpression;
import fr.inria.lang.vM.Constraint;
import fr.inria.lang.vM.Constraints;
import fr.inria.lang.vM.Feature;
import fr.inria.lang.vM.FeatureDefinition;
import fr.inria.lang.vM.FeatureHierarchy;
import fr.inria.lang.vM.FeaturesGroup;
import fr.inria.lang.vM.IntegerAttrDefBounded;
import fr.inria.lang.vM.IntegerAttrDefUnbounded;
import fr.inria.lang.vM.LeftImplication;
import fr.inria.lang.vM.Model;
import fr.inria.lang.vM.Orgroup;
import fr.inria.lang.vM.RealAttrDefBounded;
import fr.inria.lang.vM.RealAttrDefUnbounded;
import fr.inria.lang.vM.Relationships;
import fr.inria.lang.vM.RightImplication;
import fr.inria.lang.vM.StringAttrDef;
import fr.inria.lang.vM.Xorgroup;

public class VMReader implements IReader {

	@Override
	public AttributedFeatureModel parseFile(String fileName) throws Exception {
		// AttributedFeatureModel res = new AttributedFeatureModel();
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
		Relationships rel = model.getVm().getRelationships();
		
		//Create features
		FeatureHierarchy fhs = rel.getRoot();
		fr.familiar.attributedfm.Feature ffeat = new fr.familiar.attributedfm.Feature(
				fhs.getParent().getName());
		visitFeatureHierarchy(ffeat, fhs);
		fr.familiar.attributedfm.AttributedFeatureModel fm = new AttributedFeatureModel();
		fm.setRoot(ffeat);
		
		//Create atts
		visitAttributes(model);
		//Create ctc
		//visitConstriants(model);
		return fm;
	}
	
	private void visitConstriants(Model model) {
		Constraints cons = model.getVm().getConstraints();
		EList<Constraint> con = cons.getConstraints();
		for (Constraint co : con) {
			ComplexExpression cex = (ComplexExpression) co.getExpression();
			if (cex instanceof RightImplication) {
				RightImplication ri = (RightImplication) cex;
				System.out.println("Do something with RightImplication"+
						" left:"+ (ComplexExpression)ri.getLeft()+
						" right:"+ (ComplexExpression)ri.getRight());
			} else if (cex instanceof LeftImplication) {
				System.out.println("Do something with RightImplication");
			}
			// System.out.println("clase"+cex.eClass().getName());
		}
	}
	private void visitAttributes(Model model) {
		Attributes atts = model.getVm().getAttributes();
		EList<AttrDef> att = atts.getAttrDefs();
		for (AttrDef at : att) {
			if (at instanceof BooleanAttrDef) {
				System.out.println("Bool att value:"+((BooleanAttrDef)at).getVal());
			}else if (at instanceof StringAttrDef) {
				System.out.println("Str att value:"+((StringAttrDef)at).getVal());
			}else if (at instanceof IntegerAttrDefBounded) {
				System.out.println("StrBounded att min:"+((IntegerAttrDefBounded)at).getMin()+" max:"+((IntegerAttrDefBounded)at).getMax());
			}else if (at instanceof IntegerAttrDefUnbounded) {
				System.out.println("StrUnBounded att val:"+((IntegerAttrDefUnbounded)at).getVal());
			}else if (at instanceof RealAttrDefBounded) {
				System.out.println("RealAttrDefBounded att val:"+((RealAttrDefBounded)at).getMin()+" max:"+((RealAttrDefBounded)at).getMax()+" delta: "+((RealAttrDefBounded)at).getDelta());
			}else if (at instanceof RealAttrDefUnbounded) {
				System.out.println("RealAttrDefUnbounded att val:"+((RealAttrDefUnbounded)at).getVal());
			}
		}
	}
	

	/**
	 * @param fh
	 */
	private void visitFeatureHierarchy(fr.familiar.attributedfm.Feature ffeatroot,
			FeatureHierarchy fh) {
		EList<FeatureDefinition> fhchildren = fh.getChildren();
		for (FeatureDefinition fd : fhchildren) {

			fr.familiar.attributedfm.Relation frel = new fr.familiar.attributedfm.Relation();

			if (fd instanceof Feature) {
				fr.inria.lang.vM.Feature f = (fr.inria.lang.vM.Feature) fd;
				if (f.isOptional()) {
					frel.addCardinality(new Cardinality(0, 1));
				} else if (!f.isOptional()) {// isMandatory
					frel.addCardinality(new Cardinality(1, 1));
				}
				frel.addDestination(new fr.familiar.attributedfm.Feature(f
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
						fr.familiar.attributedfm.Feature feat = new fr.familiar.attributedfm.Feature(
								((Feature) fdef).getName());
						frel.addDestination(feat);
					} else if (fdef instanceof FeatureHierarchy) {
						fr.familiar.attributedfm.Feature feat = new fr.familiar.attributedfm.Feature(
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
				fr.familiar.attributedfm.Feature fdest = new fr.familiar.attributedfm.Feature(
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
