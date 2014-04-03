/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar.operations.heuristics;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class KSynthesisPluginLoader {

	/**
	 * Load heuristic plugins from the jar containted in a directory
	 * @param directory : path to a directory containing plugin jars
	 * @return list of loaded plugins or null if an error happened
	 */
	public List<KSynthesisPlugin> loadFromDirectory(File directory) {
		if (directory.isDirectory()) {
			List<KSynthesisPlugin> plugins = new ArrayList<KSynthesisPlugin>();
			for (File file : directory.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".jar")) {
					List<KSynthesisPlugin> loadedPlugins = loadFromJar(file);
					if (loadedPlugins != null) {
						plugins.addAll(loadedPlugins);	
					} else {
						return null;
					}
				}
			}
			return plugins;
		} else {
			return null;
		}
	}
	
	/**
	 * Load heuristic plugins from a jar
	 * @param jar : path to a jar containing the plugins to load
	 * @return list of loaded plugins or null if an error happened
	 */
	public List<KSynthesisPlugin> loadFromJar(File jar) {
		
		if (jar.exists()) {
			List<KSynthesisPlugin> plugins = new ArrayList<KSynthesisPlugin>();
			
			try {
				// Define class loader
				URL[] urls = new URL[]{jar.toURI().toURL()};
				URLClassLoader classLoader = URLClassLoader.newInstance(urls);
				
				// Search plugin in the jar
				JarFile jarFile = new JarFile(jar);
				Enumeration<JarEntry> entries = jarFile.entries();
				while (entries.hasMoreElements()) {
					JarEntry entry = entries.nextElement();
					if (entry.toString().endsWith(".class")) {
						// Convert file name into class name
						String className = entry.toString();
						className = className.substring(0, className.length() - 6); // remove ".class"
						className = className.replaceAll("/", ".");
						
						// Check if the class implements HeuristicPlugin and instantiate it
						Class<?> pluginClass = Class.forName(className, true, classLoader);
						boolean isAbstract = Modifier.isAbstract(pluginClass.getModifiers()); 
						boolean isPlugin = KSynthesisPlugin.class.isAssignableFrom(pluginClass);
						if (!isAbstract && isPlugin) {
							KSynthesisPlugin plugin = (KSynthesisPlugin) pluginClass.newInstance();
							plugins.add(plugin);
						}
						
					}
				}
				
				return plugins;
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			
		} else {
			return null;			
		}

	}


}
