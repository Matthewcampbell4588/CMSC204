
public class QueueUnderflowException extends RuntimeException {
	 private static final long serialVersionUID = -98217994408756195L;
	  
	  public QueueUnderflowException() {
	    super("Dequeue method has been called on an empty queue");
	  }
}
