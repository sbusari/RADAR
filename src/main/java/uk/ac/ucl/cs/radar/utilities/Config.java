package uk.ac.ucl.cs.radar.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties properties;
	
	private  void intantiateConfig( String configFile){
		try {
			if (properties == null){
				properties = new Properties();
				properties.load(new FileInputStream(configFile));
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public  String getConfig (String filePath, String key){
		intantiateConfig(filePath);
		String result = properties.getProperty(key); 
		if (result == null)
			  throw new IllegalArgumentException(key.toUpperCase() + " name cannot be found.");
		return result;		
	}
	
	public  String getConfig (String filePath, String key, String defaultValue){
		intantiateConfig(filePath);
		String output = properties.getProperty(key);
		return (output != null ? output : defaultValue);
	}

	
	
}

