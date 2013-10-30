package fr.familiar.fm.converter.tvl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.sat4j.specs.TimeoutException;
import org.xtext.example.mydsl.fML.And_expr;
import org.xtext.example.mydsl.fML.CNF;
import org.xtext.example.mydsl.fML.CNFExpression;
import org.xtext.example.mydsl.fML.Child;
import org.xtext.example.mydsl.fML.FeatureModel;
import org.xtext.example.mydsl.fML.Impl_expr;
import org.xtext.example.mydsl.fML.Mandatory;
import org.xtext.example.mydsl.fML.Neg_expr;
import org.xtext.example.mydsl.fML.Optional;
import org.xtext.example.mydsl.fML.Or_expr;
import org.xtext.example.mydsl.fML.Orgroup;
import org.xtext.example.mydsl.fML.Production;
import org.xtext.example.mydsl.fML.Xorgroup;
import org.xtext.example.mydsl.fML.impl.And_exprImpl;
import org.xtext.example.mydsl.fML.impl.FMLFactoryImpl;
import org.xtext.example.mydsl.fML.impl.FeatureModelImpl;
import org.xtext.example.mydsl.fML.impl.Impl_exprImpl;
import org.xtext.example.mydsl.fML.impl.MandatoryImpl;
import org.xtext.example.mydsl.fML.impl.Neg_exprImpl;
import org.xtext.example.mydsl.fML.impl.OptionalImpl;
import org.xtext.example.mydsl.fML.impl.Or_exprImpl;
import org.xtext.example.mydsl.fML.impl.ProductionImpl;

import be.ac.fundp.info.TVLParser.SyntaxTree.AndExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Attribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.BooleanExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Constraint;
import be.ac.fundp.info.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.FalseExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Feature;
import be.ac.fundp.info.TVLParser.SyntaxTree.GEQExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.GreaterExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.InExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LEQExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LowerExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.NotEqualsExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.NotExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.OrExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.TrueExpression;
import be.ac.fundp.info.TVLParser.Util.BooleanForm;
import be.ac.fundp.info.TVLParser.Util.IDGenerator;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.Symbol;
import fr.familiar.fm.basic.FMLFeatureModelWriter;

/**
 * TVL to FAMILIAR Translator class.
 * Currently supported :
 * 
 * - Parental relations :
 * + Mandatory / Optional relation
 * + XOR group relation in the case minCardinality == maxCardinality &&
 * minCardinality == 1 && nChildren > 1
 * + OR group relation in the case minCardinality != maxCardinality &&
 * minCardinality == 1 && maxCardinality == nChildren
 * + Special cardinality groups ex : x = 5 children, minCardinality = 2,
 * maxCardinality = 4.
 * 
 * - Attributes :
 * + Boolean attributes. Handle special case like or-xor-special card subgroups.
 * 
 * @author Charles Vanbeneden
 * 
 */

public class TVLTranslator {

	// Permit to create FAMILIAR internal representation objetcs.
	private FMLFactoryImpl _fmlFactory = null;
	// The FAMILIAR Feature Model in output.
	private FeatureModel _fm = null;
	// Modified TVLParser
	private MyTVLParser _parser = null;
	// Set of warnings filled by the parser. Explain if a structure is ignored.
	// (int,enum...)
	private Set<String> _warnings = new HashSet<String>();
	// Contains all the names and the translation between TVL and FAMILIAR
	private NameSpaceTVLtoFAMILIARMap _nameSpace;

	/**
	 * Constructor for a file input.
	 * 
	 * @param inputFile
	 *            TVL file containing the feature model to translate.
	 * @throws Exception
	 */
	public TVLTranslator(File inputFile) throws Exception {
		try {
			// Init the TVL Parser
			_parser = new MyTVLParser(inputFile);
			if (!_parser.isValid()) {
				throw new FeatureModelInvalidException
				("The given TVL model is syntactically invalid: " + 
						_parser.getSyntaxError().getMessage());
			}
			
		} catch (FileNotFoundException e) {
			throw e;
		}
		_nameSpace = new NameSpaceTVLtoFAMILIARMap(_parser);
		/*
		 * TODO
		 * Calculated here because other functions like getTVLSolutions etc
		 * modifies the parser objects and array id of IDGenerator... Why, I
		 * don't know but it works this way...
		 */
		translateToFAMILIAR();
	}

