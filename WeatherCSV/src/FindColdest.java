import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class FindColdest {
	
	public CSVRecord coldestHourInFile(CSVParser parser) {
		CSVRecord coldestSoFar = null;
		for (CSVRecord current : parser) {
			if (coldestSoFar == null) {
				coldestSoFar = current;
			}
			else {
				double currentTp = Double.parseDouble(current.get("TemperatureF"));
				double coldest = Double.parseDouble(coldestSoFar.get("TemperatureF"));
				if (coldest > currentTp) {
					coldestSoFar = current;
				}
			}
		}
		
	return coldestSoFar;
	}
	
	public void testColdestHourInFile() {
		FileResource fr = new FileResource();
		CSVRecord smallest = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + smallest.get("TemperatureF") +
				   " at " + smallest.get("DateUTC"));
		
	}
	
	public String fileWithColdestTemperature() {
		String file_name = null;
		CSVRecord coldestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
			if (coldestSoFar == null) {
				coldestSoFar = currentRow;
				file_name = f.getName();
			}
			//Otherwise
			else {
				double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
				double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
				//Check if currentRow’s temperature > largestSoFar’s
				if (currentTemp < coldestTemp) {
					//If so update largestSoFar to currentRow
					coldestSoFar = currentRow;
					file_name = f.getName();
				}
			}
		}
		System.out.println("temperature: " + coldestSoFar.get("TemperatureF"));
		
		return file_name;
	}
	public void coldestFileName () {
		String coldest = fileWithColdestTemperature();
		System.out.println("coldest file was " + coldest);
	}
	
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		CSVRecord lowest = null;
		for (CSVRecord current : parser) {
			if (lowest == null) {
				if (current.get("Humidity").equals("N/A")) {
					continue;
				}
				lowest = current;
			}
			else {
				if (! (current.get("Humidity").equals("N/A"))) {
					double currentTp = Integer.parseInt(current.get("Humidity"));
					double lTp = Integer.parseInt(lowest.get("Humidity"));
					if (lTp > currentTp) {
						lowest = current;
					}
				}
			}
		}
		System.out.println("lowest humidity: "+lowest.get("Humidity")+"at date " + lowest.get("DateUTC"));
		
	return lowest;
		
	}
	
	public void testlowestHumidityInFile() {
		FileResource fr = new FileResource();
		CSVRecord smallest = lowestHumidityInFile(fr.getCSVParser());
		
		
	}
	
	public CSVRecord lowestHumidityInManyFiles() {
		DirectoryResource dr = new DirectoryResource();
		CSVRecord lowest = null;
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord cur = lowestHumidityInFile(fr.getCSVParser());
			if (lowest == null) {
				lowest = cur;
			}
			else {
				int curH = Integer.parseInt(cur.get("Humidity"));
				int lH = Integer.parseInt(lowest.get("Humidity"));
				if (curH < lH) {
					lowest = cur;
				}
			}
		}
		System.out.println("final lowest humidity: "+lowest.get("Humidity")+"at date " + lowest.get("DateUTC"));
		return lowest;
		
	}
	
	public void testLowestHumidityInManyFiles() {
		CSVRecord smallest = lowestHumidityInManyFiles();
	}
	
	public double averageTemperatureInFile(CSVParser parser) {
		double totalTP = 0;
		int count = 0;
		for (CSVRecord current : parser) {
			count += 1;	
			totalTP += (Double.parseDouble(current.get("TemperatureF")));
		}
		
		System.out.println("average temperature is "+ totalTP/count);
		return totalTP/count;
		
	}
	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource();
		double abg = averageTemperatureInFile(fr.getCSVParser());
		
	}
	
	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		double totalTp = 0;
		int count = 0;
		for (CSVRecord current : parser) {
			int hum = Integer.parseInt(current.get("Humidity"));
			if (hum >= value) {
				count += 1;	
				totalTp += (Double.parseDouble(current.get("TemperatureF")));
			}
		}
		if (count == 0) {
			System.out.println("No temperatures with that humidity");
			return 0;
		}
		System.out.println("Average Temp when high Humidity is "+totalTp/count);
		return totalTp/count;
	}
	
	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource();
		double abg = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(), 80);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FindColdest p = new FindColdest();
		// p.testColdestHourInFile();
		// p.coldestFileName();
		// p.testlowestHumidityInFile();
		// p.testLowestHumidityInManyFiles();
		// p.testAverageTemperatureInFile();
		p.testAverageTemperatureWithHighHumidityInFile();
	}

}
