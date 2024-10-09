
public class Notation {
	//Matthew Campbell
	 private static MyQueue<String> MyQueue;
	  private static MyStack<String> MyStack;
	  private final static String OPS = "+-*/";

	  private static String stackTop() {
	    try {
	      return MyStack.top();
	    } catch (StackUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }


	  private static String stackPop() {
	    try {
	      return MyStack.pop();
	    } catch (StackUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }
	  private static boolean stackPush(String c) {
	    try {
	      return MyStack.push(c);
	    } catch (StackOverflowException e) {
	      e.getMessage();
	    }
	    return false;
	  }
	  private static boolean enqueue(String c) {
	    try {
	      return MyQueue.enqueue(c);
	    } catch (QueueOverflowException e) {
	      e.getMessage();
	    }
	    return false;
	  }
	  private static String dequeue() {
	    try {
	      return MyQueue.dequeue();
	    } catch (QueueUnderflowException e) {
	      e.getMessage();
	    }
	    return null;
	  }

	  private static int calculatePrec(char c) {
	    if (c == '*' || c == '/') {
	      return 1;
	    } else if (c == '+' || c == '-') {
	      return 0;
	    }
	    return -1;
	  }

	  
	  private static String applyOperator(String first, String second, char operator) throws InvalidNotationFormatException {
	    double a = Double.parseDouble(first);
	    double b = Double.parseDouble(second);
	    switch (operator) {
	      case '+':
	        return Double.toString(a + b);
	      case '-':
	        return Double.toString(a - b);
	      case '*':
	        return Double.toString(a * b);
	      case '/':
	        if (b == 0)
	          throw new InvalidNotationFormatException();
	        return Double.toString(a / b);
	    }
	    return null;
	  }

	  
	  public static String convertInfixToPostfix(String complexInfix)throws InvalidNotationFormatException {
		  MyQueue = new MyQueue<String>();
	    MyStack = new MyStack<String>();

	    for (int i = 0; i < complexInfix.length(); i++) {
	      char cur = complexInfix.charAt(i);
	      if (cur == ' ') {
	        continue;
	      } else if (Character.isDigit(cur)) {
	        enqueue(Character.toString(cur));
	      } else if (cur == '(') {
	        stackPush(Character.toString(cur));
	      } else if (OPS.indexOf(cur) >= 0) {
	        while (!MyStack.isEmpty() && calculatePrec(stackTop().charAt(0)) >= calculatePrec(cur)) {
	          enqueue(stackPop());
	        }
	        stackPush(Character.toString(cur));
	      } else if (cur == ')') {
	        char top = stackPop().charAt(0);
	        while (top != '(') {
	          enqueue(Character.toString(top));
	          if (MyStack.isEmpty()) {
	            throw new InvalidNotationFormatException();
	          } else {
	            top = stackPop().charAt(0);
	          }
	        }
	      }
	    }
	    while (!MyStack.isEmpty()) {
	      enqueue(stackPop());
	    }
	    return MyQueue.toString();
	  }

	  
	  public static String convertPostfixToInfix(String complexPostfix)throws InvalidNotationFormatException {
		  MyStack = new MyStack<String>();
	    for (int i = 0; i < complexPostfix.length(); i++) {
	      char cur = complexPostfix.charAt(i);
	      if (cur == ' ') {
	        continue;
	      } else if (Character.isDigit(cur)) {
	        stackPush(Character.toString(cur));
	      } else if (OPS.indexOf(cur) >= 0) {
	        String a = stackPop().toString(), b, tmp;
	        if (MyStack.isEmpty()) {
	          throw new InvalidNotationFormatException();
	        } else {
	          b = stackPop().toString();
	          tmp = '(' + b + cur + a + ')';
	          stackPush(tmp);
	        }
	      }
	    }
	    if (MyStack.size() != 1) {
	    	throw new InvalidNotationFormatException();
	    }
	    return stackPop();
	  }

	 
	  public static double evaluatePostfixExpression(String complexPostfix)throws InvalidNotationFormatException {
		  MyStack = new MyStack<String>();
	    for (int i = 0; i < complexPostfix.length(); i++) {
	      char cur = complexPostfix.charAt(i);
	      if (cur == ' ') {
	        continue;
	      } else if (Character.isDigit(cur) || cur == '(') {
	        stackPush(Character.toString(cur));
	      } else if (OPS.indexOf(cur) >= 0) {
	        String a = stackPop().toString(), b;
	        String result;
	        if (MyStack.isEmpty()) {
	          throw new InvalidNotationFormatException();
	        } else {
	          b = stackPop().toString();
	          result = applyOperator(b, a, cur);
	          stackPush(result);
	        }
	      }
	    }
	    if (MyStack.size() != 1) {
	      throw new InvalidNotationFormatException();
	    }
	    return Double.parseDouble(stackPop());
	  }

	  
	  public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
	    String postfixExpression = convertInfixToPostfix(infixExpr);
	    return evaluatePostfixExpression(postfixExpression);
	  }
}