	/**
	 * Constructor for a string input.
	 * 
	 * @param input
	 *            String containing the TVL feature model to parse.
	 * @throws Exception
	 */
	public TVLTranslator(String input) throws Exception {
		// _featureNameLibrary = new HashSet<String>();
		// Init the TVL Parser
		_parser = new MyTVLParser(input);
		if (!_parser.isValid()) {
			throw new FeatureModelInvalidException(
					"The given TVL model is syntactically invalid: " + 
					_parser.getSyntaxError().getMessage());
		}
		_nameSpace = new NameSpaceTVLtoFAMILIARMap(_parser);
		/*
		 * TODO
		 * Calculated here because other functions like getTVLSolutions etc
		 * modifies the parser objects and array id of IDGenerator... Why, I
		 * don't know but it works this way...
		 */
		translateToFAMILIAR();
	}

	/**
	 * 
	 * @return The complete FAMILIAR feature model resulting of the TVL feature
	 *         model translation.
	 * @throws Exception
	 */
	private FeatureModelImpl translateToFAMILIAR() throws Exception {
		if (_fmlFactory == null)
			_fmlFactory = new FMLFactoryImpl();
		assert (_fmlFactory != null);
		Feature rootTVL;
		// normal form
		rootTVL = _parser.getNormalFormInternalRepresentation().getRootFeature();

		// boolean form
		// rootTVL = _parser.getBooleanFormRepresentation().getRootFeature();

		// Creates the base of the FAMILIAR Feature Model
		_fm = _fmlFactory.createFeatureModel();

		// Begin the translation by the root feature of the TVL Feature Model.
		generateEntryProduction(rootTVL, "");
		// FAMILIAR Feature Model resulting of the translation of the TVL FM.
		
		return ((FeatureModelImpl) _fm);
	}

