package be.cleve.anthony;


import ie.lero.spl.fmprimitives.AlternativeGroup;
import ie.lero.spl.fmprimitives.Feature;
import ie.lero.spl.fmprimitives.FeatureGroup;
import ie.lero.spl.fmprimitives.FeatureHasMandatorySubfeature;
import ie.lero.spl.fmprimitives.FeatureHasSubfeature;
import ie.lero.spl.fmprimitives.FeatureModel;
import ie.lero.spl.fmprimitives.FeatureModelPrimitive;
import ie.lero.spl.fmprimitives.FmprimitivesPackage;
import ie.lero.spl.fmprimitives.GroupHasChild;
import ie.lero.spl.fmprimitives.GroupHasParent;
import ie.lero.spl.fmprimitives.OrGroup;
import ie.lero.spl.fmprimitives.Requires;

import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;



public class FormatConverter {
	
	
	
	static Map<Feature, String> _ftNames = new HashMap<Feature, String>();
	
	static int _id = 1;

	public static FeatureModel loadFeatureModel(URI fromURI) {

		try {
			EcoreRegistering.registerPackages(URI.createURI("http://lero.ie/spl/fmprimitives.ecore"));
		} catch (NotValidEPackageURIException e) {
			e.printStackTrace();
		}
		
		ResourceSet resourceSet = new ResourceSetImpl();	
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
		Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		// AM: strange: java.lang.ClassCastException: org.xtext.example.mydsl.fmprimitives.impl.FmprimitivesFactoryImpl cannot be cast to ie.lero.spl.fmprimitives.FmprimitivesFactory
	    resourceSet.getPackageRegistry().put(FmprimitivesPackage.eNS_URI, FmprimitivesPackage.eINSTANCE); 
		Resource resource = resourceSet.getResource(fromURI, true);
		FeatureModel featureModel = null;
		try {
			featureModel = (FeatureModel)
			EcoreUtil.getObjectByType(resource.getContents(),
			FmprimitivesPackage.eINSTANCE.getFeatureModel());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			resourceSet.getResources().remove(resource);
		}
		return featureModel;
	}
	
	public static String getSafeFeatureName(Feature feature) {
			return _ftNames.get(feature) ;
	}

	public static String toFamiliarFeatureModel(String filePath){
		

		
		
		FeatureModel featureModel = loadFeatureModel(URI.createFileURI(filePath));
		
		// we pre-compute names associated to features (uniqueness required *at the moment* in FML)
		computeNames(featureModel);
		
		String famFM = "FM (";
		EList<Feature> featureList = featureModel.getFeatures();
		ListIterator<Feature> featureIter = featureList.listIterator();
		while (featureIter.hasNext()){
			Feature feature = featureIter.next();
			EList<GroupHasParent> parentGroupList = feature.getGroupHasParent();
			ListIterator<GroupHasParent> parentGroupIter = parentGroupList.listIterator();
			if (parentGroupIter.hasNext()){
				// OR or XOR decomposition
				while (parentGroupIter.hasNext()){
					GroupHasParent parentGroup = parentGroupIter.next();
					FeatureGroup group = parentGroup.getGroup();
					EList<GroupHasChild> childGroupList = group.getGroupHasChild();
					ListIterator<GroupHasChild> childGroupIter = childGroupList.listIterator();
					int i = 0;
					famFM+="\n"+getSafeFeatureName(feature)+" : ";
					
					// AM: it seems possible to use an Or/Alternative group with only one feature;
					// TODO => my interpretation: mandatory feature
					boolean notRealXor = (group instanceof AlternativeGroup || group instanceof OrGroup) && childGroupList.size() == 1;
					if (!notRealXor)
						famFM+="(";
					
					
					
					while (childGroupIter.hasNext()){
						GroupHasChild childGroup = childGroupIter.next();
						Feature childFeature = childGroup.getChild();
						if (i>0){
							famFM+=" | ";
						}
						famFM+=getSafeFeatureName(childFeature);
						i++;
					}
					if (group instanceof OrGroup && !notRealXor){
						famFM+=")+;";
					}else{
						if (!notRealXor)
							famFM+=")";
						famFM += ";" ;
					}	
				}
			}else{
				EList<FeatureHasSubfeature> subFeatureList = feature.getFeatureHasSubfeature();
				ListIterator<FeatureHasSubfeature> subFeatureIter = subFeatureList.listIterator();
				if (subFeatureIter.hasNext()){
					//  AND decomposition
					famFM+="\n"+getSafeFeatureName(feature)+" : ";
					while (subFeatureIter.hasNext()){
						FeatureHasSubfeature hasSubFeature = subFeatureIter.next();
						Feature subFeature = hasSubFeature.getSubfeature();
						if(hasSubFeature instanceof FeatureHasMandatorySubfeature){
							// mandatory subfeature
							famFM+=" "+getSafeFeatureName(subFeature);
						}else{
							// optional subfeature
							famFM+=" ["+getSafeFeatureName(subFeature)+"]";
						}
					}
					famFM+=";";
				}
				
			}
			
				
		}
		// adding the requires constraints...
		EList<FeatureModelPrimitive> fmPrimitiveList = featureModel.getPrimitives();
		ListIterator<FeatureModelPrimitive> fmPrimitiveIter = fmPrimitiveList.listIterator();
		while (fmPrimitiveIter.hasNext()){
			FeatureModelPrimitive fmPrimitive = fmPrimitiveIter.next();
			if (fmPrimitive instanceof Requires){
				Requires requires = (Requires) fmPrimitive;
				// for the moment, we assume requires constraints of the form F1 -> F2 
				// to do: consider more complex requires constraints
				Feature fromFeature = requires.getSources().get(0);
				Feature toFeature = requires.getTargets().get(0);
				famFM+="\n(!"+getSafeFeatureName(fromFeature)+" | "+getSafeFeatureName(toFeature)+");";
			}
		}
		famFM += "\n)";

		return famFM;
	}
	
	private static void computeNames(FeatureModel featureModel) {
		reset();
		
		EList<Feature> fts = featureModel.getFeatures();
		
		for (Feature ft : fts) {
			String identifier = ft.getId();
			String oname = ft.getName();
			
			String safeSyntacticalName = "";
			if (identifier.startsWith("@PLUG")){
				safeSyntacticalName = "_"+ oname.replace("-", "_").replace("/", "_");
			}else{
				safeSyntacticalName = oname.replace("-", "_").replace("/", "_");
			}
			
			if (_ftNames.containsValue(safeSyntacticalName)) {
				safeSyntacticalName = safeSyntacticalName.trim() + "_" + _id++;
			}
			
			_ftNames.put(ft, safeSyntacticalName);
		}
		
		
		
	}

	private static void reset() {
		_ftNames = new HashMap<Feature, String>();
		_id = 1;
	}
	
	
}
