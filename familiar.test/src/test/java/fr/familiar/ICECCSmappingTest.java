/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project.
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>.
 */

package fr.familiar;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */
public class ICECCSmappingTest extends FMLTest {

	private String _VSARid = "vsarSpecialization";

	private String _PCFid = "pcf";

	private String _glFMid = "glFM"; // global name FM

	public ICECCSmappingTest(FMLShell shell, String vSARspecification,
			String pCFspecification, String prules) throws Exception {
		_shell = shell;
		_environment = _shell.getCurrentEnv();
		_builder = FMLCommandInterpreter.getBuilder();
		map(vSARspecification, pCFspecification, prules);

	}

	private void map(String VSARspecification, String PCFspecification,
			String prules) throws Exception {
		// after specialization

		String VSARspecialization = _VSARid + " = " + VSARspecification + "\n";
		_shell.parse(VSARspecialization);
		String PCFfm = _PCFid + " = " + PCFspecification + "\n";
		_shell.parse(PCFfm);
		_shell.parse(prules);
		_shell.parse(_glFMid + " = aggregate { " + _VSARid
				+ " pcf } withMapping prules\n");
		// _shell.parse("VSARprime = slice " + _glFMid + " including " + _VSARid
		// + ".*\n");

	}

	public FeatureModelVariable getVSAR() throws Exception {
		return getFMVariable(_VSARid);
	}

	public FeatureModelVariable getGl() throws Exception {
		return getFMVariable(_glFMid);
	}

}
