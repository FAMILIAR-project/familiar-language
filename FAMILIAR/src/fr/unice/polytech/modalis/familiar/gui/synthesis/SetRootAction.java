package fr.unice.polytech.modalis.familiar.gui.synthesis;

import fr.unice.polytech.modalis.familiar.gui.synthesis.actions.SynthesisAction;
import fr.unice.polytech.modalis.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SetRootAction implements SynthesisAction {
	
	private InteractiveFMSynthesizer synthesizer;
	private String root;

	public SetRootAction(InteractiveFMSynthesizer synthesizer, String root) {
		this.synthesizer = synthesizer;
		this.root = root;
		
	}

	@Override
	public void undo() {
		synthesizer.resetParentCandidates(root);
	}

}
