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

package fr.familiar.fm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;
import gsd.synthesis.BDDBuilder;
import gsd.synthesis.Formula;

public class FMLBDDWriter {

	private FeatureModelVariable _fmv;
	private BDDBuilder<String> _builder;

	private boolean _splot = false;

	public static final String _COMMA = ";";

	public static final String _BEGIN_BUILDER = "<BUILDER>";
	public static final String _END_BUILDER = "</BUILDER>";

	public static final String _BEGIN_BDD = "<BDD>";
	public static final String _END_BDD = "</BDD>";

	public static final String _BEGIN_FM = "<FM>";
	public static final String _END_FM = "</FM>";

	public static final String _BEGIN = "<FMLBDD>";
	public static final String _END = "</FMLBDD>";

	public static final String FML_BDD_EXTENSION = "fmlbdd";

	public static final String _NEWLINE = System.getProperty("line.separator");
	public static final String _ASSOCIATION = "=>";

	public FMLBDDWriter(FeatureModelVariable fmv, BDDBuilder<String> builder) {
		this(fmv, builder, false);
	}

	public FMLBDDWriter(FeatureModelVariable fmv, BDDBuilder<String> builder,
			boolean splot) {
		_fmv = fmv;
		_builder = builder;
		_splot = splot;
	}

	/**
	 * serialize to file whose filename is...
	 * 
	 * @param fileName
	 */
	public void serializeToFile(String fileName) {
		String content = serializeToString();
		try {
			FileSerializer.write(fileName, content);
		} catch (IOException e) {
			FMLShell.getInstance().printError(
					"Unable to serialize FML BDD " + e);
		}
	}
	
	public String serializeBDDOnlyToString() {

		Formula<String> fla = _fmv.getFormulaAsIs() ;
		if (fla == null) {
			if (_splot) {
				fla = _fmv.getSPLOTFormulaAligned(_builder);
			} else {
				fla = _fmv.getFormula();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(_BEGIN + _NEWLINE);

		sb.append(_BEGIN_BUILDER + _NEWLINE);

		Map<String, Integer> map = _builder.getFeatureMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Integer i = map.get(key);
			sb.append(i + "" + _ASSOCIATION + "" + key + "" + _COMMA + "");
		}
		sb.append(_NEWLINE);
		sb.append(_END_BUILDER + _NEWLINE);

		sb.append(_BEGIN_BDD + _NEWLINE);
		BDD bdd = fla.getBDD();
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		BDDBuilder<String> lbuilder = BDDBuilderFactory.mkBuilder(map);
		try {
			lbuilder.getFactory().save(bw, bdd);
			bw.flush();
		} catch (IOException e) {
			FMLShell.getInstance().printError(
					"Unable to serialize bdd -- FML BDD " + e);
		}

		sb.append(sw.getBuffer());
		sb.append(_NEWLINE);
		sb.append(_END_BDD + _NEWLINE);

		sb.append(_END + _NEWLINE);

		fla.free();
		return sb.toString();
	}

	public String serializeToString() {

		Formula<String> fla = _fmv.getFormulaAsIs() ;
		if (fla == null) {
			if (_splot) {
				fla = _fmv.getSPLOTFormulaAligned(_builder);
			} else {
				fla = _fmv.getFormula();
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(_BEGIN + _NEWLINE);

		sb.append(_BEGIN_FM + _NEWLINE);
		sb.append(_fmv.getSyntacticalRepresentation());
		sb.append(_NEWLINE);
		sb.append(_END_FM + _NEWLINE);

		sb.append(_BEGIN_BUILDER + _NEWLINE);

		Map<String, Integer> map = _builder.getFeatureMap();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			Integer i = map.get(key);
			sb.append(i + "" + _ASSOCIATION + "" + key + "" + _COMMA + "");
		}
		sb.append(_NEWLINE);
		sb.append(_END_BUILDER + _NEWLINE);

		sb.append(_BEGIN_BDD + _NEWLINE);
		BDD bdd = fla.getBDD();
		StringWriter sw = new StringWriter();
		BufferedWriter bw = new BufferedWriter(sw);
		BDDBuilder<String> lbuilder = BDDBuilderFactory.mkBuilder(map);
		try {
			lbuilder.getFactory().save(bw, bdd);
			bw.flush();
		} catch (IOException e) {
			FMLShell.getInstance().printError(
					"Unable to serialize bdd -- FML BDD " + e);
		}

		sb.append(sw.getBuffer());
		sb.append(_NEWLINE);
		sb.append(_END_BDD + _NEWLINE);

		sb.append(_END + _NEWLINE);

		fla.free();
		return sb.toString();
	}

}
