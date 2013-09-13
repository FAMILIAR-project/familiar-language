package fr.unice.polytech.modalis.familiar.operations.heuristics.metrics;

import net.sf.extjwnl.data.PointerType;
import net.sf.extjwnl.data.PointerUtils;
import net.sf.extjwnl.data.Synset;
import net.sf.extjwnl.data.relationship.Relationship;
import net.sf.extjwnl.data.relationship.RelationshipFinder;
import net.sf.extjwnl.data.relationship.RelationshipList;
import net.sf.extjwnl.dictionary.Dictionary;

public class PathLengthMetric extends WordNetMetric {


	public PathLengthMetric(Dictionary dictionary) {
		super(dictionary);
	}

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

	@Override
	public boolean isXorGroupRequired() {
		return false;
	}

	@Override
	public boolean isOrGroupRequired() {
		return false;
	}

}
