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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.xtext.example.mydsl.fml.Command;
import org.xtext.example.mydsl.fml.Map;
import org.xtext.example.mydsl.fml.SetCommand;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.variable.BooleanVariable;
import fr.familiar.variable.ConstraintVariable;
import fr.familiar.variable.FeatureModelVariable;
import fr.familiar.variable.RType;
import fr.familiar.variable.SetVariable;
import fr.familiar.variable.Variable;
import gsd.synthesis.Expression;
import gsd.synthesis.FeatureEdge;
import gsd.synthesis.FeatureModel;

/**
 * @author mathieuacher
 * 
 */
public class MapConstraint extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd2
	 * @param ns
	 * @param an
	 */
	public MapConstraint(Command cmd2, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd2, ns, an);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cmd2
	 * @param var
	 * @param ns
	 * @param an
	 */
	public MapConstraint(Command cmd2, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd2, var, ns, an);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#eval()
	 */
	@Override
	public void eval() {
		FMLShell.getInstance().printDebugMessage("map constraints to fm");
		assert (getCmd() instanceof Map);
		Map mapper = (Map) getCmd();

		FeatureModelVariable fmv = _environment.parseFMCommand(mapper.getFm(), null, null);

		String rootName = fmv.root().getFtName();

		SetCommand cstCmd = mapper.getCst();
		SetVariable cv = _environment.parseSetCommand(cstCmd,
				_assigner);
		
		
		List<Expression<String>> constraints = new ArrayList<Expression<String>>();
		Set<Variable> vars = cv.getVars() ;
		for (Variable var : vars) {
			if (!(var instanceof ConstraintVariable)) {
				FMLShell.getInstance().printError("variable is not of type constraint " + var);
				return ; 
			}
			else {
				ConstraintVariable cstVar = (ConstraintVariable) var ;
				constraints.add(cstVar.getConstraint());
			}
		}
		
		
		
		List<FeatureModelVariable> lfmv = new ArrayList<FeatureModelVariable>();
		lfmv.add(fmv);
		boolean isCorrect = true;
		for (Expression<String> constraint : constraints) {
			if (!fmv.addConstraint(constraint))
				isCorrect = false;
		}

		// reparses needed due to a bug with (ANTLR and) gsd.synthesis
		// reparses
		
		FeatureModel<String> resultingFM = fmv.getFm();
		resultingFM.getDiagram().addEdge(
				resultingFM.getDiagram().findVertex(rootName),
				resultingFM.getDiagram().getTopVertex(), FeatureEdge.MANDATORY);

		/*
		 * for (Expression<String> expr : resultingFM.getConstraints()) { if (!
		 * (resultingFM.addConstraint(expr)))
		 * System.err.println("(Map) constraint" + expr + " already exists"); }
		 */

		fmv = new FeatureModelVariable(getAssigner(), resultingFM);

		// for the moment
		// TODO: control the bind
		setVariable(new BooleanVariable(_assigner, isCorrect));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * modalis.polytech.unice.fr.familiar.parser.AbstractCommandAnalyzer#getType
	 * ()
	 */
	@Override
	public RType getType() {
		return RType.BOOLEAN;
	}

}
