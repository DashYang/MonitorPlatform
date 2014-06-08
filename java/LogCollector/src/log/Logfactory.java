package log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import database.Isqltool;
import database.StatusManage;

import storage.Statusunit;
import utilities.FileReader;
import utilities.PropertiesReader;
import utilities.ShellMachine;

public class Logfactory {
	private static Logger logger = Logger.getLogger(Logfactory.class);
	public static void readstat(){
		String path = PropertiesReader.getValue("logpath") + "/"+ PropertiesReader.getValue("readfile");
		logger.info(path);
		ArrayList<String> logs = FileReader.getfiles(path);
		ArrayList<Statusunit> statusunits = new ArrayList<Statusunit>();
		Isqltool tool = new StatusManage();
		for(String tmp:logs){
			Statusunit statusunit = new Statusunit(tmp);
			tool.add(statusunit);
		}
	}
	public static void writestat(){
		String cmd = PropertiesReader.getValue("shellscript");
		String directory = PropertiesReader.getValue("shellpath");
		String[] command = {"bash",cmd,"&"};
		ShellMachine.run(command, directory);
		logger.info("记录脚本启动");
	}
	
	private static String getDBLatestupdatetime(String date,String name){
		Isqltool tool = new StatusManage();
		ArrayList<Object> result = tool.show(date, name, true);
		if(result == null || result.size() <= 0)
			return " 00:00:00";
		else{
			Statusunit unit = (Statusunit) result.get(0);
			String yearandhour = unit.getDate();
			return " " + yearandhour.split(" ")[1];
		}
			
	}
	
	public static void asyncreadstat(){
		Date date = new Date();
		String hostname = PropertiesReader.getValue("hostname");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat totaldf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currenttime = df.format(date); 
		logger.info("当前日期 " + currenttime);
		String filepath = PropertiesReader.getValue("logpath") + "/" + currenttime + ".stat";
		String begintime = getDBLatestupdatetime(currenttime,hostname);
		logger.info("此文件在数据库最后更新时间" + begintime);
		String lastreadtimestr = currenttime + begintime;
		Calendar lastreadtime = Calendar.getInstance();
		try {
			lastreadtime.setTime(totaldf.parse(lastreadtimestr));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Isqltool tool = new StatusManage();
		while(true){
			logger.info("读取文件  " + filepath);
			logger.info("上次读取时间" + lastreadtime.getTime().toString());
			if(FileReader.isStatExists(filepath)){
				ArrayList<String> logs = FileReader.asyncfile(filepath, lastreadtime);
				for(String tmp:logs){
					Statusunit statusunit = new Statusunit(tmp);
					String timestr = FileReader.getTime(tmp);
					try {
						Calendar tmptime = Calendar.getInstance();
						tmptime.setTime(totaldf.parse(timestr));
						if(tmptime.after(lastreadtime)){
							lastreadtime = tmptime;
						}
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					logger.info(tmp);
					tool.add(statusunit);
				}
			}else{
				logger.info("文件不存在，请确认脚本是否开启");
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
}
