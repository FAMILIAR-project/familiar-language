/*
 * This file is part of the Feature Model Synthesis project (FMSynth).
 *
 * Copyright (C) 2010 Steven She <shshe@gsd.uwaterloo.ca>
 *
 * FMSynth is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FMSynth is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FMSynth.  (See files COPYING and COPYING.LESSER.)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package gsd.synthesis;

import java.util.Comparator;
import java.util.PriorityQueue;

import net.sf.javabdd.BDD;

/**
 * A priority queue that orders BDDs according to their node count (lowest node
 * count is first).
 *
 * @author Steven She (shshe@uwaterloo.ca)
 */
public class BDDQueue extends PriorityQueue<BDD>{
	private static final long serialVersionUID = 2149156386061285730L;
	private final BDDBuilder<?> mBuilder;

	public BDDQueue(BDDBuilder<?> builder) {
		super(10, new Comparator<BDD>() {
			public int compare(BDD o1, BDD o2) {
				return o1.nodeCount() == o2.nodeCount() ? 0
						: o1.nodeCount() < o2.nodeCount() ? -1 : 1;
			}
		});
		this.mBuilder = builder;
	}


	public BDD getConjoinedBDD() {
		BDD result = mBuilder.one();
		while (size() > 0) {
			BDD polled = poll();
			result.andWith(polled);
		}
		return result;
	}
}