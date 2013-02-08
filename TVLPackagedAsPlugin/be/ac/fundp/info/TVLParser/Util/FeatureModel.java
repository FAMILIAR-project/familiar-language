package be.ac.fundp.info.TVLParser.Util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.sat4j.core.VecInt;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.fundp.info.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;

/**
 * FeatureModel exposes the operations needed to configure a feature model.
 * 
 * - isSelectable / isUnSelectable
 * - isSelected / isUnselected / isUnassigned
 * - include / exclude / unassign
 * - explain
 * 
 * - getUserChoices 
 * - getModel
 * 
 * @author rm
 *
 */
public class FeatureModel {
		
		protected Solver solver = null;
		protected FeatureTree tree = new FeatureTree();

		private final List<Integer> fixedIds ;
		private final List<Integer> userChoices ;

		private TVLParser parser = null;
		
		/**
		 * Builds a new feature model
		 * 
		 * @param input Can either be the fullText TVL diagram or the path to a file containing the TVL code 
		 * @param fullText is set to true when input contains the tvl code, false when it contains the path to a file
		 * @throws ContradictionException
		 * @throws UnsatisfiableModelException
		 * @throws IOException
		 */
		public FeatureModel(String input, boolean fullText) throws ContradictionException, UnsatisfiableModelException, IOException {
			if(fullText) {
				parser = new TVLParser(input);
			}
			else {
				parser = new TVLParser(new File(input));
			}
			this.tree.setRoot(parser.getRoot()); 
			solver = new Solver(tree.getRoot());
			fixedIds = new ArrayList<Integer>();
			userChoices = new ArrayList<Integer>();
		}
		
		// TODO: Change this to avoid exposure of Parser and Solver objects
		public FeatureModel(FeatureSymbol root) throws ContradictionException, UnsatisfiableModelException {
			this.tree.setRoot(root); 
			solver = new Solver(root);
			fixedIds = new ArrayList<Integer>();
			userChoices = new ArrayList<Integer>();
		}
		
		
		// TODO: Avoid exposure of solver exceptions
		public boolean hasAtLeastOneSolution() throws TimeoutException, ContradictionException {
			return isSatisfiable();	
		}


		private boolean isSatisfiable() throws TimeoutException, ContradictionException {
			return solver.isSatisfiable(getAssumptions());
		}
		
		private boolean isSatisfiable(int id) throws TimeoutException, ContradictionException {
			VecInt assumptions = getAssumptions();
			assumptions.push(id);
			return solver.isSatisfiable(assumptions);
		}

		private VecInt getAssumptions() {
			VecInt assumptions = new VecInt();
			for(int i : this.userChoices){
				assumptions.push(i);
			}
			return assumptions;
		}
		
		
		private List<List<FDElement>> explain(int id) throws Exception {
			 List<List<Integer>> explanation = solver.explain(id, this.fixedIds) ;
		 
			 List<List<FDElement>> solutions = new ArrayList<List<FDElement>>();
			 for(List<Integer> constr : explanation) {
				 List<FDElement> constrExpl = new ArrayList<FDElement>();
				 for(Integer dimacsId : constr) {
					 constrExpl.add(makeFDElement(tree.findFeatureByDimacsId(Math.abs(dimacsId))));
				 }
				 solutions.add(constrExpl);
			 }
			 return solutions; 
		}
		
		/*
		 *********************************************************************************************
		 *  isXXX ?
		 * 
		 *******************************************************************************************
		 */
		
		/**
		 * Returns true if the feature corresponding to the given name can be selected or is already selected, false otherwise
		 * 
		 * @param featureName The name of the feature
		 * @param longName indicates whether the name is fully qualified or not
		 * @throws Exception
		 */
		public boolean isSelectable(String featureName, boolean longName) throws Exception{
			return isSelectable(findFeature(featureName, longName)) ;
		}
		
		/**
		 * Returns true if the given feature can be selected or is already selected, false otherwise
		 * 
		 * @param feature
		 * @return
		 * @throws TimeoutException
		 * @throws ContradictionException
		 */
		public boolean isSelectable(FeatureSymbol feature) throws TimeoutException, ContradictionException {
			if(feature != null) {
				final int id = feature.getDIMACS_ID();
				if(fixedIds.contains(Integer.valueOf(id))) {
					return true;
				}
				else {
					return isSatisfiable(id);
				}
			}
			return false;
		}

		public boolean isUnSelectable(String featureName, boolean longName) throws Exception{
			return isUnSelectable(findFeature(featureName, longName)) ;
		}
		
