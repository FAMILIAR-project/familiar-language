package fr.familiar.operations;

public enum SortStrategySlicingCriterion {
	RANDOM, // random
	UP, // features closed to the root first
	UP_AND_SIBLING, // features closed to the root first + closed siblings
	BOTTOM
	// ~leaves features first

}
