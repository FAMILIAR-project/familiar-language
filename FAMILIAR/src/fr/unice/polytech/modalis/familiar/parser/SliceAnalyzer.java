/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.SetCommand;
import org.xtext.example.mydsl.fML.Slice;
import org.xtext.example.mydsl.fML.SliceMode;

import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.FeatureVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.RefVariable;
import fr.unice.polytech.modalis.familiar.variable.SetVariable;
import fr.unice.polytech.modalis.familiar.variable.Variable;

/**
 * @author mathieuacher
 * 
 */
public class SliceAnalyzer extends FMLAbstractCommandAnalyzer {

	
	
	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public SliceAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public SliceAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#
	 * eval()
	 */
	@Override
	public void eval() {
		assert (_command instanceof Slice);
		Slice sliceCmd = (Slice) _command;

		FMCommand fmCmd = sliceCmd.getFm();
		FeatureModelVariable fmToSlice = _environment.parseFMCommand(fmCmd, null, null);

		SetCommand setCmd = sliceCmd.getFts();
		SetVariable setFts = _environment.parseSetCommand(setCmd, null);

		SliceMode sliceMode = sliceCmd.getMode();

		Set<String> fts = new HashSet<String>();
		Set<Variable> svars = setFts.getVars();
		for (Variable var : svars) {

			if (var instanceof RefVariable)
				var = ((RefVariable) var).getValueReference();

			if (!(var instanceof FeatureVariable)) {
				FMLShell.getInstance().printError(
						"var=" + var + " is not a feature in the set feature");
				return;
			}

			assert (var instanceof FeatureVariable);
			FeatureVariable ftv = (FeatureVariable) var;

			// TODO: check that ftv truly belongs to fmToSlice

			fts.add(ftv.getFtName());

		}

		fmToSlice.setBuilder(FMLCommandInterpreter.getBuilder()); 
		final FeatureModelVariable rFM = 
			fmToSlice.slice(sliceMode, fts) ; 
		assert (rFM != null) ;
		rFM.setIdentifier(_assigner) ;
		setVariable(rFM);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#
	 * getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE_MODEL;
	}



}