		public boolean isUnSelectable(FeatureSymbol feature) throws TimeoutException, ContradictionException {
			if(feature != null) {
				final int id = feature.getDIMACS_ID() ;
				// Value is already excluded 
				if(fixedIds.contains(Integer.valueOf(-id))) return true;
				
				// Value was included as the result of a propagation 
				if(fixedIds.contains(Integer.valueOf(id)) && !userChoices.contains(Integer.valueOf(id))) return false;
					
				return isSatisfiable(-feature.getDIMACS_ID());
			}
			return false;
		}
		
		public boolean isIncluded(String featureName, boolean longName) throws Exception{
			return isIncluded(longName? tree.findFeatureByLongId(featureName) : tree.findFeatureByShortId(featureName)) ;
		}
		
		public boolean isIncluded(FeatureSymbol feature) {
			return fixedIds.contains(feature.getDIMACS_ID());
		}
		
		public boolean isExcluded(String featureName, boolean longName) throws Exception{
			return isExcluded(longName ? tree.findFeatureByLongId(featureName) : tree.findFeatureByShortId(featureName)) ;
		}
			
		public boolean isExcluded(FeatureSymbol feature){
			return fixedIds.contains(-feature.getDIMACS_ID());
		}
		

		public boolean isUnassigned(String featureName, boolean longName) throws Exception {
			return isUnassigned(longName ? tree.findFeatureByLongId(featureName) : tree.findFeatureByShortId(featureName)) ;
		}
		
		public boolean isUnassigned(FeatureSymbol feature){
			return (!isIncluded(feature) && !isExcluded(feature)) ;
		}
		
		
		
		/*
		 *********************************************************************************************
		 * Actions (include, exclude, unassign, explain)
		 * 
		 *******************************************************************************************
		 */
	

		/**
		 * Includes a feature in the configuration
		 * 
		 * @param featureName the name of the feature
		 * @param longName is set to true when the full dotted notation is used to specify the name of the feature
		 * @return the list of features of which the state has changed
		 * @throws Exception
		 */
		public List<FDElement> include(String featureName, boolean longName) throws Exception {
			final FeatureSymbol fs = findFeature(featureName, longName) ;
			if(isSelectable(fs)) {
				resetFixedIds();
				this.userChoices.add(fs.getDIMACS_ID());
				
			}
			 else
				throw new ContradictionException("This feature cannot be included");
			return  makeAllFDElements(include(fs)); 
		}
		
		private List<FeatureSymbol> include(FeatureSymbol feature) throws Exception {
			return setState(feature,true);
		}
		
		
		/**
		 * Excludes a feature from the configuration
		 * 
		 * @param featureName the name of the feature
		 * @param longName is set to true when the full dotted notation is used to specify the name of the feature
		 * @return the list of features of which the state has changed
		 * @throws Exception
		 */
		public List<FDElement> exclude(String featureName, boolean longName) throws Exception {
			final FeatureSymbol fs = findFeature(featureName, longName) ;
			 if(isUnSelectable(fs)) {
			 	resetFixedIds();
			 	this.userChoices.add(-fs.getDIMACS_ID()) ;
			 }
			 else throw new ContradictionException("This feature cannot be excluded");
			return  makeAllFDElements(exclude(fs));
		}

		private List<FeatureSymbol> exclude(FeatureSymbol feature) throws Exception {
			return setState(feature,false);
		}
		
		
		/**
		 * Unassigns a feature
		 * 
		 * @param featureName the name of the feature as defined in the diagram
		 * @param longName is set to true when the full dotted notation is used to specify the name of the feature
		 * @return the list of features of which the state has changed
		 * @throws Exception
		 */
		public List<FDElement> unassign(String featureName, boolean longName) throws Exception {
			final FeatureSymbol fs = findFeature(featureName, longName) ;
			return makeAllFDElements(unassign(fs));
		}
		
		
		private List<FeatureSymbol> unassign(FeatureSymbol feature) throws Exception{
			int id = feature.getDIMACS_ID() ;

			if(!isUnassigned(feature)) {
					return unassign(isIncluded(feature) ? id : -id) ;
			}
			else throw new ContradictionException("This feature is not assigned and cannot be unassigned");
			
		}
		
