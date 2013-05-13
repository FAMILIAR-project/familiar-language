package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;

import net.sf.extjwnl.JWNLException;
import net.sf.extjwnl.dictionary.Dictionary;
import fr.unice.polytech.modalis.familiar.gui.FamiliarEditor;
import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.LatentSemanticMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.WuPalmerMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.mst.WeightedImplicationGraph;

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
			case WORDNET_WUP:
				Dictionary dictionary = WordNetPropertyFileChooser.getInstance();
				if (dictionary != null) {
					environment.setParentSimilarityMetric(new WuPalmerMetric(dictionary));	
				}
				break;
			case LSA_LSI:
				WeightedImplicationGraph<String> originalBig = environment.getSynthesizer().getOriginalBig();
				try {
					environment.setParentSimilarityMetric(new LatentSemanticMetric(originalBig));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
			default:
				break;

			}
		}
	}

}
