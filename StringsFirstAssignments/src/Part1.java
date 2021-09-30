import edu.duke.*;
import java.io.*;
public class Part1 {
	
	public String findSimpleGene(String dna) {
		int start = dna.indexOf("ATG");
		if (start==-1) {
			return "";
		}
		int end = dna.indexOf("TAA", start+3);
		if (end==-1) {
			return "";
		}
		return dna.substring(start, end+3);
	}
	
	public void testSimpleGene() {
		String a = "ATGGTAA";
		System.out.println(a);
		System.out.println(findSimpleGene(a));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part1 p = new Part1();
		p.testSimpleGene();
		
	}

}
