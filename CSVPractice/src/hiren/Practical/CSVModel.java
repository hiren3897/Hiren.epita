package hiren.Practical;

import java.util.List;


import hiren.Practical.Datafetch;;

public class CSVModel {
	
	String CSV_Name;
	String CSV_City;
	String CSV_State;
	String CSV_Country;
	String CSV_address;
	public String getCSV_Name() {
		return CSV_Name;
	}
	public void setCSV_Name(String cSV_Name) {
		CSV_Name = cSV_Name;
	}
	public String getCSV_City() {
		return CSV_City;
	}
	public void setCSV_City(String cSV_City) {
		CSV_City = cSV_City;
	}
	public String getCSV_State() {
		return CSV_State;
	}
	public void setCSV_State(String cSV_State) {
		CSV_State = cSV_State;
	}
	public String getCSV_Country() {
		return CSV_Country;
	}
	public void setCSV_Country(String cSV_Country) {
		CSV_Country = cSV_Country;
	}
	public String getCSV_address() {
		return CSV_address;
	}
	public void setCSV_address(String cSV_address) {
		CSV_address = cSV_address;
	}
	
	public CSVModel() {
		
	}
	
	public List<Datafetch> setData(List<Datafetch> list) {
		CSVModel dataSet= new CSVModel();
		for (int i = 0; i < list.size(); i++) {
			
			String id = list.get(i).getId();
			String address = list.get(i).getAddress();
			
		}
		return list;
		
	}
	

}
