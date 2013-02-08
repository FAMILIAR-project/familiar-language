/**
 * 
 */
package fr.unice.polytech.modalis.familiar.parser;

import org.xtext.example.mydsl.fML.CTCRCommand;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.FMCommand;

import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;

/**
 * @author mathieuacher
 * 
 */
public class CTCRAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public CTCRAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public CTCRAnalyzer(Command cmd, String assigner, NameSpace ns,
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

		assert (_command instanceof CTCRCommand);
		CTCRCommand ctcrCmd = (CTCRCommand) _command;

		FMCommand fmCmd = ctcrCmd.getFm();
		FeatureModelVariable fmv = _environment.parseFMCommand(fmCmd, null, null);

		setVariable(new DoubleVariable(_assigner, fmv.CTCR()));

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
		return RType.DOUBLE;
	}

}
