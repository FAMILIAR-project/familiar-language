/*
	This file is part of FaMaTS.

    FaMaTS is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FaMaTS is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FaMaTS.  If not, see <http://www.gnu.org/licenses/>.

 */
package es.us.isa.ChocoReasoner.attributed;

import static choco.Choco.and;
import static choco.Choco.constant;
import static choco.Choco.div;
import static choco.Choco.eq;
import static choco.Choco.geq;
import static choco.Choco.gt;
import static choco.Choco.ifOnlyIf;
import static choco.Choco.ifThenElse;
import static choco.Choco.implies;
import static choco.Choco.leq;
import static choco.Choco.lt;
import static choco.Choco.makeIntVar;
import static choco.Choco.minus;
import static choco.Choco.mod;
import static choco.Choco.mult;
import static choco.Choco.neg;
import static choco.Choco.neq;
import static choco.Choco.not;
import static choco.Choco.or;
import static choco.Choco.plus;
import static choco.Choco.power;
import static choco.Choco.sum;
import inria.FAMILIAR.Model.ConstantIntConverter;
import inria.FAMILIAR.Model.Feature;
import inria.FAMILIAR.Model.GenericAttribute;
import inria.FAMILIAR.Model.Relation;
import inria.FAMILIAR.Model.Domain.Cardinality;
import inria.FAMILIAR.Model.Domain.Domain;
import inria.FAMILIAR.Model.Domain.IntegerDomain;
import inria.FAMILIAR.Model.Domain.KeyWords;
import inria.FAMILIAR.Model.Domain.ObjectDomain;
import inria.FAMILIAR.Model.Domain.Range;
import inria.FAMILIAR.Model.Domain.RangeIntegerDomain;
import inria.FAMILIAR.Model.Domain.SetIntegerDomain;
import inria.FAMILIAR.Reasoning.FeatureModelReasoner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import choco.cp.model.CPModel;
import choco.kernel.model.Model;
import choco.kernel.model.constraints.Constraint;
import choco.kernel.model.variables.integer.IntegerExpressionVariable;
import choco.kernel.model.variables.integer.IntegerVariable;
import es.us.isa.util.Node;
import es.us.isa.util.Tree;

public class ChocoReasoner extends FeatureModelReasoner {

	protected Map<String, Feature> features;
	protected Map<String, IntegerVariable> variables;
	protected Map<String, inria.FAMILIAR.Model.GenericAttribute> atts;
	protected Map<String, IntegerVariable> attVars;
	protected Map<String, Constraint> dependencies;
	protected Map<String, IntegerExpressionVariable> setRelations;
	protected Model problem;
	protected boolean reify;
	protected ChocoParser chocoParser;
	protected List<Constraint> configConstraints;
	protected ConstantIntConverter constantIntConverter = new ConstantIntConverter();
	public ChocoReasoner() {
		super();
		reset();
	}

	@Override
	public void reset() {
		features = new HashMap<String, Feature>();
		variables = new HashMap<String, IntegerVariable>();
		atts = new HashMap<String, GenericAttribute>();
		attVars = new HashMap<String, IntegerVariable>();
		problem = new CPModel();
		dependencies = new HashMap<String, Constraint>();
		setRelations = new HashMap<String, IntegerExpressionVariable>();
		reify = false;
		configConstraints = new ArrayList<Constraint>();
		chocoParser = new ChocoParser();
	}

	public Model getProblem() {
		return problem;
	}

	public void setProblem(Model problem) {
		this.problem = problem;
	}


	public void addRoot(Feature feature) {
		IntegerVariable root = variables.get(feature.getName());
		problem.addConstraint(eq(root, 1));
	}

	public void addMandatory(Relation rel,
			Feature child, Feature parent) {

		Constraint mandatoryConstraint = createMandatory(rel, child, parent);
		problem.addConstraint(mandatoryConstraint);

	}

	@Override
	public void addOptional(Relation rel,
			Feature child, Feature parent) {

		Constraint optionalConstraint = createOptional(rel, child, parent);
		problem.addConstraint(optionalConstraint);

	}

