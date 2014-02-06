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
