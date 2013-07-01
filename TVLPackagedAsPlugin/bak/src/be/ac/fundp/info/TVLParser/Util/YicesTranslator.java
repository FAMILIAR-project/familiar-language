package be.ac.fundp.info.TVLParser.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import be.ac.fundp.info.TVLParser.SyntaxTree.AbsExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.AndExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Attribute;
import be.ac.fundp.info.TVLParser.SyntaxTree.Constraint;
import be.ac.fundp.info.TVLParser.SyntaxTree.CountAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.EqualsExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ExcludesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Expression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ExpressionList;
import be.ac.fundp.info.TVLParser.SyntaxTree.FalseExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.Feature;
import be.ac.fundp.info.TVLParser.SyntaxTree.GEQExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.IfAndOnlyIfExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ImpliesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.InExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.IncludesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.IntExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.InverseImpliesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LEQExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LongIDExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.LowerExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.MaxAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.MinAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.MinusExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.MulAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.NotExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.OrAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.OrExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ParenthesesExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.QuestExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.RealExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SetExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.SumAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.TrueExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.XorAggExpression;
import be.ac.fundp.info.TVLParser.SyntaxTree.ZeroExpression;
import be.ac.fundp.info.TVLParser.exceptions.AmbiguousReferenceException;
import be.ac.fundp.info.TVLParser.exceptions.BadTypeUseException;
import be.ac.fundp.info.TVLParser.exceptions.ChildrenFeatureNotFoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.ExpressionTypeViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.IDEnumValuesConflictException;
import be.ac.fundp.info.TVLParser.exceptions.IllegalExpressionException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberOutOfBoundException;
import be.ac.fundp.info.TVLParser.exceptions.SetExpressionMemberViolatingAttributeTypeException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentConstructorException;
import be.ac.fundp.info.TVLParser.exceptions.SharedFeatureUsingParentSelectorException;
import be.ac.fundp.info.TVLParser.exceptions.SymbolNotFoundException;
import be.ac.fundp.info.TVLParser.symbolTables.AttributeSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.ConstraintSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.EnumSetExpressionSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.FeatureSymbol;
import be.ac.fundp.info.TVLParser.symbolTables.SetExpressionSymbol;

public class YicesTranslator {

	private int featureId = 0;
	private int attributeId = 0;
	private HashMap<String, String> featureTable = new HashMap<String, String>();
	private List<String> constraints = new Vector<String>();

	public List<String> getConstraints() {
		return constraints;
	}

	public HashMap<String, String> getFeatureTable() {
		return featureTable;
	}

	private static HashMap<Integer, String> typesMap = new HashMap<Integer, String>();
	static {
		typesMap.put(0, "unknown");
		typesMap.put(1, "int");
		typesMap.put(2, "float");
		typesMap.put(3, "bool");
		typesMap.put(4, "int");
		typesMap.put(5, "struct");
		typesMap.put(6, "struct_field");
		typesMap.put(7, "user_created");
	}

	private static HashMap<String, Integer> enumTypesMap = new HashMap<String, Integer>();
	private static int enumId = 0;

	private HashMap<String, String[]> attributeTable = new HashMap<String, String[]>();

	public HashMap<String, String[]> getAttributeTable() {
		return attributeTable;
	}

	private Feature _root;

	public YicesTranslator() {
		/*
		 * _root = root;
		 * populateFeatureTable(_root);
		 */
	}

	public void populateIdTables(FeatureSymbol f, String prefix) {
		String longId = (prefix != "") ? prefix + "_" + f.getID() : f.getID();
		if (!featureTable.containsKey(f.getID())) {
			featureTable.put(f.getID(), longId);
		}

		if (f.hasAttributesSymbols()) {

			for (AttributeSymbol as : f.getAttributesSymbols().values()) {
				if (as.hasASetExpressionSymbol()) {
					// System.out.println(as.getID() + " has a set expression");
					SetExpressionSymbol set = as.getSetExpressionSymbol();
					if (set.isAnEnumSetExpressionSymbol()) {
						EnumSetExpressionSymbol enumSet = (EnumSetExpressionSymbol) set;
						// System.out.println("which is an enum set of size " +
						// enumSet.getContainedValues().size()) ;
						String valuesConstraint = "";
						for (Expression val : enumSet.getContainedValues()) {

							if (!enumTypesMap.containsKey(val.toString()))
								enumTypesMap.put(val.toString(), enumId++);
							valuesConstraint += " (= " + longId + "_" + as.getID() + " " + enumTypesMap.get(val.toString()) + ")";
						}
						constraints.add("(or" + valuesConstraint + ")");
					}
				}
				// System.out.println(longId+"_"+as.getID() + " => " +
				// longId+"_"+as.getID() +"::"+typesMap.get(as.getType()));
				attributeTable.put(longId + "_" + as.getID(), new String[] { longId + "_" + as.getID(), typesMap.get(as.getType()) });
			}
		}

		if (f.hasChildrenFeatures()) {
			for (FeatureSymbol c : f.getChildrenFeatures().values()) {
				populateIdTables(c, longId);
			}
		}
	}

