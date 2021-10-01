import edu.duke.*;
import java.io.*;
public class Part1 {
	/*
	 * Write the method findStopCodon that has three parameters, a String parameter named dna, 
	 * an integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna, 
	 * and a String parameter named stopCodon. This method returns the index of the first occurrence of stopCodon 
	 * that appears past startIndex and is a multiple of 3 away from startIndex. 
	 * If there is no such stopCodon, this method returns the length of the dna strand.
	 */
	
	public int findStopCodon(String dna, int startIndex, String stopCodon) {
		int end = dna.indexOf(stopCodon,startIndex);
		while (end != -1) {
			if ((end-startIndex)%3==0) {
				return end;
			}
			end = dna.indexOf(stopCodon, end+1);
		}
		return dna.length();
	}

	/*
	 * Write the void method testFindStopCodon that calls the method findStopCodon with several examples and prints out the results 
	 * to check if findStopCodon is working correctly. Think about what types of examples you should check. For example, 
	 * you may want to check some strings of DNA that have genes and some that do not. What other examples should you check?
	 */
	public void testFindStopCodon() {
		String a = "ATGATGAATT";
		System.out.println(findStopCodon(a, 0, "ATT"));
		
	}
	
	/*
	 * Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:

Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.

Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of 
three away from the “ATG”. Hint: call findStopCodon.

Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of 
three away from the “ATG”. Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” 
that is a multiple of three away from the “ATG”. 

Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop 
codon and therefore no gene, return the empty string.
	 */
	public String findGene(String dna) {
		int start = dna.indexOf("ATG");
		if (start == -1) {
			return "";
		}
		
		int taa = findStopCodon(dna, start, "TAA");
		int tag = findStopCodon(dna, start, "TAG");
		int tga = findStopCodon(dna, start, "AGA");
		int tp =  Math.min(taa, tag);
		int closest = Math.min(tga, tp);
		if (closest == dna.length()) {
			return "";
		}
		else {
			return dna.substring(start, closest+3);
		}
		
	}
	/*
	 * Write the void method testFindGene that has no parameters. You should create five DNA strings. The strings should have specific test cases such as DNA with no “ATG”, DNA with “ATG” and one valid stop codon, DNA with “ATG” and multiple valid stop codons, DNA with “ATG” and no valid stop codons. Think carefully about what would be good examples to test. For each DNA string you should: 

Print the DNA string. 

Calculate the gene by sending this DNA string as an argument to findGene. If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
	 */
	public void testFindGene() {
		String a = "ATGAAATAA";
		System.out.println(findGene(a));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part1 p1 = new Part1();
		//p1.testFindStopCodon();
		p1.testFindGene();
	}

}
