package inria.FAMILIAR.Readers;

import es.us.isa.FAMA.models.FAMAAttributedfeatureModel.fileformats.AttributedWriter;
import inria.FAMILIAR.Model.AttributedFeatureModel;

public class test {
	static String vml2FilePath = "/Users/ealferez/git/VM/fr.inria.lang.vml2.example.MOTIV/VideoContent.vm";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VMReader myReader = new VMReader();
		try {
			AttributedFeatureModel model = myReader.parseFile(vml2FilePath);
			AttributedWriter writer = new AttributedWriter();
			writer.writeFile("./fama.txt", model);
			System.out.println(model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}