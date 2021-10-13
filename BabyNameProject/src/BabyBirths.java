/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;

import java.io.File;

import org.apache.commons.csv.*;
// name, F/M, number
public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			//false means no first row as type name
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int boyNames = 0;
		int girlNames = 0;
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				boyNames += 1;
			}
			else {
				totalGirls += numBorn;
				girlNames += 1;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("boy names = " + boyNames);
		System.out.println("girl names =  "+girlNames);
		System.out.println("total names =  "+girlNames+boyNames);
	}
	
	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
	
	
	public int getRank(int year, String name, String gender) {
		FileResource fr = new FileResource();
		int rank = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			String g = rec.get(1);
			if (g.equals(gender)) {
				rank += 1;
				if (name.equals(rec.get(0))) {
					return rank;
				}
			}
			
		}
		return -1;
	}
	
	public void testGetRank() {
		int a = getRank(2012, "Frank", "M");
		System.out.println(a);
	}
	
	
	public String getName(int year, int rank, String gender) {
		FileResource fr = new FileResource();
		int r = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			String g = rec.get(1);
			if (g.equals(gender)) {
				r += 1;
				if (r == rank) {
					return rec.get(0);
				}
			}
			
		}
		return null;
	}
	
	
	public void testGetName() {
		String a = getName(2012, 450, "M");
		System.out.println(a);
	}
	
	public String whatIsNameInYear(String name, int year, int newYear, String gender) {
		int old_rank = getRank(year, name, gender);
		String new_name = getName(newYear, old_rank, gender);
		System.out.println(name + " born in "+year+" would be "+new_name+" if she was born in "+newYear);
		return new_name;
	}
	
	public void testWhatName() {
		String a = whatIsNameInYear("Owen", 2012, 2014, "M");
		
	}
	
	public int yearOfHighestRank(String name, String gender) {
		int highest = 9999999;
		String f_name = null;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			int rank = 0;
			for (CSVRecord rec : fr.getCSVParser(false)) {
				String g = rec.get(1);
				if (g.equals(gender)) {
					rank += 1;
					if (name.equals(rec.get(0)) && rank < highest) {
						highest = rank;
						f_name = f.getName();
					}
				}
				
			}
			
		}
		System.out.println(f_name+": "+highest);
		return highest;
	}
	
	public void testYearOfHighestRank() {
		int a = yearOfHighestRank("Mich", "M");
	}
	
	public double getAverageRank(String name, String gender) {
		double total_rank = 0;
		int num = 0;
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			num += 1;
			FileResource fr = new FileResource(f);
			int rank = 0;
			for (CSVRecord rec : fr.getCSVParser(false)) {
				String g = rec.get(1);
				if (g.equals(gender)) {
					rank += 1;
					if (name.equals(rec.get(0))) {
						total_rank+=rank;
					}
				}
				
			}
			if (rank == 0) {
				System.out.println("not found in file");
				return -1;
			}
			
		}
		System.out.println("avg rank is "+total_rank/num);
		return total_rank/num;
	}
	
	public void testAVG() {
		double a = getAverageRank("Robert", "M");
	}
	
	public int getTotalBirthsRankedHiger(int year, String name, String gender) {
		int total_higher = 0;
		FileResource fr = new FileResource();
		int rank = 0;
		for (CSVRecord rec : fr.getCSVParser(false)) {
			String g = rec.get(1);
			if (g.equals(gender)) {
				rank += 1;
				if (name.equals(rec.get(0))) {
					System.out.println("total higher born: "+total_higher);
					return total_higher;
				}
				total_higher += Integer.parseInt(rec.get(2));
			}
			
		}
		return -1;
	}
	
	public void testhigher() {
		int a = getTotalBirthsRankedHiger(2012, "Emily", "F");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BabyBirths b = new BabyBirths();
		// b.testTotalBirths();
		// b.testGetRank();
		// b.testGetName();
		// b.testWhatName();
		// b.testYearOfHighestRank();
		// b.testAVG();
		b.testhigher();
	}

}