	/**
	 * Side effect : Modify this._fm, the FAMILIAR feature model. Complete
	 * _nameSpace with the names encountered. Fill _warnings with messages
	 * concerning ignored TVL structures.
	 * Adds :
	 * - productions
	 * - temp productions when there is a special transformation. Ex: boolean
	 * attribute with xor-or-spec card group.
	 * - contraints when there is special cardinality relations.
	 * 
	 * @param featureTVL
	 *            The TVL feature from which it's necessary to create FAMILIAR
	 *            productions. This function is recursive and thus generates
	 *            children's productions and so on...
	 * @param path
	 *            Must be set at "". It's the current path in the TVL tree.
	 */
	private void generateEntryProduction(Feature featureTVL, String path) {

		Production production = _fmlFactory.createProduction();

		try {
			((ProductionImpl) production).setName(_nameSpace.addName(featureTVL.getID(), path));
		} catch (NameProblemException e) {
			e.printStackTrace();
		}

		// Ignore DAG
		if (featureTVL.isShared())
			_warnings.add("The TVL feature " + featureTVL.getID() + " is a shared feature. This characteristic is ignored!");

		if (path.compareTo("") == 0)
			path = featureTVL.getID();
		else
			path = path + "." + featureTVL.getID();

		/*
		 * Boolean Attributes :
		 * We must add Temp_FeatureX to the production when there is:
		 * - a child Xor group
		 * - a child Or group
		 * - a child special cardinality group
		 * -> relType = 1 / 2 / 3
		 * Create a second production with the children relation now apparented
		 * to Temp_FeatureX
		 */
		Boolean hasBooleanAttributes = false;
		Vector<Attribute> attributesTVL = featureTVL.getAttributes();

		if (attributesTVL != null) {
			Iterator<Attribute> attributesIterator = attributesTVL.iterator();
			Attribute attribute = null;
			while (attributesIterator.hasNext()) {
				attribute = attributesIterator.next();
				/*
				 * See getStringEquivalentToAttributeType javadoc for available
				 * types
				 * 
				 * Only Boolean attributes are supported...
				 */
				if (attribute.getType() == 3) {
					hasBooleanAttributes = true;
					// Add this attribute like a optional feature
					try {
						Optional optional = _fmlFactory.createOptional();
						((OptionalImpl) optional).setName(_nameSpace.addName(attribute.getID(), path, 2));
						((ProductionImpl) production).getFeatures().add(optional);
					} catch (NameProblemException e) {
						// for debug purpose. If the translator is correct, that
						// exception shouldn't been thrown.
						e.printStackTrace();
					}
					// Attribute ignored
				} else
					_warnings.add("Warning : The attribute " + path + "." + attribute.getID() + " of type "
							+ getStringEquivalentToAttributeType(attribute.getType())
							+ " isn't supported and thus ignored. Only Boolean attributes are supported by the translator!");
			}
		}

		if (featureTVL.hasChildrenFeatures()) {

			/*
			 * Cardinality & number of children. Used in the cardinality
			 * transformation rules.
			 */
			Integer minCardinality = Integer.decode(featureTVL.getMinGroupCardinality());
			Integer maxCardinality = Integer.decode(featureTVL.getMaxGroupCardinality());
			Vector<Feature> childrenFeaturesTVL = featureTVL.getChildrenFeatures();
			Integer nChildren = childrenFeaturesTVL.size();

			/*
			 * Which parental relation do we have ?
			 * relType = 0 -> Mandatory / Optional (default)
			 * relType = 1 -> XOR Group
			 * relType = 2 -> OR Group
			 * relType = 3 -> Mandatory / Optional + Contraints to replace
			 * special cardinality groups - ex : [2..3] with 5 children.
			 */
			Integer relType = 0;

			// FAMILIAR internal representation object initialisation.
			Optional optional = null;
			Mandatory mandatory = null;
			Xorgroup xorGroup = _fmlFactory.createXorgroup();
			Orgroup orGroup = _fmlFactory.createOrgroup();

			// Cardinality transformation rules
			// [x..x] - where x = #children - AND -> Mandatory / Optional
			// relations
			if (minCardinality == maxCardinality && minCardinality == nChildren)
				relType = 0;
			// [1..1] - XOR
			else if (minCardinality == maxCardinality && minCardinality == 1 && nChildren > 1)
				relType = 1;
			// [1..x] - where x = #children - OR
			else if (minCardinality != maxCardinality && minCardinality == 1 && maxCardinality == nChildren)
				relType = 2;
			// special cardinality groups
			else
				relType = 3;

			Mandatory tempGroup = null;
			Production subTempProduction = null;
			if (relType != 0 && hasBooleanAttributes) {
				/*
				 * Use of a temp group when there is xor - or - special
				 * cardinality groups because multi-group isn't supported. This
				 * permit reasoning about boolean attributes internally.There
				 * is thus a need of cleaning the final productions for not
				 * showing internal transformations to the user.
				 * 
				 * A
				 * |\
				 * Temp_Feature Boolean_Feature
				 * |
				 * Special group (or / xor / special card)
				 */
				tempGroup = _fmlFactory.createMandatory();
				String nameTempGroup = "";
				try {
					// Creates a new name for a temp feature
					nameTempGroup = _nameSpace.addName("", path, 1);
					tempGroup.setName(nameTempGroup);
				} catch (NameProblemException e) {
					e.printStackTrace();
				}
				((ProductionImpl) production).getFeatures().add(tempGroup);

				// Temp production
				subTempProduction = _fmlFactory.createProduction();
				((ProductionImpl) subTempProduction).setName(nameTempGroup);
			}

			Iterator<Feature> iterator = childrenFeaturesTVL.iterator();
			Feature childrenTVL = null;

			while (iterator.hasNext()) {
				childrenTVL = iterator.next();

				// Child is a DAG -> ignored.
				if (childrenTVL.isShared())
					_warnings.add("The TVL feature " + childrenTVL.getID() + " is a shared feature. This characteristic is ignored!");

				switch (relType) {
				// relType = 0 -> Mandatory / Optional
				case 0:
					Child child = null;
					if (childrenTVL.isOptionnal()) {
						optional = _fmlFactory.createOptional();
						try {
							((OptionalImpl) optional).setName(_nameSpace.addName(childrenTVL.getID(), path));
						} catch (NameProblemException e) {
							e.printStackTrace();
						}
						child = optional;
					} else {
						mandatory = _fmlFactory.createMandatory();
						try {
							((MandatoryImpl) mandatory).setName(_nameSpace.addName(childrenTVL.getID(), path));
						} catch (NameProblemException e) {
							e.printStackTrace();
						}
						child = mandatory;
					}
					((ProductionImpl) production).getFeatures().add(child);
					break;
				// relType = 1 -> XOR Group
				case 1:
					try {
						xorGroup.getFeatures().add(_nameSpace.addName(childrenTVL.getID(), path));
					} catch (NameProblemException e) {
						e.printStackTrace();
					}
					break;
				// relType = 2 -> OR Group
				case 2:
					try {
						orGroup.getFeatures().add(_nameSpace.addName(childrenTVL.getID(), path));
					} catch (NameProblemException e) {
						e.printStackTrace();
					}
					break;
				// special cardinality groups : each child optional
				case 3:
					optional = _fmlFactory.createOptional();
					try {
						((OptionalImpl) optional).setName(_nameSpace.addName(childrenTVL.getID(), path));
					} catch (NameProblemException e) {
						e.printStackTrace();
					}
					if (!hasBooleanAttributes)
						((ProductionImpl) production).getFeatures().add(optional);
					else
						((ProductionImpl) subTempProduction).getFeatures().add(optional);
					break;

				}
			}

			// Add the sub-xor group to production
			if (relType == 1) {
				if (!hasBooleanAttributes)
					((ProductionImpl) production).getFeatures().add(xorGroup);
				else
					((ProductionImpl) subTempProduction).getFeatures().add(xorGroup);
				// Add the sub-or group to production
			} else if (relType == 2) {
				if (!hasBooleanAttributes)
					((ProductionImpl) production).getFeatures().add(orGroup);
				else
					((ProductionImpl) subTempProduction).getFeatures().add(orGroup);
				// Add cardinality constraint
			} else if (relType == 3) {
				CNF constraint;
				// Calculates a constraint representing the special cardinality
				// relation
				if (!hasBooleanAttributes)
					constraint = cardIJ(featureTVL, production);
				else
					// Put the constraint at the temp production
					constraint = cardIJ(featureTVL, subTempProduction);
				if (constraint != null)
					// Add the calculated constraint to the feature model.
					((FeatureModelImpl) _fm).getExpr().add(constraint);
			}

			// add the production to the FAMILIAR feature model.
			((FeatureModelImpl) _fm).getFeatures().add(production);
			// add the temp production to the FAMILIAR feature model.
			if (hasBooleanAttributes && relType != 0)
				((FeatureModelImpl) _fm).getFeatures().add(subTempProduction);

			// Execute the current function on each child to create the next
			// production.
			iterator = childrenFeaturesTVL.iterator();
			while (iterator.hasNext()) {
				childrenTVL = iterator.next();
				generateEntryProduction(childrenTVL, path);
			}
		}
		if (featureTVL.hasConstraints())
			// Called after creating the children for keeping references.
			generateConstraints(featureTVL, path);

	}

