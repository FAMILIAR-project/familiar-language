package fr.familiar.operations.heuristics.actions;

import fr.familiar.operations.heuristics.InteractiveFMSynthesizer;

public class SelectParentAction implements KSynthesisAction {

	private InteractiveFMSynthesizer synthesizer;
	private String child;
	private String parent;

	public SelectParentAction(InteractiveFMSynthesizer synthesizer, String child, String parent) {
		this.synthesizer = synthesizer;
		this.child = child;
		this.parent = parent;
	}
	
	@Override
	public void execute() {
		synthesizer.selectParentUnrecorded(child, parent);
	}
}
