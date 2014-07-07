package fr.familiar.test.heuristics;

import fr.familiar.variable.FeatureModelVariable;

public class CommonEdgesDiff implements Comparable<CommonEdgesDiff> {

	private FeatureModelVariable fm;
	private String firstHeuristic;
	private double firstScore;
	private String secondHeuristic;
	private double secondScore;
	
	public CommonEdgesDiff(FeatureModelVariable fm, String firstHeuristic, double firstScore, String secondHeuristic, double secondScore) {
		super();
		this.fm = fm;
		this.firstHeuristic = firstHeuristic;
		this.firstScore = firstScore;
		this.secondHeuristic = secondHeuristic;
		this.secondScore = secondScore;
	}

	public FeatureModelVariable getFm() {
		return fm;
	}

	public String getFirstHeuristic() {
		return firstHeuristic;
	}

	public double getFirstScore() {
		return firstScore;
	}

	public String getSecondHeuristic() {
		return secondHeuristic;
	}

	public double getSecondScore() {
		return secondScore;
	}

	public double getScoreDiff() {
		return firstScore - secondScore;
	}
	
	
	@Override
	public int compareTo(CommonEdgesDiff o) {
		return Double.compare(this.getScoreDiff(), o.getScoreDiff());
	}
	
	@Override
	public String toString() {
		return fm.getCompleteIdentifier() + "\n"
				+ firstHeuristic + "\n"
				+ firstScore + "\n"
				+ secondHeuristic + "\n"
				+ secondScore + "\n"
				+ getScoreDiff();
	}
}