	/**
	 * 
	 * Attribute type :<br />
	 * UNKNOWN = 0;<br />
	 * INT = 1;<br />
	 * REAL = 2;<br />
	 * BOOL = 3;<br />
	 * ENUM = 4;<br />
	 * STRUCT = 5;<br />
	 * STRUCT_FIELD = 6;<br />
	 * USER_CREATED = 7;<br />
	 * 
	 * @param type
	 *            Type from a TVL Feature / Feature Symbol / ...
	 * @return A string containing the english representation of the given type.
	 */
	private String getStringEquivalentToAttributeType(Integer type) {
		String out = "";
		switch (type) {
		case 0:
			out = "Unknown";
			break;
		case 1:
			out = "Integer";
			break;
		case 2:
			out = "Real";
			break;
		case 3:
			out = "Boolean";
			break;
		case 4:
			out = "Enum";
			break;
		case 5:
			out = "Struct";
			break;
		case 6:
			out = "Struct Field";
			break;
		case 7:
			out = "User created";
			break;
		}
		return out;
	}

	/**
	 * Side-effect :<br />
	 * Modify the FAMILIAR feature model <i>_fm</i> by adding the translated
	 * constraints.<br />
	 * Modify the warnings list <i>_warnings</i> if there are constraints
	 * ignored.
	 * 
	 * @param featureTVL
	 *            The feature containing constraints you want to translate.
	 * @param path
	 *            The path of <i>featureTVL</i>.
	 */
	private void generateConstraints(Feature featureTVL, String path) {
		Iterator<Constraint> constraints = featureTVL.getConstraints().iterator();
		CNF constraintFAMILIAR = null;
		while (constraints.hasNext()) {
			Constraint constraint = constraints.next();

			if (!(constraint.getExpression() instanceof BooleanExpression))
				_warnings.add("Expression ignored : Not a boolean expression!");
			else {
				BooleanExpression simplifiedForm = ((BooleanExpression) constraint.getExpression());
				/*
				 * Some TVL expression are BooleanExpression but contains reals.
				 * In that case, these expressions must be ignored.
				 * 
				 * Example : a == b is a boolean expression. a and b are boolean
				 * OR integer.
				 */
				if (!isOnlyComposedOfBooleanExpression(simplifiedForm))
					_warnings.add("Expression ignored : After research, that's not a boolean expression!");
				else {
					/*
					 * Simplified structure :
					 * - No AndAggExpression
					 * - No OrAggExpression
					 * - No XorAggExpression
					 * - No ExcludesExpression
					 * - No RequireExpression
					 * - No IfAndOnlyIfExpression
					 * - No EqualsExpression
					 * - No ImpliesExpression
					 */
					simplifiedForm = simplifiedForm.toSimplifiedForm();
					simplifiedForm = simplifiedForm.distributeDisjunctions();
					simplifiedForm = simplifiedForm.distributeNegations();
					// Translate the constraint
					constraintFAMILIAR = translateConstraintExpressionTree(simplifiedForm);
					if (constraintFAMILIAR != null)
						// Adding the constraint to the feature model
						_fm.getExpr().add(constraintFAMILIAR);
					else
						_warnings.add("Constraint not added due to a partial incompatible expression.");
				}
			}

		}
	}

