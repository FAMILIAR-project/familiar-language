package fr.familiar.test.regression.featureide;

import java.util.List;

import org.junit.Test;

import fr.familiar.test.featureide.FMLMergeSliceTest;

public class FMLMergeSliceRepeatTest extends FMLMergeSliceTest {

	public final static int N_TIMES = 10;

	public FMLMergeSliceRepeatTest(List<String> lfms,
			List<String> possibleHierarchies) throws Exception {
		super(lfms, possibleHierarchies);
	}

	@Test
	public void repeat() throws Exception {
		for (int i = 0; i < N_TIMES; i++) {
			testMerge1();
		}
	}
}