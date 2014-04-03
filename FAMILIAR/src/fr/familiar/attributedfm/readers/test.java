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
