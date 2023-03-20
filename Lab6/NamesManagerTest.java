import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class NamesManagerTest {

	@Test
	public void testOneMatch() {
		
		List<String> names = new LinkedList<>();
		names.add("Bear,Yogi");
		
		int result = NamesManager.countMatches('Y', names);
		
		assertEquals(1, result);
		
	}

	@Test
	public void testNoMatch() {
		List<String> names = new LinkedList<>();
		names.add("Bear,Yogi");
		
		int result = NamesManager.countMatches('O', names);
		
		assertEquals(0, result);
	}

	// test inputs being null
	@Test(expected=IllegalArgumentException.class)
	public void testNullInputBoth() {
		int result = NamesManager.countMatches(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullInputCharacter() {
		List<String> names = new LinkedList<>();
		names.add("Todd, ");

		int result = NamesManager.countMatches(null, names);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testNullInputList() {
		int result = NamesManager.countMatches('Y', null);
	}

	@Test
	public void testEmptyName() {
		List<String> names = new LinkedList<>();
		names.add("");
		
		int result = NamesManager.countMatches('Y', names);
		
		assertEquals(0, result);
	}
	
	@Test
	public void testNullName() {
		List<String> names = new LinkedList<>();
		names.add(null);
		
		int result = NamesManager.countMatches('Y', names);

		assertEquals(0, result);
	}

	// no last name
	@Test
	public void testNoFirstName() {
		List<String> names = new LinkedList<>();
		names.add("Todd, ");
		
		int result = NamesManager.countMatches('Y', names);
		
		assertEquals(0, result);
	}

	@Test
	public void testNameFormattedSpace() {
		List<String> names = new LinkedList<>();
		names.add("Howard, Todd");
		
		int result = NamesManager.countMatches('T', names);
		
		assertEquals(0, result);
	}

	@Test
	public void testMissingSurname() {
		List<String> names = new LinkedList<>();
		names.add(",Todd");
		
		int result = NamesManager.countMatches('T', names);
		
		assertEquals(1, result);
	}
}
