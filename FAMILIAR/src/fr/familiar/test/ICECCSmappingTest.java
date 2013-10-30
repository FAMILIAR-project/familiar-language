/**
 * 
 */
package fr.familiar.test;

import fr.familiar.interpreter.FMLShell;
import fr.familiar.parser.FMLCommandInterpreter;
import fr.familiar.variable.FeatureModelVariable;

/**
 * @author mathieuacher
 * 
 */
public class ICECCSmappingTest extends FMLTest {

	private String _VSARid = "vsarSpecialization";

	private String _PCFid = "pcf";

	private String _glFMid = "glFM"; // global name FM

	public ICECCSmappingTest(FMLShell shell, String vSARspecification,
			String pCFspecification, String prules) throws Exception {
		_shell = shell;
		_environment = _shell.getCurrentEnv();
		_builder = FMLCommandInterpreter.getBuilder();
		map(vSARspecification, pCFspecification, prules);

	}

	private void map(String VSARspecification, String PCFspecification,
			String prules) throws Exception {
		// after specialization

		String VSARspecialization = _VSARid + " = " + VSARspecification + "\n";
		_shell.parse(VSARspecialization);
		String PCFfm = _PCFid + " = " + PCFspecification + "\n";
		_shell.parse(PCFfm);
		_shell.parse(prules);
		_shell.parse(_glFMid + " = aggregate { " + _VSARid
				+ " pcf } withMapping prules\n");
		// _shell.parse("VSARprime = slice " + _glFMid + " including " + _VSARid
		// + ".*\n");

	}

	public FeatureModelVariable getVSAR() throws Exception {
		return getFMVariable(_VSARid);
	}

	public FeatureModelVariable getGl() throws Exception {
		return getFMVariable(_glFMid);
	}

}
