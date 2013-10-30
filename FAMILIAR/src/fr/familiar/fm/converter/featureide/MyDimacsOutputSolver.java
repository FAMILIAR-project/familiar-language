/**
 * 
 */
package fr.familiar.fm.converter.featureide;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.prop4j.Literal;
import org.prop4j.Node;
import org.prop4j.SatSolver;
import org.sat4j.tools.DimacsOutputSolver;

/**
 * @author macher
 *
 */
public class MyDimacsOutputSolver extends SatSolver {

	
	private ByteArrayOutputStream _sBuffer ; 
	
	private PrintWriter _pw ;
	
	private Map<String, Number> _var2IDS ; 
	
	/**
	 * @param node
	 * @param timeout
	 */
	public MyDimacsOutputSolver(Node node, long timeout) {
		super(node, timeout);
	}
	
	@Override
	protected void initSolver(Node node, long timeout) {
		_sBuffer = new ByteArrayOutputStream();
		_pw  = new PrintWriter(_sBuffer, true); // new PrintWriter(System.out, true) ; //
		solver = new DimacsOutputSolver(_pw) ; //SolverFactory.newDefault();
        solver.setTimeoutMs(timeout);
		node = node.toCNF();
    	solver.newVar(varToInt.size());
    	addClauses(node);
    	
    	populateVar2IDs(node) ; 
	}

	private void populateVar2IDs(Node n) {
		
		_var2IDS = new HashMap<String, Number>() ;
		Set<Literal> literals = collectLiterals(n);
		for (Literal no : literals) {
			int i = Math.abs(getIntOfLiteral(no));
			String s = no.var.toString() ;
			_var2IDS.put(s, i);
		}
		
		
	}
	
	/**
	 * @param node
	 * @return the set of literals of a given node TODO: duplicates? for
	 *         debugging
	 */
	private Set<Literal> collectLiterals(Node node) {
		Set<Literal> literals = new HashSet<Literal>();
		if (node instanceof Literal)
			literals.add((Literal) node);
		else {
			Node[] children = node.getChildren();
			for (int j = 0; j < children.length; j++) {
				literals.addAll(collectLiterals(children[j]));
			}

		}

		return literals;
	}

	public PrintWriter getPrintWriter() {
		return _pw ;
	}

	
	public OutputStream getOutputStream() {
		return _sBuffer;
	}
	
	public Map<String, Number> getVar2IDS() {
		return _var2IDS ; 
	}
	
	
}
