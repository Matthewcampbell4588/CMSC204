
public class StackOverflowException extends RuntimeException{
	private static final long serialVersionUID = -324591898653728278L;
	  
	  public StackOverflowException() {
	    super("Push method has been called on a full stack");
	  }
}
