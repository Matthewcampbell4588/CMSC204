
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Matthew Campbell
 *
 */
public class PasswordCheckerTest_STUDENT {
	  ArrayList<String> passwords;
	  String password1, password2;
	@Before
	public void setUp() throws Exception {
		  String[] p = {"458899pU%", "Bagpipes56", "Judo&@##@3", "be65ight", "Morphus$666", "februyurY24?", "", "u999v", "foodCharac1%", "Abbbcd65@", "myMatthewcam2!"};
			    passwords = new ArrayList<String>();
			    passwords.addAll(Arrays.asList(p));
	}

	@After
	public void tearDown() throws Exception {
	
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		  try {
		      assertTrue(PasswordCheckerUtility.isValidPassword("1$4"));
		      PasswordCheckerUtility.isValidPassword("can4$");
		      assertTrue("Did not throw lengthException", false);
		    } catch (LengthException e) {
		      assertTrue("Successfully threw a lengthExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides lengthException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		 try {
		      assertTrue(PasswordCheckerUtility.isValidPassword("Baconing4U$"));
		      PasswordCheckerUtility.isValidPassword("bacon4566");
		      assertTrue("Did not throw NoUpperAlphaException", false);
		    } catch (NoUpperAlphaException e) {
		      assertTrue("Successfully threw a NoUpperAlphaExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides NoUpperAlphaException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
		      assertTrue(PasswordCheckerUtility.isValidPassword("Baconing4U$"));
		      PasswordCheckerUtility.isValidPassword("BACONING");
		      assertTrue("Did not throw NoLowerAlphaException", false);
		    } catch (NoLowerAlphaException e) {
		      assertTrue("Successfully threw a NoLowerAlphaExcepetion", true);
		    } catch (Exception e) {
		      assertTrue("Threw some other exception besides NoLowerAlphaException", false);
		    }
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		 try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("Macon4U$"));
		      boolean wPwd = PasswordCheckerUtility.isWeakPassword("Macon4U$");
		      assertTrue(wPwd);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some incorrect exception", false);
		    }
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		 try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("Maccon4U$"));
		      PasswordCheckerUtility.isValidPassword("Maaacon4U$");
		      assertTrue("Did not throw an InvalidSequenceException", false);
		    } catch (InvalidSequenceException e) {
		      assertTrue("Successfully threw an InvalidSequenceExcepetion", true);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some other exception besides an InvalidSequenceException", false);
		    }
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		 try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("Maccon4U$"));
		      PasswordCheckerUtility.isValidPassword("MooorningU");
		      assertTrue("Did not throw a NoDigitException", false);
		    } catch (NoDigitException e) {
		      assertTrue("Successfully threw a NoDigitException", true);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some other exception besides a NoDigitException", false);
		    }
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
		      assertEquals(true, PasswordCheckerUtility.isValidPassword("Maccon4U$"));
		      PasswordCheckerUtility.isValidPassword("Maaaaaccong4U");
		      assertTrue("Did not throw a NoSpecialCharacterException", false);
		    } catch (NoSpecialCharacterException e) {
		      assertTrue("Successfully threw a NoSpecialCharacterException", true);
		    } catch (Exception e) {
		      System.out.println(e.getMessage());
		      assertTrue("Threw some other exception besides a NoSpecialCharacterException", false);
		    }
	}
	

	
}
	

