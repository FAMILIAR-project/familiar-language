package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.ac.fundp.info.TVLParser.Util.FDElement;

public class FDElementTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testEqualsFDElement1() {
			FDElement a = new FDElement("a", true, false);
			FDElement b = new FDElement("b", true, false);
			assertFalse(a.equals(b));
	}
	
	@Test
	public void testEqualsFDElement2() {
			FDElement a = new FDElement("a", true, false);
			FDElement b = new FDElement("a", true, false);
			assertTrue(a.equals(b));
	}
	
	@Test
	public void testEqualsFDElement3() {
			FDElement a = new FDElement("a", true, false);
			assertTrue(a.equals(a));
	}
	
	@Test
	public void testEqualsFDElement4() {
			FDElement a = new FDElement("a", true, false);
			FDElement b = new FDElement("a", false, false);
			assertFalse(a.equals(b));
	}
	
	@Test
	public void testEqualsFDElement5() {
			List<FDElement> myList = new ArrayList<FDElement>();
			FDElement a = new FDElement("a", true, false);
			FDElement otherA = new FDElement("a", true, false);
			
			FDElement c = new FDElement("c", true, false);
			FDElement d = new FDElement("d", true, false);
			FDElement e = new FDElement("e", true, false);
			
			myList.add(c);
			myList.add(d);
			myList.add(a);
			myList.add(e);
			assertTrue(a.equals(otherA));
			
			assertTrue(myList.indexOf(otherA) >= 0);
	}

	@Test
	public void testEqualsFDElement6() {
			List<FDElement> myList = new ArrayList<FDElement>();
			FDElement a = new FDElement("a", true, false);
			FDElement otherA = new FDElement("a", true, false);
			
			FDElement c = new FDElement("c", true, false);
			FDElement d = new FDElement("d", true, false);
			FDElement e = new FDElement("e", true, false);
			
			myList.add(c);
			myList.add(d);
			myList.add(a);
			myList.add(e);
			assertTrue(a.equals(otherA));
			FDElement result = null ;
			for(FDElement o : myList){
				if(o.equals(otherA)) {  result = o ; break; }
			}
			assertNotNull(result);
			assertTrue(result.equals(a));
			assertTrue(result.equals(otherA));
	}
	
	@Test
	public void testEqualsFDElement7() {
			List<FDElement> myList = new ArrayList<FDElement>();
			FDElement a = new FDElement("a", true, false);
			FDElement otherA = new FDElement("a", true, false);
			
			FDElement c = new FDElement("c", true, false);
			FDElement d = new FDElement("d", true, false);
			FDElement e = new FDElement("e", true, false);
			
			myList.add(c);
			myList.add(d);
			myList.add(a);
			myList.add(e);
			assertTrue(a.equals(otherA));
			FDElement result = null ;
			int i = 0;
			while(i < myList.size()){
				if(myList.get(i).equals(otherA)) {  result = myList.get(i) ; break; }
				i++;
			}
			assertNotNull(result);
			
			assertTrue(result.equals(a));
			assertTrue(result.equals(otherA));
	}
}
