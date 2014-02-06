package fr.familiar.operations.heuristics;

import fr.familiar.experimental.FGroup;
import gsd.graph.ImplicationGraph;

import java.util.Set;

public abstract class SimpleHeuristic implements Heuristic {

	@Override
	public boolean isMutexGroupsRequired() {
		return false;
	}

	@Override
	public boolean isXorGroupsRequired() {
		return false;
	}

	@Override
	public boolean isOrGroupsRequired() {
		return false;
	}

	@Override
	public void setImplicationGraph(ImplicationGraph<String> implicationGraph) {

	}

	@Override
	public void setGroups(Set<FGroup> groups) {

	}

}
