package be.ac.fundp.info.TVLParser.Util;

import java.util.Map;

import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;

public class FeatureTree {
	
	private FeatureSymbol root;

	public FeatureTree() {
	}

	public FeatureSymbol getRoot() {
		return root;
	}

	public void setRoot(FeatureSymbol root) {
		this.root = root;
	}
	
	
	//TODO : Avoid exposing the tree
	public FeatureSymbol findFeatureByLongId(String longFeatureName) throws Exception {
		String[] names = longFeatureName.split("\\.");
		
		if(names.length > 0){
			FeatureSymbol f ;
			
			if(root.getID().equals(names[0])) f = root ;
			else throw new Exception("Inexistent root : " + longFeatureName);
			boolean isRoot = true;
			for(String name : names){
				if(!isRoot) {
					f = f.getChildrenFeature(name) ; 
					if (f == null) throw new Exception("Inexistent feature : "+ longFeatureName);
				}
				else isRoot = false ;
			}
			return f;
		}
		
		throw new Exception("Inexistent feature : empty name ?");
	}
	
	public FeatureSymbol findFeatureByShortId(String shortFeatureName) {
		return findFeatureByShortId(shortFeatureName, root);
	}
	
	private FeatureSymbol findFeatureByShortId(String shortFeatureName, FeatureSymbol start) {
		
		if(start.getID().equals(shortFeatureName)) return start;
		if(start.getChildrenFeature(shortFeatureName) != null) return start.getChildrenFeature(shortFeatureName);
		else {
			Map<String, FeatureSymbol> children = start.getChildrenFeatures();
			if(children != null) {
				FeatureSymbol result = null;
				for(FeatureSymbol child : children.values()){
					result = findFeatureByShortId(shortFeatureName, child);
					if(result != null) return result ;
				}
			}
		}
		return null ;	
	}
	
	
	public FeatureSymbol findFeatureByDimacsId(int id) throws Exception {
		return findFeatureByDimacsId(id,root);
	}
	
	private FeatureSymbol findFeatureByDimacsId(int id, FeatureSymbol start) throws Exception {
		if(start.getDIMACS_ID() == id) return start;
		else {
			Map<String, FeatureSymbol> children = start.getChildrenFeatures();
			if(children != null) {
				FeatureSymbol result = null;
				for(FeatureSymbol child : children.values()){
					result = findFeatureByDimacsId(id, child);
					if(result != null) return result ;
				}
			}
		}
		return null ;	
	}
	
	
}