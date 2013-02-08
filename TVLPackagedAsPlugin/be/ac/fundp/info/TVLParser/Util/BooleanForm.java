package be.ac.fundp.info.TVLParser.Util;

import java.util.Arrays;
import java.util.Vector;

import be.ac.fundp.info.TVLParser.SyntaxTree.AndExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Attribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.BaseAttribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.BooleanExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Cardinality;
import be.ac.fundp.info.TVLParser.SyntaxTree.Constraint;
import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Feature;
import be.ac.fundp.info.TVLParser.SyntaxTree.FeatureBody;
import be.ac.fundp.info.TVLParser.SyntaxTree.FeatureGroup;
import be.ac.fundp.info.TVLParser.SyntaxTree.HierarchicalFeatures;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.NotExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.OrExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.TrueExpression;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeaturesGroupAlreadySpecifiedException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.EnumSetExpressionSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeaturesSymbolTable;

/**
 * 
 * This class allows to create an object which will contains the syntax tree of the boolean form
 * of a feature model. Before any operation, the feature model has to be normalized, otherwise,
 * the class constructor will crash.
 * 
 * To be sure that the new syntax tree doesn't contain errors, parse it with a new TVL Parser.
 *
 */
public class BooleanForm {
	
	// The feature symbol table of the normalized form of the feature model
	private FeaturesSymbolTable featuresSymbolTable;
	
	// The feature body of the root feature (in the generated syntax tree, see the grammar)
	private FeatureBody rootFeatureBody = null;
	
	// The root feature of the generated syntax tree (see the grammar)
	private Feature rootFeature;
	