	/**
	 * Recursive function.
	 * 
	 * @param be
	 *            The expression to verify.
	 * @return True if the given <i>be</i> expression is only composed of
	 *         booleans. False otherwise.
	 */
	private boolean isOnlyComposedOfBooleanExpression(BooleanExpression be) {
		if (be instanceof LowerExpression || be instanceof GreaterExpression || be instanceof GEQExpression || be instanceof LEQExpression
				|| be instanceof LowerExpression)
			return false;
		else if (be instanceof LongIDExpression) {
			try {
				if (((LongIDExpression) be).getType() != 3)
					return false;

				/*
				 * String[] nameSplitted = ((LongIDExpression)
				 * be).getLongID().split("\\.");
				 * int i = 0;
				 * String path = "";
				 * if (nameSplitted.length > 1)
				 * while (i < nameSplitted.length - 1) {
				 * if (path != "")
				 * path = path + ".";
				 * path = path + nameSplitted[i];
				 * i++;
				 * }
				 * String name = nameSplitted[nameSplitted.length - 1];
				 * String fname = _nameSpace.getFAMILIARName(path, name);
				 * if (fname == null || fname.compareTo("") == 0)
				 * return false;
				 * return true;
				 */

			} catch (Exception e) {
				return false;
			}
		} else if (be instanceof InExpression) {
			Expression expression = ((InExpression) be).getExpression();
			if (!(be instanceof BooleanExpression))
				return false;
			return isOnlyComposedOfBooleanExpression((BooleanExpression) expression);
		} else if (be instanceof EqualsExpression) {
			Expression expression1 = ((EqualsExpression) be).getExpression1();
			Expression expression2 = ((EqualsExpression) be).getExpression2();
			if (!(expression1 instanceof BooleanExpression) || !(expression2 instanceof BooleanExpression))
				return false;
			return isOnlyComposedOfBooleanExpression((BooleanExpression) expression1)
					&& isOnlyComposedOfBooleanExpression((BooleanExpression) expression2);
		} else if (be instanceof NotEqualsExpression) {
			Expression expression1 = ((NotEqualsExpression) be).getExpression1();
			Expression expression2 = ((NotEqualsExpression) be).getExpression2();
			if (!(expression1 instanceof BooleanExpression) || !(expression2 instanceof BooleanExpression))
				return false;
			return isOnlyComposedOfBooleanExpression((BooleanExpression) expression1)
					&& isOnlyComposedOfBooleanExpression((BooleanExpression) expression2);
		}
		// type ˆ explorer
		return true;
	}

	/**
	 * Recursive function.
	 * 
	 * Side-effect : <br />
	 * <i>_warnings</i> is modified for adding messages about ignored
	 * structures.
	 * 
	 * @param expression
	 *            TVL expression to translate to a FAMILIAR expression.
	 * @return FAMILIAR expression resulting of the translation of the given
	 *         <i>expression</i>
	 */
	private CNFExpression translateConstraintExpressionTree(Expression expression) {
		if (expression instanceof OrExpression) {
			Or_expr orFAMILIAR = _fmlFactory.createOr_expr();
			CNFExpression left = translateConstraintExpressionTree(((OrExpression) expression).getExpression1());
			CNFExpression right = translateConstraintExpressionTree(((OrExpression) expression).getExpression2());
			if (left == null || right == null)
				return null;
			((Or_exprImpl) orFAMILIAR).setLeft(left);
			((Or_exprImpl) orFAMILIAR).setRight(right);
			return orFAMILIAR;
		} else if (expression instanceof AndExpression) {
			And_expr andFAMILIAR = _fmlFactory.createAnd_expr();
			CNFExpression left = translateConstraintExpressionTree(((AndExpression) expression).getExpression1());
			CNFExpression right = translateConstraintExpressionTree(((AndExpression) expression).getExpression2());
			if (left == null || right == null)
				return null;
			((And_exprImpl) andFAMILIAR).setLeft(left);
			((And_exprImpl) andFAMILIAR).setRight(right);
			return andFAMILIAR;
		} else if (expression instanceof NotExpression) {
			Neg_expr neg = _fmlFactory.createNeg_expr();
			CNFExpression sub = translateConstraintExpressionTree(((NotExpression) expression).getExpression());
			if (sub == null)
				return null;
			neg.setExpr(sub);
			return neg;
		} else if (expression instanceof FalseExpression) {
			CNFExpression cexpr = _fmlFactory.createCNFExpression();
			cexpr.setName("false");
			return cexpr;
		} else if (expression instanceof ImpliesExpression) {
			Impl_expr impl = _fmlFactory.createImpl_expr();
			CNFExpression left = translateConstraintExpressionTree(((ImpliesExpression) expression).getExpression1());
			CNFExpression right = translateConstraintExpressionTree(((ImpliesExpression) expression).getExpression2());
			if (left == null || right == null)
				return null;
			((Impl_exprImpl) impl).setLeft(left);
			((Impl_exprImpl) impl).setLeft(right);
			return impl;
		} else if (expression instanceof LongIDExpression) {
			CNFExpression cexpr = _fmlFactory.createCNFExpression();

			String[] nameSplitted = ((LongIDExpression) expression).getLongID().split("\\.");
			int i = 0;
			String path = "";
			if (nameSplitted.length > 1)
				while (i < nameSplitted.length - 1) {
					if (path != "")
						path = path + ".";
					path = path + nameSplitted[i];
					i++;
				}
			String name = nameSplitted[nameSplitted.length - 1];

			// cexpr.setName(((LongIDExpression) expression).getLongID());

			try {
				cexpr.setName(_nameSpace.getFAMILIARName(path, name));
			} catch (NameProblemException e) {
				e.printStackTrace();
			}

			// cexpr.setName(((LongIDExpression) expression).getLongID());

			return cexpr;
		} else if (expression instanceof ParenthesesExpression) {
			return null;
		} else if (expression instanceof TrueExpression) {
			CNFExpression cexpr = _fmlFactory.createCNFExpression();
			cexpr.setName("true");
			return cexpr;
		} else {
			_warnings.add("Unsuported type constraint : " + expression.getClass().toString());
			return null;
		}
	}

