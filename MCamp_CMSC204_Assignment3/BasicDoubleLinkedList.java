import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
//Matthew Campbell
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	
	public class Node {
		public T data;
		public Node next;
		public Node prev;
		
		public Node(T dataEntry, Node nextNode, Node prevNode) {
			data = dataEntry;
			next = nextNode;
			prev = prevNode;
		}
		
		
		public void changeNext(Node n) {
			next = n;
		}
		
	
		public void changePrevious(Node n) {
			prev = n;
		}
	}
	
	public Node head; 
	public Node tail; 
	public int size; 

	public BasicDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
	}
	
	
	private class basicLLIterator implements ListIterator<T> {		
		private int cursor;
		private ArrayList<T> list;
		public basicLLIterator() {
			cursor = 0;
			list = toArrayList();
		}
		public boolean hasNext() {
			if(cursor < list.size()) {
				return true;
			} else {
				return false;
			}
		}
		public T next() throws NoSuchElementException{
			if(hasNext()) {
				T element = list.get(cursor);
				cursor++;
				return element;
			} else {
				throw new NoSuchElementException();
			}
		}
		public boolean hasPrevious() {
			if(cursor > 0) {
				return true;
			} else {
				return false;
			}
		}
		public T previous() throws NoSuchElementException {
			if(hasPrevious()) {
				cursor--;
				T element = list.get(cursor);
				return element;
			} else {
				throw new NoSuchElementException();
			}
		}
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}
	}
	public BasicDoubleLinkedList<T> addToEnd(T data) {
		if(tail == null) {
			tail = head = new Node(data, null, null);
		} else {
			Node old = tail;
			tail = new Node(data, null, old);
			old.changeNext(tail);	
		}
		size++;
		return this;
	}
	public BasicDoubleLinkedList<T> addToFront(T data) {
		if(head == null) {
			head = tail = new Node(data, null, null);
		} else {
			Node old = head;
			head = new Node(data, old, null);
			old.changePrevious(head);
		}
		size++;
		return this;
	}
	public T getFirst() {
		return head.data;
	}
	public T getLast() {
		return tail.data;
	}
	public int getSize() {
		return size;
	}
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return new basicLLIterator();
	}
	public BasicDoubleLinkedList<T> remove(T targetData, Comparator<T> comparator) {
		if(comparator.compare(targetData, head.data) == 0) {
			head = new Node(head.next.data, head.next.next, null);
			size--;
			return this;
		}
		Node current = head;
		while(comparator.compare(targetData, current.data) != 0) {
			if(current.next == null) {
				return null;
			} else {
				current = current.next;
			}
		}
		if(current.next == null) {
			current.prev.changeNext(null);
			tail = current.prev;
			size--;
			return this;
		}
		current.prev.changeNext(current.next);
		current.next.changePrevious(current.prev);
		current = null;
		size--;
		
		return this;
	}
	public T retrieveFirstElement() {
		T firstElement = head.data;
		head = new Node(head.next.data, head.next.next, null);
		size--;
		return firstElement;
	}
	public T retrieveLastElement() {
		T lastElement = tail.data;
		tail = new Node(tail.prev.data, null, tail.prev.prev);
		size--;
		return lastElement;
	}
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		Node n = head;
		while(n.next != null) {
			list.add(n.data);
			n = n.next;
		}
		list.add(tail.data);
		return list;
	}
	
}