package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.File;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.TimeoutException;

import be.ac.fundp.info.TVLParser.TVLParser;
import be.ac.fundp.info.TVLParser.Util.FDElement;
import be.ac.fundp.info.TVLParser.Util.FeatureModel;
import be.ac.fundp.info.TVLParser.exceptions.UnsatisfiableModelException;

public class FeatureModelTest {

	// private String testFilesPath = "/home/raph/workspace/TVLParser/test/";
	private String testFilesPath = "test/";
	private TVLParser parser = null;
	FeatureModel fm = null;

	@Before
	public void setUp() throws Exception {
		String filePath = this.testFilesPath.concat("votes.tvl");
		parser = new TVLParser(new File(filePath));
		// Parser.printInfo();
		fm = new FeatureModel(parser.getRoot());
	}

	@After
	public void tearDown() throws Exception {
		// System.out.println(fm.getSatCalls() + " sat calls") ;
		parser = null;
		fm = null;
	}

	@Test
	public void testFeatureModel() throws ContradictionException, UnsatisfiableModelException {
		assertNotNull(fm);
	}

	@Test
	public void testHasAtLeastOneSolution() throws TimeoutException, ContradictionException {
		assertTrue(fm.hasAtLeastOneSolution());
	}

	@Test
	public void testIsSelectable() throws Exception {
		assertTrue(fm.isSelectable("Votes.Artificial1.EnableVoting", true));
		assertTrue(fm.isSelectable("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		assertTrue(fm.isSelectable("Votes.Artificial3.DefaultVoteValue.NoDVV", true));
		assertTrue(fm.isSelectable("Votes.Artificial3.DefaultVoteValue.AbstentionDVV", true));

	}

	@Test
	public void testSelect_1() throws Exception {
		assertFalse(fm.isExcluded("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		assertFalse(fm.isIncluded("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);
		assertTrue(fm.isIncluded("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
	}

	@Test
	public void testPropagate_1() throws Exception {
		assertTrue(fm.isUnassigned("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		assertTrue(fm.isUnassigned("Votes.Artificial2.AvailableVoteValues.YesAVV", true));
		assertTrue(fm.isUnassigned("Votes.Artificial1.EnableVoting", true));
		assertTrue(fm.isUnassigned("Votes", true));

		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);
		assertTrue(fm.isIncluded("Votes", true));
		assertTrue(fm.isIncluded("Votes.Artificial1", true));
		assertTrue(fm.isIncluded("Votes.Artificial2", true));
		assertTrue(fm.isIncluded("Votes.Artificial3", true));
		assertTrue(fm.isIncluded("Votes.Artificial1.EnableVoting", true));

		assertTrue(fm.isIncluded("Votes.Artificial2.AvailableVoteValues.YesAVV", true));
		assertTrue(fm.isUnassigned("Votes.Artificial2.AvailableVoteValues.NoAVV", true));
		assertTrue(fm.isUnassigned("Votes.Artificial2.AvailableVoteValues.AbstentionAVV", true));

		assertTrue(fm.isIncluded("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		assertTrue(fm.isExcluded("Votes.Artificial3.DefaultVoteValue.NoDVV", true));
		assertTrue(fm.isExcluded("Votes.Artificial3.DefaultVoteValue.AbstentionDVV", true));
	}

	@Test
	public void testPropagate_2() throws Exception {
		fm.include("Votes", true);

		assertTrue(fm.isIncluded("Votes", true));
		assertTrue(fm.isIncluded("Votes.Artificial1", true));
		assertTrue(fm.isIncluded("Votes.Artificial2", true));
		assertTrue(fm.isIncluded("Votes.Artificial3", true));
	}

	@Test
	public void testUnSelectAfterSelect_1() throws Exception {

		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		try {
			fm.exclude("Votes.Artificial3.DefaultVoteValue.YesDVV", true);
			fail("This feature is unselectable, an exception should be thrown !");
		} catch (ContradictionException ce) {
			// This is what we expect !
		}

	}

	@Test
	public void testUnSelectAfterSelect_2() throws Exception {

		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		try {
			fm.exclude("Votes.Artificial2.AvailableVoteValues.YesAVV", true);
			fail("This feature is unselectable, an exception should be thrown !");
		} catch (ContradictionException ce) {
			// This is what we expect !
		}

	}

	@Test
	public void testUnSelectAfterSelect_3() throws Exception {
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		fm.exclude("Votes.Artificial2.AvailableVoteValues.NoAVV", true);
		fm.exclude("Votes.Artificial2.AvailableVoteValues.AbstentionAVV", true);
		System.out.println(fm.toString());
	}

	@Test
	public void testUnAssign_1() throws Exception {
		try {
			fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);
			fm.unassign("Votes.Artificial3.DefaultVoteValue.NoDVV", true);
			fail("This feature should not be unassigned");
		} catch (ContradictionException e) {
			// This is what we want !
		}
		assertFalse(fm.isUnassigned("Votes.Artificial3.DefaultVoteValue.NoDVV", true));

	}

	@Test
	public void testUnAssign_2() throws Exception {
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		fm.unassign("Votes.Artificial3.DefaultVoteValue.YesDVV", true);
		System.out.println(fm.toString());
		assertTrue(fm.isUnassigned("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
		assertTrue(fm.isSelectable("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
	}

	@Test
	public void testUnAssign_3() throws Exception {
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		fm.unassign("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		assertTrue(fm.isUnSelectable("Votes.Artificial3.DefaultVoteValue.YesDVV", true));
	}

	@Test
	public void testUnAssign_4() throws Exception {
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		fm.unassign("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		try {
			fm.unassign("Votes.Artificial3.DefaultVoteValue.NoDVV", true);
			fail("This should not be feasible");
		} catch (ContradictionException e) {
			// This is what we expect
		}

	}

	@Test
	public void testUnAssign_5() throws Exception {
		fm.include("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		List<FDElement> list = fm.unassign("Votes.Artificial3.DefaultVoteValue.YesDVV", true);

		assertNotNull(list);
		assertTrue(list.size() > 0);
		for (FDElement elem : list) {
			System.out.println(elem.getName() + " : " + elem.isSelected());
		}

	}

	/*
	 * @Test
	 * public void testSudoku() throws Exception {
	 * String filePath = this.testFilesPath.concat("sudoku.tvl");
	 * Parser = new TVLParser(new File(filePath));
	 * fm = new FeatureModel(Parser.getRoot());
	 * 
	 * assertNotNull(fm) ;
	 * 
	 * }
	 */
}
