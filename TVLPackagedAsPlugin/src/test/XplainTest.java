package test;

import static org.junit.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.TimeoutException;
import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FDElement;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.Util.Solver;

public class XplainTest {

	private String testFilesPath = "test/";
	private TVLParser parser = null;
	private FeatureModel fm = null;

	@Before
	public void setUp() throws Exception {

		String filePath = this.testFilesPath.concat("ploneTest.tvl");
		parser = new TVLParser(new File(filePath));
		fm = new FeatureModel(parser.getRoot());

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExplain() throws Exception {
		try {
			fm.include("IsDefault", false);
			fm.excludeWithExplanation("ToDiscuss", false);
		} catch (be.ac.fundp.info.TVLParser.Util.ContradictionException ce) {
			fail("This should not happen");
		}
	}

	@Test
	public void testExplain2() throws Exception {
		try {
			fm.include("IsDefault", false);
			fm.includeWithExplanation("DefaultToDiscussNormalItems", false);

			fail("This should not happen");
		} catch (be.ac.fundp.info.TVLParser.Util.ContradictionException ce) {
			describeException(ce);
		}
	}

	@Test
	public void testExplain3() throws Exception {
		try {
			fm.includeWithExplanation("UseGroupsAsCategories", false);
			fm.includeWithExplanation("DefaultToDiscussNormalItems", false);
			fm.includeWithExplanation("DefaultToDiscussLateItems", false);
			fail("This should not happen");
		} catch (be.ac.fundp.info.TVLParser.Util.ContradictionException ce) {
			describeException(ce);
		}

	}

	@Test
	public void testExplain4() throws Exception {
		try {
			fm.include("IsDefault", false);
			fm.unassign("IsDefault", false);
			fm.include("IsDefault", false);
			fm.includeWithExplanation("DefaultToDiscussNormalItems", false);

			fail("This should not happen");
		} catch (be.ac.fundp.info.TVLParser.Util.ContradictionException ce) {
			describeException(ce);
		}
	}

	@Test
	public void testExplain5() throws Exception {
		fm.include("IsDefault", false);
		List<FDElement> explanation = fm.explain("DefaultToDiscussNormalItems", false, new ArrayList<FDElement>());
		System.out.println(explanation);
		assertNotNull(explanation);
		assertFalse(explanation.isEmpty());
	}

	@Test
	public void testExplain6() throws Exception {
		String filePath = this.testFilesPath.concat("ploneTest-simple.tvl");
		parser = new TVLParser(new File(filePath));
		fm = new FeatureModel(parser.getRoot());
		fm.include("Observations", false);
		fm.include("Keywords", false);
		fm.include("ToDiscuss", false);
		fm.include("DefaultToDiscussNormalItems", false);
		fm.include("DefaultToDiscussLateItems", false);
		List<FDElement> explanation = fm.explain("UseGroupsAsCategories", false, new ArrayList<FDElement>());
		assertNotNull(explanation);
		System.out.println("EXPLANATION = " + explanation);
		assertEquals(2, explanation.size());

	}

	private void describeException(be.ac.fundp.info.TVLParser.Util.ContradictionException ce) throws Exception {
		List<FDElement> solutions = fm.findSolutions(ce, new ArrayList<FDElement>());
		if (!solutions.isEmpty()) {
			System.out.println("The following choices may be the source of your problem, you may want to unassign one of these : ");
			for (FDElement element : solutions) {
				System.out.println(" > " + element.getName());
			}
		} else {
			System.out.println("No solution found");
		}
	}

}
