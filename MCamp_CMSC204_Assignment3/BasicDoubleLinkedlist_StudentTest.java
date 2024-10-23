import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicDoubleLinkedlist_StudentTest {
	BasicDoubleLinkedList<String> A1;
	BasicDoubleLinkedList<Double> b2;
	StringComparator c1;
	DoubleComparator c2;
	@BeforeEach
	void setUp() throws Exception {
		A1 = new BasicDoubleLinkedList<String>();
		A1.addToEnd("gold");
		A1.addToEnd("silver");
		c1 = new StringComparator();
		
		b2 = new BasicDoubleLinkedList<Double>();
		b2.addToEnd(10.0);
		b2.addToEnd(20.0);
		c2 = new DoubleComparator();
	}
	@AfterEach
	void tearDown() throws Exception {
		A1 = null;
		b2 = null;
		c1 = null;
		c2 = null;
	}
	@Test
	public void testGetSize() {
		assertEquals(2, A1.getSize());
		assertEquals(2,b2.getSize());
	}
	@Test
	public void testAddToEnd() {
		A1.addToEnd("crystal");
		assertEquals("crystal", A1.getLast());
		
		b2.addToEnd(30.0);
		assertEquals(30.0, b2.getLast());
	}
	@Test
	public void testAddToFront() {
		A1.addToFront("red");
		assertEquals("red", A1.getFirst());
		
		b2.addToFront(5.0);
		assertEquals(5.0, b2.getFirst());
	}
	@Test
	public void testGetFirst() {
		assertEquals("gold", A1.getFirst());
		assertEquals(10.0,b2.getFirst());
	}
	@Test
	public void testGetLast() {
		assertEquals("silver", A1.getLast());
		assertEquals(20.0, b2.getLast());
	}
	@Test
	public void testToArrayList()
	{
		ArrayList<String> list;
		A1.addToFront("red");
		A1.addToEnd("crystal");
		list = A1.toArrayList();
		assertEquals("red",list.get(0));
		assertEquals("gold",list.get(1));
		assertEquals("silver",list.get(2));
		assertEquals("crystal",list.get(3));
	}
	@Test
	public void testRemove() {
		A1.remove("silver", c1);
		assertEquals(1, A1.getSize());
		assertEquals("gold", A1.getLast());
	}
	@Test
	public void testRetrieveFirstElement() {
		assertEquals("gold", A1.retrieveFirstElement());
		assertEquals(10.0, b2.retrieveFirstElement());
	}
	@Test
	public void testRetrieveLastElement() {
		assertEquals("silver", A1.retrieveLastElement());
		assertEquals(20.0, b2.retrieveLastElement());
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