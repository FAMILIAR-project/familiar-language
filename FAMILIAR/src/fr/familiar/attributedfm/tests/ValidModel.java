package fr.familiar.attributedfm.tests;

import fr.familiar.parser.AttributedFeatureModelVariable;

public class ValidModel {
	public static void main(String[] args) {
		AttributedFeatureModelVariable facade = new AttributedFeatureModelVariable();
		facade.readModelFAMAFormat("/Users/malawito/Dropbox/Documentos/Workspaces/genetic/SimpleGenetic/James.afm");
		System.out.println(facade.isValid());;
	}
}
