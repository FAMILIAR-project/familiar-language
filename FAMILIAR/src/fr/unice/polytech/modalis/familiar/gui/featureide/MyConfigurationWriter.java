package fr.unice.polytech.modalis.familiar.gui.featureide;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.ConfigurationWriter;
import de.ovgu.featureide.fm.core.configuration.SelectableFeature;

public class MyConfigurationWriter extends ConfigurationWriter {

	public MyConfigurationWriter(Configuration conf) {
		super(conf);
	}

	/*
	 * Mathieu: I have put the exception handling directly into the method
	 */
	public String writeIntoString() {
		StringBuffer out = new StringBuffer();
		// writeSelectedFeatures(configuration.getRoot(), out);
		// ==============================
		Class c = this.getClass();
		Field configurationField;
		try {
			configurationField = c.getDeclaredField("configuration");
			configurationField.setAccessible(true);
			Method configurationMethod = configurationField.getClass()
					.getDeclaredMethod("getRoot");
			SelectableFeature selectableFeature = (SelectableFeature) configurationMethod
					.invoke(configurationField);
			this.getClass().getMethod("writeSelectedFeatures")
					.invoke(this, selectableFeature, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ===============================
		return out.toString();
	}

}
