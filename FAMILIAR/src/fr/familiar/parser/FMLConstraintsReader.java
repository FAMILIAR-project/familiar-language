package fr.familiar.parser;

import fr.familiar.interpreter.FMLShell;
import gsd.synthesis.Expression;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class FMLConstraintsReader {

	public Collection<Expression<String>> parse(File file) {
		Collection<Expression<String>> clauses = new ArrayList<Expression<String>>();
		try {
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String line = null;
		while ((line = br.readLine()) != null) {
			clauses.addAll(MyExpressionParser.parseConstraints(line)); 
		}
		br.close() ;
		reader.close() ; 
		}
		catch (IOException e) {
			e.printStackTrace() ; 
			FMLShell.getInstance().printError("Unable to parse constraints in file " + e);
		}
		return clauses ; 
	}
	
	public Collection<Expression<String>> parse(String content) {
		return MyExpressionParser.parseConstraints(content) ; 
	}

	

}