		private List<FeatureSymbol> unassign(int id) throws Exception {
			final Integer theId = Integer.valueOf(id) ;
			if(userChoices.contains(theId)) {
				this.userChoices.remove(theId) ;
				final ArrayList<Integer> before = new ArrayList<Integer>();
				before.addAll(fixedIds) ;
				resetFixedIds();
				final List<FeatureSymbol> modified = fullPropagate(tree.getRoot(), new ArrayList<FeatureSymbol>()) ;
				FeatureSymbol tmp;
				List<FeatureSymbol> modTmp;
				for(Integer i : before) {
					modTmp = null ;
					if((i > 0) && (!i.equals(Integer.valueOf(theId))) && !fixedIds.contains(i)) {
							tmp = tree.findFeatureByDimacsId(i); 
							
							if(isSelectable(tmp)) {
									userChoices.add(i);
									modTmp = include(tmp) ;
									
								for(FeatureSymbol fs : modTmp) {
									if(!modified.contains(fs)){
										modified.add(fs);
									}
								}
							}
					}
				}
				return mergeUnassigned(before, modified);
			}
			else {
				throw new ContradictionException("This feature has been automatically set and cannot be unassigned");
			}
			
		}
		
		private List<FeatureSymbol> setState(FeatureSymbol feature, boolean state) throws Exception {
			if(feature != null) {
				int id = feature.getDIMACS_ID();
				id = (state ? id : -id) ;
				final boolean operationAllowed = (state ? isSelectable(feature) : isUnSelectable(feature)) ;
			
				if(isUnassigned(feature) && operationAllowed){
					// Assign new state
					fixedIds.add(id);
					return fullPropagate(tree.getRoot(), new ArrayList<FeatureSymbol>());
				}
			}
			return null;		
		}
		
		private void resetFixedIds() {
			fixedIds.clear();
			fixedIds.addAll(userChoices);
		}
		

		/**
		 * This method propagates a choice, i.e. an inclusion / exclusion. 
		 * It automatically includes features that could never be excluded given the current state of the configuration 
		 * and excludes the ones that could never be included. For each of this changes, a propagation is made again.
		 * 
		 * This ensures the selection process is "backtrack-free". In other words the user will never face a "dead end".
		 * 
		 * @param feature
		 * @param modified
		 * @return
		 * @throws Exception
		 */
		private List<FeatureSymbol> fullPropagate(FeatureSymbol feature, List<FeatureSymbol> modified) throws Exception {

			 final boolean selectable = isSelectable(feature);
			 final boolean unSelectable = isUnSelectable(feature);
			 List<FeatureSymbol> sub = null;

			 if(selectable && !unSelectable && !isIncluded(feature)) {
				 //	 feature will not be unselectable, including it automatically 
				 sub = include(feature);
				 modified.add(feature);
				 if(sub != null) modified.addAll(sub);
			 }
			 
			 if(unSelectable && !selectable && !isExcluded(feature)) {
				 //	 feature will not be selectable, excluding it automatically
				 sub = exclude(feature);
				 modified.add(feature);
				 if(sub != null) modified.addAll(sub);
			 }
			
			final Map<String, FeatureSymbol> children = feature.getChildrenFeatures();
			if(children != null) {
				for(FeatureSymbol child : children.values()){
					fullPropagate(child, modified);
				}
			}
			
			return modified; 
		}
		

		/**
		 * Returns a list of elements that are involved in constraints preventing to change the state of the given feature 
		 * 
		 * @param featureName
		 * @param longName
		 * @param dontRetry : a list of element that should not be explained anymore (used for recursion, start with an empty List)
		 * @return
		 * @throws Exception
		 */
		public List<FDElement> explain(String featureName, boolean longName, List<FDElement> dontRetry) throws Exception {
			
			final FeatureSymbol fs = findFeature(featureName, longName) ;
			List<List<FDElement>> explanation = null ;
			int goal = 0;
			if(isIncluded(fs)) goal = -fs.getDIMACS_ID() ;
			
			if(isExcluded(fs)) goal = fs.getDIMACS_ID() ;
			List<FDElement> result = null;
			if(goal != 0) { 
				result = new ArrayList<FDElement>();
				explanation = explain(goal);
				
				List<FDElement> metaExplanation = explain(explanation, dontRetry);
				
			}
			return result ;
		}
		
		private List<FDElement> explain(List<List<FDElement>> explanation, List<FDElement> dontRetry) throws Exception {
			List<FDElement> uChoices = this.getUserChoices();
			List<FDElement> result = new ArrayList<FDElement>();
			List<FDElement> toExplainLater = new ArrayList<FDElement>();
			if(explanation != null) {
				for(List<FDElement> constr : explanation) {
					
					for(FDElement elem : constr) {
						if(dontRetry.indexOf(elem) < 0) {
							if(uChoices.indexOf(elem) >= 0) {
									if(result.indexOf(elem) < 0) {
										result.add(elem);
										dontRetry.add(elem);
									}						
							}
							else {
									if(elem.isSelected() || elem.isUnselected()) {
										dontRetry.add(elem);
										toExplainLater.add(elem);
									}
							} 
						}
					}
					
				}
				if(result.size() == 0) {
					for(FDElement elem : toExplainLater){
						for(FDElement sol : explain(elem.getName(), false, dontRetry)) {
							if(result.indexOf(sol) < 0) result.add(sol) ;
						}
					}
				}
				return result;
				
			}
			else {
				return null;
			}
		}

		
		
		
		private List<FDElement> makeAllFDElements(List<FeatureSymbol> list){
			final List<FDElement> result = new ArrayList<FDElement>();
			for(FeatureSymbol feature : list) {
				result.add(makeFDElement(feature));
			}
			return result; 
		}
		
