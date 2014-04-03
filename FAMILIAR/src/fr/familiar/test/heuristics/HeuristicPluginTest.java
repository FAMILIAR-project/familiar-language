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

package fr.familiar.test.heuristics;

import java.io.File;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;
import fr.familiar.experimental.FGroup;
import fr.familiar.operations.heuristics.ConfigurableHeuristicPlugin;
import fr.familiar.operations.heuristics.Heuristic;
import fr.familiar.operations.heuristics.KSynthesisPlugin;
import fr.familiar.operations.heuristics.KSynthesisPluginLoader;
import fr.familiar.test.FMLTest;
import fr.familiar.variable.FeatureModelVariable;
import gsd.graph.ImplicationGraph;

public class HeuristicPluginTest {

	@Test
	public void testHeuristicPluginLoader() {
		KSynthesisPluginLoader loader = new KSynthesisPluginLoader();
		List<KSynthesisPlugin> plugins = loader.loadFromDirectory(new File("plugins/heuristics"));
		assertNotNull(plugins);
		for (KSynthesisPlugin plugin : plugins) {
			if (!(plugin instanceof ConfigurableHeuristicPlugin) && plugin instanceof Heuristic) {
				System.out.println(plugin.getName());
				Heuristic heuristic = (Heuristic) plugin;
				heuristic.setImplicationGraph(new ImplicationGraph<String>());
				heuristic.setGroups(new HashSet<FGroup>());
				System.out.println(heuristic.similarity("car", "wheel"));	
			}
		}	
		
	}
	
}
