package hiren.Practical;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


import hiren.Practical.CSVModel;

public class Main {
	
	

	public static void main(String[] args) throws IOException {
		
		
		List<Datafetch> list = CSVEntry.readAll();
		for (Datafetch datafetch : list) {
			System.out.println(datafetch.getId()+ " " + datafetch.getAddress()+ " "  + datafetch.getCountry());	
		}
		
		
		CSVEntry.writeCsvFile("newCSV1.csv");

	}
	

}
