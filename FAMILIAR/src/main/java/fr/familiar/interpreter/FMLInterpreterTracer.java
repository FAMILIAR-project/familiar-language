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

import java.util.ArrayList;
import java.util.List;

public class FMLInterpreterTracer {

	private List<String> _commands;

	public List<String> getTraces() {
		return _commands;
	}

	public FMLInterpreterTracer() {
		_commands = new ArrayList<String>();
	}

	public void registerCommand(String command) {

		_commands.add(command);
		onRegister(command);
	}

	public String last() {
		if (_commands.isEmpty())
			throw new IllegalArgumentException("no last command");
		return _commands.get(_commands.size() - 1);
	}

	public String first() {
		if (_commands.isEmpty())
			throw new IllegalArgumentException("no first command");
		return _commands.get(0);
	}

	/**
	 * @param occ
	 *            first characters of the commands
	 * @return possible completions for the specified command.
	 */
	List<String> getCompletions(String prefix) {
		List<String> res = new ArrayList<String>();
		if (_commands.isEmpty())
			return res;
		for (String candidate : _commands) {
			if (candidate.startsWith(prefix))
				res.add(candidate);
		}

		return res;
	}

	protected void onRegister(String command) {
		// nothing by default
	}

	public void reset() {
		_commands = new ArrayList<String>();

	}

}
