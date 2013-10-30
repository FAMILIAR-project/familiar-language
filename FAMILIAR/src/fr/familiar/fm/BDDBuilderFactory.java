package fr.familiar.fm;

import gsd.synthesis.BDDBuilder;
import gsd.synthesis.FeatureGraphFactory;

import java.util.Map;

public class BDDBuilderFactory {

	public static BDDBuilder<String> mkBuilder(Map<String, Integer> map) {
		BDDBuilder<String> builder = new BDDBuilder<String>(
				FeatureGraphFactory.mkStringFactory());
		builder.getFeatureMap().putAll(map);
		return builder;
	}

}
