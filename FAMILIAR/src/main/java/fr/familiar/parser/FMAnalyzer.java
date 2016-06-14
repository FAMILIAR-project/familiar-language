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

package fr.familiar.parser;

import org.xtext.example.mydsl.fml.Aggregate;
import org.xtext.example.mydsl.fml.AggregateMerge;
import org.xtext.example.mydsl.fml.AsFM;
import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.CopyVariable;
import org.xtext.example.mydsl.fml.Extract;
import org.xtext.example.mydsl.fml.Hierarchy;
import org.xtext.example.mydsl.fml.IdentifierExpr;
import org.xtext.example.mydsl.fml.Merge;
import org.xtext.example.mydsl.fml.Slice;
import org.xtext.example.mydsl.fml.Synthesis;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.RefVariable;
import fr.familiar.variable.Variable;

/**
 * @author macher1
 *
 */
public class FMAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public FMAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd
	 * @param assigner
	 * @param ns
	 * @param an
	 */
	public FMAnalyzer(Command cmd, String assigner, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, assigner, ns, an);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		FMLAbstractCommandAnalyzer pars = null;
		Command cmd = _command ; 
		String varID = _assigner ; 
		if (cmd instanceof IdentifierExpr) {
			Variable v = _environment.parse((Command) cmd, varID);
			if (v instanceof RefVariable)
				v = ((RefVariable) v).getValueReference();
			if (!(v instanceof FeatureModelVariable)) {
				FMLShell.getInstance().setError(
						"Unable to parse the FM command (v=" + v + ")");
				return ;
			}
			setVariable((FeatureModelVariable) v);
			return ; 
		}

		else if (cmd instanceof CopyVariable) {
			Variable v = _environment.parse((Command) cmd, varID);
			if (v instanceof RefVariable)
				v = ((RefVariable) v).getValueReference();
			if (!(v instanceof FeatureModelVariable)) {
				FMLShell.getInstance().setError(
						"Unable to parse the FM command");
				return ;
			}
			setVariable((FeatureModelVariable) v);
			return ; 
		}

		else if (cmd instanceof org.xtext.example.mydsl.fml.FeatureModel) {

			pars = new FMBuilder((Command) cmd, varID, ns, _environment);
			pars.parse();

		}

		else if (cmd instanceof Slice) {

			pars = new SliceAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();
		}

		// composition (merge)
		else if (cmd instanceof Merge) {
			pars = new MergeAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();
		}
		
		// composition (aggregateMerge)
		else if (cmd instanceof AggregateMerge) {
			pars = new MergeAggregateAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();
		}
		
		// composition (aggregateMerge)
		else if (cmd instanceof Hierarchy) {
					pars = new HierarchyAnalyzer((Command) cmd, varID, ns, _environment);
					pars.parse();
		}
		
		// ksynthesis
		else if (cmd instanceof Synthesis) {
			pars = new KSynthesisAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();
		}

		else if (cmd instanceof AsFM) {
			pars = new AsFMAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();

		}

		// extract
		else if (cmd instanceof Extract) {
			pars = new ExtractAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();

		}

		else if (cmd instanceof Aggregate) {
			pars = new AggregatorAnalyzer((Command) cmd, varID, ns, _environment);
			pars.parse();

		}

		else {
			FMLShell.getInstance().printTODO("unknown FMCommand");
			return ;
		}
		
		// FIXME: RefVariable?

		Variable v = pars.getVariable();
		if (!(v instanceof FeatureModelVariable)) {
			FMLShell.getInstance().setError(
					"Unable to parse the FM command (v=" + v + ")");
			return ;
		}

		FeatureModelVariable fmv = (FeatureModelVariable) v;
		setVariable(fmv);

	}

	/* (non-Javadoc)
	 * @see fr.unice.polytech.modalis.familiar.parser.FMLAbstractCommandAnalyzer#getType()
	 */
	@Override
	public RType getType() {
		return RType.FEATURE_MODEL  ;
	}

}
