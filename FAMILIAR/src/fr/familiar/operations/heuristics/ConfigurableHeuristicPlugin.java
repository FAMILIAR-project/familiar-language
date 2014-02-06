package fr.familiar.operations.heuristics;

import java.io.File;

public interface ConfigurableHeuristicPlugin extends KSynthesisPlugin {
	
	boolean init(File configFile);
	boolean stop();
}