	private List<String> aggTable = new Vector<String>();

	public List<String> getAggTable() {
		return aggTable;
	}

	private int aggId = 1;

	private String addAggOp(List<Expression> expressions, String op) {
		String key = "agg_" + aggId++;
		ExpressionList list = null;
		String inConstr = "(or ";
		for (Expression e : expressions) {
			constraints.add("(" + op + " " + key + " " + translate(e) + ")");
			inConstr += " (= " + key + " " + translate(e) + ")";
			if (list == null) {
				list = new ExpressionList(e);
			} else {
				list = new ExpressionList(e, list);
			}
		}
		inConstr += ")";
		constraints.add(inConstr);
		aggTable.add(key);
		return key;
	}

	public void translateConstraints(FeatureSymbol f) {

		if (f.hasConstraintSymbols()) {
			for (ConstraintSymbol c : f.getConstraintSymbols()) {
				constraints.add(translate(c.getExpression()));
			}
		}

		if (f.hasChildrenFeatures()) {
			for (FeatureSymbol c : f.getChildrenFeatures().values()) {
				translateConstraints(c);
			}
		}

	}

	public void generateJustificationRule(FeatureSymbol f) {
		if (f.hasChildrenFeatures()) {
			for (FeatureSymbol c : f.getChildrenFeatures().values()) {
				constraints.add("(=> " + featureTable.get(c.getID()) + " " + featureTable.get(f.getID()) + ")");
				generateJustificationRule(c);
			}
		}
	}

	public String toString() {
		String result = "(benchmark\n:source{R@ph}\n:logic QF_LRA\n";

		// Declare variables for features
		for (String s : featureTable.values()) {
			result += "\n" + s;
			/*
			 * List<String> atts = attributeTable.get(featureTable.get(s));
			 * if(atts != null) {
			 * for(String att : atts)
			 * result += "\n:extrafuns (("+att+" Int))";
			 * }
			 */
		}

		result += ")";
		return result;
	}

	private static final HashMap<String, String> type2binaryOp = new HashMap<String, String>();
	static {
		type2binaryOp.put("AndExpression", "and");
		type2binaryOp.put("DivideExpression", "/");
		type2binaryOp.put("EqualsExpression", "=");
		type2binaryOp.put("GEQExpression", ">=");
		type2binaryOp.put("GreaterExpression", ">");
		type2binaryOp.put("ImpliesExpression", "=>");
		// type2binaryOp.put("InverseImpliesExpression", "=>");
		type2binaryOp.put("LEQExpression", "<=");
		type2binaryOp.put("LowerExpression", "<");
		type2binaryOp.put("MinusExpression", "-");
		type2binaryOp.put("NotEqualsExpression", "/=");
		type2binaryOp.put("OrExpression", "or");
		type2binaryOp.put("PlusExpression", "+");
		type2binaryOp.put("TimesExpression", "*");
	}

	private static final HashMap<String, String> aggType2op = new HashMap<String, String>();
	static {
		aggType2op.put("AndAggExpression", "and");
		aggType2op.put("OrAggExpression", "or");
	}

