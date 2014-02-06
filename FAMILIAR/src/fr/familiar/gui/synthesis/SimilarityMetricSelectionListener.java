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
