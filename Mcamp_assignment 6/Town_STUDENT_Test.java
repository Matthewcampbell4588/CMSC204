
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Matthew Campbell
public class Town_STUDENT_Test {
	private Town town;
	  
	@Before
	public void setUp() throws Exception {
		town = new Town("gettysburg");		 
	}

	@After
	public void tearDown() throws Exception {
		town = null;
	}

	@Test
	public void testGetName() {
		assertEquals(town.getName(), "gettysburg");
	}

	@Test
	public void testToString() {
		assertEquals(town.toString(), "gettysburg");
	}

	@Test
	public void testEquals() {
		Town town2 = new Town("gettysburg");
		assertTrue(town.equals(town2));
		
	}
	
	@Test
	public void testCompareTo() {
		Town town2 = new Town("Rockville");
		assertTrue(town.compareTo(town2) != 0);
	}



}