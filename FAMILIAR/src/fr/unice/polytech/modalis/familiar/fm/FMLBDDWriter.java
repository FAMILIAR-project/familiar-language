package fr.unice.polytech.modalis.familiar.fm;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import net.sf.javabdd.BDD;
import fr.unice.polytech.modalis.familiar.interpreter.FMLShell;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.utils.FileSerializer;
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

	public String serializeToString() {

		Formula<String> fla = null;
		if (_splot) {
			fla = _fmv.getSPLOTFormulaAligned(_builder);
		} else {
			fla = _fmv.getFormula();
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
