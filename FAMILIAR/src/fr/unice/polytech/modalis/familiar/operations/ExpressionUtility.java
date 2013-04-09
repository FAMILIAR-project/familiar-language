/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.operations;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.ExpressionUtil;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import net.sf.javabdd.BDD;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.log4j.Logger;

import FeatureName.FeatureName;


/**
 * @author mathieuacher
 * 
 */
public class ExpressionUtility {

	
	
	private static Logger _LOGGER = Logger.getLogger(ExpressionUtility.class);


	
	public static String toString(Expression<String> e) {
		switch (e.getType()) {
		case FEATURE:
			return FeatureName.quoteNeedsBe(e.getFeature().toString());
		case TRUE:
			return "1";
		case FALSE:
			return "0";
		case NOT:
			return "!" + toString(e.getLeft());
		default:
			StringBuffer sb = new StringBuffer();
			sb.append('(');
			sb.append(toString(e.getLeft()));
			sb.append(" " + e.getType() + " ");
			sb.append(toString(e.getRight()));
			sb.append(')');
			return sb.toString();
		}
	}
	
	/**
	 * @param oldvalue
	 *            the value to replace
	 * @param newvalue
	 *            the new value to set in the expression for each occurrence of
	 *            oldvalue
	 * @param expr
	 *            the expression to modify
	 */
	public static Expression<String> replaceOccurenceInExpression(
			String oldvalue, String newvalue, Expression<String> expr) {

		assert (!oldvalue.equals(ExpressionType.FALSE.toString()));
		assert (!oldvalue.equals(ExpressionType.TRUE.toString()));

		if (!ExpressionUtility.inExpression(oldvalue, expr))
			return ExpressionUtility.clone(expr); // nothing to do

		Expression<String> lexpr = expr.getLeft();
		Expression<String> newlexpr = lexpr;
		if (ExpressionUtility.inExpression(oldvalue, lexpr))
			newlexpr = replaceOccurenceInExpression(oldvalue, newvalue, lexpr);

		Expression<String> rexpr = expr.getRight();
		Expression<String> newrexpr = rexpr;
		if (ExpressionUtility.inExpression(oldvalue, rexpr))
			newrexpr = replaceOccurenceInExpression(oldvalue, newvalue, rexpr);

		Expression<String> newexpr = null;
		switch (expr.getType()) {
		case TRUE:
			newexpr = new Expression<String>(ExpressionType.TRUE);
			break;
		case FALSE:
			newexpr = new Expression<String>(ExpressionType.FALSE);
			break;
		case AND:
			newexpr = new Expression<String>(ExpressionType.AND, newlexpr,
					newrexpr);
			break;
		case OR:
			newexpr = new Expression<String>(ExpressionType.OR, newlexpr,
					newrexpr);
			break;
		case IMPLIES:
			newexpr = new Expression<String>(ExpressionType.IMPLIES, newlexpr,
					newrexpr);
			break;
		case IFF:
			newexpr = new Expression<String>(ExpressionType.IFF, newlexpr,
					newrexpr);
			break;
		case NOT:
			newexpr = new Expression<String>(ExpressionType.NOT, newlexpr, null);
			break;
		case FEATURE:
			if (expr.getFeature() == null)
				return null; // nothing to do

			if (newvalue.equals(FeatureGraphFactory.DEFAULT_TOP_STRING)) {
				FMLShell.getInstance()
						.printDebugMessage("True value to be set");
				expr = new Expression<String>(ExpressionType.TRUE);
				return ExpressionUtility.clone(expr);
			} else if (newvalue
					.equals(FeatureGraphFactory.DEFAULT_BOTTOM_STRING)) {
				FMLShell.getInstance().printDebugMessage(
						"False value to be set");
				expr = new Expression<String>(ExpressionType.FALSE);
				return ExpressionUtility.clone(expr);
			} else if (newvalue.startsWith("~")) {
				FMLShell.getInstance().printDebugMessage("negated variable");
				expr = new Expression<String>(ExpressionType.NOT,
						new Expression<String>(newvalue.substring(1)), null);
				return ExpressionUtility.clone(expr);
			} else
				expr.setFeature(newvalue);
			return new Expression<String>(newvalue);
		default:
			throw new UnsupportedOperationException();

		}

		expr.setLeft(newlexpr);
		expr.setRight(newrexpr);
		return newexpr;

	}

	/**
	 * @param name
	 *            the feature name supposed to be in expr
	 * @param expr
	 *            the expression/constraint to consider
	 * @return determine whether name appears in any expression of expr
	 */
	public static boolean inExpression(final String name,
			Expression<String> expr) {

		if (expr == null)
			return false;

		if (inExpression(name, expr.getLeft()))
			return true;
		if (inExpression(name, expr.getRight()))
			return true;

		switch (expr.getType()) {
		case TRUE:
			return name.equals(ExpressionType.TRUE.toString());
		case FALSE:
			return name.equals(ExpressionType.FALSE.toString());
		case AND:

		case OR:

		case IMPLIES:

		case IFF:

		case NOT:

		case FEATURE:
			if (expr.getFeature() == null)
				return false;
			return expr.getFeature().equals(name);
		default:
			return false;
		}

	}

