package hiren.Practical;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


import hiren.Practical.CSVModel;
import hiren.Practical.Datafetch;;

public class CSVEntry {
	public static List<CSVModel> datalist;
	private static final String COMMA_DELIMITER = ",";
 
    //CSV file header
    private static final String FILE_HEADER = "ADDRESS,CITY,COUNTRY,NAME";
 
	
	public static List<Datafetch> readAll() throws IOException{
		List<Datafetch> Alldata= new ArrayList<>();
		
		List<String> lines= Files.readAllLines(new File("C:\\eclipse\\supermarkets.csv").toPath());
		lines.remove(0); //because of header
		
		for (String line : lines) {
			String[]parts=line.split(",");
			Datafetch model = new Datafetch();
			model.setId(parts[0]);
			model.setAddress(parts[1]);
			model.setCountry(parts[2]);
			model.setName(parts[3]);
			model.setEmployees(parts[4]);
			Alldata.add(model);
			
		}
	
		 
		return Alldata;
	}

	public static List<CSVModel> city_country_split() throws IOException {
		List<CSVModel> allData = new ArrayList<>();
		List<Datafetch> result= readAll();
		for (int i = 0; i < result.size(); i++) {
			
			String totaldata = result.get(i).getCountry();
			String[] divide = totaldata.split(":");
			String city = divide[0];
			String country = divide[1];
			CSVModel csvdata = new CSVModel();
			csvdata.setCSV_City(city);
			csvdata.setCSV_Country(country);
			csvdata.setCSV_address(result.get(i).getAddress());
			csvdata.setCSV_Name(result.get(i).getName());
			allData.add(csvdata);
			
		}
		return allData;
		
	}
	 
	public static void writeCsvFile(String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(filename));
		pw.println(FILE_HEADER.toString());
		datalist = city_country_split();
		for (CSVModel dataModel : datalist) {
			pw.print(dataModel.getCSV_address());
			pw.print(COMMA_DELIMITER);
			pw.print(dataModel.getCSV_City());
			pw.print(COMMA_DELIMITER);
			pw.print(dataModel.getCSV_Country());
			pw.print(COMMA_DELIMITER);
			pw.println(dataModel.getCSV_Name());
		}
		pw.flush();
		pw.close();
	}
	
	

}
