package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import net.sf.extjwnl.dictionary.Dictionary;
import fr.unice.polytech.modalis.familiar.gui.FamiliarEditor;
import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerDB;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WuPalmerMetric;

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
				environment.setParentSimilarityMetric(new SimmetricsMetric(metric));
				break;
			case SIMMETRICS_LEVENSHTEIN:
				environment.setParentSimilarityMetric(new SimmetricsMetric(metric));
				break;
			case WORDNET_WUP:
				Dictionary dictionaryWUP = WordNetPropertyFileChooser.getInstance();
				if (dictionaryWUP != null) {
					environment.setParentSimilarityMetric(new WuPalmerMetric(dictionaryWUP));	
				}
				break;
			case WORDNET_PATHLENGTH:
				Dictionary dictionaryPL = WordNetPropertyFileChooser.getInstance();
				if (dictionaryPL != null) {
					environment.setParentSimilarityMetric(new WuPalmerMetric(dictionaryPL));	
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
					WikipediaMinerDB wikipediaMinerDB = new WikipediaMinerDB(propertiesFile.getAbsolutePath());
					try {
						wikipediaMinerDB.loadDatabase();
						WikipediaMinerMetric wikipediaMinerMetric = new WikipediaMinerMetric(wikipediaMinerDB);
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
