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

package fr.familiar.tvl;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;

import fr.familiar.FMLTest;
import fr.familiar.fm.converter.tvl.TVLTranslator;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

/**
 * 
 * @author Charles Vanbeneden
 * 
 */
public abstract class TranslatorTest extends FMLTest {

	protected TVLTranslator _translator;
	protected Set<Set<String>> _solutionsTVL;
	protected Set<Set<String>> _configsFAMILIAR;
	public static final String FM_LAPTOP_TVL = "root Laptop { group allof { " + "Screen{ group oneOf{" + "				S12," + "				S15,"
			+ "				S17" + "			}" + "		}," + "	CPU {" + "			group oneOf{" + "				LowTDP {" + "					group oneOf{" + "						Atom270,"
			+ "						AtomZ320" + "						}" + "				}," + "			HighTDP {" + "					group oneOf{" + "						Core2T6600," + "						Core2P7350"
			+ "					}" + "				}" + "			}" + "		}," + "		RAM {" + "			group oneOf {" + "				Kingstom2Go666," + "				Kingstom4Go666,"
			+ "				Corsair4Go1030" + "			}" + "		}," + "		CG {" + "			group oneOf {" + "				Integrated{" + "					group allOf {GMA}	"
			+ "				}," + "			Standalone" + "					group oneOf {" + "						Nvidia8400M," + "						Nvdia8600M," + "						Nvidia3670,"
			+ "						Nvidia9400M" + "					}" + "				}" + "		}, " + "		HDD {" + "			group oneOf {" + "				WD160Go5400tr," + "				WD500Go {"
			+ "					group oneOf {" + "						S5400tr," + "						S7200tr" + "					}" + "				}" + "			}" + "		}," + "		Battery {"
			+ "			group oneOf {" + "				Cells6," + "				Cells9" + "			}" + "		}," + "		Connectivity{" + "			group allOf{" + "				Wifi {"
			+ "					group oneOf{" + "						ABG," + "						N" + "					}" + "				}," + "				opt Bluetooth" + "			}" + "		},"
			+ "		opt Warranty {" + "			group oneOf{" + "				Year2theft," + "				Year3theft" + "			}" + "		}" + "	}" + "}";

	@Before
	public void setUp() throws Exception {
		super.setUp();
		_translator = null;
		_solutionsTVL = null;
		_configsFAMILIAR = null;
	}

	/**
	 * seems buggy!
	 * @param inputTVL
	 * @throws Exception
	 */
	@Deprecated
	protected void compareProductionsTVLVersusFAMILIARFromTVLInput(String inputTVL) throws Exception {
		String inputFAMILIAR = null;
		try {
			
			_translator = new TVLTranslator(inputTVL);
			
			inputFAMILIAR = _translator.getFAMILIARFMLOutput();
			_shell.parse("fm = " + inputFAMILIAR);
			FeatureModelVariable fmVariable = getFMVariable("fm");

			_configsFAMILIAR = transformFAMILIARConfigsRepresentation(fmVariable.configs());
			_solutionsTVL = _translator.getTVLFilteredSolutions();
			

			String debug = "\r\nFAMILIAR input :\r\n" + inputFAMILIAR + "\r\n";
			if (_configsFAMILIAR == null)
				debug = debug + "FAMILIAR configs :\r\nnull\r\n";
			if (_solutionsTVL == null)
				debug = debug + "TVL configs :\r\nnull\r\n";
			if (_configsFAMILIAR != null && _solutionsTVL != null) {
				debug = debug + "\r\n#FProd : " + _configsFAMILIAR.size() + " / #TProd " + _solutionsTVL.size()
						+ "\r\nTVL Solutions : \r\n" + solutionToString(_solutionsTVL) + "FAMILIAR Solutions :\r\n"
						+ solutionToString(_configsFAMILIAR) + "\r\n";
				if (_translator.hasWarnings())
					debug = debug + "Warnings :\r\n" + _translator.getWarningsToString();
			} else
				assertTrue(debug, false);
			assertTrue(debug, _configsFAMILIAR.size() == _solutionsTVL.size());

			assertTrue(debug, _configsFAMILIAR.containsAll(_solutionsTVL) && _solutionsTVL.containsAll(_configsFAMILIAR));
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * @param configsFAMILIARs
	 * @return
	 */
	protected Set<Set<String>> transformFAMILIARConfigsRepresentation(Set<Variable> configsFAMILIARs) {
		Set<Set<String>> configsFAMILIAR = new HashSet<Set<String>>();
		Set<String> subConfig = null;
		for (Variable fts : configsFAMILIARs) {
			SetVariable sv = (SetVariable) fts;
			Set<Variable> vs = sv.getVars();
			subConfig = new HashSet<String>();
			for (Variable ft : vs) {
				String name = ((FeatureVariable) ft).getFtName();
				// clean temp vars
				if (!name.contains("Temp_Var_") && !name.contains("Temp_Feature")) {
					String[] splitted = name.split("_");
					subConfig.add(splitted[splitted.length - 1]);
				}
			}
			configsFAMILIAR.add(subConfig);
		}
		return configsFAMILIAR;
	}

	/**
	 * @param solutions
	 *            Solution set to convert to a String
	 * @return A string representing the solution set.
	 */
	protected String solutionToString(Set<Set<String>> solutions) {
		Iterator<Set<String>> iterator = solutions.iterator();
		String out = "";
		while (iterator.hasNext()) {
			Iterator<String> iterator2 = iterator.next().iterator();
			while (iterator2.hasNext()) {
				out = out + " " + iterator2.next();
			}
			out = out + "\r\n";
		}
		return out;
	}

	@After
	public void tearDown() {
		super.tearDown();
	}

}
