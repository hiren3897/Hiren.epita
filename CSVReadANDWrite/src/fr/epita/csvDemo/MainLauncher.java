package fr.epita.csvDemo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.ToIntFunction;

public class MainLauncher {
	
	public static List<NewCSVDataModel>datalist;
	private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";

    //CSV file header
    private static final String FILE_HEADER = "Streetindex,Total_Vehicle,Northvolume,SouthVolume,Day,Month";

	public static void main(String[] args) throws IOException {
		
		      TrafficEntryCSV dao=new TrafficEntryCSV();
		      List<fetchData>list=dao.readAll();
		      System.out.println("list size: "+ list.size());
		      Integer count=0;
		      for(fetchData data: list){
		          count+=data.getTotal_vehicle();
		      }
		      // second method
		     // long count2=list.stream().mapToInt(FetchData::getTotal_vehicle).sum();
		        datalist=new ArrayList<>();
		       double average=calculateAverageOn(list,fetchData::getTotal_vehicle);
		       //int count2=calculateCountOn(list,fetchData::getTotal_vehicle);
		       LinkedHashMap<String ,Integer>map=setindex(list);
		        volumesplit(list);
		       // datesplit(list);
		        totalvehiclepassing(list);
		        System.out.println("total passing vechicle"+ count);
		        System.out.println("average passing vechicle"+ average);
		        System.out.println("index on street"+ map);
		        writeCsvFile("NewDataList");
		    
	}    
		    private static void totalvehiclepassing(List<fetchData> list) {
		        for(int i=0;i<list.size();i++){
		            NewCSVDataModel dataModel=new NewCSVDataModel();
		            dataModel.setTotalvehicle(String.valueOf(list.get(i).getTotal_vehicle()));
		        }
		    }

		    private static  double calculateAverageOn(List<fetchData>list, ToIntFunction<fetchData>function){
		       double average=list
		       .stream()
		       .mapToInt(function)
		       .average()
		       .getAsDouble();
		       return average;
		    }

		    private static  int calculateCountOn(List<fetchData>list, ToIntFunction<fetchData>function){
		        int count=list
		                .stream()
		                .mapToInt(function)
		                .sum();
		        return count;
		    }

		    private static LinkedHashMap setindex(List<fetchData>list){
		        LinkedHashMap<Integer,String> map=new LinkedHashMap<>();
		        int id=1;
		        for(int i=0;i<list.size();i++){
		            NewCSVDataModel dataModel=new NewCSVDataModel();
		            String street=list.get(i).getStreet();
		            if(!map.containsKey(street)) {
		                map.put(id, street);
		                id = id + 1;
		                dataModel.setStreetindex(String.valueOf(id));
		                datalist.add(dataModel);
		            }
		        }
		       return map;
		    }

		    private static  void volumesplit(List<fetchData>list){

		        for(int i=0;i<list.size();i++){
		            NewCSVDataModel dataModel=new NewCSVDataModel();
		            String totalvolume = list.get(i).getVehiclevolumn();
		            String[]vol=totalvolume.split("/");
		            String north=vol[0];
		            String south=vol[1];
		            String[]nvol=north.split(":");
		            String[]svol=north.split(":");
		            String northint=nvol[1];
		            String southint=svol[1];
		            dataModel.setNorthdata(northint);
		            dataModel.setSouthdata(southint);
		        }
		    }

		   /* private static  void datesplit(List<fetchData>list){
		        for(int i=0;i<list.size();i++){
		            NewCSVDataModel dataModel=new NewCSVDataModel();
		            String date=list.get(i).getDateofcount();
		            String[]vol=date.split("/");
		            String day=vol[0];
		            String month = vol[1];
		            dataModel.setDay(day);
		            dataModel.setMonth(month);

		        }
		    }*/
		    public static void writeCsvFile(String fileName) throws IOException {
		        PrintWriter pw = new PrintWriter(new FileWriter(fileName));
		        try {
		           // pw = new FileWriter(fileName);
		            //Write the CSV file header
		            pw.print(FILE_HEADER.toString());
		            //Add a new line separator after the header
		            pw.print(NEW_LINE_SEPARATOR);
		            //Write a new student object list to the CSV file
		            for (NewCSVDataModel dataModel : datalist) {
		                pw.print(String.valueOf(dataModel.getStreetindex()));
		                pw.print(COMMA_DELIMITER);
		                pw.print(String.valueOf(dataModel.getTotalvehicle()));
		                pw.print(COMMA_DELIMITER);
		                pw.print(String.valueOf(dataModel.getNorthdata()));
		                pw.print(COMMA_DELIMITER);
		                pw.print(String.valueOf(dataModel.getSouthdata()));
		                pw.print(COMMA_DELIMITER);
		                pw.print(String.valueOf(dataModel.getDay()));
		                pw.print(COMMA_DELIMITER);
		                pw.print(String.valueOf(dataModel.getMonth()));
		                pw.print(NEW_LINE_SEPARATOR);
		            }
		            System.out.println("CSV file was created successfully !!!");
		        } catch (Exception e) {
		            System.out.println("Error in CsvFileWriter !!!");
		            e.printStackTrace();
		        } finally {
		            pw.flush();
					pw.close();
		        }
		    }

	

}
