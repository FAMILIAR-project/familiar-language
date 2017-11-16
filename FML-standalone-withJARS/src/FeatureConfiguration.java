import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Sets;

import fr.familiar.variable.FeatureModelVariable;

public class FeatureConfiguration {

	private Set<String> _selectedFeatures;
	private FeatureModelVariable _fmv;
	

	public FeatureConfiguration(Set<String> cf, FeatureModelVariable fmv) {
		_selectedFeatures = cf; 	
		_fmv = fmv; 
	}

	public Set<String> getSelectedFeatures() {
		return _selectedFeatures;
	}
	
	public Set<String> getDeselectFeatures() {
		return Sets.difference(_fmv.features().names(), _selectedFeatures);
	}
	
	
	
	 public Map<String, Boolean> getConfMap() {

	        Map<String, Boolean> lConf = new HashMap<>();

	        Set<String> allFts = _fmv.features().names();
	        for (String ft : allFts) {
	            if (_selectedFeatures.contains(ft))
	                lConf.put(ft, true);
	            else
	                lConf.put(ft, false);
	            
	        }

	        return lConf;
	    }
}
