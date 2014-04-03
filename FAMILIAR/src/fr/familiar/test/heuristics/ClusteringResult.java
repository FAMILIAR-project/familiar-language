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
