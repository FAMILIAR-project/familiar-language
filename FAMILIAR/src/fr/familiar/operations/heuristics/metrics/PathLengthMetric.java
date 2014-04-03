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
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;

public class PathLengthMetric extends WordNetMetric {


	@Override
	public double synsetSimilarity(Synset synset1, Synset synset2) {
		double maxScore = 0;
		try {
			RelationshipList relations = RelationshipFinder.findRelationships(synset1, synset2, PointerType.HYPERNYM);
			
			for (Relationship relation : relations) {
				double score = 1.0/(relation.getDepth()+1);
				if (score > maxScore) {
					maxScore = score;
				}
			}
			
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		return maxScore;
	}
	
	@Override
	public String toString() {
		return "WordNet metric (Path length)";
	}

//	@Override
//	public boolean isXorGroupRequired() {
//		return false;
//	}
//
//	@Override
//	public boolean isOrGroupRequired() {
//		return false;
//	}
	
	@Override
	public String getName() {
		return "Path Length (WordNet)";
	}

}
