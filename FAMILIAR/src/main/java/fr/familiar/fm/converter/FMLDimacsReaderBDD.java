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

package fr.familiar.fm.converter;

import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.StringTokenizer;



public class FMLDimacsReaderBDD extends FMDimacsReader {
	
	public Collection<Expression<String>> parseExpressions(String dimacs) {
		String[] lines = dimacs.split(System.getProperty("line.separator")) ;
		Collection<Expression<String>> disjClauses = new HashSet<Expression<String>>();
		for (String line : lines) {
			treatExpression(line, disjClauses) ;
		}		
		return disjClauses ; 
	}

	public Collection<Expression<String>> parseExpressions(File dimacsFile) throws IOException {
		FileReader reader = new FileReader(dimacsFile);
		BufferedReader br = new BufferedReader(reader);
		Collection<Expression<String>> disjClauses = new HashSet<Expression<String>>();
		String line = null;
	
		while ((line = br.readLine()) != null) {
			treatExpression(line, disjClauses);
		}
		br.close() ;
		reader.close() ; 
		return disjClauses ;
	}
	
	private void treatExpression(String line,
			Collection<Expression<String>> disjClauses) {
		
		if (line.startsWith("c")) {
			int id = extractNumber(line);
			String varName = extractVarName(line, ("" + id).length());
			_var2IDs.put(id, varName);
			if (line.contains("$")) {
				_fakes.add(varName);
				
			}
			return ;
		}
		if (line.startsWith("p")) {
			return ;
		}

		// otherwise treat the clause
		Expression<String> e = clauseToExpression(line);
		if (e != null)
			disjClauses.add(e);

		
	}
	
	private Expression<String> clauseToExpression(String line) {
		if (line == null || line.isEmpty())
			return null;
		
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		if (!tokenizer.hasMoreElements())
			return null ; 
			
		Expression<String> r = treatToken(tokenizer.nextToken());
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			Expression<String> e = treatToken (token) ;
			if (e != null)
				r = r.or(e);
		}
		return r ;	
}
	
	private Expression<String> treatToken(String token) {

		if (token.startsWith("-")) {
			String varID = token.substring(1);
			int id = Integer.parseInt(varID);
			String varName = _var2IDs.get(id);
			return new Expression<String>(ExpressionType.NOT, varName.trim(), null) ;
		} else {
			int id = Integer.parseInt(token);
			if (id == 0)
				return null;
			String varName = _var2IDs.get(id);
			return new Expression<String> (varName.trim()) ;
		}
	}

}