	/**
	 * This constructor creates a new BooleanForm object. It will contain the syntax tree
	 * ("rootFeature") of the boolean form of the root feature of "featureSymbolTable". 
	 * Each value of an enum attribute will be transformed into a boolean attribute 
	 * and each constraints will be converted into a CNF formula (see interface "BooleanExpression" 
	 * in package "SyntaxTree"). Warning, "featuresSymbolTable" must correspond to the feature symbol
	 * table of a normalized feature model, otherwise, the constructor will crash.
	 * 
	 * @param featuresSymbolTable
	 * 		The generated syntax tree will correspond to the boolean form of the root feature of this 
	 * 		feature symbol table.
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
	public BooleanForm(FeaturesSymbolTable featuresSymbolTable) throws ChildrenFeaturesGroupAlreadySpecifiedException, AmbiguousReferenceException, SymbolNotFoundException, ChildrenFeatureNotFoundException {
		FeatureSymbol rootFeatureSymbol;
		this.featuresSymbolTable = featuresSymbolTable;
		rootFeatureSymbol = (FeatureSymbol) featuresSymbolTable.getFeatureSymbol(featuresSymbolTable.getRootFeatureID());
		enumSymbolPreManagement(rootFeatureSymbol);
		this.rootFeature = startConstructBooleanSyntaxTree(rootFeatureSymbol);

	}
	
	/**
	 * This method allow to ( for "featureSymbol" and for each of its descendants ) :
	 * 	1) Transform each value of an enum attribute into a boolean attribute.
	 * 	2) To generate the boolean constraint corresponding to the "in" clause of each 
	 * 	   enum attribute.
	 * 
	 * For example, enum color in { Red, Blue } is transformed into :
	 * 
	 * 		bool color_Red;
	 * 		bool color_Blue;
	 * 		( color_Red && !color_Blue ) || ( color_Blue && !color_Red);
	 * 
	 * color_Red and color_Blue are also added to the dictionary "enumValueToBooleanAttribute"
	 * of color :
	 * 
	 * 				"Red"  --> color_Red
	 * 				"Blue" --> color_Blue
	 * 
	 * This dictionary allows to retrieve the boolean attribute corresponding to an
	 * enum value, this mechanism is very practical to easily transform an expression which
	 * concerns an enum attribute.  
	 * 
	 * Warning
	 * ------- 
	 * This method must be used before all others methods from this class. Otherwise,
	 * the boolean form construction will crash. It doesn't construct the normalized syntax 
	 * tree. It only modifies the features symbol table in order to incorporate the boolean 
	 * form of the enum attributes
	 *  
	 * @param featureSymbol
	 * 		The feature symbol for which the enum attributes will be converted.
	 */
	private void enumSymbolPreManagement(FeatureSymbol featureSymbol) {
		if (featureSymbol.hasAttributesSymbols()) {
			int i = 0;
			Object[] featuresAttributesArray = featureSymbol.getAttributesSymbols().keySet().toArray(); 
			while (i <= featuresAttributesArray.length -1) {
				AttributeSymbol attributeSymbol = featureSymbol.getAttributeSymbol(featuresAttributesArray[i].toString());
				if (attributeSymbol.getTrueType() == Expression.ENUM) {
					String featurePath = this.featuresSymbolTable.getNonAmbiguousPath(featureSymbol);					
					attributeSymbol.initializeEnumValueToBooleanAttribute();
					Vector<Expression> enumValues =  ((EnumSetExpressionSymbol) attributeSymbol.getSetExpressionSymbol()).getContainedValues();
					int j = 0;
					String enumValue;
					String booleanAttributeID;
					// Transform each enum value of attributeSymbol into a boolean attribute 
					while (j <= enumValues.size() - 1) {
						enumValue = ((LongIDExpression) enumValues.get(j)).getLongID();
						booleanAttributeID = attributeSymbol.getID().concat("_".concat(enumValue));
						while (featureSymbol.containsAttributeSymbol(booleanAttributeID)) {
							booleanAttributeID = booleanAttributeID.concat("E");
						}
						AttributeSymbol booleanAttribute = new AttributeSymbol(Expression.BOOL, booleanAttributeID);
						featureSymbol.addAttribute(booleanAttribute);
						attributeSymbol.addBooleanAttribute(enumValue, booleanAttribute);
						j++;
					}
					// Transform "in { ... }" into a new constraint
					if (attributeSymbol.getNumberOfBooleanAttributes() == 1) {
						this.rootFeatureBody = new FeatureBody(new Constraint(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(0)).getLongID())).getID(), this.featuresSymbolTable)), this.rootFeatureBody);
					}
					else {
						AndExpression andExpression1 = new AndExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(0)).getLongID())).getID(), this.featuresSymbolTable), new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(1)).getLongID())).getID(), this.featuresSymbolTable)));
						AndExpression andExpression2 = new AndExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(1)).getLongID())).getID(), this.featuresSymbolTable), new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(0)).getLongID())).getID(), this.featuresSymbolTable)));
						int k =2;
						while (k <= attributeSymbol.getNumberOfBooleanAttributes()-1) {
							andExpression1 = new AndExpression(andExpression1, new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(k)).getLongID())).getID(), this.featuresSymbolTable)));
							andExpression2 = new AndExpression(andExpression2, new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(k)).getLongID())).getID(), this.featuresSymbolTable)));
							k++;
						}
						OrExpression simplifiedExpression = new OrExpression(new ParenthesesExpression(andExpression1), new ParenthesesExpression(andExpression2));
						k = 2;
						while (k <= attributeSymbol.getNumberOfBooleanAttributes()-1) {
							AndExpression andExpression = new AndExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(k)).getLongID())).getID(), this.featuresSymbolTable), new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(0)).getLongID())).getID(), this.featuresSymbolTable)));
							int l = 1;
							while (l <= attributeSymbol.getNumberOfBooleanAttributes()-1) {
								if (k!=l) {
									andExpression = new AndExpression(andExpression, new NotExpression(new LongIDExpression(featurePath+"."+(attributeSymbol.getBooleanAttribute(((LongIDExpression) enumValues.get(l)).getLongID())).getID(), this.featuresSymbolTable)));
								}
								l++;
							}
							simplifiedExpression = new OrExpression(simplifiedExpression, new ParenthesesExpression(andExpression));
							k++;
						}
						this.rootFeatureBody = new FeatureBody(new Constraint(writeParenthesesArounfCNFClauses((AndExpression) simplifiedExpression.distributeDisjunctions())), this.rootFeatureBody);
					}
				}
				i++;
			}
			
		}
		if (featureSymbol.hasChildrenFeatures()) {
			int i = 0;
			Object[] featuresChildrenArray = featureSymbol.getChildrenFeatures().keySet().toArray();
			while (i <= featuresChildrenArray.length-1) {
				enumSymbolPreManagement(featureSymbol.getChildrenFeature(featuresChildrenArray[i].toString()));
				i++;
			}
		}
	}
	
	/**
	 * This method allows to start the boolean transformation of the feature model. It is really like
	 * the "constructBooleanSyntaxTree" method but in this version, it only uses the feature
	 * body ( this.rootFeatureBody ) of the root feature. It allows to not always check if 
	 * "rootFeatureSymbol" is the root feature of the diagram. However, in this version, the constraints 
	 * of "rootFeatureSymbol" are took into account, transformed into CNF formulas and added to 
	 * "rootFeatureBody".
	 * 
	 * For more information about constraints transformation, see interface "BooleanExpression" in
	 * package "SyntaxTree".
	 * 
	 * @param rootFeatureSymbol
	 * 		The root feature symbol of the feature model. This feature symbol has to be normalized before
	 * 		any operation, otherwise, this method will crash.
	 * 
	 * @return
	 * 		The feature (in the syntax tree format) corresponding to the boolean form of featureSymbol.
	 * 
	 * @throws ChildrenFeaturesGroupAlreadySpecifiedException
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details.
	 */
	private Feature startConstructBooleanSyntaxTree(FeatureSymbol rootFeatureSymbol) throws ChildrenFeaturesGroupAlreadySpecifiedException {
		if (rootFeatureSymbol.hasChildrenFeatures()) {
			Cardinality cardinality = new Cardinality(String.valueOf(rootFeatureSymbol.getMinGroupCardinality()), String.valueOf(rootFeatureSymbol.getMaxGroupCardinality()));
			Object[] featureChildrenArray = rootFeatureSymbol.getChildrenFeatures().keySet().toArray();
			int i = 1;
			HierarchicalFeatures hierarchicalFeatures = new HierarchicalFeatures(constructBooleanSyntaxTree(rootFeatureSymbol.getChildrenFeature(featureChildrenArray[0].toString())));
			while (i <= featureChildrenArray.length -1) {
				hierarchicalFeatures = new HierarchicalFeatures(constructBooleanSyntaxTree(rootFeatureSymbol.getChildrenFeature(featureChildrenArray[i].toString())), hierarchicalFeatures);
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
				if (attributeSymbol.getTrueType() != Expression.ENUM) {
					attribute = attributeSymbolManagement(attributeSymbol);
					rootFeatureBody = new FeatureBody(attribute, rootFeatureBody);
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
				constraint = ConstraintSymbolManagement(constraintSymbol);
				this.rootFeatureBody = new FeatureBody(constraint, this.rootFeatureBody); 
				i++;
			}
		}
		return new Feature(true, rootFeatureSymbol.getID(), this.rootFeatureBody, false, false);
	}
	
	/**
	 * This method allows to convert a feature symbol and each of its descendants into their boolean forms.
	 * In fact, each feature symbol is transformed into a feature (syntax tree format) and each attribute symbol
	 * (only boolean) is transformed into an attribute (syntax tree format) and added to its parent feature.
	 * 
	 * Be careful, "featureSymbol" can't be the root feature. Otherwise, the constraints of the root feature
	 * won't be added to the new feature.
	 * 
	 * 
	 * @param featureSymbol
	 * 		The feature symbol which will be convert into a feature (syntax tree format)
	 * 
	 * @return
	 * 		the feature (in the syntax tree format) corresponding to normalized form of featureSymbol
	 * 
	 * @throws ChildrenFeaturesGroupAlreadySpecifiedException
	 * 			This exception is normally never thrown. See the class of this exception in 
	 * 			the package "exceptions" for more details.
	 */
	private Feature constructBooleanSyntaxTree(FeatureSymbol featureSymbol) throws ChildrenFeaturesGroupAlreadySpecifiedException {
		FeatureBody featureBody;
		String nonAmbiguousFeaturePath = featuresSymbolTable.getNonAmbiguousPath(featureSymbol);
		if (featureSymbol.isIntoSyntaxTree()) {
			if (featureSymbol.isShared()) {
				return new Feature(false, nonAmbiguousFeaturePath, featureSymbol.isOptionnal(), true);
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
				HierarchicalFeatures hierarchicalFeatures = new HierarchicalFeatures(constructBooleanSyntaxTree(featureSymbol.getChildrenFeature(featureChildrenArray[0].toString())));
				while (i <= featureChildrenArray.length -1) {
					hierarchicalFeatures = new HierarchicalFeatures(constructBooleanSyntaxTree(featureSymbol.getChildrenFeature(featureChildrenArray[i].toString())), hierarchicalFeatures);
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
					if (attributeSymbol.getTrueType() != Expression.ENUM) {
						attribute = attributeSymbolManagement(attributeSymbol);
						featureBody = new FeatureBody(attribute, featureBody);
					}
					i++;
				}
			}
			featureSymbol.setIntoSyntaxTree();
			
			if (featureSymbol.isOptionnal()) {
				Feature feature = new Feature(featureSymbol.isRoot(), featureSymbol.getID(), featureBody, true, featureSymbol.isShared());
				HierarchicalFeatures hierarchicalFeature = new HierarchicalFeatures(feature);
				Cardinality cardinalityArtificialParent = new Cardinality("0", "1");
				FeatureGroup featureGroup = new FeatureGroup(cardinalityArtificialParent, hierarchicalFeature);
				Feature artificialParent = new Feature(false, "ArtificialParent"+featureSymbol.getID(), new FeatureBody(featureGroup, null), false, false);
				//TODO : Feature card artificial parent ???
				return artificialParent;
			}
			else {
				Feature feature = new Feature(featureSymbol.isRoot(), featureSymbol.getID(), featureBody, false, featureSymbol.isShared());
				//Feature Card Management
				feature.setFeatureCardinality(new Cardinality(featureSymbol.getMinFeatureCardinality(), featureSymbol.getMaxFeatureCardinality()));
				
				return feature;
			}
		}
	}
	
	/**
	 * This method generates the attribute (in the syntax tree format) corresponding to "attributeSymbol".
	 * 
	 * Be careful, "attributeSymbol" must be a boolean attribute. The enum attribute are managed with
	 * "enumSymbolPreManagement". 
	 * 
	 * @param attributeSymbol
	 * 		The attribute symbol which will be transform into an attribute (respecting the syntax tree format).
	 * 
	 * @return
	 * 		The attribute (respecting the syntax tree format) corresponding to "attributeSymbol".
	 */
	private Attribute attributeSymbolManagement(AttributeSymbol attributeSymbol) {
		return new BaseAttribute(attributeSymbol.getTrueType(), attributeSymbol.getID(), null);
	}
	
	/**
	 * This method allows to convert a constraint symbol into it's boolean form. For more 
	 * information about the boolean transformation process of a constraint, see interface 
	 * "BooleanExpression" in package "SyntaxTree".
	 * 
	 * @param constraintSymbol
	 * 		The constraint symbol which will be convert into a constraint (syntax tree format).
	 * 
	 * @return
	 * 		The constraint ( in the syntax tree format ) corresponding to the boolean
	 * 		form of constraintSymbol.
	 */
	private Constraint ConstraintSymbolManagement(ConstraintSymbol constraintSymbol) {
		BooleanExpression constraintExpression = (BooleanExpression) constraintSymbol.getExpression();
		constraintExpression = constraintExpression.toSimplifiedForm().removeArrows();
	//	System.out.println(constraintExpression.toString());
		constraintExpression = constraintExpression.distributeNegations();
	//	System.out.println(constraintExpression.toString());
		constraintExpression = constraintExpression.distributeDisjunctions();
	//	System.out.println("-------------------------------------------------------------------------");
		if (constraintExpression.getClass().toString().contains("AndExpression")) {
			return new Constraint(writeParenthesesArounfCNFClauses((AndExpression) constraintExpression));
		}
		else {
			return new Constraint(constraintExpression);
		}
		
	}
	
	/**
	 * This method is only "visual" and allows to write parentheses around the clauses from a CNF formula.
	 *  
	 * @param andExpression
	 * 		The CNF formula
	 *  
	 * @return
	 * 		The same CNF formula with parentheses around its clauses.
	 */
	private BooleanExpression writeParenthesesArounfCNFClauses(AndExpression andExpression) {
		if (andExpression.getExpression1().getClass().toString().contains("AndExpression")) {
			andExpression.setExpression1(writeParenthesesArounfCNFClauses((AndExpression) andExpression.getExpression1()));
		}
		else {
			andExpression.setExpression1(new ParenthesesExpression(andExpression.getExpression1()));
		}
		if (andExpression.getExpression2().getClass().toString().contains("AndExpression")) {
			andExpression.setExpression2(writeParenthesesArounfCNFClauses((AndExpression) andExpression.getExpression2()));
		}
		else {
			andExpression.setExpression2(new ParenthesesExpression(andExpression.getExpression2()));
		}
		return andExpression;
	}
	
	/**
	 * Return the root feature of the new generated syntax tree (boolean form of the root feature
	 * of "featuresSymbolTable").
	 * 
	 * @return a feature (respecting the syntax tree format) corresponding to the boolean form of the 
	 * root feature of "featuresSymbolTable".
	 */
	public Feature getRootFeature() {
		return this.rootFeature;
	}
	
	/**
	 * This method allows to generate the constraints corresponding to the cardinality of "featureSymbol".
	 * If "featureSymbol" doesn't have any child feature, this method will crash. For more information about
	 * the cardinality transformation process see "Technical Report : Varied Feature Diagram (VFD) Language :
	 * A Reasoning Tool" from Jean-christophe Trigaux and Patrick Heymans.
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * @param featureSymbol
	 * 		The feature symbol for which the cardinalities constraints will be generated. Be careful,
	 * 		this must feature must have children features, otherwise, this method will crash.
	 * 
	 * @return
	 * 		An array of int arrays containing the constraints corresponding to the cardinality of
	 * 		feature symbol. Each sub array represents a clause and each int represents the numerical
	 * 		ID of a feature.
	 * 
	 */
	public static int[][] generateCNFRules(FeatureSymbol featureSymbol) {
		int min = featureSymbol.getMinGroupCardinality();
		int max = featureSymbol.getMaxGroupCardinality();
		int nbrSons = featureSymbol.getChildrenFeatures().size();
		int[][] result;
		// Cases of Table 1 in Technical Report VFD J-C Trigaux and P Heymans
		// (n° O6 in the document stack) .
		if (min == 0 && max == 0) {
			// ^ i=1..n (not g v not fi)
			result = card00(featureSymbol);

		} else if (min == 0 && max == nbrSons) {
			// No output generated
			result = new int[0][];

		} else if (min == 0 && max < nbrSons && nbrSons >= 2 && max >= 1) {
			// not g v LTseq(fn, ..., fj)
			result = card0j(featureSymbol);

		} else if (min == 1 && max == nbrSons && nbrSons >= 1) {
			// not g v f1 v ... v fn
			result = card1n(featureSymbol);

		} else if (min == max && max == nbrSons && nbrSons >= 1) {
			// ^i=1..n (not g v fi)
			result = cardnn(featureSymbol);

		} else if (min > 0 && max == nbrSons && min < max && max >= 2) {
			// not g v GTseq(fn,...fi)
			result = cardin(featureSymbol);

		} else  {//if (min >= 1 && max < nbrSons && nbrSons >= 2 && min <= max) {
			// not g v GTseq(fn,...,fi) ^ LTseq(fn,...fj)
			result = cardij(featureSymbol); }

		/*} else {
			throw new IllegalArgumentException(
					"Can't generate feature CNF for min=" + min + " max=" + max
					+ " nbrSons=" + nbrSons + " in feature " + feature);
		}*/

		return result;
	}

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey. 
	 * 
	 * ^ i=1..n (not g v not fi)
	 * 
	 */
	private static int[][] card00(FeatureSymbol featureSymbol) {
		Object[] sons = featureSymbol.getChildrenFeatures().keySet().toArray();
		int[][] result = new int[sons.length][];
		int sonId;
		for (int i = 0; i < sons.length; i++) {
			sonId = featureSymbol.getChildrenFeature(sons[i].toString()).getDIMACS_ID();
			result[i] = new int[] { -featureSymbol.getDIMACS_ID(), -sonId }; // -g v -fi
		}
		return result;
	}
	
	

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * not g v LTseq(fn, ..., fj)
	 */
	private static int[][] card0j(FeatureSymbol featureSymbol) {
		// The first id that the LTseq generator will use
		int firstEncVar = IDGenerator.getInstance().getNewID(null);
		// Construct the inputs with the sons ids
		Object[] sons = featureSymbol.getChildrenFeatures().keySet().toArray();
		int[] inputs = new int[sons.length];
		int sonId;
		for (int i = 0; i < sons.length; i++) {
			sonId = featureSymbol.getChildrenFeature(sons[i].toString()).getDIMACS_ID();
			// If the son is optional, a new intermediate feature is used
			inputs[i] = sonId;
		}
		// Call the LTseq generator
		int[][] result = ConstraintsToCNF.lessThanConstraint(firstEncVar, featureSymbol.getMaxGroupCardinality(), inputs);
		
		int artVarNumber = ConstraintsToCNF.getNbrAuxilaryVariablesUsedByLessThanEncoding(featureSymbol.getChildrenFeatures().size(), featureSymbol.getMaxGroupCardinality());
		IDGenerator.getInstance().setNextID( firstEncVar + artVarNumber);

		// distribute not g on the result
		int length;
		for (int i = 0; i < result.length; i++) {
			length = result[i].length;
			result[i] = Arrays.copyOf(result[i], length + 1);
			result[i][length] = -featureSymbol.getDIMACS_ID();
		}
		return result;
	}

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * not g v f1 v ... v fn           {@link SATReasoner}).
	 */
	private static int[][] card1n(FeatureSymbol featureSymbol) {
		int[][] result = new int[1][];
		int sonId;
		Object[] sons = featureSymbol.getChildrenFeatures().keySet().toArray();
		result[0] = new int[sons.length + 1];
		result[0][0] = -featureSymbol.getDIMACS_ID(); // -g

		for (int i = 0; i < sons.length; i++) {
			sonId = featureSymbol.getChildrenFeature(sons[i].toString()).getDIMACS_ID();
			result[0][i + 1] = sonId; // fi
		}
		return result;
	}

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally write by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * ^i=1..n (not g v fi)
	 */
	private static int[][] cardnn(FeatureSymbol featureSymbol) {
		Object[] sons = featureSymbol.getChildrenFeatures().keySet().toArray();
		int[][] result = new int[sons.length][];
		int sonId;
		for (int i = 0; i < sons.length; i++) {
			sonId = featureSymbol.getChildrenFeature(sons[i].toString()).getDIMACS_ID();
			result[i] = new int[] { -featureSymbol.getDIMACS_ID(), sonId }; // -g v fi
		}
		return result;
	}

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * not g v GTseq(fn,...fi)
	 */
	private static int[][] cardin(FeatureSymbol featureSymbol) {
		// The first id that the GTseq generator will use
		int firstEncVar = IDGenerator.getInstance().getNewID(null);
		// Construct the inputs with the sons ids
		Object[] sons = featureSymbol.getChildrenFeatures().keySet().toArray();
		int[] inputs = new int[sons.length];
		int sonId;
		for (int i = 0; i < sons.length; i++) {
			sonId = featureSymbol.getChildrenFeature(sons[i].toString()).getDIMACS_ID();
			inputs[i] = sonId;
		}

		// Call the GTseq generator
		int[][] result = ConstraintsToCNF.greaterThanConstraint(firstEncVar,
				featureSymbol.getMinGroupCardinality(), inputs);
		int artVarNumber = ConstraintsToCNF.getNbrAuxilaryVariablesUsedByGreaterThanEncoding(featureSymbol.getChildrenFeatures().size(), featureSymbol.getMinGroupCardinality());
	//	System.out.println(firstEncVar);
	//	System.out.println(artVarNumber);
	//	System.out.println( firstEncVar + artVarNumber);
		IDGenerator.getInstance().setNextID( firstEncVar + artVarNumber);
		
		// distribute not g on the result
		int length;
		for (int i = 0; i < result.length; i++) {
			length = result[i].length;
			result[i] = Arrays.copyOf(result[i], length + 1);
			result[i][length] = -featureSymbol.getDIMACS_ID();
		}

		return result;
	}

	/**
	 * Used by the method "generateCNFRules".
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey.
	 * 
	 * not g v GTseq(fn,...,fi) ^ LTseq(fn,...fj)
	 */
	private static int[][] cardij(FeatureSymbol featureSymbol) {
			// -g v GTseq
			int[][] result1 = cardin(featureSymbol);


			// -g v LTseq
			int[][] result2 = card0j(featureSymbol);


			// (-g v GTseq) ^ (-g v LTseq)
			int[][] result = Arrays
					.copyOf(result1, result1.length + result2.length);
			for (int i = 0; i < result2.length; i++) {
				result[result1.length + i] = result2[i];
			}
			
			

			return result;
	}
	
	/**
	 * This method allows to merge all the constraints from "rootFeatureSymbol" in one single booleanExpression.
	 * If rootFeature has no constraints, return null.
	 * 
	 * @param rootFeatureSymbol
	 * 		The feature symbol which contains the constraints to be merged.
	 * 
	 * @return
	 * 		A boolean expression, the conjunction of all the constraints of "rootFeatureSymbol."
	 * 		If rootFeature has no constraints, return null.
	 */
	public static BooleanExpression mergeConstraints(FeatureSymbol rootFeatureSymbol) {
		if (rootFeatureSymbol != null && rootFeatureSymbol.hasConstraintSymbols()) {
			int i = 0;
			BooleanExpression mergedExpression = null;
			while ( i <= rootFeatureSymbol.getConstraintSymbols().size() -1) {
				if (mergedExpression == null) {
					mergedExpression = (BooleanExpression) rootFeatureSymbol.getConstraintSymbols().get(i).getExpression(); 
				}
				else {
					mergedExpression = new AndExpression(mergedExpression, rootFeatureSymbol.getConstraintSymbols().get(i).getExpression()); 
				}
				i++;
			}
			return mergedExpression;
		}
		else {
			return null;
		}
	}
	
	/**
	 * This method allows to generate the justification rule corresponding to "featureSymbol".
	 * For more information about the justification rules, see "Technical Report : Varied Feature 
	 * Diagram (VFD) Language : A Reasoning Tool" from Jean-christophe Trigaux and Patrick Heymans.
	 * 
	 * This method has been originally wrote by Michaël Marcozzi and Xavier Devroey. 
	 * 
	 * @param featureSymbol
	 * 		The feature symbol for which the justification rule will be generated.
	 * 
	 * @return
	 * 		An array of int containing the justification rule of "featureSymbol". Each unit of
	 * 		this array contains a numerical ID corresponding to a feature.
	 */
	public static int[] generateFeatureJustificationRule(FeatureSymbol featureSymbol) {
		if (featureSymbol==null) return new int[0];
		int id = featureSymbol.getDIMACS_ID();
		if (featureSymbol.isRoot()) {
			return new int[] { id };
		} 
		else {
			Object[] parents = featureSymbol.getParentsFeature().keySet().toArray();
			int[] res = new int[1 + parents.length];
			res[0] = -id;
			int fatherId;
			for (int i = 0; i < parents.length; i++) {
				fatherId = featureSymbol.getParentFeatureSymbol(parents[i].toString()).getDIMACS_ID();
				res[i + 1] = fatherId;
			}
			return res; // res has form (not g v p 1 v ... v p n)
		}
	}
	
	public static int[][] generateAttributesJustificationRule(FeatureSymbol featureSymbol) {
		Object[] attributes = featureSymbol.getAttributesSymbols().keySet().toArray();
		int[][] result = new int[attributes.length][];
		int attributeID;
		for (int i = 0; i < attributes.length; i++) {
			attributeID = featureSymbol.getAttributeSymbol(attributes[i].toString()).getDIMACS_ID();
			result[i] = new int[] { featureSymbol.getDIMACS_ID(), -attributeID }; // f v -ai
		}
		return result;
	}
	
	/**
	 * This method allows to generate the numerical version of a clause from a CNF formula.
	 * So, "expression" has to have this format "Car || Truck || Bus" (inclusive disjunction).
	 * This method removes also all TrueExpression or FalseExpression. So, the returned array
	 * will only contains numerical ID from features or attributes. 
	 * 
	 * 
	 * @param expression
	 * 		The clause for which the numerical version will be generated.
	 * 
	 * @return
	 * 		An array of int representing the numerical version of "expression". Each
	 * 		unit of this array contains an ID from a feature or from an attribute. Example :
	 * 				"Car || Truck || Bus"
	 * 				ID : 1  ID : 2   ID : 3
	 * 		will be convert into :
	 * 				{ 1, 2, 3 }
	 */
	public static int[] toNumericalClause(BooleanExpression expression) {
		int numericalID;
		if (!(expression instanceof OrExpression)) {
			numericalID = getNumericalID(expression);
			if (numericalID == 1)
				return null;
			else 
				return new int[]{numericalID};
		}
		else {
			OrExpression orExpression = (OrExpression) expression;
			if ((!(orExpression.getExpression1() instanceof OrExpression)) && (!(orExpression.getExpression2() instanceof OrExpression))) {
				numericalID = getNumericalID((BooleanExpression) orExpression.getExpression1());
				int numericalID2 = getNumericalID((BooleanExpression) orExpression.getExpression2());
				if ((numericalID == 1) || (numericalID2 == 1)) {
					return null;
				}
				else {
					if ((numericalID == -1) && (numericalID2 == -1)) {
						return new int[]{-1};
					}
					else {
						if (numericalID == -1) {
							return new int[]{numericalID2};
						}
						else {
							if (numericalID2 == -1) {
								return new int[]{numericalID};
							}
							else {
								return new int[] { numericalID, numericalID2};
							}
						}
					}
				}
			}
			else {
				if (!(orExpression.getExpression1().getClass().toString().contains("OrExpression"))) {
					int [] clause = toNumericalClause((OrExpression) orExpression.getExpression2());
					numericalID = getNumericalID((BooleanExpression) orExpression.getExpression1());
					if ((clause == null) || (numericalID == 1)) {
						return null;
					}
					else {
						if ((clause.length == 1) && (clause[0] == -1) && (numericalID == -1)) {
							return new int[]{-1};
						}
						else {
							if ((clause.length == 1) && (clause[0] == -1)) {
								return new int[]{numericalID};
							}
							else {
								if (numericalID == -1) {
									return clause;
								}
								else {
									int[] mergedClause = new int[1+clause.length];
									int i = 1;
									int j = 0;
									mergedClause[0] = numericalID;
									while (j <= clause.length-1) {
										mergedClause[i] = clause[j];
										i++;
										j++;
									}
									return mergedClause;
								}
							}
						}
					}
				}
				else {
					if (!(orExpression.getExpression2().getClass().toString().contains("OrExpression"))) {
						int [] clause = toNumericalClause((OrExpression) orExpression.getExpression1());
						numericalID = getNumericalID((BooleanExpression) orExpression.getExpression2());
						if ((clause == null ) || (numericalID == 1)) {
							return null;
						}
						else {
							if ((clause.length == 1) && (clause [0] == -1) && (numericalID == -1)) {
								return new int[]{-1};
							}
							else {
								if ((clause.length == 1) && (clause [0] == -1)) {
									return new int[]{numericalID};
								}
								else {
									if (numericalID == -1) {
										return clause;
									}
									else {
										int[] mergedClause = new int[clause.length+1];
										int i = 0;
										while (i <= clause.length-1) {
											mergedClause[i] = clause[i];
											i++;
										}
										mergedClause[i] = numericalID;;
										return mergedClause;
									}
								}
							}
						}
					}
					else {
						int [] clause1 = toNumericalClause((OrExpression) orExpression.getExpression1());
						int [] clause2 = toNumericalClause((OrExpression) orExpression.getExpression1());
						if ((clause1 == null) || (clause2 == null)) {
							return null;
						}
						else {
							if ((clause1.length == 1) && (clause2.length == 1) && (clause1[0] == -1) && (clause2[0] == -1)) {
								return new int[]{-1};
							}
							else {
								if ((clause1.length == 1) && (clause1[0] == -1)) {
									return clause2;
								}
								else {
									if ((clause2.length == 1) && (clause2[0] == -1)) {
										return clause1;
									}
									else {
										int[] mergedClause = new int[clause1.length+clause2.length];
										int i = 0;
										int j = 0;
										while (j <= clause1.length-1) {
											mergedClause[i] = clause1[j];
											i++;
											j++;
										}
										j = 0;
										while (j <= clause2.length-1) {
											mergedClause[i] = clause2[j];
											i++;
											j++;
										}
										return mergedClause;
									}
								}
							}
						}	
					}
				}
			}
		}
	}
	
	/**
	 * This method allows to return the numerical ID corresponding to expression.
	 * If "expression" is a LongIDExpression, it returns the numericalID of its symbol.
	 * If "expression" is a NotExpression, it returns the negation of its numerical ID.
	 * If "expression" is a TrueExpression, it returns 1.
	 * If "expression" is a TrueExpression, it returns -1.
	 *  
	 * @param expression
	 * 		This expression must be  :
	 * 				- A NotExpression containing a LongIDExpression
	 * 				- A LongIDEXpression
	 * 				- A TrueExpression
	 * 				- A FalseExpression
	 * 			
	 * @return
	 * 		The numericalID corresponding to "expression".
	 */
	private static int getNumericalID(BooleanExpression expression) {
		if (expression instanceof NotExpression) {
			return -getNumericalID((BooleanExpression) ((NotExpression) expression).getExpression());
		}
		else if (expression instanceof LongIDExpression) {
			return ((LongIDExpression) expression).getSymbol().getDIMACS_ID();
		}
		else if (expression instanceof TrueExpression) {
			return 1;
		}
		else return -1;
	}
	
	
}
