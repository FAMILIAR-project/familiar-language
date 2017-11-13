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

import java.net.URI;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;

import de.ovgu.featureide.fm.core.FeatureModel;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.configuration.ConfigurationWriter;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.utils.URIUtils;
import fr.familiar.variable.featureide.ConfigurationVariableFeatureIDEImpl;

public class ConfigurationRegister {

	private static HashMap<String, ConfigurationVariableFeatureIDEImpl> config2File = new HashMap<String, ConfigurationVariableFeatureIDEImpl>();

	@Deprecated
	// use IFile instead
	public static void register(String key, ConfigurationVariableFeatureIDEImpl value) {
		config2File.put(key, value);
	}

	public static FeatureModel getFeatureModel(String key) {
		ConfigurationVariableFeatureIDEImpl cf = config2File.get(key);
		return cf.getConfiguration().getFeatureModel();
	}

	private static ConfigurationVariableFeatureIDEImpl getConfigurationVariable(String key) {
		return config2File.get(key);
	}

	public static Configuration getConfiguration(String key) {
		return getConfigurationVariable(key).getConfiguration();
	}

	// ===================================================================
	private static HashMap<IFile, ConfigurationVariableFeatureIDEImpl> ifile_conf = new HashMap<IFile, ConfigurationVariableFeatureIDEImpl>();
	private static HashMap<ConfigurationVariableFeatureIDEImpl, IFile> conf_ifile = new HashMap<ConfigurationVariableFeatureIDEImpl, IFile>();

	public static boolean isRegistred(ConfigurationVariableFeatureIDEImpl conf) {
		return ifile_conf.containsValue(conf);
	}

	public static void register(ConfigurationVariableFeatureIDEImpl value) {

		String directory = FMLShell.getInstance().getTemporaryPath();
		String name = value.getIdentifier();
		String filename = directory + name + ".equation";
		URI uri = URI.create(filename);
		IFile file = URIUtils.getIFileFromURI(uri);

		ConfigurationWriter cw = new ConfigurationWriter(
				value.getConfiguration());
		try {
			cw.saveToFile(file);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		ifile_conf.put(file, value);
		conf_ifile.put(value, file);
	}

	public static ConfigurationVariableFeatureIDEImpl getConf(IFile file) {

		ConfigurationVariableFeatureIDEImpl conf = ifile_conf.get(file);
		if (conf != null) {
			ConfigurationWriter cw = new ConfigurationWriter(
					conf.getConfiguration());
			try {
				cw.saveToFile(file);
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}

		return conf;
	}

	public static IFile getFile(ConfigurationVariableFeatureIDEImpl conf) {
		IFile file = conf_ifile.get(conf);
		ConfigurationWriter cw = new ConfigurationWriter(
				conf.getConfiguration());
		try {
			cw.saveToFile(file);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return file;
	}
}
