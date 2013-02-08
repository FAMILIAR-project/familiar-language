/**
 * 
 */
package fr.unice.polytech.modalis.familiar.fm.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.unice.polytech.modalis.familiar.fm.FeatureNodeUtils;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import gsd.synthesis.Expression;
import gsd.synthesis.ExpressionType;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureGraph;
import gsd.synthesis.FeatureGraphFactory;
import gsd.synthesis.FeatureModel;
import gsd.synthesis.FeatureNode;

/**
 * @author mathieuacher
 * 
 */
public class FMLtoTVLConverter {

	private FeatureModelVariable _fmv;

	Map<String, Integer> ftToId = new HashMap<String, Integer>();

	

	/**
	 * increment for id generator
	 */
	private int _id = 0;

	/**
	 * the feature model to process internally (a clone/copy)
	 */
	private FeatureModel<String> _fm;

	public FMLtoTVLConverter(FeatureModelVariable fmv) {
		_fmv = fmv;
		_fm = fmv.getFm(); // FeatureModelParser.parseString(fmv.getFm().toString());
	}

	/**
	 * @return a string representation of the feature model in the SPLOT format
	 */
	public String toStringRepresentation() {
		return fMLtoTVL();
	}


	/**
	 * FML -> TVL
	 * 
	 * @return a string representation of the feature model in the TVL format
	 */
	private String fMLtoTVL() {

		StringBuilder sb = new StringBuilder() ; 
		

		// feature diagram (tree) 
		String fd = transformFM();
		sb.append(fd);
		
		return sb.toString() ; 

	}

	private Set<String> transformConstraints() {
		Set<String> cnfs = new HashSet<String>();

		Set<Expression<String>> constraints = _fm.getConstraints();
		// convert each expression to CNF expression
		for (Expression<String> expression : constraints) {
			cnfs.add(exprToString(expression));

		}

		return cnfs;
	}

	
	
	/**
	 * 
	 * TVL representation of the constraint
	 * @param cnf
	 * @return
	 */
	private String exprToString(Expression<String> cnf) {

		ExpressionType type = cnf.getType();
		switch (type) {
		case FEATURE:
			return cnf.getFeature();
		case TRUE:
			return _fmv.root().name();
		case FALSE:
			return "!" + _fmv.root().name();
		case NOT:
			return "!" + exprToString(cnf.getLeft());
		default:
			StringBuffer sb = new StringBuffer();
			sb.append(exprToString(cnf.getLeft()));
			sb.append(" " + typeValue(type) + " ");
			sb.append(exprToString(cnf.getRight()));
			return sb.toString();
		}

	}

	/**
	 *  
	 * @param type
	 * @return
	 */
	private String typeValue(ExpressionType type) {
		 if (type == ExpressionType.AND) 
			 return "&&" ;
		 else if (type == ExpressionType.OR)
			 return "||" ; 
		 else if (type == ExpressionType.IMPLIES)
			 return "->" ; 
		 else
			 return type.toString() ; 
	}

	/**
	 * Inspired from FeatureModelSerializer
	 * 
	 * @return a FML, string-based representation of a feature model
	 */
	private String transformFM() {

		// transform Mutex-groups in the feature model
		FeatureModelUtil.normalizeMultiGroups(_fm);
		
		final FeatureGraph<String> fg = _fm.getDiagram();

		// FMLShell.getInstance().printDebugMessage("After normalization (Mutex/splot):\nfm="
		// + _fm + "\nfg=" + fg);

		final StringBuilder sb = new StringBuilder();

		if (fg.isTop())
			return FeatureGraphFactory.DEFAULT_TOP_STRING;
		else if (fg.isBottom())
			return FeatureGraphFactory.DEFAULT_BOTTOM_STRING;

		// starting from the root
		String rootName = _fmv.root().getFtName();
		FeatureNode<String> rootNode = fg.findVertex(rootName);
		sb.append(processFeatureWithNesting(fg, rootNode, 0));

		return sb.toString();
	}
	
