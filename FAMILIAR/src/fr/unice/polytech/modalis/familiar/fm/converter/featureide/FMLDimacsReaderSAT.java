package fr.unice.polytech.modalis.familiar.fm.converter.featureide;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.prop4j.And;
import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.Or;

import fr.unice.polytech.modalis.familiar.fm.converter.FMDimacsReader;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;

/**
 * @author macher
 *
 */
public class FMLDimacsReaderSAT extends FMDimacsReader {
	



	public List<Node> parseDisjNodes (File file) throws IOException {
		
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		List<Node> disjClauses = new ArrayList<Node>();
		String line = null;
	
		while ((line = br.readLine()) != null) {
			treatLine(line, disjClauses);
		}
		br.close() ;
		reader.close() ; 
		return disjClauses ;

		
	}
	
	private void treatLine(String line, List<Node> disjClauses) {
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
		Node o = treatClause(line);
		if (o != null)
			disjClauses.add(o);
		
	}

	private Node treatClause(String line) {
		if (line == null || line.isEmpty())
			return null;
		List<Literal> vars = new ArrayList<Literal>();
		StringTokenizer tokenizer = new StringTokenizer(line, " ");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			Literal l = treatVariable(token);
			if (l != null)
				vars.add(l);
		}

		return new Or(vars);
	}
	
	
	private Literal treatVariable(String token) {

		if (token.startsWith("-")) {
			String varID = token.substring(1);
			int id = Integer.parseInt(varID);
			String varName = _var2IDs.get(id);
			return new Literal(varName.trim(), false);
		} else {
			int id = Integer.parseInt(token);
			if (id == 0)
				return null;
			String varName = _var2IDs.get(id);
			return new Literal(varName.trim(), true);
		}
	}


	

	private List<Node> parseDisjNodes(String dimacs) {
		String[] lines = dimacs.split(System.getProperty("line.separator")) ;
		List<Node> disjClauses = new ArrayList<Node>();
		for (String line : lines) {
			treatLine(line, disjClauses) ;
		}		
		return disjClauses ; 
	}

	

	public SATFMLFormula parseSATFMLFormula(String dimacs) {
		List<Node> nodes = parseDisjNodes(dimacs) ;
		return new SATFMLFormula(new And(nodes)); 
	}

	public SATFMLFormula parseSATFMLFormula(File dimacs) {
		List<Node> nodes;
		try {
			nodes = parseDisjNodes(dimacs);
			SATFMLFormula fla = new SATFMLFormula(new And(nodes));			
			return fla ;
		} catch (IOException e) {
			e.printStackTrace();
			FMLShell.getInstance().printError("Unable to parse DIMACS file " + e);
			return null ; 
		}
	}

}
