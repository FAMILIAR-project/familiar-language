package fr.unice.polytech.modalis.familiar.gui.synthesis.actions;

import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SelectParentAction implements SynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private String feature;
	private String parent;

	public SelectParentAction(InteractiveFMSynthesizer synthesizer, String feature, String parent) {
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
