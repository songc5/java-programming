import edu.duke.*;
import java.io.*;
public class Part2 {

	/*
	 * Write the method named howMany that has two String parameters named stringa and stringb. 
	 * This method returns an integer indicating how many times stringa appears in stringb, 
	 * where each occurrence of stringa must not overlap with another occurrence of it. For example, 
	 * the call howMany(“GAA”, “ATGAACGAATTGAATC”) returns 3 as GAA occurs 3 times. The call howMany(“AA”, “ATAAAA”) returns 2.
	 *  Note that the AA’s found cannot overlap.
	 */
	
	public int howMany(String stringa, String stringb) {
		int occur = stringb.indexOf(stringa);
		int count = 0;
		while (occur != -1) {
			count += 1;
			occur = stringb.indexOf(stringa, occur+stringa.length());
		}
		
		return count;
	}
	
	public void testHowMany() {
		String a = "AA";
		String b = "ATTAAAAA";
		System.out.println(howMany(a,b));
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part2 p2 = new Part2();
		p2.testHowMany();
	}

}
