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

package fr.familiar.parser;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConfigurationVariableSPLOTImpl;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.ConfigurationVariableFeatureIDEImpl;

public class ConfigurationVariableFactory {
	
	public static ConfigurationVariableFactory INSTANCE = new ConfigurationVariableFactory() ;
	
	// TODO
	private static VPConfigurationVariable _DEFAULT_VP_CF = VPConfigurationVariable.BDD ; // BDD ; 
	private VPConfigurationVariable _vpCf = _DEFAULT_VP_CF ; 
	
	
	private ConfigurationVariableFactory() {
			
	}

	public ConfigurationVariable mkFeatureIDE(FeatureModelVariable fmv, String assigner) {
		// FIXME @FeatureIDE 
		return new ConfigurationVariableFeatureIDEImpl(fmv, assigner);
	}

	public ConfigurationVariable mkSPLOT(FeatureModelVariable fmv, String assigner) {
		return new ConfigurationVariableSPLOTImpl(fmv, assigner);
	}

	public ConfigurationVariable mkDefault(FeatureModelVariable fmv, String assigner) {
		// FIXME
		_vpCf = _DEFAULT_VP_CF ; 
		if (_vpCf.equals(VPConfigurationVariable.SPLOT))
			return ConfigurationVariableFactory.INSTANCE.mkSPLOT(fmv, assigner);
		else if (_vpCf.equals(VPConfigurationVariable.FEATUREIDE))
			return ConfigurationVariableFactory.INSTANCE.mkFeatureIDE(fmv, assigner);
		else if (_vpCf.equals(VPConfigurationVariable.BDD))
			return ConfigurationVariableFactory.INSTANCE.mkBDD(fmv, assigner);
		else {
			FMLShell.getInstance().printError("Unsupported vpCf=" + _vpCf);
			return null ; 
		}
	}

	public ConfigurationVariable mkBDD(FeatureModelVariable fmv, String assigner) {
		return new ConfigurationVariableBDDImpl(fmv, assigner);
	}


}
