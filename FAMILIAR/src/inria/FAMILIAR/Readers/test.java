package inria.FAMILIAR.Readers;

import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedWriter;
import fr.unice.polytech.modalis.familiar.variable.AttributedFeatureModelVariable;
import inria.FAMILIAR.Model.AttributedFeatureModel;

public class test {
	static String vml2FilePath = "/Users/ealferez/git/VM/fr.inria.lang.vm.examples.MOTIV/VideoContent.vm";

	public static void main(String[] args) {
	
		VMReader myReader = new VMReader();
		try {
			AttributedFeatureModel model = myReader.parseFile(vml2FilePath);

			AttributedFeatureModelVariable var = new AttributedFeatureModelVariable(model);
			
			System.out.println(var.isValid());

			AttributedWriter writer = new AttributedWriter();
			writer.writeFile("./fama.txt", model);
			//System.out.println(model);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
