import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

//Matthew Campbell
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comp;
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		super();
		comp = comparator;
	}
	public SortedDoubleLinkedList<T> add(T data) {
		if(head == null) {
			head = tail = new Node(data, null, null);
			size++;
			return this;
		}
		if(comp.compare(data, head.data) <= 0) {
			Node n = new Node(data, head, null);
			head.changePrevious(n);
			head = n;
			size++;
			return this;
		}
		Node current = head;
		while(comp.compare(data, current.data) > 0) {
			
			if(current.next == null) {
				Node n = new Node(data, null, tail);
				tail.changeNext(n);
				tail = n;
				size++;
				return this;
			}
			current = current.next;
		}
		Node n = new Node(data, current, current.prev);
		current.prev.changeNext(n);
		current.changePrevious(n);
		size++;
		return this;
	}
	public BasicDoubleLinkedList<T> addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	public BasicDoubleLinkedList<T> addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}
	
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException {
		return super.iterator();
	}
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator) {
		super.remove(data, comparator);
		return this;
	}

	
}