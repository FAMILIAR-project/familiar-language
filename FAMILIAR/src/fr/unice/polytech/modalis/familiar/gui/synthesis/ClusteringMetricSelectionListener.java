package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.AlwaysZeroMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.RandomMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.SimmetricsMetric;

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
			default:
				break;

			}
			
		}

	}

}
