package fr.familiar.attributedfm.readers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;




//import fr.familiar.attributedfm.Relation;
import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;
import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.attributedfm.GenericAttribute;
import fr.familiar.attributedfm.StringDomainIntConverter;
import fr.familiar.attributedfm.domain.BooleanDomain;
import fr.familiar.attributedfm.domain.Cardinality;
import fr.familiar.attributedfm.domain.Range;
import fr.familiar.attributedfm.domain.RangeIntegerDomain;
import fr.familiar.attributedfm.domain.RangeRealDomain;
import fr.familiar.attributedfm.domain.RealRange;
import fr.familiar.attributedfm.domain.SetRealDomain;
import fr.familiar.attributedfm.domain.StringDomain;
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
import fr.inria.lang.vM.Model;
import fr.inria.lang.vM.Orgroup;
import fr.inria.lang.vM.RealAttrDefBounded;
import fr.inria.lang.vM.RealAttrDefUnbounded;
import fr.inria.lang.vM.Relationships;
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

		// Create features
		FeatureHierarchy fhs = rel.getRoot();
		fr.familiar.attributedfm.Feature ffeat = new fr.familiar.attributedfm.Feature(
				fhs.getParent().getName());
		visitFeatureHierarchy(ffeat, fhs);
		fr.familiar.attributedfm.AttributedFeatureModel fm = new AttributedFeatureModel();
		fm.setRoot(ffeat);

		// Create atts
		visitAttributes(model, fm);
		// Create ctc
		visitConstriants(model);
		return fm;
	}

	private void visitConstriants(Model model) {
		Constraints cons = model.getVm().getConstraints();
		EList<Constraint> con = cons.getConstraints();
		
		System.out.println(con);for (Constraint co : con) {
			ComplexExpression cex = (ComplexExpression) co.getExpression();
			System.out.println(cex);
//			if (cex instanceof RightImplication) {
//				RightImplication ri = (RightImplication) cex;
//				System.out.println("Do something with RightImplication"
//						+ " left:" + (ComplexExpression) ri.getLeft()
//						+ " right:" + (ComplexExpression) ri.getRight());
//			} else if (cex instanceof LeftImplication) {
//				System.out.println("Do something with RightImplication : "
//						+ cex);
//			}

		}
	}

	private void visitAttributes(Model model, AttributedFeatureModel fm) {
		Attributes atts = model.getVm().getAttributes();
		EList<AttrDef> att = atts.getAttrDefs();
		for (AttrDef at : att) {
			if (at instanceof BooleanAttrDef) {
				String fname = ((BooleanAttrDef) at).getName().getHead()
						.getOwnedFeature().getName();
				String name = ((BooleanAttrDef) at).getName().getName();
				Boolean val = Boolean.parseBoolean(((BooleanAttrDef) at)
						.getVal());

				fr.familiar.attributedfm.Feature feature = fm
						.searchFeatureByName(fname);
				GenericAttribute attribute = new GenericAttribute(name,
						new BooleanDomain(), !val, val);
				feature.addAttribute(attribute);

			} else if (at instanceof StringAttrDef) {
				String fname = ((StringAttrDef) at).getName().getHead()
						.getOwnedFeature().getName();
				String name = ((StringAttrDef) at).getName().getName();
				String val = ((StringAttrDef) at).getVal();

				fr.familiar.attributedfm.Feature feature = fm.searchFeatureByName(fname);
				GenericAttribute attribute = new GenericAttribute(name,	new StringDomain(new StringDomainIntConverter(), val),null, val);
				feature.addAttribute(attribute);

			} else if (at instanceof IntegerAttrDefBounded) {
				String fname = ((IntegerAttrDefBounded) at).getName().getHead().getOwnedFeature().getName();
				String name = ((IntegerAttrDefBounded) at).getName().getName();
				Integer min = Integer.parseInt(((IntegerAttrDefBounded) at).getMin());
				Integer max = Integer.parseInt(((IntegerAttrDefBounded) at).getMax());

				fr.familiar.attributedfm.Feature feature = fm.searchFeatureByName(fname);
				Collection<Range> ranges = new ArrayList<Range>();
				ranges.add(new Range(min, max));
				GenericAttribute attribute = new GenericAttribute(name,	new RangeIntegerDomain(ranges), 0, 0);
				feature.addAttribute(attribute);

			} else if (at instanceof IntegerAttrDefUnbounded) {


				String fname = ((IntegerAttrDefUnbounded) at).getName()
						.getHead().getOwnedFeature().getName();
				String name = ((IntegerAttrDefUnbounded) at).getName()
						.getName();
				Integer integer = Integer
						.parseInt(((IntegerAttrDefUnbounded) at).getVal()
								.getValue());

				Collection<Range> ranges = new ArrayList<Range>();
				ranges.add(new Range(-21474836, 21474836));

				fr.familiar.attributedfm.Feature feature = fm
						.searchFeatureByName(fname);
				GenericAttribute attribute = new GenericAttribute(name,
						new RangeIntegerDomain(ranges), 0, integer);
				feature.addAttribute(attribute);

			} else if (at instanceof RealAttrDefBounded) {
				String fname = ((RealAttrDefBounded) at).getName().getHead().getOwnedFeature().getName();
				String name = ((RealAttrDefBounded) at).getName().getName();
				float min = Float.parseFloat(((RealAttrDefBounded) at).getMin());
				float max = Float.parseFloat(((RealAttrDefBounded) at).getMax());
				fr.familiar.attributedfm.Feature feature = fm.searchFeatureByName(fname);
				GenericAttribute attribute;
				if (((RealAttrDefBounded) at).getDelta() != null) {
					float delta = Float.parseFloat(((RealAttrDefBounded) at)
							.getDelta());
					// The magic of real 2 integers happens here
					Set<Float> ranges = new HashSet<Float>();
					for (float i = min; i < max; i += delta) {
						ranges.add(i);
					}
					 attribute = new GenericAttribute(name,	new SetRealDomain(ranges), 0, 0);
				}else{
					Collection<RealRange> ranges = new ArrayList<RealRange>();
					ranges.add(new RealRange(-21474837, 21474835));
					 attribute = new GenericAttribute(name,	new RangeRealDomain(ranges), 0, 0);
				}


				feature.addAttribute(attribute);

			} else if (at instanceof RealAttrDefUnbounded) {
			
				// Reason over that without a delta is gonnna be toooooooooo
				// expensive.

				String fname = ((RealAttrDefUnbounded) at).getName().getHead()
						.getOwnedFeature().getName();
				String name = ((RealAttrDefUnbounded) at).getName().getName();
				float val = Float.parseFloat(((RealAttrDefUnbounded) at)
						.getVal());

				Collection<RealRange> ranges = new ArrayList<RealRange>();
				ranges.add(new RealRange(-21474837, 21474835));

				fr.familiar.attributedfm.Feature feature = fm
						.searchFeatureByName(fname);
				GenericAttribute attribute = new GenericAttribute(name,
						new RangeRealDomain(ranges), 0, val);
				feature.addAttribute(attribute);

			}// else {
				//System.out.println(att); // TODO decide what to do with the rest
			//}

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
