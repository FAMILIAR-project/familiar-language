package gsd.synthesis;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import fr.familiar.variable.FeatureModelVariable;

@RunWith(value = Parameterized.class)
public class FeatureModelTreeTest extends FMLTest{

	protected String _fm;
	protected FeatureModelTree tree;

	public FeatureModelTreeTest(String fm) {
		_fm = fm;
	}

	@Parameters
	public static List<String[]> data() {
		String[][] data = new String[][] {
				 { FMLTest.FM_LAPTOP }};
		return Arrays.asList(data);
	}

	@Test
	public void test1() throws Exception
	{
		System.out.println("hello");
		
		this.tree = new FeatureModelTree();
		_shell.parse("fm1 =" + _fm);
		FeatureModelVariable fm1 = getFMVariable("fm1");
		
		this.tree.buildTreeFromFMV(fm1);
		
		System.out.println(this.tree.toJson());
		
		
	}
}
