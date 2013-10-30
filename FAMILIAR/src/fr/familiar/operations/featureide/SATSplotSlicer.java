package fr.familiar.operations.featureide;

import java.util.Collection;

import org.xtext.example.mydsl.fML.SliceMode;

import splar.plugins.reasoners.sat.sat4j.FMReasoningWithSAT;
import fr.familiar.interpreter.FMLShell;
import fr.familiar.operations.FMSliceReasoningWithSAT;
import fr.familiar.variable.FeatureModelVariable;

public class SATSplotSlicer extends FMSlicerSAT {

	public SATSplotSlicer() {
		
	}

	@Override
	public FeatureModelVariable sliceFM(FeatureModelVariable fmv, Collection<String> fts, SliceMode mode) {
		FMReasoningWithSAT reasoner = mkSATReasoningSlicer(fmv, fts, mode);
		FMLShell.getInstance().printTODO("Not yet implemented with SPLOT");
		// TODO: play with the reasoner
		return null;
	}

	public FMReasoningWithSAT mkSATReasoningSlicer(FeatureModelVariable fmv, Collection<String> fts,
			SliceMode mode) {
		FMReasoningWithSAT reasoner = getSPLOTSATReasoner(fmv, fts, mode);
		return reasoner;
	}

	private FMReasoningWithSAT getSPLOTSATReasoner(FeatureModelVariable fmv, Collection<String> fts,
			SliceMode mode) {
		// SAT reasoner construction parameters
		// - "MiniSAT" - name of the SAT4J solver used
		// - Timeout parameter
		int SATtimeout = (int) SATFormula.SAT_TIMEOUT ; 

		splar.core.fm.FeatureModel fmSplot = fmv.toSPLOT();

		FMReasoningWithSAT reasoner = new FMSliceReasoningWithSAT("MiniSAT",
				fmSplot, SATtimeout, fts, mode);

		// Initialize the reasoner
		try {
			reasoner.init();
		} catch (Exception e) {
			FMLShell.getInstance().printError(
					"Unable to load the SPLOT SAT reasoner " + e);
			return null;
		}

		return reasoner;
	}

}
