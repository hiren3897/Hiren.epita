package hiren.Practical;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import hiren.Practical.CSVModel;

public class Main {
	
	public static List<CSVModel> datalist;
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    //CSV file header
    private static final String FILE_HEADER = "ADDRESS,CITY,COUNTRY,NAME";

	public static void main(String[] args) throws IOException {
		
		CSVEntry DAO = new CSVEntry();
		List<Datafetch> list = DAO.readAll();
		for (Datafetch datafetch : list) {
			System.out.println(datafetch.getId()+ " " + datafetch.getAddress()+ " "  + datafetch.getCountry());
			
		}
		
		
		datalist = DAO.city_country_split();
		writeCsvFile("newCSV.csv");

	}
	
	public static void writeCsvFile(String filename) throws IOException {
		PrintWriter pw = new PrintWriter(new FileWriter(filename));
		pw.println(FILE_HEADER.toString());
		
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
