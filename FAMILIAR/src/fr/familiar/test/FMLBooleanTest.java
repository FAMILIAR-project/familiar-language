package fr.familiar.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FMLBooleanTest extends FMLTest {

	@Test
	public void testBool1() throws Exception {

		_shell.setVerbose(true);
		_shell.parse("a = true && false\n" + "b = true || false\n"
				+ "c = true && true\n" + "d = false && false\n"
				+ "e = false && false\n"
				+ "f = isValid FM ( A : B C ; ) && false\n"
				+ "g = isValid FM ( A : B C ; ) && true\n"
				+ "h = isValid FM ( A : B C ; !B | !C; ) && true\n"
				+ "i = isValid FM ( A : B C ; !B | !C; ) || true\n"
				+ "j = isValid FM ( A : B C ; !B | !C; ) || false");

		assertFalse(getBooleanVariable("a").isTrue());
		assertTrue(getBooleanVariable("b").isTrue());
		assertTrue(getBooleanVariable("c").isTrue());
		assertFalse(getBooleanVariable("d").isTrue());
		assertFalse(getBooleanVariable("e").isTrue());
		assertFalse(getBooleanVariable("f").isTrue());
		assertTrue(getBooleanVariable("g").isTrue());
		assertFalse(getBooleanVariable("h").isTrue());
		assertTrue(getBooleanVariable("i").isTrue());
		assertFalse(getBooleanVariable("j").isTrue());

	}

	// TODO: other scripts

}
