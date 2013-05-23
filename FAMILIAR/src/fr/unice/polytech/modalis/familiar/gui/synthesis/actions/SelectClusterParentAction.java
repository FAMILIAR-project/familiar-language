package fr.unice.polytech.modalis.familiar.gui.synthesis.actions;

import java.util.Set;

import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SelectClusterParentAction implements SynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private Set<String> children;
	private String parent;

	public SelectClusterParentAction(InteractiveFMSynthesizer synthesizer, Set<String> children, String parent) {
		this.synthesizer = synthesizer;
		this.children = children;
		this.parent = parent;
	}

	@Override
	public void undo() {
		for (String child : children) {
			synthesizer.resetParentCandidates(child);
		}
	}
}
