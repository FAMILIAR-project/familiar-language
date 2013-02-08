package fr.unice.polytech.modalis.familiar.experimental.featureide;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.experimental.BinaryMappingCorrespondence;
import fr.unice.polytech.modalis.familiar.experimental.MappingCorrespondence;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;

public class FMMatcher {

	protected StringSimilarity _strSimilarity;

	public FMMatcher(StringSimilarity strSimilarity) {
		_strSimilarity = strSimilarity;
	}

	public FMMatcher() {
		this(new StringSimilarityStrictEquality());
	}

	@Deprecated
	public MappingCorrespondence<String> computeMatchFeatures(
			FeatureModelVariable fm1, FeatureModelVariable fm2) {
		MappingCorrespondence<String> mapping = new BinaryMappingCorrespondence<String>();

		Set<String> fts1 = fm1.features().names();
		Set<String> fts2 = fm2.features().names();
		for (String ft1 : fts1) {
			for (String ft2 : fts2) {
				if (similarity(ft1, ft2))
					mapping.associateElementTo(ft1, ft2);
			}

		}

		return mapping;
	}

	private String[] closedFt(String ft1, Set<String> fts2) {
		return _strSimilarity.computeCloserElement(ft1, fts2);
	}

	private boolean similarity(String ft1, String ft2) {
		return _strSimilarity.isSimilar(ft1, ft2);
	}

	public void setSimilarity(StringSimilarity strSimilarity) {
		_strSimilarity = strSimilarity;
	}

	public MappingCorrespondence<String> computeOneToOneMappingOrder(
			FeatureModelVariable fm1, FeatureModelVariable fm2) {

		MappingCorrespondence<String> mapping = new BinaryMappingCorrespondence<String>();

		Set<String> fts1 = fm1.features().names();
		Set<String> fts2 = fm2.features().names();
		for (String ft1 : fts1) {
			String[] closedFt = closedFt(ft1, fts2);
			if (closedFt.length > 0) {
				for (int i = 0; i < closedFt.length; i++)
					if (mapping.associateElementTo(ft1, closedFt[i]))
						break;
			} else
				mapping.noMatching(ft1);

		}

		return mapping;
	}

}
