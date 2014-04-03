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
import fr.familiar.operations.heuristics.metrics.LevenshteinMetric;
import fr.familiar.operations.heuristics.metrics.MetricName;
import fr.familiar.operations.heuristics.metrics.PathLengthMetric;
import fr.familiar.operations.heuristics.metrics.RandomMetric;
import fr.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.familiar.operations.heuristics.metrics.SmithWatermanMetric;
import fr.familiar.operations.heuristics.metrics.WikipediaMinerDB;
import fr.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.familiar.operations.heuristics.metrics.WuPalmerMetric;

public class SimilarityMetricSelectionListener implements ActionListener {

	private MetricName metric;

	public SimilarityMetricSelectionListener(MetricName metric) {
		this.metric = metric;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

		if (tab instanceof FMSynthesisEnvironment) {
			FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
			switch (metric) {
			case ALWAYS_ZERO:
				environment.setParentSimilarityMetric(new AlwaysZeroMetric());
				break;
			case RANDOM:
				environment.setParentSimilarityMetric(new RandomMetric());
				break;
			case SIMMETRICS_SMITHWATERMAN:
//				environment.setParentSimilarityMetric(new SimmetricsMetric(metric));
				environment.setParentSimilarityMetric(new SmithWatermanMetric());
				break;
			case SIMMETRICS_LEVENSHTEIN:
//				environment.setParentSimilarityMetric(new SimmetricsMetric(metric));
				environment.setParentSimilarityMetric(new LevenshteinMetric());
				break;
			case WORDNET_WUP:
				File dictionaryWUP = WordNetPropertyFileChooser.getInstance();
				if (dictionaryWUP != null) {
					WuPalmerMetric wup = new WuPalmerMetric();
					wup.init(dictionaryWUP);
					environment.setParentSimilarityMetric(wup);	
				}
				break;
			case WORDNET_PATHLENGTH:
				File dictionaryPL = WordNetPropertyFileChooser.getInstance();
				if (dictionaryPL != null) {
					PathLengthMetric pl = new PathLengthMetric();
					pl.init(dictionaryPL);
					environment.setParentSimilarityMetric(pl);	
				}
				break;
//			case LSA_LSI: // TODO : restore this when LSA is ready
//				WeightedImplicationGraph<String> originalBig = environment.getSynthesizer().getOriginalBig();
//				try {
//					environment.setParentSimilarityMetric(new LatentSemanticMetric());
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				break;
			case WIKIPEDIA_MINER:
				JFileChooser fileChooser = new JFileChooser();
				int choice = fileChooser.showOpenDialog(FamiliarEditor.INSTANCE);
				if (choice == JFileChooser.APPROVE_OPTION) {
					File propertiesFile = fileChooser.getSelectedFile();
					try {
						WikipediaMinerMetric wikipediaMinerMetric = new WikipediaMinerMetric();
						wikipediaMinerMetric.init(propertiesFile);
						environment.setParentSimilarityMetric(wikipediaMinerMetric);
					} catch (Exception e1) {
						
					}
				}	
				break;
			default:
				break;

			}
		}
	}

}
