package fr.epita.hiren.dataModeling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import fr.epita.hiren.dataModeling.fetchData;
public class TestSER1 {
	
	public static List<fetchData> readAll() throws IOException{
		List<fetchData> Alldata= new ArrayList<>();
		
		List<String> lines= Files.readAllLines(new File("/home/hiren.rathod/subject/data.csv").toPath());
		lines.remove(0); //because of header
		
		for (String line : lines) {
			String[]parts=line.split(",");
			fetchData model = new fetchData();
			model.setName(parts[0]);
			model.setSex(parts[1]);
			model.setAge(parts[2]);
			model.setHeight(parts[3]);
			model.setWeight(parts[4]);
			Alldata.add(model);
			
		}
	
		 
		return Alldata;
	}
	
	public static List<fetchData> test() throws IOException{
		List<fetchData> result= readAll();
		
		for (int i = 1; i < 2; i++) {
			
			System.out.println(result.get(i).getName() +
					result.get(i).getSex() + 
					result.get(i).getAge() + 
					result.get(i).getHeight() + 
					result.get(i).getWeight());
			
		}
		return result;
		
	}
	
	

}
