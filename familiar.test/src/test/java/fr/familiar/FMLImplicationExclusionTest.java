/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.Test;

import fr.familiar.fm.FMLUtils;
import fr.familiar.fm.converter.ExclusionGraph;
import fr.familiar.operations.EGBuilder;
import fr.familiar.operations.ImplicationGraphUtil;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureNode;
import gsd.synthesis.Formula;

public class FMLImplicationExclusionTest extends FMLTest {

	@Test
	public void testImplication1() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : B C D; C -> D; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (1)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		assertEquals(6 * 2, impl1.edgeSet().size());
	}

	@Test
	public void testImplication2() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] C D; C -> D; C -> B; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (2)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		assertEquals((6 * 2), impl1.edgeSet().size());
	}

	@Test
	public void testImplication3() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : [B] C D; C -> D; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (3)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		assertEquals((6 * 2) - 3, impl1.edgeSet().size());
	}

	@Test
	public void testImplication4() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] D; B : [C]; C -> D; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (4)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		System.err.println("[[fm1]]=" + FMLUtils.setConfigToSetStr(fmv1.configs()));
		assertEquals(7, impl1.edgeSet().size());
	}

	@Test
	public void testImplication5() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] D; B : [C]; C -> D; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (4)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		System.err.println("[[fm1]]=" + FMLUtils.setConfigToSetStr(fmv1.configs()));
		assertEquals(7, impl1.edgeSet().size());
	}

	@Test
	public void testImplication6() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] [D] E; B : [C]; C -> D; )");
		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (4)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		Set<Expression<String>> implExpr = ImplicationGraphUtil
				.toExpressions(impl1);
		assertEquals(implExpr.size(), impl1.edgeSet().size());
		System.err.println("implExpr=" + implExpr);

		Set<Expression<String>> biimplExpr = ImplicationGraphUtil
				.toExpressionsWithBiImply(impl1);
		System.err.println("biimplExpr=" + biimplExpr);

		ImplicationGraph<String> implHierarchy1 = fmv1
				.getImplicationGraphFromFeatureHierarchy(_builder);
		System.err.println("\n\tImplication graph -- feature hiearchy (4)\n");
		ImplicationGraphUtil.debugImplicationGraph(implHierarchy1);

		Set<Expression<String>> impliedEdges = ImplicationGraphUtil
				.computeImpliesEdge(fmv1, impl1, _builder);
		System.err.println("impliedEdges=" + impliedEdges);

		Set<Expression<String>> impliedEdges2 = fmv1
				.computeImpliesEdge(_builder);
		System.err.println("impliedEdges2=" + impliedEdges2);

		assertTrue(impliedEdges.equals(impliedEdges2));

		System.err.println("[[fm1]]=" + FMLUtils.setConfigToSetStr(fmv1.configs()));
		assertEquals(10, impl1.edgeSet().size());
	}

	@Test
	public void testImplicationExclusion1() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1", "FM (A : (B|C|D) E ; )");

		ImplicationGraph<String> impl1 = fmv1.computeImplicationGraph(_builder);
		System.err.println("\n\tImplication graph (1)\n");
		ImplicationGraphUtil.debugImplicationGraph(impl1);

		assertEquals(8, impl1.edgeSet().size());

		UndirectedGraph<FeatureNode<String>, DefaultEdge> excl1 = EGBuilder
				.buildForGroups(fmv1.getFormula(), fmv1.getFm().getDiagram(),
						_builder);
		System.err.println("\n\tExclusion graph (1)\n");
		debugExclusionGraph(excl1);
		assertEquals(3, excl1.edgeSet().size());

		Formula<String> fla1 = fmv1.getFormula();
		ExclusionGraph<String> excl1bis = EGBuilder.build(fla1.getBDD(),
				_builder, fla1.getDomain()); // EGBuilder.buildForGroups(fmv1.getFormula(),
												// fmv1.getFm().getDiagram(),
												// _builder);
		System.err.println("\n\tExclusion graph (1bis)\n");
		System.err.println(excl1bis);
		assertEquals(3, excl1bis.edgeSet().size());
	}

	@Test
	public void testExclusion1() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] [C] [D] E ; E : (F|G); G -> !B; )");

		Formula<String> fla1 = fmv1.getFormula();
		ExclusionGraph<String> excl1 = EGBuilder.build(fla1.getBDD(), _builder,
				fla1.getDomain()); // EGBuilder.buildForGroups(fmv1.getFormula(),
									// fmv1.getFm().getDiagram(), _builder);
		System.err.println("\n\tExclusion graph (1)\n");
		System.err.println(excl1);
		assertEquals(2, excl1.edgeSet().size());
	}

	@Test
	public void testExclusion2() throws Exception {
		FeatureModelVariable fmv1 = FM("fm1",
				"FM (A : [B] [C] [D] E ; E : (F|G|H); G -> !B; )");

		Formula<String> fla1 = fmv1.getFormula();
		ExclusionGraph<String> excl1 = EGBuilder.build(fla1.getBDD(), _builder,
				fla1.getDomain()); // EGBuilder.buildForGroups(fmv1.getFormula(),
									// fmv1.getFm().getDiagram(), _builder);
		System.err.println("\n\tExclusion graph (2)\n");
		System.err.println(excl1);
		assertEquals(4, excl1.edgeSet().size());
	}

	private void debugExclusionGraph(
			UndirectedGraph<FeatureNode<String>, DefaultEdge> excl1) {
		Set<DefaultEdge> edges1 = excl1.edgeSet();
		for (DefaultEdge e1 : edges1) {
			FeatureNode<String> s1 = excl1.getEdgeSource(e1);
			FeatureNode<String> t1 = excl1.getEdgeTarget(e1);
			System.err.println("" + s1 + " => " + t1);
		}

	}

}
