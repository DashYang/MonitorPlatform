package monitor.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	public static String getValue(String key)
	{
		ClassLoader loader = ConfigReader.class.getClassLoader();
		InputStream input = loader.getResourceAsStream("/config.properties");
        Properties prop = new Properties(); 
        try {
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        String value = "";
        value = (String) prop.get(key);
        return value;
	}
}