		private List<FeatureSymbol> mergeUnassigned(List<Integer> before, List<FeatureSymbol> after) throws Exception {
			FeatureSymbol elem;
			final List<FeatureSymbol> result = new ArrayList<FeatureSymbol>();
			result.addAll(after);
			for(Integer i : before) {
				elem  = tree.findFeatureByDimacsId((i>0)?i:-i) ;
				
				boolean found = false ;
				for(FeatureSymbol changed : after) {
					if(changed.getID().equals(elem.getID())) {
						found = true;
						break;
					}
				}
				
				if(!found) {
					result.add(elem) ;
				}	
			}
			return result;
		}
	
		
		
		private FDElement makeFDElement(FeatureSymbol symbol) {
			return new FDElement(symbol.getID(), isIncluded(symbol), isExcluded(symbol));
		}

		
		
		


		
		private FeatureSymbol findFeature(String featureName, boolean longName)
				throws Exception {
			return (longName ? tree.findFeatureByLongId(featureName) : tree.findFeatureByShortId(featureName) );
		}
		
		
		
	
			
		
		public String flattenFeature(FeatureSymbol feature, int tabs){
			String s = "";
			for(int i=0;i < tabs;i++) s+="\t";
			s+=feature.getID() + "(" ; 
			if(!isUnassigned(feature)) s+= isIncluded(feature);
			else s+="?";
			s+=")";
			s+=" cloneable ["+feature.getMinFeatureCardinality()+".."+feature.getMaxFeatureCardinality()+"]" ;
			s+=" group ["+feature.getMinGroupCardinality()+".."+feature.getMaxGroupCardinality()+"]" ;
			s+="\n" ;
			Map<String, FeatureSymbol> children = feature.getChildrenFeatures();
			if(children != null) {
				for(FeatureSymbol child : children.values()){
					s+=flattenFeature(child, tabs+1);
				}
			}
			if(feature.hasConstraintSymbols()) {
				for(ConstraintSymbol cs : feature.getConstraintSymbols()) {
					s+="\n**" + cs.getExpression().toString() ;
				}
			}
			return s ;
		}
		public List<FDElement> getUserChoices() throws Exception {
			final List<FDElement> result = new ArrayList<FDElement>();
			for(Integer did : this.userChoices) {
				FeatureSymbol fs = tree.findFeatureByDimacsId(did);
				result.add(makeFDElement(fs));
			}
			return result ;
		}
		
		public List<FDElement> getModel() {
			return getModel(tree.getRoot());
		}
		
		public FeatureSymbol getRoot() {
			return tree.getRoot();
		}
		
		public void setModel(List<FDElement> model) throws Exception {
			for(FDElement feature : model) {
				if(feature.isSelected()) 
					include(feature.getName(),false) ;
				
				if(feature.isUnselected()) 
					exclude(feature.getName(), false);
			}
		}
		
		private List<FDElement> getModel(FeatureSymbol feature) {
			
			final List<FDElement> result = new ArrayList<FDElement>();
		
			result.add(makeFDElement(feature));
			final Map<String, FeatureSymbol> children = feature.getChildrenFeatures();
			if(children != null) {
				for(FeatureSymbol child : feature.getChildrenFeatures().values()) {
					result.addAll(getModel(child));
				}
			}
			
			return result ;
		}
			
		public String toString() {
			return flattenFeature(tree.getRoot(), 0);
		}
	
		/**
		 * Return the symbols table containing definition of the feature and attributes of the model.
		 * if an exception occurs during parsing and validation of the model, re-throw that exception 
		 * @throws Exception exception encountered by the Parser during parsing or validation of the model
		 */
		public FeaturesSymbolTable getFeaturesSymbolTable() throws Exception{
			if (this.parser.getFeaturesSymbolTable() == null || this.parser.getFeaturesSymbolTable().isEmpty()){
				this.parser.run();
			}	
			this.parser.validate();
			return this.parser.getFeaturesSymbolTable();		
		}
}
