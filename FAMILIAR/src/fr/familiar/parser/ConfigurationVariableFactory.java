package fr.familiar.parser;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.ConfigurationVariable;
import fr.familiar.variable.ConfigurationVariableSPLOTImpl;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.featureide.ConfigurationVariableFeatureIDEImpl;

public class ConfigurationVariableFactory {
	
	public static ConfigurationVariableFactory INSTANCE = new ConfigurationVariableFactory() ;
	
	// TODO
	private static VPConfigurationVariable _DEFAULT_VP_CF = VPConfigurationVariable.FEATUREIDE ; // BDD ; 
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
