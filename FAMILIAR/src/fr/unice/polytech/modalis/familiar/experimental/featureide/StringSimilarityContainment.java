package fr.unice.polytech.modalis.familiar.experimental.featureide;

public class StringSimilarityContainment extends StringSimilarity {

	@Override
	public boolean isSimilar(String str1, String str2) {
		return str1.contains(str2) || str2.contains(str1);
	}

}
