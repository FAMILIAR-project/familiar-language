package fr.unice.polytech.modalis.familiar.test.heuristics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.unice.polytech.modalis.familiar.test.FMLTest;
import gsd.graph.ImplicationGraph;

public class MetricTest extends FMLTest {

	@Test
	public void testImplicationGraphMetrics() {
		ImplicationGraphMetrics metrics = new ImplicationGraphMetrics();
		ImplicationGraph<Integer> ig = new ImplicationGraph<Integer>();
		ig.addVertex(1);
		ig.addVertex(2);
		ig.addVertex(3);
		ig.addEdge(1, 2);
		ig.addEdge(1, 3);
		ig.addEdge(2, 1);
		
		
		assertEquals(3, metrics.maxDegree(ig));
		assertEquals(1, metrics.maxIndegree(ig));
		assertEquals(2, metrics.maxOutdegree(ig));
		assertEquals(1, metrics.minDegree(ig));
		assertEquals(1, metrics.minIndegree(ig));
		assertEquals(0, metrics.minOutdegree(ig));
	}
}
