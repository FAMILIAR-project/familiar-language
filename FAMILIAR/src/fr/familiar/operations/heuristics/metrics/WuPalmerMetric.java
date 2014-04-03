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

package fr.familiar.operations.heuristics.metrics;

import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.AsymmetricRelationship;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;


public class WuPalmerMetric extends WordNetMetric {

	private static final int DEFAULT_DEPTH = 1; // simulates an abstract root node


	/**
	 * Compute Wu & Palmer formula on two synsets
	 * @param synset1
	 * @param synset2
	 * @return
	 * @throws CloneNotSupportedException
	 */
	@Override
	public double synsetSimilarity(Synset synset1, Synset synset2) {
		double score = 0;
		try {
			RelationshipList relations = RelationshipFinder.findRelationships(synset1, synset2, PointerType.HYPERNYM);
			// Find shortest path 
			Relationship shortestPath = null;
			for (Relationship relation : relations) {
				if (shortestPath == null) {
					shortestPath = relation;
				} else if (relation.getSize() < shortestPath.getSize()) {
					shortestPath = relation;
				}
			}
			// Compute wup
			double n1 = PointerUtils.getHypernymTree(synset1).toList().get(0).size() + DEFAULT_DEPTH;
			double n2 = PointerUtils.getHypernymTree(synset2).toList().get(0).size() + DEFAULT_DEPTH;
			double n3 = DEFAULT_DEPTH;

			if (shortestPath != null) {
				int indexLcs = ((AsymmetricRelationship) shortestPath).getCommonParentIndex();
				Synset lcs = shortestPath.getNodeList().get(indexLcs).getSynset();
				n3 = PointerUtils.getHypernymTree(lcs).toList().get(0).size() + DEFAULT_DEPTH;				
			}

			score = (2*n3) / (n1 + n2);				

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return score;
	}

	
//	@Override
//	public String toString() {
//		return "WordNet metric (Wu & Palmer)";
//	}
//
//
//	@Override
//	public boolean isXorGroupRequired() {
//		return false;
//	}
//
//
//	@Override
//	public boolean isOrGroupRequired() {
//		return false;
//	}
	@Override
	public String getName() {
		return "Wu & Palmer (WordNet)" ;
	}


}
