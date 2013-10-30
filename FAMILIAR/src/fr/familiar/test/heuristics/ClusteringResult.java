package fr.familiar.test.heuristics;

public class ClusteringResult {

	private int nbClusters;
	private int sumSize;
	private int nbValidClusters;
	private int nbFeaturesInCorrectClusters;
	
	public int getNbClusters() {
		return nbClusters;
	}
	public void setNbClusters(int nbClusters) {
		this.nbClusters = nbClusters;
	}
	public double getMeanSize() {
		if (nbClusters > 0) {
			return sumSize / (double) nbClusters;
		} else {
			return 0.0;
		}
	}
	public int getNbValidClusters() {
		return nbValidClusters;
	}
	public void setNbValidClusters(int nbValidClusters) {
		this.nbValidClusters = nbValidClusters;
	}
	public int getNbFeaturesInCorrectClusters() {
		return nbFeaturesInCorrectClusters;
	}
	public void setNbFeaturesInCorrectClusters(int nbFeaturesInCorrectClusters) {
		this.nbFeaturesInCorrectClusters = nbFeaturesInCorrectClusters;
	}
	public int getSumSize() {
		return sumSize;
	}
	public void setSumSize(int sumSize) {
		this.sumSize = sumSize;
	}
	
	
	
}
