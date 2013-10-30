package fr.familiar.gui.synthesis.actions;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class IgnoreParentAction implements SynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private String feature;
	private String parent;
	
	public IgnoreParentAction(InteractiveFMSynthesizer synthesizer, String feature, String parent) {
		this.synthesizer = synthesizer;
		this.feature = feature;
		this.parent = parent;
	}
	
	@Override
	public void undo() {
		synthesizer.resetParentCandidates(feature);
		// TODO : restore previous actions except this action
	}
}