	/*
	 * Dynamic type casting and method invocation.
	 * This allows this translator to contain the full translation logic
	 * without having to add code in each Expression that might be translated.
	 * 
	 * TODO: Error handling.
	 */
	public String translate(Expression e) {
		try {
			String clazzName = e.getClass().getName();
			Class<?> clazz = Class.forName(clazzName);
			String key = clazzName;
			if (clazzName.indexOf('.') > 0)
				key = clazzName.substring(clazzName.lastIndexOf('.') + 1);
			// If binary expression, call translateBinaryExpression
			if (type2binaryOp.containsKey(key)) {
				return translateBinaryExpression(type2binaryOp.get(key), (BinaryExpression) clazz.cast(e));
			}

			if (aggType2op.containsKey(key)) {
				return genericTranslate(aggType2op.get(key), ((AggExpression) clazz.cast(e)).getExpressionList().getExpressions());
			}

			// Otherwise call specific translate
			return this.getClass().getMethod("translate", clazz).invoke(this, clazz.cast(e)).toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private String translateBinaryExpression(String op, BinaryExpression be) {
		return genericTranslate(op, be.getExpression1(), be.getExpression2());
	}

	private String genericTranslate(String op, Expression... expressions) {
		String result = "(" + op;
		for (Expression e : expressions)
			result += " " + translate(e);
		result += ")";
		return result;
	}

	private String genericTranslate(String op, Vector<Expression> expressions) {
		String result = "(" + op + " ";
		for (Expression e : expressions)
			result += " " + translate(e);
		result += ")";
		return result;
	}

	public String translate(TrueExpression e) {
		return "true";
	}

	public String translate(FalseExpression e) {
		return "false";
	}

	// public String translate(AndAggExpression e){
	// return genericTranslate("and",e.getExpressionList().getExpressions());
	// }
	//
	// public String translate(OrAggExpression e){
	// return genericTranslate("or",e.getExpressionList().getExpressions());
	// }

	public String translate(InverseImpliesExpression e) {
		return genericTranslate("=>", e.getExpression2(), e.getExpression1());
	}

	public String translate(LongIDExpression e) {
		// return "(= " + e.getNormalizedLongID().replace('.','_')+" 1)" ;
		String r1 = featureTable.get(e.getLongID().substring(e.getNormalizedLongID().lastIndexOf('.') + 1));
		if (r1 == null) {
			if (e.getLongID().indexOf('.') < 0) {
				r1 = String.valueOf(enumTypesMap.get(e.toString()));
			} else {
				String feature = e.getLongID().substring(0, e.getLongID().indexOf('.'));
				String attName = e.getLongID().substring(e.getLongID().indexOf('.') + 1);
				r1 = attributeTable.get(featureTable.get(feature) + "_" + attName)[0];
			}
		}
		return r1;
	}

	public String translate(NotExpression e) {
		return "(not " + translate(e.getExpression()) + ")";
	}

	public String translate(ParenthesesExpression e) {
		return translate(e.getExpression());
	}

	public String translate(IntExpression e) {
		return String.valueOf(e.getValue());
	}

	public String translate(ZeroExpression e) {
		return "0";
	}

	public String translate(InExpression e) {
		if (e.getSetExpression().hasAnExpressionList()) {
			if (e.getExpression() instanceof LongIDExpression) {

				ExpressionList list = e.getSetExpression().getExpressionList();
				ExpressionList myList = null;
				for (Expression e_fromList : list.getExpressions()) {
					Integer val = null;
					if (e_fromList instanceof IntExpression) {
						val = ((IntExpression) e_fromList).getValue();
					} else {
						if (e_fromList instanceof LongIDExpression)
							val = enumTypesMap.get(e_fromList.toString());
					}
					EqualsExpression ee = new EqualsExpression(e.getExpression(), new IntExpression(String.valueOf(val)));
					if (myList == null)
						myList = new ExpressionList(ee);
					else
						myList = new ExpressionList(ee, myList);
				}

				return translate(new OrAggExpression(myList));
			}
		}

		else {
			GEQExpression low = new GEQExpression(e.getExpression(), new IntExpression(e.getSetExpression().getMinExpression()));
			LEQExpression high = new LEQExpression(e.getExpression(), new IntExpression(e.getSetExpression().getMaxExpression()));
			return translate(new AndExpression(low, high));
		}
		return null;
	}

	public String translate(IncludesExpression e) {
		return translate(e.toSimplifiedForm());
	}

	public String translate(ExcludesExpression e) {
		return translate(e.toSimplifiedForm());
	}

	public String translate(CountAggExpression e) {
		return translate(e.toNormalForm());
	}

	private String translateAggOp(String op, Expression e, String tail) {
		return "(" + op + " " + translate(e) + " " + tail + ")";
	}

	public String translate(SumAggExpression e) {
		String result = "0";
		for (Expression sub : e.getExpressionList().getExpressions()) {
			result = translateAggOp("+", sub, result);
		}
		return result;
	}

	public String translate(MulAggExpression e) {
		String result = "1";
		for (Expression sub : e.getExpressionList().getExpressions()) {
			result = translateAggOp("*", sub, result);
		}
		return result;
	}

	public String translate(QuestExpression e) {
		// return translate(e.toSimplifiedForm());
		return genericTranslate("ite", e.getExpression1(), e.getExpression2(), e.getExpression3());
	}

	public String translate(RealExpression e) {
		return e.toString();
	}

	public String translate(IfAndOnlyIfExpression e) {
		return translate(e.toSimplifiedForm());
	}

	public String translate(XorAggExpression e) {
		return translate(e.toSimplifiedForm());
	}

	public String translate(AbsExpression e) {
		return translate(new QuestExpression(new LowerExpression(e.getExpression(), new ZeroExpression()), new MinusExpression(
				new ZeroExpression(), e.getExpression()), e.getExpression()));
	}

	public String translate(MaxAggExpression e) {
		return addAggOp(e.getExpressionList().getExpressions(), ">=");
	}

	public String translate(MinAggExpression e) {
		return addAggOp(e.getExpressionList().getExpressions(), "<=");
	}
}
