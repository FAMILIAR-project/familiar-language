package inria.FAMILIAR.Readers;

import inria.FAMILIAR.Model.AttributedFeatureModel;
import inria.FAMILIAR.Model.Feature;
import inria.FAMILIAR.Model.Relation;
import es.us.isa.FAMA.models.variabilityModel.parsers.IReader;

public class VMReader implements IReader {

	@Override
	public AttributedFeatureModel parseFile(String fileName) throws Exception {
		AttributedFeatureModel res = new AttributedFeatureModel();
		
		//for(){
			Feature feat = new Feature("");
			
			Relation rel = new Relation();
			feat.addRelation(rel);
			rel.addDestination(new Feature());
		//}
		
		return null;
	}

	@Override
	public AttributedFeatureModel parseString(String data) throws Exception {
		//Not implemented currently. Gonna force to read from file.
		return null;
	}

	@Override
	public boolean canParse(String fileName) {
		// TODO Auto-generated method stub
		return true;
	}

}
