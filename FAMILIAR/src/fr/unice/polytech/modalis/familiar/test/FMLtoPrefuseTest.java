package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.junit.runners.Parameterized.Parameters;

import prefuse.data.Edge;
import prefuse.data.Tree;
import prefuse.data.Tuple;
import fr.unice.polytech.modalis.familiar.fm.FMLConverter2TreePrefuse;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

//@RunWith(Parameterized.class)
public class FMLtoPrefuseTest extends FMLTest {

	private String _fmSpecification = FM_LAPTOP;

	public FMLtoPrefuseTest() {
	}

	/*
	 * public FMLtoPrefuseTest(String fmSpecification) { _fmSpecification =
	 * fmSpecification ;
	 * 
	 * }
	 */

	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {
				{ "FM (A : B [C] D ; )" },
				{ "FM (A : B [C] D ; D : (E|F|G) ; C : (I|J|K|L)+ ; B : M [N] O P ; P : Q [R] ; R : (S|T|U) ; )" },
		// { FM_LAPTOP }

		};
		return Arrays.asList(data);
	}

	//

	@Test
	public void testTree1() throws Exception {

		String fmID = "fmFoo";
		FeatureModelVariable fmvFoo = FM(fmID, _fmSpecification);
		// FeatureModelVariable fmvFoo = FM (fmID,
		FMLConverter2TreePrefuse convPrefuse = new FMLConverter2TreePrefuse();
		Tree t = convPrefuse.toTreePrefuse(fmvFoo);
		assertNotNull(t);
		String strNodes = "";
		Iterator<Tuple> itTuples = t.getNodes().tuples();
		while (itTuples.hasNext()) {
			strNodes += itTuples.next() + ",";
		}
		String strEdges = "";
		Iterator<Edge> itEdges = t.edges();
		while (itEdges.hasNext()) {
			strEdges += itEdges.next() + ",";
		}
		System.err.println("#nodes=" + t.getNodeCount() + "\ntuples="
				+ strNodes + "\nedges=" + strEdges);

		assertTrue(t.isValidTree());

		// assertEquals(4, t.getNodes().getTupleCount());

		String absolutePath = "/Users/mathieuacher/Documents/workspaceScala/FMLPrefuseEditor/";
		String location = absolutePath + "fooTree.xml";

		convPrefuse.serialize(fmvFoo, new File(location));

	}

}
