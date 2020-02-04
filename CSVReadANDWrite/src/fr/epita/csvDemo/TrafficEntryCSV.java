package fr.epita.csvDemo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class TrafficEntryCSV {
	
	 public List<fetchData> readAll(){
	        List<fetchData>alldata= new ArrayList<>();
	        try {
	            List<String> lines=Files.readAllLines(new File("C:\\eclipse\\Average_Daily_Traffic_Counts.csv").toPath());
	            //because of headers
	            lines.remove(0);
	            for (String line:lines){
	                String[]parts=line.split(",");
	                fetchData model= new fetchData();
	                model.setId(parts[0]);
	                model.setLocaladdress(parts[1]);
	                model.setStreet(parts[2]);
	                model.setDateofcount(parts[3]);
	                model.setTotal_vehicle(Integer.parseInt(parts[4]));
	                model.setVehiclevolumn(parts[5]);
	                model.setLatitude(parts[6]);
	                model.setLongitude(parts[7]);
	                model.setLocation(parts[8]);
	                alldata.add(model);
	            }
	            return alldata;
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return alldata;
	    }

}
