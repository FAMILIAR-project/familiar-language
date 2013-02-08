package be.ac.fundp.info.TVLParser.Util;

import be.ac.fundp.info.TVLParser.SyntaxTree.Attribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.AttributeBody;
import be.ac.fundp.info.TVLParser.SyntaxTree.BaseAttribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.Cardinality;
import be.ac.fundp.info.TVLParser.SyntaxTree.Constraint;
import be.ac.fundp.info.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ExpressionList;
import be.ac.fundp.info.TVLParser.SyntaxTree.Feature;
import be.ac.fundp.info.TVLParser.SyntaxTree.FeatureBody;
import be.ac.fundp.info.TVLParser.SyntaxTree.FeatureGroup;
import be.ac.fundp.info.TVLParser.SyntaxTree.HierarchicalFeatures;
import be.ac.fundp.info.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.InExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.NotExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeaturesGroupAlreadySpecifiedException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.EnumSetExpressionSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;
import be.ac.fundp.info.TVLParser.symbolTables.IntervalSetExpressionSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.RecordSymbol;

/**
 * This class allows to create an object which will contains the syntax tree of the normal form
 * of a feature model.
 * 
 * To be sure that the new syntax tree doesn't contain errors, parse it with a new TVL Parser.
 */
public class NormalForm {
	
	// The features symbol table of the non normalized FM.
	private FeaturesSymbolTable featuresSymbolTable;
	
	public FeaturesSymbolTable getFeaturesSymbolTable() {
		return featuresSymbolTable;
	}

	// The feature body of the root feature (in the generated syntax tree, see the grammar) 
	private FeatureBody rootFeatureBody = null;
	
	// The root feature of the generated syntax tree (see the grammar)
	private Feature rootFeature;
	
	/**
	 * This constructor creates a new NormalForm object. It will contain the syntax tree
	 * ("rootFeature") of the normal form of the root feature of "featureSymbolTable".
	 * The feature model will transformed as described in : "Introducing TVL, a comprehensive 
	 * Text-based Feature Modeling Language and its Semantics".
	 * 
	 * @param rootFeatureSymbol
	 *	    	The generated syntax tree will correspond to the normal form of the root feature of this 
	 * 			feature symbol table.
	 * 
	 * @throws ChildrenFeaturesGroupAlreadySpecifiedException
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details.
	 * 
	 * @throws ChildrenFeatureNotFoundException 
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details
	 * 
	 * @throws SymbolNotFoundException 
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details
	 * 
	 * @throws AmbiguousReferenceException 
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details 
	 */
	public NormalForm(FeaturesSymbolTable featuresSymbolTable) throws ChildrenFeaturesGroupAlreadySpecifiedException, AmbiguousReferenceException, SymbolNotFoundException, ChildrenFeatureNotFoundException {
		FeatureSymbol rootFeatureSymbol;
		this.featuresSymbolTable = featuresSymbolTable;
		rootFeatureSymbol = (FeatureSymbol) featuresSymbolTable.getFeatureSymbol(featuresSymbolTable.getRootFeatureID());
		recordSymbolPreManagement(rootFeatureSymbol);
		this.rootFeature = startConstructNormalizedSyntaxTree(rootFeatureSymbol);

	}
	
	/**
	 * Allow to transform all the struct attributes into their normal forms. This method 
	 * must be call before all other normalization methods. Otherwise, the expressions
	 * which refer to a struct attribute could use a depreciated ID. This method is also
	 * called on the children features of featureSymbol. Be careful that this method
	 * doesn't construct the normalized syntax tree. It only modifies the features symbols
	 * table in order to incorporate the normalized struct attributes. 
	 * 
	 * @param featureSymbol
	 * 		The feature symbol which may contains non normalized struct attributes.
	 */
	private void recordSymbolPreManagement(FeatureSymbol featureSymbol) {
		if (featureSymbol.hasAttributesSymbols()) {
			int i = 0;
			Object[] featuresAttributesArray = featureSymbol.getAttributesSymbols().keySet().toArray(); 
			while (i <= featuresAttributesArray.length -1) {
				AttributeSymbol attributeSymbol = featureSymbol.getAttributeSymbol(featuresAttributesArray[i].toString());
				if (attributeSymbol.isARecordSymbol()) {
					RecordSymbol recordSymbol = (RecordSymbol) attributeSymbol;
					Object[] recordAttributesArray = recordSymbol.getAttributeSymbols().keySet().toArray();
					AttributeSymbol recordAttribute; 
					int j = 0;
					while (j <= recordAttributesArray.length-1) {
						recordAttribute = recordSymbol.getAttributeSymbol(recordAttributesArray[j].toString());
						String tempAttributeID = recordSymbol.getID()+"_"+recordAttribute.getID();
						while (featureSymbol.containsAttributeSymbol(tempAttributeID)) {
							tempAttributeID = tempAttributeID.concat("S");
						}
						recordAttribute.setID(tempAttributeID);
						featureSymbol.addAttribute(recordAttribute);
						j++;
					}
				}
				i++;
			}
		}
		if (featureSymbol.hasChildrenFeatures()) {
			int i = 0;
			Object[] featuresChildrenArray = featureSymbol.getChildrenFeatures().keySet().toArray();
			while (i <= featuresChildrenArray.length-1) {
				recordSymbolPreManagement(featureSymbol.getChildrenFeature(featuresChildrenArray[i].toString()));
				i++;
			}
		}
	}
	
