/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for 
 * manIpulation and Automatic Reasoning) project (2010-2017)
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
 * along with FAMILIAR.  If not, see <http://www.gnu.org/licenses/>
 */

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
