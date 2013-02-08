package fr.unice.polytech.modalis.familiar.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import net.sf.javabdd.BDD;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xtext.example.mydsl.fML.FMFormat;

import fr.unice.polytech.modalis.familiar.fm.FMLBDDReader;
import fr.unice.polytech.modalis.familiar.fm.FMLBDDWriter;
import fr.unice.polytech.modalis.familiar.variable.FeatureModelVariable;
import fr.unice.polytech.modalis.utils.FileSerializer;

@RunWith(Parameterized.class)
public class FMLBDDSerializerTest extends FMLTest {

	private String _fmSpecification;
	private String _fmID;

	public FMLBDDSerializerTest(String fmSpecification, String fmID) {
		_fmSpecification = fmSpecification;
		_fmID = fmID;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
				{ "FM (A : B [C] [D]; C : E F; F -> G; )", "fm1" },
				{ FMLTest.FM_LAPTOP, "fmLaptop" },

		});
	}

	@Test
	public void testWriter() throws Exception {

		final String FILENAME = "output/" + _fmID + "."
				+ FMLBDDWriter.FML_BDD_EXTENSION;

		FeatureModelVariable fmv1 = FM(_fmID, _fmSpecification);
		FileSerializer.write(FILENAME, fmv1.convert(FMFormat.FMLBDD));

		FMLBDDReader reader = new FMLBDDReader(FILENAME);
		/*
		 * String bddRepresentation = reader.getBDDRepresentation(); String
		 * builderRepresentation = reader.getBuilderRepresentation();
		 */

		Map<String, Integer> map = reader.getMapBuilder();
		assertTrue(map.equals(_builder.getFeatureMap()));

		BDD bdd = reader.getBDD();
		assertTrue(fmv1.getFormula().getBDD().equals(bdd));
		// new AllConfigsBDD(lbuilder).process(fla)

	}

}