	public String processFeatureWithNesting(FeatureGraph<String> fg, FeatureNode<String> v, int nesting) {
			
		
		String sb = "" ;
			if (fg.children(v).size() == 0) return sb ;
			boolean rootTime = false ; 
			if (FeatureVariable.isRoot(v, _fmv)) {
				rootTime = true ; 
				sb = "root " ; 
			}
						
			//nesting: sb += (" " * nesting) + v + " {\n"
			sb += v + " {\n" ;
			
					
			int spacesRequired = sb.length();
			
			
			List<String> groups = new ArrayList<String>() ; 
			
			//*** Xor/Or groups ***//
			for (FeatureEdge fe : fg.incomingEdges(v)) {
				Set<FeatureNode<String>> sources = fg.getSources(fe);
			 
	           if (sources.size() != 1 ) {
          	   
	          	    String kinfOfGroup = "group " ;
	          	    
	                int type = fe.getType() ;
	                
	                if (type == FeatureEdge.MUTEX)
		        	   	kinfOfGroup += "[0..1]" ; 
	                else if (type == FeatureEdge.OR) {
		                kinfOfGroup += "someOf" ; 
	                }
	                else if (type == FeatureEdge.XOR) {
		                kinfOfGroup += "oneOf" ; 
	                }
	                else
		                kinfOfGroup += "UNKOWN" ; // TODO
		           
	                
	               List<String> strSources = new ArrayList<String>() ;
	               for (FeatureNode<String>	source : sources) {
					
	            	   String s = source.getFeature() ; 
	            	   strSources.add(s);
	               }
	               
	          	   groups.add( 
	          		   processGroup (kinfOfGroup, 
	          				   strSources, 
	          				   spacesRequired));
	           	   
	           }
	                     
			}
	
			//*** And groups ***//
			// collect features that belong to And-group 
			Set<FeatureNode<String>> andSources = new HashSet<FeatureNode<String>>(); 
			
			for (FeatureEdge fe : fg.incomingEdges(v)) {
				Set<FeatureNode<String>> sources = fg.getSources(fe);
				for (FeatureNode<String> s : sources) {
					if(! (FeatureNodeUtils.isInOR(fg, s) || FeatureNodeUtils.isInXOR(fg, s)
							|| FeatureNodeUtils.isInMTX(fg, s)))
						andSources.add(s);
				}
				
			}
			
		
					
			// And-groups content
			if(andSources.size() > 0)  {
				String kinfOfGroup = "group allOf" ; 
				List<String> gr = new ArrayList<String>() ;
				for (FeatureNode<String> c : andSources) {
					if (FeatureNodeUtils.isOptional(fg, c)) 
						gr.add("opt " + ftName(c)) ;
					else if (FeatureNodeUtils.isMandatory(fg, c)) 
						gr.add(ftName(c));
					else {
						gr.add("");
						FMLShell.getInstance().printError("Unable to convert to TVL coz " + c);
					}
					
				}
				
			    groups.add(processGroup (kinfOfGroup, gr, spacesRequired));
			           
			}
			
			// TODO separate groups with a comma
			sb += formatGroups(groups, "", ",\n", "\n") ;

			
			if (rootTime) { // say we're writing constraints immediatly after root declaration
				Set<String> cnfs = transformConstraints();
				for (String cnf : cnfs) {
					//
					sb += cnf + " ; \n";
				}
				rootTime = false ; 
			}
			
		
			sb +=  mkSpaces(spacesRequired - 2) + "} \n" ;
			
			for (FeatureNode<String> child : fg.children(v))  // process recursively
				sb += processFeatureWithNesting(fg, child, nesting + spacesRequired) ;
			
			
			
			return sb ; 
		}
	
	private String formatGroups(List<String> groups, String beginSep, String inSep, String endSep) {
		StringBuilder sb = new StringBuilder(); 
		sb.append(beginSep);
		int n = groups.size() ;
		int i = 0 ; 
		for (String gr : groups) {
			sb.append(gr);
			if (i++ < n - 1)
				sb.append(inSep);
			else
				sb.append(endSep);
		}
		
		return sb.toString() ;
	}

	private String processGroup (String kinfOfGroup, List<String> sources, int spacesRequired) {
		   
		String s = "" ; 
		  	   
		// kind of group 	
		String kindOf =  mkSpaces(spacesRequired - 2) + kinfOfGroup + " {\n" ;
        s += kindOf ;
        
        // group members
        int spacesRequiredInsideGroup = kindOf.length() ; 
        String strSources = "" ; 
        for (String source : sources) {
			strSources += source ; 
		}
        String groupContents = formatGroups(sources, 
        			"" + mkSpaces(spacesRequiredInsideGroup),
        			",\n" + mkSpaces(spacesRequiredInsideGroup),
        			"\n");  
        s += groupContents ;
        
        
     	// close bracket
        s += mkSpaces(spacesRequiredInsideGroup -2) + "}" ; 
        
        return s ; 
	}
	
		
		


	private String mkSpaces(int spacesRequired) {
		String spaces = ""; 
		for (int i = 0; i < spacesRequired ; i++) {
			spaces += " " ;
		}
		return spaces ; 
	}

	

	private String ftName(FeatureNode<String> v) {
		return v.getFeature();
	}

	private String featureID(String ftName) {
		if (!ftToId.containsKey(ftName))
			ftToId.put(ftName, _id++); // TODO: unique id
		return "_r" + ftToId.get(ftName).toString();
	}

	private String featureID(FeatureNode<String> v) {
		return featureID(v.getFeature());
	}

	

	}
