package fr.familiar.interpreter;

/**
 * absolutely weird
 * @author macher
 *
 */
public class FMLPreference {

	// TODO: constructor
	// TODO: add other things

	private boolean _isConfigurationGraphicalDisplay = true;
	private boolean _graphicalDisplay = true;

	private boolean _traceActivated = false;

	private boolean _isVariableViewActivated = false;

	public boolean isGraphicalDisplay() {
		return _graphicalDisplay;
	}

	public void setGraphicalDisplay(boolean display) {
		_graphicalDisplay = display;
	}

	public boolean isTraceActivated() {
		return _traceActivated;
	}

	public boolean isVariableViewActivated() {
		return _isVariableViewActivated;
	}

	public boolean isConfigurationGraphicalDisplay() {
		return _isConfigurationGraphicalDisplay;
	}

}
