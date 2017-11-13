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

import java.util.HashSet;
import java.util.Set;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.FMCommand;
import org.xtext.example.mydsl.fml.SetCommand;
import org.xtext.example.mydsl.fml.Slice;
import org.xtext.example.mydsl.fml.SliceMode;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.FeatureVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;

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
