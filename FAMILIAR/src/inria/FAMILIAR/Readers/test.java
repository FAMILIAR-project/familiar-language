package inria.FAMILIAR.Readers;

import es.us.isa.ChocoReasoner.attributed.ChocoReasoner;
import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedWriter;
import fr.unice.polytech.modalis.familiar.variable.AttributedFeatureModelVariable;
import inria.FAMILIAR.Model.AttributedFeatureModel;

public class test {
	static String vml2FilePath = "/Users/ealferez/git/VM/fr.inria.lang.vml2.example.MOTIV/VideoContent.vm";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VMReader myReader = new VMReader();
		try {
			AttributedFeatureModel model = myReader.parseFile(vml2FilePath);
			AttributedFeatureModelVariable var = new AttributedFeatureModelVariable(model);
			
			System.out.println(var.isValid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}