/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
 * http://familiar-project.github.com/
 *
 * FAMILIAR is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

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
