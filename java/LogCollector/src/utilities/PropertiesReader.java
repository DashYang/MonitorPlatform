package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static String path = "config.properties";
	public static String getValue(String key)
	{
		String value = "";
		try {
			InputStream is = new FileInputStream(path); 
	        //创建一个Properties容器 
	        Properties prop = new Properties(); 
	        //从流中加载properties文件信息 
	        prop.load(is); 
	        value = (String) prop.get(key);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
        return value; 
	}
}