	/**
	 * @param expr
	 *            a propositional constraint expression
	 * @return a clone of an expression
	 */
	public static Expression<String> clone(Expression<String> expr) {

		if (expr == null)
			return null;

		Expression<String> lexpr = clone(expr.getLeft());
		Expression<String> rexpr = clone(expr.getRight());

		switch (expr.getType()) {
		case TRUE:
			return new Expression<String>(ExpressionType.TRUE);
		case FALSE:
			return new Expression<String>(ExpressionType.FALSE);
		case AND:
			return new Expression<String>(ExpressionType.AND, lexpr, rexpr);
		case OR:
			return new Expression<String>(ExpressionType.OR, lexpr, rexpr);
		case IMPLIES:
			return new Expression<String>(ExpressionType.IMPLIES, lexpr, rexpr);
		case IFF:
			return new Expression<String>(ExpressionType.IFF, lexpr, rexpr);
		case NOT:
			return new Expression<String>(ExpressionType.NOT, lexpr, null);
		case FEATURE:
			if (expr.getFeature() == null)
				return null; // nothing to do
			return new Expression<String>(expr.getFeature());
		default:
			throw new UnsupportedOperationException();

		}

	}

	/**
	 * Splits a top-level conjunction into a collection of conjoined
	 * expressions. For example, for the expression A & (B | C) & D & E, a
	 * collection containing 4 expressions is returned: A, (B|C) D, E
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> Collection<Expression<T>> splitConjunction(
			Expression<T> e) {
		if (e == null)
			return Collections.emptyList();
		else if (e.getType() == ExpressionType.AND) {
			return CollectionUtils.union(splitConjunction(e.getLeft()),
					splitConjunction(e.getRight()));
		} else {
			ArrayList<Expression<T>> coll = new ArrayList<Expression<T>>();
			coll.add(e);
			return coll;
		}
	}

	public static boolean isTautology(Expression<String> e,
			BDDBuilder<String> builder) {
		BDD bddExpression = builder.mkExpression(e);
		return bddExpression.isOne();
	}

	
	

	public static Expression<String> mkConjunction(
			Collection<Expression<String>> feats) {
		if (feats.size() == 0)
			throw new IllegalArgumentException(
					"There must be at least 1 feature!");

		Iterator<Expression<String>> iter = feats.iterator();
		Expression<String> e = iter.next();
		while (iter.hasNext())
			e = e.and(iter.next());

		return e;
	}

	public static Expression<String> mkCNF(Collection<FeatureNode<String>> vs) {
		if (vs.size() == 0)
			throw new IllegalArgumentException(
					"There must be at least 1 feature node!");

		Iterator<FeatureNode<String>> iter = vs.iterator();
		Expression<String> e = ExpressionUtil.mkDisjunction(iter.next()
				.features());
		while (iter.hasNext()) {
			e = e.and(ExpressionUtil.mkDisjunction(iter.next().features()));
		}
		return e;
	}
	
	public static Expression<String> mkDisjunction(
			Collection<Expression<String>> feats) {
		if (feats.size() == 0)
			throw new IllegalArgumentException(
					"There must be at least 1 feature!");

		Iterator<Expression<String>> iter = feats.iterator();
		Expression<String> e = iter.next();
		while (iter.hasNext())
			e = e.or(iter.next());

		return e;
	}

	public static Expression<String> atMost1(Set<FeatureNode<String>> sources) {
		Collection<Expression<String>> mtxs = new HashSet<Expression<String>>() ;
		FeatureNode<String>[] arr = sources.toArray(new FeatureNode[0]);
		for (int i=0; i<arr.length-1; i++) {
			
			FeatureNode<String> v1 = arr[i];
			for (int j=i+1; j<arr.length; j++) {
				
				FeatureNode<String> v2 = arr[j];
				mtxs.add(new Expression<String>(ExpressionType.IMPLIES, 
						new Expression<String> (v1.getFeature()),
						new Expression<String> (ExpressionType.NOT, v2.getFeature(), null)));
			}
		}
		return mkConjunction (mtxs) ; 
	}
	
	
	public static Expression<String> mkDisjunction(Set<FeatureNode<String>> sources) {
		Set<String> strSources = new HashSet<String> () ; 
		for (FeatureNode<String> featureNode : sources) {
			strSources.add(featureNode.getFeature());
		}
		return ExpressionUtil.mkDisjunction(strSources);
	}

	public static Expression<String> mkImplicationHierarchies (Set<FeatureNode<String>> sources, FeatureNode<String> target) {
		// child (source) implies parent (target)
		Set<Expression<String>> exprs = new HashSet<Expression<String>>() ; 
		for (FeatureNode<String> source : sources) {
			exprs.add(new Expression<String>(ExpressionType.IMPLIES, 
					new Expression<String>(source.getFeature()),  
					new Expression<String>(target.getFeature()))); 
		}
		return mkConjunction(exprs);
	}
	
	public static Expression<String> atLeast1  (Set<FeatureNode<String>> sources, FeatureNode<String> target) {
		//  parent (target) implies child (source) 
		Set<Expression<String>> exprs = new HashSet<Expression<String>>() ; 
		for (FeatureNode<String> source : sources) {
			exprs.add(new Expression<String>(ExpressionType.IMPLIES, 
					new Expression<String>(target.getFeature()), 
					new Expression<String>(source.getFeature()) 
					)); 
		}
		return ExpressionUtility.mkDisjunction(exprs);
	}
	



}
