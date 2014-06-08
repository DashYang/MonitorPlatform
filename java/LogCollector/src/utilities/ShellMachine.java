package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;


public class ShellMachine {
	private static Logger logger = Logger.getLogger(ShellMachine.class);
	public static ArrayList<String> run(String[] command, String directory) {
		ArrayList<String> processList = new ArrayList<String>();
		try {
			ProcessBuilder builder = new ProcessBuilder(command);
			if (directory != null)
				builder.directory(new File(directory));
			builder.redirectErrorStream(true);
			logger.info("file path " + directory);
			for(int i = 0; i < command.length;i ++){
				logger.info(command[i]);
			}
			Process process = builder.start();
			// 得到命令执行后的结果
			BufferedReader input = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			String line = "";
			while ((line = input.readLine()) != null) {
				processList.add(line);
			}
			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return processList;
	}
}