	/**
	 * This method allows to transform an AttributeSymbol into its normalized form (respecting 
	 * the syntax tree format). Each clause (attribute domain, value specification,...) is 
	 * converted into a constraint which is added to the feature body ( this.rootFeatureBody ) 
	 * of the root feature. Finally, this method returns a simple attribute which only contains 
	 * the attribute ID and its type. For more information about the normalization process of an 
	 * attribute, see : "Introducing TVL, a comprehensive Text-based Feature Modeling Language 
	 * and its Semantics".
	 *  
	 * @param attributeSymbol 
	 * 		the attribute symbol which will be convert in an Attribute (syntax tree format)
	 * 
	 * @param featureSymbol
	 * 		the feature symbol which owns attributeSymbol
	 *  
	 * @param featurePath
	 * 		the non ambiguous path which leads to featureSymbol
	 * 
	 * 
	 * @return 
	 * 		the attribute (in the syntax tree format) corresponding to the normalized form of attributeSymbol.
	 */
	private Attribute attributeSymbolManagement(AttributeSymbol attributeSymbol, FeatureSymbol featureSymbol, String featurePath) {
		BaseAttribute attribute = new BaseAttribute(attributeSymbol.getTrueType(), attributeSymbol.getID(), null);
		if (attributeSymbol.hasExpression()) {
			Constraint constraint = new Constraint(new EqualsExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), attributeSymbol.getExpression().toNormalForm()));
			this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody);
		}
		else {
			if (attributeSymbol.hasASetExpressionSymbol()) {
				if (attributeSymbol.getSetExpressionSymbol().isAnEnumSetExpressionSymbol()) {
					EnumSetExpressionSymbol enumSet = (EnumSetExpressionSymbol) attributeSymbol.getSetExpressionSymbol();
					int i = 1;
					ExpressionList expressionList = new ExpressionList(enumSet.getContainedValues().get(0).toNormalForm());
					while (i <= enumSet.getContainedValues().size()-1) {
						expressionList = new ExpressionList(enumSet.getContainedValues().get(i).toNormalForm(), expressionList);
						i++;
					}
					SetExpression setExpression = new SetExpression(expressionList, null);
					if (attributeSymbol.getTrueType() ==  Expression.ENUM) {
						attribute = new BaseAttribute(attributeSymbol.getTrueType(), attributeSymbol.getID(), new AttributeBody(setExpression));
					}
					else {
						Constraint constraint = new Constraint(new InExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), setExpression));
						this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody);
					}
				}
				else {
					IntervalSetExpressionSymbol intervalSet = (IntervalSetExpressionSymbol) attributeSymbol.getSetExpressionSymbol();
					SetExpression setExpression = new SetExpression(intervalSet.getMin(), intervalSet.getMax(), null);
					Constraint constraint = new Constraint(new InExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), setExpression));
					this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody);
				}
			}
			if (attributeSymbol.hasIfInDeclaration()) {
				Constraint constraint;
				if (attributeSymbol.getIfInExpression() != null) {
					constraint = new Constraint(new ImpliesExpression(new LongIDExpression(featurePath, null) , new EqualsExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), attributeSymbol.getIfInExpression().toNormalForm())));
				}	
				else {
					constraint = new Constraint(new ImpliesExpression(new LongIDExpression(featurePath, null) , new InExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), attributeSymbol.getIfInSetExpression().toNormalForm())));
				}
				this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody);
			}
			if (attributeSymbol.hasIfOutDeclaration()) {	
				Constraint constraint;
				if (attributeSymbol.getIfOutExpression() != null) {
					constraint = new Constraint(new ImpliesExpression(new NotExpression(new LongIDExpression(featurePath, null)), new EqualsExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), attributeSymbol.getIfOutExpression().toNormalForm())));
				}	
				else {
					constraint = new Constraint(new ImpliesExpression(new NotExpression(new LongIDExpression(featurePath, null)), new InExpression(new LongIDExpression(featurePath+"."+attributeSymbol.getID(), null), attributeSymbol.getIfOutSetExpression().toNormalForm())));
				}
				this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody);
			}
		}
		return attribute;
	}
	/**
	 * This method allows to convert a feature symbol into its normalized form. The attributes
	 * are normalized thanks to the "attributeSymbolManagement" method. Each constraints is 
	 * normalized thanks to the "constraintManagementMethod" and added to the feature body 
	 * ( this.rootFeatureBody ) of the root feature. This method is also called for each child 
	 * feature of featureSymbol. Finally, the result of this method is a feature object 
	 * (respecting the syntax tree format) which only contains (into its feature body) a feature 
	 * group (if featureSymbol had children features) and attributes (if featureSymbol had 
	 * attributes).
	 * 
	 * @param featureSymbol
	 * 		the feature symbol which will be convert into a feature (syntax tree format)
	 * 
	 * @return
	 * 		the feature (in the syntax tree format) corresponding to normalized form of featureSymbol
	 * 
	 * @throws ChildrenFeaturesGroupAlreadySpecifiedException
	 */
	private Feature constructNormalizedSyntaxTree(FeatureSymbol featureSymbol) throws ChildrenFeaturesGroupAlreadySpecifiedException {
		FeatureBody featureBody;
		String nonAmbiguousFeaturePath = featuresSymbolTable.getNonAmbiguousPath(featureSymbol);
		if (featureSymbol.isIntoSyntaxTree()) {
			if (featureSymbol.isShared()) {
				return new Feature(featureSymbol.isRoot(), nonAmbiguousFeaturePath, featureSymbol.isOptionnal(), featureSymbol.isShared());
			}
			else {
				throw new ChildrenFeaturesGroupAlreadySpecifiedException("Error : the feature "+featureSymbol.getID()+" is saved many times in the syntax tree");
			}
		}
		else {
			featureBody = null;
			if (featureSymbol.hasChildrenFeatures()) {
				Cardinality cardinality = new Cardinality(String.valueOf(featureSymbol.getMinGroupCardinality()), String.valueOf(featureSymbol.getMaxGroupCardinality()));
				Object[] featureChildrenArray = featureSymbol.getChildrenFeatures().keySet().toArray();
				int i = 1;
				HierarchicalFeatures hierarchicalFeatures = new HierarchicalFeatures(constructNormalizedSyntaxTree(featureSymbol.getChildrenFeature(featureChildrenArray[0].toString())));
				while (i <= featureChildrenArray.length -1) {
					hierarchicalFeatures = new HierarchicalFeatures(constructNormalizedSyntaxTree(featureSymbol.getChildrenFeature(featureChildrenArray[i].toString())), hierarchicalFeatures);
					i++;
				}
				FeatureGroup featureGroup = new FeatureGroup(cardinality, hierarchicalFeatures);
				featureBody = new FeatureBody(featureGroup, featureBody);
			}
			if (featureSymbol.hasAttributesSymbols()) {
				Object[] featureAttributesArray = featureSymbol.getAttributesSymbols().keySet().toArray();
				int i = 0;
				Attribute attribute;
				while (i <= featureAttributesArray.length-1) {
					AttributeSymbol attributeSymbol = featureSymbol.getAttributeSymbol(featureAttributesArray[i].toString());
					if (!attributeSymbol.isARecordSymbol()) {
						attribute = attributeSymbolManagement(attributeSymbol, featureSymbol, nonAmbiguousFeaturePath);
						featureBody = new FeatureBody(attribute, featureBody);
					}
					i++;
				}
			}
			if (featureSymbol.hasConstraintSymbols()) {
				int i = 0;
				Constraint constraint;
				ConstraintSymbol constraintSymbol;
				while (i <= featureSymbol.getConstraintSymbols().size()-1) {
					constraintSymbol = featureSymbol.getConstraintSymbols().get(i);
					constraint = ConstraintSymbolManagement(constraintSymbol, nonAmbiguousFeaturePath);
					this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody); 
					i++;
				}
			}
			featureSymbol.setIntoSyntaxTree();
			if (featureSymbol.isOptionnal()) {
				//Feature feature = new Feature(false, featureSymbol.getID(), featureBody, true, featureSymbol.isShared());
				//return feature;
			}
			else {
				//return new Feature(featureSymbol.isRoot(), featureSymbol.getID(), featureBody, true, featureSymbol.isShared());
			}
			
			Feature feature = new Feature(featureSymbol.isRoot(), featureSymbol.getID(), featureBody, featureSymbol.isOptionnal(), featureSymbol.isShared());
			//Feature card. Management
			feature.setFeatureCardinality(new Cardinality(featureSymbol.getMinFeatureCardinality(), featureSymbol.getMaxFeatureCardinality()));

			return feature;			
		}
	}
	
	/**
	 * This method allows to start the normalization process of the diagram. It is really like
	 * the "constructNormalizedSyntaxTree" method but in this version, it only uses the feature
	 * body ( this.rootFeatureBody ) of the root feature. It allows to not always check if 
	 * rootFeatureSymbol is the root feature of the diagram. For more information, see above : 
	 * "constructNormalizedSyntaxTree".
	 * 
	 * @param rootFeatureSymbol
	 * 		The root feature symbol of the diagram
	 * 
	 * @return
	 * 		The feature (in the syntax tree format) corresponding to the normalized form of featureSymbol.
	 * 
	 * @throws ChildrenFeaturesGroupAlreadySpecifiedException
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details 
	 */
	private Feature startConstructNormalizedSyntaxTree(FeatureSymbol rootFeatureSymbol) throws ChildrenFeaturesGroupAlreadySpecifiedException {
		if (rootFeatureSymbol.hasChildrenFeatures()) {
			Cardinality cardinality = new Cardinality(String.valueOf(rootFeatureSymbol.getMinGroupCardinality()), String.valueOf(rootFeatureSymbol.getMaxGroupCardinality()));
			Object[] featureChildrenArray = rootFeatureSymbol.getChildrenFeatures().keySet().toArray();
			int i = 1;
			HierarchicalFeatures hierarchicalFeatures = new HierarchicalFeatures(constructNormalizedSyntaxTree(rootFeatureSymbol.getChildrenFeature(featureChildrenArray[0].toString())));
			while (i <= featureChildrenArray.length -1) {
				hierarchicalFeatures = new HierarchicalFeatures(constructNormalizedSyntaxTree(rootFeatureSymbol.getChildrenFeature(featureChildrenArray[i].toString())), hierarchicalFeatures);
				i++;
			}
			FeatureGroup featureGroup = new FeatureGroup(cardinality, hierarchicalFeatures);
			this.rootFeatureBody = new FeatureBody(featureGroup, this.rootFeatureBody);
		}
		if (rootFeatureSymbol.hasAttributesSymbols()) {
			Object[] featureAttributesArray = rootFeatureSymbol.getAttributesSymbols().keySet().toArray();
			int i = 0;
			Attribute attribute;
			while (i <= featureAttributesArray.length-1) {
				AttributeSymbol attributeSymbol = rootFeatureSymbol.getAttributeSymbol(featureAttributesArray[i].toString()); 
				if (!attributeSymbol.isARecordSymbol()) {
					attribute = attributeSymbolManagement(attributeSymbol, rootFeatureSymbol, rootFeatureSymbol.getID());
					this.rootFeatureBody = new FeatureBody(attribute, this.rootFeatureBody);
				}
				i++;
			}
		}
		if (rootFeatureSymbol.hasConstraintSymbols()) {
			int i = 0;
			Constraint constraint;
			ConstraintSymbol constraintSymbol;
			while (i <= rootFeatureSymbol.getConstraintSymbols().size()-1) {
				constraintSymbol = rootFeatureSymbol.getConstraintSymbols().get(i);
				constraint = ConstraintSymbolManagement(constraintSymbol, rootFeatureSymbol.getID());
				this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody); 
				i++;
			}
		}
		return new Feature(true, rootFeatureSymbol.getID(), this.rootFeatureBody, rootFeatureSymbol.isOptionnal(), rootFeatureSymbol.isShared());
	}
	
	/**
	 * This method allows to convert a constraint symbol into it's normalized form. For more 
	 * information about the normalization process of a constraint , see : "Introducing TVL, 
	 * a comprehensive Text-based Feature Modeling Language and its Semantics".
	 * 
	 * @param constraintSymbol
	 * 		The constraint symbol which will be convert into a constraint (syntax tree format)
	 * 
	 * @param featurePath
	 * 		The non ambiguous path which allows to access to the feature symbol which contains
	 * 		constraintSymbol. 
	 * 
	 * @return
	 * 		The constraint object ( in the syntax tree format ) corresponding to the normalized
	 * 		form of constraintSymbol.
	 * 
	 */
	private Constraint ConstraintSymbolManagement(ConstraintSymbol constraintSymbol, String featurePath) {
		Constraint constraint;
		if (constraintSymbol.isIfIn() && constraintSymbol.isIfOut()) {
			constraint = new Constraint(constraintSymbol.getExpression().toNormalForm());
		}
		else {
			if (constraintSymbol.isIfIn()) {
				constraint = new Constraint(new ImpliesExpression(new LongIDExpression(featurePath, null), constraintSymbol.getExpression().toNormalForm()));
			}
			else {
				constraint = new Constraint(new ImpliesExpression(new NotExpression(new LongIDExpression(featurePath, null)), constraintSymbol.getExpression().toNormalForm()));
			}
			
		}
		return constraint;
	}
	
	/**
	 * Return the root feature of the new generated syntax tree.
	 *  
	 * @return
	 * 		The root feature of the new generated syntax tree.
	 */
	public Feature getRootFeature() {
		return this.rootFeature;
	}
}
