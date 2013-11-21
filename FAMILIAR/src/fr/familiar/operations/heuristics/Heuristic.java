package fr.familiar.operations.heuristics;

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

public interface Heuristic {

	boolean isMutexGroupsRequired();
	boolean isXorGroupsRequired();
	boolean isOrGroupsRequired();
	
	void setImplicationGraph(ImplicationGraph<String> implicationGraph);
	void setGroups(Set<FGroup> groups);
	
	double similarity(String child, String parent);
}
