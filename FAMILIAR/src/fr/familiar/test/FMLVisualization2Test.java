package fr.familiar.test;

import org.junit.Test;

import fr.familiar.utils.FileSerializer;
import fr.familiar.variable.FeatureModelVariable;

public class FMLVisualization2Test extends FMLTest {

	@Test
	public void testProtovisVS() throws Exception {

		_shell.parse("run \"deployment.fml\"" + "\n");
		FeatureModelVariable VSAR = getFMVariable("requirement");
		FeatureModelVariable platform = getFMVariable("platform");
		FileSerializer.write("output/" + VSAR.getIdentifier() + ".html",
				VSAR.toProtovis());
		FileSerializer.write("output/" + platform.getIdentifier() + ".html",
				platform.toProtovis());

		_shell.parse("aggVS = aggregate { requirement platform } withMapping TRANSrules");
		FeatureModelVariable aggVS = getFMVariable("aggVS");
		FileSerializer.write("output/" + aggVS.getIdentifier() + ".html",
				aggVS.toProtovis());

	}

}