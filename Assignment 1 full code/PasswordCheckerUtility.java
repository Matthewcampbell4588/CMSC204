

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
// @MatthewCampbell
public final class PasswordCheckerUtility {
	private static boolean hasLowerAlpha(String pass) throws NoLowerAlphaException {

		if(pass.equals(pass.toUpperCase())) 
			throw new NoLowerAlphaException("lowercase");
		else
			return  false;

	}
	
	
	private static boolean hasDigit(String pass) throws NoDigitException {
		char[] pass2=pass.toCharArray();
		int count=0;
		for(int i=0;i<pass2.length;i++) {
			if(Character.isDigit(pass2[i])) {
				count++;
			}
		}
		if(count==0) {
			throw new NoDigitException("Password must contain a numeric character");
		}
		else {
			return true;
		}

	}
	
	
	private static boolean hasSpecialChar(String pass) throws NoSpecialCharacterException {
		String reg="[a-zA-Z0-9]*";
		Pattern pat=Pattern.compile(reg);
		Matcher mat=pat.matcher(pass);
		
		if(mat.matches()) {
			throw new NoSpecialCharacterException("Password must contain a Special Character");
		}
		else {
			return true;
		}
		

	}
	

	public static boolean isValidPassword(String passwordString)
		      throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException,
		      NoSpecialCharacterException, InvalidSequenceException {
		    if(isValidLength(passwordString)) {
		    	if(hasUpperAlpha(passwordString)) {
		    		if(hasLowerAlpha(passwordString)) {
		    			if( hasDigit(passwordString)) {
		    				if( hasSpecialChar(passwordString)) {
		    					if(NoSameCharInSequence​(passwordString)) {
		    						return true;
		    					}
		    					
		    				}
		    			}
		    		}
		    	}
		    }
		    return true;
		  }
	
	
	
	
	

	public static boolean isWeakPassword(String passwordString) throws WeakPasswordException{
		
		if(passwordString.length()>=6 && passwordString.length()<=9) {
			throw new WeakPasswordException("");
		}
		else 
			return false;
	}
		
		
	
	
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {

	    ArrayList<String> invalidPasswords = new ArrayList<>();
	    for (String s : passwords) {
	      try {
	        isValidPassword(s);
	      } catch (Exception ex) {
	        invalidPasswords.add(s + " The password must contain at least " + ex.getMessage());
	      }
	    }
	    return invalidPasswords;
	  }

	public static boolean comparePasswordsWithReturn(String passwordString, String passwordAString) {

		if(passwordString.equals(passwordAString)) {
			return true;
		}
		else {
		return false;
		}
	}

	public static boolean hasUpperAlpha(String string) throws NoUpperAlphaException {
		// TODO Auto-generated method stub
		if(string.equals(string.toLowerCase())) {
			throw new NoUpperAlphaException("");
		}
		else
		return true;
	}

	public static   boolean isValidLength(String password) throws LengthException{
		if(!(password.length() >= 6)) {
			throw new LengthException("The password must be at least 6 characters long");
		}
		else 
			return true;
		
		
	}

	public static void comparePasswords(String password, String passwordConfirm)  throws UnmatchedException {
		if(!password.equals(passwordConfirm)) {
			throw new UnmatchedException ("Passwords do not match");
		}
		
		
	}
	public static boolean NoSameCharInSequence​(String password) throws InvalidSequenceException{
		for(int i=0; i<password.length()-2;i++) {
			
		if(password.charAt(i)==password.charAt(i+1)&&password.charAt(i)==password.charAt(i+2)) {
			throw new InvalidSequenceException("Password should not contain more than 2 of the same character in sequence");
			
		}
		}
			return true;
	}


	
	
}