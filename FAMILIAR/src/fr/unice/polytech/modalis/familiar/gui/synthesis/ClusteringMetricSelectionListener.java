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
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WikipediaMinerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WuPalmerMetric;

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
				environment.setClusteringMetric(new SimmetricsMetric(metric));
				break;
			case SIMMETRICS_LEVENSHTEIN:
				environment.setClusteringMetric(new SimmetricsMetric(metric));
				break;
			case WORDNET_WUP:
				Dictionary dictionary = WordNetPropertyFileChooser.getInstance();
				if (dictionary != null) {
					environment.setClusteringMetric(new WuPalmerMetric(dictionary));	
				}
				break;
			case WIKIPEDIA_MINER:
				JFileChooser fileChooser = new JFileChooser();
				int choice = fileChooser.showOpenDialog(FamiliarEditor.INSTANCE);
				if (choice == JFileChooser.APPROVE_OPTION) {
					File propertiesFile = fileChooser.getSelectedFile();
					WikipediaMinerMetric wikipediaMinerMetric = new WikipediaMinerMetric(propertiesFile.getAbsolutePath());
					try {
						wikipediaMinerMetric.loadDatabase();
						environment.setClusteringMetric(wikipediaMinerMetric);
					} catch (Exception e1) {
						
					}
				}	
				break;
				
				// TODO : add LSA when ready
			default:
				break;

			}
			
		}

	}

}