	@Override
	public void addCardinality(Relation rel,
			Feature child, Feature parent,
			Iterator<Cardinality> cardinalities) {

		Constraint cardConstraint = createCardinality(rel, child, parent,
				cardinalities);
		problem.addConstraint(cardConstraint);

	}

	@Override
	public void addRequires(Relation rel,
			Feature origin,
			Feature destination) {

		Constraint requiresConstraint = createRequires(rel, origin, destination);
		problem.addConstraint(requiresConstraint);

	}

	@Override
	public void addExcludes(Relation rel,
			Feature origin, Feature dest) {

		Constraint excludesConstraint = createExcludes(rel, origin, dest);
		problem.addConstraint(excludesConstraint);

	}

	@Override
	public void addFeature(Feature f,
			Collection<Cardinality> cards) {

		createFeature(f, cards);
		try {
			addAttributes(f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void createFeature(Feature f,
			Collection<Cardinality> cards) {
		features.put(f.getName(), f); // Save the feature
		Iterator<Cardinality> cardIt = cards.iterator();// Looks for all the
		// cardinality and save
		// it
		IntegerVariable var;
		SortedSet<Integer> vals = new TreeSet<Integer>();

		while (cardIt.hasNext()) {
			Cardinality card = cardIt.next();
			int min = card.getMin();
			int max = card.getMax();
			for (int i = min; i <= max; i++) {
				vals.add(i);
			}
		}

		// we don't have to check if it is already inserted into the set,
		// because
		// no repeated elements are allowed.
		vals.add(0);
		// we convert the ordered set to an array of ints
		int[] domain = new int[vals.size()];
		Iterator<Integer> itv = vals.iterator();
		int pos = 0;
		while (itv.hasNext()) {
			domain[pos] = itv.next();
			pos++;
		}
		var = makeIntVar(f.getName(), domain);
		problem.addVariable(var);
		this.variables.put(f.getName(), var);
	}

	private void addAttributes(Feature f) throws Exception {

		IntegerVariable varFeat = variables.get(f.getName());
		createAttributes(f);
		// una vez procesados todos los atributos, si la feature esta presente
		// tenemos en cuenta las invariantes
		Iterator<? extends inria.FAMILIAR.Model.Constraint> itInv = f
				.getInvariants().iterator();
		while (itInv.hasNext()) {

			inria.FAMILIAR.Model.Constraint inv = itInv.next();
			Constraint c = createInvariant(f, varFeat, inv);
			problem.addConstraint(c);

		}

	}

	protected Constraint createInvariant(Feature f,
			IntegerVariable varFeat,
			inria.FAMILIAR.Model.Constraint inv) {

		Constraint c = chocoParser.translateToInvariant(inv.getAST(), f
				.getName());
		// si y solo si la feature esta presente, tendremos en cuenta la
		// invariante
		Constraint reifiedInvariant = implies(geq(varFeat, 1), c);
		dependencies.put(inv.getName(), reifiedInvariant);
		return reifiedInvariant;

	}

	protected void createAttributes(Feature f) throws Exception {
		IntegerVariable varFeat = variables.get(f.getName());
		Iterator<? extends GenericAttribute> it = f.getAttributes().iterator();
		while (it.hasNext()) {
			IntegerVariable attVar = null;
			GenericAttribute att = it.next();
			String attName = f.getName() + "." + att.getName();
			Domain d = att.getDomain();
			Object nullValue = att.getNullValue();
			Integer intNullVal = 0;
			if (d instanceof IntegerDomain) {
				intNullVal = att.getIntegerValue(nullValue);
				if (d instanceof RangeIntegerDomain) {
					RangeIntegerDomain rangeDom = (RangeIntegerDomain) d;
					Iterator<Range> itRanges = rangeDom.getRanges().iterator();
					int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

					while (itRanges.hasNext()) {
						Range r = itRanges.next();
						if (r.getMin() < min) {
							min = r.getMin();
						}
						if (r.getMax() > max) {
							max = r.getMax();
						}
					}

					if (intNullVal > max) {
						max = intNullVal;
					} else if (intNullVal < min) {
						min = intNullVal;
					}
					// creamos la vble con el rango
					attVar = makeIntVar(attName, min, max, "cp:bound", "cp:no_decision");
				} else if (d instanceof SetIntegerDomain) {
					SetIntegerDomain setDom = (SetIntegerDomain) d;
					List<Integer> allowedVals = new LinkedList<Integer>();
					allowedVals.addAll(setDom.getAllIntegerValues());
					allowedVals.add(intNullVal);
					// attVar = makeIntVar(attName,
					// allowedVals,"cp:bound","cp:no_decision");
					int[] valsArray = new int[allowedVals.size()];
					Iterator<Integer> itValues = allowedVals.iterator();
					int i = 0;
					while (itValues.hasNext()) {
						valsArray[i] = itValues.next();
						i++;
					}
					// attVar = makeIntVar(attName,
					// allowedVals,"cp:bound","cp:no_decision");
					attVar = makeIntVar(attName, valsArray, "cp:enum", "cp:no_decision");
				}
			} else if (d instanceof ObjectDomain) {
				intNullVal = att.getIntegerValue(nullValue);
				ObjectDomain objDom = (ObjectDomain) d;
				List<Integer> allowedVals = new LinkedList<Integer>();
				allowedVals.addAll(objDom.getAllIntegerValues());
				allowedVals.add(intNullVal);
				int[] valsArray = new int[allowedVals.size()];
				Iterator<Integer> itValues = allowedVals.iterator();
				int i = 0;
				while (itValues.hasNext()) {
					valsArray[i] = itValues.next();
					i++;
				}
				// attVar = makeIntVar(attName,
				// allowedVals,"cp:bound","cp:no_decision");
				attVar = makeIntVar(attName, valsArray, "cp:enum", "cp:no_decision");
			} else {
				throw new Exception("Unknown domain type");
			}
			// a�adimos la IntegerVariable
			attVars.put(attName, attVar);
			atts.put(attName, att);
			problem.addVariable(attVar);

			// si la feature esta presente, tenemos en cuenta el dominio. si no,
			// valor nulo
			Constraint domain = setAttributeDomain(attVar, att);
			Constraint reifiedDomain = ifThenElse(geq(varFeat, 1), domain, eq(
					attVar, intNullVal));
			problem.addConstraint(reifiedDomain);
		}
		// return varFeat;
	}

	protected Constraint setAttributeDomain(IntegerVariable var,
			GenericAttribute att) {

		Constraint res = null;
		Domain d = att.getDomain();

		if (d instanceof RangeIntegerDomain) {
			RangeIntegerDomain rangeDom = (RangeIntegerDomain) d;
			Iterator<Range> it = rangeDom.getRanges().iterator();
			if (it.hasNext()) {
				Range r = it.next();
				// var >= min && var <= max
				res = and(leq(var, r.getMax()), geq(var, r.getMin()));
			}
			while (it.hasNext()) {
				Range r = it.next();
				// var >= min && var <= max
				Constraint c = and(leq(var, r.getMax()), geq(var, r.getMin()));
				res = or(res, c);
			}
		} else if ((d instanceof SetIntegerDomain)
				|| (d instanceof ObjectDomain)) {
			// SetIntegerDomain setDom = (SetIntegerDomain)d;
			Iterator<Integer> it = d.getAllIntegerValues().iterator();
			if (it.hasNext()) {
				int i = it.next();
				res = (eq(var, i));
			}
			while (it.hasNext()) {
				int i = it.next();
				Constraint c = (eq(var, i));
				res = or(res, c);
			}
		}

		return res;

	}

	@Override
	public void addSet(Relation rel,
			Feature parent,
			Collection<Feature> children,
			Collection<Cardinality> cardinalities) {

		Constraint setConstraint = createSet(rel, parent, children,
				cardinalities);
		problem.addConstraint(setConstraint);// add only this constraint

	}



	public void createProblem() {
		this.problem = new CPModel();
	}

	public void setReify(boolean b) {
		this.reify = false;
	}

	public Map<String, IntegerVariable> getVariables() {
		return variables;
	}

	public Map<String, IntegerExpressionVariable> getSetRelations() {
		return setRelations;
	}

	public Map<String, Constraint> getRelations() {
		return dependencies;
	}

	public Feature searchFeatureByName(String id) {
		return features.get(id);
	}

	public Collection<Feature> getAllFeatures() {
		return this.features.values();
	}

	public Map<String, IntegerVariable> getAttributesVariables() {
		return attVars;
	}

	public Collection<GenericAttribute> getAllAttributes() {
		return atts.values();
	}

//	@Override
//	public void applyStagedConfiguration(Configuration conf) {
//
//		Iterator<Entry<Object, Integer>> it = conf.getElements().entrySet().iterator();
//
//		Map<String, IntegerVariable> vars = getVariables();
//		Map<String, IntegerExpressionVariable> rels = getSetRelations();
//		Map<String, IntegerVariable> atts = getAttributesVariables();
//		while (it.hasNext()) {
//			Entry<Object, Integer> e = it.next();
//			Object v = e.getKey();
//			int arg1 = e.getValue().intValue();
//			Constraint aux;
//			// the constraint is created to not have a solution for the problem
//			IntegerVariable errorVar = makeIntVar("error", 0, 0,
//					"cp:no_decision");
//			Constraint error = eq(1, errorVar);
//			if (v instanceof Feature) {
//				IntegerVariable arg0 = vars.get(((Feature)v).getName());
//				if (!getAllFeatures().contains((Feature) v)) {
//					if (e.getValue() == 0) {
//						System.err.println("The feature " + ((Feature)v).getName()
//								+ " do not exist on the model");
//					} else {
//						problem.addConstraint(error);
//						this.configConstraints.add(error);
//						System.err.println("The feature " + ((Feature)v).getName()
//								+ " do not exist, and can not be added");
//					}
//				} else {
//					aux = eq(arg0, arg1);
//					problem.addConstraint(aux);
//					this.configConstraints.add(aux);
//				}
//
//			} else if (v instanceof Relation) {
//				IntegerExpressionVariable arg0 = rels.get(((Feature)v).getName());
//				if (!getSetRelations().keySet().contains(((Feature)v).getName())) {
//					if (e.getValue() == 0) {
//						System.err.println("The relation " + ((Feature)v).getName()
//								+ "do not exist already in to the model");
//					} else {
//						problem.addConstraint(error);
//						this.configConstraints.add(error);
//						System.err.println("The relation " + ((Feature)v).getName()
//								+ "do not exist, and can not be added");
//					}
//				} else {
//					aux = eq(arg0, arg1);
//					problem.addConstraint(aux);
//					this.configConstraints.add(aux);
//				}
//			} else if (v instanceof GenericAttribute) {
//				GenericAttribute attAux = (GenericAttribute) v;
//				String attName = attAux.getFeature().getName() + "."
//						+ ((Feature)v).getName();
//				IntegerVariable arg0 = atts.get(attName);
//				if (!getAllAttributes().contains((GenericAttribute) v)) {
//					if (e.getValue() == 0) {
//						System.err.println("The attribute " + ((Feature)v).getName()
//								+ " do not exist on the model");
//					} else {
//						problem.addConstraint(error);
//						this.configConstraints.add(error);
//						System.err.println("The attribute " + ((Feature)v).getName()
//								+ " do not exist, and can not be added");
//					}
//				} else {
//					aux = eq(arg0, arg1);
//					problem.addConstraint(aux);
//					this.configConstraints.add(aux);
//				}
//			} else {
//				System.err.println("Type of the Variability element "
//						+ ((Feature)v).getName() + " not recognized");
//			}
//		}
//
//	}

//	@Override
//	public void unapplyStagedConfigurations() {
//		Iterator<Constraint> it = this.configConstraints.iterator();
//		while (it.hasNext()) {
//			Constraint cons = it.next();
//			problem.removeConstraint(cons);
//			it.remove();
//		}
//	}

	@Override
	public void addConstraint(inria.FAMILIAR.Model.Constraint c) {

		Constraint relation = createConstraint(c);
		problem.addConstraint(relation);

	}

	protected Constraint createMandatory(Relation rel,
			Feature child, Feature parent) {

		IntegerVariable childVar = variables.get(child.getName());
		IntegerVariable parentVar = variables.get(parent.getName());
		Constraint mandatoryConstraint = ifOnlyIf(eq(parentVar, 1), eq(
				childVar, 1));
		dependencies.put(rel.getName(), mandatoryConstraint);
		return mandatoryConstraint;

	}

	protected Constraint createOptional(Relation rel,
			Feature child, Feature parent) {

		IntegerVariable childVar = variables.get(child.getName());
		IntegerVariable parentVar = variables.get(parent.getName());
		Constraint optionalConstraint = implies(eq(parentVar, 0), eq(childVar,
				0));
		dependencies.put(rel.getName(), optionalConstraint);
		return optionalConstraint;

	}

	protected Constraint createCardinality(Relation rel,
			Feature child, Feature parent,
			Iterator<Cardinality> cardinalities) {

		IntegerVariable childVar = variables.get(child.getName());
		IntegerVariable parentVar = variables.get(parent.getName());

		SortedSet<Integer> cardValues = new TreeSet<Integer>();
		Iterator<Cardinality> itc = cardinalities;
		while (itc.hasNext()) {
			Cardinality card = itc.next();
			for (int i = card.getMin(); i <= card.getMax(); i++)
				cardValues.add(i);
		}
		int[] cardValuesArray = new int[cardValues.size()];
		Iterator<Integer> itcv = cardValues.iterator();
		int pos = 0;
		while (itcv.hasNext()) {
			cardValuesArray[pos] = itcv.next();
			pos++;
		}
		IntegerVariable cardinalityVar = makeIntVar(rel.getName() + "_card",
				cardValuesArray, "cp:no_decision");
		Constraint cardConstraint = ifThenElse(gt(parentVar, 0), eq(childVar,
				cardinalityVar), eq(childVar, 0));
		dependencies.put(rel.getName(), cardConstraint);
		return cardConstraint;

	}

	protected Constraint createRequires(Relation rel,
			Feature origin, Feature destination) {
		IntegerVariable originVar = variables.get(origin.getName());
		IntegerVariable destinationVar = variables.get(destination.getName());
		Constraint requiresConstraint = implies(gt(originVar, 0), gt(
				destinationVar, 0));
		dependencies.put(rel.getName(), requiresConstraint);
		return requiresConstraint;
	}

	protected Constraint createExcludes(Relation rel,
			Feature origin, Feature dest) {

		IntegerVariable originVar = variables.get(origin.getName());
		IntegerVariable destVar = variables.get(dest.getName());
		Constraint excludesConstraint = implies(gt(originVar, 0),
				eq(destVar, 0));
		dependencies.put(rel.getName(), excludesConstraint);
		return excludesConstraint;
	}

	protected Constraint createSet(Relation rel, Feature parent,
			Collection<? extends Feature> children,
			Collection<Cardinality> cardinalities) {

		Cardinality card = null;
		// This constraint should be as ifThenElse(A>0;sum(B,C) in
		// {n,m};B=0,C=0)
		// Save the parent to check the value
		IntegerVariable parentVar = variables.get(parent.getName());

		// Save the cardninality if exist from the parameter cardinalities
		SortedSet<Integer> cardValues = new TreeSet<Integer>();
		Iterator<Cardinality> itc = cardinalities.iterator();
		while (itc.hasNext()) {
			card = itc.next();
			for (int i = card.getMin(); i <= card.getMax(); i++)
				cardValues.add(i);
		}
		int[] cardValuesArray = new int[cardValues.size()];
		Iterator<Integer> itcv = cardValues.iterator();
		int pos = 0;
		while (itcv.hasNext()) {
			cardValuesArray[pos] = itcv.next();
			pos++;
		}

		IntegerVariable cardinalityVar = makeIntVar(rel.getName() + "_card",
				cardValuesArray, "cp:no_decision");// cp:no_decision
		problem.addVariable(cardinalityVar);
		// Save all children to have the posiblitily of sum them
		ArrayList<IntegerVariable> varsList = new ArrayList<IntegerVariable>();
		Iterator<? extends Feature> it = children.iterator();

		while (it.hasNext()) {
			varsList.add(variables.get(it.next().getName()));
		}

		// creates the sum constraint with the cardinality variable
		// If parent var is equal to 0 then he sum of children has to be 0
		IntegerVariable[] aux = {};
		aux = varsList.toArray(aux);

		// If parent is greater than 0, then apply the restriction
		// ifThenElse(A>0;sum(B,C) in {n,m};B=0,C=0)
		Constraint setConstraint = ifThenElse(gt(parentVar, 0), eq(sum(aux),
				cardinalityVar), eq(sum(aux), 0));
		dependencies.put(rel.getName(), setConstraint);
		setRelations.put(rel.getName(), sum(aux));
		return setConstraint;
	}

	protected Constraint createConstraint(
			inria.FAMILIAR.Model.Constraint c) {
		
		Constraint relation = chocoParser.translateToConstraint(c.getAST());
		dependencies.put(c.getName(), relation);
		return relation;

	}

	protected class ChocoParser {

		private String featName;

		public ChocoParser() {
			// count = 0;
			featName = null;
		}

		public Constraint translateToInvariant(Tree<String> ast,
				String featInvariant) {
			featName = featInvariant;
			Constraint res = null;
			Node<String> n = ast.getRootElement();
			res = translateLogical(n);
			return res;
		}

		public Constraint translateToConstraint(Tree<String> ast) {
			featName = null;
			Constraint res = null;
			Node<String> n = ast.getRootElement();
			res = translateLogical(n);
			return res;
		}

		private Constraint translateLogical(Node<String> tree) {
			// constraints logicas:
			// AND, OR, NOT, IMPLIES, IFF, REQUIRES, EXCLUDES
			// LOGICO -> LOGICO
			Constraint res = null;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			int n = children.size();
			if (n == 2) {
				if (data.equals(KeyWords.AND)) {
					Constraint e1 = translateLogical(children.get(0));
					Constraint e2 = translateLogical(children.get(1));
					res = and(e1, e2);
				} else if (data.equals(KeyWords.OR)) {
					Constraint e1 = translateLogical(children.get(0));
					Constraint e2 = translateLogical(children.get(1));
					res = or(e1, e2);
				} else if (data.equals(KeyWords.IMPLIES)
						|| data.equals(KeyWords.REQUIRES)) {
					Constraint e1 = translateLogical(children.get(0));
					Constraint e2 = translateLogical(children.get(1));
					res = implies(e1, e2);
				} else if (data.equals(KeyWords.IFF)) {
					Constraint e1 = translateLogical(children.get(0));
					Constraint e2 = translateLogical(children.get(1));
					res = ifOnlyIf(e1, e2);
				} else if (data.equals(KeyWords.EXCLUDES)) {
					// tendremos una feature > 0 a cada lado,
					// asi que hacemos un implies negando la parte dcha
					// (feat > 0) implies (not (feat > 0))
					Constraint e1 = translateLogical(children.get(0));
					Constraint aux = translateLogical(children.get(1));
					Constraint e2 = not(aux);
					res = implies(e1, e2);
				} else {
					res = translateRelational(tree);
				}
			} else if (n == 1) {
				if (data.equals(KeyWords.NOT)) {
					Constraint e1 = translateLogical(children.get(0));
					res = not(e1);
				}
			} else {
				if (isFeature(tree)) {
					IntegerVariable feat = variables.get(data);
					res = gt(feat, 0);
				}
			}
			return res;
		}

		private Constraint translateRelational(Node<String> tree) {
			// constraints relaciones:
			// >, >=, <, <=, ==, !=
			// ENTERO -> LOGICO
			Constraint res = null;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			IntegerExpressionVariable e1 = translateInteger(children.get(0));
			IntegerExpressionVariable e2 = translateInteger(children.get(1));
			if (data.equals(KeyWords.GREATER)) {
				res = gt(e1, e2);
			} else if (data.equals(KeyWords.GREATER_EQUAL)) {
				res = geq(e1, e2);
			} else if (data.equals(KeyWords.LESS)) {
				res = lt(e1, e2);
			} else if (data.equals(KeyWords.LESS_EQUAL)) {
				res = leq(e1, e2);
			} else if (data.equals(KeyWords.EQUAL)) {
				res = eq(e1, e2);
			} else if (data.equals(KeyWords.NON_EQUAL)) {
				res = neq(e1, e2);
			}
			return res;
		}

		private IntegerExpressionVariable translateInteger(Node<String> tree) {
			// constraints enteras:
			// ENTERO -> ENTERO
			IntegerExpressionVariable res = null;
			String data = tree.getData();
			List<Node<String>> children = tree.getChildren();
			if (data.equals(KeyWords.PLUS)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = plus(e1, e2);
			} else if (data.equals(KeyWords.MINUS)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = minus(e1, e2);
			} else if (data.equals(KeyWords.MULT)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = mult(e1, e2);
			} else if (data.equals(KeyWords.DIV)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = div(e1, e2);
			} else if (data.equals(KeyWords.MOD)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = mod(e1, e2);
			} else if (data.equals(KeyWords.POW)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				IntegerExpressionVariable e2 = translateInteger(children.get(1));
				res = power(e1, e2);
			} else if (data.equals(KeyWords.UNARY_MINUS)) {
				IntegerExpressionVariable e1 = translateInteger(children.get(0));
				res = neg(e1);
			} else if (isIntegerConstant(tree)) {
				// TODO por ahora, solo permitiremos constraints
				// con constantes enteras
				int value = Integer.parseInt(data);
				// IntegerVariable aux1 = makeIntVar("@aux" + count, value,
				// value);
				// IntegerVariable aux1 = Choco.makeConstantVar("@aux" + count,
				// value);
				IntegerVariable aux1 = constant(value);
				// hara falta una constraint para el valor?
				res = aux1;
				// count++;
			}
			else if (isAttribute(tree)) {
				String attName = getAttributeName(tree);
				res = attVars.get(attName);
			}
			else {
				//es una constante, usamos el intConverter
				
				Integer i = constantIntConverter.translate2Integer(tree.getData());
				if (i != null){
					res = constant(i);
				}
			}
			return res;
		}

		private String getAttributeName(Node<String> n) {
			String res = null;
			if (featName == null) {
				String s = n.getData();
				boolean b = s.equals(KeyWords.ATTRIBUTE);
				if (b && (n.getNumberOfChildren() == 2)) {
					List<Node<String>> list = n.getChildren();
					res = list.get(0).getData() + "." + list.get(1).getData();
				}
			} else {
				res = featName + "." + n.getData();
			}

			return res;
		}

		private boolean isAttribute(Node<String> n) {
			if (featName == null) {
				return n.getData().equals(KeyWords.ATTRIBUTE);
			} else {
				String aux = featName + "." + n.getData();
				Object res = atts.get(aux);
				return (res != null);
			}

		}

		private boolean isFeature(Node<String> n) {
			String s = n.getData();
			return (features.get(s) != null);
		}

		private boolean isIntegerConstant(Node<String> n) {
			String s = n.getData();
			try {
				Integer.parseInt(s);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}
		
		private boolean isStringConstant(Node<String> n) {
			if (!isFeature(n) && !isVersionConstant(n)){
				return true;
			}
			return false;
		}
		
		private boolean isVersionConstant(Node<String> n) {
			boolean b = true;
			StringTokenizer st = new StringTokenizer(n.getData());
			if (b = (st.countTokens() == 3)){
				String s1 = st.nextToken();
				String s2 = st.nextToken();
				String s3 = st.nextToken();
				b = b && isInteger(s1);
				b = b && isInteger(s2);
				b = b && isInteger(s3);
				
			}
			return b;
		}
		
		private boolean isInteger(String s){
			try {
				Integer.parseInt(s);
				return true;
			} catch (NumberFormatException e) {
				return false;
			}
		}

	}



	@Override
	public void setConstantIntConverter(
			ConstantIntConverter constantIntConverter) {
		this.constantIntConverter=constantIntConverter;
		
	}





}