	/**
	 * Represent (not g v (GTseq(fn,...,fi) ^ LTseq(fn,...fj))) from Metzger
	 * 2007 p250. This formula creates a boolean constraint equivalent of a i-j
	 * card special group relation.
	 * 
	 * @param top
	 *            TVL feature from where you call this function.
	 * @param currentFAMILIARProduction
	 *            The FAMILIAR production from where you call this function.
	 *            This
	 *            production is modified when artificial features are created.
	 * @return An OPTIMAL constraint representing special cardinality relation.
	 */
	private CNF cardIJ(Feature top, Production currentFAMILIARProduction) {

		FeatureSymbol topSymbol = null;
		try {
			topSymbol = _parser.getFeatureSymbol(top.getID());
			return generateAndExpressionFromTVLSolution(0, BooleanForm.generateCNFRules(topSymbol), currentFAMILIARProduction);
		} catch (Exception e) {
			// isn't valid etc... Should never be thrown.
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Used to translate a TVL constraint representation (int[][]) into a
	 * FAMILIAR constraint representation.
	 * The first dimension of this array represent AND relations (so you call
	 * this function first).
	 * The second dimension of this array represent OR relations.
	 * 
	 * @example The tree is composed like that :<br />
	 *          And<br />
	 *          | \<br />
	 *          OR And<br />
	 *          .........<br />
	 *          There is NO null left or right.
	 * 
	 * @param alreadyDone
	 *            Initialize it to 0.
	 * @param solution
	 *            TVL int array representing a constraint.
	 * @param currentFAMILIARProduction
	 *            The production from where you call this function. This
	 *            production is modified when artificial features are created.
	 * @return A CNFExpression representing the translated int tab to a FAMILIAR
	 *         constraint.
	 */
	private CNFExpression generateAndExpressionFromTVLSolution(int alreadyDone, int[][] solution, Production currentFAMILIARProduction) {
		// End of the recursion
		if (alreadyDone == solution.length)
			return null;

		CNFExpression cleft = generateOrExpressionFromTVLSolution(0, solution[alreadyDone], currentFAMILIARProduction);
		CNFExpression cright = generateAndExpressionFromTVLSolution(alreadyDone + 1, solution, currentFAMILIARProduction);
		// No null left.
		if (solution[alreadyDone].length == 0 || cleft == null)
			return cright;
		// No null right.
		if (cright == null)
			return cleft;

		CNF andExpression = _fmlFactory.createAnd_expr();
		// Left : CNFExpression
		((And_exprImpl) andExpression).setLeft(cleft);
		// Right : CNFExpression or And_expr
		((And_exprImpl) andExpression).setRight(cright);

		return (CNFExpression) andExpression;
	}

	/**
	 * Used to translate a TVL or constraints representation (int[]) into a
	 * FAMILIAR constraint representation.
	 * 
	 * @example The tree is composed like that :<br />
	 *          OR<br />
	 *          | \<br />
	 *          CNFExpression OR<br />
	 * <br />
	 *          There is no null left or right.
	 * 
	 * @param alreadyDone
	 *            Initialise it to 0.
	 * @param solution
	 *            TVL int array representing a constraint.
	 * @param currentFAMILIARProduction
	 *            The production from where you call this function. This
	 *            production is modified when artificial features are created.
	 * @returnA CNFExpression representing the translated int tab to a FAMILIAR
	 *          constraint.
	 */
	private CNFExpression generateOrExpressionFromTVLSolution(int alreadyDone, int[] solution, Production currentFAMILIARProduction) {
		// End of the recursion
		if (alreadyDone == solution.length)
			return null;

		// Is used to mark if the current symbol is negative
		boolean neg = false;
		if (solution[alreadyDone] < 0) {
			neg = true;
			// The IDGenerator don't support negative symbol.
			solution[alreadyDone] = -solution[alreadyDone];
		}
		// Conversion of the int value to correspondent symbol.
		Symbol symbol = IDGenerator.getInstance().getSymbol(solution[alreadyDone]);

		CNF orExpression = _fmlFactory.createOr_expr();
		CNFExpression leftExpression;
		if (neg) {
			/**
			 * OR
			 * | \
			 * NOT OR
			 * |
			 * CNFExpression
			 */
			leftExpression = _fmlFactory.createNeg_expr();
			CNFExpression cexpr = _fmlFactory.createCNFExpression();
			if (symbol == null)
				// Creating tempory var.
				cexpr.setName("Temp_Var_" + solution[alreadyDone]);
			else
				cexpr.setName(symbol.getID());
			((Neg_exprImpl) leftExpression).setExpr(cexpr);
		} else {
			/**
			 * OR
			 * | \
			 * CNFExpression OR
			 */
			leftExpression = _fmlFactory.createCNFExpression();
			if (symbol == null)
				// Creating tempory var.
				leftExpression.setName("Temp_Var_" + solution[alreadyDone]);
			else
				leftExpression.setName(symbol.getID());
		}
		if (symbol == null)
			/**
			 * Creating temp var implies adding tempory features to the
			 * production : currentFAMILIARProduction.
			 */
			addOptionalFeatureToProductionNoDuplicates(currentFAMILIARProduction, "Temp_Var_" + solution[alreadyDone]);

		CNFExpression cright = generateOrExpressionFromTVLSolution(alreadyDone + 1, solution, currentFAMILIARProduction);
		// No null right.
		if (cright == null)
			// Left always exists.
			return leftExpression;

		// Final Or_expr object.
		((Or_exprImpl) orExpression).setLeft(leftExpression);
		((Or_exprImpl) orExpression).setRight(cright);
		return (CNFExpression) orExpression;
	}

	/**
	 * Add the <i>name</i> feature to <i>production</i> if the name doesn't
	 * already exists.
	 * 
	 * This function is used to add temp feature to the production without
	 * thinking about duplicates.
	 * 
	 * Called by generateOrExpressionFromTVLSolution.
	 * 
	 * @param production
	 *            The production where you want to add a feature.
	 * @param name
	 *            The name of the feature to add.
	 */
	private void addOptionalFeatureToProductionNoDuplicates(Production production, String name) {
		Iterator<Child> iterator = production.getFeatures().iterator();
		Boolean stop = false;
		while (iterator.hasNext() && stop == false)
			if (((OptionalImpl) iterator.next()).getName().compareTo(name) == 0)
				stop = true;
		if (!stop) {
			Optional newTempFeature = _fmlFactory.createOptional();
			newTempFeature.setName(name);
			production.getFeatures().add(newTempFeature);
		}
	}

	/**
	 * 
	 * @return The FAMILIAR internal representation of the TVL feature model.
	 * @throws Exception
	 */
	public FeatureModel getFAMILIARFeatureModel() throws Exception {
		if (_fm == null)
			this.translateToFAMILIAR();
		return _fm;
	}

	/**
	 * 
	 * @return A string containing the translation of the TVL normal form into
	 *         FAMILIAR FML representation.
	 * @throws Exception
	 */
	public String getFAMILIARFMLOutput() throws Exception {
		return new FMLFeatureModelWriter(getFAMILIARFeatureModel()).toString();
	}

	/**
	 * Put the parser output to the specified (<i>path</i>) file.
	 * 
	 * @param path
	 *            Path to the file to write.
	 * @return True if the operation succeed. False otherwise.
	 */
	public boolean getFAMILIARFMLOuputToFile(String path) {
		try {
			// Create file
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write("fm = " + getFAMILIARFMLOutput());
			// Close the output stream
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @return The string representation of the TVL Normal Form Object.
	 * @throws Exception
	 */
	public String getTVLNormalFormToString() throws Exception {
		return _parser.getNormalFormToString();
	}

	/**
	 * 
	 * @return The string representation of the TVL Boolean Form Object.
	 * @throws Exception
	 */
	public String getTVLBooleanFormToString() throws Exception {
		return _parser.getBooleanFormToString();
	}

	/**
	 * @example
	 *          Input of the parser : "root A{group oneOf{C,D}}"<br />
	 *          Will return in console :<br />
	 *          Set<br />
	 *          |-Set<br />
	 *          | |- A - C<br />
	 *          |-Set<br />
	 *          | |- A - D
	 * 
	 * @return A set of solutions. A solution is a set of String representing
	 *         the name of each feature. There is no duplicates (definition of
	 *         Set). If the Feature Model is unsatisfiable, an empty Set is
	 *         returned. So, set.size() == 0 -> true
	 * @throws TimeoutException
	 *             If the solver compute for too long.
	 * @throws FeatureModelInvalidException
	 *             Thrown if the TVL input contain an invalid feature model.
	 * @throws UnsatisfiableModelException
	 */
	public Set<Set<String>> getTVLFilteredSolutions() throws TimeoutException, FeatureModelInvalidException {
		
		int[][] solutions = _parser.getSolutions();
		System.err.println("solutions=" + _parser.countSolutions()); 
		Set<Set<String>> out = null;
		if (solutions != null) {
			out = new HashSet<Set<String>>();
			Set<String> inner = null;
			for (int i = 0; i < solutions.length; i++) {
				inner = new HashSet<String>();
				for (int j = 0; j < solutions[i].length; j++) {
					if (solutions[i][j] >= 0) {
						Symbol symbol = IDGenerator.getInstance().getSymbol(solutions[i][j]);
						if (symbol != null && !symbol.getID().startsWith("ArtificialParent"))
							inner.add(symbol.getID());
					}
				}
				out.add(inner);
			}
		}
		return out;
	}

	/**
	 * 
	 * @return The number of solutions given by the TVL parser.
	 */
	public long getTVLNumberSolutions() {
		try {
			return _parser.countSolutions();
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * Ouput in the console the TVL solutions of the input passed to the
	 * translator.
	 * 
	 * @example
	 *          Input of the parser : "root A{group oneOf{C,D}}"<br />
	 *          Will ouput in console :<br />
	 *          AC<br />
	 *          AD
	 */
	public void getTVLFilteredSolutionsToString() {
		try {
			Set<Set<String>> solutionsTVL = getTVLFilteredSolutions();
			if (solutionsTVL == null)
				System.out.println("No Solutions");
			else {
				if (solutionsTVL.size() == 0)
					System.out.println("No solution. The Feature Model is unsatisfiable.");
				else {
					Iterator<Set<String>> iterator = solutionsTVL.iterator();
					while (iterator.hasNext()) {
						Iterator<String> iterator2 = iterator.next().iterator();
						System.out.print("{");
						while (iterator2.hasNext()) {
							System.out.print(iterator2.next());
							if (iterator2.hasNext())
								System.out.print(", ");
						}
						System.out.println("}");
					}
				}
			}
		} catch (TimeoutException e) {
			System.err.println("Solver Timeout.");
		} catch (FeatureModelInvalidException e) {
			System.out.println("Invalid FM.");
		}
	}

	/**
	 * 
	 * @return True if there are warnings messages. So there are ignored TVL
	 *         structures.
	 */
	public Boolean hasWarnings() {
		return _warnings.size() > 0;
	}

	/**
	 * 
	 * @return A string containing the representation of all warnings. Each
	 *         warning is on the next line (\r\n)
	 */
	public String getWarningsToString() {
		Iterator<String> iterator = _warnings.iterator();
		String warningsStr = "";
		while (iterator.hasNext())
			warningsStr = warningsStr + iterator.next() + "\r\n";
		return warningsStr;
	}

	/*
	 * @return
	 * 
	 * @throws TimeoutException
	 * 
	 * @throws FeatureModelInvalidException
	 */

	/*
	 * public Set<Set<String>> getTVLFilteredSolutions() throws
	 * TimeoutException, FeatureModelInvalidException {
	 * Set<Set<String>> solutionsTVL = getTVLSolutions();
	 * Object[] solutions = solutionsTVL.toArray();
	 * int i = 0;
	 * int j;
	 * // Remove duplicates
	 * while (i < solutions.length) {
	 * j = i + 1;
	 * while (j < solutions.length) {
	 * if (((Set<String>) solutions[i]).containsAll((Set<String>) solutions[j])
	 * && ((Set<String>) solutions[j]).containsAll(((Set<String>)
	 * solutions[i]))) {
	 * System.out.println("Remove : " + solutions[j].toString());
	 * solutionsTVL.remove(solutions[j]);
	 * }
	 * j++;
	 * }
	 * i++;
	 * }
	 * return solutionsTVL;
	 * }
	 */

}