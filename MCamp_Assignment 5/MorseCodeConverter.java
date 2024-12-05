import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
// Matthew Campbell
public class MorseCodeConverter {
	
	private static MorseCodeTree tree=new MorseCodeTree();
	
	
	public static String printTree() {
		String data="";
		ArrayList<String>list=new ArrayList<String>();
		list=tree.toArrayList();
		for(int i=0;i<list.size();i++) {
			data+=list.get(i);
		}
		return data;
	}

	public static String convertToEnglish(String morseCode) {
		String[] codeWords;
		String[] codeLetters;
		String result="";
		codeWords=morseCode.split("/");
		for(int i=0;i<codeWords.length;i++) {
			codeWords[i]=codeWords[i].trim();
			codeLetters=codeWords[i].split(" ");
			for(int j=0;j<codeLetters.length;j++) {
				result+=tree.fetch(codeLetters[j]);
			}
			result+=" ";
		}
		result=result.trim();
		return result;
	}

	public static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner in=new Scanner(codeFile);
		String result="";
		while(in.hasNext()) {
			result+=convertToEnglish(in.nextLine());
		}
		return result;
	}

}