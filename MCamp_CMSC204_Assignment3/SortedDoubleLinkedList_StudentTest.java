import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortedDoubleLinkedList_StudentTest {

	SortedDoubleLinkedList<String> A1;
	SortedDoubleLinkedList<Double> b2;
	
	StringComparator c1;
	DoubleComparator c2;
	
	@BeforeEach
	void setUp() throws Exception {
		c1 = new StringComparator();
		A1 = new SortedDoubleLinkedList<String>(c1);
		
		c2 = new DoubleComparator();
		b2 = new SortedDoubleLinkedList<Double>(c2);
	}

	@AfterEach
	void tearDown() throws Exception {
		c1 = null;
		c2 = null;

		A1 = null;
		b2 = null;
	}
	
	@Test
	public void testAdd() {
		A1.add("BC");
		A1.add("AV");
		A1.add("CD");
		assertEquals(3, A1.getSize());
		ArrayList<String> listOne = A1.toArrayList();
		assertEquals("AV", listOne.get(0));
		assertEquals("BC", listOne.get(1));
		assertEquals("CD", listOne.get(2));
		
		b2.add(4.0);
		b2.add(3.5);
		b2.add(7.3);
		assertEquals(3, b2.getSize());
		ArrayList<Double> listTwo = b2.toArrayList();
		assertEquals(3.5, listTwo.get(0));
		assertEquals(4.0, listTwo.get(1));
		assertEquals(7.3, listTwo.get(2));
	}
	
	@Test
	public void testIterator() {
		A1.add("BC");
		A1.add("AV");
		A1.add("CD");
		ListIterator<String> iteratorOne = A1.iterator();
		assertEquals(true, iteratorOne.hasNext());
		assertEquals("AV", iteratorOne.next());
		assertEquals("BC", iteratorOne.next());
		assertEquals(true, iteratorOne.hasPrevious());
		assertEquals("BC", iteratorOne.previous());
		
		b2.add(2.0);
		b2.add(4.5);
		b2.add(7.3);
		ListIterator<Double> iteratorTwo = b2.iterator();
		assertEquals(true, iteratorTwo.hasNext());
		assertEquals(2.0, iteratorTwo.next());
		assertEquals(4.5, iteratorTwo.next());
		assertEquals(7.3, iteratorTwo.next());
		assertEquals(true, iteratorTwo.hasPrevious());
		assertEquals(7.3, iteratorTwo.previous());
	}
	
	@Test
	void testRemove() {
		A1.add("BC");
		A1.add("AV");
		A1.add("CD");
		assertEquals(3, A1.getSize());
		A1.remove("BC", c1);
		assertEquals(2, A1.getSize());
		ArrayList<String> listOne = A1.toArrayList();
		assertEquals("AV", listOne.get(0));
		assertEquals("CD", listOne.get(1));
		
		b2.add(3.0);
		b2.add(2.5);
		b2.add(1.3);
		assertEquals(3, b2.getSize());
		b2.remove(3.0, c2);
		ArrayList<Double> listTwo = b2.toArrayList();
		assertEquals(1.3, listTwo.get(0));
		assertEquals(2.5, listTwo.get(1));
	}
	
	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}

}