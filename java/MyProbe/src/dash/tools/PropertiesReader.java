package dash.tools;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

public class PropertiesReader {
	private static String configpath = "config.properties";
	private static String shellscriptpath = "shellscript.properties";
	private  static String getValue(String key,String path){
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
	
	public static String getValueFromConfig(String key){
		return getValue(key, configpath);
	}
	
	public static String getValueFromShellScirpt(String key){
		return getValue(key, shellscriptpath);
	}
	
	public static ArrayList<String> getAllKeysFromShellScript() {
		ArrayList<String> keysList = new ArrayList<String>();
		try {
			InputStream is = new FileInputStream(shellscriptpath); 
	        //创建一个Properties容器 
	        Properties prop = new Properties(); 
	        //从流中加载properties文件信息 
	        prop.load(is); 
	        for(Object key:prop.keySet()){
	        	keysList.add(key.toString());
	        	System.out.println("key:"+key.toString());
	        }
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
        return keysList; 
	}
}
