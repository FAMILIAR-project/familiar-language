/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.test.heuristics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.operations.heuristics.mst.OptimumBranchingFinder;
import fr.familiar.operations.heuristics.mst.WeightedImplicationGraph;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.Variable;
import gsd.graph.ImplicationGraph;
import gsd.graph.SimpleEdge;

public class OptimumBranchingTest extends FMLTest {
	
	final private List<String> excludeFiles = Arrays.asList(new String[] {
			 //two features with the same name (parent)
			"model_20100830_561967343.xml", 
			"model_20100927_1382418986.xml", 
			"model_20101112_864228137.xml", 
			"model_20110406_353034837.xml", 
			"model_20110407_1283128166.xml", 
			"model_20110712_1373520081.xml", 
			"model_20101115_2000504462.xml",
			"model_20120130_333619036.xml",
			"model_20120328_573707444.xml",
			"model_20120328_663906927.xml",
			"model_20120418_828883554.xml",
			"model_20120424_1703152596.xml",
			"REAL-FM-1.xml",	
			"REAL-FM-11.xml",
			"REAL-FM-16.xml",
			"REAL-FM-4.xml",
			// group with one element
			"model_20101120_2091447559.xml", 
			"model_20101123_920943759.xml", 
			"model_20110301_216655728.xml", 
			"model_20110207_906076676.xml", 
			"model_20110323_789959080.xml", 
			"model_20110330_919429247.xml", 
			"model_20110507_674768061.xml", 
			"model_20110704_328391695.xml", 
			"model_20110826_1574631601.xml",
			"model_20110925_62365838.xml",
			"model_20120111_1667229430.xml",
			"model_20120205_24117969.xml",
			"model_20120328_288933955.xml",
			"model_20120801_1784537083.xml",
			// Parsing problem?
			"model_20091225_1547989376.xml",
			"model_20091205_755658379.xml"
		
	});

	
	private final static String SPLOT_FOLDER = "inputFML/splot-models-2012-08-07";
	
	@Test
	public void testCamerini() {
		// Define input graph
		WeightedImplicationGraph<String> graph = new WeightedImplicationGraph<String>();
		graph.addVertex("1");
		graph.addVertex("2");
		graph.addVertex("3");
		graph.addVertex("4");

		SimpleEdge currentEdge = null;
		currentEdge = graph.addEdge("2", "1");
		graph.setEdgeWeight(currentEdge, 6);
		currentEdge = graph.addEdge("3", "2");
		graph.setEdgeWeight(currentEdge, 10);
		currentEdge = graph.addEdge("2", "3");
		graph.setEdgeWeight(currentEdge, 10);
		currentEdge = graph.addEdge("3", "4");
		graph.setEdgeWeight(currentEdge, 8);
		currentEdge = graph.addEdge("4", "2");
		graph.setEdgeWeight(currentEdge, 12);
		currentEdge = graph.addEdge("1", "4");
		graph.setEdgeWeight(currentEdge, 1);
		
		// Define optimum branching

		ImplicationGraph<String> mst = new ImplicationGraph<String>();
		mst.addVertex("1");
		mst.addVertex("2");
		mst.addVertex("3");
		mst.addVertex("4");

		mst.addEdge("2", "1");
		mst.addEdge("3", "2");
		mst.addEdge("4", "2");


		// Find optimum branching
		OptimumBranchingFinder<String> tarjan = new OptimumBranchingFinder<String>();
		ImplicationGraph<String> result = tarjan.findOptimumBranching(graph);

		assertEquals(mst, result);
	}
	
	@Test
	public void testSPLOTSpanningTree() {
		Heuristic metric = new RandomMetric();
		OptimumBranchingFinder<String> tarjan = new OptimumBranchingFinder<String>();

		for (FeatureModelVariable fm : collectSPLOTFMs()) {
			// Compute implication graph
			WeightedImplicationGraph<String> big = new WeightedImplicationGraph<String>(fm.computeImplicationGraph());
			
			// Define edges' weight
			for (SimpleEdge edge : big.edges()) {
				String source = big.getSource(edge);
				String target = big.getTarget(edge);
				big.setEdgeWeight(edge, metric.similarity(source, target));
			}
			
			// Compute optimum branching
			ImplicationGraph<String> result = tarjan.findOptimumBranching(big);

			// Check if the optimum branching is a spanning tree
			assertEquals("not a spanning tree (does not contain the same number of vertices)", big.vertices().size(), result.vertices().size());
			for (String vertex : big.vertices()) {
				assertTrue("not a spanning tree (does not contain vertex " + vertex + ")", result.containsVertex(vertex));	
			}
			int nbRoot = 0;
			for (String vertex : result.vertices()) {
				assertTrue("not a spanning tree (outdegree(" + vertex + ") > 1)", result.outDegreeOf(vertex) == 0 || result.outDegreeOf(vertex) == 1);
				if (result.outDegreeOf(vertex) == 0) {
					nbRoot++;
				}
			}
			assertEquals("not a spanning tree (not a unique root)", 1, nbRoot);
			for (SimpleEdge edge : result.edges()) {
				assertTrue("not a spanning tree (" + result.getEdgeSource(edge) + "->" + result.getEdgeTarget(edge) + " is not present in the original graph)", big.getEdge(result.getEdgeSource(edge), result.getEdgeTarget(edge)) != null);
			}
		}
	}
	
	private List<FeatureModelVariable> collectSPLOTFMs() {
		File splotFolder = new File(SPLOT_FOLDER);
		File[] splotFiles = splotFolder.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".xml") && !excludeFiles.contains(name);
				
			}
		});
		
		List<FeatureModelVariable> fms = new ArrayList<FeatureModelVariable>(); 
		int i = 0;  
		for (File splotFile : splotFiles) {
			i++ ; 	
			String fileName = splotFile.getName() ; 
			
			String fmName = fileName.replace("-", "");
			String mkFmlInput = "fm_" + i + "_" + fmName + "" + " = " + "FM (" + "\"" +  fileName + "\"" + ")\n\n" ;
//			System.err.println("" + mkFmlInput) ;
			Variable v = null ;
			v = _shell.parse(mkFmlInput);
			 
			assertTrue(v instanceof FeatureModelVariable);
			if (v != null) {
				fms.add((FeatureModelVariable)v);
			}
			
		}
		
		return fms ; 
	}

}
