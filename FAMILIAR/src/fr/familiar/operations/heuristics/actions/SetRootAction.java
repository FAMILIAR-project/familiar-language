package fr.familiar.operations.heuristics.actions;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SetRootAction implements KSynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private String root;
	
	public SetRootAction(InteractiveFMSynthesizer synthesizer, String root) {
		this.synthesizer = synthesizer;
		this.root = root;
	}

	@Override
	public void execute() {
		synthesizer.setRootUnrecorded(root);
	}

}
