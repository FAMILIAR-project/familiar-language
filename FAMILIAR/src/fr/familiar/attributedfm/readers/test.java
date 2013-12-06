package fr.familiar.attributedfm.readers;

import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedWriter;
import fr.familiar.attributedfm.AttributedFeatureModel;
import fr.familiar.parser.AttributedFeatureModelVariable;

public class test {
	static String vml2FilePath = "/Users/malawito/Dropbox/Documentos/Workspaces/genetic/SimpleGenetic/VideoContent_02.vm" ; 
			// "/Users/ealferez/git/VM/fr.inria.lang.vm.examples.MOTIV/VideoContent.vm";

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
