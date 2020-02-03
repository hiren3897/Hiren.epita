package fr.epita.quiz.services.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	private static Properties properties = new Properties();
	private static boolean isInit = false;
	
	public String getValue(String key) throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(new File("Config.Properties")));
		
		
		return "";
		
	}

}
