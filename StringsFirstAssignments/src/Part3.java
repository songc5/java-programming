import edu.duke.*;
import java.io.*;
public class Part3 {
	public boolean twoOccurrences(String stringa, String stringb) {
		int count = 0;
		int current = 0;
		while (current != -1) {
			current = stringb.indexOf(stringa, current);
			if (current != -1) {
				count += 1;
				current += stringa.length();
			}
		}
		if (count > 1) {
			System.out.println(count);
			return true;
		}
		return false;
	}
	
	public String lastPart(String stringa, String stringb) {
		int occur = stringb.indexOf(stringa);
		if (occur == -1) {
			return stringb;
		}
		return stringb.substring(occur+stringa.length());
	}
	public void testing() {
		String a  = "bana";
		String b = "banana";
		System.out.println(twoOccurrences(a, b));
		System.out.println(lastPart(a, b));
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part3 p3 = new Part3();
		p3.testing();
	}

}
