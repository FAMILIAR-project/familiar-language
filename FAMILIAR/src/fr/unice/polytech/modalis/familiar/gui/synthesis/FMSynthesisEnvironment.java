package fr.unice.polytech.modalis.familiar.gui.synthesis;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import fr.unice.polytech.modalis.familiar.gui.Tab2EnvVar;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.FeatureSimilarityMetric;
import fr.unice.polytech.modalis.familiar.operations.heuristics.metrics.MetricName;

public class FMSynthesisEnvironment extends JPanel implements Observer{

	private InteractiveFMSynthesizer synthesizer;
	private FMViewer fmViewer;
	private BIGViewer bigViewer;
	private ParentSelector parentSelector;
	private ClusterViewer clusterViewer;
	private CliqueViewer cliqueViewer;

	public FMSynthesisEnvironment(InteractiveFMSynthesizer synthesizer) {
		this.synthesizer = synthesizer;
		synthesizer.addObserver(this);

		// Create views
		fmViewer = new FMPanel();
		bigViewer = new BIGPanel();
		parentSelector = new ParentSelector(this);
		clusterViewer = new ClusterViewer();
		cliqueViewer = new CliqueViewer();

		// Set layout
		this.setLayout(new GridLayout(1, 1));
		JSplitPane cliqueClusterSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, clusterViewer, cliqueViewer);
		JSplitPane leftSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, parentSelector, cliqueClusterSplitPane);
		JSplitPane fmBigSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, fmViewer, bigViewer); 
		JSplitPane globalSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftSplitPane, fmBigSplitPane);
		this.add(globalSplitPane);

		update(synthesizer, null); // Initialize display
	}


	@Override
	public void update(Observable o, Object arg) {
		fmViewer.updateFM(synthesizer.getFeatureModelVariable());
		bigViewer.updateBIG(synthesizer.getImplicationGraph());
		parentSelector.updateParents(synthesizer.getParentCandidates());
		Set<Set<String>> similarityClusters = synthesizer.getSimilarityClusters();
		if (similarityClusters != null) {
			clusterViewer.updateClusters(similarityClusters);	
		}
		cliqueViewer.updateCliques(synthesizer.getCliques());
	}

	public void selectParent(String child, String parent) {
		synthesizer.selectParent(child, parent);
	}

	public void ignoreParent(String child, String parent) {
		synthesizer.ignoreParent(child, parent);
	}

	public void updateSelectedFeatures(List<String> selectedFeatures, List<String> unselectedFeatures) {
		clusterViewer.updateSelectedClusters(selectedFeatures, unselectedFeatures);
		cliqueViewer.updateSelectedCliques(selectedFeatures, unselectedFeatures);
	}

	public void setRoot(String root) {
		synthesizer.setRoot(root);
	}

	public void setParentSimilarityMetric(FeatureSimilarityMetric metric) {
		synthesizer.setParentSimilarityMetric(metric);
	}


	public void setClusteringMetric(FeatureSimilarityMetric metric) {
		synthesizer.setClusteringParameters(metric, synthesizer.getClusteringThreshold());
	}

	public void setClusteringThreshold(double threshold) {
		synthesizer.setClusteringParameters(synthesizer.getClusteringSimilarityMetric(), threshold);
	}

	public void computeCompleteFeatureModel() {
		synthesizer.computeCompleteFeatureModel();
	}


	public static JMenu createSynthesisMenu() {

		// Parent similarity metric selection
		JMenu parentSimilarityMetricMenu = new JMenu("Similarity metric");
		ButtonGroup parentSimilarityMetricGroup = new ButtonGroup();

		for (MetricName metric : MetricName.values()) {
			JRadioButtonMenuItem metricItem = new JRadioButtonMenuItem(metric.toString());
			metricItem.addActionListener(new SimilarityMetricSelectionListener(metric));
			parentSimilarityMetricMenu.add(metricItem);
			parentSimilarityMetricGroup.add(metricItem);
			if (metric.equals(InteractiveFMSynthesizer.defaultParentSimilarityMetric)) {
				metricItem.setSelected(true);	
			}
		}


		// Clustering metric selection
		JMenu clusteringMetricMenu = new JMenu("Clustering metric");
		ButtonGroup clusteringMetricGroup = new ButtonGroup();

		for (MetricName metric : MetricName.values()) {
			JRadioButtonMenuItem metricItem = new JRadioButtonMenuItem(metric.toString());
			metricItem.addActionListener(new ClusteringMetricSelectionListener(metric));
			clusteringMetricMenu.add(metricItem);
			clusteringMetricGroup.add(metricItem);
			if (metric.equals(InteractiveFMSynthesizer.defaultClusteringSimilarityMetric)) {  
				metricItem.setSelected(true);
			}
		}

		// Clustering threshold selection
		JMenuItem clusteringThreshold= new JMenuItem("Define clustering threshold...");
		clusteringThreshold.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

				if (tab instanceof FMSynthesisEnvironment) {
					FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
					
					// Display a input dialog to enter the threshold
					ThresholdValueDialog dialog = new ThresholdValueDialog();
					
					double threshold = dialog.getValue();
					if (threshold != -1) {
						environment.setClusteringThreshold(threshold);	
					}
					
				}
			}
		});

		// Complete FM according to a heuristic
		JMenuItem completeFM = new JMenuItem("Complete FM");
		completeFM.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component tab = Tab2EnvVar.INSTANCE.getTab().getSelectedComponent();

				if (tab instanceof FMSynthesisEnvironment) {
					FMSynthesisEnvironment environment = (FMSynthesisEnvironment) tab;
					environment.computeCompleteFeatureModel();
				}
			}
		});

		// Create menu
		JMenu synthesisMenu = new JMenu("Synthesis");

		synthesisMenu.add(parentSimilarityMetricMenu);
		synthesisMenu.add(clusteringMetricMenu);
		synthesisMenu.add(clusteringThreshold);
		synthesisMenu.add(completeFM);

		// Enable the menu only when the active tab is a synthesis environment
		Tab2EnvVar.INSTANCE.getTab().addChangeListener(new SynthesisTabListener(synthesisMenu));

		return synthesisMenu;
	}


}
