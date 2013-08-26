package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;

public class DirectedPathLengthMetric extends WordNetMetric {


	public DirectedPathLengthMetric(Dictionary dictionary) {
		super(dictionary);
	}

	@Override
	public double synsetSimilarity(Synset synset1, Synset synset2) {
		double maxScore = 0;
		try {
			RelationshipList relations = RelationshipFinder.findRelationships(synset1, synset2, PointerType.HYPERNYM);
			
			int depth1 = PointerUtils.getHypernymTree(synset1).toList().get(0).size();
			int depth2 = PointerUtils.getHypernymTree(synset2).toList().get(0).size();
			
			int minimalPathLength;
			if (depth1 > depth2) {
				minimalPathLength = 1;
			} else {
				minimalPathLength = 2;
			}
			
			for (Relationship relation : relations) {
				double score = 1.0 / (relation.getDepth() + minimalPathLength);
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
		return "WordNet metric (Directed path length)";
	}

}
