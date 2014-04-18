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

package fr.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import net.sf.extjwnl.dictionary.Dictionary;
import fr.familiar.gui.FamiliarEditor;
import fr.familiar.gui.Tab2EnvVar;
import fr.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
//import fr.familiar.operations.heuristics.metrics.LevenshteinMetric;
import fr.familiar.operations.heuristics.metrics.MetricName;
import fr.familiar.operations.heuristics.metrics.PathLengthMetric;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
//import fr.familiar.operations.heuristics.metrics.SimmetricsMetric;
//import fr.familiar.operations.heuristics.metrics.SmithWatermanMetric;
//import fr.familiar.operations.heuristics.metrics.WikipediaMinerDB;
//import fr.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.familiar.operations.heuristics.metrics.WuPalmerMetric;

public class ClusteringMetricSelectionListener implements ActionListener {

	private MetricName metric;

	public ClusteringMetricSelectionListener(MetricName metric) {
		this.metric = metric;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

		if (tab instanceof FMSynthesisEnvironment) {
			FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
			switch (metric) {
			case ALWAYS_ZERO:
				environment.setClusteringMetric(new AlwaysZeroMetric());
				break;
			case RANDOM:
				environment.setClusteringMetric(new RandomMetric());
				break;
			case SIMMETRICS_SMITHWATERMAN:
//				environment.setClusteringMetric(new SimmetricsMetric(metric));
//				environment.setClusteringMetric(new SmithWatermanMetric());
				break;
			case SIMMETRICS_LEVENSHTEIN:
//				environment.setClusteringMetric(new SimmetricsMetric(metric));
//				environment.setClusteringMetric(new LevenshteinMetric());
				break;
			case WORDNET_WUP:
				File dictionaryWUP = WordNetPropertyFileChooser.getInstance();
				if (dictionaryWUP != null) {
					WuPalmerMetric wup = new WuPalmerMetric();
					wup.init(dictionaryWUP);
					environment.setClusteringMetric(wup);	
				}
				break;
			case WORDNET_PATHLENGTH:
				File dictionaryPL = WordNetPropertyFileChooser.getInstance();
				if (dictionaryPL != null) {
					PathLengthMetric pl = new PathLengthMetric();
					pl.init(dictionaryPL);
					environment.setClusteringMetric(pl);	
				}
				break;
			case WIKIPEDIA_MINER:
//				JFileChooser fileChooser = new JFileChooser();
//				int choice = fileChooser.showOpenDialog(FamiliarEditor.INSTANCE);
//				if (choice == JFileChooser.APPROVE_OPTION) {
//					File propertiesFile = fileChooser.getSelectedFile();
//					try {
//						WikipediaMinerMetric wikipediaMinerMetric = new WikipediaMinerMetric();
//						wikipediaMinerMetric.init(propertiesFile);
//						environment.setClusteringMetric(wikipediaMinerMetric);
//					} catch (Exception e1) {
//						e1.printStackTrace();
//					}
//				}	
				break;
				
				// TODO : add LSA when ready
			default:
				break;

			}
			
		}

	}

}
