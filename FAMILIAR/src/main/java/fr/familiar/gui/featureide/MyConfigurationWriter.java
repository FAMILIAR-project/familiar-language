/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

package fr.familiar.gui.featureide;

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
