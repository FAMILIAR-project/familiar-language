/**
 * 
 */
package fr.familiar.parser;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.Hierarchy;

import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import gsd.synthesis.FeatureModel;

/**
 * @author macher1
 *
 */
public class HierarchyAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public HierarchyAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public HierarchyAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		Hierarchy hCmd = (Hierarchy) _command ; 
		
		FMCommand fmCmd = hCmd.getFm() ;
		FeatureModelVariable hFmv = _environment.parseFMCommand(fmCmd, null, null) ;
		
		FeatureModel<String> hierarchy = hFmv.getHierarchy() ; 
		FeatureModelVariable rFmv = new FeatureModelVariable ("", hierarchy) ;
		setVariable (rFmv);
		

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE_MODEL ;
	}

}
