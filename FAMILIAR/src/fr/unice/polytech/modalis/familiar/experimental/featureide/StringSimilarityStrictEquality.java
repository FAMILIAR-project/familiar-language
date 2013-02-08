package fr.unice.polytech.modalis.familiar.experimental.featureide;

public class StringSimilarityStrictEquality extends StringSimilarity {

	@Override
	public boolean isSimilar(String str1, String str2) {
		return str1.equals(str2);
	}

}
