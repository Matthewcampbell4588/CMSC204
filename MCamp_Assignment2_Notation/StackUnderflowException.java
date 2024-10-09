
public class StackUnderflowException extends RuntimeException {
	private static final long serialVersionUID = -2730101186961769183L;
	  
	  public StackUnderflowException() {
	    super("Pop or top method has been called on an empty stack");
	  }
}
