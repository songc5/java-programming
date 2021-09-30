import edu.duke.*;
import java.io.*;
public class Part4 {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		URLResource u = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
		for (String word : u.words()) {
			//System.out.println(word);
			int pos = word.indexOf("youtube.com");
			if (pos != -1) {
				String wordl = word.toLowerCase();
				int start = wordl.lastIndexOf("\"", pos);
				int stop = wordl.indexOf("\"", pos+1);
				if (start != -1 & stop != -1) {
					System.out.println(word.substring(start, stop+1));
				}
				
			}
		}

	}

}
