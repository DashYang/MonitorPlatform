package dash.server;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import dash.tools.PropertiesReader;
import dash.tools.ShellMachine;

public class Collector {
	private static Logger logger = Logger.getLogger(Collector.class);
	
	public static ArrayList<String> getInfoByKey(String key){
		String directory = PropertiesReader.getValueFromConfig("shellpath");
		logger.info("request key = " + key);
		String cmd = PropertiesReader.getValueFromShellScirpt(key);
		String[] command = {"sh",cmd};
		ArrayList<String> result = ShellMachine.run(command, directory);
		logger.info(result);
		return result;
	}
	
	public static ArrayList<String> collectKeys(){
		ArrayList<String> keyList = new ArrayList<String>();
		keyList= PropertiesReader.getAllKeysFromShellScript();
		return keyList;
	}
	
	public static ArrayList<String> executeCommand(ArrayList<String> cmds){
		String directory = PropertiesReader.getValueFromConfig("execshellpath");
		
		logger.info("execute command = " + cmds);
		String cmd = PropertiesReader.getValueFromShellScirpt(cmds.get(1));
		
		logger.info("get value from properties" + cmd);
		
		cmds.set(1, cmd);
		int size = cmds.size();
		String[] command = (String[]) cmds.toArray(new String[size]);
		
		logger.info("become string " + command);
		ArrayList<String> result = ShellMachine.run(command, directory);
		return result;
	}
	
	public static void main(String[] args){
		
	}
}
