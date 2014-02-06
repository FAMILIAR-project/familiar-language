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

	@Override
	public String toString() {
		return getName();
	}

}
