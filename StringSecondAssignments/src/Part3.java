import edu.duke.*;
import java.io.*;
public class Part3 {

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
	
	public String findNextGene(String dna, int next) {
		int start = dna.indexOf("ATG", next);
		if (start == -1) {
			return "";
		}
		
		int taa = findStopCodon(dna, start, "TAA");
		int tag = findStopCodon(dna, start, "TAG");
		int tga = findStopCodon(dna, start, "TGA");
		int tp =  Math.min(taa, tag);
		int closest = Math.min(tga, tp);
		if (closest == dna.length()) {
			return "";
		}
		else {
			return dna.substring(start, closest+3);
		}
		
	}
	
	public void printAllGenes(String dna) {
		int start = 0;
		while (true) {
			String gene = findNextGene(dna, 0);
			if (gene.isEmpty()) {
				return;
			}
			System.out.println(gene);
			start = dna.indexOf(gene, start)+gene.length();
		}
	}
	
	public int countGenes(String dna) {
		int count = 0;
		int start = 0;
		while (true) {
			String gene = findNextGene(dna, start);
			if (gene.isEmpty()) {
				break;
			}
			//System.out.println(gene);
			count += 1;
			start = dna.indexOf(gene, start)+gene.length();
		}
		
		return count;
	}
	
	public void testCountGenes() {
		String a = "ATGTAAGATGCCCTAGT";
		System.out.println(countGenes(a));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Part3 p3 = new Part3();
		p3.testCountGenes();
	}

}
