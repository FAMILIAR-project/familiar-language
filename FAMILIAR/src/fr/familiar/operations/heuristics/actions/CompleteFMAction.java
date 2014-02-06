package fr.familiar.operations.heuristics.actions;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class CompleteFMAction implements KSynthesisAction {

	private InteractiveFMSynthesizer synthesizer;

	public CompleteFMAction(InteractiveFMSynthesizer synthesizer) {
		this.synthesizer = synthesizer;
		
	}
	
	@Override
	public void execute() {
		synthesizer.computeCompleteFeatureModelUnrecorded();
	}
}
