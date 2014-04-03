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