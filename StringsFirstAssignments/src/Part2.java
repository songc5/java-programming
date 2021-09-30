import edu.duke.*;
import java.io.*;
public class Part2 {
	public String findSimpleGene(String dna, String startC, String stopC) {
		String lowerDNA = dna.toLowerCase();
		int start = lowerDNA.indexOf(startC.toLowerCase());
		if (start==-1) {
			return "";
		}
		int end = lowerDNA.indexOf(stopC.toLowerCase(), start+3);
		if (end==-1) {
			return "";
		}
		return dna.substring(start, end+3);
	}
	
	public void testSimpleGene() {
		String a = "ATGGTAA";
		System.out.println(a);
		System.out.println(findSimpleGene(a, "ATT", "TAA"));
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part2 p2 = new Part2();
		p2.testSimpleGene();
	}

}
