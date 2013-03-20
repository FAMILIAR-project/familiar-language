package be.ac.fundp.info.TVLParser.Util;

//import java.util.Collection;
import java.util.List;

//import org.sat4j.specs.IConstr;

@SuppressWarnings("serial")
public class ContradictionException extends Exception {
	
	private final List<List<FDElement>> explanation ;
	


	public ContradictionException(String message,List<List<FDElement>> explanation) {
		super(message);
		this.explanation = explanation ;
	}
	
	public List<List<FDElement>> getExplanation() {
		return explanation;
	}
	
	
}
