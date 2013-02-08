/*
 * This file is part of the FAMILIAR (for FeAture Model scrIpt Language for manIpulation and Automatic Reasoning) project (https://nyx.unice.fr/projects/familiar/).
 *
 * Copyright (C) 2010 University of Nice Sophia Antipolis, UMR CNRS 6070, I3S Laboratory
 *
 * FAMILIAR is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * FAMILIAR is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FAMILIAR.  (See file COPYING)  If not, see
 * <http://www.gnu.org/licenses/>.
 */
package fr.unice.polytech.modalis.familiar.parser;

import org.eclipse.emf.ecore.EObject;
import org.xtext.example.mydsl.fML.Command;
import org.xtext.example.mydsl.fML.ConfigurationCommand;
import org.xtext.example.mydsl.fML.Convert;
import org.xtext.example.mydsl.fML.FMCommand;
import org.xtext.example.mydsl.fML.FMFormat;
import org.xtext.example.mydsl.fML.FeatureModel;

import fr.unice.polytech.modalis.familiar.fm.FMLBDDWriter;
import fr.unice.polytech.modalis.familiar.fm.FeatureModelPrinter;
import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModelReader;
import fr.unice.polytech.modalis.familiar.fm.basic.FMLFeatureModelWriter;
import fr.unice.polytech.modalis.familiar.fm.converter.FMLtoTVLConverter;
import fr.unice.polytech.modalis.familiar.fm.converter.S2T2Converter;
import fr.unice.polytech.modalis.familiar.fm.converter.SPLOTConverter;
import fr.unice.polytech.modalis.familiar.fm.featureide.FMLtoFeatureIDE;
import fr.unice.polytech.modalis.familiar.interpreter.BDDStrategy;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.operations.featureide.SATFMLFormula;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.familiar.variable.RType;
import fr.unice.polytech.modalis.familiar.variable.StringVariable;
import gsd.synthesis.FeatureGraphFactory;

/**
 * @author mathieuacher
 * 
 *         convert a feature model in different formats TODO: should be tested!
 */
public class ConvertAnalyzer extends FMLAbstractCommandAnalyzer {

	/**
	 * @param cmd
	 * @param ns
	 * @param an
	 */
	public ConvertAnalyzer(Command cmd, NameSpace ns, FMLCommandInterpreter an) {
		super(cmd, ns, an);

	}

	/**
	 * @param cmd
	 * @param var
	 * @param ns
	 * @param an
	 */
	public ConvertAnalyzer(Command cmd, String var, NameSpace ns,
			FMLCommandInterpreter an) {
		super(cmd, var, ns, an);

	}

	@Override
	public RType getType() {
		return RType.STRING; // TODO: can be a string
	}

	@Override
	public void eval() {
		assert (_command instanceof Convert);
		Convert convertCmd = (Convert) _command;

		EObject eo = convertCmd.getV();

		if (eo instanceof FMCommand) {
			FMCommand fmToConvert = (FMCommand) eo;

			FMLShell.getInstance().printDebugMessage(
					"evaluating FM to convert: " + fmToConvert);

			FeatureModelVariable fmv = _environment.parseFMCommand(fmToConvert, null, null);
			FMFormat format = convertCmd.getFormat();
			String convertion = convert(fmv, format);
			FMLShell.getInstance()
					.printDebugMessage("convertion=" + convertion);

			StringVariable sv = new StringVariable(getAssigner(), convertion);
			setVariable(sv);
		} else if (eo instanceof ConfigurationCommand) {
			FMLShell.getInstance().printTODO(
					"currently unable to serialize configurations like " + eo);
		} else {
			FMLShell.getInstance().setError(
					"unexpected error: feature models or configurations expected but "
							+ eo);
		}
	}

	public static String convert(FeatureModelVariable fmv, FMFormat format) {
		String convertion = null;

		if (format == FMFormat.FIDE) {
			// FIXME @FeatureIDE 
			convertion = new FMLtoFeatureIDE(fmv).convertToString(); 
		}

		else if (format == FMFormat.FTRISKELL) {
			FMLShell.getInstance().printTODO("Triskell format not supported");
			return null;
		}

		else if (format == FMFormat.FCALC) {
			// SCALA: convertion = FMConverter.convertToFMCalc(fmw.getFm());
			String strfm = new FeatureModelPrinter(fmv).toString();
			strfm = strfm.replace("[", "");
			strfm = strfm.replace("]", "?");
			return strfm;
		}

		else if (format == FMFormat.FTVL) {
			// SCALA: convertion = FMConverter.convertToTVL(fmw.getFm());
			//FMLShell.getInstance().printTODO("supported in Scala version");
			convertion = new FMLtoTVLConverter(fmv).toStringRepresentation();
			
		}

		else if (format == FMFormat.FFML) {

			String strfm = new FMLFeatureModelPrinter<String>(
					FeatureGraphFactory.mkStringFactory(), false).toString(fmv
					.getFm());
			FeatureModel fm = new FMLFeatureModelReader().parseString("FM ("
					+ strfm + ")");
			convertion = new FMLFeatureModelWriter(fm).toString();

		}

		else if (format == FMFormat.FFML2) { // XMI FIXME

			String strfm = new FMLFeatureModelPrinter<String>(
					FeatureGraphFactory.mkStringFactory(), false).toString(fmv
					.getFm());
			FeatureModel fm = new FMLFeatureModelReader().parseString("FM ("
					+ strfm + ")");
			convertion = new FMLFeatureModelWriter(fm).toStringXMI();

		}

		else if (format == FMFormat.FSPLOT) {
			convertion = new SPLOTConverter(fmv).toStringRepresentation();
		}

		else if (format == FMFormat.FMLBDD) {
			boolean splot = FMLShell.getInstance().getBDDStrategy() == BDDStrategy.SPLOT;
			convertion = new FMLBDDWriter(fmv,
					FMLCommandInterpreter.getBuilder(), splot)
					.serializeToString();
		}
		
		else if (format == FMFormat.DIMACS) {
			// FIXME @FeatureIDE 
			convertion =  new SATFMLFormula(fmv).toDIMACS() ; 			
		}

		else if (format == FMFormat.S2T2) {
			// FIXME
			convertion = new S2T2Converter
			(FMLShell.getInstance().isStandalone()).fmlToS2T2XMI(fmv);
		}
	

		else {

			FMLShell.getInstance().printTODO("Unsupported format");
			return null;

		}

		return convertion;

	}

}
