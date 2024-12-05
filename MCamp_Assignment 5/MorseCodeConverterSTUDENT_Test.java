import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//Matthew Campbell 
public class MorseCodeConverterSTUDENT_Test {

	File codeFile;
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertToEnglishString() {
		String w1=MorseCodeConverter.convertToEnglish("--. . -. . .-. .- .-.. / -.- . -. --- -... ..");
		assertEquals("general kenobi", w1);
		
		String w2=MorseCodeConverter.convertToEnglish("-.-- --- ..- / ..- -. -.. . .-. . ... - .. -- .- - . / -- -.-- / .--. --- .-- . .-.");
		assertEquals("you underestimate my power", w2);
	}

	@Test
	public void testConvertToEnglishFile() throws FileNotFoundException {
		getFile("howDoILoveThee");
		String f1=MorseCodeConverter.convertToEnglish(codeFile);
		assertEquals("how do i love thee let me count the ways", f1);
		
		getFile("LoveLooksNot");
		String f2=MorseCodeConverter.convertToEnglish(codeFile);
		assertEquals("love looks not with the eyes but with the mind", f2);
	}
	
	public void getFile(String in) throws FileNotFoundException {		
		JFileChooser chooser = new JFileChooser();
		int status;

		chooser.setDialogTitle("Select Input File - " + in);
		status = chooser.showOpenDialog(null);

		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				codeFile = chooser.getSelectedFile();
			}
			catch (Exception e) {
			
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}