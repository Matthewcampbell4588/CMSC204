import java.util.ArrayList;
//Matthew Campbell
public class MyStack<T> implements StackInterface<T>{

	private Object[] elements;
	  private int first;
	  private int last;
	  private int numElements;
	  private int size;

	  public MyStack() {
	    this.size = 20;
	    this.elements = new Object[size];
	  }
	  public MyStack(int capacity) {
	    this.size = capacity;
	    this.first = this.last = -1;
	    this.numElements = 0;
	    elements = new Object[capacity];
	  }
	  @Override
	  public boolean isEmpty() {
	    return numElements == 0;
	  }
	  @Override
	  public boolean isFull() {
	    return size == numElements;
	  }
	  @Override
	  public T pop() throws StackUnderflowException {
	    if (isEmpty()) {
	      throw new StackUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInTop = (T) elements[last];
	    if (firstInTop == null)
	      return null;
	    elements[last] = null;
	    last--;
	    numElements--;
	    return firstInTop;
	  }
	  @Override
	  public T top() throws StackUnderflowException {
	    if (isEmpty()) {
	      throw new StackUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInTop = (T) elements[last];
	    return firstInTop;
	  }
	  @Override
	  public int size() {
	    return numElements;
	  }
	  @Override
	  public boolean push(T e) throws StackOverflowException {
	    if (isFull()) {
	      throw new StackOverflowException();
	    }

	    if (isEmpty()) {
	      first = last = 0;
	    } else {
	      last++;
	    }
	    numElements++;
	    elements[last] = e;
	    return true;
	  }
	  @Override
	  public String toString() {
	    StringBuilder sb = new StringBuilder();

	    for (int i = first; i <= last; i++) {
	      sb.append(elements[i]);
	    }
	    return sb.toString();
	  }
	  @Override
	  public String toString(String delimiter) {
	    StringBuilder sb = new StringBuilder();

	    for (int i = first; i < last; i++) {
	      sb.append(elements[i] + delimiter);
	    }
	    sb.append(elements[last]);
	    return sb.toString();
	  }

	  /**
	   * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
	   * is the first bottom element of the Stack
	   * @param list elements to be added to the Stack from bottom to top
	   */
	  @Override
	  public void fill(ArrayList<T> list) {
	    ArrayList<T> cloneList = new ArrayList<>(list);
	    cloneList.forEach(t -> {
	      try {
	        push(t);
	      } catch (StackOverflowException ex) {
	        ex.getMessage();
	      }
	    });
	  }

	}