package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;

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
			default:
				break;

			}
			
		}
	}

}
