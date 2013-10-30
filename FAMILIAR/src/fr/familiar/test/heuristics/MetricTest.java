package fr.familiar.test.heuristics;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.familiar.operations.heuristics.metrics.CommonEdgesMetric;
import fr.familiar.operations.heuristics.metrics.FMEditDistanceMetric;
import fr.familiar.operations.heuristics.metrics.ImplicationGraphMetrics;
import fr.familiar.operations.heuristics.metrics.RefactoringEditDistance;
import fr.familiar.operations.heuristics.metrics.ZhangEditDistance;
import fr.familiar.parser.FMBuilder;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
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
	
	@Test
	public void testCommonEdges() {
		FeatureModelVariable reference = new FeatureModelVariable(
				"1", FMBuilder.getInternalFM("A: B C; B: D E F;"));
		FeatureModelVariable fm = new FeatureModelVariable(
				"2", FMBuilder.getInternalFM("B: A C; A: D E F;"));
		CommonEdgesMetric metric = new CommonEdgesMetric();
		assertEquals(0, metric.commonEdges(fm, reference));
	}
	
	@Test
	public void testRefactoringEditDistance1() {
		FeatureModelVariable reference = new FeatureModelVariable(
				"1", FMBuilder.getInternalFM("A: B C; B: D E F;"));
		FeatureModelVariable fm = new FeatureModelVariable(
				"2", FMBuilder.getInternalFM("B: D C; D: A E F;"));
		
		FMEditDistanceMetric metric = new RefactoringEditDistance();
		assertEquals(2, metric.editDistance(fm, reference));
	}
	
	@Test
	public void testRefactoringEditDistance2() {
		FeatureModelVariable reference = new FeatureModelVariable(
				"1", FMBuilder.getInternalFM("A: B C; B: D E F;"));
		FeatureModelVariable fm = new FeatureModelVariable(
				"2", FMBuilder.getInternalFM("A: B  E F; B: C D;"));
		
		FMEditDistanceMetric metric = new RefactoringEditDistance();
		assertEquals(2, metric.editDistance(fm, reference));
	}
	
	@Test
	public void testZhangEditDistance1() {
		FeatureModelVariable reference = new FeatureModelVariable(
				"1", FMBuilder.getInternalFM("A: B C; B: D E F;"));
		FeatureModelVariable fm = new FeatureModelVariable(
				"2", FMBuilder.getInternalFM("A: B E F; B: C D;"));
		FMEditDistanceMetric metric = new ZhangEditDistance();
		assertEquals(2, metric.editDistance(fm, reference));
	}
	
	@Test
	public void testZhangEditDistance2() {
		FeatureModelVariable reference = new FeatureModelVariable(
				"1", FMBuilder.getInternalFM("F: D E; D: A C; C: B;"));
		FeatureModelVariable fm = new FeatureModelVariable(
				"2", FMBuilder.getInternalFM("F: C E; C: D; D: A B;"));
		FMEditDistanceMetric metric = new ZhangEditDistance();
		assertEquals(1, metric.editDistance(fm, reference));
	}
}
