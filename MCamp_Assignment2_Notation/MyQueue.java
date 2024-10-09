import java.util.ArrayList;
//Matthew Campbell
public class MyQueue<T> implements QueueInterface<T> {
	 private Object[] elements;
	  private int first;
	  private int last;
	  private int numElements;
	  private int capacity;
	  public MyQueue() {
	    capacity = 20;
	    elements = new Object[capacity];

	  }
	  public MyQueue(int capacity) {
	    this.capacity = capacity;
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
	    return capacity == numElements;
	  }
	  @Override
	  public T dequeue() throws QueueUnderflowException {
	    if (isEmpty()) {
	      throw new QueueUnderflowException();
	    }
	    @SuppressWarnings("unchecked")
	    T firstInLine = (T) elements[first];
	    if (firstInLine == null)
	      return null;
	    elements[first] = null;
	    first++;
	    numElements--;
	    return firstInLine;
	  }
	  @Override
	  public int size() {
	    return numElements;
	  }

	  @Override
	  public boolean enqueue(T e) throws QueueOverflowException {
	    if (isFull()) {
	      throw new QueueOverflowException();
	    }
	    
	    if(isEmpty()) {
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
	    StringBuilder s = new StringBuilder();
	   
	    for (int i = first; i <= last; i++) {
	      s.append(elements[i]);
	    }
	    return s.toString();
	  }
	  @Override
	  public String toString(String delimiter) {
	    StringBuilder s = new StringBuilder();
	    
	    for (int i = first; i < last; i++) {
	      s.append(elements[i] + delimiter);
	    }
	    s.append(elements[last]);
	    return s.toString();
	  }
	  @Override
	  public void fill(ArrayList<T> list) {
	    ArrayList<T> cloneList = new ArrayList<>(list);
	    cloneList.forEach(t -> {
	      try {
	        enqueue(t);
	      } catch (QueueOverflowException ex) {
	        ex.getMessage();
	      }
	    });
	  }

	}

